package control.api;

import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VisiteApi", urlPatterns = MyConsts.VISITE_API_URL_PATTERN)
public class VisiteApi extends API {

    // TODO: 2/20/2018 action : getNearestVisitForLogement(nbrPieces,region,prix,etage)
    // TODO: 2/20/2018 action : addVisit
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
