/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */
package fr.scythe.alephmachine;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private String botDir;
    public static final Background btnBackground = new Background(new BackgroundFill(Color.grayRgb(170), new CornerRadii(5), Insets.EMPTY));

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
        pStage.setResizable(false);

        // Textfield to choose the directory of the bot
        TextField tField = new TextField();
        tField.setMaxSize(200, 50);

        // Construct of the buttons
        Button btnStart = new Button();
        btnStart.setText("Start Aleph");
        btnStart.setMaxSize(100, 50);
        btnStart.setBackground(btnBackground);

        Button btnBuild = new Button();
        btnBuild.setText("Build");
        btnBuild.setPadding(new Insets(5));
        btnBuild.setMaxSize(100, 50);
        btnBuild.setBackground(btnBackground);

        // onClick event
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tField.getText().isEmpty()) {
                    System.out.println("Calling botInit() method. . .");
                    try {
                        botInit(tField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", tField.getText());
                    }
                } else {
                    System.out.println("TextField is empty");
                }
            }
        });

        btnBuild.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tField.getText().isEmpty()) {
                    System.out.println("Calling botBuild() method. . .");
                    try {
                        botBuild(tField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", tField.getText());
                    }
                } else {
                    System.out.println("TextField is empty");
                }
            }
        });

        // New menu bar
        MenuBar menuBar = new MenuBar();
        VBox menuVbox = new VBox(menuBar);
        Menu menuRun = new Menu("Run");
        Menu menuBuild = new Menu("Build");
        menuBar.getMenus().addAll(menuBuild, menuRun);

        // Adding of the buttons
        VBox center = new VBox(5);
        center.getChildren().addAll(tField, btnStart, btnBuild);
        center.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        center.setAlignment(Pos.CENTER);

        // Adding of the stack pane (Container)
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(center);
        borderPane.setTop(menuVbox);

        // pScene = the window
        Scene pScene = new Scene(borderPane, 395, 475);
        pStage.setScene(pScene);
        pStage.show();


    }

    // Build of the bot
    public static void botBuild(String botDir) throws IOException {
        Process processBuild = Runtime.getRuntime().exec("cmd go build", null, new File(botDir));
        Process processMKDIR = Runtime.getRuntime().exec("mkdir wow", null, new File(botDir));
        AlertBox.display("Alert - Task Started", "The command \"go build\" have been launched in :", botDir);

    }

    // Launch of the bot
    public static void botInit(String botDir) throws IOException {
        // Process processRun = Runtime.getRuntime().exec("go run", null, new File(botDir));
        Process processMkdir = Runtime.getRuntime().exec("mkdir wow", null, new File(botDir));
        AlertBox.display("Alert - Task Started", "The command \"go run\" have been launched in :", botDir);
    }

}
