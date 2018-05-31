package control.servlets.administration.Agent;

import control.servlets.MyServlet;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AgentServlet",value = "/AgentServlet")
public class AgentServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.AGENT) {
                String what = request.getParameter("what");
                if (what == null) {
                    this.getServletContext().getRequestDispatcher("/jsp/agent.jsp").forward(request, response);
                } else {
                    switch (what) {
                        case "myNotifications":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=EMPLOYEE_NOTIFICATIONS").forward(request, response);
                            break;
                        case "mesRapports":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=RAPPORTS_LIST").forward(request, response);
                            break;
                        case "mesLogementsVisites":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=LOGEMENTS_FOR_USER").forward(request, response);
                            break;
                        case "modifierVisite":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MODIFIER_VISITE").forward(request, response);
                            break;
                        case "etablirRapport": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=ETABLIR_RAPPORT").forward(request, response);
                            break;
                        }
                        case "myVisits": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=AGENT_VISITES").forward(request, response);
                            break;
                        }
                        case "visitesPassees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MY_PASSED_VISITS").forward(request, response);
                            break;
                        }
                        case "visitesAnnulees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MY_CANCELED_VISITS").forward(request, response);
                            break;
                        }
                        case "listeClients": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CLIENTS").forward(request, response);
                            break;
                        }
                        case "myClients": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CLIENTS_FOR_AGENT").forward(request, response);
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
                            this.getServletContext().getRequestDispatcher("/jsp/agent.jsp").forward(request, response);
                            break;
                    }

                }
            } else redirectToDashboard(request, response);
        } else redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
    }
}
