package control.api;

import control.servlets.MyServlet;
import model.beans.Appel;
import model.db.daos.AppelsDAO;
import utils.JsonUtil;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AppelsApi", urlPatterns = MyConsts.APPELS_API_URL)
public class AppelsApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getById":
                    Appel appel = (Appel) new AppelsDAO().getById(Integer.parseInt(request.getParameter("id")));
                    responseBody = JsonUtil.objectToJson(appel);
                    break;
            }
            response.getWriter().append(responseBody);
        }
    }
}
