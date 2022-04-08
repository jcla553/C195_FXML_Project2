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
            String sql = "SELECT * FROM customers";

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
                Customers C = new Customers(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, last_updated_by, divisionCode);
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
        }

            return true;  //TODO return success if insert successful.
    }
}
