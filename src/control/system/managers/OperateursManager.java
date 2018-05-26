package control.system.managers;

import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.VisitesDao;

public class OperateursManager {
    private final Employe loggedInOperateur;

    public OperateursManager(Employe loggedInOperateur) {
        this.loggedInOperateur = loggedInOperateur;
    }


    public boolean createClient(Client client){
        return new ClientDAO().add(client);
    }

    public boolean changerMotDePasse(String mdp){
        return new EmployeDAO().changePassword(loggedInOperateur.getId(),mdp);
    }

}
