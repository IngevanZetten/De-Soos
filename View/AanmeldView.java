/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.AanmeldingDAO;
import Datalayer.ActiviteitDAO;
import Datalayer.CategorieDAO;
import Model.Activiteit;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Inge
 */
public class AanmeldView extends GridPane {

    private Label lblcbCategorieën, lblcbActiviteiten, lblNaam, lblDatum, lblTijd,
            lblAdres, lblPlaats, lblAfstand, lblPrijs;
    private TextArea taOmschrijving;
    private ComboBox<String> cbCategorieën, cbActiviteiten;
    private Button btnBekijk, btnAanmelden;
    private VBox vboxCombobox, vboxActiviteitInfo;
    private HBox hboxNaamFoto;
    private Separator separator;
    private ImageView imgFoto;
    private CategorieDAO categorieDAO;
    private ActiviteitDAO activiteitDAO;
    private AanmeldingDAO aanmeldingDAO;
    private String naamActiviteit;
    private static int idGebruiker;
    private String activiteitID;
    private BerichtenBox berichtenBox;

    public AanmeldView(Pane mainPane) {

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(20, 10, 10, 10));

        this.berichtenBox = new BerichtenBox();

        //definieren dao's
        this.categorieDAO = new CategorieDAO();
        this.activiteitDAO = new ActiviteitDAO();
        this.aanmeldingDAO = new AanmeldingDAO();

        //lijn tussen comboboxen en informatie over activiteit
        this.separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        //definieren labels
        this.lblcbCategorieën = new Label("Kies een categorie: ");
        this.lblcbActiviteiten = new Label("Kies een activiteit: ");
        this.lblNaam = new Label("Naam");
        lblNaam.setFont(new Font("Arial", 30));
        lblNaam.setWrapText(true);

        this.lblDatum = new Label("Datum");
        this.lblTijd = new Label("Tijd");
        this.lblAdres = new Label("Adres");
        this.lblPlaats = new Label("Plaats");
        this.lblAfstand = new Label("Afstand");
        this.lblPrijs = new Label("Prijs");

        this.taOmschrijving = new TextArea();
        taOmschrijving.setWrapText(true);
        taOmschrijving.setEditable(false);

        //definieren imageview
        this.imgFoto = new ImageView();
        Image imageFoto = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\activiteitDefault.jpg");
        imgFoto.setImage(imageFoto);
        imgFoto.setFitHeight(225);
        imgFoto.setFitWidth(225);

        //definieren comboboxen
        this.cbCategorieën = new ComboBox<>();
        cbCategorieën.setPromptText("Categorieën");
        cbCategorieën.setMinWidth(280);
        cbCategorieën.setStyle("-fx-selection-bar: #003366;");

        this.cbActiviteiten = new ComboBox<>();
        cbActiviteiten.setPromptText("Activiteiten");
        cbActiviteiten.setMinWidth(280);
        cbActiviteiten.setMaxWidth(280);
        cbActiviteiten.setStyle("-fx-selection-bar: #003366;");

        //comboboxen vullen
        ArrayList<String> categorieën = categorieDAO.geefCategorieën();
        for (int i = 0; i < categorieën.size(); i++) {

            cbCategorieën.getItems().add(categorieën.get(i));
        }

        cbCategorieën.setOnAction(e -> {

            String naamCategorie = cbCategorieën.getValue();
            ArrayList<String> activiteitenLijst = activiteitDAO.geefActiviteiten(naamCategorie);
            cbActiviteiten.getItems().clear();
            for (int i = 0; i < activiteitenLijst.size(); i++) {

                cbActiviteiten.getItems().add(activiteitenLijst.get(i));
            }

        });

        //definieren button bekijk activiteiten
        this.btnBekijk = new Button("Bekijk Activiteit");
        btnBekijk.setStyle("-fx-background-color: #003366");
        btnBekijk.setTextFill(Paint.valueOf("white"));
        btnBekijk.setOnAction(e -> {

        });

        btnBekijk.setOnAction(e -> {

            vboxActiviteitInfo.setVisible(true);
            btnAanmelden.setVisible(true);
            this.naamActiviteit = cbActiviteiten.getValue();
            activiteitID = activiteitDAO.geefActviteitID(naamActiviteit);
            Activiteit activiteit = activiteitDAO.geefActviteitBijId(activiteitID);

            lblNaam.setText(activiteit.getNaam());
            lblDatum.setText("Datum: " + activiteit.getDatum());
            lblTijd.setText("Tijd: " + activiteit.getTijd());
            lblAdres.setText("Adres: " + activiteit.getAdres());
            lblPlaats.setText("Plaats: " + activiteit.getPlaats());
            lblAfstand.setText("Afstand: " + Integer.toString(activiteit.getAfstand()) + " km");
            lblPrijs.setText("Prijs: " + Integer.toString(activiteit.getPrijs()) + " euro");
            taOmschrijving.setText(activiteit.getOmschrijving());
            
            
        });

        //vbox met keuze opties
        this.vboxCombobox = new VBox();
        vboxCombobox.setSpacing(20);

        vboxCombobox.getChildren().addAll(lblcbCategorieën, cbCategorieën,
                lblcbActiviteiten, cbActiviteiten, btnBekijk);

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
        vboxActiviteitInfo.setVisible(false);

        //button aanmelden
        this.btnAanmelden = new Button("Aanmelden");
        btnAanmelden.setStyle("-fx-font-weight: bold;");
        btnAanmelden.setBackground(new Background(new BackgroundFill(Paint.valueOf("#CCCC99"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAanmelden.setVisible(false);

        btnAanmelden.setOnAction(e -> {

            if (aanmeldingDAO.voegAanmeldingToe(idGebruiker, activiteitID)) {
                berichtenBox.GeefBerichtenBox("Aanmelding", "U bent aangemeld voor de activiteit! \n"
                        + "Deze activiteit is nu toegevoegd aan uw activiteiten. ");
            } else {
                berichtenBox.GeefBerichtenBox("Aanmelding", "U bent al aangemeld voor deze activiteit!");
            }

        });

        //voeg nodes toe aan layout
        add(vboxCombobox, 0, 0);
        add(separator, 1, 0);
        add(vboxActiviteitInfo, 2, 0);
        add(btnAanmelden, 2, 1);
        mainPane.getChildren().add(this);
        

    }

    public static void setId(int id) {
        AanmeldView.idGebruiker = id;
    }

}
