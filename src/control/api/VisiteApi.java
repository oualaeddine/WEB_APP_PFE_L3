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
                String endTime = rdv.getDate().toString() + "T" + getTimeForRdv(rdv.getHorraire());
                jsonObject.addProperty("start", dateTime);
                jsonObject.addProperty("end", endTime);

                rdvsToReturn.add(jsonObject);
            }
            response.getWriter().append(rdvsToReturn.toString());
        }

        if (request.getParameter("action").equals("getFreeAgentForDate")) {
            String date;
            date = request.getParameter("date");

            int rdv = getHorraireFromStringDate(date);
            int logementId = Integer.parseInt(request.getParameter("logementId"));
            Logement logement = (Logement) new LogementDAO().getById(logementId);
            int region = logement.getLocalite().getId();

            RDV myRdv = new RDV();
            myRdv.setDate((java.sql.Date) getDateFromString(date));
            myRdv.setHorraire(rdv);

            System.out.println(myRdv);

            response.getWriter().append(Util.objectToJson(new VisitesDao().getFreeAgentsForVisite(myRdv, region)));
        }

    }

    private static Date getDateFromString(String date) {
        return Util.getDateFromString(date.substring(0, 10));
    }

    private static int getHorraireFromStringDate(String date) {
        String time = date.substring(11, 13);
        System.out.println("time = " + time);
        switch (time) {
            case "08": {
                return 1;
            }
            case "10": {
                return 1;
            }
            case "12": {
                return 1;
            }
            case "14": {
                return 1;
            }
            default:
                return 0;
        }
    }

    private String getTimeForRdv(int horraire) {
        switch (horraire) {
            case 1: {
                return "10:00:00";
            }
            case 2: {
                return "12:00:00";
            }
            case 3: {
                return "14:00:00";
            }
            case 4: {
                return "16:00:00";
            }
            default: {
                return "00:00:00";
            }
        }
    }

    private LinkedList<RDV> getTakenDatesForLogement(int logementId) {

        LinkedList<RDV> takenRdv = new LinkedList<>();
        LinkedList<Visite> l = getVisitesForLogement(logementId);
        for (Visite visite : l) {

            RDV newRdv = new RDV();
            newRdv.setDate(visite.getTimestamp());
            newRdv.setHorraire(visite.getHorraire());
            //System.out.println("getTakenDatesForLogement " + newRdv.toString());
            takenRdv.add(newRdv);
        }

        LinkedList<RDV> l2 = getTakenVisitesForAgents(logementId);
        for (RDV newRdv : l2) {
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
