package control.servlets.client.general;

import control.servlets.MyServlet;
import control.servlets.client.MyClientServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Client;
import utils.GoogleMail;
import utils.Util;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "ClientLoginServlet", urlPatterns = "/loginsignup")
public class ClientLoginServlet extends MyClientServlet {
    private AuthManager authManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            String tag = request.getParameter("tag");
            if (tag != null) {
                authManager = new AuthManager();
                switch (tag) {
                    case "login": {
                        try {
                            doLogin(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.getWriter().append("retry time mknch");
                        }
                        break;
                    }
                    case "signup": {
                        doSignup(request, response);
                        break;
                    }
                    case "edit": {
                        doEdit(request, response);
                        break;
                    }

                    default: {
                        redirectToLogin(request, response, 700);
                        break;
                    }
                }
            } else {
                redirectToLogin(request, response, 700);
            }
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) {
        String prenom = request.getParameter("prenomInput");
        String nom = request.getParameter("nomInput");
        String email = request.getParameter("emailInput");
        String tel = request.getParameter("inputTel");
        String username = request.getParameter("usernameInput");
        String password = request.getParameter("passwordInput");
        String adresse = request.getParameter("adresseInput");

        Client client = new Client();
        client.setPrenom(prenom);
        client.setNom(nom);
        client.setEmail(email);
        client.setTel(tel);
        client.setUsername(username);
        client.setPassword(password);
        client.setAdresse(adresse);
        client.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));
        if (authManager.modifierProfil(client)) {

        }
    }

    private void doSignup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String prenom = request.getParameter("prenomInput");
        String nom = request.getParameter("nomInput");
        String email = request.getParameter("emailInput");
        String tel = request.getParameter("inputTel");
        String username = request.getParameter("usernameInput");
        String password = request.getParameter("passwordInput");
        String adresse = request.getParameter("adresseInput");

        Client client = new Client();
        client.setPrenom(prenom);
        client.setNom(nom);
        client.setEmail(email);
        client.setTel(tel);
        client.setUsername(username);
        client.setPassword(password);
        client.setAdresse(adresse);
        client.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));
        if (authManager.signupClient(client)) {
            try {
                GoogleMail.Send("eritpimmobilier", "eritppfe", client.getEmail(), "", "Bienvenue chez ERITP", Util.getWelcomeEmail(client));
                System.out.println("Welcome email sent");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            redirectToLogin(request, response, MyServlet.REGISTRATION_SUCCESS);
        } else {
            redirectToLogin(request, response, MyServlet.REGISTRATION_ERROR);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (authManager.authenticateClient(username, password)) {
            System.out.println("login = " + true);
            authManager.createSessionForClient(username, request);
            redirectToHome(request, response);
        } else {
            System.out.println("login = " + false);
            redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            getServletContext().getRequestDispatcher("/jsp/client/register.jsp").forward(request, response);
        }
    }
}
