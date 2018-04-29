package control.servlets.administration.operateur;

import control.servlets.MyServlet;
import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.VentesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjouterVenteServlet", urlPatterns = "/ajouterVente")
public class AjouterVenteServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null)
            switch (action) {
                case "add": {
                    String _logementId = request.getParameter("idLogement");
                    String _clientId = request.getParameter("idClient");

                    if (_clientId != null && _logementId != null) {

                        int clientId = Integer.parseInt(_clientId);
                        int logementId = Integer.parseInt(_logementId);

                        Vente vente = new Vente();

                        Client client = new Client();
                        client.setId(clientId);
                        vente.setClient(client);

                        Logement logement = new Logement();
                        logement.setId(logementId);
                        vente.setLogement(logement);

                        if (new VentesDAO().add(vente))
                            redirectToDashboard(request, response);
                    }
                }
            }
        else
            this.getServletContext().getRequestDispatcher("/programmerVisite/ajouterVente.jsp").forward(request, response);

    }
}
