package control.api;

import model.beans.humans.Agent;
import model.db.daos.AgentsDAO;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static control.system.Util.objectToJson;

@WebServlet(name = "AgentApi")
public class AgentApi extends HttpServlet implements API {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String _userId = request.getParameter("userId");
        int userId = 0;
        if (_userId != null)
            userId = Integer.parseInt(_userId);
        String password = request.getParameter("password");
        if (password != null || userId == 0) {
            if (checkLogin(UserType.AGENT, userId, password)) {
                AgentsDAO agentsDAO = new AgentsDAO();
                Agent agent = new Agent();
                String action = request.getParameter("action");
                switch (action) {
                    case "add":
                        responseBody = objectToJson(agentsDAO.addAgent(agent));
                        break;
                    case "delete":
                        responseBody = objectToJson(agentsDAO.deleteAgent(agent));
                        break;
                    case "getById":
                        responseBody = objectToJson(agentsDAO.getAgentById(userId));
                        break;
                    case "edit":
                        responseBody = objectToJson(agentsDAO.updateAgen(agent));
                        break;
                    case "changePWD":
                        String newPassword = request.getParameter("action");
                        if (newPassword != null)
                            responseBody = objectToJson(agentsDAO.changeAgentPassword(agent, newPassword));
                        break;
                    default:
                        responseBody = "wrong_action";
                }
            } else {
                responseBody = "auth_failed";
            }
        } else
            responseBody = "error_occured";
        response.getWriter().append(responseBody);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("denied");
    }


}
