package utils;


import com.google.gson.Gson;
import model.db.daos.*;
import model.enums.UserType;

public class Util {

    /**
     * @param object object we want to convert to JSON
     * @return String containing the object in parameter formatted tto JSON
     */
    public static String objectToJson(Object object) {
        String json ;
        json = new Gson().toJson(object);
        System.out.println("JSON = " + json);
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
}