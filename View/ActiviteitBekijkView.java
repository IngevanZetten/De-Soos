/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.ActiviteitDAO;
import Datalayer.OverzichtAanmeldingDAO;
import Model.OverzichtAanmelding;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Inge
 */
public class ActiviteitBekijkView extends GridPane {

    private Button btnBekijk; //btnUitschrijven
    private Label lblNaam, lblTitel;
    private ImageView imgActiviteit;
    private BekijkActiviteitScherm bekijkActiviteitScherm;
    private static int idGebruiker;
    private OverzichtAanmeldingDAO overzichtAanmeldingDAO;
    private ActiviteitDAO activiteitDAO;
    private ArrayList<HBox> hboxLijst;

    public ActiviteitBekijkView(Pane mainPane) {

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(10, 10, 10, 10));

        //definieren dao's
        this.overzichtAanmeldingDAO = new OverzichtAanmeldingDAO();
        this.activiteitDAO = new ActiviteitDAO();

        this.lblTitel = new Label("Activiteiten waarvoor u zich heeft aangemeld: ");
        lblTitel.setFont(new Font(20));

        this.bekijkActiviteitScherm = new BekijkActiviteitScherm();

        ArrayList<OverzichtAanmelding> aanmeldingen = overzichtAanmeldingDAO.geefAanmelding(idGebruiker);
        this.hboxLijst = new ArrayList<>();

        for (OverzichtAanmelding oa : aanmeldingen) {
            this.lblNaam = new Label();
            lblNaam.setFont(new Font("Arial", 20));
            lblNaam.setText(oa.getNaam());
            lblNaam.setMinWidth(350);
            this.imgActiviteit = new ImageView();
            Image ActiviteitFoto = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\activiteitDefault.jpg");
            imgActiviteit.setImage(ActiviteitFoto);
            imgActiviteit.setFitHeight(150);
            imgActiviteit.setFitWidth(200);
            this.btnBekijk = new Button("Bekijk");
            btnBekijk.setStyle("-fx-background-color: #003366");
            btnBekijk.setTextFill(Paint.valueOf("white"));

            btnBekijk.setOnAction(e -> {

                bekijkActiviteitScherm.geefScherm(oa.getNaam());

            });

            HBox hbox = new HBox();
            hbox.setSpacing(30);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.getChildren().addAll(imgActiviteit, lblNaam, btnBekijk);

            hboxLijst.add(hbox);
        }

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        //vBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMinSize(750, 500);
        scrollPane.setStyle("-fx-background-color: transparant");

        for (HBox hBox : hboxLijst) {
            vBox.getChildren().add(hBox);
        }

        add(lblTitel, 0, 0);
        add(scrollPane, 0, 1);
        mainPane.getChildren().add(this);

    }

    public static void setId(int id) {
        ActiviteitBekijkView.idGebruiker = id;
    }

}
