

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
public class Gebruiker {

    private int Id;
    private String email;
    private String wachtwoord;
    private String naam;
    private int leeftijd;
    private String plaats;
    private String imgURL;

    public Gebruiker(int Id, String email, String wachtwoord, String naam, int leeftijd, String plaats, String imgURL) {
        this.Id = Id;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.plaats = plaats;
        this.imgURL = imgURL;
    }

    public int getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public String getNaam() {
        return naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public String getPlaats() {
        return plaats;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
