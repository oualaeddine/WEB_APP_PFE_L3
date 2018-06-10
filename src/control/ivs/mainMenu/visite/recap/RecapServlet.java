package control.ivs.mainMenu.visite.recap;

import com.twilio.twiml.VoiceResponse;
import control.ivs.IVSConsts;
import model.beans.Appel;
import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Client;
import model.db.daos.AppelsDAO;
import model.db.daos.ClientDAO;
import model.db.daos.LogementDAO;
import model.db.daos.VisitesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecapServlet", urlPatterns = IVSConsts.RECAP_SERVLET_URL)
public class RecapServlet extends HttpServlet {

    private String
            language,
            region,
            fourchettePrix,
            type,
            superficie,
            rooms;
    private String callerNumber;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("region");
        type = request.getParameter("type");
        superficie = request.getParameter("superficie");
        rooms = request.getParameter("rooms");

        callerNumber = request.getParameter("number");

        Logement logement = new LogementDAO().getLogement(fourchettePrix, region, type, superficie);
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getByPhone(callerNumber);


        boolean added = false;

        if (client != null) {
            Visite rdv = getVisiteLaPlusProche(logement, client);
            added = new VisitesDao().add(rdv);
        } else {
            Visite rdv = getVisiteLaPlusProche(logement, callerNumber);

            Appel appel = new Appel();
            appel.setNumeroTel(callerNumber);
            appel.setVisite(rdv);

            added = new AppelsDAO().add(appel);
        }


        VoiceResponse voiceResponse;
    }

    private Visite getVisiteLaPlusProche(Logement logement, Client client) {
        return null;
    }

    private Visite getVisiteLaPlusProche(Logement logement, String client) {
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
