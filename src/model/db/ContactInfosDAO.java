package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactInfosDAO {
    protected Statement
            statement;

    public ContactInfosDAO() {
        DbConnector.createConnexion();
        if (statement == null)
            statement = DbConnector.getStatment();
    }

    public boolean update(String nomSociete, String email, String tel, String adresse, String description) {
        try {
            statement.execute("UPDATE contact_infos " +
                    "SET nom_societe='" + nomSociete + "'," +
                    "email='" + email + "'," +
                    "tel='" + tel + "'," +
                    "adresse='" + adresse + "'," +
                    "description='" + description + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNomSociete() {
        ResultSet result;
        try {
            result = statement.executeQuery("select nom_societe from contact_infos;");
            if (result.next()) {
                return result.getString("nom_societe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Introuvable";
    }

    public String getEmailSociete() {
        ResultSet result;
        try {
            result = statement.executeQuery("select email from contact_infos;");
            if (result.next()) {
                return result.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Introuvable";
    }

    public String getTelSociete() {
        ResultSet result;
        try {
            result = statement.executeQuery("select tel from contact_infos;");
            if (result.next()) {
                return result.getString("tel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Introuvable";
    }

    public String getAdresseSociete() {
        ResultSet result;
        try {
            result = statement.executeQuery("select adresse from contact_infos;");
            if (result.next()) {
                return result.getString("adresse");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Introuvable";
    }

    public String getDescriptionSociete() {
        ResultSet result;
        try {
            result = statement.executeQuery("select description from contact_infos;");
            if (result.next()) {
                return result.getString("description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Introuvable";
    }


}
