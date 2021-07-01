/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */

package fr.scythe.alephmachine;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonBuilder {

    public static void buttonDirChooser(Button btn) {
        btn.setBackground(new Background(new BackgroundFill(Color.rgb(76, 149, 255), new CornerRadii(5), Insets.EMPTY)));
        btn.setFont(Font.font("Verdana"));
        btn.setMaxSize(170, 30);
        btn.setMinSize(170, 30);
        btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        btn.setOnMouseEntered(event -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(34, 124, 255), new CornerRadii(5), Insets.EMPTY)));
        });
        btn.setOnMouseExited(event -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(76, 149, 255), new CornerRadii(5), Insets.EMPTY)));
        });

    }



}
