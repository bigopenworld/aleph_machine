/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */
package fr.scythe.alephmachine;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Main Method called. . .");
        launch(args);
    }


    // pStage = Stage primaire
    @Override
    public void start(Stage pStage) throws Exception {
        System.out.println("Launch of the app. . .");
        pStage.setTitle("Aleph Machine");

        // Construct of the button
        Button btnStart = new Button();
        btnStart.setText("Start Aleph");

        // Adding of the stack pane (Container)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(btnStart);

        // Scene = the window
        Scene scene = new Scene(stackPane, 300, 400);
    }
}
