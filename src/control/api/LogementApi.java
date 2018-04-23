package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.Localite;
import model.beans.Logement;
import model.beans.RDV;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;
import model.enums.TypeLogement;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

import static utils.Util.objectToJson;


@WebServlet(name = "LogementApi", urlPatterns = MyConsts.LOGEMENT_API_URL_PATTERN)
public class LogementApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "search":
                    Logement logement = new Logement();
                    String type = request.getParameter("type");
//                    TypeLogement typeLogement = type.equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT;
//                    logement.setTypeLogement(typeLogement);
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

                    LinkedList<Logement> logements = new LogementDAO().getAll();

                    // LinkedList<Logement> rdvs = new LogementDAO().getLogementsSelonCriteres(logement, pMax, pMin, sMax, sMin);

                    JsonArray rdvsToReturn = new JsonArray();
                    for (Logement myLogement : logements) {
                        JsonObject jsonObject = new JsonObject();
                        String id = "" + myLogement.getId();
                        String titre = myLogement.getTitre();
                        String description = myLogement.getDescription();
                        String adresse = myLogement.getAdresse();
                        String price = "" + myLogement.getPrix();
                        String superficie = "" + myLogement.getSuperficie();

                        jsonObject.addProperty("id", id);
                        jsonObject.addProperty("titre", titre);
                        jsonObject.addProperty("description", description);
                        jsonObject.addProperty("adresse", adresse);
                        jsonObject.addProperty("price", price);
                        jsonObject.addProperty("superficie", superficie);

                        rdvsToReturn.add(jsonObject);

                    }
                    responseBody = rdvsToReturn.toString();
            }

            response.getWriter().append(responseBody);
        }
    }
}
