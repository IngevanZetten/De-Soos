/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Font;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 *
 * @author Inge
 */
public class DeSoosMenu extends MenuBar {

    private MenuItem miProfielBekijken, miActiviteitenAanmelden, miActiviteitenBekijken;
    private Menu mProfiel, mActiviteiten, mMijnActviteiten;

    public DeSoosMenu(Pane mainPane) {

        this.mProfiel = new Menu("Profiel");
        this.mActiviteiten = new Menu("Activiteiten");
        this.mMijnActviteiten = new Menu("Mijn activiteiten");

        this.miProfielBekijken = new MenuItem("Profiel bekijken");
        this.miActiviteitenAanmelden = new MenuItem("Aanmelden voor activiteit");
        this.miActiviteitenBekijken = new MenuItem("Mijn activiteiten bekijken");

        miProfielBekijken.setOnAction(e -> {

            mainPane.getChildren().clear();
            new ProfielView(mainPane);

        });

        miActiviteitenAanmelden.setOnAction(e -> {

            mainPane.getChildren().clear();
            new AanmeldView(mainPane);

        });
        
        miActiviteitenBekijken.setOnAction(e -> {
        
            mainPane.getChildren().clear();
            new ActiviteitBekijkView(mainPane);
        
        });

        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#DAC3B3"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-selection-bar: #003366;");

        mActiviteiten.getItems().add(miActiviteitenAanmelden);
        mProfiel.getItems().add(miProfielBekijken);
        mMijnActviteiten.getItems().add(miActiviteitenBekijken);
        getMenus().addAll(mProfiel, mActiviteiten, mMijnActviteiten);

    }

}
