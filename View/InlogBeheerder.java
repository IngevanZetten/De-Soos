/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Inge
 */
public class InlogBeheerder {

    private Label lblTitel, lblFoutInlog;
    private Button btnInlog;
    private TextField tfGebruikersnaam;
    private PasswordField tfWachtwoord;
    private HashMap<String, String> map;

    public void geefScherm() {

        Stage window = new Stage();
        

        //Zorgt ervoor dat er niks anders gedaan kan worden tot acties op de alertbox zijn verwerkt
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Inloggen beheerder");
        window.setWidth(500);
        window.setHeight(500);

        this.lblTitel = new Label(" Bent u beheerder? log dan hier in om een activiteit\n toe te voegen aan de Soos - Roosendaal.");
        lblTitel.setWrapText(true);
        this.lblFoutInlog = new Label();
        lblFoutInlog.setTextFill(Paint.valueOf("red"));

        this.map = new HashMap<>();
        map.put("gebruikersnaam", "mai van zetten");
        map.put("wachtwoord", "DeSoos");

        this.tfGebruikersnaam = new TextField();
        tfGebruikersnaam.setPromptText("Gebruikersnaam");
        this.tfWachtwoord = new PasswordField();
        tfWachtwoord.setPromptText("Wachtwoord");

        this.btnInlog = new Button("Inloggen");
        btnInlog.setStyle("-fx-background-color: #003366");
        btnInlog.setTextFill(Paint.valueOf("white"));
        btnInlog.setOnAction(e -> {

            String gebruikersnaam = tfGebruikersnaam.getText();
            String wachtwoord = tfWachtwoord.getText();
            if(map.containsValue(gebruikersnaam) && map.containsValue(wachtwoord)){
                InvoerActiviteitView invoerScherm = new InvoerActiviteitView();
                invoerScherm.geefScherm();
                window.close();
            }
            else{
                lblFoutInlog.setText("Beheerder niet gevonden!");
            }
            
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tfGebruikersnaam, tfWachtwoord, btnInlog, lblFoutInlog);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.add(lblTitel, 0, 0);
        layout.add(vbox, 0, 1);
        layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));
        

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
