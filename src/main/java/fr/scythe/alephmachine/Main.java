/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */
package fr.scythe.alephmachine;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

//TODO : Make the menubar functional.
//TODO : Export the project as a .jar artifact.

public class Main extends Application {

    public static final Background btnBackground = new Background(new BackgroundFill(Color.grayRgb(170), new CornerRadii(5), Insets.EMPTY));

    // pStage = Stage primaire
    @Override
    public void start(Stage pStage) {
        System.out.println("Launch of the app. . .");
        pStage.setTitle("Aleph Machine");
        Image iconStage = new Image("file:aleph_icon.jpg");
        pStage.getIcons().add(iconStage);

        // Construct of the button
        pStage.setResizable(false);

        // Directory Directorychooser to find the bot
        DirectoryChooser dChooser = new DirectoryChooser();
        dChooser.setTitle("Open folder");
        FileChooser executableChoooser = new FileChooser();
        executableChoooser.setTitle("Open .exe file");

        // Construct of the buttons
        Button btnStart = new Button();
        btnStart.setText("Run Aleph");
        btnStart.setMaxSize(100, 30);
        btnStart.setMinSize(100, 30);
        btnStart.setBackground(btnBackground);

        Button btnBuild = new Button();
        btnBuild.setText("Build");
        btnBuild.setPadding(new Insets(5));
        btnBuild.setMaxSize(100, 30);
        btnBuild.setMinSize(100, 30);
        btnBuild.setBackground(btnBackground);

        // Construct of the button used to select the directories
        Button btnDirChooserBuild = new Button("Select your bot folder");
        ButtonBuilder.buttonDirChooser(btnDirChooserBuild);
        Button btnDirChooserRun = new Button("Select the .exe file");
        ButtonBuilder.buttonDirChooser(btnDirChooserRun);

        // Get Data from lambda statement
        AtomicReference<File> sharedFileBuild = new AtomicReference<>();
        btnDirChooserBuild.setOnAction(event -> {
            sharedFileBuild.set(dChooser.showDialog(pStage));
            if(sharedFileBuild != null) {
                btnDirChooserRun.setBackground(new Background(new BackgroundFill(Color.rgb(6, 95, 40), new CornerRadii(5), Insets.EMPTY)));

                btnDirChooserRun.setOnMouseExited(e -> {
                    btnDirChooserRun.setBackground(new Background(new BackgroundFill(Color.rgb(6, 95, 40), new CornerRadii(5), Insets.EMPTY)));
                });
            }
        });

        AtomicReference<File> sharedFileRun = new AtomicReference<>();
        btnDirChooserRun.setOnAction(e -> {
            sharedFileRun.set(executableChoooser.showOpenDialog(pStage));
            if(sharedFileRun != null) {
                btnDirChooserRun.setBackground(new Background(new BackgroundFill(Color.rgb(6, 95, 40), new CornerRadii(5), Insets.EMPTY)));

                btnDirChooserRun.setOnMouseExited(event -> {
                    btnDirChooserRun.setBackground(new Background(new BackgroundFill(Color.rgb(6, 95, 40), new CornerRadii(5), Insets.EMPTY)));
                });

            }

        });

        // onClick event
        btnStart.setOnAction(event -> {
            try {
                if(sharedFileRun.get().getAbsolutePath() != null) {
                    System.out.println("Calling botInit() method. . .");
                    try {
                        BotActions.botRun(sharedFileRun.get().getAbsolutePath(), sharedFileRun.get().getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", sharedFileBuild.get().getAbsolutePath());

                    }
                } else {
                    System.out.println("TextField is empty");
                }
            } catch (NullPointerException e) {
                AlertBox.display("ALERT - Empty Location", "The path is null. Please set a valid path.", null);
            }
        });

        btnBuild.setOnAction(event -> {
            try {
                if (sharedFileBuild.get().getAbsolutePath() != null) {
                    System.out.println("Calling botBuild() method. . .");
                    try {
                        BotActions.botBuild(sharedFileBuild.get().getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", sharedFileBuild.get().getAbsolutePath());
                    }
                } else {
                    System.out.println("TextField is empty");
                }
            } catch (NullPointerException e){
                AlertBox.display("ALERT - Empty Location", "The path is null. Please set a valid path.", null);
            }
        });

        // New menu bar
        MenuBar menuBar = new MenuBar();
        VBox menuVbox = new VBox(menuBar);
        Menu menuRun = new Menu("Run");
        Menu menuBuild = new Menu("Build");
        menuBar.getMenus().addAll(menuBuild, menuRun);

        HBox runLayout = new HBox(10);
        runLayout.getChildren().addAll(btnDirChooserRun, btnStart);
        runLayout.setAlignment(Pos.CENTER);

        HBox buildLayout = new HBox(10);
        buildLayout.getChildren().addAll(btnDirChooserBuild, btnBuild);
        buildLayout.setAlignment(Pos.CENTER);

        // Adding of the buttons
        VBox center = new VBox(15);
        center.getChildren().addAll(runLayout, buildLayout);
        center.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        center.setAlignment(Pos.CENTER);

        // Adding of the stack pane (Container)
        BorderPane borderPane = new BorderPane();
        borderPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        borderPane.setCenter(center);
        borderPane.setTop(menuVbox);

        // pScene = the window
        Scene pScene = new Scene(borderPane, 395, 475);
        pStage.setScene(pScene);
        pStage.show();

    }

    // Build of the bot
    public static void main(String[] args) {
        System.out.println("Main Method called. . .");
        launch(args);
    }

}