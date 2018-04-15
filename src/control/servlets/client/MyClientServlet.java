package control.servlets.client;

import control.servlets.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyClientServlet")
public class MyClientServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void redirectToHome(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected int getLoggedInId(HttpServletRequest request) {
        return super.getLoggedInId(request);
    }

    @Override
    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        super.redirectToLogin(request, response, wrongCredentialsError);
    }


}
