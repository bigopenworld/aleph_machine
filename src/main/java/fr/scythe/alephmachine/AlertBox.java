/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */

package fr.scythe.alephmachine;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String content, String path) {

        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.setResizable(false);
        window.setMaxWidth(250);
        window.setTitle(title);


        Label contentLabel = new Label(content);
        Label pathLabel = new Label(path);

        Button buttonClose = new Button("Close");
        buttonClose.setBackground(Main.btnBackground);
        buttonClose.setMaxSize(50, 30);
        buttonClose.setOnAction(e -> window.close());

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(contentLabel, pathLabel, buttonClose);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(50));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

    }

}
