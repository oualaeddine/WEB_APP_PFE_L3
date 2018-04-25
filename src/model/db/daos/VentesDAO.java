package model.db.daos;

import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.EtatVente;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class VentesDAO extends DAO {
    public boolean confirm(Vente vente){
        try {
            venteStatement.execute("UPDATE logement SET etat='vendu' WHERE id="
                    +vente.getLogement().getId()+";");
            venteStatement.execute("UPDATE vente SET etat='confirmee' WHERE id="+vente.getId()+";");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean annuler(Vente vente) {
        try {
            venteStatement.execute("UPDATE vente SET etat='annulee' WHERE id=" + vente.getId()+";");
            logementStatement.execute("UPDATE logement SET gele=0 WHERE id=" + vente.getLogement().getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Vente getByClientAndlogement(Client client,Logement logement){
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE clientId=" + client.getId() +
                    " AND logementId=" + logement.getId() + ";");
            if (result.next()){
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Employe)new EmployeDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((Employe) new EmployeDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setDate(result.getDate("date"));
                switch (result.getString("etat")) {
                    case "confirmee": vente.setEtatVente(EtatVente.CONFIRMEE); break;
                    case "non_confirmee": vente.setEtatVente(EtatVente.EN_COURS); break;
                    case "annulee": vente.setEtatVente(EtatVente.ANNULEE); break;
                }
                return vente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employe getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE id="+id);
            if (result.next()){
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Employe)new EmployeDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((Employe) new EmployeDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                if (result.getString("etat").equals("confirmee"))
                    vente.setEtatVente(EtatVente.CONFIRMEE);
                else
                    vente.setEtatVente(EtatVente.EN_COURS);
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
            venteStatement.execute("INSERT INTO vente (agentId, responsableId, clientId, logementId, etat) VALUES (" +
                    vente.getAgent().getId()+","+
                    vente.getResponsableVente().getId()+","+
                    vente.getClient().getId()+","+
                    vente.getLogement().getId()+"," +
                    "'encours'"+
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
            venteStatement.execute("DELETE FROM vente WHERE id="+vente.getId()+";");
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
            result=venteStatement.executeQuery("SELECT * FROM vente WHERE id="+vente.getId()+";");
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
            result = venteStatement.executeQuery("SELECT * FROM vente;");
            while (result.next()){
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Employe)new EmployeDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((Employe) new EmployeDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));

                list.add(vente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean cancelVente(Vente vente) {
        try {
            venteStatement.execute("UPDATE vente SET etat='annulee' WHERE id=" + vente.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Vente> getConfirmed() {
        ResultSet result;
        LinkedList<Vente> list = new LinkedList<>();
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE etat='confirmee';");
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setAgent((Employe) new EmployeDAO().getById(result.getInt("agentId")));
                vente.setResponsableVente((Employe) new EmployeDAO().getById(result.getInt("responsableId")));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));

                list.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
