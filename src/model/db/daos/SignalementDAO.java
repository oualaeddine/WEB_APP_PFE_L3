package model.db.daos;

import model.beans.Signalement;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SignalementDAO extends DAO {
    public boolean add(int plaignantId, int clientId, String comment) {
        try {
            statement.execute("INSERT INTO signalement (idPlaignant, idClient, infos) VALUES (" + plaignantId + ", " + clientId + ", '" + comment + "' );");
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
        LinkedList<Signalement> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM signalement;");
            while (result.next()) {
                Signalement signalement = new Signalement();
                signalement.setPlaignant((Employe) new EmployeDAO().getById(result.getInt("idPlaignant")));
                signalement.setClient((Client) new ClientDAO().getById(result.getInt("idClient")));
                signalement.setMotif(result.getString("infos"));

                list.add(signalement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT (count(id)) FROM signalement;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
