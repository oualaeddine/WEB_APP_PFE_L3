package model.beans.views;

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
        html = "";        // TODO: 2/18/2018

    }

    private void setupTableHeaderForClients() {
        // TODO: 2/18/2018

    }

    private void setupTableHeaderForLocalite() {
        // TODO: 2/18/2018

    }

    private void setupTableHeaderForAdmins() {
        // TODO: 2/18/2018

    }

    private void setupTableHeaderForVisites() {
        // TODO: 2/18/2018

    }

    private void setupTableHeaderForEmployees() {
        // TODO: 2/18/2018

    }

    private void setupTableHeaderForLogements() {
        // TODO: 2/18/2018

    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }
}
