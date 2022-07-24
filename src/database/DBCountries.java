package database;

import model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
/**
 * The DB Countries database class.
 */
public class DBCountries {

    /**
     * This returns all of the countries used to populate the country combo box.
     * @return The list of Countries
     */
    public static ObservableList<Countries> getAllCountries(){

        ObservableList<Countries> cList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";

            // connects to database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through result set
            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                // add the result to the list
                cList.add(C);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cList;
    }
}
