package control.servlets.client.general;

import control.servlets.MyServlet;
import control.system.managers.ClientsManager;
import model.beans.Message;
import model.beans.Plainte;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import model.db.daos.MessageVisiteursDAO;
import model.db.daos.PlainteDAO;

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
            if (request.getParameter("plainte") == null) {
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
            } else {
                String contenu = request.getParameter("content");
                Client client = (Client) new ClientDAO().getById(Integer.parseInt(String.valueOf(request.getSession().getAttribute(LOGGED_IN_USER_ID))));
                Plainte plainte = new Plainte();
                plainte.setPlaignant(client);
                plainte.setContenu(contenu);

                if (new PlainteDAO().add(plainte)) {
                    redirectToHome(request, response, ACTION_SUCCESS);
                } else {
                    redirectToHome(request, response, ACTION_ERROR);
                }
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
