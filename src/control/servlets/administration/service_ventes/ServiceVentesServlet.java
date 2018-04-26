package control.servlets.administration.service_ventes;

import control.servlets.MyServlet;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServiceVentesServlet", value = "/ServiceVentesServlet")
public class ServiceVentesServlet extends MyServlet {

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            String what = request.getParameter("what");
            if (what == null) {
                this.getServletContext().getRequestDispatcher("/jsp/responsable_ventes.jsp").forward(request, response);
            } else {
                switch (what) {
                    case "AdminsMessages":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=ADMINISTRATION_MESSAGES_FOR_EMPLOYEES").forward(request, response);
                        break;
                    case "ajouterVersement":
                        this.getServletContext().getRequestDispatcher("/ajouterVersement").forward(request, response);
                        break;
                    case "versementsParVente":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VERSEMENTS_FOR_VENTE").forward(request, response);
                        break;
                    case "versementsParClient":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VERSEMENTS_FOR_USER").forward(request, response);
                        break;
                    case "ventesEnAttente":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VENTES_EN_ATTENTE").forward(request, response);
                        break;
                    case "ventesConfirmees":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=CONFIRMED_VENTES").forward(request, response);
                        break;
                    case "ventesAnnulees":
                        this.getServletContext().getRequestDispatcher("/jsp/table.jsp?page=VENTES_ANNULEES").forward(request, response);
                        break;
                    case "modifierProfil":
                        this.getServletContext().getRequestDispatcher("/jsp/modifierProfil.jsp").forward(request, response);
                        break;
                    case "changePassword":
                        this.getServletContext().getRequestDispatcher("/jsp/changePassword.jsp").forward(request, response);
                        break;
                    case MyConsts.LOGOUT_SERVLET_URL:
                        this.getServletContext().getRequestDispatcher("/logout").forward(request, response);
                        break;

                }
            }
        } else {
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }
}
