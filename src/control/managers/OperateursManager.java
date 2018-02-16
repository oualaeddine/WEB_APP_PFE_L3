package control.managers;

import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Operateur;
import model.db.daos.ClientDAO;
import model.db.daos.OperateurDAO;
import model.db.daos.VisitesDao;

public class OperateursManager {
    private final Operateur loggedInOperateur;

    public OperateursManager(Operateur loggedInOperateur) {
        this.loggedInOperateur = loggedInOperateur;
    }

    public boolean createClient(Client client){
        client.setAddedBy(loggedInOperateur.getId());
        return new ClientDAO().add(client);
    }
    public boolean banClient(Client client){
        return new ClientDAO().banById(client.getId());
    }
    public boolean createVisite(Visite visite){
        return new VisitesDao().add(visite);
    }

    public boolean changerMotDePasse(String mdp){
        return new OperateurDAO().changePassword(mdp,loggedInOperateur.getId());
    }

}
