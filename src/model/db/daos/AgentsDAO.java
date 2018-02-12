package model.db.daos;

import model.beans.humans.Agent;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AgentsDAO extends DAO implements DAOInterface{

    public AgentsDAO() {
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM agent WHERE id="+id);
            if (result.next()){
                Agent agent = new Agent();
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setDateAdded(result.getDate("dateAdded"));
                //TODO:addedBy + idRegion
                agent.setSuspended(result.getBoolean("isSuspended"));
            }
        }catch (SQLException e){
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
        Agent agent = (Agent) object;
        try {
            statement.execute("INSERT INTO admin (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended`,`idregion`)"
                    + " VALUES (" +
                    "'" + agent.getNom() + "'," +
                    "'" + agent.getPrenom() + "'," +
                    "'" + agent.getDateNaissance() + "',"+
                    "'" + agent.getAdresse() +"',"+
                    "'" + agent.getTel() +"',"+
                    "'" + agent.getEmail() +"',"+
                    "'" + agent.getUsername() +"',"+
                    "'" + agent.getPassword() +"',"+
                    "'" + agent.getDateAdded() +"',"+
                    "'" + agent.getAddedBy() +"',"+
                    "0,"+
                    "0"+       /*TODO: id region ?*/
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
        try{
            statement.execute("DELETE FROM agent WHERE username = '"+agent.getUsername()+"' AND password = '"+agent.getPassword()+"'");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exists(Object object) {
        Agent agent = (Agent) object;
        ResultSet result;
        try{
            result = statement.executeQuery("SELECT * FROM agent");
            while (result.next()){
                String username = result.getString("username");
                String password = result.getString("password");
                if (agent.getUsername().equals(username) && agent.getPassword().equals(password)) {
                    return true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Agent> list=new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM agent");
            while (result.next()){
                Agent agent = new Agent();
                agent.setNom(result.getString("nom"));
                agent.setPrenom(result.getString("prenom"));
                agent.setDateNaissance(result.getDate("dateNaiss"));
                agent.setAdresse(result.getString("adresse"));
                agent.setTel(result.getString("tel"));
                agent.setEmail(result.getString("email"));
                agent.setUsername(result.getString("username"));
                agent.setPassword(result.getString("password"));
                agent.setDateAdded(result.getDate("dateAdded"));
                //TODO:addedBy + idRegion
                agent.setSuspended(result.getBoolean("isSuspended"));
                list.add(agent);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    public boolean changeAgentPassword(Agent agent, String newPassword) {
        if (statement != null)
            return false;
        else
            return false;
    }
}
