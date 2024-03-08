/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package addrbook;

/**
 *
 * @author Bogrm
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file, make sure the file name matches exactly, including case sensitivity
            Parent root = FXMLLoader.load(getClass().getResource("/addrbook/AdrBook.fxml"));

            // Set the scene to the stage
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("My Address Book");
            
            // Show the GUI
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}
