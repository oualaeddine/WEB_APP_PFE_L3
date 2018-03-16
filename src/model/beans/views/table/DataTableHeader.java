package model.beans.views.table;

import model.enums.TablePage;

public class DataTableHeader {
    private String html;
    private TablePage currentPage;

    public String getHtml() {
        setupTableHeader();
        return html;
    }

    private void setupTableHeader() {
        switch (currentPage) {
            case LOGEMENTS:
                setupTableHeaderForLogements();
                break;
            case LOGEMENTS_FOR_USER:
                setupTableHeaderForLogements();
                break;
            case LOGEMENTS_VENDUS:
                setupTableHeaderForLogements();
                break;
            case FROZEN_LOGEMENTS:
                setupTableHeaderForLogements();
                break;
            case LOGEMENTS_NON_VENDUS:
                setupTableHeaderForLogements();
                break;
            case AGENTS:
                setupTableHeaderForEmployees();
                break;
            case SUSPENDED_AGENTS:
                setupTableHeaderForEmployees();
                break;
            case OPERATEURS:
                setupTableHeaderForEmployees();
                break;
            case SUSPENDED_OPERATEURS:
                setupTableHeaderForEmployees();
                break;
            case RESPONSABLES_VENTES:
                setupTableHeaderForEmployees();
                break;
            case SUSPENDED_RESPONSABLES_VENTES:
                setupTableHeaderForEmployees();
                break;
            case VISITES:
                setupTableHeaderForVisites();
                break;
            case REPORTED_VISITES:
                setupTableHeaderForVisites();
                break;
            case CANCELED_VISITES:
                setupTableHeaderForVisites();
                break;
            case USER_VISITES:
                setupTableHeaderForVisites();
                break;
            case PROGRAMMED_VISITES:
                setupTableHeaderForVisites();
                break;
            case PASSED_VISITS:
                setupTableHeaderForVisites();
                break;
            case ADMINS:
                setupTableHeaderForAdmins();
                break;
            case LOCALITES:
                setupTableHeaderForLocalite();
                break;
            case CLIENTS:
                setupTableHeaderForClients();
                break;
            case CLIENTS_FOR_USER:
                setupTableHeaderForClients();
                break;
            case BANNED_CLIENTS:
                setupTableHeaderForClients();
                break;
            case MESSAGES:
                setupTableHeaderForMessages();
                break;
            case ADMINISTRATION_MESSAGES_FOR_USER:
                setupTableHeaderForMessages();
                break;
            case CLIENTS_MESSAGES_FOR_USER:
                setupTableHeaderForMessages();
                break;
        }
    }

    private void setupTableHeaderForMessages() {
        html = "";

    }

    private void setupTableHeaderForClients() {
        html =  "<td><b>ID</b></td>" +
                "<td><b>Nom</b></td>" +
                "<td><b>Prenom</b></td>" +
                "<td><b>Telephone</b></td>" +
                "<td><b>Adresse</b></td>" +
                "<td><b>email</b></td>" +
                "<td><b>Date de naissance</b></td>";

    }

    private void setupTableHeaderForLocalite() {
        html =  "<td><b>ID</b></td>" +
                "<td><b>Nom</b></td>";

    }

    private void setupTableHeaderForAdmins() {
        html = "<td><b>ID</b></td>" +
                "<td><b>Nom</b></td>" +
                "<td><b>Prenom</b></td>" +
                "<td><b>Telephone</b></td>" +
                "<td><b>Adresse</b></td>" +
                "<td><b>email</b></td>" +
                "<td><b>Date de naissance</b></td>" +
                "<td><b>Role</b></td>";

    }

    private void setupTableHeaderForVisites() {
        html = "<td><b>ID</b></td>" +
                "<td><b>Logement</b></td>" +
                "<td><b>Agent</b></td>" +
                "<td><b>Client</b></td>" +
                "<td><b>Horaire</b></td>" +
                "<td><b>Etat</b></td>" ;

    }

    private void setupTableHeaderForEmployees() {
        html = "<td><b>ID</b></td>" +
                "<td><b>Nom</b></td>" +
                "<td><b>Prenom</b></td>" +
                "<td><b>Telephone</b></td>" +
                "<td><b>Adresse</b></td>" +
                "<td><b>email</b></td>" +
                "<td><b>Date de naissance</b></td>" +
                "<td><b>Etat</b></td>" +
                "<td><b>Approuver par</b></td>"+
                "<td><b>Date d'ajout</b></td>";
    }

    private void setupTableHeaderForLogements() {
        html =
                "                        <tr>\n" +
                "                            <th>ID</th>\n" +
                "                            <th>Titre</th>\n" +
                "                            <th>Description</th>\n" +
                "                            <th>Superficie</th>\n" +
                "                            <th>Adresse</th>\n" +
                "                            <th>Localité</th>\n" +
                "                            <th>Prix</th>\n" +
                "                            <th>Etage</th>\n" +
                "                            <th>Etat</th>\n" +
                "                        </tr>";
    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }
}
