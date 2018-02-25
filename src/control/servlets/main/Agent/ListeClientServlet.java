package control.servlets.main.Agent;

import control.servlets.MyServlet;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListeClientServlet",value = "/Clients")
public class ListeClientServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jit lel post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (request.getParameter("what") != null) {
                this.getServletContext().getRequestDispatcher("/jsp/ListeClient.jsp").forward(request,response);
            } else if (request.getParameter("client") != null) {

                Client client = (Client) new ClientDAO().getById(Integer.parseInt(request.getParameter("client")));
                if (new ClientDAO().isBanned(client)) System.out.println("retablir:" + new ClientDAO().retablirById(client.getId()));
                else System.out.println("banir: " + new ClientDAO().banById(client.getId()));

                this.getServletContext().getRequestDispatcher("/Clients?what=ban").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/jsp/ListeClient.jsp").forward(request, response);
            }
        }else redirectToLogin(request,response,0);
    }
}
