package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.Signalement;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import model.db.daos.SignalementDAO;
import utils.JsonUtil;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

import static utils.JsonUtil.objectToJson;

@WebServlet(name = "ClientApi", urlPatterns = MyConsts.CLIENT_API_URL_PATTERN)
public class ClientApi extends API {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append(objectToJson(new ClientDAO().getAll()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        if (request.getParameter("action") != null && request.getParameter("action").equals("getById")) {
//            int clientId = Integer.parseInt(request.getParameter("clientId"));
//            response.getWriter().append(JsonUtil.objectToJson(new ClientDAO().banById(clientId)));
//        }
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "getClientByPhoneNumber":
                    String phoneNumber = request.getParameter("id");
                    Client elclient = new ClientDAO().getByPhone(phoneNumber);
                    response.getWriter().append(JsonUtil.objectToJson(elclient));
                    break;
                case "getSignalementById":
                    int signalementId = Integer.parseInt(request.getParameter("id"));
                    Signalement signalement = (Signalement) new SignalementDAO().getById(signalementId);
                    response.getWriter().append(JsonUtil.objectToJson(signalement));
                    break;
                case "getById":
                    int clientId = Integer.parseInt(request.getParameter("clientId"));
                    response.getWriter().append(JsonUtil.objectToJson(new ClientDAO().getById(clientId)));
                    break;
                case "getAllClients":
                    LinkedList listeDesClients = new ClientDAO().getAll();

                    JsonArray clientsToReturn = new JsonArray();
                    for (Object client : listeDesClients) {
                        JsonObject jsonObject = new JsonObject();
                        String nom = ((Client) client).getNom();
                        String prenom = ((Client) client).getPrenom();
                        String id = ((Client) client).getId() + "";
                        String telephone = ((Client) client).getTel();
                        String isBanned = ((Client) client).isBannedString();
                        String dateDeNaissance = ((Client) client).getDateNaissance().toString();

                        jsonObject.addProperty("id", id);
                        jsonObject.addProperty("nom", nom);
                        jsonObject.addProperty("prenom", prenom);
                        jsonObject.addProperty("telephone", telephone);
                        jsonObject.addProperty("isBanned", isBanned);
                        jsonObject.addProperty("dateDeNaissance", dateDeNaissance);

                        clientsToReturn.add(jsonObject);
                    }

                    response.getWriter().append(clientsToReturn.toString());
            }
        }


    }
}
