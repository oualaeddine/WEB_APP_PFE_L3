package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.beans.Logement;
import model.beans.RDV;
import model.beans.Visite;
import model.db.daos.LogementDAO;
import model.db.daos.VisitesDao;
import utils.MyConsts;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

@WebServlet(name = "VisiteApi", urlPatterns = MyConsts.VISITE_API_URL_PATTERN)
public class VisiteApi extends API {

    // TODO: 2/20/2018 action : addVisit
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // TODO: 4/21/2018 return liste of possible
        // visites for logementId {id(autoInc),start(dateTtime),end(dateTtime),idAgent


        if (request.getParameter("action").equals("getTakenDates")) {
            int logementId = Integer.parseInt(request.getParameter("logementId"));
            LinkedList<RDV> rdvs = getTakenDatesForLogement(logementId);
            JsonArray rdvsToReturn = new JsonArray();
            for (RDV rdv : rdvs) {
                JsonObject jsonObject = new JsonObject();
                String dateTime = rdv.getDate().toString() + "T" + getTimeForRdv(rdv.getHorraire());
                jsonObject.addProperty("start", dateTime);

                rdvsToReturn.add(jsonObject);
            }
            response.getWriter().append(rdvsToReturn.toString());

        }
        if (request.getParameter("action").equals("getFreeAgentForDate")) {
            Date date = new Date();
            try {
                date = Util.getDateFromString(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int rdv = Integer.parseInt(request.getParameter("rdv"));
            int logementId = Integer.parseInt(request.getParameter("logementId"));
            Logement logement = (Logement) new LogementDAO().getById(logementId);
            int region = logement.getLocalite().getId();
            RDV myRdv = new RDV();
            myRdv.setDate((java.sql.Date) date);
            myRdv.setHorraire(rdv);
            response.getWriter().append(Util.objectToJson(new VisitesDao().getFreeAgentForDate(myRdv, region)));
        }

    }

    private String getTimeForRdv(int horraire) {
        switch (horraire) {
            case 1: {
                return "08:00:00";
            }
            case 2: {
                return "10:00:00";
            }
            case 3: {
                return "12:00:00";
            }
            case 4: {
                return "14:00:00";
            }
            default: {
                return "00:00:00";
            }
        }
    }

    private LinkedList<RDV> getTakenDatesForLogement(int logementId) {

        LinkedList<RDV> takenRdv = new LinkedList<>();

        for (Visite visite : getVisitesForLogement(logementId)) {

            RDV newRdv = new RDV();
            newRdv.setDate(visite.getDate());
            newRdv.setHorraire(visite.getHoraire());

            takenRdv.add(newRdv);
        }

        for (RDV newRdv : getTakenVisitesForAgents(logementId)) {
            if (!takenRdv.contains(newRdv))
                takenRdv.add(newRdv);
        }

        return takenRdv;
    }

    private LinkedList<RDV> getTakenVisitesForAgents(int logementId) {
        Logement logement = (Logement) new LogementDAO().getById(logementId);
        return new VisitesDao().getTakenRDVForAgents(logement.getLocalite().getId());
    }

    private LinkedList<Visite> getVisitesForLogement(int logementId) {
        Logement logement = new Logement();
        logement.setId(logementId);
        return new VisitesDao().getVisitesByLogement(logement);
    }
}
