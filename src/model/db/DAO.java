package model.db;

import model.beans.humans.Employe;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public abstract class DAO {
    protected Statement statement;

    public DAO() {
        this.statement = DbConnector.getStatment();
    }

    public abstract Employe getByEmail(String email);
    protected boolean reintegrerById(int id, String table) {
        try {
            statement.execute("UPDATE " + table + " SET isSuspended = 0 " +
                    "WHERE id=" + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean suspendById(int id, String table) {
        try {
            statement.execute("UPDATE " + table + " SET isSuspended = 1" +
                    " WHERE id=" + id + " ;");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean deleteById(int id, String table) {
        try {
            statement.execute("DELETE FROM " + table + " WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public abstract Object getById(int id);

    public abstract boolean deleteById(int id);

    public abstract boolean update(Object object);

    public abstract boolean add(Object object);

    public abstract boolean delete(Object object);

    public abstract LinkedList getAll();
}
