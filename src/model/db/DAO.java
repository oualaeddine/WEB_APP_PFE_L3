package model.db;

import model.beans.humans.Person;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public abstract class DAO {
    protected Statement
            rapportStatement, statement, clientStatement, employeStatement, localiteStatement, logementStatement, venteStatement, versementStatement, visiteStatement;

    public DAO() {
        DbConnector.createConnexion();

        if (clientStatement == null)
            clientStatement = DbConnector.getStatment();
        else {
            clientStatement = DbConnector.getStatment();
        }

        if (employeStatement == null)
            employeStatement = DbConnector.getStatment();
        if (localiteStatement == null)
            localiteStatement = DbConnector.getStatment();
        if (logementStatement == null)
            logementStatement = DbConnector.getStatment();
        if (venteStatement == null)
            venteStatement = DbConnector.getStatment();
        if (versementStatement == null)
            versementStatement = DbConnector.getStatment();
        if (visiteStatement == null)
            visiteStatement = DbConnector.getStatment();
        if (rapportStatement == null)
            rapportStatement = DbConnector.getStatment();
        if (statement == null)
            statement = DbConnector.getStatment();

    }

    public abstract Person getByEmail(String email);

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
