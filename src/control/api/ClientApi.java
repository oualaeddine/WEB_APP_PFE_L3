package control.api;

import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import static utils.Util.objectToJson;

@WebServlet(name = "ClientApi", urlPatterns = MyConsts.CLIENT_API_URL_PATTERN)
public class ClientApi extends API {
    // TODO: 2/20/2018 action getClientByPhoneNumber
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append(objectToJson(new ClientDAO().getAll()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList listeDesClients = new ClientDAO().getAll();
        LinkedList<HashMap> returnList = new LinkedList<>();


        for (Object client : listeDesClients) {
            Client _client = (Client) client;
            HashMap<String, String> clientHM = new HashMap<>();

            clientHM.put("id", "" + _client.getId());
            clientHM.put("nom", "" + _client.getNom());
            clientHM.put("prenom", "" + _client.getPrenom());
            clientHM.put("date", "" + _client.getDateNaissance());
            clientHM.put("telephone", "" + _client.getTel());
            clientHM.put("isBanned", "" + _client.isBannedString());


            returnList.add(clientHM);
        }
        response.getWriter().append(objectToJson(returnList));
    }
}
