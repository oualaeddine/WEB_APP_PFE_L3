package model.beans.views;

import model.enums.UserType;

public class TablesView {

    public static String getNavForUser(UserType userType, String currentPage) {
        return null;
    }

    public String getBreadcrumbTitle() {
        return null;
    }

    public String getNav() {
        return null;

    }

    public boolean getTableStructure() {
        String s = "   <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n" +
                "                        <thead>\n" +
                "                        <tr>\n" +
                "                            <th>id</th>\n" +
                "                            <th>Client</th>\n" +
                "                            <th>Logement</th>\n" +
                "                            <th>Agent</th>\n" +
                "                            <th>Start date</th>\n" +
                "                        </tr>\n" +
                "                        </thead>\n" +
                "                        <tfoot>\n" +
                "                        <tr>\n" +
                "                            <th>id</th>\n" +
                "                            <th>Client</th>\n" +
                "                            <th>Logement</th>\n" +
                "                            <th>Agent</th>\n" +
                "                            <th>Start date</th>\n" +
                "                        </tr>\n" +
                "                        </tfoot>\n" +
                "                    </table>";
        return false;
    }

    public String getDataTableJsFillScript() {
        return null;
    }

    public String getDataTableTitle() {
        return null;
    }
}
