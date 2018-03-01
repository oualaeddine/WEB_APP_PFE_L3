package control.system.managers;

import model.beans.Localite;
import model.beans.Logement;
import model.beans.humans.*;
import model.db.daos.*;
import model.enums.AdminRole;
import model.enums.UserType;

import java.sql.Date;

public class AdminsManager {
    private final Admin loggedInAdmin;

    public AdminsManager(Admin loggedInAdmin) {
        this.loggedInAdmin = loggedInAdmin;
    }


    public Admin creerCompte(String nom, String prenom, Date dateNaiss, String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended,AdminRole role){
        Admin admin = new Admin();
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setDateNaissance(dateNaiss);
        admin.setAdresse(adresse);
        admin.setTel(tel);
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setAddedBy(addedBy);
        admin.setSuspended(isSuspended);
        switch (role){
            case SUPER_USER:
                admin.setRole(AdminRole.SUPER_USER); break;
            case ADMIN:
                admin.setRole(AdminRole.ADMIN); break;
        }
        return admin;
    }
    public boolean reintegrerEmploye(UserType userType, int userId){
        switch (userType){
            case ADMIN: {
                if (new AdminsDAO().isSuper(loggedInAdmin.getId()))
                    return new AdminsDAO().reintegrerById(userId);
                else {
                    System.out.println("Access denied: you do not know da wae");
                    return false;
                }
            }
            case AGENT:{
                return new AgentsDAO().reintegrerById(userId);
            }
            case OPERATEUR:{
                return new OperateurDAO().reintegrerById(userId);
            }
            case RESPONSABLE_VENTES:{
                return new ResponsableVentesDAO().reintegrerById(userId);
            }
            default: return false;
        }
    }
    public boolean suspendEmployee(UserType userType, int userId) {
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
        return new ClientDAO().add(client);
    }
    public boolean deleteClient(Client client){
        return new ClientDAO().delete(client);
    }

    public boolean createAgent(Agent agent){
        agent.setAddedBy(loggedInAdmin.getId());
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
    public boolean deleteResponsableVente(ResponsableVente responsableVente){
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
