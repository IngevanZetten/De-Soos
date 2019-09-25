/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.GebruikerDAO;
import Model.Gebruiker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Inge
 */
public class ProfielView extends GridPane {

    private ImageView imgProfielFoto;
    private Label lblNaam, lblLeeftijd, lblPlaats, lblEmail;
    private TextField tfNaam, tfLeeftijd, tfPlaats, tfEmail;
    private VBox vBox;
    private Gebruiker gebruiker;
    private JavaFXBpDeSoos jeBpDeSoos = new JavaFXBpDeSoos();
    private GebruikerDAO gebruikerDAO;
    private String mail, wachtwoord;
    private static int id;

    public ProfielView(Pane mainPane) {

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(30, 10, 10, 10));

        this.gebruikerDAO = new GebruikerDAO();

        this.imgProfielFoto = new ImageView();
        Image profielFoto = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\defaultFoto.jpg");
        imgProfielFoto.setImage(profielFoto);
        imgProfielFoto.setFitHeight(300);
        imgProfielFoto.setFitWidth(250);
        
        //labels aanmaken
        this.lblNaam = new Label("Naam: ");
        this.lblLeeftijd = new Label("Leeftijd: ");
        this.lblPlaats = new Label("Plaats: ");
        this.lblEmail = new Label("Email: ");

        //textfields aanmaken
        this.tfNaam = new TextField();
        this.tfLeeftijd = new TextField();
        this.tfPlaats = new TextField();
        this.tfEmail = new TextField();

        //geef gebruiker bij id en vul tekstvakken met waarden die horen bij deze gebruiker
        
        gebruiker = gebruikerDAO.geefGebruikerBijId(id);
        tfNaam.setText(gebruiker.getNaam());
        tfLeeftijd.setText(Integer.toString(gebruiker.getLeeftijd()));
        tfPlaats.setText(gebruiker.getPlaats());
        tfEmail.setText(gebruiker.getEmail());

        this.vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(imgProfielFoto, lblNaam, tfNaam, lblLeeftijd,
                tfLeeftijd, lblPlaats, tfPlaats, lblEmail, tfEmail);

        add(vBox, 0, 0);

        mainPane.getChildren().add(this);

    }
    
    public static void setId(int id){
         ProfielView.id = id;
    }

}
