package model.db.daos;

import model.beans.humans.Operateur;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OperateurDAO extends DAO implements DAOInterface{
    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM operateur WHERE id="+id);
            if (result.next()){
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
                operateur.setAddedBy(result.getInt("addedBy"));
                operateur.setSuspended(result.getBoolean("isSuspended"));

                return operateur;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
                    "'"+operateur.getNom()+"',"+
                    "'"+operateur.getPrenom()+"',"+
                    operateur.getDateNaissance()+","+
                    "'"+operateur.getAdresse()+"',"+
                    "'"+operateur.getTel()+"',"+
                    "'"+operateur.getEmail()+"',"+
                    "'"+operateur.getUsername()+"',"+
                    "'"+operateur.getPassword()+"',"+
                    operateur.getDateAdded()+","+
                    operateur.getAddedBy()+","+
                    operateur.isSuspended()+","+
                    ");");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean exists(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        return null;
    }

}
