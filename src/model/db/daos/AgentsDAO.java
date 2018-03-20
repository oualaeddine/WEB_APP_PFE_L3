package model.db.daos;

import model.beans.Localite;
import model.beans.humans.Admin;
import model.beans.humans.Agent;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AgentsDAO extends DAO {

    public static final String TABLE_NAME = "agent";

    public AgentsDAO() {
    }

    @Override
    public Agent getByEmail(String email) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE email='" + email + "';");
            if (result.next()) {
                Agent agent = new Agent();
                agent.setId(result.getInt("id"));
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setDateAdded(result.getDate("dateAdded"));
                agent.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("idRegion")));
                agent.setSuspended(result.getBoolean("isSuspended"));

                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changePassword(int id, String pwd){
        try {
            statement.execute("UPDATE agent SET password='"+pwd+"' WHERE id = "+id+";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean reintegrerById(int id){return super.reintegrerById(id,TABLE_NAME);}

    public boolean suspendById(int id){
        return  super.suspendById(id,"agent");
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id);
            if (result.next()) {
                Agent agent = new Agent();
                agent.setId(result.getInt("id"));
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setDateAdded(result.getDate("dateAdded"));
                agent.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("idRegion")));
                agent.setSuspended(result.getBoolean("isSuspended"));

                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Agent> getAgentByLocation(Localite localite) {
        ResultSet result;
        LinkedList<Agent> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM agent WHERE idregion=" + localite.getId() + ";");
            while (result.next()) {
                Agent agent = new Agent();
                agent =(Agent) getById(result.getInt("id"));
                list.add(agent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Agent getByUsername(String username) {
        ResultSet result;
        try {
            Agent admin = new Agent();
            result = statement.executeQuery("SELECT * FROM agent WHERE username='" + username + "';");
            if (result.next()) {
                admin.setNom(result.getString("nom"));
                admin.setPrenom(result.getString("prenom"));
                admin.setDateNaissance(result.getDate("dateNaiss"));
                admin.setAdresse(result.getString("adresse"));
                admin.setTel(result.getString("tel"));
                admin.setEmail(result.getString("email"));
                admin.setDateAdded(result.getDate("dateAdded"));
                admin.setSuspended(result.getBoolean("isSuspended"));
                admin.setId(result.getInt("id"));
                admin.setLocalite((Localite)new LocaliteDAO().getById(result.getInt("idregion")));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Agent getByUsername(Agent admin){
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM agent WHERE username='" + admin.getUsername() + "';");
            if (result.next()) {
                admin.setNom(result.getString("nom"));
                admin.setPrenom(result.getString("prenom"));
                admin.setDateNaissance(result.getDate("dateNaiss"));
                admin.setAdresse(result.getString("adresse"));
                admin.setTel(result.getString("tel"));
                admin.setEmail(result.getString("email"));
                admin.setDateAdded(result.getDate("dateAdded"));
                admin.setSuspended(result.getBoolean("isSuspended"));
                admin.setId(result.getInt("id"));
                admin.setLocalite((Localite)new LocaliteDAO().getById(result.getInt("idregion")));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteById(int agentId) {
        return super.deleteById(agentId, "agent");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Agent myAdmin = (Agent) object;
        try {
            statement.execute("INSERT INTO agent (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended`,`idregion`)"
                    + " VALUES (" +
                    "'" + myAdmin.getNom() + "'," +
                    "'" + myAdmin.getPrenom() + "'," +
                    "'" + myAdmin.getDateNaissance() + "'," +
                    "'" + myAdmin.getAdresse() + "'," +
                    "'" + myAdmin.getTel() + "'," +
                    "'" + myAdmin.getEmail() + "'," +
                    "'" + myAdmin.getUsername() + "'," +
                    "'" + myAdmin.getPassword() + "'," +
                    "0," +
                    "0" + //TODO: id region ?
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
        Agent agent = (Agent) object;
        try {
            statement.execute("DELETE FROM agent WHERE username = '" + agent.getUsername() + "' AND password = '" + agent.getPassword() + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Agent> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM agent;");
            while (result.next()) {
                Agent agent = new Agent();
                agent.setId(result.getInt("id"));
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setCreator((Admin) new AdminsDAO().getById(result.getInt("addedBy")));
                agent.setDateAdded(result.getDate("dateAdded"));
                agent.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("idRegion")));
                agent.setSuspended(result.getBoolean("isSuspended"));

                list.add(agent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changePassword(String mdp, int id) {
        try {
            statement.execute("UPDATE agent SET password='"+mdp+"' WHERE id="+id+";");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Agent> getSuspendedAgents() {
        LinkedList<Agent> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM agent WHERE isSuspended=1;");
            while (result.next()) {
                Agent agent = new Agent();
                agent.setId(result.getInt("id"));
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setDateAdded(result.getDate("dateAdded"));
                agent.setLocalite((Localite) new AgentsDAO().getById(result.getInt("idRegion")));
                agent.setSuspended(result.getBoolean("isSuspended"));
                list.add(agent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
