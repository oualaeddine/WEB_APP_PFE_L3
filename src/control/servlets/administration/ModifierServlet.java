package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.AdminsManager;
import model.beans.humans.Employe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifierServlet", value = "/ModifierServlet")
public class ModifierServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employe loggedInEmploye = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
        if (isLoggedIn(request)) {
            String modifier = request.getParameter("modifier");
            AdminsManager adminsManager = new AdminsManager(loggedInEmploye);
            if (modifier != null) {
                switch (modifier) {
                    case "employe":
                        if (adminsManager.modifierEmploye(request)) {
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        } else {
                            redirectToDashboard(request, response, ACTION_ERROR);
                        }
                        break;
                    case "client":
                        if (adminsManager.modifierClient(request))
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        else
                            redirectToDashboard(request, response, ACTION_ERROR);
                        break;
                    case "logement":
                        if (adminsManager.modifierLogement(request)) {
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        } else {
                            redirectToDashboard(request, response, ACTION_ERROR);
                        }
                        break;

                }
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            String modifier = request.getParameter("modifier");
            if (modifier != null) {
                switch (modifier) {
                    case "employe":
                        this.getServletContext().getRequestDispatcher("/jsp/modifier.jsp?what=employe").forward(request, response);
                        break;
                    case "client":
                        this.getServletContext().getRequestDispatcher("/jsp/modifier.jsp?what=client").forward(request, response);
                        break;
                    case "logement":
                        this.getServletContext().getRequestDispatcher("/jsp/modifier.jsp?what=logement").forward(request, response);
                        break;

                }
            } else {
                redirectToDashboard(request, response, 0);
            }
        }
    }
}
