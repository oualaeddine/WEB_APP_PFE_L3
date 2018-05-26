package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.*;
import model.beans.Logement;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.*;
import model.enums.UserType;
import utils.GoogleMail;
import utils.Util;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "AjoutServlet", value = "/AjoutServlet")
public class AjoutServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Employe loggedInEmploye = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            Object manager = null;
        if (isLoggedIn(request)) {
            int loggedInUserId = (int) request.getSession().getAttribute(LOGGED_IN_USER_ID);
            switch (loggedInEmploye.getUserType()) {
                case ADMIN:
                    manager = new AdminsManager(loggedInEmploye);
                    break;
                case SU:
                    manager = new SuManager(loggedInEmploye);
                    break;
                case RESPONSABLE_VENTES:
                    manager = new ResponsablesVentesManager(loggedInEmploye);
                    break;
                case AGENT:
                    manager = new AgentsManager(loggedInEmploye);
                    break;
                case OPERATEUR:
                    manager = new OperateursManager(loggedInEmploye);
                    break;
                default:
                    manager = new AuthManager();
                    break;
            }
        } else manager = new AuthManager();

            String ajouter = request.getParameter("ajouter");
            if (ajouter != null) {
                switch (ajouter) {
                    case "client":
                        String prenom = request.getParameter("prenomInput");
                        String nom = request.getParameter("nomInput");
                        String email = request.getParameter("emailInput");
                        String tel = request.getParameter("inputTel");
                        String username = request.getParameter("usernameInput");
                        String password = request.getParameter("passwordInput");
                        String adresse = request.getParameter("adresseInput");

                        Client client = new Client();
                        client.setPrenom(prenom);
                        client.setNom(nom);
                        client.setEmail(email);
                        client.setTel(tel);
                        client.setUsername(username);
                        client.setPassword(password);
                        client.setAdresse(adresse);
                        client.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));
                        System.out.println("Ajout client: "+new AuthManager().signupClient(client));
                        break;
                    case "inscriptionEmploye":
                        System.out.println("wsalt hna");
                        try {

                            System.out.println("Inscription:" + ((AuthManager) manager).registerEmploye(request));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "approuvement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).approuverEmploye(request)) {
                                int approvedId = Integer.parseInt(request.getParameter("employeApprouve"));
                                Employe approvedEmploye = (Employe) new EmployeDAO().getById(approvedId);
                                try {
                                    GoogleMail.Send("hchimmobilier", "HchImmobilier1234", approvedEmploye.getEmail(), "", "Approbation du compte", Util.getApprobationEmail(approvedEmploye));
                                    System.out.println("Sent");

                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println();
                        }
                        break;
                    case "localite":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println(((AdminsManager) manager).ajouterLocalite(request));
                        }
                        break;
                    case "logement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println(((AdminsManager) manager).createLogement(request));
                        }
                        break;
                    case "employe":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            try {
                                System.out.println(((AdminsManager) manager).ajouterEmploye(request));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "assignation":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println("Nouvelle assignation: " + ((AdminsManager) manager).assigner(request));
                        }
                        break;
                    case "signalement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) != UserType.CLIENT)
                            System.out.println("Signalement: " + ((EmployeManager) manager).signalerClient(request));
                        break;
                    case "suspend":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println("Suspendu: " + ((AdminsManager) manager).suspendEmployee(request));
                        }
                        break;
                    case "gel":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println("Logement gelé: " + ((AdminsManager) manager).gelerLogement(request));
                        }
                        break;
                    case "ban":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            System.out.println("Client banni: " + ((AdminsManager) manager).bannirClient(request));
                        }
                        break;
                }
            }
        redirectToDashboard(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
