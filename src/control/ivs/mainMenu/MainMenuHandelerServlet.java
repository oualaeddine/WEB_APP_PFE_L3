package control.ivs.mainMenu;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Redirect;
import control.ivs.IVSConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainMenuHandelerServlet", urlPatterns = IVSConsts.MAIN_MENU_HANDELER_SERVLET_URL)
public class MainMenuHandelerServlet extends HttpServlet {

    private String language;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        language = request.getParameter("language");
        String action = request.getParameter("Digits");

        VoiceResponse voiceResponse;
        switch (action) {
            case "1":
                voiceResponse = goProgramVisit();
                break;
            case "2":
                voiceResponse = callOperator();
                break;
            case "3":
                voiceResponse = changeLanguage();
                break;
            default:
                voiceResponse = goMain();
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

    private VoiceResponse goMain() {
        // TODO: 6/6/2018
        return new VoiceResponse
                .Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.WELCOME_MESSAGE_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", language)
                        .build())
                .build();
    }

    private VoiceResponse changeLanguage() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.CHANGE_LANGUAGE_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", language)
                        .build())
                .build();
    }

    private VoiceResponse callOperator() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.CALL_OPERATOR_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", language)
                        .build())
                .build();
    }

    private VoiceResponse goProgramVisit() {
        return new VoiceResponse.Builder()
                .redirect(new Redirect
                        .Builder(IVSConsts.PROGRAM_VISIT_SERVLET_URL)
                        .method(HttpMethod.POST)
                        .option("language", language)
                        .build())
                .build();
    }
}
