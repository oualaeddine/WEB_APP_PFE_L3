package control.servlets.main.Agent;

import control.system.managers.AgentsManager;
import control.servlets.MyServlet;
import model.beans.Visite;
import model.beans.humans.Agent;
import model.db.daos.VisitesDao;
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
            Visite visite = (Visite) request.getSession().getAttribute("visite");
            System.out.println("EnvoyerRapportServlet visite: "+visite);
            Agent agent = (Agent) request.getSession().getAttribute("user");
            switch (request.getParameter("etatVisite")) {
                case "validee":
                    visite.setEtatVisite(EtatVisite.VALIDEE);
                    System.out.println(new VisitesDao().validerVisite(visite));
                    break;
                case "nonvalidee":
                    visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                    System.out.println(new VisitesDao().visiteNegative(visite));
                    break;
                case "reportee":
                    visite.setEtatVisite(EtatVisite.REPORTEE);
                    System.out.println(new VisitesDao().reporter(visite.getId()));
                    break;
                case "annulee":
                    visite.setEtatVisite(EtatVisite.ANNULEE);
                    System.out.println(new VisitesDao().annulerVisite(visite));
                    break;
            }
            this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request,response);
        }else redirectToLogin(request,response,0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            int visiteId =Integer.parseInt(request.getParameter("visite"));
            Agent logged = (Agent) request.getSession().getAttribute(LOGGED_IN_USER);
            System.out.println("logged In agent: "+logged.getNom()+" "+logged.getPrenom());
            System.out.println("visite id: "+visiteId);
            this.getServletContext().getRequestDispatcher("/jsp/Rapport.jsp").forward(request,response);
        }else
            redirectToLogin(request,response,0);
    }
}
