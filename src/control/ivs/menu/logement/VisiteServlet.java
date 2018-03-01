package control.ivs.menu.logement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VisiteServlet")
public class VisiteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prix = request.getParameter("Digits");
        String chambresNbr = request.getParameter("chambre");
        String region = request.getParameter("region");
// TODO: 2/20/2018 check if prix = 0 -> go to welcome
        if (isLogementExists(prix, region, chambresNbr))
            bookForVisit(prix, region, chambresNbr);
        else
            respondWithNoLogement();


    }

    private boolean isLogementExists(String prix, String region, String chambresNbr) {
        // TODO: 2/20/2018 check if exists with api
        return false;
    }

    private void bookForVisit(String prix, String region, String chambresNbr) {
        // TODO: 2/20/2018 getVisiteFromAPI
        boolean succes = true;
        String date = null;
        if (succes)
            respondWithDate(date);
        else
            respondWithFailure();
    }

    private void respondWithNoLogement() {
// TODO: 2/20/2018
    }

    private void respondWithFailure() {
// TODO: 2/20/2018
    }

    private void respondWithDate(String date) {
// TODO: 2/20/2018
    }
}
