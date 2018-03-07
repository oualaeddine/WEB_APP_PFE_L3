package control.servlets.main.auth;

import utils.GoogleMail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet({"/ForgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("code") == null) {
            String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String code = "";
            for (int i = 0; i < 6; i++) {
                code = code + candidateChars.charAt((int) (Math.random() * candidateChars.length()));
            }
            String adress = request.getParameter("emailAdress");
            try {
                new GoogleMail().Send("hchimmobilier", "HchImmobilier1234", adress, "", "Récuperation du mot de passe", "Votre code de confirmation est: " + code);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            System.out.println(code);
        } else { //todo: test if token is valid
            
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request,response);
    }
}
