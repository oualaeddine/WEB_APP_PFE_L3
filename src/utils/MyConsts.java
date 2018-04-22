package utils;

public class MyConsts {
    public static final String

    /*APIs urls*/
            AGENT_API_URL_PATTERN = "/api/AgentApi",
            CLIENT_API_URL_PATTERN = "/api/clientApi",
            LOGEMENT_API_URL_PATTERN = "/api/logementApi",
            VISITE_API_URL_PATTERN = "/api/visiteApi",
    /*Servlets urls*/

    /*ADMIN*/
    ADMIN_SERVLET_URL = "/AdminServlet",
            SUPER_USER_SERVLET_URL = "/SUServlet",

    /*AUTH*/
    LOGIN_SERVLET_URL = "/login",
            LOGOUT_SERVLET_URL = "/logout",
            SIGNUP_SERVLET_URL = "/signup",

    /*CLIENT*/
    CLIENT_SERVLET_URL = "/espaceClients",

    /*AGENT*/
    AGENT_SERVLET_URL = "/AgentServlet",

    /*OPERATEUR*/
    OPERATEUR_SERVLET_URL = "/OperateurServlet",

    /*RESPONSABLE VENTES*/
    RESONSABLE_VENTES_SERVLET_URL = "",

    /*HOME*/
    HOME_SERVLET_URL = "/home";


    public static final String
            FOOTER_COPYRIGHT = "<small>Copyright © Berrehal-Benghezal-Rahab PFE GL L3 2018</small>",
            OPERATEUR_NAV_CSS_CLASS = "operateur",
            AGENT_NAV_CSS_CLASS = "My",
            DEFAULT_NAV_CSS_CLASS = "",
            RESPONSABLE_VENTES_NAV_CSS_CLASS = "",
            ADMIN_NAV_CSS_CLASS = "admin",
            SUPER_USER_NAV_CSS_CLASS = "superuser";



    /*IVS*/

    public static final String
            ERROR_SERVLET_URL = "/errorServlet",
            APPARTEMENT_SERVLET_URL = "/APPARTEMENT_SERVLET_URL",
            ChangeLanguageServlet = "/ChangeLanguageServlet",
            RedirectToHumanServlet = "/RedirectToHumanServlet",
            WelcomeMenuHandelerServlet = "/WelcomeMenuHandelerServlet",
            WELCOME_SERVLET_URL = "/welcome";
    public static final String ROOMS_NUMBER_SERVLET_URL = "/rooms";
    public static final String REGION_SERVLET_URL = "/region";
    public static final String FOURCHETE_PRIX_SERVLET_URL = "/prix";
    public static final String GET_VISIT_SERVLET_URL = "/Visites";
    public static final String GET_LOGEMENT_SERVLET_URL = "/Logement";
    public static final String GET_CLIENTS_SERVLET_URL = "/Clients";
    public static final String PRIX_MP3_URL = "";
    public static final String DASHBOARD_NAV_ELEMENT_TITLE = "Accueil";
    public static final String ETABLIR_RAPPORT_NAV_TITLE = "Etablir Rapport";
    public static final String PROGRAMMER_VISITE_NAV_TITLE = "Programmer visite";

    /*Regions*/
    public static final String AJOUTER_REGION_NAV = "Ajouter localité";
    /*Messages nav titles*/
    public static final String MESSAGES_NAV_ELEMENT_TITLE = "Messages";
    public static final String ADMINISTRATION_MESSAGES_NAV_ELEMENT_TITLE = "Administration";
    public static final String SEND_MESSAGE_NAV_ELEMENT_TITLE = "Envoyer un message";

    /* Visites nav titles*/
    public static final String VISITES_TITLE = "Visites";
    public static final String VISITES_PROGRAMMEES_TITLE = "Visites programmées";
    public static final String VISITES_PASSEES_TITLE = "Visites passées";
    public static final String VISITES_ANNULEES_TITLE = "Visites annulées";

    /*Logements nav titles*/
    public static final String LOGEMENT_TITLE = "Logements";
    public static final String ALL_LOGEMENTS_TITLE = "Liste logements";
    public static final String LOGEMENTS_VENDUS_TITLE = "Logements vendus";
    public static final String LOGEMENTS_GELES_TITLE = "Logements gelés";


    /*Clients nav titles*/
    public static final String CLIENTS_TITLE = "Clients";
    public static final String LISTE_CLIENTS = "Liste des clients";
    public static final String CLIENTS_MES_CLIENTS = "Mes clients";
    public static final String CLIENTS_SIGNALER_CLIENT = "Signaler personal";
    /*Profil nav titles*/
    public static final String MODIFIER_PROFIL = "Modifier profil";
    public static final String CHANGE_PASSWORD = "Changer mot de passe";


    public static final String LOGOUT_PAGE_TITILE = "Deconnexion";

    public static final String EMPLOYE_SIGNALER_CLIENT = "Signaler client";
    public static final String CLIENT_LOGIN_SERVLET_URL = "/loginsignup";
}
