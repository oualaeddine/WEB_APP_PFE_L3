package control.ivs.mainMenu.visite.region;

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

@WebServlet(name = "RegionMenuHandelerServlet")
public class RegionMenuHandelerServlet extends HttpServlet {
    private String language;
    private String region;
    private String fourchettePrix;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("Digits");

        VoiceResponse voiceResponse;
        if (new LogementDAO().isThereLogements(fourchettePrix, region))
            switch (language) {
                case "fr":
                    voiceResponse = goTypeFr();
                    break;
                case "ar":
                    voiceResponse = goTypeAr();
                    break;
                default:
                    voiceResponse = goTypeFr();
                    break;
            }
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

    private VoiceResponse goTypeAr() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.TYPE_MENU_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", "ar")
                        .option("prix", fourchettePrix)
                        .option("region", region)
                        .build())
                .build();
    }

    private VoiceResponse goTypeFr() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.TYPE_MENU_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", "ar")
                        .option("prix", fourchettePrix)
                        .option("region", region)
                        .build())
                .build();
    }

    private VoiceResponse noLogementsMessage() {
        return getVoiceResponse(language);
    }
}
