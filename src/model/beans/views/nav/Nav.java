package model.beans.views.nav;

import model.beans.views.MyView;
import model.enums.TablePage;
import model.enums.UserType;
import utils.MyConsts;
import utils.Util;

import java.util.LinkedList;

public class Nav {
    private int UserId;
    private UserType userType;
    private TablePage currentPage;
    private LinkedList<MyView> navElements;
    private int userId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

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
        LinkedList<NavElement> visitesSubNavElementList = new LinkedList<>();

        NavElement visitesProgrammeesNavElement = new NavElement(isPage(currentPage,TablePage.PROGRAMMED_VISITES),MyConsts.VISITES_TITLE,MyConsts.GET_VISIT_SERVLET_URL + "?what=programmees","");
        visitesSubNavElementList.add(visitesProgrammeesNavElement);

        NavElement visitesPasseesNavElement = new NavElement((isPage(currentPage, TablePage.PASSED_VISITS)), MyConsts.VISITES_TITLE, MyConsts.GET_VISIT_SERVLET_URL + "?what=passees", "");
        visitesSubNavElementList.add(visitesPasseesNavElement);

        NavElement visitesAnnuleesNavElement = new NavElement(isPage(currentPage,TablePage.CANCELED_VISITES),MyConsts.VISITES_TITLE, MyConsts.GET_VISIT_SERVLET_URL + "?what=annulees","");
        visitesSubNavElementList.add(visitesAnnuleesNavElement);

        ExpendableNavElement visitesExpendableNavElement = new ExpendableNavElement("visitesNav","","Visites",visitesSubNavElementList,isPage(currentPage,TablePage.VISITES));
        navElements.add(visitesExpendableNavElement);

        /*logements*/
        LinkedList<NavElement> logementsSubNavElement = new LinkedList<>();

        NavElement listeLogementsNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS),MyConsts.LOGEMENT_TITLE,MyConsts.GET_LOGEMENT_SERVLET_URL + "?what=all","");
        logementsSubNavElement.add(listeLogementsNavElement);

        NavElement logementsVendusNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS_VENDUS),MyConsts.LOGEMENT_TITLE, MyConsts.GET_LOGEMENT_SERVLET_URL + "?what=vendus","");
        logementsSubNavElement.add(logementsVendusNavElement);

        NavElement logementsGelesNavElement = new NavElement(isPage(currentPage,TablePage.FROZEN_LOGEMENTS),MyConsts.LOGEMENT_TITLE, MyConsts.GET_LOGEMENT_SERVLET_URL + "?what=geles","");
        logementsSubNavElement.add(logementsGelesNavElement);

        ExpendableNavElement logementsExpendableNavElement = new ExpendableNavElement("logementsNav","","Logements",logementsSubNavElement,isPage(currentPage,TablePage.LOGEMENTS));
        /*clients*/
        LinkedList<NavElement> clientsSubNavElements = new LinkedList<>();

        NavElement listeClientsNavElement = new NavElement(isPage(currentPage, TablePage.CLIENTS), MyConsts.CLIENTS_TITLE, MyConsts.GET_CLIENTS_SERVLET_URL + "?what=all", "");
        clientsSubNavElements.add(listeClientsNavElement);

        NavElement mesClientsNavElement = new NavElement(isPage(currentPage,TablePage.CLIENTS_FOR_USER),MyConsts.CLIENTS_TITLE,MyConsts.GET_CLIENTS_SERVLET_URL + "?what=myclients","");
        clientsSubNavElements.add(mesClientsNavElement);

        ExpendableNavElement clientsExpendableNavElement = new ExpendableNavElement("clientsNav","","Clients",clientsSubNavElements,isPage(currentPage,TablePage.CLIENTS));
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

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
