/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Inge
 */
public class OverzichtAanmelding {

    private String imgURL;
    private String naam;

    public OverzichtAanmelding(String imgURL, String naam) {
        this.imgURL = imgURL;
        this.naam = naam;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getNaam() {
        return naam;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

}
