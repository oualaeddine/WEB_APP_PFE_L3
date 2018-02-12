package model.db.daos;

import model.beans.humans.Admin;
import model.db.DAO;
import model.db.DAOInterface;
import model.enums.AdminRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AdminsDAO extends DAO implements DAOInterface{
    public AdminsDAO() {

    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try{
            result = statement.executeQuery("SELECT * FROM admin WHERE id = "+id);
            if (result.next()){
                Admin admin = new Admin();
                admin.setId(result.getInt("id"));
                admin.setNom(result.getString("nom"));
                admin.setPrenom(result.getString("prenom"));
                admin.setDateNaissance(result.getDate("dateNaiss"));
                admin.setAdresse(result.getString("adresse"));
                admin.setTel(result.getString("tel"));
                admin.setEmail(result.getString("email"));
                admin.setUsername(result.getString("username"));
                admin.setPassword(result.getString("password"));
                if(result.getString("role").equals("SU"))
                    admin.setRole(AdminRole.SUPER_USER);
                else admin.setRole(AdminRole.ADMIN);
                admin.setDateAdded(result.getDate("dateAdded"));
                //TODO: addedBy + idRegion
                admin.setSuspended(result.getBoolean("isSuspended"));

                return admin;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "admin");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Admin myAdmin = (Admin) object;
        try {
            statement.execute("INSERT INTO admin (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`role`,`dateAdded`,`addedBy`,`isSuspended`)"
                    + " VALUES (" +
                    "'" + myAdmin.getNom() + "'," +
                    "'" + myAdmin.getPrenom() + "'," +
                    "'" + myAdmin.getDateNaissance() + "',"+
                    "'" + myAdmin.getAdresse() +"',"+
                    "'" + myAdmin.getTel() +"',"+
                    "'" + myAdmin.getEmail() +"',"+
                    "'" + myAdmin.getUsername() +"',"+
                    "'" + myAdmin.getPassword() +"',"+
                    "'" + myAdmin.getRole()+"',"+
                    "CURRENT_DATE(),"+
                    "0,"+ //TODO: mnine njibou addedBy ?
                    "0"+
                    ");"
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Admin myAdmin = (Admin) object;
        if (exists(myAdmin)) {
            try {
                statement.execute("DELETE FROM admin WHERE username = " +
                        "'" + myAdmin.getUsername() + "'"
                        + " AND password = " +
                        "'" + myAdmin.getPassword() + "'"
                );
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean exists(Object object){
        Admin myAdmin = (Admin) object;
        ResultSet result;
        try{
            result = statement.executeQuery("SELECT * FROM admin");
            while (result.next()){
                String username = result.getString("username");
                String password = result.getString("password");
                if (myAdmin.getUsername().equals(username) && myAdmin.getPassword().equals(password))
                    return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList<Admin> getAll() {
        ResultSet result;
        LinkedList<Admin> list=new LinkedList<>();
        try{
            result = statement.executeQuery("SELECT * FROM admin;");
            while (result.next()){
                Admin admin = new Admin();
                admin.setId(result.getInt("id"));
                admin.setNom(result.getString("nom"));
                admin.setPrenom(result.getString("prenom"));
                admin.setDateNaissance(result.getDate("dateNaiss"));
                admin.setAdresse(result.getString("adresse"));
                admin.setTel(result.getString("tel"));
                admin.setEmail(result.getString("email"));
                admin.setUsername(result.getString("username"));
                admin.setPassword(result.getString("password"));
                if(result.getString("role").equals("SU"))
                    admin.setRole(AdminRole.SUPER_USER);
                else admin.setRole(AdminRole.ADMIN);
                admin.setDateAdded(result.getDate("dateAdded"));
                //TODO: addedBy
                admin.setSuspended(result.getBoolean("isSuspended"));

                list.add(admin);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
