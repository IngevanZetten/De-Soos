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
public class Activiteit {

    private String id;
    private String naam;
    private String adres;
    private String plaats;
    private int afstand;
    private String tijd;
    private String datum;
    private int prijs;
    private String omschrijving;
    private String imgURL;
    private String categorie;

    public Activiteit(String id, String naam, String adres, String plaats, int afstand, String tijd, String datum, int prijs, String omschrijving, String imgURL, String categorie) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.plaats = plaats;
        this.afstand = afstand;
        this.tijd = tijd;
        this.datum = datum;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        this.imgURL = imgURL;
        this.categorie = categorie;
    }

    public String getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getAdres() {
        return adres;
    }

    public String getPlaats() {
        return plaats;
    }

    public int getAfstand() {
        return afstand;
    }

    public String getTijd() {
        return tijd;
    }

    public String getDatum() {
        return datum;
    }

    public int getPrijs() {
        return prijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void setAfstand(int afstand) {
        this.afstand = afstand;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    
}
