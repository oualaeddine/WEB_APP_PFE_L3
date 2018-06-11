package control.ivs.mainMenu.visite.rooms;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Redirect;
import control.ivs.IVSConsts;
import model.db.daos.LogementDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static control.ivs.IVSConsts.getVoiceResponse;

@WebServlet(name = "RoomsMenuHandelerServlet", urlPatterns = IVSConsts.ROOMS_MENU_HANDELER_SERVLET_URL)
public class RoomsMenuHandelerServlet extends HttpServlet {
    private String language;
    private String region;
    private String fourchettePrix;
    private String type;
    private String rooms;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("region");
        type = request.getParameter("type");
        rooms = request.getParameter("Digits");

        VoiceResponse voiceResponse;

        if (new LogementDAO().isThereLogementsAppartement(fourchettePrix, region, type, rooms))
            voiceResponse = goToRecap();
        else
            voiceResponse = noLogementsMessage();

        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }

    private VoiceResponse goToRecap() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.TYPE_MENU_SERVLET_URL +
                        "?language=" + language +
                        "&prix=" + fourchettePrix +
                        "&region=" + region +
                        "&type=" + type +
                        "&rooms=" + rooms)
                        .method(HttpMethod.GET)
                        .build()
                )
                .build();
    }

    private VoiceResponse noLogementsMessage() {
        return getVoiceResponse(language);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
