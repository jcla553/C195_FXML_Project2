package controller;

import database.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

import static main.Main.customerSequence;

public class AddCustomerController {

    Stage stage;
    Parent scene;

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
    private ComboBox stateCombo;

    @FXML
    private ComboBox countryCombo;


    /**
     * Save entry to database, navigate to main page.
     * @param event The save button press event.
     */
    public void onSaveButtonAction(ActionEvent event) {
        try{
            int customerId = customerSequence++; // not provided by the user
            String customerName = customerNameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            // int divisionCode = stateCombo // TODO get ID of combo selection
            String phoneNumber = phoneTxt.getText();
            //int country = countryCombo. // TODO get ID of combo selection

            boolean successfulInsert = DBCustomers.addCustomer(customerName, address, postalCode,
                                                    1, phoneNumber, 1);
            // todo fix this insert to use the combo box values


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
}
