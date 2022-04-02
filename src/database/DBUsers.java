package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

    public static ObservableList<Users> getAllUsers(){

        ObservableList<Users> userlist = FXCollections.observableArrayList();

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
                userlist.add(U);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userlist;
    }

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
                    return rs.getInt("User_ID");
                    }
                }
            return -1; // not found

        } catch (SQLException throwables) {
            System.out.println("not found");
                throwables.printStackTrace();
        }

        return -1; // if we got here, no match
    }

}
