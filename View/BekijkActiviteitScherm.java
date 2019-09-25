/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.AanmeldingDAO;
import Datalayer.ActiviteitDAO;
import Model.Activiteit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Inge
 */
public class BekijkActiviteitScherm {

    private Label lblNaam, lblDatum, lblTijd,
            lblAdres, lblPlaats, lblAfstand, lblPrijs;
    private TextArea taOmschrijving;
    private VBox vboxActiviteitInfo;
    private HBox hboxNaamFoto, hboxButtons, hboxLettertype;
    private ImageView imgFoto;
    private Button btnTerug, btnUitschrijven, btnVergroot, btnVerklein;
    private GridPane layout;
    private static int idGebruiker;
    private static String idActviteit;
    private ActiviteitDAO activiteitDAO;
    private AanmeldingDAO aanmeldingDAO;
    private BerichtenBox berichtenBox;

    public void geefScherm(String naamActiviteit) {

        Stage window = new Stage();

        //Zorgt ervoor dat er niks anders gedaan kan worden tot acties op de alertbox zijn verwerkt
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Bekijk activiteit");
        window.setMinWidth(600);
        window.setMinHeight(600);

        //definieren dao's
        this.activiteitDAO = new ActiviteitDAO();
        this.aanmeldingDAO = new AanmeldingDAO();
        
        this.berichtenBox = new BerichtenBox();

        //layout definieren
        this.lblNaam = new Label("Naam");
        lblNaam.setFont(new Font("Arial", 25));
        lblNaam.setWrapText(true);

        this.lblDatum = new Label("Datum");
        this.lblTijd = new Label("Tijd");
        this.lblAdres = new Label("Adres");
        this.lblPlaats = new Label("Plaats");
        this.lblAfstand = new Label("Afstand");
        this.lblPrijs = new Label("Prijs");

        this.taOmschrijving = new TextArea();
        taOmschrijving.setEditable(false);
        taOmschrijving.setWrapText(true);

        //definieren imageview
        this.imgFoto = new ImageView();
        Image imageFoto = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\activiteitDefault.jpg");
        imgFoto.setImage(imageFoto);
        imgFoto.setFitHeight(150);
        imgFoto.setFitWidth(150);

        //nodes vullen met waarden van aangeklikte object
        String id =  activiteitDAO.geefActviteitID(naamActiviteit);
        Activiteit activiteit = activiteitDAO.geefActviteitBijId(id);
        lblNaam.setText(activiteit.getNaam());
        lblDatum.setText("Datum: " + activiteit.getDatum());
        lblTijd.setText("Tijd: " + activiteit.getTijd());
        lblAdres.setText("Adres: " + activiteit.getAdres());
        lblPlaats.setText("Plaats: " + activiteit.getPlaats());
        lblAfstand.setText("Afstand: " + Integer.toString(activiteit.getAfstand()) + " km");
        lblPrijs.setText("Prijs: " + Integer.toString(activiteit.getPrijs()) + " euro");
        taOmschrijving.setText(activiteit.getOmschrijving());

        //button lettertype vergoten
        this.btnVergroot = new Button("aA");
        btnVergroot.setStyle("-fx-background-color: #003366");
        btnVergroot.setTextFill(Paint.valueOf("white"));
        btnVergroot.setOnAction(e -> {

            layout.setStyle("-fx-font: 20\"Arial\";");

        });

        //button lettertype verkleinen
        this.btnVerklein = new Button("Aa");
        btnVerklein.setStyle("-fx-background-color: #003366");
        btnVerklein.setTextFill(Paint.valueOf("white"));
        btnVerklein.setOnAction(e -> {

            layout.setStyle("-fx-font: 16\"Arial\";");

        });

        //button terug naar dit scherm
        btnTerug = new Button("Terug");
        btnTerug.setStyle("-fx-background-color: #CCCC99;");
        btnTerug.setOnAction(e -> {

            window.close();

        });

        //button uitschrijven
        this.btnUitschrijven = new Button("Uitschrijven");
        btnUitschrijven.setStyle("-fx-background-color: #CCCC99;");
        btnUitschrijven.setOnAction(e -> {
        
            if(aanmeldingDAO.verwijderAanmelding(idGebruiker, id)){
                berichtenBox.GeefBerichtenBox("Aanmelding", "U bent uitgeschreven voor deze activiteit");
                window.close();
            }
            
        
        });

        //hbox buttons lettertype vergoten/verkleinen
        this.hboxLettertype = new HBox();
        hboxLettertype.setAlignment(Pos.CENTER_RIGHT);
        hboxLettertype.setSpacing(10);
        hboxLettertype.getChildren().addAll(btnVergroot, btnVerklein);

        //hbox buttons
        this.hboxButtons = new HBox();
        hboxButtons.setSpacing(10);
        hboxButtons.setAlignment(Pos.CENTER_RIGHT);
        hboxButtons.getChildren().addAll(btnTerug, btnUitschrijven);

        //hbox met informatie activiteit
        this.hboxNaamFoto = new HBox();
        hboxNaamFoto.setSpacing(10);
        hboxNaamFoto.setAlignment(Pos.CENTER_LEFT);
        hboxNaamFoto.getChildren().addAll(imgFoto, lblNaam);

        //Vbox activiteitInfo vull
        this.vboxActiviteitInfo = new VBox();
        vboxActiviteitInfo.setSpacing(10);

        vboxActiviteitInfo.getChildren().addAll(hboxNaamFoto, lblDatum, lblTijd, lblAdres,
                lblPlaats, lblAfstand, lblPrijs, taOmschrijving);

        //layout
        layout = new GridPane();

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        layout.add(hboxLettertype, 0, 0);
        layout.add(vboxActiviteitInfo, 0, 1);
        layout.add(hboxButtons, 0, 2);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));

        //Toon scherm aan gebruiker en wacht totdat het scherm gesloten wordt voor terugkeren
        //naar vorig scherm
        Scene scene = new Scene(layout);
        
        window.setScene(scene);
        window.showAndWait();

    }

    public static void setId(int id) {
        BekijkActiviteitScherm.idGebruiker = id;
    }

    public static void setActiviteitID(String activiteitID) {
        BekijkActiviteitScherm.idActviteit = activiteitID;
    }

}
