package control.ivs.mainMenu.visite.region;


import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Redirect;
import com.twilio.twiml.voice.Say;
import control.ivs.IVSConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegionServlet", urlPatterns = IVSConsts.REGION_MENU_SERVLET_URL)
public class RegionServlet extends HttpServlet {
    private String fourchettePrix;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        fourchettePrix = request.getParameter("Digits");

        VoiceResponse voiceResponse;
        switch (language) {
            case "fr":
                voiceResponse = handleChoicesFr();
                break;
            case "ar":
                voiceResponse = handleChoicesAr();
                break;
            default:
                voiceResponse = handleChoicesFr();
                break;
        }
        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }

    private VoiceResponse handleChoicesAr() {
        return new VoiceResponse.Builder()
                .say(new Say.Builder("seules les logements dans la region de constantine sont pris en charge par ce systeme").language(Say.Language.FR_FR).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.REGION_MENU_HANDELER_SERVLET_URL + "?language=fr&prix=" + fourchettePrix + "&Digits=" + 2)
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    private VoiceResponse handleChoicesFr() {
        return new VoiceResponse.Builder()
                //.play(new Play.Builder(IVSConsts.FR_REGION_MENU_MP3_URL).build())
                .say(new Say.Builder("seules les logements dans la region de constantine sont pris en charge par ce systeme").language(Say.Language.FR_FR).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.REGION_MENU_HANDELER_SERVLET_URL + "?language=fr&prix=" + fourchettePrix + "&Digits=" + 2)
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
