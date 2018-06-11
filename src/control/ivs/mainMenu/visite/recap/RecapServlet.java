package control.ivs.mainMenu.visite.recap;

import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Say;
import control.ivs.IVSConsts;
import model.beans.Appel;
import model.beans.Logement;
import model.beans.RDV;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        callerNumber = request.getParameter("From");

        Logement logement = new LogementDAO().getLogement(fourchettePrix, region, type, superficie);
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getByPhone(callerNumber);


        boolean added = false;

        if (client != null) {
            Visite rdv = getVisiteLaPlusProche(logement);
            RDV rdv1 = new RDV();
            rdv1.setDate(rdv.getTimestamp());
            rdv1.setHorraire(rdv.getHorraire());

            // TODO: 6/11/2018 jibili agent libre w 7atih f rdv
            rdv.setAgent(new VisitesDao().getFreeAgentsForVisite(rdv1, Integer.parseInt(region)));
            added = new VisitesDao().add(rdv);
        } else {
            System.out.println("mahouch client whab yreserver " + callerNumber);
            Visite rdv = getVisiteLaPlusProche(logement);
            rdv.setLogement(logement);
            Appel appel = new Appel();
            appel.setNumeroTel(callerNumber);
            appel.setVisite(rdv);

            added = new AppelsDAO().add(appel);
        }


        VoiceResponse voiceResponse = getVisiteProgrammedMsg();


        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }

    private VoiceResponse getVisiteProgrammedMsg() {
        return new VoiceResponse.Builder()
                .say(new Say.Builder("vous avez pris un rendez vous")
                        .language(Say.Language.FR_FR)
                        .build())
                .hangup(new Hangup.Builder().build())
                .build();
    }

    private Visite getVisiteLaPlusProche(Logement logement) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();


        int startDay = localDate.getDayOfMonth();
        int startMonth = localDate.getMonthValue();

        for (int m = startMonth - 1; m <= 12; m++) {
            for (int j = startDay; j < 32; j++) {
                Visite visite = new Visite();
                visite.setTimestamp(new Date(2018, m, j));
                if (new VisitesDao().isFree(1, j, m, 2018, logement.getId())) {
                    visite.setHorraire(1);
                    return visite;
                }
                if (new VisitesDao().isFree(2, j, m, 2018, logement.getId())) {
                    visite.setHorraire(1);
                    return visite;
                }
                if (new VisitesDao().isFree(3, j, m, 2018, logement.getId())) {
                    visite.setHorraire(1);
                    return visite;
                }
                if (new VisitesDao().isFree(4, j, m, 2018, logement.getId())) {
                    visite.setHorraire(1);
                    return visite;
                }
            }
        }

        return null;
    }

    private Visite getVisiteLaPlusProche(Logement logement, String client) {
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
