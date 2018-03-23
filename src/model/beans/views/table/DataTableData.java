package model.beans.views.table;

import model.beans.Localite;
import model.beans.Logement;
import model.beans.Vente;
import model.beans.Visite;
import model.beans.humans.*;
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
            case ASSIGNER_REGION:
                setupDataAgentList();
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
            case LOCALITES:
                setupDataLocaliteList();
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
                //todo: hedi teni
                break;
            case BANNED_CLIENTS:
                setupDataBannedClients();
                break;
            case MESSAGES:
                break;
            case ADMINISTRATION_MESSAGES_FOR_USER:
                break;
            case CLIENTS_MESSAGES_FOR_USER:
                break;
            
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
            data.add(new DataTableRow(DataTableRowFormat.ASSIGNER_REGION, agent));
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
        for (Logement logement : new LogementDAO().getLogementsForUser(userType, userId)) {
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
