package model.beans.views.nav;

import model.beans.views.MyView;
import model.enums.TablePage;
import model.enums.UserType;
import utils.MyConsts;
import utils.Util;

import java.util.LinkedList;

public class Nav {
    private UserType userType;
    private TablePage currentPage;
    private LinkedList<MyView> navElements;

    public String getTitle() {
        if (userType != UserType.ADMIN)
            return "Espace " + Util.getStringFromType(userType) + "s";
        else
            return "Admin Panel";
    }

    public LinkedList<MyView> getElements() {
        setupNavElements();
        return this.navElements;
    }

    private void setupNavElements() {
        this.navElements = new LinkedList<>();
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
        String urlBase = MyConsts.ADMIN_DASHBOARD_SERVLET_URL;

        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*messages*/// TODO: 3/1/2018 complete
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.MESSAGES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "");// TODO: 3/1/2018 add icon
        messagesSubNavElementList.add(administrationMessagesNavElement);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*Employees*/
            /*Agents*/
            /*Operators*/
            /*Responsables Ventes*/
        /*Regions*/
        /*logements*/
        /*clients*/
        /*visites*/
        /*Ventes*/
        /*statistics*/
        /*profile*/
        /*logout*/
    }

    private void setupNavElementsForResponsableVentes() {        // TODO: 2/18/2018
        String urlBase = MyConsts.RESONSABLE_VENTES_SERVLET_URL;

        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*messages*/// TODO: 3/1/2018 complete
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.MESSAGES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "");// TODO: 3/1/2018 add icon
        messagesSubNavElementList.add(administrationMessagesNavElement);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*ventes*/
        /*clients*/
        /*profile*/
        /*logout*/

    }

    private void setupNavElementsForAOperateur() {
        String urlBase = MyConsts.OPERATEUR_SERVLET_URL;

        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*programmer visite*/
        /*ajouter client*/
        /*messages*/// TODO: 3/1/2018 complete
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.MESSAGES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "");// TODO: 3/1/2018 add icon
        messagesSubNavElementList.add(administrationMessagesNavElement);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*visites*/
        /*logements*/
        /*clients*/
        /*agents*/
        /*profile*/
        /*logout*/
    }

    private void setupNavElementsForAgent() {
        String urlBase = MyConsts.AGENT_SERVLET_URL;
        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*etablir rapport*/

        NavElement etablirRapportNavElement = new NavElement(false, MyConsts.ETABLIR_RAPPORT_NAV_TITLE, urlBase + "?what=etablirRapport", "fa-edit");
        navElements.add(etablirRapportNavElement);

        /*messages*/// TODO: 3/1/2018 complete
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.MESSAGES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "");// TODO: 3/1/2018 add icon
        messagesSubNavElementList.add(administrationMessagesNavElement);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*visites*/
        /*logements*/
        /*clients*/
        /*profile*/
        /*logout*/

    }

    private boolean isPage(TablePage currentPage, TablePage navPage) {
        return currentPage == navPage;
    }

    public String getCssBackgroundClass() {
        switch (userType) {
            case AGENT:
                return MyConsts.AGENT_NAV_CSS_CLASS;
            case OPERATEUR:
                return MyConsts.OPERATEUR_NAV_CSS_CLASS;
            case ADMIN:
                return MyConsts.ADMIN_NAV_CSS_CLASS;
            case RESPONSABLE_VENTES:
                return MyConsts.RESPONSABLE_VENTES_NAV_CSS_CLASS;
        }
        return MyConsts.DEFAULT_NAV_CSS_CLASS;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setCurrentPage(TablePage currentPage) {
        this.currentPage = currentPage;
    }
}
