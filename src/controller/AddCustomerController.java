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

/**
 * Controller for the Add Customer Page.
 */
public class AddCustomerController implements Initializable {

    /**
     * fxml step
     */
    Stage stage;
    /**
     * fxml step
     */
    Parent scene;

    /**
     * Save button object.
     */
    public Button SaveBtn;
    /**
     * Cancel button object.
     */
    public Button CancelBtn;

    /**
     * Object to contain the page objects.
     */
    @FXML
    private AnchorPane addCustomerAnchorPane;

    // customerSequence is used for new customerId values.
    /**
     * Disabled text field.
     */
    @FXML
    private TextField customerIdTxt;

    /**
     * Data entry field for customer name.
     */
    @FXML
    private TextField customerNameTxt;

    /**
     * Data entry field for customer address.
     */
    @FXML
    private TextField addressTxt;

    /**
     * Data entry field for customer postal code.
     */
    @FXML
    private TextField postalCodeTxt;

    /**
     * Data entry field for customer phone number.
     */
    @FXML
    private TextField phoneTxt;

    /**
     * Data entry field for customer division (state, territory, etc).
     */
    @FXML
    private ComboBox<Division> divisionsComboBox;

    /**
     * Data entry field for customer country.
     */
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
