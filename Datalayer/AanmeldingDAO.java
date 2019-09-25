/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * Data acces object voor de model klasse Aanmelding. Hierin worden alle database opdrachten die nodig zijn 
 * voor de klasse Aanmelding via methoden gemaakt.
 * @author Inge
 */
public class AanmeldingDAO {

    DbConnector dbConnector = new DbConnector();

    /**
     * Methode die een aanmelding invoert in de database
     * @param idGebruiker
     * @param idActiviteit
     * @return booleanwaarde true als statement uitgevoerd is, false als statement niet uitgevoerd is.
     */
    public boolean voegAanmeldingToe(int idGebruiker, String idActiviteit) {

        Connection conn = null;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO aanmeldingen values(" + idGebruiker + ",'"
                    + idActiviteit + "'" + ")";
            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean verwijderAanmelding(int idGebruiker, String idActiviteit) {

        Connection conn = null;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM aanmeldingen WHERE idgebruiker = " + idGebruiker + "AND idactiviteit = " + "'" + idActiviteit + "'" + "";
            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
