package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.AdminsManager;
import control.system.managers.AuthManager;
import control.system.managers.EmployeManager;
import model.beans.humans.Employe;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = {"/DashboardServlet"})
public class DashboardServlet extends MyServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request) && request.getSession().getAttribute(LOGGED_IN_USER_TYPE) != UserType.CLIENT) {
            String action = request.getParameter("action");
            if (action != null) {
                String error = "";
                switch (action) {
                    case "modifierProfil":
                        EmployeManager employeManager = new EmployeManager((Employe) request.getSession().getAttribute(LOGGED_IN_USER));
                        if (employeManager.modifierProfil(request)) {
                            System.out.println("Modification : true");
                            redirectToDashboard(request, response, ACTION_SUCCESS);
                        } else {
                            redirectToDashboard(request, response, ACTION_ERROR);
                        }
                        break;
                    case "modifierContactInfos":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            AdminsManager adminsManager = new AdminsManager((Employe) request.getSession().getAttribute(LOGGED_IN_USER));
                            if (adminsManager.modifierContactInfos(request)) {
                                System.out.println("Modification : true");
                                redirectToDashboard(request, response, ACTION_SUCCESS);
                            } else {
                                redirectToDashboard(request, response, ACTION_ERROR);
                            }
                        } else {
                            redirectToDashboard(request, response, ACCESS_DENIED);
                        }

                        break;
                }
            } else {
                doGet(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            UserType type = (UserType) request.getSession().getAttribute(LOGGED_IN_USER_TYPE);
            switch (type) {
                case SU:
                    this.getServletContext().getRequestDispatcher("/SUServlet").forward(request, response);
                    break;
                case ADMIN:
                    this.getServletContext().getRequestDispatcher("/AdminServlet").forward(request, response);
                    break;
                case OPERATEUR:
                    this.getServletContext().getRequestDispatcher("/OperateurServlet").forward(request, response);
                    break;
                case AGENT:
                    this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request, response);
                    break;
                case RESPONSABLE_VENTES:
                    this.getServletContext().getRequestDispatcher("/ServiceVentesServlet").forward(request, response);
                    break;
                case CLIENT:
                    this.getServletContext().getRequestDispatcher("/ClientServlet").forward(request, response);
                    break;
            }
        } else {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        }
    }
}
