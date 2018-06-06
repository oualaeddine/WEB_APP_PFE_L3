package control.ivs.mainMenu.visite.type;

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

@WebServlet(name = "TypeLogementServlet")
public class TypeLogementServlet extends HttpServlet {
    private String language;
    private String fourchettePrix;
    private String region;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("region");

        VoiceResponse voiceResponse;
        switch (language) {
            case "fr":
                voiceResponse = handleChoiceFr();
                break;
            case "ar":
                voiceResponse = handleChoiceAr();
                break;
            default:
                voiceResponse = handleChoiceFr();
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

    private VoiceResponse handleChoiceAr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.FR_TYPE_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.TYPE_MENU_HANDELER_SERVLET_URL + "?language=fr&prix=" + fourchettePrix + "&region=" + region)
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }

    private VoiceResponse handleChoiceFr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.AR_TYPE_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.TYPE_MENU_HANDELER_SERVLET_URL + "?language=fr&prix=" + fourchettePrix + "&region=" + region)
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }
}
