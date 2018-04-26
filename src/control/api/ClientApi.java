package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
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
    // TODO: 2/20/2018 action getClientByPhoneNumber
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append(objectToJson(new ClientDAO().getAll()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action") != null && request.getParameter("action").equals("getById")) {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            response.getWriter().append(JsonUtil.objectToJson(new ClientDAO().banById(clientId)));
        }

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
