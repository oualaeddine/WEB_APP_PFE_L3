package control.ivs.welcome;

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

@WebServlet(name = "WelcomeHandelerServlet", urlPatterns = IVSConsts.WELCOME_HANDELER_SERVLET_URL)
public class WelcomeHandelerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedOption = request.getParameter("Digits");

        System.out.println("welcome handeler");

        VoiceResponse voiceResponse;
        switch (selectedOption) {
            case "1":
                voiceResponse = goFrench();
                break;
            case "2":
                voiceResponse = goArab();
                break;
            default:
                voiceResponse = goFrench();
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

    private VoiceResponse goArab() {
        return new VoiceResponse
                .Builder()
                .play(new Play.Builder(IVSConsts.YOU_CHOOSED_ARABIC_MP3_URL).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.MAIN_MENU_SERVLET_URL + "?language=ar")
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    private VoiceResponse goFrench() {
        return new VoiceResponse
                .Builder()
                .play(new Play.Builder(IVSConsts.YOU_CHOOSED_FRENCH_MP3_URL).build())
                .redirect(new Redirect
                        .Builder(IVSConsts.MAIN_MENU_SERVLET_URL + "?language=fr")
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

}
