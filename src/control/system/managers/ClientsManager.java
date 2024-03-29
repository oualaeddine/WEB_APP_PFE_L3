package control.system.managers;

import control.servlets.MyServlet;
import model.beans.Message;
import model.beans.Plainte;
import model.beans.Visite;
import model.beans.humans.Client;
import model.db.daos.*;
import utils.Util;

import javax.servlet.http.HttpServletRequest;
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
    public boolean reporterRDV(int id, Date newTime){
        Visite visite = new VisitesDao().getById(id);
        visite.setTimestamp(newTime);
        if (new VisitesDao().reporter(id) && reserverVisite(visite)) return true;
        return false;
    }
    public boolean supprimerCompte(Client client){
        return new ClientDAO().delete(client);
    }

    public boolean modifierProfil(HttpServletRequest request) {
        String prenom = request.getParameter("prenomInput");
        String nom = request.getParameter("nomInput");
        String email = request.getParameter("emailInput");
        String tel = request.getParameter("inputTel");
        String adresse = request.getParameter("adresseInput");

        Client client = new Client();
        client.setId(loggedInClient.getId());
        client.setPrenom(prenom);
        client.setNom(nom);
        client.setEmail(email);
        client.setTel(tel);
        client.setAdresse(adresse);
        client.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));

        return new ClientDAO().update(client);

    }

    public boolean sePlaindre(HttpServletRequest request) {
        String contenu = request.getParameter("plainte");
        Plainte plainte = new Plainte();
        plainte.setPlaignant(loggedInClient);
        plainte.setContenu(contenu);
        return new PlainteDAO().add(plainte);
    }

    public boolean contacterSociete(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
        String email = client.getEmail();
        String objet = request.getParameter("objet");
        String msg = request.getParameter("message");
        String nom = client.getNom();
        String prenom = client.getPrenom();
        String tel = client.getTel();
        Message message = new Message();
        message.setEmail(email);
        message.setObject(objet);
        message.setContent(msg);
        message.setClient(true);
        message.setTel(tel);
        message.setNom(nom);
        message.setPrenom(prenom);
        return new MessageVisiteursDAO().add(message);
    }

    public boolean ajouterALaListeDeSouhaits(int clientId, int logementId) {
        LogementDAO logementDAO = new LogementDAO();
        if (logementDAO.isInWishList(clientId, logementId)) {
            return logementDAO.retirerDeLaListeDeSouhaits(clientId, logementId);
        } else {
            return logementDAO.ajouterALaListeDeSouhaits(clientId, logementId);
        }
    }
}
