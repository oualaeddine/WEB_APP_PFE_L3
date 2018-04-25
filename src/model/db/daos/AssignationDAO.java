package model.db.daos;

import model.beans.Localite;
import model.beans.humans.Employe;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AssignationDAO extends DAO{
    public boolean add(int agent, int region) {
        try {
            statement.execute("INSERT INTO assignation_region(agentId, localiteId) VALUES (" +agent+", "+
            region+
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Employe> getAgentsByLocalite(int id) {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            LinkedList<Integer> ids = new LinkedList<>();
            result = statement.executeQuery("SELECT employe.id FROM employe,assignation_region WHERE localiteId=" + id + " AND agentId=employe.id;");
            while (result.next()) {
                ids.add(result.getInt("id"));
            }
            for (int i : ids) {
                list.add((Employe) new EmployeDAO().getById(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Localite getLocaliteByAgent(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT localite.* FROM assignation_region,localite WHERE assignation_region.agentId="+id+" AND assignation_region.localiteId=localite.id;");
            if (result.next()) {
                Localite localite = new Localite();
                localite.setId(result.getInt("id"));
                localite.setNom(result.getString("nom"));
                return localite;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Employe getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            statement.execute("DELETE FROM assignation_region WHERE agentId=" + id + ";");
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
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        return null;
    }

    public int isAffected(int agent) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT agentId FROM assignation_region WHERE agentId=" + agent + ";");
            if (result.next()) {
                return result.getInt("agentId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
