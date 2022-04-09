package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    public Label TheLabel;

    Stage stage;
    Parent scene;

    @FXML
    private TextField UserNameTxt;

    @FXML
    private TextField PasswordTxt;

    @FXML
    private Label titleLbl;

    @FXML
    private Label UserNameLbl;

    @FXML
    private Label PasswordLbl;

    @FXML
    private Label LocationLbl;

    @FXML
    private Button SubmitBtn;

    @FXML
    private Button CancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LoginScreen initialized!");
        TheLabel.setText("Initialized!");
        TheLabel.setText(String.valueOf(Locale.getDefault()));

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

            System.out.println("UserName: " + UserNameTxt.getText());
            System.out.println("Password: " + PasswordTxt.getText());

        // positive number for return of isValid = the UserID
        int isValid = DBUsers.validateLogin(UserNameTxt.getText(), PasswordTxt.getText());
            System.out.println("isValid = " + isValid);

        if (isValid == 1) {
            // go to the Main Page
            gotoPage(event, "/view/MainPage.fxml");
        } else if (isValid <= 0) {
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
}
