package model.db.daos;

import model.beans.humans.Employe;
import model.db.DAO;

import java.sql.SQLException;
import java.util.LinkedList;

public class AssignationDAO extends DAO{
    public boolean add(int agent, int region) {
        try {
            statement.execute("INSERT INTO assignation_region(agentId, localiteId) VALUES (" +agent+", "+
            region+
                    ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
}
