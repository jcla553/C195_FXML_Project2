package controller;

import database.DBCountries;
import database.DBCustomers;
import database.DBDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Division;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import static main.Main.customerSequence;

public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    public Button SaveBtn;
    public Button CancelBtn;

    @FXML
    private AnchorPane addCustomerAnchorPane;

    // customerSequence is used for new customerId values.
    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private ComboBox<Division> divisionsComboBox;

    @FXML
    private ComboBox<Countries> countriesComboBox;


    /**
     * Save entry to database, navigate to main page.
     * @param event The save button press event.
     */
    public void onSaveButtonAction(ActionEvent event) {
        try{
//            int customerId = customerSequence++; // not provided by the user
            String customerName = customerNameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            int divisionCode = divisionsComboBox.getValue().getDivisionId();
            String phoneNumber = phoneTxt.getText();
            int country = countriesComboBox.getValue().getId();

            DBCustomers.addCustomer(customerName, address, postalCode,
                                    divisionCode, phoneNumber, country);

        } catch(NumberFormatException e) {
            popupError("Please enter valid value for each field.");
        }
    }

    /**
     * Navigate to the Main Page.
     * @param event Button Press event.
     * @throws IOException Error possible if target page does not exist.
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        stage.setScene(new Scene(scene));
    }

    /**
     * Moved the Alert creation into a method to allow a single line call.
     * @param contentText The context appropriate message for the user.
     */
    @FXML
    public void popupError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        countriesComboBox.setItems(DBCountries.getAllCountries());
        // set a default value to avoid error for onCountryClicked event.
        countriesComboBox.getSelectionModel().selectFirst();

        divisionsComboBox.setItems(DBDivision.getAllDivisions());
    }

    /**
     * Filters the State / Division options by the selected Country.
     * @param ActionEvent Trigger action when country is selected by user.
     */
    public void onCountryClicked(ActionEvent ActionEvent) {
        divisionsComboBox.setItems(DBDivision.getFilteredDivisions(countriesComboBox.getValue().getId()));
    }
}
