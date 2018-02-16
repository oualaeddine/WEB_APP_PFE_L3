package model.db.daos;

import model.beans.Visite;
import model.db.DAO;

import java.util.LinkedList;

public class VisitesDao extends DAO {

    public Visite getById(int id) {
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "visite");
    }

    public boolean update(Visite visite) {
        return false;
    }

    public boolean add(Visite visite) {
        return false;
    }

    public LinkedList<Visite> getAll() {
        return null;
    }

    public LinkedList<Visite> getPassee() {
        return null;
    }

    public LinkedList<Visite> getByAgent(int agentId) {
        return null;
    }
}
