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
        int error = 0;
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
                        if (new AuthManager().signupClient(client)) {
                            error = ACTION_SUCCESS;
                            System.out.println("Ajout réussi");
                        } else {
                            error = ACTION_ERROR;
                            System.out.println("Ajout non effectué");
                        }
                        break;
                    case "inscriptionEmploye":
                        try {
                            if (((AuthManager) manager).registerEmploye(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Inscription: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Inscription: false");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "approuvement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).approuverEmploye(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Approuvement: true");
                                int approvedId = Integer.parseInt(request.getParameter("employeApprouve"));
                                Employe approvedEmploye = (Employe) new EmployeDAO().getById(approvedId);
                                try {
                                    GoogleMail.Send("eritpimmobilier", "eritppfe", approvedEmploye.getEmail(), "", "Approbation du compte", Util.getApprobationEmail(approvedEmploye));
                                    System.out.println("Sent");

                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Approuvement: false");
                            }
                        }
                        break;
                    case "localite":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).ajouterLocalite(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Ajout: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Ajout: false");
                            }
                        }
                        break;
                    case "logement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).createLogement(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Ajout logement: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Ajout logement: false");
                            }
                        }
                        break;
                    case "employe":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            try {
                                if (((AdminsManager) manager).ajouterEmploye(request)) {
                                    error = ACTION_SUCCESS;
                                    System.out.println("Ajout employe: true");
                                } else {
                                    error = ACTION_ERROR;
                                    System.out.println("Ajout employe: false");
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "assignation":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).assigner(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Assignation: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Assignation: false");
                            }
                        }
                        break;
                    case "signalement":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) != UserType.CLIENT) {
                            if (new EmployeManager(loggedInEmploye).signalerClient(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Signalement: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Signalement: false");
                            }
                        }
                        break;
                    case "suspend":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).suspendEmployee(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Suspend: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Suspend: false");
                            }
                        }
                        break;
                    case "gel":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).gelerLogement(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Logement gelé: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Logement gelé: false");
                            }
                        }
                        break;
                    case "ban":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.SU) {
                            if (((AdminsManager) manager).bannirClient(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Ajout: true");
                            } else {
                                error = ACTION_ERROR;
                                System.out.println("Ajout: false");
                            }
                        }
                        break;
                    case "annulationVente":
                        if (request.getSession().getAttribute(LOGGED_IN_USER_TYPE) == UserType.RESPONSABLE_VENTES) {
                            if (((ResponsablesVentesManager) manager).annulerVente(request)) {
                                error = ACTION_SUCCESS;
                                System.out.println("Annulation: true");
                            }
                        } else {
                            error = ACTION_ERROR;
                            System.out.println("Annulation: false");
                        }
                        break;
                }
            }
        redirectToDashboard(request, response, error);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
