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
            case SIGNALEMENT:
                setupTableHeaderForPlainte();
                break;
            case PLAINTES:
                setupTableHeaderForPlainte();
                break;
            case SIGNALER_CLIENT:
                setupTableHeaderForSignalerClient();
                break;
            case ASSIGNER_REGION:
                setupTableHeaderForAssignerRegion();
                break;
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
                setupTableHeaderForAgents();
                break;
            case SUSPENDED_AGENTS:
                setupTableHeaderForEmployees();
                break;
            case OPERATEURS:
                setupTableHeaderForOperateurs();
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
            case AGENT_VISITES:
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
            case CLIENTS_FOR_AGENT:
                setupTableHeaderForClientsForAgent();
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
            case VENTES:
                setupTableHeaderForVentes();
                break;
        }
    }

    private void setupTableHeaderForOperateurs() {
        html = "<tr><td>ID</th>" +
                "<th>Nom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "</tr>";
    }

    private void setupTableHeaderForPlainte() {
        html = "<tr>" +
                "<th>Plaignant</th>" +
                "<th>Sujet de la plainte</th>" +
                "<th>Motif</th>" +
                "</tr>";
    }

    private void setupTableHeaderForSignalerClient() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom   </th>" +
                "<th>Prenom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Action</th>" +
                "</tr>";
    }

    private void setupTableHeaderForClientsForAgent() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom   </th>" +
                "<th>Prenom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Etat</th>" +
                "<tr>";
    }

    private void setupTableHeaderForAssignerRegion() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom              </th>" +
                "<th>Telephone        </th>" +
                "<th>Adresse          </th>" +
                "<th>email            </th>" +
                "<th>Date de naissance</th>" +
                "<th>Région           </th>" +
                "<th> Assigner        </th>" +
                "</tr>" ;
    }

    private void setupTableHeaderForVentes() {
        html =
                "                        <tr>\n" +
                        "                            <th>ID</th>\n" +
                        "                            <th>Agent</th>\n" +
                        "                            <th>Responsable vente</th>\n" +
                        "                            <th>Client</th>\n" +
                        "                            <th>Logement</th>\n" +
                        "                            <th>Date</th>\n" +
                        "                            <th>Etat</th>\n" +
                        "                        </tr>";
    }

    private void setupTableHeaderForAgents() {
        html =  "<tr><th>ID</th>" +
                "<th>Nom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Localité</th></tr>" ;
    }

    private void setupTableHeaderForMessages() {
        html = "";

    }

    private void setupTableHeaderForClients() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom</th>" +
                "<th>Prenom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Etat</th></tr>" ;
    }

    private void setupTableHeaderForLocalite() {
        html =  "<tr>" +
                "<th>ID</th>" +
                "<th>Nom</th></tr>";

    }

    private void setupTableHeaderForAdmins() {
        html = "<tr><td><b>ID</b></th>" +
                "<th>Nom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Role</th></tr>";

    }

    private void setupTableHeaderForVisites() {
        html = "<tr><th>ID</th>" +
                "<th>Logement</th>" +
                "<th>Agent</th>" +
                "<th>Client</th>" +
                "<th>Horaire</th>" +
                "<th>Etat</th></tr>" ;

    }

    private void setupTableHeaderForEmployees() {
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Nom</th>" +
                "<th>Prenom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Etat</th>" +
                "<th>Approuvé par</th>"+
                "<th>Date d'ajout</th></tr>";
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
