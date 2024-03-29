package control.api;

import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.JsonUtil.objectToJson;

/**
 * name = now du servlet
 * urlPatterns = le nom du servlet sur le lien
 */
@WebServlet(name = "AgentApi", urlPatterns = MyConsts.AGENT_API_URL_PATTERN)
public class AgentApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        //on recupere les parametres (userId et password)
        // pour verifier si il a le droit d'effectuer l'operation
        String _userId = request.getParameter("userId");
        int userId = 0;
        if (_userId != null)
            userId = Integer.parseInt(_userId);
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        //on verifie si les parametres ne sont pas vides
        if (password != null && userId != 0 && username != null) {
            //on verifie si il a le droit d'acces
            if (checkLogin(username, password)) {
                EmployeDAO agentsDAO = new EmployeDAO();
                Employe agent = new Employe();
                //on recupere l'action voulu
                String action = request.getParameter("action");
                //on verifie si l'action n'est pas vide
                // et selon cette action on effectue une operation
                if (action != null)
                    switch (action) {
                        case "add": {
                            responseBody = objectToJson(agentsDAO.add(agent));
                            break;
                        }
                        case "delete": {
                            agent.setId(userId);
                            responseBody = objectToJson(agentsDAO.delete(agent));
                            break;
                        }
                        case "getById": {
                            responseBody = objectToJson(agentsDAO.getById(userId));
                            break;
                        }
                        case "edit": {
                            agent.setId(userId);
                            responseBody = objectToJson(agentsDAO.update(agent));
                            break;
                        }
                        case "changePWD": {
                            agent.setId(userId);
                            String newPassword = request.getParameter("action");
                            if (newPassword != null)
                                responseBody = objectToJson(agentsDAO.changePassword(agent.getId(),newPassword));
                            break;
                        }
                        default: {
                            responseBody = ACTION_ERROR;
                            break;
                        }
                    }
                else {
                    responseBody = ACTION_NOT_SET_ERROR;
                }
            } else {
                responseBody = AUTH_ERROR;
            }
        } else {
            responseBody = PARAMETER_NOT_SET_ERROR;
        }
        response.getWriter().append(responseBody);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getById":
                    Employe employe = (Employe) new EmployeDAO().getById(Integer.parseInt(request.getParameter("id")));
                    response.getWriter().append(objectToJson(employe));
                    break;
            }
        } else {
            response.getWriter().append(REQUEST_TYPE_ERROR);
        }
    }
}
