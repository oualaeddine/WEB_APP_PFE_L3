package model.db.daos;

import model.db.DAO;

public class LocaliteDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "localite");
    }
}
