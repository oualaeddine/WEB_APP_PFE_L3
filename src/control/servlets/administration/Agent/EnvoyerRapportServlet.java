package control.servlets.administration.Agent;

import control.servlets.MyServlet;
import control.system.managers.AgentsManager;
import model.beans.Rapport;
import model.beans.Visite;
import model.beans.humans.Employe;
import model.db.daos.RapportDAO;
import model.db.daos.VisitesDao;
import model.enums.EtatClient;
import model.enums.EtatVisite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnvoyerRapportServlet",value = "/NewRapport")
public class EnvoyerRapportServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            Employe agent = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            AgentsManager agentsManager = new AgentsManager(agent);
            System.out.println(agentsManager.envoyerRapport(request));
            this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request,response);
        }else redirectToLogin(request,response,0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            int visiteId =Integer.parseInt(request.getParameter("visiteRapport"));
            Employe logged = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            System.out.println("logged In agent: "+logged.getNom()+" "+logged.getPrenom());
            System.out.println("visite id: "+visiteId);
            this.getServletContext().getRequestDispatcher("/jsp/Rapport.jsp").forward(request,response);
        }else
            redirectToLogin(request,response,0);
    }
}
