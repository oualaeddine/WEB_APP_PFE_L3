package control.servlets.main.auth;

import control.system.managers.AuthManager;
import control.servlets.MyServlet;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "LogoutServlet")
@WebServlet({MyConsts.LOGOUT_SERVLET_URL})
public class LogoutServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {        // TODO: 2/18/2018
//            request.getSession().invalidate();
//            redirectToLogin(request,response,WRONG_CREDENTIALS_ERROR);
            new AuthManager().logout(request);
            this.getServletContext().getRequestDispatcher(MyConsts.LOGIN_SERVLET_URL);
        } else {
            redirectToNotLoggedIn(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {        // TODO: 2/18/2018

        } else {
            redirectToNotLoggedIn(request, response);
        }
    }

    private void redirectToNotLoggedIn(HttpServletRequest request, HttpServletResponse response) {

    }
}
