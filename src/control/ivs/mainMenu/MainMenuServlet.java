package control.ivs.mainMenu;

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

@WebServlet(name = "MainMenuServlet", urlPatterns = IVSConsts.MAIN_MENU_SERVLET_URL)
public class MainMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String language = request.getParameter("language");
        System.out.println("main menu ");

        VoiceResponse voiceResponse;
        switch (language) {
            case "fr": {
                voiceResponse = doMainFr();
                break;
            }
            case "ar": {
                voiceResponse = doMainAr();
                break;
            }
            default: {
                voiceResponse = doMainFr();
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

    private VoiceResponse doMainFr() {

        System.out.println("doMainFr ");

        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.FR_MAIN_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.MAIN_MENU_HANDELER_SERVLET_URL + "?language=fr")
                        .method(HttpMethod.GET)
                        .numDigits(1)
                        .build())
                .build();
    }

    private VoiceResponse doMainAr() {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.AR_MAIN_MENU_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.MAIN_MENU_HANDELER_SERVLET_URL + "?language=ar")
                        .method(HttpMethod.GET)
                        .build())
                .build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
