package control.servlets.administration.auth;

import control.servlets.MyServlet;
import model.beans.humans.Person;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/ChangePassword"})
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person =(Person) request.getSession().getAttribute("user");
        String mdp = request.getParameter("newMdp");
        switch ((UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE)) {
            case CLIENT:
                if (new ClientDAO().changePassword(person.getId(), mdp)) {
                    System.out.println("Password changed successfully");
                } else System.out.println("Error changing password");
                break;
            default:
                if (new EmployeDAO().changePassword(person.getId(), mdp)) {
                    System.out.println("Password changed successfully");
                } else System.out.println("Error changing password");
                break;
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/jsp/changePassword.jsp").forward(request, response);
    }
}
