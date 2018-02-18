package model.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DAO implements DAOInterface{
    protected Statement statement;

    public DAO() {
        this.statement = DbConnector.getStatment();
    }

    protected boolean reintegrerById(int id, String table){
        try {
            statement.execute("UPDATE "+table+" SET isSuspended = 0 " +
                    "WHERE id="+id+";");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    protected boolean suspendById(int id,String table) {
        try {
            statement.execute("UPDATE "+table+" SET isSuspended = 1" +
                    " WHERE id="+id+" ;");
            return true;
        }catch (SQLException e){
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
