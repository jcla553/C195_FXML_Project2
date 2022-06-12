package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCustomers;
import database.DBUsers;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
    private TextField typeTxt;
    @FXML
    private ComboBox<Contacts> contactComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField startTimeTxt;
    @FXML
    private TextField endTimeTxt;
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

        contactComboBox.getSelectionModel().select(selectedAppointment.getContact_ID());

        Timestamp startTimeTimestamp = selectedAppointment.getStart();
        System.out.println("Timestamp: " + startTimeTimestamp);
        LocalDate startDate = startTimeTimestamp.toLocalDateTime().toLocalDate();
        startDatePicker.setValue(startDate);
        LocalTime startTime = startTimeTimestamp.toLocalDateTime().toLocalTime(); // not set for timezone yet
        startTimeTxt.setText(startTime.toString());

        Timestamp endTimeTimestamp = selectedAppointment.getEnd();
        LocalTime endTime = endTimeTimestamp.toLocalDateTime().toLocalTime(); // not set for timezone yet
        endTimeTxt.setText(endTime.toString());

        customerIDTxt.setText(String.valueOf(selectedAppointment.getAppointment_ID()));

        // make time stuff converted for time zone
        // todo - make this a lamda lamda lamda
        // 1. Start with a LocalDateTime
        LocalDateTime my1 = startTimeTimestamp.toLocalDateTime();

        // 2. Convert to a ZonedDateTime at the origin ZoneId
        ZonedDateTime my2 = my1.atZone(ZoneId.of("UTC"));

        // 3. Convert that to a ZonedDateTime at the target Zoneid (withZoneSameInstant)
        ZonedDateTime my3 = my2.withZoneSameInstant(ZoneId.systemDefault());

        // 4. Convert that to the changed LocalDateTime
        LocalDateTime my4 = my3.toLocalDateTime();
        System.out.println("my4 = " + my4);

        LocalTime my5 = my4.toLocalTime();
        System.out.println("my5 = " + my5);

        Integer my5ish = my5.getHour();
        System.out.println("my5ish = " + my5ish);

        startTimeTxt.setText(my5ish.toString());

        // build again for endTimeTxt
        LocalDateTime my6 = endTimeTimestamp.toLocalDateTime();
        ZonedDateTime my7 = my6.atZone(ZoneId.of("UTC"));
        ZonedDateTime my8 = my7.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime my9 = my8.toLocalDateTime();
        LocalTime my10 = my9.toLocalTime();
        Integer my10ish = my10.getHour();

        endTimeTxt.setText(my10ish.toString());

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

        // provide values for contactsComboBox
        contactComboBox.setItems(DBContacts.getAllContacts());
    }

    @FXML
    public void onSaveButtonAction(ActionEvent event) throws IOException {
        String appointmentId = appointmentIdTxt.getText();
        String title = titleTxt.getText();
        String description = descriptionTxt.getText();
        String location = locationTxt.getText();
        int contactId = contactComboBox.getValue().getContactId();
        String type = typeTxt.getText();
        String customerId = customerIDTxt.getText();
        String userId = DBUsers.getCurrentUser();

        // date stuff
        LocalDate startDate = startDatePicker.getValue();
        System.out.println("startDate: " + startDate);

        LocalTime startTimeField = LocalTime.of(Integer.parseInt(startTimeTxt.getText()), 0, 0);
        LocalTime endTimeField = LocalTime.of(Integer.parseInt(startTimeTxt.getText()), 0, 0);

        // Make the spinner values actual time.
//        LocalTime startTimeSpinnerValue = LocalTime.of((Integer) startTimeSpinner.getValue(), 0, 0);
//        LocalTime endTimeSpinnerValue = LocalTime.of((Integer) endTimeSpinner.getValue(), 0, 0);

//        LocalDateTime nowDateTime = LocalDateTime.now();
//        System.out.println("nowDateTime: " + nowDateTime);

        // Make a ZonedDateTime object
        ZonedDateTime startTime = ZonedDateTime.of(startDate, startTimeField, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("startTime: " + startTime);

        ZonedDateTime endTime = ZonedDateTime.of(startDate, endTimeField, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("  endTime: " + endTime);

        // create new appointment using model method
        DBAppointments.updateAppointment(appointmentId, title, description, location, type, startTime, endTime, customerId, userId, contactId);

//        gotoPage(event, "/view/MainPage.fxml");
        gotoPage(event, "/view/EditAppointment.fxml");


    }

    public void onDeleteButtonAction(ActionEvent event) {
        Appointments isItSelected = editAppointmentTableView.getSelectionModel().getSelectedItem();
        if (isItSelected == null){
            popupError("Please select a record.");
        }

        // Confirm deletion is wanted
        Stage stage = (Stage) editAppointmentAnchorPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Only interaction with confirmation window
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        String type = typeTxt.getText();

        alert.setHeaderText("Record will be deleted.");
        alert.setContentText("Confirm if deletion desired.  Appointment is of type: " + type);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //Delete the record
            System.out.println("delete button");
            DBAppointments.deleteAppointment(editAppointmentTableView.getSelectionModel().getSelectedItem().getAppointment_ID());
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("CANCEL BUTTON PRESSED");
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
     * Simplified navigation
     * @param event button click is passed along.
     * @param fxmlPagePath The target .fmxl file.
     * @throws IOException Exception is thrown if file not available.
     */
    public void gotoPage(ActionEvent event, String fxmlPagePath) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(fxmlPagePath));
        stage.setScene(new Scene(scene));
    }

}
