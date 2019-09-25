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
public class Aanmelding {

    private int idGebruiker;
    private String idActiviteit;

    public Aanmelding(int idGebruiker, String idActiviteit) {
        this.idGebruiker = idGebruiker;
        this.idActiviteit = idActiviteit;
    }

    public int getIdGebruiker() {
        return idGebruiker;
    }

    public String getIdActiviteit() {
        return idActiviteit;
    }

    public void setIdGebruiker(int idGebruiker) {
        this.idGebruiker = idGebruiker;
    }

    public void setIdActiviteit(String idActiviteit) {
        this.idActiviteit = idActiviteit;
    }

}
