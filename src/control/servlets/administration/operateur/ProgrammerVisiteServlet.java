package control.servlets.administration.operateur;

import control.api.VisiteApi;
import control.servlets.MyServlet;
import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.LogementDAO;
import model.db.daos.VisitesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ProgrammerVisiteServlet", urlPatterns = "/ProgrammerVisite")
public class ProgrammerVisiteServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if (isLoggedIn(request)) {

        // } else {
        //     redirectToLogin(request, response, 0);
        // }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        if (isLoggedIn(request)) {
            this.getServletContext().getRequestDispatcher("/programmerVisite/programmerVisite.jsp").forward(request, response);
        } else {
            redirectToLogin(request,response,0);
        }*/
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

            System.out.println("Ajout de la visite: " + new VisitesDao().add(visite));
//            this.getServletContext().getRequestDispatcher("/DashboardServlet").forward(request, response);
        } else
            this.getServletContext().getRequestDispatcher("/programmerVisite/index.html").forward(request, response);

    }
}
