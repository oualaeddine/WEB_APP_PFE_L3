package control.servlets;

import control.managers.AuthManager;
import model.enums.UserType;
import utils.Consts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    /*SERVLETS CONSTS*/
    public static final String
            LOGIN_NEEDED_REDIRECT_MESSAGE = "you must login first to see this page",
            LOGGED_IN_USER_USERNAME = "username",
            LOGGED_IN_USER_ID = "id",
            LOGGED_IN_USER_TYPE = "type",
            LOGGED_IN_USER_PASSWORD = "password";
    protected static final int
            LOGIN_NEEDED_ERROR_ID = 0,
            WRONG_CREDENTIALS_ERROR = 1,
            MISSING_CREDENTIALS_ERROR = 2;


    protected boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null || session.getAttribute(LOGGED_IN_USER_USERNAME) == null);
    }

    protected String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (isLoggedIn(request))
            return session.getAttribute(LOGGED_IN_USER_USERNAME).toString();
        else
            return null;
    }

    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException {
        response.sendRedirect(Consts.LOGIN_SERVLET_URL/* + "?" + LOGIN_NEEDED_ERROR_ID*/);
    }

    protected void redirectToDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserType userType = (UserType) request.getSession().getAttribute(LOGGED_IN_USER_TYPE);
        if (userType != null) {
            String dashboardUrl;
            switch (userType) {
                case CLIENT:
                    dashboardUrl = Consts.CLIENT_SERVLET_URL;
                    break;
                case AGENT:
                    dashboardUrl = Consts.CLIENT_SERVLET_URL;

                    break;
                case OPERATEUR:
                    dashboardUrl = Consts.CLIENT_SERVLET_URL;

                    break;
                case ADMIN:
                    dashboardUrl = Consts.CLIENT_SERVLET_URL;

                    break;
                case RESPONSABLE_VENTES:
                    dashboardUrl = Consts.CLIENT_SERVLET_URL;
                    break;
                default:
                    dashboardUrl = Consts.HOME_SERVLET_URL;
            }
            getServletContext().getRequestDispatcher(dashboardUrl).forward(request, response);
        } else {
            new AuthManager().logout(request);
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
