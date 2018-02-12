package model.db.daos;

import model.db.DAO;

public class OperateurDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "operateur");
    }

}
