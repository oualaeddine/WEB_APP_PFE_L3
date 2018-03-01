package control.ivs;

import com.twilio.twiml.*;
import utils.MyConsts;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = MyConsts.WELCOME_SERVLET_URL)
public class WelcomeServlet extends javax.servlet.http.HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .play(new Play.Builder("https://twiliopfe2.000webhostapp.com/Welcome.mp3").build())
                .gather(new Gather.Builder()
                        .action(MyConsts.WelcomeMenuHandelerServlet)
                        .numDigits(1)
                        .build())
                .build();

        response.setContentType("application/xml");
        try {
            String resp = voiceResponse.toXml();
            response.getWriter().print(resp);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
