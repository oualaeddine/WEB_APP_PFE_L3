package control.servlets.main.Agent;

import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnvoyerRapportServlet",value = "/EtablirRapport")
public class EnvoyerRapportServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {

        }redirectToLogin(request,response,0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            this.getServletContext().getRequestDispatcher("/jsp/EtablirRapport.jsp").forward(request,response);
        }else
            redirectToLogin(request,response,0);
    }
}
