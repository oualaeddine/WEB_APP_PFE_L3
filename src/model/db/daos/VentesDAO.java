package model.db.daos;

import model.beans.Logement;
import model.beans.Vente;
import model.beans.Visite;
import model.beans.humans.Agent;
import model.beans.humans.Client;
import model.beans.humans.ResponsableVente;
import model.db.DAO;
import model.db.DAOInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class VentesDAO extends DAO {
    public boolean confirm(int id){
        try {
            statement.execute("UPDATE logement SET etat='vendu' WHERE id="+id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM vente WHERE id="+id);
            if (result.next()){
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Agent)new AgentsDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((ResponsableVente) new ResponsableVentesDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setDate(result.getDate("date"));
                return vente;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "vente");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Vente vente = (Vente) object;
        try{
            statement.execute("INSERT INTO vente (agentId, responsableId, clientId, logementId, date) VALUES (" +
                    vente.getAgent().getId()+","+
                    vente.getResponsableVente().getId()+","+
                    vente.getClient().getId()+","+
                    vente.getLogement().getId()+","+
                    vente.getDate()+
                    ");");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Vente vente = (Vente) object;
        try {
            statement.execute("DELETE FROM vente WHERE id="+vente.getId()+";");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(Object object) {
        Vente vente = (Vente) object;
        ResultSet result;
        try {
            result=statement.executeQuery("SELECT * FROM vente WHERE id="+vente.getId()+";");
            if (result.next()) return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList getAll() {
        LinkedList<Vente> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM vente;");
            while (result.next()){
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Agent)new AgentsDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((ResponsableVente) new ResponsableVentesDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setDate(result.getDate("date"));

                list.add(vente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
