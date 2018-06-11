package control.system.managers;

import model.beans.Localite;
import model.beans.Location;
import model.beans.Logement;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.ContactInfosDAO;
import model.db.daos.*;
import model.enums.AdminRole;
import model.enums.TypeLogement;
import model.enums.UserType;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;

@SuppressWarnings("ALL")
public class AdminsManager {
    private final Employe loggedInAdmin;

    public AdminsManager(Employe loggedInAdmin) {
        this.loggedInAdmin = loggedInAdmin;
    }


    public Employe creerCompte(String nom, String prenom, Date dateNaiss, String adresse, String tel, String email, String username, String password, int addedBy, boolean isSuspended, AdminRole role) {
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
        switch (role) {
            case SUPER_USER:
                admin.setUserType(UserType.SU);
                break;
            case ADMIN:
                admin.setUserType(UserType.ADMIN);
                break;
        }
        return admin;
    }

    public boolean reintegrerEmploye(int userId) {
        return new EmployeDAO().reintegrerById(userId);
    }

    public boolean suspendEmployee(HttpServletRequest request) {
        int employeId = Integer.parseInt(request.getParameter("employeSuspendu"));
        Employe employe = (Employe) new EmployeDAO().getById(employeId);
        if (employe.isSuspended()) {
            Util.sendEmail(employe.getEmail(), "Vous avez été réintegré", Util.getReintegrerEmployeEmail(employe));
            return new EmployeDAO().reintegrerById(employeId);
        } else {
            Util.sendEmail(employe.getEmail(), "Vous avez été suspendu", Util.getSuspendreEmail(employe));
            return new EmployeDAO().suspendById(employeId);
        }
    }

    public boolean createClient(Client client) {
        return new ClientDAO().add(client);
    }

    public boolean deleteClient(HttpServletRequest request) {
        int client = Integer.parseInt(request.getParameter("deletedClient"));
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

    public boolean createLogement(HttpServletRequest request) {
        Logement logement = new Logement();
        logement.setTitre(request.getParameter("titreInput"));
        logement.setDescription(request.getParameter("description"));
        logement.setAdresse(request.getParameter("adresse"));
        logement.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
        logement.setLocalite((Localite) new LocaliteDAO().getById(Integer.parseInt(request.getParameter("region"))));
        logement.setNbrPieces(Integer.parseInt(request.getParameter("nbrPcs")));
        logement.setNbrSdb(Integer.parseInt(request.getParameter("nbrSdb")));
        logement.setAvecJardin(!(request.getParameter("jardin") == null));
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


        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = null;
        try {
            filePart = request.getPart("photo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            try {
                inputStream = filePart.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        logement.setPicture(inputStream);


        return new LogementDAO().add(logement);
    }

    public boolean deleteLogement(HttpServletRequest request) {
        int logementId = Integer.parseInt(request.getParameter("deletedLogementId"));
        return new LogementDAO().delete(logementId);
    }

    public boolean createLocalite(Localite localite) {
        return new LocaliteDAO().add(localite);
    }

    public boolean deleteLocalite(Localite localite) {
        return new LocaliteDAO().delete(localite);
    }

    public boolean gelerLogement(HttpServletRequest request) {
        int logementId = Integer.parseInt(request.getParameter("logementGele"));
        Logement logementGele = (Logement) new LogementDAO().getById(logementId);
        if (logementGele.isGele()) {
            return (new LogementDAO().degeler(logementId));
        } else {
            return (new LogementDAO().geler(logementId));
        }

    }

    public boolean approuverEmploye(ServletRequest request) {
        return new EmployeDAO().approuverEmploye(Integer.parseInt(request.getParameter("employeApprouve")), loggedInAdmin.getId(), request.getParameter("userTypeInput"));
    }


    public boolean assigner(HttpServletRequest request) {
        int agent = Integer.parseInt(request.getParameter("agentInput"));
        int region = Integer.parseInt(request.getParameter("selectRegion"));
        int assignationId = new AssignationDAO().isAffected(agent);
        if (assignationId != 0) {
            System.out.println("Agent déjà assigné, suppression de la 1ere assignation: " + new AssignationDAO().deleteById(assignationId));
        }
        return new AssignationDAO().add(agent, region);
    }

    public boolean ajouterLocalite(HttpServletRequest request) {
        String nom = request.getParameter("nomInput");
        Localite localite = new Localite();
        localite.setNom(nom);
        return new LocaliteDAO().add(localite);
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

    public boolean bannirClient(HttpServletRequest request) {
        int clientBanniId = Integer.parseInt(request.getParameter("clientBanni"));
        Client client1 = (Client) new ClientDAO().getById(clientBanniId);
        if (client1.isBanned()) {
            Util.sendEmail(client1.getEmail(), "Votre compte a été rétabli", Util.getRetablirClientEmail(client1));
            return new ClientDAO().retablirById(clientBanniId);
        } else {
            Util.sendEmail(client1.getEmail(), "Vous avez été bannis", Util.getBannirEmail(client1));
            return new ClientDAO().banById(clientBanniId);
        }
    }

    public boolean modifierContactInfos(HttpServletRequest request) {
        ContactInfosDAO contactInfosDAO = new ContactInfosDAO();
        String nomSociete = request.getParameter("nomInput");
        String tel = request.getParameter("inputTel");
        String adresse = request.getParameter("adresseInput");
        String email = request.getParameter("emailInput");
        String description = request.getParameter("descriptionInput");

        return new ContactInfosDAO().update(nomSociete, email, tel, adresse, description);
    }

    public boolean deleteEmploye(HttpServletRequest request) {
        int employeId = Integer.parseInt(request.getParameter("employeId"));
        return new EmployeDAO().deleteById(employeId);
    }

    public boolean modifierEmploye(HttpServletRequest request) {
        int employeId = Integer.parseInt(request.getParameter("employeModifie"));
        String prenom = request.getParameter("prenomInput");
        String nom = request.getParameter("nomInput");
        String tel = request.getParameter("inputTel");
        String date = request.getParameter("dateNaissance");
        String adresse = request.getParameter("adresseInput");
        String email = request.getParameter("emailInput");
        Employe employe = new Employe();
        employe.setId(employeId);
        employe.setPrenom(prenom);
        employe.setNom(nom);
        employe.setTel(tel);
        employe.setDateNaissance(Util.getDateFromString(date));
        employe.setAdresse(adresse);
        employe.setEmail(email);

        return new EmployeDAO().update(employe);
    }

    public boolean modifierLogement(HttpServletRequest request) {
        Logement logement = new Logement();
        logement.setTitre(request.getParameter("titreInput"));
        logement.setDescription(request.getParameter("description"));
        logement.setAdresse(request.getParameter("adresse"));
        logement.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
        logement.setLocalite((Localite) new LocaliteDAO().getById(Integer.parseInt(request.getParameter("region"))));
        logement.setNbrPieces(Integer.parseInt(request.getParameter("nbrPcs")));
        logement.setNbrSdb(Integer.parseInt(request.getParameter("nbrSdb")));
        logement.setAvecJardin(!(request.getParameter("jardin") == null));
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
        return new LogementDAO().update(logement);
    }

    public boolean modifierClient(HttpServletRequest request) {
        String prenom = request.getParameter("clientprenomInput");
        String nom = request.getParameter("clientnomInput");
        String email = request.getParameter("clientemailInput");
        String tel = request.getParameter("clientinputTel");
        String adresse = request.getParameter("clientadresseInput");

        Client client = new Client();
        client.setId(Integer.parseInt(request.getParameter("clientModifie")));
        client.setPrenom(prenom);
        client.setNom(nom);
        client.setEmail(email);
        client.setTel(tel);
        client.setAdresse(adresse);
        client.setDateNaissance(Util.getDateFromString(request.getParameter("dateNaissance")));

        return new ClientDAO().update(client);
    }
}
