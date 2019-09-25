/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.OverzichtAanmelding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Inge
 */
public class OverzichtAanmeldingDAO {

    DbConnector dbConnector = new DbConnector();

    public ArrayList<OverzichtAanmelding> geefAanmelding(int idGebruiker) {

        Connection conn = null;
        ArrayList<OverzichtAanmelding> aanmeldingen = new ArrayList<>();
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String url = "SELECT foto, naam FROM Aanmeldingen JOIN Activiteiten ON Aanmeldingen.idactiviteit = activiteiten.id "
                    + "WHERE idgebruiker = " + "'" + idGebruiker + "'" + "";
            if (statement.execute(url)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String imgURL = resultSet.getString("foto");
                    String naam = resultSet.getString("naam");

                    OverzichtAanmelding oa = new OverzichtAanmelding(imgURL, naam);
                    aanmeldingen.add(oa);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return aanmeldingen;
    }

}
