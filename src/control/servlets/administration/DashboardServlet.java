package control.servlets.administration;

import control.servlets.MyServlet;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = {"/DashboardServlet"})
public class DashboardServlet extends MyServlet {

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            UserType type = (UserType) request.getSession().getAttribute(LOGGED_IN_USER_TYPE);
            switch (type) {
                case SU:
                    this.getServletContext().getRequestDispatcher("/SUServlet").forward(request, response);
                    break;
                case ADMIN:
                    this.getServletContext().getRequestDispatcher("/AdminServlet").forward(request, response);
                    break;
                case OPERATEUR:
                    this.getServletContext().getRequestDispatcher("/OperateurServlet").forward(request, response);
                    break;
                case AGENT:
                    this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request, response);
                    break;
                case RESPONSABLE_VENTES:
                    this.getServletContext().getRequestDispatcher("/ResVentesServlet").forward(request, response);
                    break;
                case CLIENT:
                    this.getServletContext().getRequestDispatcher("/ClientServlet").forward(request, response);
                    break;
            }
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
