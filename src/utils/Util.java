package utils;


import com.sun.org.omg.CORBA.OpDescriptionSeqHelper;
import model.db.daos.*;
import model.enums.UserType;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Util {

    /**
     * @param object object we want to convert to JSON
     * @return String containing the object in parameter formatted tto JSON
     */
    public static String objectToJson(Object object) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
            System.out.println("JSON = " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String getTableNameFromType(UserType type) {
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

/*    hedi galk mach affaire akhdem gson khir :3
        public static String objectToJson(Object object) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
            System.out.println("JSON = " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }  */