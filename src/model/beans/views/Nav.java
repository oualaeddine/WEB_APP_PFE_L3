package model.beans.views;

import model.enums.TablePage;
import model.enums.UserType;
import utils.Consts;
import utils.Util;

import java.util.LinkedList;

public class Nav {
    private UserType userType;
    private int userId;
    private TablePage currentPage;
    private LinkedList<NavElement> navElements;

    public String getTitle() {
        if (userType != UserType.ADMIN)
            return "Espace " + Util.getStringFromType(userType) + "s";
        else
            return "Admin Panel";
    }

    public LinkedList<NavElement> getElements() {
        setupNavElements();
        return this.navElements;
    }

    private void setupNavElements() {
        this.navElements = new LinkedList<NavElement>();
        switch (userType) {
            case AGENT:
                setupNavElementsForAgent();
                break;
            case OPERATEUR:
                setupNavElementsForAOperateur();
                break;
            case ADMIN:
                setupNavElementsForADmin();
                break;
            case RESPONSABLE_VENTES:
                setupNavElementsForResponsableVentes();
                break;
        }
    }

    private void setupNavElementsForADmin() {
        // TODO: 2/18/2018
    }

    private void setupNavElementsForResponsableVentes() {        // TODO: 2/18/2018


    }

    private void setupNavElementsForAOperateur() {
        // TODO: 2/18/2018

    }

    private void setupNavElementsForAgent() {        // TODO: 2/18/2018


    }

    public String getCssBackgroundClass() {
        switch (userType) {
            case AGENT:
                return Consts.AGENT_NAV_CSS_CLASS;
            case OPERATEUR:
                return Consts.OPERATEUR_NAV_CSS_CLASS;
            case ADMIN:
                return Consts.ADMIN_NAV_CSS_CLASS;
            case RESPONSABLE_VENTES:
                return Consts.RESPONSABLE_VENTES_NAV_CSS_CLASS;
        }
        return Consts.DEFAULT_NAV_CSS_CLASS;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }
}
