package model.db.daos;

import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClientDAO extends DAO {
    public static final String TABLE_NAME = "client";
    public static final String[] COLUMN_NAMES = {

    };
    public boolean changePassword(int id, String pwd){
        try {
            statement.execute("UPDATE client SET password='"+pwd+"' WHERE id = "+id+";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Client getByUsername(Client client){
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM client WHERE username='" + client.getUsername() + "';");
            if (result.next()) {
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenom"));
                client.setDateNaissance(result.getDate("dateNaiss"));
                client.setAdresse(result.getString("adresse"));
                client.setTel(result.getString("tel"));
                client.setEmail(result.getString("email"));
                client.setBanned(result.getInt("isBanned")==1);
                client.setId(result.getInt("id"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updatePassword(String pwd,int id){
        try {
            statement.execute("UPDATE client SET password= '"+pwd+"' WHERE id="+id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employe getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM client WHERE id=" + id);
            if (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenom"));
                client.setDateNaissance(result.getDate("dateNaiss"));
                client.setAdresse(result.getString("adresse"));
                client.setTel(result.getString("tel"));
                client.setEmail(result.getString("email"));
                client.setUsername(result.getString("username"));
                client.setPassword(result.getString("password"));
                client.setBanned(result.getInt("isBanned")==1);

                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean banById(int id){
        try {
            statement.execute("UPDATE client SET isBanned = 1" +
                    " WHERE id="+id+" ;");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean retablirById(int id) {
        try {
            statement.execute("UPDATE client SET isBanned = 0" +
                    " WHERE id="+id+" ;");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteById(int id) {
        return super.deleteById(id, "client");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Client client = (Client) object;
        try {
            statement.execute("INSERT INTO client(`nom`, `prenom`, `dateNaiss`, `adresse`, `tel`, `email`, `username`, `password`, `dateAdded`, `isBanned`) VALUES (" +
                    "'" + client.getNom() + "'," +
                    "'" + client.getPrenom() + "'," +
                    "'"+client.getDateNaissance()+"'" + "," +
                    "'" + client.getAdresse() + "'," +
                    "'" + client.getTel() + "'," +
                    "'" + client.getEmail() + "'," +
                    "'" + client.getUsername() + "'," +
                    "'" + client.getPassword() + "'," +
                    "CURRENT_DATE" + "," +
                    0 +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Client client = (Client) object;
        try {
            String username = client.getUsername();
            String password = client.getPassword();
            statement.executeQuery("DELETE FROM client WHERE username='" + username + "' AND password='" + password + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public LinkedList getAll() {
        LinkedList<Client> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM client");
            while (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenom"));
                client.setDateNaissance(result.getDate("dateNaiss"));
                client.setAdresse(result.getString("adresse"));
                client.setTel(result.getString("tel"));
                client.setEmail(result.getString("email"));
                client.setUsername(result.getString("username"));
                client.setPassword(result.getString("password"));
                client.setBanned(result.getInt("isBanned")==1);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Client> getBannedClients() {
        ResultSet result;
        LinkedList<Client> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM client WHERE isBanned=1;");
            while (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenom"));
                client.setDateNaissance(result.getDate("dateNaiss"));
                client.setAdresse(result.getString("adresse"));
                client.setTel(result.getString("tel"));
                client.setEmail(result.getString("email"));
                client.setUsername(result.getString("username"));
                client.setPassword(result.getString("password"));
                client.setBanned(result.getInt("isBanned")==1);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean isBanned(Client client){
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT isBanned FROM client WHERE username='" + client.getUsername() + "';");
            if (result.next()) {
                return (result.getBoolean("isBanned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
