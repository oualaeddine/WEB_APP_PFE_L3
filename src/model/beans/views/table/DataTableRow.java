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
        html = "<td>" + operateur.getId() + "</td>" +
                "<td>" + operateur.getNom() + "</td>" +
                "<td>" + operateur.getPrenom() + "</td>" +
                "<td>" + operateur.getTel() + "</td>" +
                "<td>" + operateur.getAdresse() + "</td>" +
                "<td>" + operateur.getEmail() + "</td>" +
                "<td>" + operateur.getDateNaissance() + "</td>" +
                "<td>" + operateur.isSuspendedString() + "</td>" +
                "<td>" + operateur.getCreator().getNom() + " " + operateur.getCreator().getPrenom() + "</td>" +
                "<td>" + operateur.getDateAdded() + "</td>";
    }

    private void setupHtmlForClient() {
        Client client = (Client) object;
        html = "<td>" + client.getId() + "</td>" +
                "<td>" + client.getNom() + "</td>" +
                "<td>" + client.getPrenom() + "</td>" +
                "<td>" + client.getTel() + "</td>" +
                "<td>" + client.getAdresse() + "</td>" +
                "<td>" + client.getEmail() + "</td>" +
                "<td>" + client.getDateNaissance() + "</td>" +
                "<td>" + client.isBannedString() + "</td>" +
                "<td>" + client.getDateAdded() + "</td>";
    }

    private void setupHtmlForMessage() {
        Message message = (Message) object;
    }


    private void setupHtmlForVente() {
        Vente vente = (Vente) object;
    }

    private void setupHtmlForVisite() {
        Visite visite = (Visite) object;
    }

    private void setupHtmlForLogement() {
        Logement logement = (Logement) object;
    }

    public String getHtml() {
        return html;
    }
}
