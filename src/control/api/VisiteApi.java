package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.Logement;
import model.beans.RDV;
import model.beans.Visite;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import model.db.daos.LogementDAO;
import model.db.daos.VisitesDao;
import utils.JsonUtil;
import utils.MyConsts;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
        if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
            Client client = (Client) new ClientDAO().getById(Integer.parseInt(request.getParameter("idClient")));
            int horraire = VisiteApi.getHorraireFromStringDate(request.getParameter("heureDebut"));
            Date timestamp = VisiteApi.getDateFromString(request.getParameter("heureDebut"));

            Visite visite = new Visite();
            visite.setClient(client);
            visite.setHorraire(horraire);
            visite.setTimestamp(timestamp);

            System.out.println("Ajout de la visite: " + new VisitesDao().add(visite));
            this.getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

        if (request.getParameter("action").equals("getFreeAgentForDate")) {
            String date;

            date = request.getParameter("date");


            int region = Integer.parseInt(request.getParameter("region"));

            RDV myRdv = new RDV();
            myRdv.setDate(getDateFromString(date));
            myRdv.setHorraire(getHorraireFromStringDate(date));

            System.out.println(myRdv);
            System.out.println("region" + region);

            response.getWriter().append(JsonUtil.objectToJson(new VisitesDao().getFreeAgentsForVisite(myRdv, region)));
        }

    }

    public static java.sql.Date getDateFromString(String date) {
        String newdate = date.substring(0, 10);
        System.out.println("the fucking date is" + newdate);
        return Util.getDateFromString(newdate);
    }

    public static int getHorraireFromStringDate(String date) {
        String time = date.substring(11, 13);
        System.out.println("time = " + time);
        switch (time) {
            case "08": {
                return 1;
            }
            case "10": {
                return 2;
            }
            case "12": {
                return 3;
            }
            case "14": {
                return 4;
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
