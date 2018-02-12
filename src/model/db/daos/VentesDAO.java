package model.db.daos;

import model.db.DAO;

public class VentesDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "vente");
    }
}
