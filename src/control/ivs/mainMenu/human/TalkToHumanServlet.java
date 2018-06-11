package control.ivs.mainMenu.human;

import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Dial;
import control.ivs.IVSConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TalkToHumanServlet", urlPatterns = IVSConsts.CALL_OPERATOR_SERVLET_URL)
public class TalkToHumanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .dial(new Dial.Builder().callerId("+13092471806")
                        .number(
                                new com.twilio.twiml.voice.Number.
                                        Builder("+213555113103").
                                        build()
                        )
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
