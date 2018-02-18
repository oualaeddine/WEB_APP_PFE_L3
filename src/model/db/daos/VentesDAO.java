package model.db.daos;

import model.db.DAO;
import model.db.DAOInterface;

import java.sql.SQLException;
import java.util.LinkedList;

public class VentesDAO extends DAO {
    public boolean confirm(int id) {
        try {
            statement.execute("UPDATE logement SET etat='vendu' WHERE id=" + id);
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

    public boolean deleteById(int id) {
        return super.deleteById(id, "vente");
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

    public boolean exists(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        return null;
    }
}
