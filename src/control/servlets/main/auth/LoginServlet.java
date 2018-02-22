package control.servlets.main.auth;

import control.managers.AuthManager;
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

import static utils.Consts.LOGIN_SERVLET_URL;

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
//        if (isLoggedIn(request)) {
//            redirectToDashboard(request, response);
//        } else {
            String username = request.getParameter("userId");
            String password = request.getParameter("password");
            UserType type = Util.getUserTypeFromString(request.getParameter("select")) ;
        if (username != null && password != null && type != null) {
            if (authManager.authenticateByClientType(username, password, type)) {
                System.out.println(true);
                HttpSession session = request.getSession(true);
                switch (type) {
                    case CLIENT:
                        Client client = new Client();
                        client.setUsername(username);
                        client.setPassword(password);
                        session.setAttribute("loggedIn",new ClientDAO().getByUsername(client));
                        session.setAttribute("userType",UserType.CLIENT);
                        this.getServletContext().getRequestDispatcher("/ClientServlet").forward(request,response);
                        break;
                    case OPERATEUR:
                        Operateur operateur = new Operateur();
                        operateur.setUsername(username);
                        operateur.setPassword(password);
                        session.setAttribute("loggedIn",new OperateurDAO().getByUsername(operateur));
                        session.setAttribute("userType",UserType.OPERATEUR);
                        this.getServletContext().getRequestDispatcher("/OperateurServlet").forward(request,response);
                        break;
                    case ADMIN:
                        Admin admin = new Admin();
                        admin.setUsername(username);
                        admin.setPassword(password);
                        session.setAttribute("loggedIn",new AdminsDAO().getByUsername(admin));
                        session.setAttribute("userType",UserType.ADMIN);
                        this.getServletContext().getRequestDispatcher("/Dashboard").forward(request,response);
                        break;
                    case AGENT:
                        Agent agent = new Agent();
                        agent.setUsername(username);
                        agent.setPassword(password);
                        session.setAttribute("loggedIn",new AgentsDAO().getByUsername(agent));
                        session.setAttribute("userType",UserType.AGENT);
                        this.getServletContext().getRequestDispatcher("/AgentServlet").forward(request,response);
                        break;
                    case RESPONSABLE_VENTES:
                        ResponsableVente responsableVente = new ResponsableVente();
                        responsableVente.setUsername(username);
                        responsableVente.setPassword(password);
                        session.setAttribute("loggedIn",new ResponsableVentesDAO().getByUsername(responsableVente));
                        session.setAttribute("userType",UserType.RESPONSABLE_VENTES);
                        this.getServletContext().getRequestDispatcher("/ServiceVentesServlet").forward(request,response);
                        break;
                }
            }else {
                redirectToLogin(request,response,WRONG_CREDENTIALS_ERROR);
            }

        }
//            if (username != null && password != null && type != null)
//                if (authManager.authenticate(username, password, type))
//                    authManager.createSession(username, password, type, request);
//                else
//                    redirectToLogin(request, response, WRONG_CREDENTIALS_ERROR);
//            else
//                redirectToLogin(request, response, MISSING_CREDENTIALS_ERROR);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (isLoggedIn(request)) {
//            redirectToDashboard(request, response);
//        } else {
//            getServletContext().getRequestDispatcher("/html/login.html").forward(request, response);
//        }
        this.getServletContext().getRequestDispatcher("/html/login.html").forward(request,response);
    }


}
