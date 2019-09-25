/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Inge
 */
public class BerichtenBox {

    private Label label;
    private Button okButton;

    public void GeefBerichtenBox(String titel, String bericht) {

        Stage scherm = new Stage();
        
        //Zorgt ervoor dat er niks anders gedaan kan worden tot acties op de alertbox zijn verwerkt
        scherm.initModality(Modality.APPLICATION_MODAL);

        scherm.setTitle(titel);
        scherm.setWidth(400);
        scherm.setHeight(400);

        //layout definieren
        this.label = new Label();
        label.setText(bericht);
        label.setStyle("-fx-font-weight: bold;");
        this.okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #003366");
        okButton.setTextFill(Paint.valueOf("white"));

        okButton.setOnAction(e -> {

            scherm.close();

        });

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#CCCC99"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);

        //Toon scherm aan gebruiker en wacht totdat het scherm gesloten wordt voor terugkeren
        //naar vorig scherm
        Scene scene = new Scene(layout);
        scherm.setScene(scene);
        scherm.showAndWait();

    }

}
