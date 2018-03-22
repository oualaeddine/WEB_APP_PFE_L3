package control.servlets.administration;

import control.servlets.MyServlet;
import control.system.managers.AdminsManager;
import control.system.managers.AgentsManager;
import control.system.managers.OperateursManager;
import control.system.managers.SuManager;
import model.beans.Localite;
import model.beans.Logement;
import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AjoutServlet", value = "/AjoutServlet")
public class AjoutServlet extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request)) {
            Object manager = null;
            Employe employe = (Employe) request.getSession().getAttribute(LOGGED_IN_USER);
            switch (employe.getUserType()) {
                case ADMIN:
                    manager = new AdminsManager(employe);
                    break;
                case AGENT:
                    manager = new AgentsManager(employe);
                    break;
                case OPERATEUR:
                    manager = new OperateursManager(employe);
                    break;
                case RESPONSABLE_VENTES:
                    manager = new OperateursManager(employe);
                    break;
                case SU:
                    manager = new SuManager(employe);
            }
            String ajouter = request.getParameter("ajouter");
            if (ajouter != null) {
                switch (ajouter) {
                    case "localite":
                        Localite localite = new Localite();
                        localite.setNom(request.getParameter("nomInput"));
                        System.out.println("Ajout: "+new LocaliteDAO().add(localite));
                        break;
                    case "logement":
                        Logement logement = new Logement();
                        logement.setTitre(request.getParameter("titreInput"));
                        logement.setDescription(request.getParameter("description"));
                        logement.setAdresse(request.getParameter("adresse"));
                        logement.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
                        logement.setLocalite((Localite) new LocaliteDAO().getById(Integer.parseInt(request.getParameter("region"))));
                        logement.setNbrPieces(Integer.parseInt(request.getParameter("nbrPcs")));
                        logement.setNbrSdb(Integer.parseInt(request.getParameter("nbrSdb")));
                        logement.setAvecJardin(!(request.getParameter("jardin")==null));
                        logement.setAvecGarage(!(request.getParameter("garade") == null));
                        logement.setAvecSousSol(!(request.getParameter("soussol") == null));
                        System.out.println(request.getParameter("jardin").equals("jardin"));

                        logement.setEtage(Integer.parseInt(request.getParameter("etage")));
                        logement.setPrix(Double.parseDouble(request.getParameter("prix")));
                        System.out.println("Ajout: "+new LogementDAO().add(logement));
                        break;
                    case "employe":
                        String password = request.getParameter("passwordInput");
                        String confirm = request.getParameter("confirmPassword");
                        if (password.equals(confirm)) {
                            Employe employee = new Employe();
                            employee.setNom(request.getParameter("nomInput"));
                            employee.setPrenom(request.getParameter("prenomInput"));
                            employee.setTel(request.getParameter("inputTel"));
                            employee.setUsername(request.getParameter("usernameInput"));
                            employee.setPassword(password);
                            employee.setAdresse(request.getParameter("adresse"));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                            try {
                                java.util.Date date = sdf.parse(request.getParameter("dateNaissance"));
                                Date date1 = new Date(date.getTime());
                                employee.setDateNaissance(date1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            employee.setEmail(request.getParameter("emailInput"));
                            System.out.println(new EmployeDAO().add(employee));
                        }
                        break;
                    case "assignation":
                        int agent = Integer.parseInt(request.getParameter("agent"));
                        int region = Integer.parseInt(request.getParameter("localite"));
                        System.out.println("Assignation: "+ new AdminsManager(employe).assigner(agent,region));
                        break;
                }
            }
        }
        redirectToDashboard(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
