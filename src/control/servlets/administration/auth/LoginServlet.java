package control.servlets.administration.auth;

import control.system.managers.AuthManager;
import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.MyConsts.LOGIN_SERVLET_URL;

@WebServlet(name = "ClientLoginServlet", urlPatterns = {LOGIN_SERVLET_URL,"/authentification"})
//@WebServlet({"/login"})
public class LoginServlet extends MyServlet {

    private AuthManager authManager;

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
        this.authManager = new AuthManager();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            String username = request.getParameter("userId");
            String password = request.getParameter("password");
            if (username != null && password != null) {
                if (authManager.authenticateEmploye(username,password)) {
                    System.out.println(true);
                    authManager.createSessionForEmploye(username,request);
                    redirectToDashboard(request,response);
                }else
                    redirectToLogin(request,response,WRONG_CREDENTIALS_ERROR);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            getServletContext().getRequestDispatcher("/html/login.html").forward(request, response);
        }
    }


}
