package model.db.daos;

import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Agent;
import model.beans.humans.Client;
import model.db.DAO;
import model.enums.EtatVisite;
import sun.rmi.runtime.Log;

import javax.sql.rowset.serial.SerialException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class VisitesDao extends DAO {
    public LinkedList<Visite> getVisitesByAgent(Agent agent) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM visite WHERE agentId=" +agent.getId()+ ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite = getById(result.getInt("id"));
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
            result = statement.executeQuery("SELECT id FROM visite WHERE clientId=" +client.getId()+ ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite = getById(result.getInt("id"));
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
            result = statement.executeQuery("SELECT id FROM visite WHERE logementId=" +logement.getId()+ ";");
            while (result.next()) {
                Visite visite = new Visite();
                visite = getById(result.getInt("id"));
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
    public boolean visiteNegative(Visite visite){
        try {
            statement.execute("UPDATE visite SET etat = 'avisNegatif' WHERE id=" + visite.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean reporter(int id){
        try {
            statement.execute("UPDATE visite SET etat='reportee' WHERE id="+id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Visite getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM visite");
            while (result.next()){
                Visite visite = new Visite();
                visite.setId(result.getInt("id"));
                visite.setLogement((Logement)new LogementDAO().getById(result.getInt("logementId")));
                visite.setAgent((Agent) new AgentsDAO().getById(result.getInt("agentId")));
                visite.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                visite.setDate(result.getDate("timestamp"));
                switch (result.getString("etat")){
                    case "prevue": visite.setEtatVisite(EtatVisite.PROGRAMMEE); break;
                    case "avisNegatif": visite.setEtatVisite(EtatVisite.NON_VALIDEE); break;
                    case "avisPositif": visite.setEtatVisite(EtatVisite.VALIDEE);break;
                    case "reportee": visite.setEtatVisite(EtatVisite.REPORTEE);break;
                }
                return visite;
            }
        }catch (SQLException e){
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
                    visite.getLogement().getId()+","+
                    visite.getAgent().getId()+","+
                    visite.getClient().getId()+
                    ");");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Visite> getAll() {
        LinkedList<Visite> list = new LinkedList<>();

        return null;
    }

    public LinkedList<Visite> getPassee() {
        return null;
    }

    public LinkedList<Visite> getByAgent(int agentId) {
        ResultSet result;
        LinkedList<Visite> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM visite WHERE agentId=" + agentId + ";");
            while (result.next()) {
                Visite visite = getById(result.getInt("id"));
                list.add(visite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
