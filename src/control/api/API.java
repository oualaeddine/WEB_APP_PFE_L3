package control.api;

import control.system.Auth;
import model.enums.UserType;

import javax.servlet.http.HttpServlet;

public class API extends HttpServlet {
    protected static final String
            AUTH_ERROR = "auth_failed",
            ACTION_NOT_SET_ERROR = "action_not_set",
            PARAMETER_NOT_SET_ERROR = "parameters_not_set",
            ACTION_ERROR = "wrong_action",
            REQUEST_TYPE_ERROR = "denied";

    protected boolean checkLogin(UserType userType, int userId, String password) {
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
