package model.beans.views.table;

import model.beans.*;
import model.beans.humans.*;
import model.enums.DataTableRowFormat;

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
                setupHtmlForEmployee();
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
        }
    }

    private void setupHtmlForAgent() {
        Agent agent= (Agent) object;
        html = "<tr>" +
                "<td><b>"
                       + agent.getId() + "</td>" +
                "<td>" + agent.getNom() + " "+ agent.getPrenom()+"</td>" +
                "<td>" + agent.getTel() + "</td>" +
                "<td>" + agent.getAdresse() + "</td>" +
                "<td>" + agent.getEmail() + "</td>" +
                "<td>" + agent.getDateNaissance() + "</td>" +
                "<td>" + agent.getLocalite().getNom() + "</td></tr>" ;
    }

    private void setupHtmlForAdmin() {
        Admin admin = (Admin) object;
        html = "<tr>" +
                "<td><b>" + admin.getId() + "</td>" +
                "<td>" + admin.getNom() + " "+ admin.getPrenom()+"</td>" +
                "<td>" + admin.getTel() + "</td>" +
                "<td>" + admin.getAdresse() + "</td>" +
                "<td>" + admin.getEmail() + "</td>" +
                "<td>" + admin.getDateNaissance() + "</td>" +
                "<td>" + admin.getRole() + "</td>" ;
    }

    private void setupHtmlForLocalite() {
        Localite localite = (Localite) object;
        html= "<tr><td><b>" + localite.getId() + "</td>" +
              "<td>" + localite.getNom() + "</td></tr>";
    }

    private void setupHtmlForEmployee() {
        Employe operateur = (Employe) object;
        html = "<tr><td><b>" + operateur.getId() + "</td>" +
                "<td>" + operateur.getNom() + "</td>" +
                "<td>" + operateur.getPrenom() + "</td>" +
                "<td>" + operateur.getTel() + "</td>" +
                "<td>" + operateur.getAdresse() + "</td>" +
                "<td>" + operateur.getEmail() + "</td>" +
                "<td>" + operateur.getDateNaissance() + "</td>" +
                "<td>" + operateur.isSuspendedString() + "</td>" +
                "<td>" + operateur.getCreator().getNom() + " " + operateur.getCreator().getPrenom() + "</td>" +
                "<td>" + operateur.getDateAdded() + "</b></td></tr>";
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
    }


    private void setupHtmlForVente() {
        Vente vente = (Vente) object;
        html="<tr>" +
                "<td>"+vente.getId()+"</td>"+
                "<td>"+vente.getAgent().getNom()+" "+vente.getAgent().getPrenom()+"</td>"+
                "<td>"+vente.getResponsableVente().getNom()+" "+vente.getResponsableVente().getPrenom() +"</td>"+
                "<td>"+vente.getClient().getNom()+" "+vente.getClient().getPrenom() +"</td>"+
                "<td>"+vente.getLogement().getTitre()+"</td>"+
                "<td>"+vente.getDate()+"</td>"+
                "<td>"+vente.getEtatVente()+"</td>"+

            "</tr>";

    }

    private void setupHtmlForVisite() {
        Visite visite = (Visite) object;
        html ="<tr>" +
                "<td>" + visite.getId() + "</td>" +
                "<td>" + visite.getLogement().getTitre() + "</td>" +
                "<td>" + visite.getAgent().getNom()+" "+visite.getAgent().getPrenom() + "</td>" +
                "<td>" + visite.getClient().getNom()+" "+visite.getClient().getPrenom()+ "</td>" +
                "<td>" + visite.getTime() + "</td>" +
                "<td>" + visite.getEtatVisite() + "</td>" +
                "</tr>";
    }

    private void setupHtmlForLogement() {
        Logement logement = (Logement) object;

        html= "<tr>" +
                "<td>" + logement.getId() + "</td>" +
                "<td>" + logement.getTitre() + "</td>" +
                "<td>" + logement.getDescription() + "</td>" +
                "<td>" + logement.getSuperficie() + " m2</td>" +
                "<td>" + logement.getAdresse() + "</td>" +
                "<td>" + logement.getLocalite().getNom() + "</td>" +
                "<td>" + logement.getPrix() + "</td>" +
                "<td>" + logement.getEtage() + "</td>" +
                "<td>" + logement.getEtat() + "</td>" +
                "</tr>";
    }

    public String getHtml() {
        return html;
    }
}
