package control.api;

import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.VentesDAO;
import utils.JsonUtil;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
@WebServlet(name = "VenteApi", urlPatterns = MyConsts.VENTE_API_URL_PATTERN)
public class VenteApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
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
                    case "getById":
                        Vente vente = (Vente) new VentesDAO().getById(Integer.parseInt(request.getParameter("id")));
                        responseBody = JsonUtil.objectToJson(vente);
                        break;
                    case "getAll": {
                        ventes = new VentesDAO().getAll();
                        responseBody = JsonUtil.ventesListToJsonArray(ventes);
                        break;
                    }
                    case "getVenteByClient": {
                        if (request.getParameter("selectedClientId") != null) {
                            Client client = new Client();
                            int clientId = Integer.parseInt(request.getParameter("selectedClientId"));
                            client.setId(clientId);
                            ventes = new VentesDAO().getByClient(client);
                        }
                        responseBody = JsonUtil.ventesListToJsonArray(ventes);
                        break;
                    }
                    case "getEnCoursForClient": {
                        if (request.getParameter("selectedClientId") != null) {
                            Client client = new Client();
                            int clientId = Integer.parseInt(request.getParameter("selectedClientId"));
                            client.setId(clientId);
                            ventes = new VentesDAO().getEnCoursForClient(client);
                        }
                        responseBody = JsonUtil.ventesListToJsonArray(ventes);
                        break;
                    }
                    case "getConfirmed": {
                        ventes = new VentesDAO().getConfirmed();
                        break;
                    }
                    case "getEnCours": {
                        ventes = new VentesDAO().getEnCours();
                        responseBody = JsonUtil.ventesListToJsonArray(ventes);
                        break;
                    }
                }

                response.getWriter().append(responseBody);
            }
        }
    }

}
