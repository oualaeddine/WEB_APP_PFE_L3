package control.ivs.mainMenu.visite;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Redirect;
import control.ivs.IVSConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProgrammerVisiteMenuServlet", urlPatterns = IVSConsts.PROGRAM_VISIT_SERVLET_URL)
public class ProgrammerVisiteMenuServlet extends HttpServlet {
    private String language;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.language = request.getParameter("language");
        System.out.println("ProgrammerVisiteMenuServlet ");

        VoiceResponse voiceResponse;
        switch (language) {
            case "fr":
                voiceResponse = fourchettePrixFr();
                break;
            case "ar":
                voiceResponse = fourchettePrixAr();
                break;
            default:
                voiceResponse = fourchettePrixFr();
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

    private VoiceResponse fourchettePrixAr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.AR_PROGRAMMER_VISITE_MP3_URL).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.FOURCHETTE_PRIX_MENU_SERVLET_URL + "?language=" + language)
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    private VoiceResponse fourchettePrixFr() {
        System.out.println("fourchettePrixFr");


        return new VoiceResponse.Builder()
                // TODO: 6/10/2018
                // .play(new Play.Builder(IVSConsts.FR_PROGRAMMER_VISITE_MP3_URL).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.FOURCHETTE_PRIX_MENU_SERVLET_URL + "?language=" + language)
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
