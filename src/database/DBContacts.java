package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * The DB Contacts database class.
 */
public class DBContacts {

    /**
     * Query the database for all the contacts.
     * @return The ObservableList.
     */
    public static ObservableList<Contacts> getAllContacts(){

        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM CONTACTS";

            //connects to the database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts C = new Contacts(contactId, contactName, email);
                // add the result to the list
                contactList.add(C);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactList;

    }
}
