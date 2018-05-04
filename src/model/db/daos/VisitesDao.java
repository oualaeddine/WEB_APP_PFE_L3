package model.db.daos;

import model.beans.Logement;
import model.beans.RDV;
import model.beans.Visite;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.EtatVisite;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class VisitesDao extends DAO {

    public LinkedList<Visite> getNonReporteesForAgent(int agentId) {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = visiteStatement.executeQuery("SELECT visite.* FROM visite,rapport WHERE visite.id=rapport.visiteId AND visite.agentId=" + agentId + " AND (SELECT count(rapport.visiteId) FROM rapport WHERE visiteId=visite.id)=0 ;");
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
                    "  JOIN assignation_region ON id = assignation_region.agentId AND localiteId =" + regionId + " AND userType = 'agent' " +
                    "  , visite " +
                    "WHERE employe.id NOT IN (SELECT agentId " +
                    "                         FROM visite " +
                    "                         WHERE etat='prevue' and horraire = '" + rdv.getHorraire() + "' " +
                    "AND timestamp = '" + rdv.getDate().toString() + "')LIMIT 1");

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
            System.out.println("ani qbel l while");
            while (result.next()) {
                System.out.println("ani dkhelt f l'while");
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
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE clientId=" + clientId + ";");
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
                            visite.getClient().getId() + ",  'prevue' , " + visite.getHorraire()+",'" +visite.getTimestamp()+"'"+

                    ");");
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
            result = visiteStatement.executeQuery("SELECT * FROM visite");
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
                    case "avisNegatif":
                        visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                        break;
                    case "avisPositif":
                        visite.setEtatVisite(EtatVisite.VALIDEE);
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
            result = visiteStatement.executeQuery("SELECT (count(id)) FROM visite;");
            return result.getInt("id");
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
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "avisNegatif":
                        visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                        break;
                    case "avisPositif":
                        visite.setEtatVisite(EtatVisite.VALIDEE);
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
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE timestamp>current_timestamp AND etat='prevue';");
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
                    case "avisNegatif":
                        visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                        break;
                    case "avisPositif":
                        visite.setEtatVisite(EtatVisite.VALIDEE);
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
                    case "prevue":
                        visite.setEtatVisite(EtatVisite.PROGRAMMEE);
                        break;
                    case "avisNegatif":
                        visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                        break;
                    case "avisPositif":
                        visite.setEtatVisite(EtatVisite.VALIDEE);
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
            result = visiteStatement.executeQuery("SELECT * FROM visite WHERE etat='reportee';");
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
                    case "avisNegatif":
                        visite.setEtatVisite(EtatVisite.NON_VALIDEE);
                        break;
                    case "avisPositif":
                        visite.setEtatVisite(EtatVisite.VALIDEE);
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

}
