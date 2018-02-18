package control.servlets.main.auth;

import control.servlets.MyServlet;
import utils.Consts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUpServlet", urlPatterns = Consts.SIGNUP_SERVLET_URL)
public class SignUpServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {        // TODO: 2/18/2018

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {        // TODO: 2/18/2018

        }
    }
}
