package model.db.daos;

import model.db.DAO;
import model.db.DAOInterface;

import java.util.LinkedList;

public class VentesDAO extends DAO implements DAOInterface{
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

    @Override
    public boolean exists(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        return null;
    }
}
