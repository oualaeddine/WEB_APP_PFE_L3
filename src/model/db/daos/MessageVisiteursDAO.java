package model.db.daos;

import model.beans.Message;
import model.beans.humans.Person;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MessageVisiteursDAO extends DAO {
    @Override
    public Person getByEmail(String email) {
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
        Message message = (Message) object;
        String isClient = message.isClient() ? "1" : "0";
        try {
            statement.execute("INSERT INTO message_visiteur (email, objet,content, isClient, timestamp) VALUES (" +
                    "'" + message.getEmail() + "'," +
                    "'" + message.getObject() + "'," +
                    "'" + message.getContent() + "'," +
                    isClient + "," +
                    "current_timestamp " +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        ResultSet result;
        LinkedList<Message> messages = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM message_visiteur;");
            while (result.next()) {
                Message message = new Message();
                message.setId(result.getInt("id"));
                message.setEmail(result.getString("email"));
                message.setObject(result.getString("objet"));
                message.setContent(result.getString("content"));
                message.setClient(result.getBoolean("isClient"));
                message.setTimestamp(result.getTimestamp("timestamp"));

                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }
}
