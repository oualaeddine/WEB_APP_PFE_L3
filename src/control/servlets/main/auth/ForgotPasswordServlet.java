package control.servlets.main.auth;

import control.servlets.MyServlet;
import control.system.managers.AuthManager;
import model.beans.humans.Agent;
import model.beans.humans.Employe;
import model.db.daos.AgentsDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.OttDAO;
import model.enums.UserType;
import utils.GoogleMail;
import utils.Util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

import static control.servlets.MyServlet.LOGGED_IN_USER;
import static control.servlets.MyServlet.LOGGED_IN_USER_TYPE;

@WebServlet({"/ForgotPassword"})
public class ForgotPasswordServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("code");
        if (token == null) {

            String adress = request.getParameter("emailAdress");
            UserType userType = Util.getUserTypeFromString(request.getParameter("selectType"));
            Employe employe = new EmployeDAO().getByEmail(adress, userType);

            String code = new OttDAO().generateNewToken(employe.getId());
            try {
                new GoogleMail().Send("hchimmobilier", "HchImmobilier1234", adress, "", "Récuperation du mot de passe", Util.getForgotPasswordEmail(adress,userType,code) );

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            System.out.println(code);
        } else { //todo: test if token is valid
            
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("linsa") == null) {
            this.getServletContext().getRequestDispatcher("/html/forgot-password.html").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            if (Util.getUserTypeFromString(request.getParameter("wech"))!=null) {
                UserType userType = Util.getUserTypeFromString(request.getParameter("wech"));
                String username = request.getParameter("linsa");
                new AuthManager().createSession(username,userType,request);
                redirectToDashboard(request,response);
            }

            session.setAttribute(LOGGED_IN_USER_TYPE,"");
            this.getServletContext().getRequestDispatcher("/changePassword").forward(request,response);
        }

    }
}
