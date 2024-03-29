package control.ivs.mainMenu.visite.rooms;


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

@SuppressWarnings("Duplicates")
@WebServlet(name = "RoomsNumberServlet", urlPatterns = IVSConsts.ROOMS_NUMBER_SERVLET_URL)
public class RoomsNumberServlet extends HttpServlet {
    private String language;
    private String fourchettePrix;
    private String region;
    private String type;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("region");
        type = request.getParameter("type");


        VoiceResponse voiceResponse;
        switch (language) {
            case "fr":
                voiceResponse = handleChoicefr();
                break;
            case "ar":
                voiceResponse = handleChoiceAr();
                break;
            default:
                voiceResponse = handleChoicefr();
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
                .play(new Play.Builder(IVSConsts.AR_NBR_PIECES_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.ROOMS_MENU_HANDELER_SERVLET_URL +
                                "?language=" + language +
                                "&prix=" + fourchettePrix +
                                "&region=" + region +
                                "&type=" + type)
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }

    private VoiceResponse handleChoicefr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.FR_NBR_PIECES_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.ROOMS_MENU_HANDELER_SERVLET_URL +
                                "?language=" + language +
                                "&prix=" + fourchettePrix +
                                "&region=" + region +
                                "&type=" + type
                        )
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }
}
