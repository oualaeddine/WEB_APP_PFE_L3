package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.AdminsManager;
import model.beans.humans.Employe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SuppressionServlet", value = "/SuppressionServlet")
public class SuppressionServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employe loggedInEmploye = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
        if (isLoggedIn(request)) {
            String supprimer = request.getParameter("supprimer");
            AdminsManager adminsManager = new AdminsManager(loggedInEmploye);
            if (supprimer != null) {
                switch (supprimer) {
                    case "employe":
                        if (adminsManager.deleteEmploye(request)) {
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        } else {
                            redirectToDashboard(request, response, ACTION_ERROR);
                        }
                        break;
                    case "client":
                        if (adminsManager.deleteClient(request))
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        else
                            redirectToDashboard(request, response, ACTION_ERROR);
                        break;
                    case "logement":
                        if (adminsManager.deleteLogement(request)) {
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        } else {
                            redirectToDashboard(request, response, ACTION_ERROR);
                        }
                        break;

                }
            }

        }
    }
}
