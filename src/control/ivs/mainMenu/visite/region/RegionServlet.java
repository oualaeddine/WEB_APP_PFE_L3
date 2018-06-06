package control.ivs.mainMenu.visite.region;


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
                .play(new Play.Builder(IVSConsts.AR_REGION_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.REGION_MENU_HANDELER_SERVLET_URL + "?language=ar&prix=" + fourchettePrix)
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }

    private VoiceResponse handleChoicesFr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.FR_REGION_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.REGION_MENU_HANDELER_SERVLET_URL + "?language=fr&prix=" + fourchettePrix)
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }
}
