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

/** Initial JavaFX call to load the starting screen.
 * @stage Stage
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            stage.setTitle("Login Screen");
            stage.setScene(new Scene(root, 500, 500));
            stage.show();
    }

    public static void main(String[] args) {
        DBConnection.makeConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
