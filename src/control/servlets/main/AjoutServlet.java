package control.servlets.main;

import control.servlets.MyServlet;
import model.beans.Localite;
import model.beans.Logement;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjoutServlet", value = "/AjoutServlet")
public class AjoutServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            String ajouter = request.getParameter("ajouter");
            if (ajouter != null) {
                switch (ajouter) {
                    case "localite":
                        Localite localite = new Localite();
                        localite.setNom(request.getParameter("nomInput"));
                        System.out.println("Ajout: "+new LocaliteDAO().add(localite));
                        break;
                    case "logement":
                        Logement logement = new Logement();
                        logement.setTitre(request.getParameter("titreInput"));
                        logement.setDescription(request.getParameter("description"));
                        logement.setAdresse(request.getParameter("adresse"));
                        logement.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
                        logement.setLocalite((Localite) new LocaliteDAO().getById(Integer.parseInt(request.getParameter("region"))));
                        logement.setNbrPieces(Integer.parseInt(request.getParameter("nbrPcs")));
                        logement.setNbrFacades(Integer.parseInt(request.getParameter("nbrFcds")));
                        logement.setAvecJardin(!(request.getParameter("jardin")==null));
                        System.out.println(request.getParameter("jardin").equals("jardin"));

                        logement.setEtage(Integer.parseInt(request.getParameter("etage")));
                        logement.setPrix(Double.parseDouble(request.getParameter("prix")));
                        System.out.println("Ajout: "+new LogementDAO().add(logement));
                        break;
                }
            }
        }
        redirectToDashboard(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
