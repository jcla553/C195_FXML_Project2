package database;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBDivision {

    /**
     * This returns all of the divisions used to populate the division combo box.
     * @return The list of Divisions
     */
    public static ObservableList<Division> getAllDivisions() {

        ObservableList<Division> dList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";

            // connects to database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through result set
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString(("Division"));
                int countryId = rs.getInt("Country_ID");
                Division D = new Division(divisionId, divisionName, countryId);
                // add the result to the list
                dList.add(D);
            }

        } catch(SQLException throwables){
                throwables.printStackTrace();
        }

            return dList;
    }

    /**
     * Filtered list of Divisions based on Country Selection
     * @return The list of divisions for the provided country.
     */
    public static ObservableList<Division> getFilteredDivisions(int selectedCountryId) {

        ObservableList<Division> dList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions where Country_ID = " + selectedCountryId;

            // connects to database
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            // gets the results set
            ResultSet rs = ps.executeQuery();

            // walk through result set
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString(("Division"));
                int countryId = rs.getInt("Country_ID");
                Division D = new Division(divisionId, divisionName, countryId);
                // add the result to the list
                dList.add(D);
            }

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return dList;
    }

//    public static String getSpecificDivision(int specificDivision) {
//        ObservableList<Division> dList = FXCollections.observableArrayList();
//
//        try {
//            String sql = "SELECT Division FROM first_level_divisions where Division_ID = " + specificDivision;
//
//            // connects to database
//            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
//
//            // gets the results set
//            ResultSet rs = ps.executeQuery();
//
//            // get result
//            rs.next();
//
//            //                Division D = new Division(divisionName);
//                // add the result to the list
////                dList.add(D);
//
//            return rs.getString(("Division"));
//
//        } catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//
//    }
}




