package control.api;

import model.beans.Rapport;
import model.db.daos.RapportDAO;
import model.db.daos.VisitesDao;
import model.enums.EtatClient;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RapportsApi")
public class RapportsApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getAll": {
                    responseBody = JsonUtil.rapportsListToJsonArray(new RapportDAO().getAll());
                    break;
                }
                case "addRapport": {
                    // TODO: 4/26/2018 checkLoggedIn+addRapport (this is for android)
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    int visiteId = Integer.parseInt(request.getParameter("id_visite"));
                    boolean isPresent = request.getParameter("etatClient").equals("PRESENT");
                    boolean avisPositif = (request.getParameter("avis")).equals("true");
                    String commentaire = (request.getParameter("commentaire"));

                    Rapport rapport = new Rapport();
                    rapport.setVisite(new VisitesDao().getById(visiteId));
                    EtatClient etatClient = isPresent ? EtatClient.PRESENT : EtatClient.ABSENT;
                    rapport.setEtatClient(etatClient);
                    rapport.setAvis(avisPositif);
                    rapport.setCommentaire(commentaire);

                    System.out.println("Ajout du rapport: " + new RapportDAO().add(rapport));
                    /*
                        int venete = Integer.parseInt(request.getParameter("venteId"));
                        responseBody = Util.objectToJson(new AgentsManager());
                    */
                    break;
                }
                case "getForLogement": {
                    responseBody = JsonUtil.versementsListToJsonArray(new RapportDAO().getByLogement());
                    break;
                }
                case "getForAgent": {
                    if (request.getParameter("agentId") != null) {
                        int agentId = Integer.parseInt(request.getParameter("agentId"));
                        responseBody = JsonUtil.rapportsListToJsonArray(new RapportDAO().getByAgent(agentId));
                    }
                    break;
                }
            }
            response.getWriter().append(responseBody);
        }
    }
}
