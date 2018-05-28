package control.servlets.client.personal;

import control.servlets.MyServlet;
import control.system.managers.ClientsManager;
import model.beans.humans.Client;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet", value = "/Edit")
public class EditProfileServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLoggedIn(request)) {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        } else {
            if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.CLIENT) {
                String action = request.getParameter("action");
                if (action != null) {
                    switch (action) {
                        case "account":
                            if (new ClientsManager((Client) request.getSession().getAttribute(LOGGED_IN_USER)).modifierProfil(request)) {
                                redirectToDashboard(request, response, ACTION_SUCCESS);
                            } else {
                                redirectToDashboard(request, response, ACTION_ERROR);
                            }
                            break;
                        case "password":
                            String newMdp = request.getParameter("newMdp");
                            if (new ClientsManager((Client) request.getSession().getAttribute(LOGGED_IN_USER)).modifierMotDePasse(newMdp)) {
                                redirectToDashboard(request, response, ACTION_SUCCESS);
                            } else {
                                redirectToDashboard(request, response, ACTION_ERROR);
                            }
                    }
                } else {
                    redirectToDashboard(request, response);
                }
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        }
    }
}
