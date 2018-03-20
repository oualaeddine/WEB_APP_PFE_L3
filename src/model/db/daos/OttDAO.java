package model.db.daos;

import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.UserType;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OttDAO extends DAO{
    public String generateNewToken(int userId,UserType userType) {
        String code="";
        do {
            String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
            for (int i = 0; i < 6; i++) {
                code = code + candidateChars.charAt((int) (Math.random() * candidateChars.length()));
            }
        }while (exists(code));
        System.out.println("Attribution du code: "+add(code, userId,userType));
        return code;
    }
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

    public boolean verifyToken(String token, int userId, UserType userType) {
        ResultSet result;
        boolean exists=false;
        try {
            result = statement.executeQuery("SELECT id FROM ott WHERE token='" + token + "' AND userId=" + userId + " AND userType='" + Util.getStringFromType(userType) + "';");
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
    public boolean add(String code, int userId, UserType userType) {
        try {
            statement.execute("INSERT INTO ott (token, userId,userType, timestamp) VALUES (" +
                    "'" + code + "', " + userId +", '"+ Util.getStringFromType(userType) + "', CURRENT_TIMESTAMP);");
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
