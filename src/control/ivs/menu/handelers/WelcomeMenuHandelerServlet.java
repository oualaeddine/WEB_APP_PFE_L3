package control.ivs.menu.handelers;


import com.twilio.twiml.Redirect;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WelcomeMenuHandelerServlet", urlPatterns = MyConsts.WelcomeMenuHandelerServlet)
public class WelcomeMenuHandelerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedOption = request.getParameter("Digits");

        VoiceResponse voiceResponse;
        switch (selectedOption) {
            case "1":
                voiceResponse = goToAppartementServlet();
                break;
            case "2":
                voiceResponse = goToChangeLanguageServlet();
                break;
            case "3":
                voiceResponse = goToRedirectToHumanServlet();
                break;
            default:
                voiceResponse = goToErrorServlet();
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

    private VoiceResponse goToErrorServlet() {
        return new VoiceResponse
                .Builder()
                .redirect(new Redirect
                        .Builder()
                        .url(MyConsts.ERROR_SERVLET_URL)
                        .build())
                .build();
    }

    private VoiceResponse goToRedirectToHumanServlet() {
        return new VoiceResponse
                .Builder()
                .redirect(new Redirect
                        .Builder()
                        .url(MyConsts.RedirectToHumanServlet)
                        .build())
                .build();
    }

    private VoiceResponse goToChangeLanguageServlet() {
        return new VoiceResponse
                .Builder()
                .redirect(new Redirect
                        .Builder()
                        .url(MyConsts.ChangeLanguageServlet)
                        .build())
                .build();
    }

    private VoiceResponse goToAppartementServlet() {
        return new VoiceResponse
                .Builder()
                .redirect(new Redirect
                        .Builder()
                        .url(MyConsts.APPARTEMENT_SERVLET_URL)
                        .build())
                .build();
    }

}
