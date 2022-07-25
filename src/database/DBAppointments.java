package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
/**
 * The DB Appointments database class.
 */
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

    /**
     * Constructor for the DBAppointments class.
     * @param appointmentId Unique identifier.
     * @param title Appointment title.
     * @param description Appointment description.
     * @param location Appointment location.
     * @param type Appointment type.
     * @param start Appointment start date.
     * @param end Appointment end date.
     * @param customerId Appointment customer id.
     * @param userId Appointment user id.
     * @param contactId Appointment contact id.
     * @return A boolean success value.
     */
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

    /**
     * Update the appointment with new values.
     * @param appointmentId The appointment id.
     * @param title The appointment title.
     * @param description The appointment description.
     * @param location The appointment location.
     * @param type The appointment type.
     * @param startTime The appointment start time.
     * @param endTime The appointment end time.
     * @param customerId The appointment customer id.
     * @param userId The appointment user id.
     * @param contactId The appointment contact id.
     */
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

    /**
     * Delete the selected appointment.
     * @param appointmentID The ID of the appointment.
     */
    public static void deleteAppointment(int appointmentID){

        //create the query
        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentID;
        String commit = "Commit";
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

    /**
     * Get results for reporting by month.
     * @return The reporting results observable.
     */
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

    /**
     * Get results for reporting by week.
     * @return The reporting results observable.
     */
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

    /**
     * Check if appointment time is available.
     * @param appointmentId The appointment being compared, so it is exluced from comparison.
     * @param start Proposed appointment start time.
     * @param end Proposed appointment end time.
     * @param customerId The appointment customer id.
     * @return The boolean result.
     */
    public static boolean isOverlapAppointment (int appointmentId, LocalDateTime start, LocalDateTime end, String customerId) {

        // if new or edit, appointment id will be different than what is sent to be conflict.
        String sql = "SELECT * FROM appointments WHERE Appointment_ID != " + appointmentId +
                " AND Customer_ID = " + customerId +
        " AND (( '" + start + "' BETWEEN Start AND End) OR ( '" + end + "' BETWEEN Start AND End ) /* Start or End lands during existing appointment */ " +
        "   OR ( '" + start + "' < Start AND '" + end + "' > End ) /* Start AND End encapsulate existing appointment */ " +
        "   OR ( '" + start + "' = Start ) /* START matches Start */ " +
        "   OR ( '" +  end  + "' = End ))  /* End matches End */ ";

//        " (( '" + start + "' <= Start AND '" + end + "' > End) OR " + "  ( '" + start + "' >= Start AND '" + end + "' < End))";
//        AND (( '2022-07-02T12:00' BETWEEN Start AND End) OR ( '2022-07-02T16:00' BETWEEN Start AND End ) -- Start or End lands during existing appointment
//        OR ( '2022-07-02T12:00' < Start AND '2022-07-02T16:00' > End ) -- Start AND End encapsulate existing appointment
//        OR ( '2022-07-02T12:00' = Start ) -- START matches Start
//        OR ( '2022-07-02T16:00' = End ) -- End matches End

        System.out.println(sql);

        /*  list of test criteria from the instruction video
          10:00am - 11:30am
          10:30am - 11:30am
          10:00am - 11:00am
          10:30am - 11:00am
           9:30am - 11:00am
           9:30am - 10:30am
           9:30am - 11:30am
         */

        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            // gets the results set

            ResultSet rs = ps.executeQuery();

            // if a result is returned, conflict found
            while ( rs.next() ) {
                return true;
            }

        } catch (SQLException throwables) {
            System.out.println("Bad SQL for isOverlapAppointment");
            throwables.printStackTrace();
        }

        // no result found, no conflict
        return false;
    }

    /**
     * Determine if appointment is within time allowed.
     * @param startDate Start date entered by user.
     * @param startTime Start time entered by user.
     * @param endTime End time entered by user.
     * @return the boolean result.
     */
    public static boolean isDuringBusinessHours(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        System.out.println("entered isDuringBusinessHours");
        // EST zone id
//        ZoneId z = ZoneId.of( "America/New_York" );

//        Parse as a LocalDateTime as your input lacks an indicator of offset-from-UTC or time zone.
        LocalDateTime ldtStart = LocalDateTime.of(startDate, startTime) ;
        LocalDateTime ldtEnd   = LocalDateTime.of(startDate, endTime) ;

//        Apply a time zone if you are certain it was intended for this input.
        ZoneId z = ZoneId.of( "America/New_York" ) ;
        ZonedDateTime zdtStart = ZonedDateTime.of(ldtStart, ZoneId.of("America/New_York"));
        ZonedDateTime zdtEnd   = ZonedDateTime.of(ldtEnd,   ZoneId.of("America/New_York"));

        LocalTime OPEN = LocalTime.of(8, 0);
        LocalTime CLOSE = LocalTime.of(18, 0);

        LocalTime localTimeStart = zdtStart.toLocalTime ();
        LocalTime localTimeEnd   = zdtEnd.toLocalTime ();
        if (
                ( !localTimeStart.isBefore( OPEN ) )  // STARTS AFTER STORE OPEN
                && (localTimeEnd.isAfter( OPEN ) ) // ENDS AFTER STORE OPEN
                && (localTimeStart.isBefore( CLOSE ) )  // STARTS BEFORE STORE CLOSE
                && (!localTimeEnd.isAfter( CLOSE ) )  // ENDS BEFORE STORE CLOSE
            )  {
            System.out.println("evaluates to true");
            return true;
        }
        System.out.println("evaluates to false");
        return false;
    }

    /**
     * Warn user of appointment starting very soon.
     * @param userName The user logging into the system.
     * @return String stating results of inquiry.
     */
    public static String isNextAppointmentIn15Minutes(String userName) {

        try {

            LocalDateTime currentDateTime = LocalDateTime.now();

            // create query to find appointments
            String sql = "SELECT * FROM client_schedule.appointments WHERE User_ID = (  SELECT user_id FROM client_schedule.users WHERE user_name = '" + userName + "')";

            System.out.println(sql);

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            // gets the results set

            ResultSet rs = ps.executeQuery();

            // if a result is returned, loop through and compare appointment times to current time. Adjusted for timezones.
            while (rs.next()) {

                int appointmentId = rs.getInt("Appointment_ID");
                Timestamp start = rs.getTimestamp("Start");

                if (start.toLocalDateTime().isAfter(currentDateTime)
                        && start.toLocalDateTime().isBefore(currentDateTime.plusMinutes(15))) {
                    return "Appointment: " + appointmentId + " starts soon (" + start + "), please consult your schedule." ;
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return "No appointments starting within the next 15 minutes."; // no appointment found in next 15 minutes
    }

    /**
     * Gather results for reporting.
     * @return The built report string.
     */
    public static String getCustomerAppointmentsByTypeAndMonth() {
        String returnString ="";
        try {

            String sql = "SELECT count(appointment_id) as count, type, MONTHNAME(start) as month FROM client_schedule.appointments GROUP BY 2, 3 ORDER BY 2, 3";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                returnString += rs.getString("count") + " " + rs.getString("type") + " " + rs.getString("month") + "\n";
            }
            System.out.println(returnString);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnString;
    }

    /**
     * Gather results for reporting.
     * @return The built report string.
     */
    public static String getScheduleByContact() {
        String returnString ="SCHEDULE BY CONTACT \n Sorted by Customer_ID, Start \n";
        try {

            String sql = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM client_schedule.appointments order by Customer_ID, Start";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                returnString += rs.getString("Appointment_ID") + " " +
                        rs.getString("Title") + " " +
                        rs.getString("Type") + " " +
                        rs.getString("Description") + " " +
                        rs.getString("Start") + " " +
                        rs.getString("End") + " " +
                        rs.getString("Customer_ID") + " " +
                        "\n";
            }
            System.out.println(returnString);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnString;
    }

    /**
     * Gather results for reporting.
     * @return The built report string.
     */
    public static String getRegions() {
        String returnString ="List of Divisions \n Sorted by Division_ID, Start \n";
        try {

            String sql = "SELECT Division_ID, Division FROM client_schedule.first_level_divisions order by 1";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                returnString += rs.getString("Division_ID") + " " +
                        rs.getString("Division") + " " +
                        "\n";
            }
            System.out.println(returnString);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnString;
    }
}
