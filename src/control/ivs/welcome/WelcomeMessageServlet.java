package control.ivs.welcome;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Redirect;
import control.ivs.IVSConsts;
import model.db.daos.CallsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static control.ivs.IVSConsts.WELCOME_MENU_SERVLET_URL;

@WebServlet(name = "WelcomeMessageServlet", urlPatterns = WELCOME_MENU_SERVLET_URL)
public class WelcomeMessageServlet extends HttpServlet {
    private boolean isClient;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String callerNumber = request.getParameter("phone_number_sid");
        isClient = new CallsDAO().isClient(callerNumber);
        VoiceResponse voiceResponse;
        switch (language) {
            case "fr": {
                voiceResponse = doWelcomeFr();
                break;
            }
            case "ar": {
                voiceResponse = doWelcomeAr();
                break;
            }
            default: {
                voiceResponse = doWelcomeFr();
                break;
            }
        }

        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }

    private VoiceResponse doWelcomeAr() {
        if (isClient)
            return new VoiceResponse.Builder()
                    .play(new Play.Builder(IVSConsts.WELCOME_MP3_URL).build())
                    .redirect(new Redirect
                            .Builder(IVSConsts.WELCOME_HANDELER_SERVLET_URL + "?language=ar")
                            .method(HttpMethod.GET)
                            .build())
                    .build();
        else
            return new VoiceResponse.Builder()
                    .play(new Play.Builder(IVSConsts.AR_STANDARD_WELCOME_MESSAGE_MP3_URL).build())
                    .redirect(new Redirect
                            .Builder(IVSConsts.WELCOME_HANDELER_SERVLET_URL + "?language=ar")
                            .method(HttpMethod.GET)
                            .build())
                    .build();
    }

    private VoiceResponse doWelcomeFr() {
        if (isClient)
            return new VoiceResponse.Builder()
                    //   .play(new Play.Builder(IVSConsts.WELCOME_MP3_URL).build())
                    .redirect(new Redirect
                            .Builder(IVSConsts.MAIN_MENU_SERVLET_URL + "?language=fr")
                            .method(HttpMethod.GET)
                            .build())
                    .build();
        else
            return new VoiceResponse.Builder()
                    // .play(new Play.Builder(IVSConsts.FR_STANDARD_WELCOME_MESSAGE_MP3_URL).build())
                    .redirect(new Redirect
                            .Builder(IVSConsts.MAIN_MENU_SERVLET_URL + "?language=fr")
                            .method(HttpMethod.GET)
                            .build())
                    .build();
    }
}
