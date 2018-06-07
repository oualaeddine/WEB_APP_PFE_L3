package model.db.daos;

import model.beans.humans.Person;
import model.db.DAO;

import java.util.LinkedList;

public class CallsDAO extends DAO {
    public boolean isClient(String callerNumber) {
        return false;
    }

    public void add(String number) {
        // TODO: 6/6/2018 add number at state "not confirmed" with date = NOW()
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
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
    public LinkedList getAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
