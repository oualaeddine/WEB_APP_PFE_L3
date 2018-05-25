package control.system.managers;

import control.servlets.MyServlet;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.AuthDAO;
import model.db.daos.ClientDAO;
import model.db.daos.EmployeDAO;
import model.enums.UserType;
import utils.Util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;

public class AuthManager {
    private final AuthDAO authDAO;

    public AuthManager() {
        authDAO = new AuthDAO();
    }

    public boolean registerEmploye(ServletRequest request) throws ParseException {
        Employe employe = new Employe();
        EmployeDAO employeDAO = new EmployeDAO();
        employe.setPrenom(request.getParameter("prenomInput"));
        employe.setNom(request.getParameter("nomInput"));
        employe.setTel(request.getParameter("inputTel"));
        Date dateNaiss = Util.getDateFromString(request.getParameter("dateNaissance"));
        employe.setDateNaissance(dateNaiss);
        employe.setAdresse(request.getParameter("adresseInput"));
        employe.setEmail(request.getParameter("emailInput"));
        employe.setUsername(request.getParameter("usernameInput"));
        employe.setPassword(request.getParameter("passwordInput"));

        return employeDAO.inscriptionEmploye(employe);
    }

    public boolean authenticateClient(String username, String password) {
        Client client = new Client();
        client.setUsername(username);
        client.setPassword(password);
        return authDAO.exists(client, UserType.CLIENT);
    }

    public boolean authenticateEmploye(String username, String password) {
        return new EmployeDAO().authenticate(username, password);
    }

    public void logout(HttpServletRequest request) {
        /* Récupération et destruction de la session en cours */
        request.getSession().invalidate();
    }

    public void createSessionForEmploye(String username, HttpServletRequest request) {
        /* Création ou récupération de la session */
        HttpSession session = request.getSession(true);

        Employe employe;
        EmployeDAO employeDAO = new EmployeDAO();
        if (employeDAO.getByUsername(username) != null) {
            employe = employeDAO.getByUsername(username);
        } else {
            employe = employeDAO.getByEmail(username);
        }
        session.setAttribute(MyServlet.LOGGED_IN_USER, employe);
        session.setAttribute(MyServlet.LOGGED_IN_USER_TYPE, employe.getUserType());
        session.setAttribute(MyServlet.LOGGED_IN_USER_USERNAME, username);
        session.setAttribute(MyServlet.LOGGED_IN_USER_ID, employe.getId());
    }

    public void createSessionForClient(String username, HttpServletRequest request) {
            /* Création ou récupération de la session */
        HttpSession session = request.getSession(true);

        Client client;
        ClientDAO clientDAO = new ClientDAO();
        if (clientDAO.getByUsername(username) != null) {
            client = clientDAO.getByUsername(username);
        } else {
            client = clientDAO.getByEmail(username);
        }
        session.setAttribute(MyServlet.LOGGED_IN_USER, client);
        session.setAttribute(MyServlet.LOGGED_IN_USER_ID, client.getId());
        session.setAttribute(MyServlet.LOGGED_IN_USER_TYPE, UserType.CLIENT);


    }

    public boolean signupClient(Client client) {
        return new ClientDAO().add(client);
    }


    public boolean modifierProfil(Client client) {
        return new ClientDAO().update(client);
    }
}
