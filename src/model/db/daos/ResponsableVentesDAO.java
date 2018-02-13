package model.db.daos;

import model.beans.humans.ResponsableVente;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ResponsableVentesDAO extends DAO implements DAOInterface{
    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM responsable_vente WHERE id="+id);
            if (result.next()){
                ResponsableVente responsableVente = new ResponsableVente();
                responsableVente.setId(result.getInt("id"));
                responsableVente.setNom(result.getString("nom"));
                responsableVente.setPrenom(result.getString("prenom"));
                responsableVente.setDateNaissance(result.getDate("dateNaiss"));
                responsableVente.setAdresse(result.getString("adresse"));
                responsableVente.setTel(result.getString("tel"));
                responsableVente.setEmail(result.getString("email"));
                responsableVente.setUsername(result.getString("username"));
                responsableVente.setPassword(result.getString("password"));
                responsableVente.setDateAdded(result.getDate("dateAdded"));
                responsableVente.setSuspended(result.getBoolean("isSuspended"));
                return responsableVente;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "responsable_vente");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        ResponsableVente responsableVente = (ResponsableVente) object;
        try {
            statement.execute("INSERT INTO responsable_vente(nom, prenom, dateNaiss, adresse, tel, emaim, username, password, dateAdded, addedBy, isSuspended) VALUES (" +
                    "'"+responsableVente.getNom()+"',"+
                    "'"+responsableVente.getPrenom()+"',"+
                    responsableVente.getDateNaissance()+","+
                    "'"+responsableVente.getAdresse()+"',"+
                    "'"+responsableVente.getTel()+"',"+
                    "'"+responsableVente.getEmail()+"',"+
                    "'"+responsableVente.getUsername()+"',"+
                    "'"+responsableVente.getPassword()+"',"+
                    responsableVente.getDateAdded()+","+
                    0+","+
                    0+
                    ");");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        ResponsableVente responsableVente=(ResponsableVente) object;
        try {
            statement.execute("DELETE FROM responsable_vente WHERE " +
                    "username='"+responsableVente.getUsername()+"' AND " +
                    "password'"+responsableVente.getPassword()+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exists(Object object) {
        ResponsableVente responsableVente = (ResponsableVente) object;
        ResultSet result;
        try {
            result= statement.executeQuery("SELECT * FROM responsable_vente");
            while (result.next()){
                String username = result.getString("username");
                String password = result.getString("password");
                if (responsableVente.getUsername().equals(username) && responsableVente.getPassword().equals(password)) return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<ResponsableVente> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM responsable_vente");
            while (result.next()){
                ResponsableVente responsableVente = new ResponsableVente();
                responsableVente.setId(result.getInt("id"));
                responsableVente.setNom(result.getString("nom"));
                responsableVente.setPrenom(result.getString("prenom"));
                responsableVente.setDateNaissance(result.getDate("dateNaiss"));
                responsableVente.setAdresse(result.getString("adresse"));
                responsableVente.setTel(result.getString("tel"));
                responsableVente.setEmail(result.getString("email"));
                responsableVente.setUsername(result.getString("username"));
                responsableVente.setPassword(result.getString("password"));
                responsableVente.setDateAdded(result.getDate("dateAdded"));
                responsableVente.setSuspended(result.getBoolean("isSuspended"));

                list.add(responsableVente);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
