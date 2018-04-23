package control.servlets.client.general;

import control.servlets.MyServlet;
import control.servlets.client.MyClientServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
                            //  int retryTime = Integer.parseInt(request.getParameter("retryNbr"));
                            //   if (retryTime != 3)
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
                    default: {
                        // TODO: 4/15/2018  tag non specifé error
                        //  redirectToLogin(request, response, //l'error ta3na);
                        break;
                    }
                }
            } else {
                // TODO: 4/15/2018  tag non specifé error
                response.getWriter().append("tag null error");
            }
        }
    }

    private void doSignup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO: 4/15/2018 get form -> validate it -> send it to authManager

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
            redirectToLogin(request, response, 0);
        } else {
            // TODO: 4/15/2018 handle signup failed error
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            if (authManager.authenticateClient(username, password)) {
                System.out.println("login = " + true);
                authManager.createSessionForClient(username, request);
                redirectToHome(request, response);
            } else {
                System.out.println("login = " + false);
                redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
            }
        } else {
            redirectToLogin(request, response, MISSING_CREDENTIALS_ERROR);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            getServletContext().getRequestDispatcher("/html/client/register.html").forward(request, response);
        }
    }


}
