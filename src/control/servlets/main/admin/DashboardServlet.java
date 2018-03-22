package control.servlets.main.admin;

import control.system.managers.AdminsManager;
import control.servlets.MyServlet;
import model.beans.humans.Employe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = {"/DashboardServlet"})
public class DashboardServlet extends MyServlet {
    private AdminsManager adminManager;

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            Employe loggedInAdmin = new Employe();
            this.adminManager = new AdminsManager(loggedInAdmin);
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {        // TODO: 2/18/2018
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
