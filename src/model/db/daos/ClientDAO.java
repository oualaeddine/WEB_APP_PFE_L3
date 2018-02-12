package model.db.daos;

import model.db.DAO;
<<<<<<< HEAD
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClientDAO extends DAO implements DAOInterface{
    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM client WHERE id="+id);
            if (result.next()){
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
                client.setDateAdded(result.getDate("dateAdded"));
                //TODO: addedBy
                client.setSuspended(result.getBoolean("isBanned"));

                return client;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
=======
>>>>>>> a5cea49c980f8354a3fbd4c70ac38ecb8c9f3f5f

public class ClientDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "client");
        
    }
}
