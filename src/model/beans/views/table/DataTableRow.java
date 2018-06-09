package model.beans.views.table;

import model.beans.*;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.AssignationDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.VersementDAO;
import model.enums.DataTableRowFormat;
import model.enums.EtatClient;
import utils.Util;

public class DataTableRow {

    private final DataTableRowFormat dataFormat;
    private final Object object;
    private String html;

    public DataTableRow(DataTableRowFormat dataFormat, Object object) {
        this.object = object;
        this.dataFormat = dataFormat;
        setupHtml();
    }

    private void setupHtml() {
        switch (dataFormat) {
            case APPEL:
                setupHtmlForAppel();
                break;
            case ANNULER_VENTE:
                setupHtmlForAnnulerVente();
                break;
            case PLAINTE:
                setupHtmlForPlainte();
                break;
            case BANNIR_CLIENT:
                setupHtmlForBannirClient();
                break;
            case NOTIFICATIONS:
                setupHtmlForNotifications();
                break;
            case SIGNALS:
                setupHtmlForSignals();
                break;
            case SIGNALER:
                setupHtmlForSignalerClient();
                break;
            case ASSIGNER_REGION:
                setupHtmlForAssignerRegion();
                break;
            case LOCALITE:
                setupHtmlForLocalite();
                break;
            case LOGEMENT:
                setupHtmlForLogement();
                break;
            case ADMIN:
                setupHtmlForAdmin();
                break;
            case AGENT:
                setupHtmlForAgent();
                break;
            case OPERATEUR:
                setupHtmlForOperateur();
                break;
            case CLIENT:
                setupHtmlForClient();
                break;
            case MESSAGE:
                setupHtmlForMessage();
                break;
            case RESPONSABLE_VENTES:
                setupHtmlForEmployee();
                break;
            case VENTE:
                setupHtmlForVente();
                break;
            case VISITE:
                setupHtmlForVisite();
                break;
            case APPROUVER_EMPLOYE:
                setupHtmlForApprouverEmploye();
                break;
            case SUSPENDRE_EMPLOYE:
                setupHtmlForSuspendreEmploye();
                break;
            case GELER_LOGEMENT:
                setupHtmlForGelerLogement();
                break;
            case MODIFIER_VISITE:
                setupHtmlForModifierVisite();
                break;
            case ETABLIR_RAPPORT:
                setupHtmlForEtablirRapport();
                break;
            case RAPPORT:
                setupHtmlForRapport();
                break;
            case CONFIRMER_APPEL:
                setupHtmlForConfirmerAppel();
                break;

        }
    }

    private void setupHtmlForConfirmerAppel() {
        Appel appel = (Appel) object;
        String isConfirmed = appel.isConfirmed() ? "Confirmé" : "Non confirmé";
        html = "<tr>" +
                "<td>" + appel.getId() + "</td>" +
                "<td>" + appel.getNumeroTel() + "</td>" +
                "<td>" + appel.getVisite().getLogement().getTitre() + "</td>" +
                "<td>" + appel.getVisite().getTimestamp() + " | " + Util.getStringFromHorraire(appel.getVisite().getHorraire()) + "</td>" +
                "<td>" + isConfirmed + "</td>" +
                "<td><button type=\"button\"  onclick=\"getConfirmedAppel(" + appel.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#confirmerAppelModal\" value=\"" + appel.getId() + "\">Confirmer</button></td>" +
                "</tr>";
    }

    private void setupHtmlForAppel() {
        Appel appel = (Appel) object;
        String isConfirmed = appel.isConfirmed() ? "Confirmé" : "Non confirmé";
        html = "<tr>" +
                "<td>" + appel.getId() + "</td>" +
                "<td>" + appel.getNumeroTel() + "</td>" +
                "<td>" + appel.getVisite().getLogement().getTitre() + "</td>" +
                "<td>" + appel.getVisite().getTimestamp() + " | " + Util.getStringFromHorraire(appel.getVisite().getHorraire()) + "</td>" +
                "<td>" + isConfirmed + "</td>" +
                "</tr>";

    }

    private void setupHtmlForAnnulerVente() {
        Vente vente = (Vente) object;
        double restant = vente.getLogement().getPrix() - new VersementDAO().getSommeVersementsByVente(vente.getId());
        html = "<tr>" +
                "<td>" + vente.getId() + "</td>" +
                "<td>" + vente.getClient().getFullName() + "</td>" +
                "<td>" + vente.getLogement().getTitre() + "</td>" +
                "<td>" + vente.getDate() + "</td>" +
                "<td>" + restant + "</td>" +
                "<td>" + vente.getEtatVente() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getCanceledVente(" + vente.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#annulerVenteModal\" value=\"" + vente.getId() + "\">Annuler</button></td>" +
                "</tr>";
    }

    private void setupHtmlForPlainte() {
        Plainte plainte = (Plainte) object;
        html = "<tr>" +
                "<td>" + plainte.getId() + "</td>" +
                "<td>" + plainte.getPlaignant().getFullName() + "</td>" +
                "<td>" + plainte.getContenu() + "</td>" +
                "<td>" + plainte.getDate() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForBannirClient() {
        Client client = (Client) object;
        String action = client.isBanned() ? "Rétablir" : "Bannir";
        html = "<tr>" +
                "<td>" + client.getId() + "</td>" +
                "<td>" + client.getNom() + "</td>" +
                "<td>" + client.getPrenom() + "</td>" +
                "<td>" + client.getTel() + "</td>" +
                "<td>" + client.getAdresse() + "</td>" +
                "<td>" + client.getEmail() + "</td>" +
                "<td>" + client.getDateNaissance() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getBannedClientId(" + client.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#bannirModal\" value=\"" + client.getId() + "\">" + action + "</td>" +
                "</tr>";
    }

    private void setupHtmlForNotifications() {
        Notification notification = (Notification) object;
        html = "<tr>" +
                "<td>" + notification.getId() + "</td>" +
                "<td>" + notification.getContent() + "</td>" +
                "<td>" + notification.getTimestamp() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForRapport() {
        Rapport rapport = (Rapport) object;
        String avis = rapport.getEtatClient() == EtatClient.ABSENT ? "ABSENT" : rapport.isAvis() ? "Positif" : "Négatif";
        String commentaire = rapport.getEtatClient() == EtatClient.ABSENT ? "ABSENT" : rapport.getCommentaire();
        html = "<tr>" +
                "<td>" + rapport.getVisite().getId() + " </td>" +
                "<td>" + rapport.getVisite().getTimestamp() + " " + Util.getStringFromHorraire(rapport.getVisite().getHorraire()) + " </td>" +
                "<td>" + rapport.getVisite().getLogement().getTitre() + "</td>" +
                "<td>" + rapport.getVisite().getClient().getFullName() + "</td>" +
                "<td>" + rapport.getEtatClient() + "</td>" +
                "<td>" + avis + "</td>" +
                "<td>" + commentaire + "</td>" +
                "</tr>";
    }

    private void setupHtmlForEtablirRapport() {
        Visite visite = (Visite) object;
        html ="<tr>" +
                "<td>" + visite.getId() + "</td>" +
                "<td>" + visite.getLogement().getTitre() + "</td>" +
                "<td>" + visite.getAgent().getNom()+" "+visite.getAgent().getPrenom() + "</td>" +
                "<td>" + visite.getClient().getNom()+" "+visite.getClient().getPrenom()+ "</td>" +
                "<td>" + visite.getTimestamp() + "</td>" +
                "<td>" + visite.getEtatVisite() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getVisiteTaaLrapport(" + visite.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#etablirRapportModal\" value=\"" + visite.getId() + "\">Rapport</td>"+
                "</tr>";
    }

    private void setupHtmlForModifierVisite() {
        Visite visite = (Visite) object;
        html ="<tr>" +
                "<td>" + visite.getId() + "</td>" +
                "<td>" + visite.getLogement().getTitre() + "</td>" +
                "<td>" + visite.getAgent().getNom()+" "+visite.getAgent().getPrenom() + "</td>" +
                "<td>" + visite.getClient().getNom()+" "+visite.getClient().getPrenom()+ "</td>" +
                "<td>" + visite.getTimestamp() + "</td>" +
                "<td>" + visite.getEtatVisite() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getVisiteModifieeId(" + visite.getId() +
                "," + visite.getLogement().getId() + "," + visite.getLogement().getLocalite().getId() + "," + visite.getClient().getId() +
                ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#modifierVisiteModal\"" +
                " value=\"" + visite.getId() + "\">Modifier</td>" +
                "</tr>";
    }

    private void setupHtmlForGelerLogement() {
        Logement logement = (Logement) object;
        String action = logement.isGele() ? "Dégeler" : "Geler";
        String etat = logement.isGele() ? "Gelé" : "Libre";
        html = "<tr>" +
                "<td>"+ logement.getId() +"</td>"+
                "<td>"+ logement.getTitre() +"</td>"+
                "<td>"+ logement.getDescription() +"</td>"+
                "<td>"+ logement.getSuperficie() +"</td>"+
                "<td>"+ logement.getAdresse() +"</td>"+
                "<td>"+ logement.getLocalite().getNom() +"</td>"+
                "<td>"+ logement.getPrix() +" DZD</td>"+
                "<td>"+ logement.getEtage() +"</td>"+
                "<td>"+ etat +"</td>"+
                "<td><button type=\"button\"  onclick=\"getLogementGeleId(" + logement.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#gelerModal\" value=\"" + logement.getId() + "\">"+action+"</td>" +
                "</tr>";
    }

    private void setupHtmlForSuspendreEmploye() {
        Employe employe = (Employe) object;
        String action = employe.isSuspended() ? "Réintegrer" : "Suspendre";
        html = "<tr>" +
                "<td>" + employe.getId() + "</td>" +
                "<td>" + employe.getNom() + " " + employe.getPrenom() + "</td>" +
                "<td>" + employe.getTel() + "</td>" +
                "<td>" + employe.getAdresse() + "</td>" +
                "<td>" + employe.getEmail() + "</td>" +
                "<td>" + employe.getDateNaissance() + "</td>" +
                "<td>" + employe.getUserType() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getSuspendedId(" + employe.getId() + ")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#suspendreModal\" value=\"" + employe.getId() + "\">" + action + "</td>" +
                "</tr>";
    }

    private void setupHtmlForApprouverEmploye() {
        Employe employe = (Employe) object;
        html = "<tr>" +
                "<td>" + employe.getId() + "</td>" +
                "<td>" + employe.getNom() + " "+ employe.getPrenom() + "</td>" +
                "<td>" + employe.getTel() + "</td>" +
                "<td>" + employe.getAdresse() + "</td>" +
                "<td>" + employe.getEmail() + "</td>" +
                "<td>" + employe.getDateNaissance() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getApprovedId("+employe.getId()+")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#approuverModal\" value=\""+employe.getId()+"\"> Approuver</td>" +
                "</tr>";
    }

    private void setupHtmlForOperateur() {
        Employe operateur = (Employe) object;
        html = "<tr>" +
                "<td>" + operateur.getId() + "</td>" +
                "<td>" + operateur.getNom() + " "+ operateur.getPrenom()+"</td>" +
                "<td>" + operateur.getTel() + "</td>" +
                "<td>" + operateur.getAdresse() + "</td>" +
                "<td>" + operateur.getEmail() + "</td>" +
                "<td>" + operateur.getDateNaissance() + "</td>" +
                "</tr>" ;
    }

    private void setupHtmlForSignals() {
        Signalement signalement = (Signalement) object;
        html = "<tr>" +
                "<td>" + signalement.getId() + "</td>" +
                "<td>" + signalement.getPlaignant().getNom() + " " + signalement.getPlaignant().getPrenom() + "</td>" +
                "<td>" + signalement.getClient().getNom() + " " + signalement.getClient().getPrenom() + "</td>" +
                "<td>" + signalement.getMotif() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForSignalerClient() {
        Client client = (Client) object;
        html = "<tr>" +
                "<td>" + client.getId() + "</td>" +
                "<td>" + client.getNom() + "</td>" +
                "<td>" + client.getPrenom() + "</td>" +
                "<td>" + client.getTel() + "</td>" +
                "<td>" + client.getAdresse() + "</td>" +
                "<td>" + client.getEmail() + "</td>" +
                "<td>" + client.getDateNaissance() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getClientId("+client.getId()+")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#signalerModal\" value=\""+client.getId()+"\"> Signaler</td>" +
                "</tr>";
    }

    private void setupHtmlForAssignerRegion() {
        Employe agent = (Employe) object;
        Localite localite = new AssignationDAO().getLocaliteByAgent(agent.getId());
        if (localite == null) {
            localite = new Localite();
            localite.setNom("Aucune région assignée");
        }
        html = "<tr>" +
                "<td>" + agent.getId() + "</td>" +
                "<td>" + agent.getNom() + " "+ agent.getPrenom()+"</td>" +
                "<td>" + agent.getTel() + "</td>" +
                "<td>" + agent.getAdresse() + "</td>" +
                "<td>" + agent.getEmail() + "</td>" +
                "<td>" + agent.getDateNaissance() + "</td>" +
                "<td>" + localite.getNom() + "</td>" +
                "<td><button type=\"button\"  onclick=\"getAgentId("+agent.getId()+")\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#myModal\" value=\""+agent.getId()+"\"> Modifier</td>" +
                "</tr>";

    }

    private void setupHtmlForAgent() {
        Employe agent= (Employe) object;
        Localite localite = new AssignationDAO().getLocaliteByAgent(agent.getId());
        if (localite == null) {
            localite = new Localite();
            localite.setNom("Aucune région assignée");
        }
        html = "<tr>" +
                "<td>"
                       + agent.getId() + "</td>" +
                "<td>" + agent.getNom() + " "+ agent.getPrenom()+"</td>" +
                "<td>" + agent.getTel() + "</td>" +
                "<td>" + agent.getAdresse() + "</td>" +
                "<td>" + agent.getEmail() + "</td>" +
                "<td>" + agent.getDateNaissance() + "</td>" +
                "<td>" + localite.getNom() + "</td>" +
                "</tr>" ;
    }

    private void setupHtmlForAdmin() {
        Employe admin = (Employe) object;
        html = "<tr>" +
                "<td>" + admin.getId() + "</td>" +
                "<td>" + admin.getNom() + " " + admin.getPrenom() + "</td>" +
                "<td>" + admin.getTel() + "</td>" +
                "<td>" + admin.getAdresse() + "</td>" +
                "<td>" + admin.getEmail() + "</td>" +
                "<td>" + admin.getDateNaissance() + "</td>" +
                "<td>" + admin.getUserType() + "</td>";
    }

    private void setupHtmlForLocalite() {
        Localite localite = (Localite) object;
        html = "<tr><td>" + localite.getId() + "</td>" +
                "<td>" + localite.getNom() + "</td></tr>";
    }

    private void setupHtmlForEmployee() {
        Employe operateur = (Employe) object;
        Employe creator = (Employe) new EmployeDAO().getById(operateur.getCreator());
        html = "<tr>" +
                "<td>" + operateur.getId() + "</td>" +
                "<td>" + operateur.getNom() + "</td>" +
                "<td>" + operateur.getPrenom() + "</td>" +
                "<td>" + operateur.getTel() + "</td>" +
                "<td>" + operateur.getAdresse() + "</td>" +
                "<td>" + operateur.getEmail() + "</td>" +
                "<td>" + operateur.getDateNaissance() + "</td>" +
                "<td>" + operateur.isSuspendedString() + "</td>" +
                "<td>" + creator.getNom() + " " + creator.getPrenom() + "</td>" +
                "<td>" + operateur.getDateAdded() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForClient() {
        Client client = (Client) object;
        html = "<tr>" +
                "<td>" + client.getId() + "</td>" +
                "<td>" + client.getNom() + "</td>" +
                "<td>" + client.getPrenom() + "</td>" +
                "<td>" + client.getTel() + "</td>" +
                "<td>" + client.getAdresse() + "</td>" +
                "<td>" + client.getEmail() + "</td>" +
                "<td>" + client.getDateNaissance() + "</td>" +
                "<td>" + client.isBannedString() + "</td>" +
                "</tr>";

    }

    private void setupHtmlForMessage() {
        Message message = (Message) object;
        String isClient = message.isClient() ? "Oui" : "Non";
        html = "<tr>" +
                "<td>" + message.getId() + "</td>" +
                "<td>" + message.getEmail() + "</td>" +
                "<td>" + message.getObject() + "</td>" +
                "<td>" + message.getContent() + "</td>" +
                "<td>" + isClient + "</td>" +
                "<td>" + message.getTimestamp() + "</td>" +
                "</tr>";
    }


    private void setupHtmlForVente() {
        Vente vente = (Vente) object;
        double restant = vente.getLogement().getPrix() - new VersementDAO().getSommeVersementsByVente(vente.getId());
        html = "<tr>" +
                "<td>" + vente.getId() + "</td>" +
                "<td>" + vente.getClient().getFullName() + "</td>" +
                "<td>" + vente.getLogement().getTitre() + "</td>" +
                "<td>" + vente.getDate() + "</td>" +
                "<td>" + restant + "</td>" +
                "<td>" + vente.getEtatVente() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForVisite() {
        Visite visite = (Visite) object;
        html = "<tr>" +
                "<td>" + visite.getId() + "</td>" +
                "<td>" + visite.getLogement().getTitre() + "</td>" +
                "<td>" + visite.getAgent().getFullName() + "</td>" +
                "<td>" + visite.getClient().getFullName() + "</td>" +
                "<td>" + visite.getTimestamp() + " | " + Util.getStringFromHorraire(visite.getHorraire()) + "</td>" +
                "<td>" + visite.getEtatVisite() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForLogement() {
        Logement logement = (Logement) object;
        String etat = logement.isGele() ? "Gelé" : "Libre";

        html= "<tr>" +
                "<td>" + logement.getId() + "</td>" +
                "<td>" + logement.getTitre() + "</td>" +
                "<td>" + logement.getDescription() + "</td>" +
                "<td>" + logement.getSuperficie() + " m2</td>" +
                "<td>" + logement.getAdresse() + "</td>" +
                "<td>" + logement.getLocalite().getNom() + "</td>" +
                "<td>" + logement.getPrix() + "</td>" +
                "<td>" + logement.getEtage() + "</td>" +
                "<td>" + etat + "</td>" +
                "</tr>";
    }

    public String getHtml() {
        return html;
    }
}
