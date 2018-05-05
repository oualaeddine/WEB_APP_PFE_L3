package control.servlets.client;

import control.servlets.MyServlet;
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
            String action = request.getParameter("what");
            if (action == null) {
                redirectToHome(request, response);
            } else {
                switch (action) {
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

                }
            }
        } else {
            redirectToLogin(request, response, 0);
        }
    }

    protected void redirectToHome(HttpServletRequest request, HttpServletResponse response) {

        try {
            this.getServletContext().getRequestDispatcher("/home").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected int getLoggedInId(HttpServletRequest request) {
        return super.getLoggedInId(request);
    }

    @Override
    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        response.sendRedirect(MyConsts.CLIENT_LOGIN_SERVLET_URL + "?error=" + LOGIN_NEEDED_ERROR_ID);
    }


}
