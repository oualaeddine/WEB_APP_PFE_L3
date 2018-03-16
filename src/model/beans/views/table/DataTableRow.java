package model.beans.views.table;

import model.beans.Logement;
import model.beans.Message;
import model.beans.Vente;
import model.beans.Visite;
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
            case LOGEMENT:
                setupHtmlForLogement();
                break;
            case ADMIN:
                setupHtmlForLogement();
                break;
            case AGENT:
                setupHtmlForEmployee();
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

    private void setupHtmlForEmployee() {
        Employe operateur = (Employe) object;
        html = "<td><b>" + operateur.getId() + "</td>" +
                "<td>" + operateur.getNom() + "</td>" +
                "<td>" + operateur.getPrenom() + "</td>" +
                "<td>" + operateur.getTel() + "</td>" +
                "<td>" + operateur.getAdresse() + "</td>" +
                "<td>" + operateur.getEmail() + "</td>" +
                "<td>" + operateur.getDateNaissance() + "</td>" +
                "<td>" + operateur.isSuspendedString() + "</td>" +
                "<td>" + operateur.getCreator().getNom() + " " + operateur.getCreator().getPrenom() + "</td>" +
                "<td>" + operateur.getDateAdded() + "</b></td>";
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
                "<td>" + client.getDateAdded() + "</td>" +
                "</tr>";

    }

    private void setupHtmlForMessage() {
        Message message = (Message) object;
    }


    private void setupHtmlForVente() {
        Vente vente = (Vente) object;
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
