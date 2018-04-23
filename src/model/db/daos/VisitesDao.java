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
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class VisitesDao extends DAO {
    public LinkedList<RDV> getTakenRDVForAgents(int regionId) {
        //Dates et horraires ou tout les agents de la region sont occupés
        ResultSet result;
        LinkedList<RDV> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT timestamp, horraire FROM visite v WHERE (SELECT count(employe.id) FROM employe,assignation_region WHERE employe.userType='agent' AND employe.id = assignation_region.agentId AND assignation_region.localiteId="+regionId+") = (SELECT count(agentId) FROM visite vv WHERE v.timestamp=vv.timestamp AND vv.horraire=v.horraire);");
            while (result.next()) {
                RDV rdv =  new RDV();
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
    public int getFreeAgentsForVisite(RDV rdv, int regionId) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT e.id FROM employe e,visite,assignation_region ar WHERE e.id=visite.agentId AND ar.agentId=e.id AND ar.localiteId="+regionId+" AND (SELECT count(visite.id) FROM visite WHERE agentId=e.id AND timestamp='"+rdv.getDate()+"' AND horraire='"+rdv.getHorraire()+"');");
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<Visite> getVisitesByAgent(Employe agent) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM visite WHERE agentId=" + agent.getId() + ";");
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

    public LinkedList<Visite> getVisitesByClient(Client client) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM visite WHERE clientId=" + client.getId() + ";");
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

    public LinkedList<Visite> getVisitesByLogement(Logement logement) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM visite WHERE logementId=" + logement.getId() + " AND date >= current_date AND date <= (current_date + '0000-02-00')  ;");
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

    public boolean validerVisite(Visite visite) {
        try {
            statement.execute("UPDATE visite SET etat='avisPositif' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean annulerVisite(Visite visite) {
        try {
            statement.execute("UPDATE visite SET etat='annulee' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean visiteNegative(Visite visite) {
        try {
            statement.execute("UPDATE visite SET etat = 'avisNegatif' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reporter(int id) {
        try {
            statement.execute("UPDATE visite SET etat='reportee' WHERE id=" + id);
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
            result = statement.executeQuery("SELECT * FROM visite WHERE id=" + id + ";");
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
            statement.execute("INSERT INTO visite(logementId, agentId, clientId, etat) VALUES (" +
                    visite.getLogement().getId() + "," +
                    visite.getAgent().getId() + "," +
                    visite.getClient().getId() +
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
            result = statement.executeQuery("SELECT * FROM visite");
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

    public LinkedList<Visite> getPassee() {
        ResultSet result;
        LinkedList<Visite> visites = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM visite WHERE timestamp<current_timestamp;");
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
            result = statement.executeQuery("SELECT * FROM visite WHERE timestamp>current_timestamp;");
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
            result = statement.executeQuery("SELECT * FROM visite WHERE etat='annulee';");
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
            result = statement.executeQuery("SELECT * FROM visite WHERE etat='reportee';");
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
