/** @author James Clark
 * C195 - Software II - Advanced Java Concepts
 */
package main;

import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/** Initial JavaFX call to load the starting screen.
 *
 */
public class Main extends Application {
    public static int customerSequence;
    public static int appointmentSequence;

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle rb = ResourceBundle.getBundle("lang/lang", Locale.getDefault());
//    ResourceBundle rb = ResourceBundle.getBundle("main/lang", Locale.FRANCE);
//    new myDefaultLocale Locale = java.util.Locale.getDefault();

        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("C195 Scheduling App");
        stage.setTitle(rb.getString("title"));
        stage.setScene(new Scene(root, 500, 500));
        stage.show();


        if (Locale.getDefault().getLanguage().equals("de") ||
                Locale.getDefault().getLanguage().equals("es") ||
                Locale.getDefault().getLanguage().equals("fr")) {

            System.out.println(rb.getString("hello") + " " + (rb.getString("world")));
        }
    }



    public static void main(String[] args) {
        // set sequences
        customerSequence = 10;
        appointmentSequence = 10;

        DBConnection.makeConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
