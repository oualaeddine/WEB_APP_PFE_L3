package control.servlets.client.general;

import control.servlets.MyServlet;
import control.system.managers.ClientsManager;
import model.beans.humans.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContactServlet", value = "/Contact")
public class ContactServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (new ClientsManager((Client) request.getSession().getAttribute(LOGGED_IN_USER)).sePlaindre(request)) {
                redirectToDashboard(request, response, ACTION_SUCCESS);
            } else {
                redirectToHome(request, response, ACTION_ERROR);
            }
        } else {
            redirectToHome(request, response, LOGIN_NEEDED_ERROR_ID);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
