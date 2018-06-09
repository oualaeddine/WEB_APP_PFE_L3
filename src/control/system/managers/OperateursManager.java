package control.system.managers;

import model.beans.Appel;
import model.beans.Logement;
import model.beans.RDV;
import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.*;
import utils.Util;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.LinkedList;

@SuppressWarnings("ALL")
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

    public boolean confirmerAppel(HttpServletRequest request) {
        Appel appel = (Appel) new AppelsDAO().getById(Integer.parseInt(request.getParameter("appelConfirme")));
        if (request.getParameter("confirmationAppel") == null) {
            appel.setVisite(null);
            return new AppelsDAO().confirmer(appel);
        } else {
            String nom = request.getParameter("nomInput");
            String prenom = request.getParameter("prenomInput");
            Date date = Util.getDateFromString(request.getParameter("dateNaissance"));
            String email = request.getParameter("emailInput");
            String tel = request.getParameter("inputTel");
            String adresse = request.getParameter("adresseInput");
            String username = request.getParameter("usernameInput");
            String password = request.getParameter("passwordInput");
            Client client = new Client();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setDateNaissance(date);
            client.setEmail(email);
            client.setTel(tel);
            client.setAdresse(adresse);
            client.setUsername(username);
            client.setPassword(password);

            if (createClient(client)) {
                Client newClient = new ClientDAO().getByUsername(username);
                Visite visite = appel.getVisite();
                visite.setClient(newClient);
                RDV rdv = new RDV();
                rdv.setHorraire(appel.getVisite().getHorraire());
                rdv.setDate(appel.getVisite().getTimestamp());

                if (new LogementDAO().isFree(visite.getLogement().getId(), rdv)) {
                    //Logement libre pour la visite: Trouver un agent
                    Employe agent = new VisitesDao().getFreeAgentsForVisite(rdv, visite.getLogement().getLocalite().getId());
                    if (agent != null) {
                        //Agent trouvé pour le logement choisi: Ajouter la visite et confirmer l'appel
                        visite.setAgent(agent);
                        return new VisitesDao().add(visite) && new AppelsDAO().confirmer(appel);
                    } else {
                        //Pas d'agent pour le logement choisi: trouver des remplacements
                        LinkedList<Logement> remplacements = new LogementDAO().getRemplacement(visite.getLogement());
                        for (Logement logement : remplacements) {
                            //Pour chaque remplacement, vérifier la disponibilité du logement et d'un agent
                            if (new LogementDAO().isFree(logement.getId(), rdv) && new VisitesDao().getFreeAgentsForVisite(rdv, logement.getLocalite().getId()) != null) {
                                //Logement libre et agent libre: Ajouter la visite et confirmer l'appel
                                visite.setLogement(logement);
                                visite.setAgent(new VisitesDao().getFreeAgentsForVisite(rdv, logement.getLocalite().getId()));
                                return new VisitesDao().add(visite) && new AppelsDAO().confirmer(appel);
                            }
                        }
                        // Aucun agent libre pour tout les remplacements ou aucun logement libre pour la visite
                        return false;
                    }
                } else { //Le logement choisi n'est pas libre: trouver un remplacement
                    LinkedList<Logement> remplacements = new LogementDAO().getRemplacement(visite.getLogement());
                    for (Logement logement : remplacements) {
                        //Pour chaque remplacement, vérifier la disponibilité du logement et d'un agent
                        if (new LogementDAO().isFree(logement.getId(), rdv) && new VisitesDao().getFreeAgentsForVisite(rdv, logement.getLocalite().getId()) != null) {
                            //Logement libre et agent libre: Ajouter la visite et confirmer l'appel
                            visite.setLogement(logement);
                            visite.setAgent(new VisitesDao().getFreeAgentsForVisite(rdv, logement.getLocalite().getId()));
                            return new VisitesDao().add(visite) && new AppelsDAO().confirmer(appel);
                        }
                    }
                    //Aucun agent libre pour tout les remplacements ou aucun logement libre pour la visite
                    return false;
                }
            } else { //Ajout du client: false
                return false;
            }
        }
    }
}
