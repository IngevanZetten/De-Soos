/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.Gebruiker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.net.nt.ConnOption;

/**
 *
 * @author Inge
 */
public class GebruikerDAO {

    

    DbConnector dbConnector = new DbConnector();

    public int valideerLogin(String email, String wachtwoord) {

        Connection conn = null;
        ResultSet resultSet;
        int count = 0;
        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT email, wachtwoord FROM Gebruikers WHERE email = " + "'" + email + "'"
                    + "AND wachtwoord = " + "'" + wachtwoord + "'";
            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    count = count + 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

    public int geefGebruikersID(String email, String wachtwoord) {

        int id = 0;
        Connection conn = null;
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT id FROM Gebruikers WHERE email = " + "'" + email + "'"
                    + "AND wachtwoord = " + "'" + wachtwoord + "'";
            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    public Gebruiker geefGebruikerBijId(int idGebruiker) {

        Gebruiker gebruiker = null;
        Connection conn = null;
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Gebruikers WHERE id = " + idGebruiker + "";
            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String wachtwoord = resultSet.getString("wachtwoord");
                    String naam = resultSet.getString("naam");
                    int leeftijd = resultSet.getInt("leeftijd");
                    String plaats = resultSet.getString("plaats");
                    String imgURL = resultSet.getString("foto");

                    gebruiker = new Gebruiker(id, email, wachtwoord, naam, leeftijd, plaats, imgURL);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gebruiker;

    }

}
