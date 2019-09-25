/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.Activiteit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Inge
 */
public class ActiviteitDAO {

    DbConnector dbConnector = new DbConnector();

    public ArrayList<String> geefActiviteiten(String naamCategorie) {

        ArrayList<String> activiteiten = new ArrayList<>();
        Connection conn = null;
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT naam FROM activiteiten WHERE naamCategorie = " + "'" + naamCategorie + "'" + "";
            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String activiteit = resultSet.getString("naam");
                    activiteiten.add(activiteit);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return activiteiten;
    }

    public String geefActviteitID(String naam) {

        Connection conn = null;
        String id = null;
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            

            if (statement.execute("SELECT * FROM activiteiten WHERE naam = " + "'" + naam + "'")) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    id = resultSet.getString("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Activiteit geefActviteitBijId(String idActviteit) {

        Activiteit activiteit = null;
        Connection conn = null;
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM activiteiten WHERE id = " + "'" + idActviteit + "'" + "";
            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String naam = resultSet.getString("naam");
                    String adres = resultSet.getString("adres");
                    String plaats = resultSet.getString("plaats");
                    int afstand = resultSet.getInt("afstand");
                    String tijd = resultSet.getString("tijd");
                    String datum = resultSet.getString("datum");
                    int prijs = resultSet.getInt("prijs");
                    String omschrijving = resultSet.getString("omschrijving");
                    String imgURL = resultSet.getString("foto");
                    String categorie = resultSet.getString("naamCategorie");

                    activiteit = new Activiteit(id, naam, adres, plaats, afstand, tijd, datum, prijs, omschrijving, imgURL, categorie);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return activiteit;
    }
    
    public boolean voegActiviteitToe(Activiteit activiteit){
        
        Connection conn = null;
        Activiteit act = activiteit;
        
        String id = act.getId();
        String naam = act.getNaam();
        String datum = act.getDatum();
        String adres = act.getAdres();
        String plaats = act.getPlaats();
        int afstand = act.getAfstand();
        String tijd = act.getTijd();
        int prijs = act.getPrijs();
        String omschrijving = act.getOmschrijving();
        String foto = act.getImgURL();
        String categorie = act.getCategorie();
        
        try {
            
            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO activiteiten values('" + id + "','"
                    + naam + "','" + adres + "','" + plaats + "'," + afstand + ",'" + tijd + "','" +
                    datum + "'," + prijs + ",'" + omschrijving + "','" + foto + "','" + categorie + "')";
            
            statement.executeUpdate(sql);
            return true;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
