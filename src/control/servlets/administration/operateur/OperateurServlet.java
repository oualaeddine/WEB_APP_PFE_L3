package control.servlets.administration.operateur;

import control.servlets.MyServlet;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "OperateurServlet",value = "/OperateurServlet")
@WebServlet({"/OperateurServlet"})
public class OperateurServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            String what = request.getParameter("what");
            if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.OPERATEUR) {
                if (what == null) {
                    this.getServletContext().getRequestDispatcher("/jsp/operateur.jsp").forward(request, response);
                } else {
                    switch (what) {
                        case "visitesReportees":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=REPORTED_VISITES").forward(request, response);
                            break;
                        case "myNotifications":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=EMPLOYEE_NOTIFICATIONS").forward(request, response);
                            break;
                        case "nouvelleVente":
                            this.getServletContext().getRequestDispatcher("/programmerVisite/ajouterVente.jsp").forward(request, response);
                            break;
                        case "modifierVisite":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MODIFIER_VISITE").forward(request, response);
                            break;
                        case "programmerVisite": {
                            this.getServletContext().getRequestDispatcher("/jsp/ProgrammerVisite.jsp").forward(request, response);

                            break;
                        }
                        case "AdminsMessages": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=ADMINISTRATION_MESSAGES_FOR_EMPLOYEES").forward(request, response);
                            break;
                        }
                        case "newMessage": {
                            this.getServletContext().getRequestDispatcher("/html/newMessage.html").forward(request, response);
                            break;
                        }

                        case "visitesProgrammees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=PROGRAMMED_VISITES").forward(request, response);
                            break;
                        }
                        case "visitesPassees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=PASSED_VISITS").forward(request, response);
                            break;
                        }
                        case "visitesAnnulees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CANCELED_VISITES").forward(request, response);
                            break;
                        }
                        case "listeClients": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CLIENTS").forward(request, response);
                            break;
                        }
                        case "myClients": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CLIENTS_FOR_USER").forward(request, response);
                            break;
                        }
                        case "signalerClient": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=SIGNALER_CLIENT").forward(request, response);
                            break;
                        }
                        case "allLogements": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=LOGEMENTS").forward(request, response);
                            break;
                        }
                        case "logementVendus": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=LOGEMENTS_VENDUS").forward(request, response);
                            break;
                        }
                        case "logementGeles": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=FROZEN_LOGEMENTS").forward(request, response);
                            break;
                        }
                        case "modifierProfil": {
                            this.getServletContext().getRequestDispatcher("/jsp/modifierProfil.jsp").forward(request, response);
                            break;
                        }
                        case "changePassword": {
                            this.getServletContext().getRequestDispatcher("/ChangePassword").forward(request, response);
                            break;
                        }
                        default:
                            this.getServletContext().getRequestDispatcher("/jsp/operateur.jsp").forward(request, response);
                            break;
                    }

                }
            } else {
                redirectToDashboard(request, response);
            }
        } else {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        }
    }
}
