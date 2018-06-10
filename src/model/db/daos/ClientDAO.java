package model.db.daos;

import model.beans.Visite;
import model.beans.humans.Client;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class ClientDAO extends DAO {
    public static final String TABLE_NAME = "client";
    public static final String[] COLUMN_NAMES = {

    };

    public boolean authenticateClient(String username, String password) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id,isBanned FROM client WHERE ((username='" + username + "' AND password='" + password + "') OR (email='" + username + "' AND password='" + password + "')) AND isBanned=0 ;");
            return (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean changePassword(int id, String pwd){
        try {
            clientStatement.execute("UPDATE client SET password='"+pwd+"' WHERE id = "+id+";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client getByUsername(String username){
        ResultSet result;
        try {
            result = clientStatement.executeQuery("SELECT * FROM client WHERE username='" + username + "';");
            if (result.next()) {
                Client client = new Client();
                client.setUsername(username);
                client.setPassword(result.getString("password"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenom"));
                client.setDateNaissance(result.getDate("dateNaiss"));
                client.setAdresse(result.getString("adresse"));
                client.setTel(result.getString("tel"));
                client.setEmail(result.getString("email"));
                client.setBanned(result.getInt("isBanned")==1);
                client.setId(result.getInt("id"));
                client.setDateAdded(result.getDate("dateAdded"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String pwd,int id){
        try {
            clientStatement.execute("UPDATE client SET password= '"+pwd+"' WHERE id="+id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client getByEmail(String email) {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("SELECT * FROM client WHERE email='" + email + "';");
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
                client.setDateAdded(result.getDate("dateAdded"));
                return client;
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
            result = clientStatement.executeQuery("SELECT * FROM client WHERE id=" + id +";");
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
                client.setDateAdded(result.getDate("dateAdded"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean banById(int id){
        try {
            clientStatement.execute("UPDATE client SET isBanned = 1" +
                    " WHERE id="+id+" ;");
            LinkedList<Visite> visites = new VisitesDao().getProgrammeesForClient(id);
            for (Visite visite : visites) {
                new VisitesDao().annulerVisite(visite);
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean retablirById(int id) {
        try {
            clientStatement.execute("UPDATE client SET isBanned = 0" +
                    " WHERE id="+id+" ;");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return super.deleteById(id, "personal");
    }

    @Override
    public boolean update(Object object) {
        Client employe = (Client) object;
        try {
            clientStatement.execute("UPDATE client SET " +
                    "nom = '" + employe.getNom() + "'," +
                    "prenom = '" + employe.getPrenom() + "'," +
                    "tel = '" + employe.getTel() + "'," +
                    "dateNaiss = '" + employe.getDateNaissance() + "'," +
                    "adresse='" + employe.getAdresse() + "'," +
                    "email='" + employe.getEmail() + "'" +
                    " WHERE employe.id=" + employe.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(Object object) {
        Client client = (Client) object;
        try {
            clientStatement.execute("INSERT INTO client(`nom`, `prenom`, `dateNaiss`, `adresse`, `tel`, `email`, `username`, `password`, `dateAdded`, `isBanned`) VALUES (" +
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
        int id = (int) object;
        try {
            clientStatement.execute("DELETE FROM client WHERE id=" + id + ";");
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
            result = clientStatement.executeQuery("SELECT * FROM client");
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
                client.setDateAdded(result.getDate("dateAdded"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("SELECT count(id) FROM client;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<Client> getBannedClients() {
        ResultSet result;
        LinkedList<Client> list = new LinkedList<>();
        try {
            result = clientStatement.executeQuery("SELECT * FROM client WHERE isBanned=1;");
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
                client.setDateAdded(result.getDate("dateAdded"));
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
            result = clientStatement.executeQuery("SELECT isBanned FROM client WHERE username='" + client.getUsername() + "';");
            if (result.next()) {
                return (result.getBoolean("isBanned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Client> getNotBannedClients() {
        ResultSet result;
        LinkedList<Client> list = new LinkedList<>();
        try {
            result = clientStatement.executeQuery("SELECT * FROM client WHERE isBanned=0;");
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
                client.setDateAdded(result.getDate("dateAdded"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Client> getClientsForAgent(int agentId) {
        ResultSet result;
        LinkedList<Client> list = new LinkedList<>();
        try {
            result = clientStatement.executeQuery("SELECT distinct client.* FROM visite,employe,client WHERE employe.id=" + agentId + " AND visite.agentId=employe.id AND client.id=visite.clientId;");
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
                client.setDateAdded(result.getDate("dateAdded"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //////////////////////////////////////////////////////////////////////////////

    /**
     * * Stats
     */
    public int getNewClientsByMonth(int month) {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select count(id) from client where MONTH(dateAdded)='" + month + "' and YEAR(dateAdded)=YEAR(current_date)");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getBestClient() {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select acheteur from (select distinct c.id as acheteur,(select count(vente.id) from vente where c.id=vente.clientId) as nbrVentes from client c,vente v where c.id=v.clientId) myTable having  max(myTable.nbrVentes);");
            if (result.next()) {
                return result.getInt("acheteur");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float percentageClientsForAgent(int userId) {
        int nbrClientForAgent = getClientsForAgent(userId).size();
        int nbrClients = getAll().size();

        return nbrClientForAgent * 100 / nbrClients;
    }

    public int visitedLogementsNbrForClient(int client) {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select count(visite.id) as nbr from visite where visite.clientId=" + client + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<String> getNotifTokens(int userId) {
        LinkedList<String> strings = new LinkedList<>();
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select * from user_push_tokens where userId=" + userId + ";");
            while (result.next()) {
                strings.add(result.getString("token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public boolean tokenExists(String token) {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select * from user_push_tokens where token='" + token + "';");
            if (result.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToken(int userId, String token) {
        try {
            clientStatement.execute("insert into user_push_tokens (userId, token) values (" +
                    userId + "," +
                    "'" + token + "'" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateToken(int userId, String token) {
        try {
            clientStatement.execute("update user_push_tokens set token = '" + token + "' where userId=" + userId + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client getByPhone(String callerNumber) {
        ResultSet result;
        try {
            result = clientStatement.executeQuery("select * from client where tel='" + callerNumber + "' and isBanned=0;");
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
                client.setBanned(result.getInt("isBanned") == 1);
                client.setDateAdded(result.getDate("dateAdded"));

                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
