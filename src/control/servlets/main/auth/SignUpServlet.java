package control.servlets.main.auth;

import control.servlets.MyServlet;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import utils.Consts;
import utils.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//@WebServlet(name = "SignUpServlet", urlPatterns = Consts.SIGNUP_SERVLET_URL)
@WebServlet({"/signup"})
public class SignUpServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {        // TODO: 2/18/2018
            RequestDispatcher dispatcher=request.getRequestDispatcher("/login");
            String prenom = request.getParameter("prenomInput");
            String nom = request.getParameter("nomInput");
            String email = request.getParameter("emailInput");
            String tel = request.getParameter("inputTel");
            String username = request.getParameter("usernameInput");
            String password = request.getParameter("passwordInput");
            String confirmPassword = request.getParameter("confirmPassword");
            String adresse = request.getParameter("adresseInput");
            if (password.equals(confirmPassword)) {
                Client client = new Client();
                client.setPrenom(prenom);
                client.setNom(nom);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    java.util.Date date = sdf.parse(request.getParameter("dateNaissance"));
                    Date date1 = new Date(date.getTime());
                    client.setDateNaissance(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                client.setEmail(email);
                client.setTel(tel);
                client.setUsername(username);
                client.setPassword(password);
                client.setAdresse(adresse);
                if (new ClientDAO().add(client)) {
                    redirectToLogin(request,response,0);
                }
            }else{
                System.out.println("Veuillez vérifier votre mot de passe");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (isLoggedIn(request)) {
//            redirectToDashboard(request, response);
//        } else {        // TODO: 2/18/2018
//
//        }
        this.getServletContext().getRequestDispatcher("/html/register.html").forward(request,response);
    }
}
