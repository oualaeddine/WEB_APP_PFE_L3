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
            case MESSAGES_FOR_ADMIN:
                setupTableHeaderForMessages();
                break;
            case ANNULER_VENTE:
                setupTableHeaderForAnnulerVentes();
                break;
            case RAPPORTS_LIST:
                setupTableHeaderForRapport();
                break;
            case VENTES_EN_COURS:
                setupTableHeaderForVentes();
                break;
            case VENTES_ANNULEES:
                setupTableHeaderForVentes();
                break;
            case CONFIRMED_VENTES:
                setupTableHeaderForVentes();
                break;
            case VERSEMENTS_FOR_VENTE:
                setupTableHeaderForVentes();
                break;
            case CLIENT_MY_NOTIFICATIONS:
                setupTableHeaderForNotifications();
                break;
            case CLIENT_MES_LOGEMENT_VISITES:
                setupTableHeaderForLogements();
                break;
            case CLIENT_MES_VENTES_EN_COURS:
                setupTableHeaderForVentes();
                break;
            case CLIENT_MY_VISITS:
                setupTableHeaderForVisites();
                break;
            case AJOUTER_VERSEMENT:
                setupTableHeaderForClients();
                break;
            case GELER_LOGEMENT:
                setupTableHeaderForGelerLogement();
                break;
            case SUSPENDRE_ADMIN:
                setupTableHeaderForSuspendreEmploye();
                break;
            case SIGNALEMENT:
                setupTableHeaderForPlainte();
                break;
            case PLAINTES:
                setupTableHeaderForSignalement();
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
            case APPROUVER_EMPLOYE:
                setupTableHeaderForApprouverEmploye();
                break;
            case SUSPENDRE_EMPLOYE:
                setupTableHeaderForSuspendreEmploye();
                break;
            case MODIFIER_VISITE:
                setupTableHeaderForModifierVisite();
                break;
            case ETABLIR_RAPPORT:
                setupTableHeaderForEtablirRapport();
                break;
            case MY_CANCELED_VISITS:
                setupTableHeaderForVisites();
                break;
            case MY_PASSED_VISITS:
                setupTableHeaderForVisites();
                break;
            case EMPLOYEE_NOTIFICATIONS:
                setupTableHeaderForNotifications();
                break;
            case BANNIR_CLIENT:
                setupTableHeaderForSignalerClient();
                break;
        }
    }

    private void setupTableHeaderForAnnulerVentes() {
        html =
                "<tr>\n" +
                        "    <th>ID</th>\n" +
                        "    <th>Client</th>\n" +
                        "    <th>Logement</th>\n" +
                        "    <th>Date de début</th>\n" +
                        "    <th>Reste à payer</th>" +
                        "    <th>Etat</th>\n" +
                        "    <th>Action</th>" +
                        "</tr>";
    }

    private void setupTableHeaderForSignalement() {
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Plaignant</th>" +
                "<th>Contenu</th>" +
                "<th>Date</th>" +
                "</tr>";
    }


    private void setupTableHeaderForNotifications() {
        html = "<tr>" +
                "<th> ID </th>" +
                "<th> Contenu </th>" +
                "<th> Date et heure de reception </th>" +
                "</tr>";
    }

    private void setupTableHeaderForRapport() {
        html = "<tr>" +
                "<th>ID Visite </th>" +
                "<th>Visite </th>" +
                "<th>Logement </th>" +
                "<th>Client    </th>" +
                "<th>Etat client   </th>" +
                "<th>Avis  </th>" +
                "<th>Commentaire  </th>" +
                "</tr>";
    }

    private void setupTableHeaderForEtablirRapport() {
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Logement </th>" +
                "<th>Agent    </th>" +
                "<th>Client   </th>" +
                "<th>Horaire  </th>" +
                "<th>Etat     </th>" +
                "<th>Action   </th>" +
                "</tr>" ;
    }

    private void setupTableHeaderForModifierVisite() {
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Logement </th>" +
                "<th>Agent    </th>" +
                "<th>Client   </th>" +
                "<th>Horaire  </th>" +
                "<th>Etat     </th>" +
                "<th>Action   </th>" +
                "</tr>" ;
    }

    private void setupTableHeaderForGelerLogement() {
        html =
                "<tr>\n" +
                "    <th>ID</th>\n" +
                "    <th>Titre</th>\n" +
                "    <th>Description</th>\n" +
                "    <th>Superficie</th>\n" +
                "    <th>Adresse</th>\n" +
                "    <th>Localité</th>\n" +
                "    <th>Prix</th>\n" +
                "    <th>Etage</th>\n" +
                "    <th>Etat</th>\n" +
                "    <th>Action</th>" +
                "</tr>";
    }


    private void setupTableHeaderForSuspendreEmploye() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom et prénom </th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>Email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Type</th>" +
                "<th>Action</th>" +
                "</tr>";
    }

    private void setupTableHeaderForApprouverEmploye() {
        html =  "<tr><td>ID</th>" +
                "<th>Nom et prénom </th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>Email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Action</th>" +
                "</tr>";
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
                "<th>Nom  </th>" +
                "<th>Prenom</th>" +
                "<th>Telephone</th>" +
                "<th>Adresse</th>" +
                "<th>email</th>" +
                "<th>Date de naissance</th>" +
                "<th>Etat</th>" +
                "</tr>";
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
                "<tr>\n" +
                        "    <th>ID</th>\n" +
                        "    <th>Client</th>\n" +
                        "    <th>Logement</th>\n" +
                        "    <th>Date de début</th>\n" +
                        "    <th>Reste à payer</th>" +
                        "    <th>Etat</th>\n" +
                        "</tr>";
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
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Email</th>" +
                "<th>Objet</th>" +
                "<th>Contenu</th>" +
                "<th>Client</th>" +
                "<th>Date et heure de réception</th>" +
                "</tr>";
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
        html = "<tr>" +
                "<th>ID</th>" +
                "<th>Logement</th>" +
                "<th>Agent</th>" +
                "<th>Client</th>" +
                "<th>Horaire</th>" +
                "<th>Etat</th>" +
                "</tr>";
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
