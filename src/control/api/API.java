package control.api;

import control.system.managers.AuthManager;
import model.enums.UserType;

import javax.servlet.http.HttpServlet;

public class API extends HttpServlet {
    protected static final String
            AUTH_ERROR = "auth_failed",
            ACTION_NOT_SET_ERROR = "action_not_set",
            PARAMETER_NOT_SET_ERROR = "parameters_not_set",
            ACTION_ERROR = "wrong_action",
            REQUEST_TYPE_ERROR = "denied";

    protected boolean checkLogin(String userName, String password) {
        AuthManager auth = new AuthManager();
        return auth.authenticateEmploye(userName, password);
    }
}
