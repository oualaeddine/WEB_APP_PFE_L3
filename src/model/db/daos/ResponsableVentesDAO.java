package model.db.daos;

import model.db.DAO;

public class ResponsableVentesDAO extends DAO {
    public boolean deleteById(int id) {
        return super.deleteById(id, "responsable_vente");
    }
}
