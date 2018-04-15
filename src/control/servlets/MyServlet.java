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

    protected int getLoggedInId(HttpServletRequest request) {
        return (int) request.getSession().getAttribute(LOGGED_IN_USER_ID);
    }

    protected String getLoggedInUsername(HttpServletRequest request) {
        Person person = (Person) request.getSession().getAttribute(LOGGED_IN_USER);
        return person.getUsername();
//        HttpSession session = request.getSession(false);
//        if (isLoggedIn(request)) {
//            Person person =(Person) session.getAttribute("loggedIn");
//            return person.getUsername();
//        } else {
//            return null;
//        }
    }

    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        response.sendRedirect(MyConsts.LOGIN_SERVLET_URL + "?error=" + LOGIN_NEEDED_ERROR_ID);
//        response.sendRedirect("/login");
    }
    protected void redirectToDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/DashboardServlet");
    }


}
