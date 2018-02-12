package model.db.daos;

import model.beans.humans.Agent;
import model.db.DAO;

public class AgentsDAO extends DAO {

    public AgentsDAO() {
    }

    public boolean deleteById(int agentId) {
        return super.deleteById(agentId, "agent");
    }

    public boolean addAgent(Agent agent) {
        if (statement != null)
            return false;
        else
            return false;
    }

    public boolean deleteAgent(Agent agent) {
        if (statement != null)
            return false;
        else
            return false;
    }

    public Agent getAgentById(int agentId) {
        if (statement != null)
            return null;
        else
            return null;
    }

    public boolean updateAgen(Agent agent) {
        if (statement != null)
            return false;
        else
            return false;
    }

    public boolean changeAgentPassword(Agent agent, String newPassword) {
        if (statement != null)
            return false;
        else
            return false;
    }
}
