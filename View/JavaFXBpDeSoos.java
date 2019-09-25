/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.DbConnector;
import Datalayer.GebruikerDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;

/**
 * blauw: #003366 groen: #CCCC99 roze: #DAC3B3
 *
 * @author Inge
 */
public class JavaFXBpDeSoos extends Application {

    private DbConnector dbConnector;
    private Header header;
    private HBox hboxLettergrootte, hboxHeader;
    private Button btnVergroot, btnVerklein, btnInlog, btnBeheerder;
    private VBox root, vboxWelkom, vboxInlog;
    private Label lblWelkom, lblFoutInlog;
    private ImageView imgLogo;
    private TextField tfGebruikersnaam;
    private PasswordField tfWachtwoord;
    private DeSoosMenu menu;
    private GebruikerDAO gebruikerDAO;

    @Override
    public void start(Stage primaryStage) {

        //check verbinding met database
        dbConnector = new DbConnector();
        if (dbConnector.createConnection() != null) {
            System.out.println("Connected to database");
        } else {
            System.out.println("no connection");
        }

        this.gebruikerDAO = new GebruikerDAO();

        //Welkom scherm nodes
        this.lblWelkom = new Label("De Soos Roosendaal - Inloggen");
        lblWelkom.setFont(new Font(25));
        lblWelkom.setTextFill(Paint.valueOf("#003366"));

        this.imgLogo = new ImageView();
        Image imageLogo = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\logo.jpg");
        imgLogo.setImage(imageLogo);
        imgLogo.setFitHeight(300);
        imgLogo.setFitWidth(300);

        //vbox welkoms scherm
        this.vboxWelkom = new VBox();
        vboxWelkom.setSpacing(20);
        vboxWelkom.setAlignment(Pos.CENTER);
        vboxWelkom.getChildren().addAll(imgLogo, lblWelkom);

        //inlog nodes
        this.tfGebruikersnaam = new TextField();
        tfGebruikersnaam.setPromptText("Gebruikersnaam");
        this.tfWachtwoord = new PasswordField();
        tfWachtwoord.setPromptText("wachtwoord");

        this.btnInlog = new Button("Inloggen");
        btnInlog.setStyle("-fx-background-color: #003366");
        btnInlog.setTextFill(Paint.valueOf("white"));
        btnInlog.setFont(new Font(20));

        this.lblFoutInlog = new Label();
        lblFoutInlog.setTextFill(Paint.valueOf("red"));

        //inlog button functionaliteit
        btnInlog.setOnAction(e -> {

            int count = gebruikerDAO.valideerLogin(tfGebruikersnaam.getText(), tfWachtwoord.getText());
            if (count == 1) {
                hboxLettergrootte.setVisible(true);
                hboxHeader.setVisible(true);
                menu.setVisible(true);
                vboxInlog.setVisible(false);
                btnBeheerder.setVisible(false);
                lblWelkom.setText("Welkom bij de Soos - Roosendaal!");
                int id = gebruikerDAO.geefGebruikersID(tfGebruikersnaam.getText(), tfWachtwoord.getText());
                View.ProfielView.setId(id);
                View.AanmeldView.setId(id);
                View.BekijkActiviteitScherm.setId(id);
                View.ActiviteitBekijkView.setId(id);

            } else if (count > 1) {
                lblFoutInlog.setText("Dubbbele gebruiker, toegang geweigerd");
            } else {
                lblFoutInlog.setText("Gebruiker niet gevonden!");
            }

        });

        //inloggen beheerder button
        this.btnBeheerder = new Button("Ik ben beheerder");
        btnBeheerder.setStyle("-fx-font-weight: bold");

        HBox hboxBeheerder = new HBox();
        hboxBeheerder.setAlignment(Pos.CENTER);
        hboxBeheerder.setPadding(new Insets(10));
        hboxBeheerder.getChildren().add(btnBeheerder);
        btnBeheerder.setOnAction(e -> {

            InlogBeheerder inlogBeheerder = new InlogBeheerder();
            inlogBeheerder.geefScherm();

        });

        this.header = new Header();
        hboxHeader = header.geefHeader();
        hboxHeader.setVisible(false);

        //buttons om lettertype te vergoten en verkleinen
        this.btnVergroot = new Button("aA");
        btnVergroot.setTextFill(Paint.valueOf("white"));
        btnVergroot.setStyle("-fx-font-weight: bold");
        btnVergroot.setBackground(new Background(new BackgroundFill(Paint.valueOf("#003366"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.btnVerklein = new Button("Aa");
        btnVerklein.setTextFill(Paint.valueOf("white"));
        btnVerklein.setStyle("-fx-font-weight: bold");
        btnVerklein.setBackground(new Background(new BackgroundFill(Paint.valueOf("#003366"), CornerRadii.EMPTY, Insets.EMPTY)));

        btnVergroot.setOnAction(e -> {

            root.setStyle("-fx-font: 20\"Arial\";");
            //primaryStage.setWidth(1100);
            //primaryStage.setHeight(1100);

        });

        btnVerklein.setOnAction(e -> {

            root.setStyle("-fx-font: 16\"Arial\";");

        });

        // zet buttons lettergrootte in een hbox
        this.hboxLettergrootte = new HBox();
        hboxLettergrootte.setAlignment(Pos.CENTER_RIGHT);
        hboxLettergrootte.setBackground(new Background(new BackgroundFill(Paint.valueOf("#003366"), CornerRadii.EMPTY, Insets.EMPTY)));
        hboxLettergrootte.getChildren().addAll(btnVergroot, btnVerklein);
        hboxLettergrootte.setVisible(false);

        //voeg inlognodes toe aan vbox
        this.vboxInlog = new VBox();
        vboxInlog.setSpacing(20);
        vboxInlog.setPadding(new Insets(10, 5, 5, 5));
        vboxInlog.setAlignment(Pos.CENTER);
        vboxInlog.getChildren().addAll(tfGebruikersnaam, tfWachtwoord, btnInlog, lblFoutInlog);

        //layout
        GridPane mainPane = new GridPane();
        mainPane.add(vboxWelkom, 0, 0);
        mainPane.add(vboxInlog, 0, 1);
        mainPane.add(hboxBeheerder, 0, 2);
        mainPane.setPadding(new Insets(50, 10, 10, 10));
        mainPane.setAlignment(Pos.CENTER);
        menu = new DeSoosMenu(mainPane);
        menu.setVisible(false);

        root = new VBox(hboxHeader, hboxLettergrootte, menu, mainPane);
        root.setStyle("-fx-font: 16\"Arial\";");
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, 1100, 900);

        primaryStage.setTitle("De Soos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
