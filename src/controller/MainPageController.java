package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    public Button addCustomerBtn;
    public Button mainPageBtn;
    public Button editCustomerBtn;
    public Button addAppointmentBtn;
    public Button exitBtn;
    public Button editAppointmentBtn;
    public Button reportsBtn;
    Stage stage;
    Parent scene;

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
     * Navigate to the Add Customer screen.
     * @param event The button press event.
     * @throws IOException if target .fxml is missing.
     */
    @FXML
    public void onAddCustomer(ActionEvent event) throws IOException {
        gotoPage(event, "/view/AddCustomer.fxml");
    }

    /**
     * Navigate to the Edit Customer screen.
     * @param event The button press event.
     * @throws IOException if target .fxml is missing.
     */
    @FXML
    public void onEditCustomer(ActionEvent event) throws IOException {
        gotoPage(event, "/view/EditCustomer.fxml");
    }

    /**
     * Navigate to the Add Appointment screen.
     * @param event The button press event.
     * @throws IOException if target .fxml is missing.
     */
    @FXML
    public void onAddAppointment(ActionEvent event) throws IOException {
        gotoPage(event, "/view/AddAppointment.fxml");
    }

    /**
     * Navigate to the Edit Appointment screen.
     * @param event The button press event.
     * @throws IOException if target .fxml is missing.
     */
    @FXML
    public void onEditAppointment(ActionEvent event) throws IOException {
        gotoPage(event, "/view/EditAppointment.fxml");
    }

    public void onReports(ActionEvent event) throws IOException {
        gotoPage(event, "/view/Reports.fxml");
    }

    /**
     * Exit the application.
     * @param event The Exit button press event.
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }
}
