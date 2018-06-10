package control.ivs;

import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Play;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IvsMainMenu", urlPatterns = IVSConsts.WELCOME_MESSAGE_SERVLET_URL)
public class FirstMessageServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("first message");
        // Create a TwiML response and add our friendly message.
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .play(new Play.Builder(IVSConsts.WELCOME_MP3_URL).build())
                .gather(new Gather.Builder()
                        .action(IVSConsts.WELCOME_HANDELER_SERVLET_URL)
                        .method(HttpMethod.POST)
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
