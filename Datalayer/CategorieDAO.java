/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Inge
 */
public class CategorieDAO {

    DbConnector dbConnector = new DbConnector();

    public ArrayList<String> geefCategorieën() {

        Connection conn = null;
        ArrayList<String> lijstCategorieën = new ArrayList<>();
        ResultSet resultSet;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            if (statement.execute("SELECT naam FROM categorieën")) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lijstCategorieën.add(resultSet.getString("naam"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lijstCategorieën;

    }

    public boolean voegCategorieToe(String naam) {

        Connection conn = null;

        try {

            conn = dbConnector.createConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO Categorieën values('" + naam.toLowerCase() + "'" + ")";

            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

}
