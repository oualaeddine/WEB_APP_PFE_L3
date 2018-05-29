package control.ivs.menu.logement.essential;


import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Play;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.twilio.http.HttpMethod.GET;

@WebServlet(name = "FourchetteArgentServlet")
public class FourchetteArgentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("Digits");
        String chambresNbr = req.getParameter("chambre");
// TODO: 2/20/2018 check if region = 0 -> go to welcome

        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
              .play(new Play.Builder(MyConsts.PRIX_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(MyConsts.GET_VISIT_SERVLET_URL + "?chambre=" + chambresNbr + "&region=" + region)
                        .method(GET)
                        .numDigits(1)
                        .build())
                .build();

        resp.setContentType("application/xml");
        try {
            String respo = voiceResponse.toXml();
            resp.getWriter().print(respo);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
