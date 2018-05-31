package control.servlets.administration.operateur;

import control.api.VisiteApi;
import control.servlets.MyServlet;
import control.system.managers.OperateursManager;
import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.LogementDAO;
import model.db.daos.VisitesDao;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@SuppressWarnings("ALL")
@WebServlet(name = "ModifierVisiteServlet", urlPatterns = "/modifierVisiteServlet")
public class ModifierVisiteServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int error = 0;
        if (isLoggedIn(request) && (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.OPERATEUR || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.CLIENT)) {
            String what = request.getParameter("what");
            if (what != null) {
                switch (what) {
                    case "annuler":
                        int visiteId = Integer.parseInt(request.getParameter("visiteId"));
                        Visite visitee = new VisitesDao().getById(visiteId);
                        if (new VisitesDao().annulerVisite(visitee)) {
                            error = ACTION_SUCCESS;
                            System.out.println("Annulation: true");
                        } else {
                            error = ACTION_ERROR;
                            System.out.println("Annulation: false");
                        }
                        break;
                    case "reporter":
                        if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
                            Logement logement = (Logement) new LogementDAO().getById(Integer.parseInt(request.getParameter("idLogement")));
                            Client client = (Client) new ClientDAO().getById(Integer.parseInt(request.getParameter("idClient")));
                            Employe agent = (Employe) new EmployeDAO().getById(Integer.parseInt(request.getParameter("idAgent")));
                            int horraire = VisiteApi.getHorraireFromStringDate(request.getParameter("heureDebut"));
                            Date timestamp = VisiteApi.getDateFromString(request.getParameter("heureDebut"));

                            Visite visite = new Visite();
                            visite.setLogement(logement);
                            visite.setClient(client);
                            visite.setAgent(agent);
                            visite.setHorraire(horraire);
                            visite.setTimestamp(timestamp);
                            if (new VisitesDao().add(visite)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Ajout de la nouvelle visite: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Ajout de la nouvelle visite: false");
                            }
                        } else
                            this.getServletContext().getRequestDispatcher("/programmerVisite/modifierVisite.jsp").forward(request, response);
                        break;
                }
            }
            redirectToDashboard(request, response, error);
        } else {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        }
    }
}
