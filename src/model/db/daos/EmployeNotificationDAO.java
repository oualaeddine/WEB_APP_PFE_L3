package model.db.daos;

import model.beans.Notification;
import model.beans.humans.Person;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class EmployeNotificationDAO extends DAO {

    public EmployeNotificationDAO() {
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM notification_employe where id=" + id + ";");
            if (result.next()) {
                Notification notification = new Notification();
                notification.setId(result.getInt("id"));
                notification.setDestinataire((Person) new EmployeDAO().getById(result.getInt("destinataire")));
                notification.setContent(result.getString("content"));
                notification.setTimestamp(result.getTimestamp("timestamp"));

                return notification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        Notification notification = (Notification) object;
        try {
            statement.execute("INSERT INTO notification_employe (destinataire, content, timestamp) VALUES (" +
                    notification.getDestinataire().getId() + "," +
                    "'" + notification.getContent() + "'," +
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
        LinkedList<Notification> notifications = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM notification_employe;");
            while (result.next()) {
                Notification notification = new Notification();
                notification.setId(result.getInt("id"));
                notification.setDestinataire((Person) new EmployeDAO().getById(result.getInt("destinataire")));
                notification.setContent(result.getString("content"));
                notification.setTimestamp(result.getTimestamp("timestamp"));
                System.out.println(notification.toString());
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

    public LinkedList<Notification> getByEmployee(int id) {
        ResultSet result;
        LinkedList<Notification> notifications = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM notification_employe where destinataire=" + id + ";");
            while (result.next()) {
                Notification notification = new Notification();
                notification.setId(result.getInt("id"));
                notification.setDestinataire((Person) new EmployeDAO().getById(result.getInt("destinataire")));
                notification.setContent(result.getString("content"));
                notification.setTimestamp(result.getTimestamp("timestamp"));
                System.out.println(notification.toString());
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
