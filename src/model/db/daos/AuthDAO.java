package model.db.daos;

import model.beans.humans.Person;
import model.db.DAO;
import model.enums.UserType;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AuthDAO extends DAO {
    public boolean exists(Object object, UserType type) {
        Person person = (Person) object;
        ResultSet result;
        try {
            String table = Util.getStringFromType(type);
            result = statement.executeQuery("SELECT * FROM " + table);
            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                if (person.getUsername().equals(username) && person.getPassword().equals(password))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getUserIdBy(String username, UserType type) {
        // TODO: 2/14/2018 jibili l userId
        ResultSet result;
        String table = Util.getStringFromType(type);
        if (table != null) {
            try {
                result = statement.executeQuery("SELECT id FROM " + table + " WHERE username = " + username);
                if (result.next()) {
                    return result.getInt("id");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
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
