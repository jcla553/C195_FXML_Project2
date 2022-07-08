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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    public RadioButton monthRadioBtn;
    public RadioButton weekRadioBtn;
    public Button CancelBtn;
    public RadioButton totalCustomerAppointmentsRadioBtn;
    public RadioButton scheduleByContactRadioBtn;
    Stage stage;
    Parent scene;

    @FXML
    private AnchorPane reportsAnchorPane;

    // Data Table Objects
    @FXML
    private TableView<Appointments> reportsTableView;
    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;
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
    private TableColumn<Appointments, Integer> customerIDCol;
    @FXML
    private TableColumn<Appointments, Integer> userIDCol;
    @FXML
    private TableColumn<Appointments, Integer> contactIDCol;

    // additional table
    @FXML
    private TableView<String> totalCustomerAppointmentsTableView;
    @FXML
    private TableColumn<List, String> countCol;
    @FXML
    private TableColumn<List, String> typesCol;
    @FXML
    private TableColumn<List, String> monthCol;


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

        //populate the Appointment Count table
        totalCustomerAppointmentsTableView.setItems(DBAppointments.getAppointmentSummary());
        countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));
        typesCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
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

    /**
     *
     */
    public void onTotalCustomerAppointmentsSelected(ActionEvent event) {
        totalCustomerAppointmentsTableView.setItems(DBAppointments.getAppointmentSummary());

    }

    public void onScheduleByContactSelected(ActionEvent event) {
    }


}
