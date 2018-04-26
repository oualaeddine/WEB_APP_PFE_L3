package control.system.managers;

import model.beans.Rapport;
import model.beans.Visite;
import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import model.db.daos.RapportDAO;
import model.db.daos.VisitesDao;
import model.enums.EtatClient;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class AgentsManager {
    private final Employe loggedInAgent;


    public AgentsManager(Employe loggedInAgent) {
        this.loggedInAgent = loggedInAgent;
    }
    public boolean changerMotDePasse(String mdp){
        return new EmployeDAO().changePassword(loggedInAgent.getId(),mdp);
    }

    public Employe creerCompte(String nom, String prenom, Date dateNaiss,String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended,int idRegion){
        Employe agent = new Employe();
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setDateNaissance(dateNaiss);
        agent.setAdresse(adresse);
        agent.setTel(tel);
        agent.setEmail(email);
        agent.setUsername(username);
        agent.setPassword(password);
        agent.setSuspended(isSuspended);
        return agent;
    }

    public boolean envoyerRapport(HttpServletRequest request){
        int visiteID = Integer.parseInt(request.getParameter("visiteRapport"));
        System.out.println("visite numero: " + visiteID);
        Visite visite = new VisitesDao().getById(visiteID);
        Rapport rapport = new Rapport();
        if (request.getParameter("etatClient") == null) {
            rapport.setEtatClient(EtatClient.ABSENT);
            rapport.setVisite(visite);
        } else {
            rapport.setVisite(visite);
            rapport.setEtatClient(EtatClient.PRESENT);
            rapport.setAvis(request.getParameter("avis").equals("positif"));
            rapport.setCommentaire(request.getParameter("commentaire"));
        }
        return new RapportDAO().add(rapport);
    }

}
