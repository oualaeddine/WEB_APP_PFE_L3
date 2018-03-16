package control.servlets;

import control.system.managers.AuthManager;
import model.beans.humans.Person;
import model.enums.UserType;
import utils.MyConsts;

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
            LOGGED_IN_USER_PASSWORD = "password",
            LOGGED_IN_USER = "user";
    protected static final int
            LOGIN_NEEDED_ERROR_ID = 0,
            WRONG_CREDENTIALS_ERROR = 1,
            MISSING_CREDENTIALS_ERROR = 2;


    protected boolean isLoggedIn(HttpServletRequest request) {
//        HttpSession session = request.getSession();
        return !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    }

    protected String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (isLoggedIn(request)) {
            Person person =(Person) session.getAttribute("loggedIn");
            return person.getUsername();
        } else {
            return null;
        }
    }

    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        response.sendRedirect(MyConsts.LOGIN_SERVLET_URL + "?" + LOGIN_NEEDED_ERROR_ID);
//        response.sendRedirect("/login");
    }

    protected void redirectToDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserType userType = (UserType) request.getSession().getAttribute(LOGGED_IN_USER_TYPE);
        if (userType != null) {
            String dashboardUrl;
            switch (userType) {
                case CLIENT:
                    dashboardUrl = MyConsts.CLIENT_SERVLET_URL;
                    break;
                case AGENT:
                    dashboardUrl = MyConsts.AGENT_SERVLET_URL;

                    break;
                case OPERATEUR:
                    dashboardUrl = MyConsts.OPERATEUR_SERVLET_URL;

                    break;
                case ADMIN:
                    dashboardUrl = MyConsts.ADMIN_SERVLET_URL;

                    break;
                case RESPONSABLE_VENTES:
                    dashboardUrl = MyConsts.RESONSABLE_VENTES_SERVLET_URL;
                    break;
                default:
                    dashboardUrl = MyConsts.HOME_SERVLET_URL;
            }
            this.getServletContext().getRequestDispatcher(dashboardUrl).forward(request, response);
        } else {
            new AuthManager().logout(request);
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
