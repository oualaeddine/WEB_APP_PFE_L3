package model.beans.views;

import model.beans.Logement;
import model.db.daos.LogementDAO;
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

    private void setupData() {// TODO: 2/18/2018 complete this method
        switch (currentPage) {
            case LOGEMENTS:
                // TODO: 2/18/2018 selon la page on effecte les données depuis le dao like this
                setupDataAllLogements();
                break;
            case LOGEMENTS_FOR_USER:
                // TODO: 2/18/2018 selon la page on effecte les données depuis le dao like this
                setupDataLogementsForUser();
                break;
            case LOGEMENTS_VENDUS:
                setupDataLogementsVendus();
                break;
            case FROZEN_LOGEMENTS:
                break;
            case LOGEMENTS_NON_VENDUS:
                break;
            case AGENTS:
                break;
            case SUSPENDED_AGENTS:
                break;
            case OPERATEURS:
                break;
            case SUSPENDED_OPERATEURS:
                break;
            case RESPONSABLES_VENTES:
                break;
            case SUSPENDED_RESPONSABLES_VENTES:
                break;
            case VISITES:
                break;
            case REPORTED_VISITES:
                break;
            case CANCELED_VISITES:
                break;
            case USER_VISITES:
                break;
            case PROGRAMMED_VISITES:
                break;
            case ADMINS:
                break;
            case LOCALITES:
                break;
            case CLIENTS:
                break;
            case CLIENTS_FOR_USER:
                break;
            case BANNED_CLIENTS:
                break;
            case MESSAGES:
                break;
            case ADMINISTRATION_MESSAGES_FOR_USER:
                break;
            case CLIENTS_MESSAGES_FOR_USER:
                break;
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
