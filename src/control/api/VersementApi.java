package control.api;

import model.db.daos.LocaliteDAO;
import model.db.daos.VersementDAO;
import utils.JsonUtil;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VersementApi")
public class VersementApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getAll": {
                    responseBody = JsonUtil.versementsListToJsonArray(new VersementDAO().getAll());
                    break;
                }
                case "getTotal": {
                    if (request.getParameter("venteId") != null) {
                        int venete = Integer.parseInt(request.getParameter("venteId"));
                        responseBody = Util.objectToJson(new VersementDAO().getSommeVersementsByVente(venete));
                    }
                    break;
                }
                case "getById": {
                    responseBody = JsonUtil.versementsListToJsonArray(new VersementDAO().getAll());
                    break;
                }

            }
            response.getWriter().append(responseBody);
        }
    }
}
