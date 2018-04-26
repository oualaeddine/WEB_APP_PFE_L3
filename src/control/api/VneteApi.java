package control.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.beans.Localite;
import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;
import model.db.daos.VentesDAO;
import utils.Util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "VneteApi")
public class VneteApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");

        LinkedList ventes = new LinkedList();
        if (action != null) {
            switch (action) {
                case "getAll": {
                    ventes = new VentesDAO().getAll();
                    break;
                }
                case "getForLogement": {

                    if (request.getParameter("clientId") != null && request.getParameter("logementId") != null) {
                        Logement logement = new Logement();
                        Client client = new Client();

                        String clientId = request.getParameter("clientId");
                        String logementId = request.getParameter("logementId");

                        client.setId(Integer.parseInt(clientId));
                        logement.setId(Integer.parseInt(logementId));

                        // ventes = new VentesDAO().getByClientAndlogement(client, logement);
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


            response.getWriter().append(responseBody);
        }
    }

}
