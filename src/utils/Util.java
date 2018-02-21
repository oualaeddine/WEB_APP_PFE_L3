package utils;


import com.google.gson.Gson;
import model.db.daos.*;
import model.enums.TablePage;
import model.enums.UserType;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static model.enums.TablePage.*;

public class Util {

    /**
     * @param object object we want to convert to JSON
     * @return String containing the object in parameter formatted tto JSON
     */


    public static String objectToJson(Object object) {
        String json;
        json = new Gson().toJson(object);
        return json;
    }

    public static UserType getUserTypeFromString(String type) {
        switch (type) {
            case "client":
                return UserType.CLIENT;
            case "admin":
                return UserType.ADMIN;
            case "agent":
                return UserType.AGENT;
            case "operateur":
                return UserType.OPERATEUR;
            case "responsable_ventes":
                return UserType.RESPONSABLE_VENTES;
            default:
                return null;
        }
    }

    public static String getStringFromType(UserType type) {
        switch (type) {
            case CLIENT:
                return ClientDAO.TABLE_NAME;
            case AGENT:
                return AgentsDAO.TABLE_NAME;
            case OPERATEUR:
                return OperateurDAO.TABLE_NAME;
            case ADMIN:
                return AdminsDAO.TABLE_NAME;
            case RESPONSABLE_VENTES:
                return ResponsableVentesDAO.TABLE_NAME;
            default:
                return null;
        }
    }

    public static String getPageTitleFromPageType(TablePage currentPage) {
        switch (currentPage) {
            case LOGEMENTS:
                return "Logements";
            case LOGEMENTS_FOR_USER:
                return "Mes Logements";
            case LOGEMENTS_VENDUS:
                return "Logements vendus";
            case FROZEN_LOGEMENTS:
                return "Logements gelés";
            case LOGEMENTS_NON_VENDUS:
                return "Logements non vendus";
            case AGENTS:
                return "Agents";
            case SUSPENDED_AGENTS:
                return "Agents suspendus";
            case OPERATEURS:
                return "Operateurs";
            case SUSPENDED_OPERATEURS:
                return "Operateurs suspendus";
            case RESPONSABLES_VENTES:
                return "Responsables ventes";
            case SUSPENDED_RESPONSABLES_VENTES:
                return "Responsables ventes suspendus";
            case VISITES:
                return "Visites";
            case REPORTED_VISITES:
                return "Visites reportées";
            case CANCELED_VISITES:
                return "Visites Annulées";
            case USER_VISITES:
                return "Mes Visites";
            case PROGRAMMED_VISITES:
                return "Visites programmées";
            case ADMINS:
                return "Admins";
            case LOCALITES:
                return "Localites";
            case CLIENTS:
                return "Clients";
            case CLIENTS_FOR_USER:
                return "Mes Clients";
            case BANNED_CLIENTS:
                return "Clients bannis";
            case MESSAGES:
                return "Boite de reception";
            case ADMINISTRATION_MESSAGES_FOR_USER:
                return "Boite de reception Administration";
            case CLIENTS_MESSAGES_FOR_USER:
                return "Boite de reception clients";
        }
     return null;
    }

    public static TablePage getPageFromString(String tablePage) {
        switch (tablePage) {
            case "LOGEMENTS":
                return LOGEMENTS;
            case "LOGEMENTS_FOR_USER":
                return LOGEMENTS_FOR_USER;
            case "LOGEMENTS_VENDUS":
                return LOGEMENTS_VENDUS;
            case "FROZEN_LOGEMENTS":
                return FROZEN_LOGEMENTS;
            case "LOGEMENTS_NON_VENDUS":
                return LOGEMENTS_NON_VENDUS;
            case "AGENTS":
                return AGENTS;
            case "SUSPENDED_AGENTS":
                return SUSPENDED_AGENTS;
            case "OPERATEURS":
                return OPERATEURS;
            case "SUSPENDED_OPERATEURS":
                return SUSPENDED_OPERATEURS;
            case "RESPONSABLES_VENTES":
                return RESPONSABLES_VENTES;
            case "SUSPENDED_RESPONSABLES_VENTES":
                return SUSPENDED_RESPONSABLES_VENTES;
            case "VISITES":
                return VISITES;
            case "REPORTED_VISITES":
                return REPORTED_VISITES;
            case "CANCELED_VISITES":
                return CANCELED_VISITES;
            case "USER_VISITES":
                return USER_VISITES;
            case "PROGRAMMED_VISITES":
                return PROGRAMMED_VISITES;
            case "ADMINS":
                return ADMINS;
            case "LOCALITES":
                return LOCALITES;
            case "CLIENTS":
                return CLIENTS;
            case "CLIENTS_FOR_USER":
                return CLIENTS_FOR_USER;
            case "BANNED_CLIENTS":
                return BANNED_CLIENTS;
            case "MESSAGES":
                return MESSAGES;
            case "ADMINISTRATION_MESSAGES_FOR_USER":
                return ADMINISTRATION_MESSAGES_FOR_USER;
            case "CLIENTS_MESSAGES_FOR_USER":
                return CLIENTS_MESSAGES_FOR_USER;
        }
        return null;
    }
    public static Date getDateFromString(String date) throws ParseException{
//        Date r = null;
//        r = new Date(0000,0,0);
//        String[] tab = date.split("-");
//        System.out.println(tab[0]+"-"+tab[1]+"-"+tab[2]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        System.out.println(sql);

        return sql;
    }
}