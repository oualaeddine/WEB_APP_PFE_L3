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

    private boolean authenticateOperateur(String username, String password) {
        Operateur operateur = new Operateur();
        operateur.setUsername(username);
        operateur.setPassword(password);
        return authDAO.exists(operateur, UserType.OPERATEUR);
    }

    private boolean authenticateClient(String username, String password) {
        Client client = new Client();
        client.setUsername(username);
        client.setPassword(password);
        return authDAO.exists(client, UserType.CLIENT);
    }

    private boolean authenticateAgent(String username, String password) {
        Agent agent = new Agent();
        agent.setUsername(username);
        agent.setPassword(password);
        return authDAO.exists(agent, UserType.AGENT);
    }

    private boolean authenticateAdmin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return authDAO.exists(admin, UserType.ADMIN);
    }

    private boolean authenticateResponsableVentes(String username, String password) {
        ResponsableVente responsableVente = new ResponsableVente();
        responsableVente.setUsername(username);
        responsableVente.setPassword(password);
        return authDAO.exists(responsableVente, UserType.RESPONSABLE_VENTES);
    }

    public void logout(HttpServletRequest request) {
        /* Récupération et destruction de la session en cours */
        request.getSession().invalidate();
    }

    public boolean authenticate(String username, String password, UserType type) {
        // UserType _type = Util.getUserTypeFromString(type);
        return type != null && authenticateByClientType(username, password, type);
    }

    public boolean authenticateByClientType(String username, String password, UserType type) {
        switch (type) {
            case CLIENT:
                return authenticateClient(username, password);
            case AGENT:
                return authenticateAgent(username, password);
            case OPERATEUR:
                return authenticateOperateur(username, password);
            case ADMIN:
                return authenticateAdmin(username, password);
            case RESPONSABLE_VENTES:
                return authenticateResponsableVentes(username, password);
            default:
                return false;
        }
    }

    public void createSession(String username, String password, UserType type, HttpServletRequest request) {
        /* Création ou récupération de la session */
        HttpSession session = request.getSession();

        /* Mise en session des attributs de l'utilisateur */
        session.setAttribute(MyServlet.LOGGED_IN_USER_USERNAME, username);
        session.setAttribute(MyServlet.LOGGED_IN_USER_PASSWORD, password);
        session.setAttribute(MyServlet.LOGGED_IN_USER_TYPE, type);
        try {
            session.setAttribute(MyServlet.LOGGED_IN_USER_ID, new AuthDAO().getUserIdBy(username, type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}