package control.managers;

import model.beans.Visite;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import model.db.daos.VisitesDao;

import java.sql.Date;

public class ClientsManager {
    private final Client loggedInClient;

    public boolean creerCompte(String nom,String prenom,Date dateNaiss, String adresse, String tel,
   String email,String username,String password){
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(dateNaiss);
        client.setAdresse(adresse);
        client.setTel(tel);
        client.setEmail(email);
        client.setUsername(username);
        client.setPassword(password);
        return new ClientDAO().add(client);
    }
    public ClientsManager(Client loggedInClient) {
        this.loggedInClient = loggedInClient;
    }
    public boolean modifierMotDePasse(String mdp){
        return new ClientDAO().updatePassword(mdp,loggedInClient.getId());
    }
    public boolean reserverVisite(Visite visite){
        visite.setClient(loggedInClient);
        return new VisitesDao().add(visite);
    }
    public boolean reporterRDV(int id, Date newDate){
        Visite visite = new VisitesDao().getById(id);
        visite.setDate(newDate);
        if (new VisitesDao().reporter(id) && reserverVisite(visite)) return true;
        return false;
    }
    public boolean supprimerCompte(Client client){
        return new ClientDAO().delete(client);
    }

}
