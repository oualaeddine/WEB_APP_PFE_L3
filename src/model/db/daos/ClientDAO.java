package model.db.daos;
import model.beans.humans.Client;
import model.db.DAO;
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
            statement.execute("INSERT INTO client(nom, prenom, dateNaiss, adresse, tel, email, username, password, dateAdded, addedBy, isBanned) VALUES (" +
                    "'"+client.getNom()+"',"+
                    "'"+client.getPrenom()+"',"+
                    client.getDateNaissance()+","+
                    "'"+client.getAdresse()+"',"+
                    "'"+client.getTel()+"',"+
                    "'"+client.getEmail()+"',"+
                    "'"+client.getUsername()+"',"+
                    "'"+client.getPassword()+"',"+
                    client.getDateAdded()+","+
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
        Client client = (Client) object;
        try {
            String username=client.getUsername();
            String password = client.getPassword();
            statement.executeQuery("DELETE FROM client WHERE username='"+username+"' AND password='"+password+"';");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exists(Object object) {
        Client client = (Client) object;
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM client");
            while (result.next()){
                String username=result.getString("username");
                String password=result.getString("password");
                if (client.getUsername().equals(username) && client.getPassword().equals(password)) return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Client> list = new LinkedList<>();
        ResultSet result;
        try {
            result=statement.executeQuery("SELECT * FROM client");
            while (result.next()){
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

                list.add(client);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
