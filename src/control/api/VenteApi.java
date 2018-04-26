package control.api;

import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.VentesDAO;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "VenteApi")
public class VenteApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody;
        String action = request.getParameter("action");

        LinkedList ventes = new LinkedList();
        if (action != null) {
            if (action.equals("getForLogement")) {
                if (request.getParameter("clientId") != null && request.getParameter("logementId") != null) {
                    Logement logement = new Logement();
                    Client client = new Client();

                    String clientId = request.getParameter("clientId");
                    String logementId = request.getParameter("logementId");

                    client.setId(Integer.parseInt(clientId));
                    logement.setId(Integer.parseInt(logementId));

                    Vente vente = new VentesDAO().getByClientAndlogement(client, logement);
                    responseBody = JsonUtil.objectToJson(vente);
                    response.getWriter().append(responseBody);
                }
            } else {
                switch (action) {
                    case "getAll": {
                        ventes = new VentesDAO().getAll();
                        break;
                    }
                    case "getForClient": {
                        if (request.getParameter("clientId") != null) {
                            Client client = new Client();
                            String clientId = request.getParameter("clientId");
                            client.setId(Integer.parseInt(clientId));
                            ventes = new VentesDAO().getByClient(client);
                        }
                        break;
                    }
                    case "getEnCoursForClient": {
                        if (request.getParameter("clientId") != null) {
                            Client client = new Client();
                            String clientId = request.getParameter("clientId");
                            client.setId(Integer.parseInt(clientId));
                            ventes = new VentesDAO().getEnCoursForClient(client);
                        }
                        break;
                    }
                    case "getConfirmed": {
                        ventes = new VentesDAO().getConfirmed();
                        break;
                    }
                    case "getEnCours": {
                        ventes = new VentesDAO().getEnCours();
                        break;
                    }
                }
                responseBody = JsonUtil.ventesListToJsonArray(ventes);
                response.getWriter().append(responseBody);
            }
        }
    }

}
