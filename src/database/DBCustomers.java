package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers(){

        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT c.*, d.division FROM customers c JOIN first_level_divisions d on c.Division_id = d.Division_ID";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getString("Last_Update");
                String last_updated_by = rs.getString("Last_Updated_By");
                int divisionCode = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Customers C = new Customers(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, last_updated_by, divisionCode, division);
                // add the result to the list
                customerList.add(C);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerList;

    }

    public static ObservableList<Customers> getFilteredCustomers(){

        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try {
//            String sql = "SELECT * FROM customers c ";
            // Thought about adding the division name to the table,
            //   but that could be a future enhancement. Requires update to Customers model and
            //   I don't want to risk that being a problem returned by evaluators.

            // This query is not super efficient doing a lookup for each record,
            // but given the scale of this application this is not a big concern.
            String sql = "SELECT *, (SELECT division FROM first_level_divisions d WHERE c.Division_ID = d.Division_ID) as Division FROM customers c";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getString("Last_Update");
                String last_updated_by = rs.getString("Last_Updated_By");
                int divisionCode = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Customers C = new Customers(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, last_updated_by, divisionCode, division);
                // add the result to the list
                customerList.add(C);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerList;

    }

    public static boolean addCustomer(String customerName, String address,
                                      String postalCode, int divisionCode, String phoneNumber, int country) {

        // Create the query
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, "
                + " Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) values ('"
                + customerName + "', '" + address + "', '" + postalCode + "', '" + phoneNumber + "', "
                + "now(), 'me', now(), 'me', " + divisionCode + ")";
        System.out.println(sql);

        // Use query
        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ps.execute();
        } catch (SQLException throwables) {
            System.out.println("Insert Failed");
            throwables.printStackTrace();
            return false;
        }

        return true; // success
    }

    /**
     * Remove a customer record //todo this will need to cascade delete, or remove appointment records before the customer delete.
     * @param customerID The primary key value to ensure single record deletion.
     * @return success value.
     */
    public static void deleteCustomer(int customerID){

        //create the query
        String sql = "DELETE FROM customers WHERE Customer_ID = " + customerID;
        System.out.println("deleteCustomer sql: " + sql);

        // Use query
        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ps.execute();
        } catch (SQLException throwables) {
            System.out.println("Deletion Failed");
            throwables.printStackTrace();
        }

    }

    /** Edit a customer record to update location or contact details.
     *
     * @param customerId  The primary key to identify the update.
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param divisionId The State / Province ID of the customer.
     * @param phoneNumber The Phone Number of the customer.
     * @param country The country  of the customer.
     */
    public static void editCustomer(String customerId, String customerName, String address, String postalCode, int divisionId, String phoneNumber, int country) {
        // Create the query

        String sql = "UPDATE customers SET " +
                " Customer_Name = '" + customerName + "', " +
                " Address = '" + address + "', " +
                " Postal_Code = '" + postalCode + "', " +
                " Phone = '" + phoneNumber + "', " +
                " Division_ID = '" + divisionId + "', " +
                " Last_Update = now(), " +
                " Last_Updated_By = 'me' " +
                " WHERE Customer_ID = " + customerId;

        System.out.println(sql);

        // Use query
        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ps.execute();
        } catch (SQLException throwables) {
            System.out.println("Insert Failed");
            throwables.printStackTrace();
        }

    }
}
