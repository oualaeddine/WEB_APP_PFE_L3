package control.system.managers;

import control.servlets.MyServlet;
import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import utils.Util;

import javax.servlet.http.HttpServletRequest;

public class EmployeManager {
    private final EmployeDAO employeDAO;

    public EmployeManager() {
        this.employeDAO = new EmployeDAO();
    }

    public boolean modifierProfil(HttpServletRequest request) {
        String prenom = request.getParameter("prenomInput");
        String nom = request.getParameter("nomInput");
        String tel = request.getParameter("inputTel");
        String date = request.getParameter("dateNaissance");
        String adresse = request.getParameter("adresseInput");
        String email = request.getParameter("emailInput");
        String username = request.getParameter("usernameInput");
        Employe employe = new Employe();
        employe.setId((Integer) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID));
        employe.setPrenom(prenom);
        employe.setNom(nom);
        employe.setTel(tel);
        employe.setDateNaissance(Util.getDateFromString(date));
        employe.setAdresse(adresse);
        employe.setEmail(email);
        employe.setUsername(username);
        return new EmployeDAO().update(employe);
    }

}
