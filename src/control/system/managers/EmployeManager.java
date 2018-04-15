package control.system.managers;

import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import utils.Util;

import javax.servlet.ServletRequest;
import java.sql.Date;
import java.text.ParseException;

public class EmployeManager {
    private final Employe loggedInEmploye;
    private final EmployeDAO employeDAO;

    public EmployeManager(Employe loggedInEmploye) {
        this.loggedInEmploye = loggedInEmploye;
        this.employeDAO = new EmployeDAO();
    }

    public boolean register(ServletRequest request) throws ParseException {
        Employe employe = new Employe();
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
}
