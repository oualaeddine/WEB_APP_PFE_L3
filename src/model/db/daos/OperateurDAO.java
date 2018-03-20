package model.db.daos;

import model.beans.humans.Admin;
import model.beans.humans.Operateur;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OperateurDAO extends DAO {
    public static final String TABLE_NAME = "operateur";
    public boolean reintegrerById(int id){return super.reintegrerById(id,TABLE_NAME);}

    @Override
    public Operateur getByEmail(String email) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE email='" + email + "';");
            if (result.next()) {
                Operateur operateur = new Operateur();
                operateur.setId(result.getInt("id"));
                operateur.setNom(result.getString("nom"));
                operateur.setPrenom(result.getString("prenom"));
                operateur.setDateNaissance(result.getDate("dateNaiss"));
                operateur.setAdresse(result.getString("adresse"));
                operateur.setEmail(result.getString("email"));
                operateur.setUsername(result.getString("username"));
                operateur.setPassword(result.getString("password"));
                operateur.setDateAdded(result.getDate("dateAdded"));
//                operateur.setAddedBy(result.getInt("addedBy")); TODO:hedi
                operateur.setSuspended(result.getBoolean("isSuspended"));

                return operateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean changePassword(int id, String pwd){
        try {
            statement.execute("UPDATE operateur SET password='"+pwd+"' WHERE id = "+id+";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Operateur getByUsername(Operateur operateur){
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM operateur WHERE username='" + operateur.getUsername() + "';");
            if (result.next()) {
                operateur.setNom(result.getString("nom"));
                operateur.setPrenom(result.getString("prenom"));
                operateur.setDateNaissance(result.getDate("dateNaiss"));
                operateur.setAdresse(result.getString("adresse"));
                operateur.setTel(result.getString("tel"));
                operateur.setEmail(result.getString("email"));
                operateur.setDateAdded(result.getDate("dateAdded"));
                operateur.setSuspended(result.getBoolean("isSuspended"));
                operateur.setId(result.getInt("id"));
                return operateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean changePassword(String pwd,int id){
        try {
            statement.execute("UPDATE operateur SET password='"+pwd+"' WHERE id="+id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM operateur WHERE id=" + id);
            if (result.next()) {
                Operateur operateur = new Operateur();
                operateur.setId(result.getInt("id"));
                operateur.setNom(result.getString("nom"));
                operateur.setPrenom(result.getString("prenom"));
                operateur.setDateNaissance(result.getDate("dateNaiss"));
                operateur.setAdresse(result.getString("adresse"));
                operateur.setEmail(result.getString("email"));
                operateur.setUsername(result.getString("username"));
                operateur.setPassword(result.getString("password"));
                operateur.setDateAdded(result.getDate("dateAdded"));
//                operateur.setAddedBy(result.getInt("addedBy")); TODO:hedi
                operateur.setSuspended(result.getBoolean("isSuspended"));

                return operateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean suspendById(int id){
        return  super.suspendById(id,"operateur");
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "operateur");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Operateur operateur = new Operateur();
        try {
            statement.execute("INSERT INTO admin (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended` VALUES(" +
                    "'" + operateur.getNom() + "'," +
                    "'" + operateur.getPrenom() + "'," +
                    operateur.getDateNaissance() + "," +
                    "'" + operateur.getAdresse() + "'," +
                    "'" + operateur.getTel() + "'," +
                    "'" + operateur.getEmail() + "'," +
                    "'" + operateur.getUsername() + "'," +
                    "'" + operateur.getPassword() + "'," +
                    operateur.getDateAdded() + "," +
                    0 + "," + //TODO:addedBy
                    operateur.isSuspended() + "," +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Operateur operateur = (Operateur) object;
        try {
            statement.execute("DELETE FROM operateur WHERE username='" + operateur.getUsername() + "' AND password='" + operateur.getPassword() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Operateur> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM operateur");
            while (result.next()) {
                Operateur operateur = new Operateur();
                operateur.setId(result.getInt("id"));
                operateur.setNom(result.getString("nom"));
                operateur.setPrenom(result.getString("prenom"));
                operateur.setDateNaissance(result.getDate("dateNaiss"));
                operateur.setTel(result.getString("tel"));
                operateur.setAdresse(result.getString("adresse"));
                operateur.setEmail(result.getString("email"));
                operateur.setUsername(result.getString("username"));
                operateur.setPassword(result.getString("password"));
                operateur.setDateAdded(result.getDate("dateAdded"));
                operateur.setCreator((Admin) new AdminsDAO().getById(result.getInt("addedBy")));
                operateur.setSuspended(result.getBoolean("isSuspended"));

                list.add(operateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Operateur> getSuspendedOperateurs() {
        LinkedList<Operateur> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM operateur WHERE isSuspended=1;");
            while (result.next()) {
                Operateur operateur = new Operateur();
                operateur.setId(result.getInt("id"));
                operateur.setNom(result.getString("nom"));
                operateur.setPrenom(result.getString("prenom"));
                operateur.setDateNaissance(result.getDate("dateNaiss"));
                operateur.setAdresse(result.getString("adresse"));
                operateur.setTel(result.getString("tel"));
                operateur.setEmail(result.getString("email"));
                operateur.setUsername(result.getString("username"));
                operateur.setPassword(result.getString("password"));
                operateur.setDateAdded(result.getDate("dateAdded"));
                operateur.setSuspended(result.getBoolean("isSuspended"));
                list.add(operateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
