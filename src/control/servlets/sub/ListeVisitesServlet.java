package control.servlets.sub;

import control.managers.AdminsManager;
import control.servlets.MyServlet;
import model.beans.humans.Admin;
import model.beans.humans.Agent;
import model.db.daos.AgentsDAO;
import model.db.daos.VisitesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Util.objectToJson;

/*
  this package contains servlets that returns only json data
  selon le parametre GET qu'elles recoient
  */
@WebServlet(name = "ListeVisitesServlet")
public class ListeVisitesServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            String responseBody = "";
            //on recupere l'action voulu
            String action = request.getParameter("action");
            //on verifie si l'action n'est pas vide
            // et selon cette action on effectue une operation
            if (action != null) {
                VisitesDao visitesDAO = new VisitesDao();

                switch (action) {
                    case "getAll": {
                        responseBody = objectToJson(visitesDAO.getAll());
                        break;
                    }
                    case "getPassee": {
                        responseBody = objectToJson(visitesDAO.getPassee());
                        break;
                    }
                    case "getVisiteOfAgent": {
                        String _agentId = request.getParameter("agentId");
                        if (_agentId != null) {
                            int agentId = Integer.valueOf(_agentId);
                            responseBody = objectToJson(visitesDAO.getVisitesByAgent((Agent)new AgentsDAO().getById(agentId)));
                        }
                        break;
                    }
                }

                response.getWriter().append(responseBody);
            } else {
                redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
            }
        }
    }
}

