package model.beans.views.table;

import model.beans.*;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.*;
import model.enums.DataTableRowFormat;
import model.enums.TablePage;
import model.enums.UserType;

import java.util.LinkedList;

public class DataTableData {
    private LinkedList<DataTableRow> data;
    private TablePage currentPage;
    private int userId;
    private UserType userType;


    public LinkedList<DataTableRow> getData() {

        setupData();
        return data;
    }

    public DataTableData() {
        data = new LinkedList<>();
    }

    private void setupData() {
        switch (currentPage) {
            case RAPPORTS_LIST:
                setupDataMesRapports();
                break;
            case CLIENT_MES_LOGEMENT_VISITES:
                setupDataLogementsVisites();
                break;
            case CLIENT_MY_VISITS:
                setupDataVisitsForClient();
                break;
            case AJOUTER_VERSEMENT:
                setupDataClientsList();
                break;
            case MODIFIER_VISITE:
                setupDataModifierVisite();
                break;
            case SIGNALEMENT:
                setupDataSignalementList();
                break;
            case SIGNALER_CLIENT:
                setupDataNotBannedClientList();
                break;
            case CLIENTS_FOR_AGENT:
                setupDataClientsForAgent();
                break;
            case ASSIGNER_REGION:
                setupDataAssignerRegion();
                break;
            case LOGEMENTS:
                setupDataAllLogements();
                break;
            case LOGEMENTS_FOR_USER:
                setupDataLogementsForUser();
                break;
            case LOGEMENTS_VENDUS:
                setupDataLogementsVendus();
                break;
            case FROZEN_LOGEMENTS:
                setupDataLogementsGeles();
                break;
            case LOGEMENTS_NON_VENDUS:
                setupDataLogementsNonVendus();
                break;
            case EMPLOYES:
                break;
            case AGENTS:
                setupDataAgentList();
                break;
            case SUSPENDED_AGENTS:
                setupDataSuspendedAgents();
                break;
            case OPERATEURS:
                setupDataOperateurList();
                break;
            case SUSPENDED_OPERATEURS:
                setupDataSuspendedOperateurs();
                break;
            case RESPONSABLES_VENTES:
                setupDataResVenteListe();
                break;
            case SUSPENDED_RESPONSABLES_VENTES:
                setupDataSuspendedResVente();
                break;
            case VISITES:
                setupDataVisitsList();
                break;
            case REPORTED_VISITES:
                setupDataReportedVisits();
                break;
            case CANCELED_VISITES:
                setupDataCanceledVisits();
                break;
            case PASSED_VISITS:
                setupDataPassedVisits();
                break;
            case AGENT_VISITES:
                setupDataAgentVisites();
                break;
            case PROGRAMMED_VISITES:
                setupDataProgrammedVisits();
                break;
            case ADMINS:
                setupDataAdminsList();
                break;
            case NEW_MESSAGE:
                break;
            case LOCALITES:
                setupDataLocaliteList();
                break;
            case PLAINTES:
                setupDataListPlaintes();
                break;
            case VENTES:
                setupDataVentes();
                break;
            case CONFIRMED_VENTES:
                setupDataConfirmedVentes();
                break;
            case CLIENTS:
                setupDataClientsList();
                break;
            case CLIENTS_FOR_USER:
                break;
            case BANNED_CLIENTS:
                setupDataBannedClients();
                break;
            case MESSAGES_FOR_ADMIN:
                break;
            case MESSAGES:
                break;
            case ADMINISTRATION_MESSAGES_FOR_USER:
                break;
            case ADMINISTRATION_MESSAGES_FOR_EMPLOYEES:
                break;
            case CLIENTS_MESSAGES_FOR_USER:
                break;
            case APPROUVER_EMPLOYE:
                setupDataApprouverEmploye();
                break;
            case SUSPENDRE_EMPLOYE:
                setupDataGetAllEmployees();
                break;
            case SUSPENDRE_ADMIN:
                setupDataSuspendreAdmin();
                break;
            case GELER_LOGEMENT:
                setupDataGelerLogement();
                break;
            case ETABLIR_RAPPORT:
                setupDataEtablirRapport();
                break;
            case CLIENT_MES_VENTES_EN_COURS:
                setupDataVentesEnCoursForClient();
                break;
            case MODIFIER_PROFIL:
                break;
            case CHANGER_MOT_DE_PASSE:
                break;
            case LOGOUT:
                break;
            case NOUVELLE_VENTE:
                break;
            case VENTES_EN_COURS:
                setupDataVentesEnCours();
                break;
            case VENTES_ANNULEES:
                setupDataVentesAnnulees();
                break;
            case VERSEMENTS_FOR_VENTE:
                setupDataVentes();
                break;
            case VERSEMENTS_FOR_USER:
                break;
            case CLIENT_MY_NOTIFICATIONS:
                setupDataClientNotifications();
                break;
            case MY_CANCELED_VISITS:
                setupDataCanceledVisitsForAgent();
                break;
            case MY_PASSED_VISITS:
                setupDataPassedVisitsForAgent();
                break;
            case EMPLOYEE_NOTIFICATIONS:
                setupDataNotificationsForEmployee();
                break;
            case BANNIR_CLIENT:
                setupDataBannirClient();
                break;
        }
    }

    private void setupDataClientNotifications() {
        LinkedList<Notification> notifications = new ClientNotificationDAO().getByClient(userId);
        for (Notification notification : notifications) {
            data.add(new DataTableRow(DataTableRowFormat.NOTIFICATIONS, notification));
        }
    }

    private void setupDataListPlaintes() {
        LinkedList<Plainte> plaintes = new PlainteDAO().getAll();
        for (Plainte plainte : plaintes) {
            data.add(new DataTableRow(DataTableRowFormat.PLAINTE, plainte));
        }
    }

    private void setupDataBannirClient() {
        LinkedList<Client> list = new ClientDAO().getAll();
        for (Client client : list) {
            data.add(new DataTableRow(DataTableRowFormat.BANNIR_CLIENT, client));
        }
    }

    private void setupDataNotificationsForEmployee() {
        LinkedList<Notification> notifications = new EmployeNotificationDAO().getByEmployee(userId);
        for (Notification notification : notifications) {
            data.add(new DataTableRow(DataTableRowFormat.NOTIFICATIONS, notification));
        }
    }

    private void setupDataPassedVisitsForAgent() {
        LinkedList<Visite> visites = new VisitesDao().getPasseeForAgent(userId);
        for (Visite visite : visites) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataCanceledVisitsForAgent() {
        LinkedList<Visite> visites = new VisitesDao().getAnnuleesForAgent(userId);
        for (Visite visite : visites) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataMesRapports() {
        LinkedList<Rapport> rapports = new RapportDAO().getByAgent(userId);
        for (Rapport rapport : rapports) {
            data.add(new DataTableRow(DataTableRowFormat.RAPPORT, rapport));
        }
    }

    private void setupDataVentesEnCours() {
        LinkedList<Vente> ventes = new VentesDAO().getEnCours();
        for (Vente v : ventes) {
            data.add(new DataTableRow(DataTableRowFormat.VENTE, v));
        }
    }

    private void setupDataVentesAnnulees() {
        LinkedList<Vente> ventes = new VentesDAO().getAnnulees();
        for (Vente v : ventes) {
            data.add(new DataTableRow(DataTableRowFormat.VENTE, v));
        }
    }

    private void setupDataLogementsVisites() {
        LinkedList<Logement> logements = new LogementDAO().getLogementsVisitesByUser(userId);
        for (Logement logement : logements) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    private void setupDataVentesEnCoursForClient() {
        Client client = (Client) new ClientDAO().getById(userId);
        LinkedList<Vente> ventes = new VentesDAO().getEnCoursForClient(client);
        for (Vente v : ventes) {
            data.add(new DataTableRow(DataTableRowFormat.VENTE, v));
        }
    }

    private void setupDataVisitsForClient() {
        LinkedList<Visite> visites = new VisitesDao().getVisitesByClient(userId);
        for (Visite visite : visites) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataEtablirRapport() {
        LinkedList<Visite> visites = new VisitesDao().getNonReporteesForAgent(userId);
        for (Visite visite : visites) {
            data.add(new DataTableRow(DataTableRowFormat.ETABLIR_RAPPORT, visite));
        }
    }

    private void setupDataModifierVisite() {
        LinkedList<Visite> visites = new VisitesDao().getProgrammee();
        for (Visite visite : visites) {
            data.add(new DataTableRow(DataTableRowFormat.MODIFIER_VISITE, visite));
        }
    }

    private void setupDataGelerLogement() {
        LinkedList<Logement> list = new LogementDAO().getNonVendus();
        for (Logement logement : list) {
            data.add(new DataTableRow(DataTableRowFormat.GELER_LOGEMENT, logement));
        }
    }

    private void setupDataSuspendreAdmin() {
        LinkedList<Employe> list = new EmployeDAO().getAllAdmins();
        for (Employe employe : list) {
            data.add(new DataTableRow(DataTableRowFormat.SUSPENDRE_EMPLOYE, employe));
        }
    }

    private void setupDataGetAllEmployees() {
        LinkedList<Employe> agents = new EmployeDAO().getAllAgents();
        LinkedList<Employe> operateurs = new EmployeDAO().getAllOperateurs();
        LinkedList<Employe> resventes = new EmployeDAO().getAllResVentes();
        for (Employe employe : agents) {
            data.add(new DataTableRow(DataTableRowFormat.SUSPENDRE_EMPLOYE, employe));
        }
        for (Employe employe : operateurs) {
            data.add(new DataTableRow(DataTableRowFormat.SUSPENDRE_EMPLOYE, employe));
        }
        for (Employe employe : resventes) {
            data.add(new DataTableRow(DataTableRowFormat.SUSPENDRE_EMPLOYE, employe));
        }
    }

    private void setupDataApprouverEmploye() {
        LinkedList<Employe> list = new EmployeDAO().getNotApprovedEmployees();
        for (Employe employe : list) {
            data.add(new DataTableRow(DataTableRowFormat.APPROUVER_EMPLOYE, employe));
        }
    }

    private void setupDataSignalementList() {
        LinkedList<Signalement> list = new SignalementDAO().getAll();
        for (Signalement signalement : list) {
            data.add(new DataTableRow(DataTableRowFormat.SIGNALS, signalement));
        }
    }

    private void setupDataNotBannedClientList() {
        LinkedList<Client> list = new ClientDAO().getNotBannedClients();
        for (Client client : list) {
            data.add(new DataTableRow(DataTableRowFormat.SIGNALER, client));
        }
    }

    private void setupDataClientsForAgent() {
        LinkedList<Client> clients = new ClientDAO().getClientsForAgent(userId);
        for (Client client : clients) {
            data.add(new DataTableRow(DataTableRowFormat.CLIENT, client));
        }
    }


    private void setupDataAssignerRegion() {
        for (Employe agent : new EmployeDAO().getAllAgents()) {
            data.add(new DataTableRow(DataTableRowFormat.ASSIGNER_REGION, agent));
        }
    }


    private void setupDataAgentVisites() {
        Employe agent =(Employe) new EmployeDAO().getById(userId);
        LinkedList<Visite> list = new VisitesDao().getVisitesByAgent(agent);
        for (Visite visite : list) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataConfirmedVentes() {
        LinkedList<Vente> list = new VentesDAO().getConfirmed();
        for (Vente vente : list) {
            data.add(new DataTableRow(DataTableRowFormat.VENTE, vente));
        }
    }

    private void setupDataVentes() {
        LinkedList<Vente> list = new VentesDAO().getAll();
        for (Vente vente : list) {
            data.add(new DataTableRow(DataTableRowFormat.VENTE, vente));
        }
    }

    private void setupDataPassedVisits() {
        LinkedList<Visite> list = new VisitesDao().getPassee();
        for (Visite visite : list) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataBannedClients() {
        for (Client client : new ClientDAO().getBannedClients()) {
            data.add(new DataTableRow(DataTableRowFormat.CLIENT, client));
        }
    }

    private void setupDataClientsList() {
        for (Object client : new ClientDAO().getAll()) {
            data.add(new DataTableRow(DataTableRowFormat.CLIENT, (Client) client));
        }
    }

    private void setupDataLocaliteList() {
        for (Object localite : new LocaliteDAO().getAll()) {
            data.add(new DataTableRow(DataTableRowFormat.LOCALITE, (Localite) localite));
        }
    }

    private void setupDataAdminsList() {
        for (Employe admin : new EmployeDAO().getAllAdmins()) {
            data.add(new DataTableRow(DataTableRowFormat.ADMIN, admin));
        }
    }

    private void setupDataProgrammedVisits() {
        LinkedList<Visite> list = new VisitesDao().getProgrammee();
        for (Visite visite : list) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataCanceledVisits() {
        for (Visite visite : new VisitesDao().getAnnulee()) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataReportedVisits() {
        for (Visite visite : new VisitesDao().getReportee()) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataVisitsList() {
        for (Visite visite : new VisitesDao().getAll()) {
            data.add(new DataTableRow(DataTableRowFormat.VISITE, visite));
        }
    }

    private void setupDataSuspendedResVente() {
        for (Employe responsableVente : new EmployeDAO().getSuspendedResVente()) {
            data.add(new DataTableRow(DataTableRowFormat.RESPONSABLE_VENTES, responsableVente));
        }
    }

    private void setupDataResVenteListe() {
        for (Object resvente : new EmployeDAO().getAllResVentes()) {
            data.add(new DataTableRow(DataTableRowFormat.RESPONSABLE_VENTES, resvente));
        }
    }

    private void setupDataSuspendedOperateurs() {
        for (Employe operateur : new EmployeDAO().getSuspendedOperateurs()) {
            data.add(new DataTableRow(DataTableRowFormat.OPERATEUR, operateur));
        }
    }

    private void setupDataOperateurList() {
        for (Object operateur : new EmployeDAO().getAllOperateurs()) {
            data.add(new DataTableRow(DataTableRowFormat.OPERATEUR, operateur));
        }
    }

    private void setupDataSuspendedAgents() {
        for (Employe agent : new EmployeDAO().getSuspendedAgents()) {
            data.add(new DataTableRow(DataTableRowFormat.AGENT,agent));
        }
    }


    private void setupDataAgentList() {
        for (Employe agent : new EmployeDAO().getAllAgents()) {
            data.add(new DataTableRow(DataTableRowFormat.AGENT, agent));
        }
    }

    private void setupDataLogementsNonVendus() {
        for (Logement logement : new LogementDAO().getNonVendus()) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    private void setupDataLogementsGeles() {
        for (Logement logement : new LogementDAO().getGeles()) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    private void setupDataLogementsVendus() {
        for (Logement logement : new LogementDAO().getLogementsVendus()) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    private void setupDataLogementsForUser() {
        LinkedList<Logement> logements = new LogementDAO().getLogementsForAgent(userId);
        for (Logement logement : logements) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    private void setupDataAllLogements() {
        data = new LinkedList<>();
        for (Logement logement : new LogementDAO().getAll()) {
            data.add(new DataTableRow(DataTableRowFormat.LOGEMENT, logement));
        }
    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
