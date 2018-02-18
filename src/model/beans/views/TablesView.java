package model.beans.views;

import model.enums.TablePage;
import model.enums.UserType;
import utils.Util;

import java.util.LinkedList;

import static utils.Util.getPageTitleFromPageType;

public class TablesView {

    private UserType loggedInUserType;
    private int loggedInUserId;
    private TablePage currentPage;
    private Nav nav;
    private DataTable dataTable;

    public TablesView() {
    }

    public String getBreadcrumbTitle() {
        return getPageTitleFromPageType(currentPage);
    }

    public Nav getNav() {
        setupNav();
        return this.nav;
    }

    private void setupNav() {
        this.nav = new Nav();
        nav.setUserId(loggedInUserId);
        nav.setUserType(loggedInUserType);
        nav.setCurrentPage(currentPage);
    }

    public String getPageTitle() {
        if (loggedInUserType != UserType.ADMIN)
            return "Espace " + Util.getStringFromType(loggedInUserType) + "s";
        else
            return "Admin Panel";
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = Util.getPageFromString(currentPage);
    }

    public void setLoggedInUserType(UserType loggedInUserType) {
        this.loggedInUserType = loggedInUserType;
    }

    public void setLoggedInUserId(int loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public DataTable getDataTable() {
        setupDataTable();
        return dataTable;
    }

    private void setupDataTable() {
        dataTable = new DataTable();
        dataTable.setUserId(loggedInUserId);
        dataTable.setUserType(loggedInUserType);
        dataTable.setCurrentPage(currentPage);
    }
}
