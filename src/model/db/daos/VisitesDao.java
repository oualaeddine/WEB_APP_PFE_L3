package model.db.daos;

import model.beans.*;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.EtatVisite;
import utils.Util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class VisitesDao extends DAO {

    public LinkedList<Visite> getNonReporteesForAgent(int agentId) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT distinct v.* FROM visite v WHERE v.agentId=" + agentId + " and v.etat='prevue' AND (SELECT count(rapport.visiteId) from rapport WHERE visiteId=v.id)=0 ;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(result.getInt("horraire"));
                EtatVisite etatVisite = null;
                switch (result.getString("etat")) {

                    case "terminee":
                        etatVisite = EtatVisite.TERMINEE;
                        break;
                    case "prevue":
                        etatVisite = EtatVisite.PROGRAMMEE;
                        break;
                    case "reportee":
                        etatVisite = EtatVisite.REPORTEE;
                        break;
                    case "annulee":
                        etatVisite = EtatVisite.ANNULEE;
                        break;
                }
                visite.setEtatVisite(etatVisite);

                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public void reattribuerAgentVisite(Visite visite) {
        Employe employe = getNewAgentForVisit(visite);
        if (employe != null) {
            try {
                visiteStatement.execute("update visite set agentId=" + employe.getId() + ";");
                Notification notification = new Notification();
                notification.setDestinataire(visite.getAgent());
                notification.setContent("Vous avez une nouvelle visite programmée pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()));
                System.out.println("Notification envoyée à l'agent: " + new EmployeNotificationDAO().add(notification));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            annulerVisite(visite);
            Util.sendEmail(visite.getClient().getEmail(), "Annulation", "Bonjour " + visite.getClient().getFullName() + ", Nous avons le regret de vous informer que votre visite prévue le: " + visite.getTimestamp() + " à " + visite.getHorraire() + " a été annulée du au suspend de l'agent responsable de cette visite et à l'impossibilité de le remplacer. Nous vous invitons à vous rendre sur notre site pour choisir une nouvelle date. <br>Merci de votre compréhension.<br>L'équipe ERITP");
            System.out.println("Visite annulée et email envoyé");
        }
    }

    public LinkedList<RDV> getFreeRdvForNext2months(int regionId) {
        LinkedList<RDV> libres = new LinkedList<>();
        ResultSet result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime today = LocalDateTime.now();
        Date sqlDate = Date.valueOf(today.toLocalDate());
        LinkedList<Visite> prochainesVisites = getProgrammeeFor2months();
        for (int i = 0; i <= 60; i++) {
            for (Visite v : prochainesVisites) {
                int region = new AssignationDAO().getLocaliteByAgent(v.getAgent().getId()).getId();
                if (v.getTimestamp().equals(sqlDate + "0000-00-" + i)) {
                    for (int j = 1; j <= 4; j++) {
                        if (v.getHorraire() != j && region == regionId) {
                            RDV rdv = new RDV();
                            rdv.setHorraire(v.getHorraire());
                            rdv.setDate(v.getTimestamp());
                            libres.add(rdv);
                        }
                    }
                }
            }


        }
        return libres;
    }

    public LinkedList<RDV> getTakenRDVForAgents(int regionId) {
        //Dates et horraires ou tout les agents de la region sont occupés
        ResultSet result;
        LinkedList<RDV> list = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT timestamp, horraire FROM visite v WHERE (SELECT count(employe.id) FROM employe,assignation_region WHERE employe.userType='agent' AND employe.id = assignation_region.agentId AND assignation_region.localiteId=" + regionId + ") = (SELECT count(agentId) FROM visite vv WHERE v.timestamp=vv.timestamp AND vv.horraire=v.horraire);");
            while (result.next()) {
                RDV rdv = new RDV();
                rdv.setDate(result.getDate("timestamp"));
                rdv.setHorraire(Integer.parseInt(result.getString("horraire")));

                list.add(rdv);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employe getNewAgentForVisit(Visite visite) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("" +
                    "SELECT " +
                    "  employe.id, " +
                    "  nom, " +
                    "  prenom, " +
                    "  tel " +
                    "FROM employe " +
                    "  JOIN assignation_region ON id = assignation_region.agentId AND localiteId =" + visite.getLogement().getLocalite().getId() + " AND userType = 'agent' and isApproved=1 and isSuspended=0" +
                    "  , visite " +
                    "WHERE employe.id NOT IN (SELECT agentId " +
                    "                         FROM visite " +
                    "                         WHERE etat='prevue' and horraire = '" + visite.getHorraire() + "' " +
                    "AND timestamp = '" + visite.getTimestamp() + "') and employe.id <> " + visite.getAgent().getId() + "  ORDER BY (SELECT count(visite.id) FROM visite WHERE visite.agentId=employe.id) ASC LIMIT 1;");

            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setTel(result.getString("tel"));
                System.out.println("Resultat " + employe);
                return employe;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Employe getFreeAgentsForVisite(RDV rdv, int regionId) {

        System.out.println("rdv = [" + rdv + "], regionId = [" + regionId + "]");
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("" +
                    "SELECT " +
                    "  employe.id, " +
                    "  nom, " +
                    "  prenom, " +
                    "  tel " +
                    "FROM employe " +
                    "  JOIN assignation_region ON id = assignation_region.agentId AND localiteId =" + regionId + " AND userType = 'agent' and isApproved=1 and isSuspended=0" +
                    "  , visite " +
                    "WHERE employe.id NOT IN (SELECT agentId " +
                    "                         FROM visite " +
                    "                         WHERE etat='prevue' and horraire = '" + rdv.getHorraire() + "' " +
                    "AND timestamp = '" + rdv.getDate().toString() + "') ORDER BY (SELECT count(visite.id) FROM visite WHERE visite.agentId=employe.id) ASC LIMIT 1;");

            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setTel(result.getString("tel"));
                System.out.println("Resultat " + employe);
                return employe;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Visite> getVisitesByAgent(Employe agent) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE agentId = " + agent.getId() + ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                list.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Visite> getVisitesByClient(int clientId) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE clientId=" + clientId + " order by timestamp desc;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                list.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Visite> getVisitesByLogement(Logement logement) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE logementId=" + logement.getId() + " AND visite.etat = 'PREVUE' ");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
               /* visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
         */
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }

                // System.out.println("getVisite" + visite.toString());
                list.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean validerVisite(Visite visite) {
        try {
            visiteStatement.execute("UPDATE visite SET etat='avisPositif' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean annulerVisite(Visite visite) {
        try {
            visiteStatement.execute("UPDATE visite SET etat='annulee' WHERE id=" + visite.getId() + ";");
            Notification notification = new Notification();
            notification.setDestinataire(visite.getAgent());
            notification.setContent("Votre visite prévue pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()) + "a été annulée");
            System.out.println("Notification envoyée à l'agent: " + new EmployeNotificationDAO().add(notification));

            Notification notification1 = new Notification();
            notification1.setDestinataire(visite.getClient());
            notification1.setContent("Votre visite prévue pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()) + "a été annulée");
            System.out.println("Notification envoyée au client: " + new ClientNotificationDAO().add(notification1));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean visiteNegative(Visite visite) {
        try {
            visiteStatement.execute("UPDATE visite SET etat = 'avisNegatif' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reporter(int id) {
        try {
            visiteStatement.execute("UPDATE visite SET etat='reportee' WHERE id=" + id);
            Visite visite = getById(id);
            Notification notification = new Notification();
            notification.setDestinataire(visite.getAgent());
            notification.setContent("Votre visite prévue pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()) + "a été reportée");
            System.out.println("Notification envoyée à l'agent: " + new EmployeNotificationDAO().add(notification));

            Notification notification1 = new Notification();
            notification1.setDestinataire(visite.getClient());
            notification1.setContent("Votre visite prévue pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()) + "a été reportée");
            System.out.println("Notification envoyée au client: " + new ClientNotificationDAO().add(notification1));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employe getByEmail(String email) {
        return null;
    }

    public Visite getById(int id) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE id=" + id + ";");
            if (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                return visite;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "visite");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    public boolean update(Visite visite) {
        return false;
    }

    public boolean add(Visite visite) {
        try {
            visiteStatement.execute("INSERT INTO visite(logementId, agentId, clientId, etat,horraire,timestamp) VALUES (" +
                    visite.getLogement().getId() + "," +
                    visite.getAgent().getId() + "," +
                    visite.getClient().getId() + ",  'prevue' , " + visite.getHorraire() + ",'" + visite.getTimestamp() + "'" +

                    ");");
            Notification notification = new Notification();
            notification.setDestinataire(visite.getAgent());
            notification.setContent("Vous avez une nouvelle visite programmée pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()));
            System.out.println("Notification envoyée à l'agent: " + new EmployeNotificationDAO().add(notification));

            Notification notification1 = new Notification();
            notification1.setDestinataire(visite.getClient());
            notification1.setContent("Vous avez une nouvelle visite programmée pour le: " + visite.getTimestamp() + " à " + Util.getStringFromHorraire(visite.getHorraire()));
            System.out.println("Notification envoyée au client: " + new ClientNotificationDAO().add(notification1));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Visite> getAll() {
        LinkedList<Visite> list = new LinkedList<>();
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite order by id desc;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                list.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("SELECT count(id) FROM visite;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<Visite> getPassee() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE timestamp<current_timestamp;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getProgrammeeFor2months() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE timestamp>=current_date AND timestamp<=(current_date+'0000-02-00');");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getProgrammee() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE timestamp>=current_timestamp AND etat='prevue';");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getPasseeForAgent(int userId) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE visite.timestamp<=current_date and visite.agentId=" + userId + ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getAnnuleesForAgent(int userId) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='annulee' and visite.agentId=" + userId + ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getAnnulee() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='annulee';");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }


    public LinkedList<Visite> getReportee() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='reportee' ORDER BY timestamp DESC ;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    /**
     * Stats
     */

    public int nbrVisitesPrevues() {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where visite.timestamp>=current_date and etat='prevue';");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesReportees() {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where etat='reportee';");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesAnnulees() {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where etat='annulee';");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesPrevuesForMonth(Month month) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where visite.timestamp>=current_date and etat='prevue' and MONTH(visite.timestamp)=" + month.getValue() + " and year(timestamp)=year(current_date) ;");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesReporteesForMonth(Month month) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where etat='reportee' and MONTH(visite.timestamp)=" + month.getValue() + " and year(timestamp)=year(current_date) ;");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesAnnuleesForMonth(Month month) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where etat='annulee' and MONTH(visite.timestamp)=" + month.getValue() + " and year(timestamp)=year(current_date) ;");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int nbrVisitesParRegion(int id) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(visite.id) as nbr from logement,visite where visite.logementId=logement.id and logement.region=" + id + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Localite getMostVisitedRegion() {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("");
            if (result.next()) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Localite> getTopFiveRegions() {
        LinkedList<Localite> localites = new LinkedList<>();
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select distinct l.region,(select count(visite.id) from logement,visite,localite where visite.logementId=logement.id and logement.region=l.region and logement.region=localite.id) as nbrVisites from logement l order by nbrVisites DESC limit 5;");
            while (result.next()) {
                localites.add((Localite) new LocaliteDAO().getById(result.getInt("region")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localites;
    }

    public int nbrVisitesForMonth(Month month) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(id) as nbr from visite where MONTH(visite.timestamp)=" + month.getValue() + " and year(timestamp)=year(current_date) ;");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesForRegionsForMonth(int region, Month month) {
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("select count(visite.id) as nbr from logement,visite where visite.logementId=logement.id and logement.region=" + region + " and MONTH(visite.timestamp)=" + month.getValue() + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public LinkedList<Visite> getTommorrowsScheduledVisits(int horraire) {
        LinkedList<Visite> visites = new LinkedList<>();
        ResultSet result;
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite where timestamp=(current_date + 1) and horraire='" + horraire + "' ;");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<Visite> getProgrammeesForClient(int client) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='prevue' and clientId=" + client + ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }

    public LinkedList<RDV> getTakenRDVForClients(int clientId) {
        LinkedList<Visite> rdvs = getProgrammeesForClient(clientId);
        LinkedList<RDV> result = new LinkedList<>();
        for (Visite visite : rdvs) {
            if (visite.getEtatVisite() == EtatVisite.PROGRAMMEE) {
                RDV rdv = new RDV();
                rdv.setHorraire(visite.getHorraire());
                rdv.setDate(visite.getTimestamp());
                result.add(rdv);
            }
        }
        return result;
    }

    public LinkedList<Visite> getProgrammeesForAgent(int id) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='prevue' and agentId=" + id + ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setTimestamp(result.getDate("timestamp"));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                switch (result.getString("etat")) {
                    case "terminee":
                        visite.setEtatVisite(EtatVisite.TERMINEE);
                        break;
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "reportee":
                        visite.setEtatVisite(EtatVisite.REPORTEE);
                        break;
                    case "annulee":
                        visite.setEtatVisite(EtatVisite.ANNULEE);
                        break;
                }
                visites.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visites;
    }


    public boolean add(Logement logement, Client client, RDV rdv) {
        Employe agent = getFreeAgentsForVisite(rdv, logement.getLocalite().getId());
        if (agent != null) {
            try {
                visiteStatement.execute("insert into visite (logementId, agentId, clientId, `timestamp`, horraire) values (" +
                        logement.getId() + ", " +
                        agent.getId() + ", " +
                        client.getId() + ", " +
                        "'" + rdv.getDate() + "', " +
                        rdv.getHorraire() +
                        ")");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isFree(int h, int j, int m, int y, int logementId) {
        // TODO: 6/11/2018
        return false;
    }
}
