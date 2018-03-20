package model.beans.views.nav;

import control.servlets.MyServlet;
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTitle() {
        if (userType != UserType.ADMIN)
            return "Espace " + Util.getStringFromType(userType)+ "s " ;
        else
            return "Espace admins ";
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
                setupNavElementsForOperateur();
                break;
            case ADMIN:
                setupNavElementsForAdmin();
                break;
            case RESPONSABLE_VENTES:
                setupNavElementsForResponsableVentes();
                break;
        }
    }

    private void setupNavElementsForAdmin() {
        String urlBase = MyConsts.ADMIN_SERVLET_URL;

        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*messages*/
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.MESSAGES_FOR_ADMIN), MyConsts.MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=messages", "fa-envelope");
        messagesSubNavElementList.add(administrationMessagesNavElement);
        NavElement envoyerMessage = new NavElement(isPage(currentPage,TablePage.NEW_MESSAGE),"Envoyer un message",urlBase + "?what=newMessage","fa fa-pencil");
        messagesSubNavElementList.add(envoyerMessage);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*Employees*/
        LinkedList<NavElement> employeesSubNavElement = new LinkedList<>();
            /*Admins*/
        NavElement listeAdminsNavElement = new NavElement(isPage(currentPage, TablePage.ADMINS), "Liste des administrateurs", urlBase + "?what=listeAdmins", "fa fa-list");
        employeesSubNavElement.add(listeAdminsNavElement);
            /*Agents*/
        NavElement listeAgentsNavElement = new NavElement(isPage(currentPage,TablePage.AGENTS),"Liste des agents",urlBase+"?what=listeAgents","fa fa-list");
        employeesSubNavElement.add(listeAgentsNavElement);
            /*Operators*/
        NavElement listeOperateursNavElement = new NavElement(isPage(currentPage, TablePage.OPERATEURS), "Liste des operateurs", urlBase + "?what=listeOperateurs", "fa fa-list");
        employeesSubNavElement.add(listeOperateursNavElement);
            /*Responsables Ventes*/
        NavElement listeResVenteNavElement = new NavElement(isPage(currentPage, TablePage.RESPONSABLES_VENTES), "Liste des responsable de ventes", urlBase + "?what=listeResVente", "fa fa-list");
        employeesSubNavElement.add(listeResVenteNavElement);

        NavElement ajouterEmployeNavElement = new NavElement(false,"Ajouter employé", urlBase+"?what=ajouterEmploye","fa fa-plus");
        employeesSubNavElement.add(ajouterEmployeNavElement);

        NavElement approuverEmploye = new NavElement(false, "Approuver un employé", urlBase + "?what=approuverEmploye", "fa-check");
        employeesSubNavElement.add(approuverEmploye);

        NavElement suspendreEmploye = new NavElement(false, "Suspendre/réintegrer un employé", urlBase + "?what=suspendreEmploye", "fa fa-ban");
        employeesSubNavElement.add(suspendreEmploye);

        ExpendableNavElement employesExpendableNavElement = new ExpendableNavElement("employesNav", "fa fa-vcard", "Employes", employeesSubNavElement,isPage(currentPage,TablePage.EMPLOYES));
        navElements.add(employesExpendableNavElement);
        /*Regions*/
        LinkedList<NavElement> regionsSubElementList = new LinkedList<>();
        NavElement listeRegions = new NavElement(isPage(currentPage, TablePage.LOCALITES), "Liste des regions", urlBase + "?what=listeRegions","fa fa-list");
        regionsSubElementList.add(listeRegions);
        NavElement ajouterRegionNavElement = new NavElement(false, MyConsts.AJOUTER_REGION_NAV, urlBase + "?what=ajouterRegion","fa fa-plus");
        regionsSubElementList.add(ajouterRegionNavElement);
        ExpendableNavElement regionsExpendableNavElement = new ExpendableNavElement("regionsNav", "fa fa-location-arrow", "Regions",regionsSubElementList,isPage(currentPage, TablePage.LOCALITES));
        navElements.add(regionsExpendableNavElement);
        /*logements*/
        LinkedList<NavElement> logementsSubNavElementList = new LinkedList<>();
        NavElement consulterLogements = new NavElement(isPage(currentPage,TablePage.LOGEMENTS), "Liste des logements",urlBase+"?what=allLogements","fa fa-list");
        logementsSubNavElementList.add(consulterLogements);

        NavElement logementsVendusNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS_VENDUS),MyConsts.LOGEMENTS_VENDUS_TITLE, urlBase + "?what=logementVendus","fa fa-check");
        logementsSubNavElementList.add(logementsVendusNavElement);

        NavElement logementsGelesNavElement = new NavElement(isPage(currentPage,TablePage.FROZEN_LOGEMENTS),MyConsts.LOGEMENTS_GELES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=logementGeles","fa fa-fw fa-object-group");
        logementsSubNavElementList.add(logementsGelesNavElement);

        NavElement ajouterLogement = new NavElement(false,"Ajouter logement",urlBase+"?what=ajouterLogement","fa fa-plus");
        logementsSubNavElementList.add(ajouterLogement);

        NavElement gelerLogement = new NavElement(false, "Geler logement", urlBase + "gelerLogement", "fa fa-hand-stop-o");
        logementsSubNavElementList.add(gelerLogement);

        ExpendableNavElement logementsExpendableNavElement = new ExpendableNavElement("logementsNav","fa fa-building","Logements",logementsSubNavElementList,isPage(currentPage,TablePage.LOGEMENTS));
        navElements.add(logementsExpendableNavElement);


        /*clients*/
        LinkedList<NavElement> clientsSubNavElementList = new LinkedList<>();

        NavElement listeClients = new NavElement(isPage(currentPage, TablePage.CLIENTS), "Liste des clients", urlBase + "?what=listeClients", "fa fa-list");
        clientsSubNavElementList.add(listeClients);

        NavElement clientsBannis = new NavElement(isPage(currentPage, TablePage.BANNED_CLIENTS), "Clients bannis", urlBase + "?what=clientsBannis", "fa fa-ban");
        clientsSubNavElementList.add(clientsBannis);

        NavElement listePlaintes = new NavElement(isPage(currentPage, TablePage.PLAINTES), "Liste des plaintes", urlBase + "?what?listePlaintes", "fa fa-warning");
        clientsSubNavElementList.add(listePlaintes);

        NavElement bannirClient = new NavElement(false, "Bannir/rétablir client", urlBase + "?what=bannirClient", "fa fa-user-times");
        clientsSubNavElementList.add(bannirClient);

        ExpendableNavElement clientExpendableNavElement = new ExpendableNavElement("clientsNav", "fa fa-users", "Clients", clientsSubNavElementList, isPage(currentPage, TablePage.CLIENTS));
        navElements.add(clientExpendableNavElement);

        /*Visites*/
        LinkedList<NavElement> visitesSubNavElementList = new LinkedList<>();
        NavElement listeVisites = new NavElement(isPage(currentPage, TablePage.VISITES), "Liste des visites", urlBase + "?what=listeVisites", "fa fa-list");
        visitesSubNavElementList.add(listeVisites);

        NavElement visitesProgrammeesNavElement = new NavElement(isPage(currentPage, TablePage.PROGRAMMED_VISITES), "Visites programmées", urlBase + "?what=visitesProgrammees", "fa-calendar");
        visitesSubNavElementList.add(visitesProgrammeesNavElement);

        NavElement visitesPasseesNavElement = new NavElement(isPage(currentPage, TablePage.PASSED_VISITS), "Visites passée", urlBase + "?what=visitesPassees","fa-calendar-check");
        visitesSubNavElementList.add(visitesPasseesNavElement);

        NavElement visitesAnnuleesNavElement = new NavElement(isPage(currentPage, TablePage.CANCELED_VISITES), "Visites annulées", urlBase + "?what=visitesAnnulees", "fa-calendar-times");
        visitesSubNavElementList.add(visitesAnnuleesNavElement);
        

        ExpendableNavElement visitesExpendableNavElement = new ExpendableNavElement("visitesNav", "fa-eye", "Visites", visitesSubNavElementList, isPage(currentPage, TablePage.VISITES));
        navElements.add(visitesExpendableNavElement);
        /*Ventes*/
        LinkedList<NavElement> ventesSubNavElementList = new LinkedList<>();
        NavElement listeVentesNavElement = new NavElement(isPage(currentPage, TablePage.VENTES), "Liste des ventes", urlBase + "?what=listeVentes", "fa-list");
        ventesSubNavElementList.add(listeVentesNavElement);

        NavElement ventesConfirmeesNavElement = new NavElement(isPage(currentPage, TablePage.CONFIRMED_VENTES), "Ventes confirmées", urlBase + "?what=ventesConfirmees", "fa-check");
        ventesSubNavElementList.add(ventesConfirmeesNavElement);

        NavElement ajouterVentesNavElement = new NavElement(false, "Ajouter vente", urlBase + "?what=ajouterVente", "fa-plus");
        ventesSubNavElementList.add(ajouterVentesNavElement);

        ExpendableNavElement ventesExpendableNavElement = new ExpendableNavElement("ventesNav", "fa-dollar", "Ventes",ventesSubNavElementList, isPage(currentPage, TablePage.VENTES));
        navElements.add(ventesExpendableNavElement);
        /*profil*/
        LinkedList<NavElement> profilSubNavElement = new LinkedList<>();

        NavElement modifierProfilNavElement = new NavElement(isPage(currentPage, TablePage.MODIFIER_PROFIL), MyConsts.MODIFIER_PROFIL, urlBase + "?what=modifierProfil", "fa fa-user-circle");
        profilSubNavElement.add(modifierProfilNavElement);

        NavElement changerMdpNavElement = new NavElement(isPage(currentPage, TablePage.CHANGER_MOT_DE_PASSE), MyConsts.CHANGE_PASSWORD, urlBase + "?what=changePassword","fa fa-lock");
        profilSubNavElement.add(changerMdpNavElement);

        ExpendableNavElement profilExpendableNavElement = new ExpendableNavElement("profilNav", "fa fa-fw fa-user","Profil", profilSubNavElement, isPage(currentPage, TablePage.MODIFIER_PROFIL));
        navElements.add(profilExpendableNavElement);

        /*statistics*/
        NavElement modifierInfosSociete = new NavElement(false, "Modifier infos contact", urlBase + "?what=modifierInfosContact", "fa fa-phone-square");
        navElements.add(modifierInfosSociete);

        /*logout*/
        NavElement logoutNavElement = new NavElement(isPage(currentPage, TablePage.LOGOUT), MyConsts.LOGOUT_PAGE_TITILE, MyConsts.LOGOUT_SERVLET_URL, "fa fa-fw fa-sign-out");
        navElements.add(logoutNavElement);
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

    private void setupNavElementsForOperateur() {
        String urlBase = MyConsts.OPERATEUR_SERVLET_URL;
        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*Programmer visite*/

        NavElement programmerVisiteNavElement = new NavElement(false, MyConsts.PROGRAMMER_VISITE_NAV_TITLE, urlBase + "?what=programmerVisite", "fa-edit");
        navElements.add(programmerVisiteNavElement);

        /*messages*/
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.ADMINISTRATION_MESSAGES_FOR_EMPLOYEES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "fa-envelope");
        messagesSubNavElementList.add(administrationMessagesNavElement);
        NavElement envoyerMessage = new NavElement(isPage(currentPage,TablePage.NEW_MESSAGE),"Envoyer un message",urlBase + "?what=newMessage","fa fa-pencil");
        messagesSubNavElementList.add(envoyerMessage);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*visites*/
        LinkedList<NavElement> visitesSubNavElementList = new LinkedList<>();
        NavElement visitesProgrammeesNavElement = new NavElement(isPage(currentPage,TablePage.PROGRAMMED_VISITES),MyConsts.VISITES_PROGRAMMEES_TITLE,MyConsts.AGENT_SERVLET_URL + "?what=visitesProgrammees","fa-calendar");
        visitesSubNavElementList.add(visitesProgrammeesNavElement);

        NavElement visitesPasseesNavElement = new NavElement((isPage(currentPage, TablePage.PASSED_VISITS)), MyConsts.VISITES_PASSEES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=visitesPassees", "fa-calendar-check");
        visitesSubNavElementList.add(visitesPasseesNavElement);

        NavElement visitesAnnuleesNavElement = new NavElement(isPage(currentPage,TablePage.CANCELED_VISITES),MyConsts.VISITES_ANNULEES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=visitesAnnulees","fa-calendar-times");
        visitesSubNavElementList.add(visitesAnnuleesNavElement);

        ExpendableNavElement visitesExpendableNavElement = new ExpendableNavElement("visitesNav","fa-eye","Visites",visitesSubNavElementList,isPage(currentPage,TablePage.VISITES));
        navElements.add(visitesExpendableNavElement);
        /*clients*/
        LinkedList<NavElement> clientsSubNavElement = new LinkedList<>();

        NavElement listeClientNavElement = new NavElement(isPage(currentPage,TablePage.CLIENTS),MyConsts.LISTE_CLIENTS,MyConsts.AGENT_SERVLET_URL+"?what=listeClients","fa fa-list");
        clientsSubNavElement.add(listeClientNavElement);

        NavElement mesClientsNavElement = new NavElement(isPage(currentPage, TablePage.CLIENTS_FOR_USER), MyConsts.CLIENTS_MES_CLIENTS, MyConsts.AGENT_SERVLET_URL + "?what=myClients", "fa fa-fw fa-gratipay");
        clientsSubNavElement.add(mesClientsNavElement);

        NavElement signalerClientNavElement = new NavElement(isPage(currentPage, TablePage.CLIENTS), MyConsts.CLIENTS_SIGNALER_CLIENT, MyConsts.AGENT_SERVLET_URL + "?what=signalerClient", "fa fa-fw fa-ban");
        clientsSubNavElement.add(signalerClientNavElement);

        ExpendableNavElement clientsExpendableNavElement = new ExpendableNavElement("clientsNav","fa fa-fw fa-users","Client",clientsSubNavElement,isPage(currentPage,TablePage.CLIENTS));
        navElements.add(clientsExpendableNavElement);

        /*logements*/
        LinkedList<NavElement> logementsSubNavElement = new LinkedList<>();

        NavElement listeLogementsNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS),MyConsts.LOGEMENT_TITLE,MyConsts.AGENT_SERVLET_URL + "?what=allLogements","fa fa-list");
        logementsSubNavElement.add(listeLogementsNavElement);

        NavElement logementsVendusNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS_VENDUS),MyConsts.LOGEMENTS_VENDUS_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=logementVendus","fa fa-check");
        logementsSubNavElement.add(logementsVendusNavElement);

        NavElement logementsGelesNavElement = new NavElement(isPage(currentPage,TablePage.FROZEN_LOGEMENTS),MyConsts.LOGEMENTS_GELES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=logementGeles","fa fa-fw fa-object-group");
        logementsSubNavElement.add(logementsGelesNavElement);

        ExpendableNavElement logementsExpendableNavElement = new ExpendableNavElement("logementsNav","fa fa-building","Logements",logementsSubNavElement,isPage(currentPage,TablePage.LOGEMENTS));
        navElements.add(logementsExpendableNavElement);
        /*profile*/
        LinkedList<NavElement> profilSubNavElement = new LinkedList<>();

        NavElement modifierProfilNavElement = new NavElement(isPage(currentPage, TablePage.MODIFIER_PROFIL), MyConsts.MODIFIER_PROFIL, MyConsts.AGENT_SERVLET_URL + "?what=modifierProfil", "fa fa-user-circle");
        profilSubNavElement.add(modifierProfilNavElement);

        NavElement changerMdpNavElement = new NavElement(isPage(currentPage, TablePage.CHANGER_MOT_DE_PASSE), MyConsts.CHANGE_PASSWORD, MyConsts.AGENT_SERVLET_URL + "?what=changePassword","fa fa-lock");
        profilSubNavElement.add(changerMdpNavElement);

        ExpendableNavElement profilExpendableNavElement = new ExpendableNavElement("profilNav", "fa fa-fw fa-user","Profil", profilSubNavElement, isPage(currentPage, TablePage.MODIFIER_PROFIL));
        navElements.add(profilExpendableNavElement);

        /*logout*/
        NavElement logoutNavElement = new NavElement(isPage(currentPage, TablePage.LOGOUT), MyConsts.LOGOUT_PAGE_TITILE, MyConsts.LOGOUT_SERVLET_URL, "fa fa-fw fa-sign-out");
        navElements.add(logoutNavElement);
    }

    private void setupNavElementsForAgent() {
        String urlBase = MyConsts.AGENT_SERVLET_URL;
        /*dashboard*/
        NavElement dashboardNavElement = new NavElement(false, MyConsts.DASHBOARD_NAV_ELEMENT_TITLE, urlBase, "fa-home");
        navElements.add(dashboardNavElement);

        /*etablir rapport*/

        NavElement etablirRapportNavElement = new NavElement(false, MyConsts.ETABLIR_RAPPORT_NAV_TITLE, urlBase + "?what=etablirRapport", "fa-edit");
        navElements.add(etablirRapportNavElement);

        /*messages*/
        LinkedList<NavElement> messagesSubNavElementList = new LinkedList<>();
        NavElement administrationMessagesNavElement = new NavElement(isPage(currentPage, TablePage.ADMINISTRATION_MESSAGES_FOR_EMPLOYEES), MyConsts.ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE, urlBase + "?what=AdminsMessages", "fa-envelope");
        messagesSubNavElementList.add(administrationMessagesNavElement);
        NavElement envoyerMessage = new NavElement(isPage(currentPage,TablePage.NEW_MESSAGE),"Envoyer un message",urlBase + "?what=newMessage","fa fa-pencil");
        messagesSubNavElementList.add(envoyerMessage);
        ExpendableNavElement messagesExpendableNavElement = new ExpendableNavElement("messagesNav", "fa-envelope", MyConsts.MESSAGES_NAV_ELEMENT_TITLE, messagesSubNavElementList, isPage(currentPage, TablePage.MESSAGES));
        navElements.add(messagesExpendableNavElement);
        /*visites*/
        LinkedList<NavElement> visitesSubNavElementList = new LinkedList<>();
        NavElement visitesProgrammeesNavElement = new NavElement(isPage(currentPage,TablePage.PROGRAMMED_VISITES),MyConsts.VISITES_PROGRAMMEES_TITLE,MyConsts.AGENT_SERVLET_URL + "?what=visitesProgrammees","fa-calendar");
        visitesSubNavElementList.add(visitesProgrammeesNavElement);

        NavElement visitesPasseesNavElement = new NavElement((isPage(currentPage, TablePage.PASSED_VISITS)), MyConsts.VISITES_PASSEES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=visitesPassees", "fa-calendar-check");
        visitesSubNavElementList.add(visitesPasseesNavElement);

        NavElement visitesAnnuleesNavElement = new NavElement(isPage(currentPage,TablePage.CANCELED_VISITES),MyConsts.VISITES_ANNULEES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=visitesAnnulees","fa-calendar-times");
        visitesSubNavElementList.add(visitesAnnuleesNavElement);

        ExpendableNavElement visitesExpendableNavElement = new ExpendableNavElement("visitesNav","fa-eye","Visites",visitesSubNavElementList,isPage(currentPage,TablePage.VISITES));
        navElements.add(visitesExpendableNavElement);
        /*clients*/
        LinkedList<NavElement> clientsSubNavElement = new LinkedList<>();

        NavElement listeClientNavElement = new NavElement(isPage(currentPage,TablePage.CLIENTS),MyConsts.LISTE_CLIENTS,MyConsts.AGENT_SERVLET_URL+"?what=listeClients","fa fa-list");
        clientsSubNavElement.add(listeClientNavElement);

        NavElement mesClientsNavElement = new NavElement(isPage(currentPage, TablePage.CLIENTS_FOR_USER), MyConsts.CLIENTS_MES_CLIENTS, MyConsts.AGENT_SERVLET_URL + "?what=myClients", "fa fa-fw fa-gratipay");
        clientsSubNavElement.add(mesClientsNavElement);

        NavElement signalerClientNavElement = new NavElement(isPage(currentPage, TablePage.CLIENTS), MyConsts.CLIENTS_SIGNALER_CLIENT, MyConsts.AGENT_SERVLET_URL + "?what=signalerClient", "fa fa-fw fa-ban");
        clientsSubNavElement.add(signalerClientNavElement);

        ExpendableNavElement clientsExpendableNavElement = new ExpendableNavElement("clientsNav","fa fa-fw fa-users","Client",clientsSubNavElement,isPage(currentPage,TablePage.CLIENTS));
        navElements.add(clientsExpendableNavElement);

        /*logements*/
        LinkedList<NavElement> logementsSubNavElement = new LinkedList<>();

        NavElement listeLogementsNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS),MyConsts.LOGEMENT_TITLE,MyConsts.AGENT_SERVLET_URL + "?what=allLogements","fa fa-list");
        logementsSubNavElement.add(listeLogementsNavElement);

        NavElement logementsVendusNavElement = new NavElement(isPage(currentPage,TablePage.LOGEMENTS_VENDUS),MyConsts.LOGEMENTS_VENDUS_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=logementVendus","fa fa-check");
        logementsSubNavElement.add(logementsVendusNavElement);

        NavElement logementsGelesNavElement = new NavElement(isPage(currentPage,TablePage.FROZEN_LOGEMENTS),MyConsts.LOGEMENTS_GELES_TITLE, MyConsts.AGENT_SERVLET_URL + "?what=logementGeles","fa fa-fw fa-object-group");
        logementsSubNavElement.add(logementsGelesNavElement);

        ExpendableNavElement logementsExpendableNavElement = new ExpendableNavElement("logementsNav","fa fa-building","Logements",logementsSubNavElement,isPage(currentPage,TablePage.LOGEMENTS));
        navElements.add(logementsExpendableNavElement);
        /*profile*/
        LinkedList<NavElement> profilSubNavElement = new LinkedList<>();

        NavElement modifierProfilNavElement = new NavElement(isPage(currentPage, TablePage.MODIFIER_PROFIL), MyConsts.MODIFIER_PROFIL, MyConsts.AGENT_SERVLET_URL + "?what=modifierProfil", "fa fa-user-circle");
        profilSubNavElement.add(modifierProfilNavElement);

        NavElement changerMdpNavElement = new NavElement(isPage(currentPage, TablePage.CHANGER_MOT_DE_PASSE), MyConsts.CHANGE_PASSWORD, MyConsts.AGENT_SERVLET_URL + "?what=changePassword","fa fa-lock");
        profilSubNavElement.add(changerMdpNavElement);

        ExpendableNavElement profilExpendableNavElement = new ExpendableNavElement("profilNav", "fa fa-fw fa-user","Profil", profilSubNavElement, isPage(currentPage, TablePage.MODIFIER_PROFIL));
        navElements.add(profilExpendableNavElement);

        /*logout*/
        NavElement logoutNavElement = new NavElement(isPage(currentPage, TablePage.LOGOUT), MyConsts.LOGOUT_PAGE_TITILE, MyConsts.LOGOUT_SERVLET_URL, "fa fa-fw fa-sign-out");
        navElements.add(logoutNavElement);
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
