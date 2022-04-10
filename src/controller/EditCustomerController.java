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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

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

    @FXML
    private TableView<Customers> editCustomerTableView;
    @FXML
    private TableColumn<Customers, String> customerNameCol;
    @FXML
    private TableColumn<Customers, String> addressCol;
    @FXML
    private TableColumn<Customers, String> postalCodeCol;
    @FXML
    private TableColumn<Customers, String> phoneCol;
    @FXML
    private TableColumn<Customers, Integer> divisionIDCol;


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

//            boolean successfulInsert = DBCustomers.editCustomer(customerName, address, postalCode,
//                                                    divisionCode, phoneNumber, country);
            System.out.println(customerName + " " + address + " " + postalCode + " " +
                    divisionCode + " " +  phoneNumber + " " + country);
            // todo fix this update to use the combo box values


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

        // populate the Customers table
        editCustomerTableView.setItems(DBCustomers.getAllCustomers());
        // match TableVIew column to the getter
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

    }

    /**
     * Filters the State / Division options by the selected Country.
     * @param ActionEvent Trigger action when country is selected by user.
     */
    public void onCountryClicked(ActionEvent ActionEvent) {
        divisionsComboBox.setItems(DBDivision.getFilteredDivisions(countriesComboBox.getValue().getId()));
    }
}
