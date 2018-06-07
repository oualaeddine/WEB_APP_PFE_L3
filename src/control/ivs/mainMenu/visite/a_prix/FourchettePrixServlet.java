package control.ivs.mainMenu.visite.a_prix;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Play;
import control.ivs.IVSConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FourchettePrixServlet", urlPatterns = IVSConsts.FOURCHETTE_PRIX_MENU_SERVLET_URL)
public class FourchettePrixServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");

        VoiceResponse voiceResponse;
        switch (language) {
            case "fr":
                voiceResponse = goRegionFr();
                break;
            case "ar":
                voiceResponse = goRegionAr();
                break;
            default:
                voiceResponse = goRegionFr();
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

    private VoiceResponse goRegionAr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.AR_FOURCHETTE_PRIX_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.REGION_MENU_SERVLET_URL + "?language=ar")
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }

    private VoiceResponse goRegionFr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.FR_FOURCHETTE_PRIX_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.REGION_MENU_SERVLET_URL + "?language=fr")
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }
}
