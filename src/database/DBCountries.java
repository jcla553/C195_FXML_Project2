package database;

import database.DBConnection;
import model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

    public static ObservableList<Countries> getAllCountries(){

        ObservableList<Countries> clist = FXCollections.observableArrayList();

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
                clist.add(C);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }
}
