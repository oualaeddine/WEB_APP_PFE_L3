package control.servlets.client.general;

import control.servlets.MyServlet;
import control.system.managers.ClientsManager;
import model.beans.Message;
import model.beans.humans.Client;
import model.db.daos.MessageVisiteursDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContactServlet", value = "/Contact")
public class ContactServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            if (new ClientsManager((Client) request.getSession().getAttribute(LOGGED_IN_USER)).contacterSociete(request)) {
                redirectToDashboard(request, response, ACTION_SUCCESS);
            } else {
                redirectToDashboard(request, response, ACTION_ERROR);
            }
        } else {
            String email = request.getParameter("email");
            String objet = request.getParameter("objet");
            String msg = request.getParameter("message");
            String nom = request.getParameter("firstname");
            String prenom = request.getParameter("lastname");
            String tel = request.getParameter("tel");

            Message message = new Message();
            message.setEmail(email);
            message.setObject(objet);
            message.setContent(msg);
            message.setPrenom(prenom);
            message.setNom(nom);
            message.setTel(tel);
            message.setClient(false);

            if (new MessageVisiteursDAO().add(message)) {
                redirectToHome(request, response, ACTION_SUCCESS);
            } else {
                redirectToHome(request, response, ACTION_ERROR);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.getServletContext().getRequestDispatcher("/jsp/client/contacterSociete.jsp").forward(request, response);
        doPost(request, response);
    }
}
