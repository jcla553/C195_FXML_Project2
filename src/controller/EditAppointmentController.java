package controller;

import database.DBAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private AnchorPane editAppointmentAnchorPane;

    // Edit field objects
    @FXML
    private TextField appointmentIdTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private ComboBox<Contacts> contactsComboBox;
    @FXML
    private TextField typeTxt;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Spinner startTimeSpinner;
    @FXML
    private Spinner endTimeSpinner;
    @FXML
    private TextField customerIDTxt;


    // Data Table Objects
    @FXML
    private TableView<Appointments> editAppointmentTableView;
    @FXML
    private TableColumn<Appointments, String> titleCol;
    @FXML
    private TableColumn<Appointments, String> descriptionCol;
    @FXML
    private TableColumn<Appointments, String> locationCol;
    @FXML
    private TableColumn<Appointments, String> typeCol;
    @FXML
    private TableColumn<Appointments, String> startCol;
    @FXML
    private TableColumn<Appointments, String> endCol;
    @FXML
    private TableColumn<Appointments, String> createDateCol;
    @FXML
    private TableColumn<Appointments, String> createdByCol;
    @FXML
    private TableColumn<Appointments, String> lastUpdateCol;
    @FXML
    private TableColumn<Appointments, String> lastUpdatedByCol;
    @FXML
    private TableColumn<Appointments, Integer> customerIDCol;
    @FXML
    private TableColumn<Appointments, Integer> userIDCol;
    @FXML
    private TableColumn<Appointments, Integer> contactIDCol;

    public void onRecordSelection(MouseEvent mouseEvent) {
        Appointments selectedAppointment = editAppointmentTableView.getSelectionModel().getSelectedItem();

        // populate the TextFields

        appointmentIdTxt.setText(String.valueOf(selectedAppointment.getAppointment_ID()));
        titleTxt.setText(String.valueOf(selectedAppointment.getTitle()));
        descriptionTxt.setText(String.valueOf(selectedAppointment.getDescription()));
        locationTxt.setText(String.valueOf(selectedAppointment.getLocation()));
        typeTxt.setText(String.valueOf(selectedAppointment.getType()));
        System.out.println("---------");
        System.out.println(selectedAppointment.getStart());

        System.out.println("1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("2");
        Timestamp startTime = selectedAppointment.getStart();
//        startDatePicker.setValue(startTime);  // TODO this is broken yo!!!!
//        LocalDate localDate = LocalDate.parse(selectedAppointment.getStart(), formatter);
        System.out.println("3");
//        System.out.println(selectedAppointment.getStart());
//        startDatePicker.setValue(localDate);
//        startDatePicker.setValue(selectedAppointment.getStart());
//        startDatePicker.setValue(selectedAppointment.getStart());

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
        // populate the Appointments table
        editAppointmentTableView.setItems(DBAppointments.getAllAppointments());
        // match TableView columns to the getter
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
    }

    public void onSaveButtonAction(ActionEvent event) {
    }

    public void onDeleteButtonAction(ActionEvent event) {
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
}
