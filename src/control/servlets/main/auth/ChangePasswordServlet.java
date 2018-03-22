package control.servlets.main.auth;

import model.beans.humans.Person;
import model.db.daos.*;
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
        String mdp = request.getParameter("new-mdp");
        String confirmMdp = request.getParameter("confirm-new-mdp");
        if (mdp.equals(confirmMdp)) {
            switch ((UserType) request.getSession().getAttribute("type")) {
                case CLIENT:
                    if (new ClientDAO().changePassword(person.getId(), mdp)) {
                        System.out.println("Password changed successfully");
                    }else System.out.println("Error changing password");
                    break;
                case ADMIN:
                    if (new EmployeDAO().changePassword(person.getId(), mdp)) {
                        System.out.println("Password changed successfully");
                    }else System.out.println("Error changing password");
                    break;
                default:
                    if (new EmployeDAO().changePassword(person.getId(), mdp)) {
                        System.out.println("Password changed successfully");
                    }else System.out.println("Error changing password");
                    break;
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/jsp/changePassword.jsp").forward(request, response);
    }
}
