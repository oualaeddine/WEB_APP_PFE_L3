package model.db.daos;

import model.beans.Localite;
import model.beans.Location;
import model.beans.Logement;
import model.beans.humans.Employe;
import model.db.DAO;
import model.db.DAOInterface;
import model.enums.EtatLogement;
import model.enums.TypeLogement;
import model.enums.UserType;
import sun.rmi.runtime.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogementDAO extends DAO {

    public LinkedList<Logement> getLogementsSelonCriteres(Logement criteres, Double prixMax, Double prixMin, Double supMax, Double supMin) {
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        String avecJardin = criteres.isAvecJardin() ? " AND avecJardin = 1" : "";
        String avecGarage = criteres.isAvecGarage() ? " AND avecGarage = 1" : "";
        String avecSousSol = criteres.isAvecSousSol() ? " AND avecSousSol = 1" : "";
        String avecMeubles = criteres.isMeubles() ? " AND avecMeubles = 1" : "";

        try {
            result = statement.executeQuery("SELECT * FROM logement WHERE " +
                    "(superficie < "+supMax+", AND superficie > "+supMin+") " +
                    "AND (prix < "+prixMax+" AND prix > "+prixMin+") " +
                    "AND  etat='avendre'" +
                    "AND region = "+criteres.getLocalite().getId()+" " +
                    "AND nbrPieces = "+criteres.getNbrPieces()+" " +
                    "AND nbrSdb = "+criteres.getNbrSdb()+" " +
                    avecGarage + "," +
                    avecJardin + "," +
                    avecSousSol + "," +
                    avecMeubles +
                    ";");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public LinkedList<Logement> getLogementsVendus() {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM logement WHERE etat='vendu';");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                list.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean degeler(int id) {
        try {
            statement.execute("UPDATE logement SET etat = 'avendre' WHERE id=" + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean geler(int id){
        try {
            statement.execute("UPDATE logement SET etat = 'gele' WHERE id="+id+";");
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
            result = statement.executeQuery("SELECT * FROM logement WHERE client.id=vente.clientId AND vente.clientId="+userId+" AND logement.id=vente.logementId;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));

                list.add(logement);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Employe getByEmail(String email) {
        return null;
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
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
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
            int
                    jardin = logement.isAvecJardin() ? 1 : 0,
                    garage = logement.isAvecGarage() ? 1 : 0,
                    sousSol = logement.isAvecSousSol() ? 1 : 0,
                    meubles = logement.isMeubles() ? 1 : 0;
            String type = logement.getTypeLogement() == TypeLogement.VILLA ? "villa" : "appartement";


            statement.execute("INSERT INTO logement (titre, description, superficie, region, adresse, nbrPieces, nbrSdb, etage,avecJardin,avecGarage,avecSousSol,avecMeubles,latitude,longitude,prix,type) VALUES (" +
                            "'" + logement.getTitre() + "', \n" +
                            "'" + logement.getDescription() + "',\n " +
                            logement.getSuperficie() + ",\n " +
                            logement.getLocalite().getId() + ", \n" +
                            "'" + logement.getAdresse() + "',\n " +
                            logement.getNbrPieces() + ", \n" +
                            logement.getNbrSdb() + ", \n" +
                            logement.getEtage() +
                            "," + jardin +
                            "," + garage +
                            "," + sousSol +
                            "," + meubles + ","+
                            logement.getLocation().getLatitude() + "," +
                            logement.getLocation().getLongitude() + "," +
                            logement.getPrix()+ "," +
                            "'" + type +"'" +
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
                    "WHERE id='"+logement.getId()+"';");
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
            result = statement.executeQuery("SELECT * FROM logement;");
            while (result.next()){
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }



    public LinkedList<Logement> getGeles() {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM logement WHERE etat='gele';");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public LinkedList<Logement> getNonVendus() {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = statement.executeQuery("SELECT * FROM logement WHERE etat='avendre' OR etat='gele';");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                switch (result.getString("etat")) {
                    case "vendu": logement.setEtat(EtatLogement.VENDU);break;
                    case "avendre": logement.setEtat(EtatLogement.AVENDRE);break;
                    case "gele": logement.setEtat(EtatLogement.GELE);break;
                }
                logement.setLocalite((Localite) new LocaliteDAO().getById(result.getInt("region")));
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setTypeLogement(result.getString("type").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
