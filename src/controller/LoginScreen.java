package controller;

import database.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Countries;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    public Label TheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized!");
        TheLabel.setText("Initialized!");
    }

    public void onSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("I am clicked");

        // TODO  this is from class demo, was used to test database connection
        ObservableList<Countries> countryList = DBCountries.getAllCountries();
        for(Countries C : countryList) {
            System.out.println("Country ID : " + C.getId() + " Name : " + C.getName());
        }
    }
}
