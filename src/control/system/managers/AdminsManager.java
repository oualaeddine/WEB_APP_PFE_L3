package control.system.managers;

import model.beans.Localite;
import model.beans.Location;
import model.beans.Logement;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.daos.*;
import model.enums.AdminRole;
import model.enums.TypeLogement;
import model.enums.UserType;
import utils.Util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;

public class AdminsManager {
    private final Employe loggedInAdmin;

    public AdminsManager(Employe loggedInAdmin) {
        this.loggedInAdmin = loggedInAdmin;
    }


    public Employe creerCompte(String nom, String prenom, Date dateNaiss, String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended,AdminRole role){
        Employe admin = new Employe();
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setDateNaissance(dateNaiss);
        admin.setAdresse(adresse);
        admin.setTel(tel);
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setSuspended(isSuspended);
        switch (role){
            case SUPER_USER:
                admin.setUserType(UserType.SU); break;
            case ADMIN:
                admin.setUserType(UserType.ADMIN); break;
        }
        return admin;
    }
    public boolean reintegrerEmploye(int userId){
        return new EmployeDAO().reintegrerById(userId);
    }
    public boolean suspendEmployee(int userId) {
        return new EmployeDAO().suspendById(userId);
    }

    public boolean createClient(Client client){
        return new ClientDAO().add(client);
    }
    public boolean deleteClient(Client client){
        return new ClientDAO().delete(client);
    }


    public boolean createEmploye(Employe employe) {
        if (employe.getUserType() == UserType.ADMIN) {
            if (loggedInAdmin.getUserType() == UserType.SU) {
                Employe admin = employe;
                admin.setUserType(UserType.ADMIN);
                return new EmployeDAO().add(admin);
            }
        } else {
            return new EmployeDAO().add(employe);
        }
        return false;
    }

    public boolean createLogement(ServletRequest request){
        Logement logement = new Logement();
        logement.setTitre(request.getParameter("titreInput"));
        logement.setDescription(request.getParameter("description"));
        logement.setAdresse(request.getParameter("adresse"));
        logement.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
        logement.setLocalite((Localite) new LocaliteDAO().getById(Integer.parseInt(request.getParameter("region"))));
        logement.setNbrPieces(Integer.parseInt(request.getParameter("nbrPcs")));
        logement.setNbrSdb(Integer.parseInt(request.getParameter("nbrSdb")));
        logement.setAvecJardin(!(request.getParameter("jardin")==null));
        logement.setAvecGarage(!(request.getParameter("garage") == null));
        logement.setAvecSousSol(!(request.getParameter("soussol") == null));
        logement.setMeubles(!(request.getParameter("meubles") == null));
        logement.setEtage(Integer.parseInt(request.getParameter("etage")));
        logement.setPrix(Double.parseDouble(request.getParameter("prix")));
        Location location = new Location();
        location.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        logement.setLocation(location);
        logement.setTypeLogement(request.getParameter("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
       return new LogementDAO().add(logement);
    }
    public boolean deleteLogement(Logement logement){
        return new LogementDAO().delete(logement);
    }

    public boolean createLocalite(Localite localite){
        return new LocaliteDAO().add(localite);
    }
    public boolean deleteLocalite(Localite localite){
        return new LocaliteDAO().delete(localite);
    }

    public boolean gelerLogement(Logement logement){
        return new LogementDAO().geler(logement.getId());
    }
    public boolean approuverEmploye(ServletRequest request){
        return new EmployeDAO().approuverEmploye(Integer.parseInt(request.getParameter("employeApprouve")), loggedInAdmin.getId(), request.getParameter("userTypeInput"));
    }

    public void imprimerFacture(int numVente){
        //TODO:Imprimer facture
    }


    public boolean assigner(int agent, int region) {
        return new AssignationDAO().add(agent, region);
    }

    public boolean ajouterLocalite(HttpServletRequest request) {

        return false;
    }

    public boolean ajouterEmploye(HttpServletRequest request) throws ParseException {
        Employe employee = new Employe();
        employee.setNom(request.getParameter("nomInput"));
        employee.setPrenom(request.getParameter("prenomInput"));
        employee.setTel(request.getParameter("inputTel"));
        employee.setUsername(request.getParameter("usernameInput"));
        employee.setPassword(request.getParameter("passwordInput"));
        employee.setUserType(Util.getUserTypeFromString(request.getParameter("select")));
        employee.setAdresse(request.getParameter("adresseInput"));
        employee.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));
        employee.setEmail(request.getParameter("emailInput"));
        employee.setCreator(loggedInAdmin.getId());
        return new EmployeDAO().add(employee);
    }
}
