package control.managers;

import model.beans.Localite;
import model.beans.Logement;
import model.beans.humans.*;
import model.db.daos.*;
import model.enums.AdminRole;
import model.enums.UserType;

public class AdminsManager {
    private final Admin loggedInAdmin;

    public AdminsManager(Admin loggedInAdmin) {
        this.loggedInAdmin = loggedInAdmin;
    }

    public boolean suspendEmploye(UserType userType, int userId) {
        switch(userType){
            case ADMIN:{
                if (loggedInAdmin.getRole()== AdminRole.SUPER_USER)
                    return new AdminsDAO().suspendById(userId);
                else
                    System.out.println("Access denied: You don't have the authority"); //lol
                break;
            }
            case AGENT:{
                return new AgentsDAO().suspendById(userId);
            }
            case CLIENT:
                return new ClientDAO().banById(userId);
            case OPERATEUR:
                return new OperateurDAO().suspendById(userId);
            case RESPONSABLE_VENTES:
                return new ResponsableVentesDAO().suspendById(userId);
        }
        return false;
    }

    public boolean createClient(Client client){
        client.setAddedBy(loggedInAdmin.getId());
//        client.setDateAdded(new java.sql.Date(System.currentTimeMillis()));
        return new ClientDAO().add(client);
    }
    public boolean deleteClient(Client client){
        return new ClientDAO().delete(client);
    }

    public boolean createAgent(Agent agent){
        agent.setAddedBy(loggedInAdmin.getId());
//        agent.setDateAdded(new java.sql.Date(System.currentTimeMillis()));
        return new AgentsDAO().add(agent);
    }

    public boolean deleteAgent(Agent agent){
        return new AgentsDAO().delete(agent);
    }

    public boolean createAdmin(Admin admin){
        AdminsDAO adminsDAO = new AdminsDAO();
        if (adminsDAO.isSuper(loggedInAdmin.getId())){
            admin.setAddedBy(loggedInAdmin.getId());
            return adminsDAO.add(admin);
        }
        return false;
    }
    public boolean deleteAdmin(Admin admin){
        AdminsDAO adminsDAO = new AdminsDAO();
        if (adminsDAO.isSuper(loggedInAdmin.getId())){
            return adminsDAO.delete(admin);
        }
        return false;
    }

    public boolean createOperateur(Operateur operateur){
        operateur.setAddedBy(loggedInAdmin.getId());
        return new OperateurDAO().add(operateur);
    }
    public boolean deleteOperateur(Operateur operateur){
        return new OperateurDAO().delete(operateur);
    }
    public boolean createResponsableVente(ResponsableVente responsableVente){
        responsableVente.setAddedBy(loggedInAdmin.getId());
        return new ResponsableVentesDAO().delete(responsableVente);
    }

    public boolean createLogement(Logement logement){
        return new LogementDAO().add(logement);
    }
    public boolean deleteLogement(Logement logement){
        return new LogementDAO().delete(logement);
    }
    public boolean createLocalite(Localite localite){
        return new LocaliteDAO().add(localite);
    }
    public boolean deleteLocalite(Localite localite){
        return new LocaliteDAO().delete(localite);
    }

    public boolean gelerLogement(Logement logement){
        return new LogementDAO().geler(logement);
    }
    public boolean approuverEmploye(UserType userType, Person person){
        //en supposant que l'employé ki ydir l'inscription ma yetzadch f la bd heta yaqblou l'admin
        switch (userType){
            case ADMIN:{
                if (new AdminsDAO().isSuper(loggedInAdmin.getId())){
                    return createAdmin((Admin)person);
                }
                break;
            }
            case AGENT:{
                return createAgent((Agent) person);
            }
            case OPERATEUR:{
                return createOperateur((Operateur) person);
            }
            case RESPONSABLE_VENTES:{
                return createResponsableVente((ResponsableVente) person);
            }
        }
        return false;
    }

    public void imprimerFacture(int numVente){
        //TODO:hedi
    }


}
