package control.servlets.administration.auth;

import control.servlets.MyServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//@WebServlet(name = "SignUpServlet", urlPatterns = MyConsts.SIGNUP_SERVLET_URL)
@WebServlet({"/signup"})
public class SignUpServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            try {
                if (new AuthManager().registerEmploye(request)) {
                    System.out.println("Inscription: true");
                    redirectToLogin(request, response, REGISTRATION_SUCCESS);
                } else {
                    System.out.println("Inscription: false");
                    redirectToLogin(request, response, REGISTRATION_ERROR);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/html/register.html").forward(request,response);
    }
}
