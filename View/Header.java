/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Inge
 */
public class Header {
    
    private ImageView imageViewLogo;
    private Label lblheader;
    
    public HBox geefHeader(){
        
        this.imageViewLogo = new ImageView();
        Image imageLogo = new Image("file:D:\\AD informatica\\Beroepsproduct Inge van Zetten(2058751)\\logo.jpg");
        imageViewLogo.setImage(imageLogo);
        imageViewLogo.setFitHeight(70);
        imageViewLogo.setFitWidth(70);
        this.lblheader = new Label("De soos - Roosendaal");
        lblheader.setFont(new Font(20));
        
        lblheader.setStyle("-fx-text-fill: #003366;");
        
        
        
        HBox hboxHeader = new HBox();
        hboxHeader.setAlignment(Pos.CENTER);
        hboxHeader.setSpacing(10);
        hboxHeader.setStyle("-fx-border-color: lightgrey");
        hboxHeader.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        hboxHeader.getChildren().addAll(imageViewLogo, lblheader);
        
        return hboxHeader;
        
    }
    
}
