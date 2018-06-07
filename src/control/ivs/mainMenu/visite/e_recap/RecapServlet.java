package control.ivs.mainMenu.visite.e_recap;

import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import control.ivs.IVSConsts;
import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Person;
import model.db.daos.LogementDAO;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecapServlet", urlPatterns = IVSConsts.RECAP_SERVLET_URL)
public class RecapServlet extends HttpServlet {
    private String language;
    private String region;
    private String fourchettePrix;
    private String type;
    private String superficie;
    private String rooms;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("a_region");
        type = request.getParameter("type");
        superficie = request.getParameter("superficie");
        rooms = request.getParameter("rooms");

        Logement logement = new LogementDAO().trouverUnLogementAvecLesCriteres(fourchettePrix, region, type, superficie, rooms);
        // TODO: 6/7/2018 nkhammemou f kifah ndirou hkayt client anonymous

        VoiceResponse voiceResponse;
        Visite visite = new Visite();


        voiceResponse = goVisiteSucces();
        notifyVisite(visite);


        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }


    }

    private void notifyVisite(Visite visite) {

        String notification = "vous avez une visite programmée " +
                " date : " + visite.getTimestamp().toString() +
                " heure : " + Util.getStringFromHorraire(visite.getHorraire()) +
                " logement : " + visite.getLogement().getId();

        notifierEmail(visite.getClient(), notification);
        notifierSms(visite.getClient(), notification);

        notifierEmail(visite.getAgent(), notification);
        notifierSms(visite.getAgent(), notification);

    }

    private void notifierSms(Person person, String notification) {
        Util.sendSms(person.getTel(), notification);
    }

    private void notifierEmail(Person person, String notification) {
        Util.sendSms(person.getEmail(), notification);

    }

    private VoiceResponse goVisiteSucces() {
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
