package control.api;

import control.system.Auth;
import model.enums.UserType;

public interface API {
    default boolean checkLogin(UserType userType, int userId, String password) {
        Auth auth = new Auth();
        switch (userType) {
            case CLIENT:
                return auth.athenticateClient(userId, password);
            case AGENT:
                return auth.athenticateAgent(userId, password);
            case OPERATEUR:
                return auth.athenticateOperateur(userId, password);
            case ADMIN:
                return auth.athenticateAdmin(userId, password);
            case RESPONSABLE_VENTES:
                return auth.athenticateResponsableVentes(userId, password);
            default:
                return false;
        }
    }
}
