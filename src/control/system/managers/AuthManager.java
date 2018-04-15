package control.system.managers;

import control.servlets.MyServlet;
import model.beans.humans.*;
import model.db.daos.*;
import model.enums.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthManager {
    private final AuthDAO authDAO;

    public AuthManager() {
        authDAO = new AuthDAO();
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

        /* Mise en session des attributs de l'utilisateur */
        Employe employe = new EmployeDAO().getByUsername(username);
        session.setAttribute(MyServlet.LOGGED_IN_USER, employe);
        session.setAttribute(MyServlet.LOGGED_IN_USER_TYPE, employe.getUserType());
        session.setAttribute(MyServlet.LOGGED_IN_USER_USERNAME, username);
        session.setAttribute(MyServlet.LOGGED_IN_USER_ID, employe.getId());

    }

    public void createSessionForClient(String username, HttpServletRequest request) {
        // TODO: 4/15/2018 ines implement this method
            /* Création ou récupération de la session */
        HttpSession session = request.getSession(true);


    }

    public boolean signupClient(Client client) {
        return new ClientDAO().add(client);
    }
}
