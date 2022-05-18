package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

    private static String currentUser;
    private static int currentUserId;

    /**
     * Obtain list of all records from users table.
     * @return userList The list of all users. This is here for completeness, but a bad idea security wise real world.
     *      Especially with the passwords not stored in a hash.
     */
    public static ObservableList<Users> getAllUsers(){

        ObservableList<Users> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set
            while (rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users U = new Users(userId, userName, password);
                // add the result to the list
                userList.add(U);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    /**
     * Check if granting access is appropriate. This could return a boolean, but
     * then a separate call would be needed to get the User_ID for appointments.
     *
     * @param userName The account identifier provided by the user.
     * @param password The password provided by the user.
     * @return return integer value User_ID if valid, -1 if not.
     */
    public static int validateLogin(String userName, String password){

        // Create the query
        String sql = "SELECT * FROM users where user_name = '" + userName + "' AND password = '" + password + "'";

        // Use query
        try {
            // connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through results set (should only be one, so no loop)
            if (rs.next()) {
                if (rs.getString("User_Name").equals(userName) &&
                            rs.getString("Password").equalsIgnoreCase(password)) {
                    // login match
                    setCurrentUser(rs.getString("User_Name"));
                    setCurrentUserId(rs.getInt("User_ID"));

                    return currentUserId;
                    }
                }
            return -1; // not found

        } catch (SQLException throwables) {
            System.out.println("not found");
                throwables.printStackTrace();
        }

        return -1; // if we got here, no match
    }

    public static void setCurrentUser(String currentUser) {
        DBUsers.currentUser = currentUser;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUserId(int userId) {
        DBUsers.currentUserId = userId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
}
