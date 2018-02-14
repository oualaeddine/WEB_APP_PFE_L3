package control.servlets.auth;

import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
        } else {
            redirectToNotLoggedIn(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
        } else {
            redirectToNotLoggedIn(request, response);
        }
    }

    private void redirectToNotLoggedIn(HttpServletRequest request, HttpServletResponse response) {

    }
}
