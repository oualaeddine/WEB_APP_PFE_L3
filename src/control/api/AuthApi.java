package control.api;

import com.google.gson.JsonObject;
import control.system.managers.AuthManager;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthApi", urlPatterns = "/authApi")
public class AuthApi extends API {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseBody = "null";
        String action = request.getParameter("action");
        if (action != null)
            switch (action) {
                case "login":
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String type = request.getParameter("type");

                    if (type.equals("client")) {
                        if (new AuthManager().authenticateClient(email, password)) {
                            JsonObject client = new JsonObject();
                            client.addProperty("exists", 1);
                            client.addProperty("userId", new ClientDAO().getByEmail(email).getId());
                            responseBody = client.toString();

                        } else {
                            JsonObject client = new JsonObject();
                            client.addProperty("exists", 0);
                            responseBody = client.toString();
                        }
                    } else {
                        if (new AuthManager().authenticateEmploye(email, password)) {
                            JsonObject agent = new JsonObject();
                            agent.addProperty("exists", 1);
                            agent.addProperty("userId", new EmployeDAO().getByEmail(email).getId());
                            responseBody = agent.toString();
                        } else {
                            JsonObject agent = new JsonObject();
                            agent.addProperty("exists", 0);
                            responseBody = agent.toString();
                        }
                    }
                    break;
            }
        response.getWriter().append(responseBody);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
