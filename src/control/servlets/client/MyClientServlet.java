package control.servlets.client;

import control.servlets.MyServlet;
import utils.MyConsts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

        try {
            this.getServletContext().getRequestDispatcher("/jsp/client/test.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected int getLoggedInId(HttpServletRequest request) {
        return super.getLoggedInId(request);
    }

    @Override
    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response, int wrongCredentialsError) throws IOException, ServletException {
        response.sendRedirect(MyConsts.CLIENT_LOGIN_SERVLET_URL + "?error=" + LOGIN_NEEDED_ERROR_ID);
    }


}
