package control.api;

import control.system.managers.AgentsManager;
import model.db.daos.RapportDAO;
import model.db.daos.VersementDAO;
import utils.JsonUtil;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RapportsApi")
public class RapportsApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getAll": {
                    responseBody = JsonUtil.rapportsListToJsonArray(new RapportDAO().getAll());
                    break;
                }
                case "addRapport": {
                    // TODO: 4/26/2018 checkLoggedIn+addRapport (this is for android)
                    /*
                        int venete = Integer.parseInt(request.getParameter("venteId"));
                        responseBody = Util.objectToJson(new AgentsManager());
                    */
                    break;
                }
                case "getForLogement": {
                    responseBody = JsonUtil.versementsListToJsonArray(new RapportDAO().getByLogement());
                    break;
                }
                case "getForAgent": {
                    if (request.getParameter("agentId") != null) {
                        int agentId = Integer.parseInt(request.getParameter("agentId"));
                        responseBody = JsonUtil.rapportsListToJsonArray(new RapportDAO().getByAgent(agentId));
                    }
                    break;
                }
            }
            response.getWriter().append(responseBody);
        }
    }
}
