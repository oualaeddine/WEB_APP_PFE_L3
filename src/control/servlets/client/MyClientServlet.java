package control.servlets.client;

import control.servlets.MyServlet;
import model.enums.UserType;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyClientServlet", value = "/ClientServlet")

public class MyClientServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.CLIENT) {
                String action = request.getParameter("what");
                if (action == null) {
                    redirectToHome(request, response);
                } else {
                    switch (action) {
                        case "contacter":
                            this.getServletContext().getRequestDispatcher("/jsp/client/contacterSociete.jsp").forward(request, response);
                            break;
                        case "changerMdp":
                            this.getServletContext().getRequestDispatcher("/jsp/client/changerMdp.jsp").forward(request, response);
                            break;
                        case "stats":
                            this.getServletContext().getRequestDispatcher("/jsp/client/statsClient.jsp").forward(request, response);
                            break;
                        case "logements":
                            this.getServletContext().getRequestDispatcher("/jsp/client/logements.jsp").forward(request, response);
                            break;
                        case "myVisits":
                            this.getServletContext().getRequestDispatcher("/jsp/client/clientTable.jsp?page=CLIENT_MY_VISITS").forward(request, response);
                            break;
                        case "mesVentes":
                            this.getServletContext().getRequestDispatcher("/jsp/client/clientTable.jsp?page=CLIENT_MES_VENTES_EN_COURS").forward(request, response);
                            break;
                        case "mesLogements":
                            this.getServletContext().getRequestDispatcher("/jsp/client/clientTable.jsp?page=CLIENT_MES_LOGEMENT_VISITES").forward(request, response);
                            break;
                        case "mesNotifs":
                            this.getServletContext().getRequestDispatcher("/jsp/client/clientTable.jsp?page=CLIENT_MY_NOTIFICATIONS").forward(request, response);
                            break;
                        case "modifierProfil":
                            this.getServletContext().getRequestDispatcher("/jsp/client/modifierProfilClient.jsp").forward(request, response);
                            break;
                    }
                }
            } else {
                redirectToDashboard(request, response);
            }
        } else redirectToHome(request, response);
    }



    @Override
    protected int getLoggedInId(HttpServletRequest request) {
        return super.getLoggedInId(request);
    }

    @Override
    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        response.sendRedirect(MyConsts.CLIENT_LOGIN_SERVLET_URL + "?error=" + wrongCredentialsError);
    }


}
