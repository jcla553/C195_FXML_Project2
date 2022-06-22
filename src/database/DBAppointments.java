package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class DBAppointments {

    /**
     * Get all of the appointments for display in table.
     * @return All appointments.
     */
    public static ObservableList<Appointments> getAllAppointments(){

        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentId, title, description, location, type, start, end,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);

                // add the results to the list
                appointmentList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentList;
    }

    public static boolean addAppointment(int appointmentId, String title, String description, String location, String type, ZonedDateTime start, ZonedDateTime end,
                                         String customerId, String userId, int contactId) {

        //create the query
        String sql = "Insert into appointments (Title, Description, Location, Type, "
                + "Start, End, "
                + "Create_Date, Created_By, Last_Update, Last_Updated_By, "
                + "Customer_ID, User_ID, Contact_ID) values ('"
                + title + "', '" + description + "', '" + location + "', '" + type + "', '"
                + Timestamp.valueOf(start.toLocalDateTime()) + "', '" + Timestamp.valueOf(end.toLocalDateTime()) + "', "
                + "now(), '" +  userId + "', " + " now(), '" + userId + "', '"
                + customerId + "', '" + DBUsers.getCurrentUserId() + "', '" + contactId + "')";
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


    public static void updateAppointment(String appointmentId, String title, String description, String location, String type,
                                         ZonedDateTime startTime, ZonedDateTime endTime, String customerId, String userId, int contactId) {

        //create the query

        String sql = "Update appointments SET " +
        " Title = '" +  title + "', " +
        " Description = '" +  description + "', " +
        " Location = '" +  location + "', " +
        " Type = '" +  type + "', " +
        " Start = '" +  Timestamp.valueOf(startTime.toLocalDateTime()) + "', " +
        " End = '" +  Timestamp.valueOf(endTime.toLocalDateTime()) + "', " +
        " Last_Update = now(), " +
        " Last_Updated_By = '" +  userId + "', " +
        " Customer_ID = '" +  customerId + "', " +
        " User_ID = '" +  DBUsers.getCurrentUserId() + "', " +
        " Contact_ID = '" +  contactId + "'" +
        " WHERE Appointment_ID = " + appointmentId;

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

    public static void deleteAppointment(int appointmentID){

        //create the query
        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentID;
        System.out.println("deleteAppointment sql: " + sql);

        // Use query
        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ps.execute();
        } catch (SQLException throwables) {
            System.out.println("Appointment Deletion Failed");
            throwables.printStackTrace();
        }

    }


    public static ObservableList<Appointments> getMonthAppointments() {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.appointments WHERE MONTH(start) = MONTH(now())";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentId, title, description, location, type, start, end,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);

                // add the results to the list
                appointmentList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentList;
    }

    public static ObservableList<Appointments> getWeekAppointments() {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.appointments WHERE YEARWEEK (start) = YEARWEEK (NOW());";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentId, title, description, location, type, start, end,
                        createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);

                // add the results to the list
                appointmentList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentList;
    }
}
