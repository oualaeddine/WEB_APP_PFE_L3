package control.api;

import model.beans.Vente;
import model.beans.Versement;
import model.db.daos.VentesDAO;
import model.db.daos.VersementDAO;
import utils.JsonUtil;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VersementApi", urlPatterns = MyConsts.VERSEMENT_API_URL_PATTERN)
public class VersementApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null && action.equals("add")) {
            Versement versement = new Versement();
            versement.setVente((Vente) new VentesDAO().getById(Integer.parseInt(request.getParameter("selectedVente"))));
            versement.setMontant(Double.parseDouble(request.getParameter("montantInput")));

            System.out.println("Ajout versement: " + new VersementDAO().add(versement));

        } else {


            switch (action) {
                case "getAll": {
                    responseBody = JsonUtil.versementsListToJsonArray(new VersementDAO().getAll());
                    break;
                }
                case "getTotal": {
                    if (request.getParameter("venteId") != null) {
                        int venete = Integer.parseInt(request.getParameter("venteId"));
                        responseBody = "" + new VersementDAO().getSommeVersementsByVente(venete);
                    }
                    break;
                }
                case "getById": {
                    if (request.getParameter("versementId") != null) {
                        int versementId = Integer.parseInt(request.getParameter("versementId"));
                        responseBody = JsonUtil.objectToJson(new VersementDAO().getById(versementId));
                    }
                    break;
                }
                case "getByClient": {
                    if (request.getParameter("clientId") != null) {
                        int clientId = Integer.parseInt(request.getParameter("clientId"));
                        responseBody = JsonUtil.objectToJson(new VersementDAO().getByClient(clientId));
                    }
                    break;
                }
                case "getByVente": {
                    if (request.getParameter("venteId") != null) {
                        int venteId = Integer.parseInt(request.getParameter("venteId"));
                        responseBody = JsonUtil.objectToJson(new VersementDAO().getByVente(venteId));
                    }
                    break;
                }

            }
            response.getWriter().append(responseBody);
        }
        response.sendRedirect("/DashboardServlet");
    }
}
