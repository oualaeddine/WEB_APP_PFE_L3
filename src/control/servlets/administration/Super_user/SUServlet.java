package control.servlets.administration.Super_user;

import control.servlets.MyServlet;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SUServlet", value = "/SUServlet")
public class SUServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                String what = request.getParameter("what");
                if (what == null) {
                    this.getServletContext().getRequestDispatcher("/jsp/superuser.jsp").forward(request, response);
                } else {
                    switch (what) {
                        case "imprimerRecuVersement":
                            this.getServletContext().getRequestDispatcher("/statistics/recuVersement.jsp").forward(request, response);
                            break;
                        case "allAppels":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=APPELS").forward(request, response);
                            break;
                        case "confirmedAppels":
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=APPELS_CONFIRMES").forward(request, response);
                            break;
                        case "listeSignals": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=SIGNALEMENT").forward(request, response);
                            break;
                        }
                        case "assignerRegion": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=ASSIGNER_REGION").forward(request, response);
                            break;
                        }
                        case "messages": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MESSAGES_FOR_ADMIN").forward(request, response);
                            break;
                        }
                        case "newMessage": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=MESSAGES_FOR_ADMIN").forward(request, response);
                            break;
                        }
                        case "listeAdmins": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=ADMINS").forward(request, response);
                            break;
                        }
                        case "suspendreAdmin": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=SUSPENDRE_ADMIN").forward(request, response);
                            break;
                        }
                        case "listeAgents": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=AGENTS").forward(request, response);
                            break;
                        }
                        case "listeOperateurs": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=OPERATEURS").forward(request, response);
                            break;
                        }
                        case "listeResVente": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=RESPONSABLES_VENTES").forward(request, response);
                            break;
                        }
                        case "ajouterEmploye": {
                            this.getServletContext().getRequestDispatcher("/jsp/ajouterEmploye.jsp").forward(request, response);
                            break;
                        }
                        case "approuverEmploye": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=APPROUVER_EMPLOYE").forward(request, response);
                            break;
                        }
                        case "suspendreEmploye": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=SUSPENDRE_EMPLOYE").forward(request, response);
                            break;
                        }
                        case "listeRegions": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=LOCALITES").forward(request, response);
                            break;
                        }
                        case "ajouterRegion": {
                            this.getServletContext().getRequestDispatcher("/jsp/ajouterRegion.jsp").forward(request, response);
                            break;
                        }
                        case "listeVisites": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VISITES").forward(request, response);
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
                        case "listePlaintes": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=PLAINTES").forward(request, response);
                            break;
                        }
                        case "bannirClient": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=BANNIR_CLIENT").forward(request, response);
                            break;
                        }
                        case "clientsBannis": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=BANNED_CLIENTS").forward(request, response);
                            break;
                        }
                        case "signalerClient": {
                            this.getServletContext().getRequestDispatcher("/html/signalerClient.html").forward(request, response);
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
                        case "gelerLogement": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=GELER_LOGEMENT").forward(request, response);
                            break;
                        }
                        case "ajouterLogement": {
                            this.getServletContext().getRequestDispatcher("/jsp/ajouterLogement.jsp").forward(request, response);
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
                        case "listeVentes": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VENTES").forward(request, response);
                            break;
                        }
                        case "ventesConfirmees": {
                            this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CONFIRMED_VENTES").forward(request, response);
                            break;
                        }
                        case "ajouterVente": {
                            this.getServletContext().getRequestDispatcher("/html/ajouterVente.html").forward(request, response);
                            break;
                        }
                        case "modifierInfosContact": {
                            this.getServletContext().getRequestDispatcher("/html/modifierInfosContact.html").forward(request, response);
                            break;
                        }
                        default:
                            this.getServletContext().getRequestDispatcher("/jsp/superuser.jsp").forward(request, response);
                            break;
                    }
                }
            } else redirectToDashboard(request, response);
        } else redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
    }
}
