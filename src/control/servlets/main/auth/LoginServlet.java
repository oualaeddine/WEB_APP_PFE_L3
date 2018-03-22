package control.servlets.main.auth;

import control.system.managers.AuthManager;
import control.servlets.MyServlet;
import model.beans.humans.*;
import model.db.daos.*;
import model.enums.UserType;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(name = "LoginServlet", urlPatterns = LOGIN_SERVLET_URL)
@WebServlet({"/login"})
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
