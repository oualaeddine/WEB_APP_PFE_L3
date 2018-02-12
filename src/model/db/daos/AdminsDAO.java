package model.db.daos;

import model.db.DAO;

public class AdminsDAO extends DAO {
    public AdminsDAO() {
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "admin");
    }
}
