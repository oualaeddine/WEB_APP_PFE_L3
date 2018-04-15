package control.system.managers;

import model.beans.Localite;
import model.beans.Logement;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;
import model.enums.AdminRole;
import model.enums.UserType;

import java.sql.Date;

public class SuManager extends AdminsManager{

    public SuManager(Employe loggedInAdmin) {
        super(loggedInAdmin);
    }


    public Employe creerCompte(String nom, String prenom, Date dateNaiss, String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended, AdminRole role){
        Employe admin = new Employe();
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setDateNaissance(dateNaiss);
        admin.setAdresse(adresse);
        admin.setTel(tel);
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setSuspended(isSuspended);
        switch (role){
            case SUPER_USER:
                admin.setUserType(UserType.SU); break;
            case ADMIN:
                admin.setUserType(UserType.ADMIN); break;
        }
        return admin;
    }
    public boolean reintegrerEmploye(int userId){
        return new EmployeDAO().reintegrerById(userId);
    }
    public boolean suspendEmployee(int userId) {
        return new EmployeDAO().suspendById(userId);
    }

    public boolean createClient(Client client){
        return new ClientDAO().add(client);
    }
    public boolean deleteClient(Client client){
        return new ClientDAO().delete(client);
    }


//    public boolean createAdmin(Employe employe) {
//        if (employe.getUserType() == UserType.ADMIN) {
//                Employe admin = employe;
//                admin.setUserType(UserType.ADMIN);
//                return new EmployeDAO().add(admin);
//            }
//        } else {
//            return new EmployeDAO().add(employe);
//        }
//        return false;
//    }

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
        return new LogementDAO().geler(logement.getId());
    }
    public boolean approuverEmploye(Employe employe){
        //en supposant que l'employé ki ydir l'inscription ma yetzadch f la bd heta yaqblou l'admin
        return new EmployeDAO().add(employe);
    }

    public void imprimerFacture(int numVente){
        //TODO:Imprimer facture
    }


}
