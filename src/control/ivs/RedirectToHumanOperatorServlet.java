package control.ivs;

import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.voice.Number;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RedirectToHumanOperatorServlet", urlPatterns = MyConsts.RedirectToHumanServlet)
public class RedirectToHumanOperatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Number number = new Number.Builder("+213696689498").build();
        Dial dial = new Dial.Builder().number(number).build();
        VoiceResponse vresponse = new VoiceResponse.Builder().dial(dial).build();
        response.setContentType("text/xml");

        try {
            response.getWriter().write(vresponse.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
