package control.servlets.service_ventes;

import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServiceVentesServlet")
public class ServiceVentesServlet extends MyServlet {

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
