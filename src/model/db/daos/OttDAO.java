package model.db.daos;

import model.beans.humans.Employe;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OttDAO extends DAO{
    public boolean exists(String token) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT id FROM ott WHERE token='"+token+"';");
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getUserBytoken(String token) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT userId FROM ott WHERE token='" + token + "';");
            if (result.next()) {
                return result.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean add(String code, int userId) {
        try {
            statement.execute("INSERT INTO ott (token, userId, timestamp) VALUES (" +
                    "'" + code + "', " + userId + ", CURRENT_TIMESTAMP);");
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
