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

/**
 * The edit appointment controller class.
 */
public class EditAppointmentController implements Initializable {

    /**
     * fxml step
     */
    Stage stage;
    /**
     * fxml step
     */
    Parent scene;

    /**
     * The anchor pane for the edit appointment page.
     */
    @FXML
    private AnchorPane editAppointmentAnchorPane;

    /**
     * Disabled text field, holds appointment id.
     */
    @FXML
    private TextField appointmentIdTxt;
    /**
     * Data entry field for appointment title.
     */
    @FXML
    private TextField titleTxt;
    /**
     * Data entry field for appointment description.
     */
    @FXML
    private TextField descriptionTxt;
    /**
     * Data entry field for appointment location.
     */
    @FXML
    private TextField locationTxt;
    /**
     * Data entry field for appointment type.
     */
    @FXML
    private TextField typeTxt;
    /**
     * Data selection field for appointment contact
     */
    @FXML
    private ComboBox<Contacts> contactComboBox;
    /**
     * Data entry for appointment start date.
     */
    @FXML
    private DatePicker startDatePicker;
    /**
     * Data entry for appointment start time.
     */
    @FXML
    private TextField startTimeTxt;
    /**
     * Data entry for appointment hour end.
     */
    @FXML
    private TextField endTimeTxt;
    /**
     * Data entry field for appointment customer id.
     */
    @FXML
    private TextField customerIDTxt;

    /**
     * Table Object.
     */
    @FXML
    private TableView<Appointments> editAppointmentTableView;
    /**
     * Object in table for the title column.
     */
    @FXML
    private TableColumn<Appointments, String> titleCol;
    /**
     * Object in table for the description column.
     */
    @FXML
    private TableColumn<Appointments, String> descriptionCol;
    /**
     * Object in table for the location column.
     */
    @FXML
    private TableColumn<Appointments, String> locationCol;
    /**
     * Object in table for the type column.
     */
    @FXML
    private TableColumn<Appointments, String> typeCol;
    /**
     * Object in table for the start column.
     */
    @FXML
    private TableColumn<Appointments, String> startCol;
    /**
     * Object in table for the end column.
     */
    @FXML
    private TableColumn<Appointments, String> endCol;
    /**
     * Object in table for the create date column.
     */
    @FXML
    private TableColumn<Appointments, String> createDateCol;
    /**
     * Object in table for the created by column.
     */
    @FXML
    private TableColumn<Appointments, String> createdByCol;
    /**
     * Object in table for the last updated column.
     */
    @FXML
    private TableColumn<Appointments, String> lastUpdateCol;
    /**
     * Object in table for the last updated by column.
     */
    @FXML
    private TableColumn<Appointments, String> lastUpdatedByCol;
    /**
     * Object in table for the customer id column.
     */
    @FXML
    private TableColumn<Appointments, Integer> customerIDCol;
    /**
     * Object in table for the user id column.
     */
    @FXML
    private TableColumn<Appointments, Integer> userIDCol;
    /**
     * Object in table for the contact id column.
     */
    @FXML
    private TableColumn<Appointments, Integer> contactIDCol;

    /**
     * Listens for an appointment record to be selected.
     * @param mouseEvent The record selection event.
     */
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

        int my5ish = my5.getHour();
        System.out.println("my5ish = " + my5ish);

        startTimeTxt.setText(Integer.toString(my5ish));

        // build again for endTimeTxt
        LocalDateTime my6 = endTimeTimestamp.toLocalDateTime();
        ZonedDateTime my7 = my6.atZone(ZoneId.of("UTC"));
        ZonedDateTime my8 = my7.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime my9 = my8.toLocalDateTime();
        LocalTime my10 = my9.toLocalTime();
        int my10ish = my10.getHour();

        endTimeTxt.setText(Integer.toString(my10ish));

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

    /**
     * Performs action when the save button is clicked.
     * @param event The button press event.
     * @throws IOException for failure condition.
     */
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

        // Make a ZonedDateTime object
        ZonedDateTime startTime = ZonedDateTime.of(startDate, startTimeField, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("startTime: " + startTime);

        ZonedDateTime endTime = ZonedDateTime.of(startDate, endTimeField, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("  endTime: " + endTime);

        if (DBAppointments.isOverlapAppointment(0, startTime.toLocalDateTime(), endTime.toLocalDateTime(), customerId)) {
            popupError("This appointment time has a conflict.");
        } else if (!DBAppointments.isDuringBusinessHours(startDate, startTimeField, endTimeField)) {
            popupError("Appointment must be between 8:00 a.m and 10:00 p.m. EST, including weekends.");
        } else {
            // create new appointment using model method
            DBAppointments.updateAppointment(appointmentId, title, description, location, type, startTime, endTime, customerId, userId, contactId);
            gotoPage(event, "/view/EditAppointment.fxml");
        }
    }

    /**
     * Performs action when the delete button is clicked.
     * @param event button press.
     * @throws IOException when failure encountered.
     */
    @FXML
    public void onDeleteButtonAction(ActionEvent event) throws IOException {
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
            gotoPage(event, "/view/EditAppointment.fxml");
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("CANCEL BUTTON PRESSED");
        }
    }

    /**
     * Filter results to the current month
     * @param event radio btn click is passed along.
     */
    public void onMonthSelected(ActionEvent event) {
        editAppointmentTableView.setItems(DBAppointments.getMonthAppointments());
    }

    /**
     * Filter results to the current month
     * @param event radio btn click is passed along.
     */
    public void onWeekSelected(ActionEvent event) {
        editAppointmentTableView.setItems(DBAppointments.getWeekAppointments());
    }

    public void getAllAppointments(ActionEvent event) {
        editAppointmentTableView.setItems(DBAppointments.getAllAppointments());
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
