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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {

    public static final Background btnBackground = new Background(new BackgroundFill(Color.grayRgb(170), new CornerRadii(5), Insets.EMPTY));
    static final        boolean    isWindows     = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public static void main(String[] args) {
        System.out.println("Main Method called. . .");
        launch(args);
    }

    // pStage = Stage primaire
    @Override
    public void start(Stage pStage) {
        System.out.println("Launch of the app. . .");
        pStage.setTitle("Aleph Machine");

        // Construct of the button
        pStage.setResizable(false);

        // Directory Directorychooser to find the bot
        FileChooser dChooser = new FileChooser();
        dChooser.setTitle("Open file");

        // Construct of the buttons
        Button btnStart = new Button();
        btnStart.setText("Start Aleph");
        btnStart.setMaxSize(100, 30);
        btnStart.setMinSize(100, 30);
        btnStart.setBackground(btnBackground);

        Button btnBuild = new Button();
        btnBuild.setText("Build");
        btnBuild.setPadding(new Insets(5));
        btnBuild.setMaxSize(100, 30);
        btnBuild.setMinSize(100, 30);
        btnBuild.setBackground(btnBackground);

        Button btnDirChooser = new Button();
        btnDirChooser.setText("Select a directory");
        btnDirChooser.setBackground(new Background(new BackgroundFill(Color.rgb(76, 149, 255), new CornerRadii(5), Insets.EMPTY)));
        btnDirChooser.setFont(Font.font("Verdana"));
        btnDirChooser.setMaxSize(170, 30);
        btnDirChooser.setMinSize(170, 30);
        btnDirChooser.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        btnDirChooser.setOnMouseEntered(event -> {
            btnDirChooser.setBackground(new Background(new BackgroundFill(Color.rgb(34, 124, 255), new CornerRadii(5), Insets.EMPTY)));
        });
        btnDirChooser.setOnMouseExited(event -> {
            btnDirChooser.setBackground(new Background(new BackgroundFill(Color.rgb(76, 149, 255), new CornerRadii(5), Insets.EMPTY)));
        });

        AtomicReference<File> sharedDir = new AtomicReference<>();

        btnDirChooser.setOnAction(event -> sharedDir.set(dChooser.showOpenDialog(pStage)));

        // onClick event
        btnStart.setOnAction(event -> {
            if(sharedDir.get().getAbsolutePath() != null) {
                System.out.println("Calling botInit() method. . .");
                try {
                    botInit(sharedDir.get().getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                    AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", sharedDir.get().getAbsolutePath());

                }
            } else {
                System.out.println("TextField is empty");
            }
        });

        btnBuild.setOnAction(event -> {
            if (sharedDir.get().getAbsolutePath() != null) {
                System.out.println("Calling botBuild() method. . .");
                try {
                    botBuild(sharedDir.get().getAbsolutePath());

                } catch (IOException e) {
                    e.printStackTrace();
                    AlertBox.display("ALERT - Wrong dir", "The current directory is not valid !", sharedDir.get().getAbsolutePath());
                }
            } else {
                System.out.println("TextField is empty");
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
        center.getChildren().addAll( btnDirChooser, btnStart, btnBuild);
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

        if (isWindows) {
            File goDir = new File("user.home\\go");

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd /C C:\\Users\\utilisateur\\go\\go1.16.3\\bin\\go.exe build " + botDir);
            processBuilder.redirectErrorStream(true);
            Process p = processBuilder.start();

            System.out.println("Everything is working pretty well");

            InputStream is = p.getInputStream();
            System.out.println("" + is.read());

        } else {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command("sh", "-a", "go", "build");
            processBuilder.start();

        }

        AlertBox.display("Alert - Task Started", "The command \"go build\" have been launched in :", botDir);

    }

    // Launch of the bot
    public static void botInit(String botDir) throws IOException {

        if(isWindows) {

            //TODO Create a production version of the program. Add 2 different FileChooser. One for the .exe file and one for the .go file.

            System.out.println(botDir);
            ProcessBuilder pBuilder = new ProcessBuilder(botDir);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();

        } else {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command("sh", "-a", "go", "run");
            processBuilder.start();

        }

        AlertBox.display("Alert - Task Started", "The command \"go run\" have been launched in :", botDir);

    }

}