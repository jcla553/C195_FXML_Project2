package controller;

import database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private AnchorPane addAppointmentAnchorPane;

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private ComboBox<Contacts> contactComboBox;

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

    public void onSaveButtonAction(ActionEvent event) {
//        String appointmentId = appointmentIdTxt.getText();
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

        // Make the spinner values actual time.
        LocalTime startTimeSpinnerValue = LocalTime.of((Integer) startTimeSpinner.getValue(), 0, 0);
        LocalTime endTimeSpinnerValue = LocalTime.of((Integer) endTimeSpinner.getValue(), 0, 0);

//        LocalDateTime nowDateTime = LocalDateTime.now();
//        System.out.println("nowDateTime: " + nowDateTime);

        // Make a ZonedDateTime object
        ZonedDateTime startTime = ZonedDateTime.of(startDate, startTimeSpinnerValue, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("startTime: " + startTime);

        ZonedDateTime endTime = ZonedDateTime.of(startDate, endTimeSpinnerValue, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("  endTime: " + endTime);

        // create new appointment using model method
        DBAppointments.addAppointment(0, title, description, location, type, startTime, endTime, customerId, userId, contactId);

        // Store the UTC time in the database

        // later when showing the time, convert the UTC to local time zone
//        LocalTime
        // todo define this
    }

    public static Date dateFromUTC(Date date){
        return new Date(date.getTime() + Calendar.getInstance().getTimeZone().getOffset(new Date().getTime()));
    }

    public static Date dateToUTC(Date date){
        return new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        contactComboBox.setItems(DBContacts.getAllContacts());

        // set default contact and date
        contactComboBox.getSelectionModel().selectFirst();
        startDatePicker.setValue(LocalDate.now());


        // populate the spinner
        SpinnerValueFactory<Integer> startTimeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24, 8);
        this.startTimeSpinner.setValueFactory(startTimeValueFactory);

        SpinnerValueFactory<Integer> endTimeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24, 8);
        this.endTimeSpinner.setValueFactory(endTimeValueFactory);
    }
}
