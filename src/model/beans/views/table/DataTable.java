package model.beans.views.table;

import model.enums.TablePage;
import model.enums.UserType;

import java.util.LinkedList;

import static utils.Util.getPageTitleFromPageType;

public class DataTable {
    private DataTableHeader tableHeader;
    private DataTableData tableData;
    private int userId;
    private UserType userType;
    private TablePage currentPage;
    public String getDataTableTitle() {
        return "Liste des " + getPageTitleFromPageType(currentPage);
    }

    public String getTableHeader() {
        setupTableHeader();
        return tableHeader.getHtml();
    }

    public LinkedList<DataTableRow> getTableData() {

        setupTableData();
        return tableData.getData();
    }

    private void setupTableHeader() {
        tableHeader = new DataTableHeader();
        tableHeader.setCurrentPage(currentPage);
    }

    private void setupTableData() {
        tableData = new DataTableData();
        tableData.setUserId(this.userId);
        tableData.setCurrentPage(this.currentPage);
        tableData.setUserType(this.userType);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }

}
