package model.db.daos;

import model.beans.Logement;
import model.db.DAO;
import model.db.DAOInterface;
import model.enums.EtatLogement;
import model.enums.UserType;
import sun.rmi.runtime.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogementDAO extends DAO {
    public LinkedList<Logement> getLogementsVendus() {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM logement WHERE etat='vendu';");
            while (result.next()) {
                Logement logement = new Logement();
                logement =(Logement) getById(result.getInt("id"));
                list.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean geler(Logement logement){
        try {
            statement.execute("UPDATE logement SET etat = 'gele' WHERE id="+logement.getId()+";");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public LinkedList<Logement> getLogementsForUser(UserType userType, int userId) {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT id FROM logement WHERE client.id=vente.clientId AND vente.clientId="+userId+" AND logement.id=vente.logementId;");
            while (result.next()) {
                Logement logement = new Logement();
                logement = (Logement) new LogementDAO().getById(result.getInt("id"));
                list.add(logement);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM logement WHERE id="+id);
            if (result.next()){
                Logement logement = new Logement();
                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")){
                    case "vendu": logement.setEtat(EtatLogement.VENDU); break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE); break;
                    case "gele": logement.setEtat(EtatLogement.GELE); break;
                }

                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrFacades(result.getInt("nbrFacades"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setEtage(result.getInt("etage"));
                return logement;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "logement");
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Logement logement = (Logement) object;
        try {
            int jardin;
            if (logement.isAvecJardin())
                jardin=1;
            else
                jardin=0;
            statement.execute("INSERT INTO logement (titre, description, superficie, etat, region, adresse, nbrPieces, nbrFacades, avecJardin, etage) VALUES ("+
                    "'"+logement.getTitre()+"',"+
                    "'"+logement.getDescription()+"',"+
                    logement.getSuperficie()+","+
                    "'"+logement.getEtat()+"',"+
                    "0,"+ /*TODO: idRegion*/
                    "'"+logement.getAdresse()+"',"+
                    logement.getNbrPieces()+","+
                    logement.getNbrFacades()+","+
                    jardin+","+
                    logement.getEtage()+
                    ");");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Logement logement = (Logement) object;
        try {
            statement.execute("DELETE FROM logement " +
                    "WHERE titre='"+logement.getTitre()+"';");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(Object object) {
        Logement logement = (Logement) object;
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM logement");
            while (result.next()){
                String adresse = result.getString("adresse");
                if (logement.getAdresse().equals(adresse)) return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList<Logement> getAll() {
        LinkedList<Logement> list = new LinkedList<>();
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * FROM logement");
            while (result.next()){
                Logement logement = new Logement();
                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                if (result.getString("etat").equals("vendu")) logement.setEtat(EtatLogement.VENDU);
                else logement.setEtat(EtatLogement.AVENDRE);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrFacades(result.getInt("nbrFacades"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setEtage(result.getInt("etage"));

                list.add(logement);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
