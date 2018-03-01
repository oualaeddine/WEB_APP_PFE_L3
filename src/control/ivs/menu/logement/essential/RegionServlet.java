package control.ivs.menu.logement.essential;


import com.twilio.twiml.*;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegionServlet")
public class RegionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String chambresNbr = request.getParameter("Digits");
// TODO: 2/20/2018 check if chambresNbr = 0 -> go to welcome

        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .play(new Play.Builder("https://twiliopfe2.000webhostapp.com/Region.mp3").build())
                .gather(new Gather.Builder()
                        .action(MyConsts.FOURCHETE_PRIX_SERVLET_URL + "?chambre=" + chambresNbr)
                        .method(Method.GET)
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
