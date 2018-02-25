package control.managers;

import model.beans.Localite;
import model.beans.Vente;
import model.beans.Visite;
import model.beans.humans.Agent;
import model.db.daos.*;
import model.enums.EtatVente;
import model.enums.EtatVisite;

import java.sql.Date;

public class AgentsManager {
    private final Agent loggedInAgent;


    public AgentsManager(Agent loggedInAgent) {
        this.loggedInAgent = loggedInAgent;
    }
    public boolean changerMotDePasse(String mdp){
        return new AgentsDAO().changePassword(mdp,loggedInAgent.getId());
    }

    public Agent creerCompte(String nom, String prenom, Date dateNaiss,String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended,int idRegion){
        Agent agent = new Agent();
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setDateNaissance(dateNaiss);
        agent.setAdresse(adresse);
        agent.setTel(tel);
        agent.setEmail(email);
        agent.setUsername(username);
        agent.setPassword(password);
        agent.setAddedBy(addedBy);
        agent.setSuspended(isSuspended);
        agent.setLocalite((Localite)new LocaliteDAO().getById(idRegion));
        return agent;
    }

    public void envoyerRapport(Visite visite){
        switch (visite.getEtatVisite()) {
            case VALIDEE:
                Vente vente = new Vente();
                vente.setAgent(loggedInAgent);
                vente.setClient(visite.getClient());
                vente.setLogement(visite.getLogement());
                vente.setEtatVente(EtatVente.NON_CONFIRMEE);
                //TODO: njibou kech responsable de vente pour s'occuper de la vente qui vient d'être créée.
                new VentesDAO().add(vente);
                new VisitesDao().validerVisite(visite);
                break;
            case NON_VALIDEE:
                new VisitesDao().visiteNegative(visite);
                break;
            case ANNULEE:


        }
    }

}
