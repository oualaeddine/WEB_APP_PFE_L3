package model.db.daos;

import model.beans.humans.*;
import model.db.DAO;
import model.enums.UserType;

import java.util.LinkedList;

public class EmployeDAO extends DAO {

    public Person getByUsername(String username, UserType userType) {
        switch (userType) {
            case ADMIN:{
                Admin admin = new Admin();
                admin.setUsername(username);
                return new AdminsDAO().getByUsername(admin);
            }
            case RESPONSABLE_VENTES:{
                ResponsableVente responsableVente = new ResponsableVente();
                responsableVente.setUsername(username);
                return new ResponsableVentesDAO().getByUsername(responsableVente);
            }
            case OPERATEUR:{
                Operateur operateur = new Operateur();
                operateur.setUsername(username);
                return new OperateurDAO().getByUsername(operateur);
            }
            case AGENT:{
                Agent agent = new Agent();
                agent.setUsername(username);
                return new AgentsDAO().getByUsername(agent);
            }
            case CLIENT:{
                Client client = new Client();
                client.setUsername(username);
                return new ClientDAO().getByUsername(client);
            }
        }
        return null;
    }

    public Employe getByEmail(String email, UserType userType) {
        switch (userType) {
            case AGENT:{
                return new AgentsDAO().getByEmail(email);
            }
            case OPERATEUR:{
                return new OperateurDAO().getByEmail(email);
            }
            case RESPONSABLE_VENTES:{
                return new ResponsableVentesDAO().getByEmail(email);
            }
            case ADMIN:{
                return new AdminsDAO().getByEmail(email);
            }
            default: return null;
        }
    }

    @Override
    public Employe getByEmail(String email) {
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
}
