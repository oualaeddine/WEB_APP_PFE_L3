package model.db.daos;

import model.db.DAO;

public class ClientDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "client");
    }
}
