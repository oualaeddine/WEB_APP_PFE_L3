package model.db.daos;

import model.beans.humans.*;
import model.db.DAO;
import model.enums.UserType;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class EmployeDAO extends DAO {

    public boolean changePassword(int id, String pass) {
        try {
            statement.execute("UPDATE employe SET password='" + pass + "' WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Employe getByUsername(String username) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM employe WHERE username='" + username + "'");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employe getByEmail(String email) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM employe WHERE email = '"+email+"';");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM employe WHERE id=" + id);
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            statement.execute("DELETE FROM employe WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Employe employe = new Employe();
        try {
            statement.execute("INSERT INTO employe (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended`,`userType` ) VALUES(" +
                    "'" + employe.getNom() + "'," +
                    "'" + employe.getPrenom() + "'," +
                    employe.getDateNaissance() + "," +
                    "'" + employe.getAdresse() + "'," +
                    "'" + employe.getTel() + "'," +
                    "'" + employe.getEmail() + "'," +
                    "'" + employe.getUsername() + "'," +
                    "'" + employe.getPassword() + "'," +
                    employe.getDateAdded() + "," +
                    employe.getCreator().getId() + "," +
                    employe.isSuspended() + "," +
                    "'"+Util.getStringFromType(employe.getUserType())+"'"+
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    public LinkedList<Employe> getAll() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM employe;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean authenticate(String username, String password) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE username='" + username + "' AND password='" + password + "';");
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reintegrerById(int id) {
        return super.reintegrerById(id,"employe");
    }

    public boolean suspendById(int userId) {
        return super.suspendById(userId, "employe");
    }

    public LinkedList<Employe> getSuspendedResVente() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='responsable_vente' AND isSuspended=1;");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllResVentes() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='responsable_vente';");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedOperateurs() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='operateur' AND isSuspended=1;");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public LinkedList<Employe> getAllOperateurs() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='operateur';");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAgents() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='agent' AND isSuspended=1;");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAgents() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM employe WHERE userType='agent';");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='admin' OR userType='SU';");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM employe WHERE userType='admin' AND isSuspended=1;");
            while (result.next()) {
                list.add((Employe) getById(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
