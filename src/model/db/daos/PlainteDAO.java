package model.db.daos;

import model.beans.Plainte;
import model.beans.humans.Client;
import model.beans.humans.Person;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PlainteDAO extends DAO {

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("select * from plainte where id=" + id + ";");
            if (result.next()) {
                Plainte plainte = new Plainte();
                plainte.setId(result.getInt("id"));
                plainte.setContenu(result.getString("contenu"));
                plainte.setPlaignant((Client) new ClientDAO().getById(result.getInt("idPlaignant")));
                plainte.setDate(result.getDate("date"));

                return plainte;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Plainte plainte = (Plainte) object;
        try {
            statement.execute("insert into plainte (idPlaignant, contenu, date) values (" +
                    plainte.getPlaignant().getId() + "," +
                    "'" + plainte.getContenu() + "'," +
                    "current_date " +
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
        LinkedList<Plainte> plaintes = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("select * from plainte;");
            while (result.next()) {
                Plainte plainte = new Plainte();
                plainte.setId(result.getInt("id"));
                plainte.setContenu(result.getString("contenu"));
                plainte.setPlaignant((Client) new ClientDAO().getById(result.getInt("idPlaignant")));
                plainte.setDate(result.getDate("date"));
                plaintes.add(plainte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plaintes;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

}
