/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.ActiviteitDAO;
import Datalayer.CategorieDAO;
import Model.Activiteit;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Inge
 */
public class InvoerActiviteitView {

    private Label lblID, lblNaam, lblDatum, lblTijd,
            lblAdres, lblPlaats, lblAfstand, lblPrijs, lblFoto, lblOmschrijving,
            lblcategorie, lblTitelCategorie, lblTitelActiviteit, lblcbCategorie,
            lblFoutCategorie;
    private TextArea taOmschrijving;
    private TextField tfID, tfNaam, tfDatum, tfTijd, tfAdres, tfPlaats, tfAfstand,
            tfPrijs, tfFoto, tfCategorieNaam;
    private Button btnCategorieToevoegen, btnActiviteitToevoegen;
    private ComboBox<String> cbCategorieën;
    private CategorieDAO categorieDAO;
    private ActiviteitDAO activiteitDAO;
    private BerichtenBox berichtenBox;
    private String categorie;

    public void geefScherm() {

        Stage window = new Stage();

        //Zorgt ervoor dat er niks anders gedaan kan worden tot acties op de alertbox zijn verwerkt
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Inloggen beheerder");
        window.setWidth(1000);
        window.setHeight(1000);

        //definieren dao's
        this.categorieDAO = new CategorieDAO();
        this.activiteitDAO = new ActiviteitDAO();

        this.berichtenBox = new BerichtenBox();

        //definieren labels
        this.lblTitelCategorie = new Label("Voeg een categorie toe:");
        this.lblcategorie = new Label("*Naam categorie: ");
        this.lblTitelActiviteit = new Label("Voeg een activiteit toe: ");
        this.lblID = new Label("*ID: ");
        this.lblNaam = new Label("*Naam: ");
        this.lblDatum = new Label("*Datum: ");
        this.lblTijd = new Label("*Tijd: ");
        this.lblAdres = new Label("*Adres: ");
        this.lblPlaats = new Label("*Plaats: ");
        this.lblAfstand = new Label("*Afstand: ");
        this.lblPrijs = new Label("*Prijs: ");
        this.lblFoto = new Label("Foto");
        this.lblOmschrijving = new Label("*Omschrijving: ");
        this.lblcbCategorie = new Label("*Categorie: ");
        this.lblFoutCategorie = new Label();
        lblFoutCategorie.setTextFill(Paint.valueOf("red"));

        //definieren textfields
        this.tfCategorieNaam = new TextField();
        this.tfID = new TextField();
        tfID.setPromptText("max 3 karakters");
        this.tfNaam = new TextField();
        this.tfDatum = new TextField();
        this.tfTijd = new TextField();
        this.tfAdres = new TextField();
        this.tfPlaats = new TextField();
        this.tfAfstand = new TextField();
        this.tfPrijs = new TextField();
        this.tfFoto = new TextField();
        this.taOmschrijving = new TextArea();
        taOmschrijving.setWrapText(true);
        taOmschrijving.setPromptText("max 2000 karakters");
        

        //buttons
        this.btnCategorieToevoegen = new Button("Voeg categorie toe");
        btnCategorieToevoegen.setStyle("-fx-background-color: #003366");
        btnCategorieToevoegen.setTextFill(Paint.valueOf("white"));
        btnCategorieToevoegen.setOnAction(e -> {

            String naam = tfCategorieNaam.getText();

            if (categorieDAO.voegCategorieToe(naam)) {
                berichtenBox.GeefBerichtenBox("Invoer categorieën", "Categorie " + naam + " is toegevoegd!");
                cbCategorieën.getItems().clear();
                ArrayList<String> categorieën = categorieDAO.geefCategorieën();

                for (String naamcat : categorieën) {
                    cbCategorieën.getItems().add(naamcat);
                }
                tfCategorieNaam.clear();
                lblFoutCategorie.setText("");

            } else {
                lblFoutCategorie.setText("Categorie bestaat al!");
            }

        });

        //combobox
        this.cbCategorieën = new ComboBox<>();
        cbCategorieën.setPromptText("Kies een categorie");

        ArrayList<String> categorieën = categorieDAO.geefCategorieën();

        for (String naam : categorieën) {
            cbCategorieën.getItems().add(naam);
        }

        cbCategorieën.setOnAction(e -> {

            categorie = cbCategorieën.getValue();

        });

        this.btnActiviteitToevoegen = new Button("Voeg activiteit toe");
        btnActiviteitToevoegen.setStyle("-fx-background-color: #003366");
        btnActiviteitToevoegen.setTextFill(Paint.valueOf("white"));
        btnActiviteitToevoegen.setOnAction(e -> {

            Activiteit activiteit = new Activiteit(tfID.getText(), tfNaam.getText(), tfAdres.getText(),
                    tfPlaats.getText(), Integer.parseInt(tfAfstand.getText()), tfTijd.getText(),
                    tfDatum.getText(), Integer.parseInt(tfPrijs.getText()), taOmschrijving.getText(),
                    tfFoto.getText(), categorie);

            if (activiteitDAO.voegActiviteitToe(activiteit)) {
                berichtenBox.GeefBerichtenBox("Invoer activiteiten", "Activiteit " + activiteit.getNaam() + " is toegevoegd!");

                tfID.clear();
                tfNaam.clear();
                tfDatum.clear();
                tfTijd.clear();
                tfAdres.clear();
                tfPlaats.clear();
                tfAfstand.clear();
                tfPrijs.clear();
                tfFoto.clear();
                taOmschrijving.clear();
                
            } else {
                berichtenBox.GeefBerichtenBox("Invoer activiteiten", "Activiteit bestaat al!");

            }

        });

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));

        layout.add(lblTitelCategorie, 0, 0);
        layout.add(lblcategorie, 0, 1);
        layout.add(tfCategorieNaam, 1, 1);
        layout.add(lblFoutCategorie, 1, 3);
        layout.add(btnCategorieToevoegen, 1, 2);
        layout.add(lblTitelActiviteit, 0, 4);
        layout.add(lblID, 0, 5);
        layout.add(tfID, 1, 5);
        layout.add(lblNaam, 0, 6);
        layout.add(tfNaam, 1, 6);
        layout.add(lblDatum, 0, 7);
        layout.add(tfDatum, 1, 7);
        layout.add(lblTijd, 0, 8);
        layout.add(tfTijd, 1, 8);
        layout.add(lblAdres, 0, 9);
        layout.add(tfAdres, 1, 9);
        layout.add(lblPlaats, 0, 10);
        layout.add(tfPlaats, 1, 10);
        layout.add(lblAfstand, 0, 11);
        layout.add(tfAfstand, 1, 11);
        layout.add(lblPrijs, 0, 12);
        layout.add(tfPrijs, 1, 12);
        layout.add(lblFoto, 0, 13);
        layout.add(tfFoto, 1, 13);
        layout.add(lblOmschrijving, 0, 14);
        layout.add(taOmschrijving, 1, 14);
        layout.add(lblcbCategorie, 0, 15);
        layout.add(cbCategorieën, 1, 15);
        layout.add(btnActiviteitToevoegen, 1, 16);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
