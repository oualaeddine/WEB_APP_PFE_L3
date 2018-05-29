package control.servlets.administration.Agent;

import control.servlets.MyServlet;
import control.system.managers.AgentsManager;
import model.beans.humans.Employe;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnvoyerRapportServlet",value = "/NewRapport")
public class EnvoyerRapportServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request) && request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.AGENT) {
            Employe agent = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            AgentsManager agentsManager = new AgentsManager(agent);
            int error;
            if (agentsManager.envoyerRapport(request)) {
                error = ACTION_SUCCESS;
                System.out.println("Rapport: true");
            } else {
                error = ACTION_ERROR;
                System.out.println("Rapport: false");
            }
            this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request,response);
        } else redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request) && request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.AGENT) {
            int visiteId =Integer.parseInt(request.getParameter("visiteRapport"));
            Employe logged = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            System.out.println("logged In agent: "+logged.getNom()+" "+logged.getPrenom());
            System.out.println("visite id: "+visiteId);
            this.getServletContext().getRequestDispatcher("/jsp/Rapport.jsp").forward(request,response);
        }else
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
    }
}
