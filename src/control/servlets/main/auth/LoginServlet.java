package control.servlets.main.auth;

import control.managers.AuthManager;
import control.servlets.MyServlet;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Consts.LOGIN_SERVLET_URL;

@WebServlet(name = "LoginServlet", urlPatterns = LOGIN_SERVLET_URL)
public class LoginServlet extends MyServlet {

    private AuthManager authManager;

    @Override
    public void init() throws ServletException {
        //todo hna on initialise l'objet li fih les methodes ta3 hed servlet
        super.init();
        this.authManager = new AuthManager();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 2/12/2018
        if (isLoggedIn(request)) {
            redirectToDashboard(request, response);
        } else {
            String username = request.getParameter(LOGGED_IN_USER_USERNAME);
            String password = (String) request.getAttribute(LOGGED_IN_USER_PASSWORD);
            UserType type = (UserType) request.getAttribute(LOGGED_IN_USER_TYPE);
            if (username != null && password != null && type != null)
                if (authManager.authenticate(username, password, type))
                    authManager.createSession(username, password, type, request);
                else
                    redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
            else
                redirectToLogin(request, response, MISSING_CREDENTIALS_ERROR);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //   if (isLoggedIn(request)) {
           // redirectToDashboard(request, response);
      //  } else {
            getServletContext().getRequestDispatcher("/html/login.html").forward(request, response);
     //   }
    }


}
