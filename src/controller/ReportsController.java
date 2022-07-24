package controller;

import database.DBAppointments;
import database.DBContacts;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The reports controller class.
 */
public class ReportsController implements Initializable {

    /**
     * The month radio button on the fxml page.
     */
    public RadioButton monthRadioBtn;
    /**
     * The week radio button on the fxml page.
     */
    public RadioButton weekRadioBtn;
    /**
     * The cancel button on the fxml page.
     */
    public Button CancelBtn;
    /**
     * The report F1 button on the fxml page.
     */
    public Button reportF1Btn;
    /**
     * The report F2 button on the fxml page.
     */
    public Button reportF2Btn;
    /**
     * The report F3 button on the fxml page.
     */
    public Button reportF3Btn;
    /**
     * fxml step
     */
    Stage stage;
    /**
     * fxml step
     */
    Parent scene;

    /**
     * The reports page anchor pane.
     */
    @FXML
    private AnchorPane reportsAnchorPane;

    /**
     * Table Object.
      */
    @FXML
    private TableView<Appointments> reportsTableView;
    /**
     * Object in table for the appointment id column.
     */
    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;
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

    /**
     * Moved the Alert creation into a method to allow a single line call.
     * @param contentText The context appropriate message for the user.
     */
    @FXML
    public void popupInfo(String contentText) {
        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(rb.getString("InfoDialog"));
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        // populate the Appointments table
        reportsTableView.setItems(DBAppointments.getAllAppointments());
        // match TableView columns to the getter
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

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

    /**
     * Filter results to the current month
     * @param event radio btn click is passed along.
     */
    public void onMonthSelected(ActionEvent event) {
        reportsTableView.setItems(DBAppointments.getMonthAppointments());
    }

    /**
     * Filter results to the current month
     * @param event radio btn click is passed along.
     */
    public void onWeekSelected(ActionEvent event) {
        reportsTableView.setItems(DBAppointments.getWeekAppointments());
    }

//    public void onReportF1Btn(ActionEvent actionEvent) {
//    }

    /**
     * total number of customer appointments by type and month
     * @param event btn click
     */
    public void onReportF1Btn(ActionEvent event) {
        popupInfo(DBAppointments.getCustomerAppointmentsByTypeAndMonth());
    }

    /**
     * Provides the report results to the user.
      * @param event btn click
     */
    public void onReportF2Btn(ActionEvent event) {
        popupInfo(DBAppointments.getScheduleByContact());
    }

    /**
     * Provides the report results to the user.
     * @param event btn click
     */
    public void onReportF3Btn(ActionEvent event) {
    }
}
