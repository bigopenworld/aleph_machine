/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */
package fr.scythe.alephmachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        pStage.setResizable(false);



        // Construct of the button
        Button btnStart = new Button();
        btnStart.setText("Start Aleph");
        btnStart.setBackground(new Background(new BackgroundFill(Color.grayRgb(170), new CornerRadii(5), Insets.EMPTY)));

        // onClick event
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Calling botInit() method. . .");
                botInit();
            }
        });

        // Adding of the stack pane (Container)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(btnStart);

        // pScene = the window
        Scene pScene = new Scene(stackPane, 300, 400);
        pStage.setScene(pScene);
        pStage.show();


    }

    // Launch of the bot
    public static void botInit() {

    }

}
