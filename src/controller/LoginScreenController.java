package controller;

import database.DBAppointments;
import database.DBUsers;
import database.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The login screen controller class.
 */
public class LoginScreenController implements Initializable {
    /**
     * TheLabel object on the fxml page.
     */
    public Label TheLabel;
    /**
     * userName object on the fxml page.
     */
    public String userName;

    /**
     * fxml step
     */
    Stage stage;
    /**
     * fxml step
     */
    Parent scene;
    /**
     * The User Name field for login.
     */
    @FXML
    private TextField UserNameTxt;

    /**
     * The Password field for login.
     */
    @FXML
    private TextField PasswordTxt;

    /**
     * The title label on the form.
     */
    @FXML
    private Label titleLbl;

    /**
     * The User Name label to direct the user.
     */
    @FXML
    private Label UserNameLbl;

    /**
     * The password label to direct the user.
     */
    @FXML
    private Label PasswordLbl;

    /**
     * The location label on the form.
     */
    @FXML
    private Label LocationLbl;

    /**
     * The zone label on the form.
     */
    @FXML
    private Label zoneIdLbl;

    /**
     * The Submit button on the login form.
     */
    @FXML
    private Button SubmitBtn;

    /**
     * The cancel button on the login form.
     */
    @FXML
    private Button CancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LoginScreen initialized!");
        TheLabel.setText("Initialized!");
        TheLabel.setText(String.valueOf(Locale.getDefault()));

        ZoneId zone = ZoneId.systemDefault();
        System.out.println("Output of systemDefault()-" +zone);

        System.out.println("Output of zoneid obtained using of function: "+ zone.normalized());

        ZoneId zoneOf = ZoneId.of(String.valueOf(zone));
        System.out.println("of on ZoneId: " + zoneOf);

        ZoneId zoneId = ZoneId.ofOffset("GMT", ZoneOffset.MAX);
        System.out.println("OfOffset on ZoneId: " + zoneId);

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
        System.out.println("It's currently " + nowDateTime + " where I am");


        //display label on form
        zoneIdLbl.setText(String.valueOf(zone));

        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());

        titleLbl.setText(rb.getString("titleLbl"));
        UserNameLbl.setText(rb.getString("UserNameLbl"));
        PasswordLbl.setText(rb.getString("PasswordLbl"));
        LocationLbl.setText(rb.getString("LocationLbl"));
        SubmitBtn.setText(rb.getString("SubmitBtn"));
        CancelBtn.setText(rb.getString("CancelBtn"));

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
     * Authenticate login
     * @param event button clicked
     * @throws IOException if target .fxml is missing.
     */
    public void onSubmitButtonAction(ActionEvent event) throws IOException{
        System.out.println("I am clicked");

        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());

        // check for values in both fields.

        isLoginFieldEmpty("UserName", UserNameTxt.getText());
        isLoginFieldEmpty("Password", PasswordTxt.getText());

            String userName = UserNameTxt.getText();
            System.out.println("UserName: " + userName);
            System.out.println("Password: " + PasswordTxt.getText());

        // positive number for return of isValid = the UserID
        int isValid = DBUsers.validateLogin(userName, PasswordTxt.getText());
            System.out.println("isValid = " + isValid);

        if (isValid == 1) {
            // log attempt
            loginLogger(userName, true);
            // check upcoming appointments
            String ifConflict = DBAppointments.isNextAppointmentIn15Minutes(userName);
//            if (DBAppointments.isNextAppointmentIn15Minutes(userName)) {
            popupInfo(ifConflict);
//            } else {
//                popupInfo("No appointments starting within the next 15 minutes.");
//            }

            // go to the Main Page
            gotoPage(event, "/view/MainPage.fxml");
        } else if (isValid <= 0) {
            loginLogger(userName, false);
            popupError(rb.getString("loginErrorMsg"));
        }

    }

    /**
     * Check if login fields have values.
     * @param field The UserName or Password
     * @param text The value provided
     */
    private void isLoginFieldEmpty(String field, String text) {
        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());

        if (text.isEmpty()) {
            popupError(rb.getString(field + "Lbl") + " " + rb.getString("loginFieldEmpty"));
        }
    }

    /**
     * Exit the application.
     * @param event The Exit button press event.
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Moved the Alert creation into a method to allow a single line call.
     * @param contentText The context appropriate message for the user.
     */
    @FXML
    public void popupError(String contentText) {
        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("ErrorDialog"));
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

    /**
     * Creates log file for history for rubric C.
     * @param userName The text entered by the user to login.
     * @param loginSuccess successful login? true or false
     * @throws IOException when error encountered.
     */
    public void loginLogger(String userName, boolean loginSuccess) throws IOException {
        String filename = "loginHistory.txt";

        // open an output stream
        PrintWriter out = new PrintWriter(
                          new BufferedWriter(
                          new FileWriter(filename, true)));

        String loginSuccessString;
        // success string
        if (loginSuccess) {
            loginSuccessString = "provided   valid credentials.";
        } else {
            loginSuccessString = "provided invalid credentials.";
        }


        // write data to the stream
        out.println(Timestamp.from(Instant.now()) + "\t User: " + userName + " " + loginSuccessString);

        // close the output stream
        out.close();

    }


}
