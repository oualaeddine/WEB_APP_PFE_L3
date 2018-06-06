package control.api;

import control.servlets.MyServlet;
import control.system.managers.ClientsManager;
import model.beans.Localite;
import model.beans.Logement;
import model.beans.humans.Client;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;
import model.enums.TypeLogement;
import utils.JsonUtil;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet(name = "LogementApi", urlPatterns = MyConsts.LOGEMENT_API_URL_PATTERN)
public class LogementApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String responseBody = "null";
        if (action != null)
            switch (action) {
                case "getMyLogements":
                    int user = Integer.parseInt(request.getParameter("userId"));
                    LinkedList<Logement> myLogements = new LogementDAO().getLogementsForClient(user);
                    responseBody = JsonUtil.logementsListToJsonArray(myLogements);
                    break;
                case "addToWishList":
                    int clientId = Integer.parseInt(request.getParameter("clientId"));
                    int logementId = Integer.parseInt(request.getParameter("logementId"));
                    System.out.println("ajout à la liste de souhaits: " + new ClientsManager((Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER)).ajouterALaListeDeSouhaits(clientId, logementId));
                    break;
            }
        response.getWriter().append(responseBody);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {

            switch (action) {
                case "addToWishList":
                    int clientId = Integer.parseInt(request.getParameter("clientId"));
                    int logementId = Integer.parseInt(request.getParameter("logementId"));
                    System.out.println("ajout à la liste de souhaits: " + new LogementDAO().ajouterALaListeDeSouhaits(clientId, logementId));
                    break;
                case "getRegions": {
                    responseBody = JsonUtil.objectToJson(new LocaliteDAO().getAll());
                    break;
                }
                case "search":
                    Logement logement = new Logement();

                    String type = request.getParameter("type");
                    TypeLogement typeLogement = type.equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT;
                    logement.setTypeLogement(typeLogement);

                    int idLocal = 0;
                    if (!request.getParameter("region").equals("null"))
                        idLocal = Integer.parseInt(request.getParameter("region"));
                    Localite localite = new Localite();
                    localite.setId(idLocal);
                    logement.setLocalite(localite);

                    String[] prix = (request.getParameter("prix")).split(",");
                    double pMin = Double.parseDouble(prix[0]), pMax = Double.parseDouble(prix[1]);

                    String[] superficies = (request.getParameter("superficie")).split(",");
                    double sMin = Double.parseDouble(superficies[0]), sMax = Double.parseDouble(superficies[1]);

                    int nbrPieces = Integer.parseInt(request.getParameter("nbrPieces"));
                    logement.setNbrPieces(nbrPieces);

                    int nbrSdb = Integer.parseInt(request.getParameter("nbrSdb"));
                    logement.setNbrSdb(nbrSdb);

                    int nbrEtages = Integer.parseInt(request.getParameter("nbrEtages"));
                    logement.setEtage(nbrEtages);

                    boolean meuble = request.getParameter("meuble").equals("true");
                    logement.setMeubles(meuble);

                    boolean garage = request.getParameter("garage").equals("true");
                    logement.setAvecGarage(garage);

                    boolean jardin = request.getParameter("jardin").equals("true");
                    logement.setAvecJardin(jardin);

                    boolean soussol = request.getParameter("soussol").equals("true");
                    logement.setAvecSousSol(soussol);

                    LinkedList<Logement> logements = new LogementDAO().getLogementsSelonCriteres(logement, pMax * 1000, pMin * 1000, sMax, sMin);
//                    LinkedList<Logement> logements = new LogementDAO().getAll();

                    responseBody = JsonUtil.logementsListToJsonArray(logements);
            }

            response.getWriter().append(responseBody);
        }
    }
}
