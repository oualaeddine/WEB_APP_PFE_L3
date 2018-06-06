package control.ivs.mainMenu.visite.superficie;

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

@WebServlet(name = "SuperficeiMenuHandelerServlet", urlPatterns = IVSConsts.SUPERFICIE_MENU_HANDELER_SERVLET_URL)
public class SuperficeiMenuHandelerServlet extends HttpServlet {
    private String language;
    private String region;
    private String fourchettePrix;
    private String type;
    private String superficie;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        fourchettePrix = request.getParameter("prix");
        region = request.getParameter("region");
        type = request.getParameter("type");
        superficie = request.getParameter("Digits");

        VoiceResponse voiceResponse;

        if (new LogementDAO().isThereLogementsVilla(fourchettePrix, region, type, superficie))
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
                        .Builder(IVSConsts.RECAP_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", language)
                        .option("prix", fourchettePrix)
                        .option("region", region)
                        .option("type", type)
                        .option("superficie", superficie)
                        .build())
                .build();
    }

    private VoiceResponse noLogementsMessage() {
        return getVoiceResponse(language);
    }
}
