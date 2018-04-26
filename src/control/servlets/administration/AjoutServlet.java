package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.*;
import model.beans.Logement;
import model.beans.humans.Employe;
import model.db.daos.AssignationDAO;
import model.db.daos.EmployeDAO;
import model.db.daos.LogementDAO;
import model.db.daos.SignalementDAO;
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
                    case "inscriptionEmploye":
                        System.out.println("wsalt hna");
                        try {

                            System.out.println("Inscription:" + ((AuthManager) manager).registerEmploye(request));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "approuvement":
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
                        break;
                    case "localite":
                        System.out.println(((AdminsManager) manager).ajouterLocalite(request));
                        break;
                    case "logement":
                        System.out.println(((AdminsManager) manager).createLogement(request));
                        break;
                    case "employe":
                        try {
                            System.out.println(((AdminsManager) manager).ajouterEmploye(request));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "assignation":
                        int agent = Integer.parseInt(request.getParameter("agentInput"));
                        int region = Integer.parseInt(request.getParameter("selectRegion"));
                        int assignationId = new AssignationDAO().isAffected(agent);
                        if (assignationId != 0) {
                            System.out.println("Agent déjà assigné, suppression de la 1ere assignation: "+new AssignationDAO().deleteById(assignationId));
                        }
                        System.out.println("Nouvelle assignation: "+ new AdminsManager(loggedInEmploye).assigner(agent,region));
                        break;
                    case "signalement":
                        System.out.println("Client: "+request.getParameter("clientInput")+" Employe: "+getLoggedInUsername(request));
                        System.out.println(new SignalementDAO().add(getLoggedInId(request), Integer.parseInt(request.getParameter("clientInput")), request.getParameter("comment")));
                        break;
                    case "suspend":
                        int employeId = Integer.parseInt(request.getParameter("employeSuspendu"));
                        System.out.println(new EmployeDAO().suspendById(employeId));
                        break;
                    case "gel":
                        int logementId = Integer.parseInt(request.getParameter("logementGele"));
                        Logement logementGele =(Logement) new LogementDAO().getById(logementId);
                        if (logementGele.isGele()) {
                            System.out.println(new LogementDAO().degeler(logementId));
                        } else {
                            System.out.println(new LogementDAO().geler(logementId));
                        }

                }
            }
        redirectToDashboard(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
