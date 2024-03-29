package control.servlets.administration.auth;

import control.servlets.MyServlet;
import model.enums.UserType;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",value = "/logout")
public class LogoutServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            boolean client = (request.getSession().getAttribute(LOGGED_IN_USER_TYPE)) == UserType.CLIENT;
            request.getSession().invalidate();
            if (client) {
                response.sendRedirect("/home");
            } else {
                redirectToLogin(request, response, LOGGED_OUT);
            }
        } else {
            redirectToLogin(request, response, LOGIN_NEEDED_ERROR_ID);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
