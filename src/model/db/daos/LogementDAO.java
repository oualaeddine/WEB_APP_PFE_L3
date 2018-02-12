package model.db.daos;

import model.db.DAO;

public class LogementDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "logement");
    }
}
