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
            if (request.getAttribute("banned") == null) {
                this.getServletContext().getRequestDispatcher("/jsp/ListeClient.jsp").forward(request, response);
            } else {
                Client client = (Client) request.getAttribute("banned");
                System.out.println(new ClientDAO().banById(client.getId()));
                request.setAttribute("banned",null);
            }
        }else redirectToLogin(request,response,0);
    }
}
