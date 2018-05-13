package model.db.daos;

import model.beans.Localite;
import model.beans.humans.Employe;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LocaliteDAO extends DAO {
    @Override
    public Employe getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = localiteStatement.executeQuery("SELECT * FROM localite WHERE id=" + id);
            if (result.next()) {
                Localite localite = new Localite();
                localite.setId(result.getInt("id"));
                localite.setNom(result.getString("nom"));

                try {
                    result.close();
                    localiteStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return localite;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "localite");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Localite localite = (Localite) object;
        try {
            localiteStatement.execute("INSERT INTO localite(nom) VALUES (" +
                    "'" + localite.getNom() + "'" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Localite localite = (Localite) object;
        try {
            localiteStatement.execute("DELETE FROM localite WHERE nom=" + localite.getNom());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(Object object) {
        Localite localite = (Localite) object;
        ResultSet result;
        try {
            result = localiteStatement.executeQuery("SELECT * FROM localite");
            while (result.next()) {
                String nom = result.getString("nom");
                if (localite.getNom().equals(nom)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Localite> list = new LinkedList<>();
        ResultSet result;
        try {
            result = localiteStatement.executeQuery("SELECT * FROM localite;");
            while (result.next()) {
                Localite localite = new Localite();
                localite.setId(result.getInt("id"));
                localite.setNom(result.getString("nom"));
                list.add(localite);
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
            result = localiteStatement.executeQuery("SELECT count(id) FROM localite;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
