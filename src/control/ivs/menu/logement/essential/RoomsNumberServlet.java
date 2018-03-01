package control.ivs.menu.logement.essential;


import com.twilio.twiml.Gather;
import com.twilio.twiml.Play;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoomsNumberServlet",urlPatterns = MyConsts.ROOMS_NUMBER_SERVLET_URL)
public class RoomsNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .play(new Play.Builder("https://twiliopfe2.000webhostapp.com/Appartement.mp3").build())
                .gather(new Gather.Builder()
                        .action(MyConsts.REGION_SERVLET_URL)
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
