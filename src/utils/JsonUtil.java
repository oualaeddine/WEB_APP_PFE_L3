package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.*;
import model.beans.humans.Client;
import model.beans.humans.Employe;

import java.util.LinkedList;

public class JsonUtil {

    public static String objectToJson(Object object) {
        String json;
        json = new Gson().toJson(object);
        return json;
    }


    public static String ventesListToJsonArray(LinkedList<Vente> ventes) {
        JsonArray result = new JsonArray();

        for (Vente vente : ventes) {
            JsonObject jsonObject = new JsonObject();

            String id = "" + vente.getId();
            String clientId = "" + vente.getClient().getNom() + " " + vente.getClient().getPrenom();
            String logementId = "" + vente.getLogement().getTitre();
            String etat = "" + vente.getEtatVente();
            String date = "" + vente.getDate();

            jsonObject.addProperty("id", id);
            jsonObject.addProperty("clientId", clientId);
            jsonObject.addProperty("logementId", logementId);
            jsonObject.addProperty("etat", etat);
            jsonObject.addProperty("date", date);

            result.add(jsonObject);
        }

        return result.toString();
    }

    public static String versementsListToJsonArray(LinkedList<Versement> versements) {
        JsonArray result = new JsonArray();

        for (Versement versement : versements) {
            JsonObject jsonObject = new JsonObject();

            String id = "" + versement.getId();
            String date = versement.getDate().toString();
            String montant = "" + versement.getMontant();

            String venteId = "" + versement.getVente().getLogement().getId();

            String clientId = "" + versement.getVente().getClient().getId();
            String clientName = versement.getVente().getClient().getFullName();

            String logementId = "" + versement.getVente().getLogement().getId();
            String logementTitle = "" + versement.getVente().getLogement().getTitre();

            jsonObject.addProperty("id", id);
            jsonObject.addProperty("date", date);
            jsonObject.addProperty("montant", montant);
            jsonObject.addProperty("venteId", venteId);
            jsonObject.addProperty("clientId", clientId);
            jsonObject.addProperty("clientName", clientName);
            jsonObject.addProperty("logementId", logementId);
            jsonObject.addProperty("logementTitle", logementTitle);

            result.add(jsonObject);
        }

        return result.toString();
    }

    public static String logementsListToJsonArray(LinkedList<Logement> logements) {

        JsonArray result = new JsonArray();
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

            result.add(jsonObject);

        }
        return result.toString();
    }

    public static String visitesListToJsonArray(LinkedList<Visite> visites) {


        return null;
    }

    public static String clientsListToJsonArray(LinkedList<Client> clients) {


        return null;
    }

    public static String employeseListToJsonArray(LinkedList<Employe> employee) {


        return null;
    }

    public static String rapportsListToJsonArray(LinkedList<Rapport> rapports) {

        JsonArray result = new JsonArray();
        for (Rapport rapport : rapports) {
            JsonObject jsonObject = new JsonObject();

            String id = "" + rapport.getVisite().getId();
            String commentaire = "" + rapport.getCommentaire();
            String EtatClient = "" + rapport.getEtatClientString();

            String visiteId = "" + rapport.getVisite().getId();
            String dateVisite = "" + rapport.getVisite().getTimestamp();
            String horraireVisite = "" + rapport.getVisite().getHorraire();
            String etatVisite = "" + rapport.getVisite().getEtatVisite();

            String agentId = "" + rapport.getVisite().getAgent().getId();
            String agentFullName = "" + rapport.getVisite().getAgent().getFullName();

            String clientId = "" + rapport.getVisite().getClient().getId();
            String clientFullName = "" + rapport.getVisite().getClient().getFullName();

            String logementId = "" + rapport.getVisite().getLogement().getId();
            String logementTitle = "" + rapport.getVisite().getLogement().getTitre();


            jsonObject.addProperty("id ", id);
            jsonObject.addProperty("commentaire", commentaire);
            jsonObject.addProperty("EtatClient", EtatClient);

            jsonObject.addProperty("visiteId", visiteId);
            jsonObject.addProperty("dateVisite", dateVisite);
            jsonObject.addProperty("horraireVisite", horraireVisite);
            jsonObject.addProperty("etatVisite", etatVisite);

            jsonObject.addProperty("agentId", agentId);
            jsonObject.addProperty("agentFullName", agentFullName);

            jsonObject.addProperty("clientId", clientId);
            jsonObject.addProperty("clientFullName", clientFullName);

            jsonObject.addProperty("logementId", logementId);
            jsonObject.addProperty("logementTitle", logementTitle);

            result.add(jsonObject);

        }
        return result.toString();
    }

    public static String notificationsToJson(LinkedList<Notification> notifications) {
        // TODO: 5/29/2018
        return null;
    }
}
