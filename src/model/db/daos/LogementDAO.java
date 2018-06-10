package model.db.daos;

import model.beans.*;
import model.beans.humans.Employe;
import model.db.DAO;
import model.enums.TypeLogement;
import model.enums.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class LogementDAO extends DAO {

    public LinkedList<Logement> getRemplacement(Logement myLogement) {
        LinkedList<Logement> logements = new LinkedList<>();
        String type = myLogement.getTypeLogement() == TypeLogement.VILLA ? "villa" : "appartement";
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select * from logement where region=" + myLogement.getLocalite().getId() + " " + "and typeLogement='" + type + "' and superficie<=" + (myLogement.getSuperficie() + 25) + " and superficie>=" + (myLogement.getSuperficie() - 25) + " and prix <= " + (myLogement.getPrix() + 500000) + " and prix >= " + (myLogement.getPrix() - 500000) + ";");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public boolean isFree(int idLogement, RDV rdv) {
        ResultSet resultSet;
        try {
            resultSet = logementStatement.executeQuery("select * from visite where visite.timestamp='" + rdv.getDate() + "' and horraire='" + rdv.getHorraire() + "' and logementId=" + idLogement + " and etat='prevue';");
            if (resultSet.next()) {
                return false;
            } else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Logement> getSimilar(Logement heda) {
        LinkedList<Logement> logements = new LinkedList<>();
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select * from logement where region=" + heda.getLocalite().getId() + " and id<>" + heda.getId() + ";");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public LinkedList<Logement> getListeDeSouhaitsForClient(int client) {
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        try {
            result = statement.executeQuery("select idLogement from souhaits where idClient=" + client + ";");
            while (result.next()) {
                Logement logement = (Logement) getById(result.getInt("idLogement"));
                if (!logement.isGele())
                    logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public boolean retirerDeLaListeDeSouhaits(int client, int logement) {
        try {
            statement.execute("DELETE FROM souhaits where (idClient=" + client + " AND idLogement=" + logement + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ajouterALaListeDeSouhaits(int client, int logement) {
        try {
            statement.execute("INSERT INTO souhaits (idClient, idLogement) VALUES (" +
                    client + "," +
                    logement +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Logement getLogement(String fourchettePrix, String region, String type, String superficie) {
        int prixMin = 0, prixMax = 0, supMin = 0, supMax = 0;
        String typeLogement = "";
        switch (fourchettePrix) {
            case "1":
                prixMin = 0;
                prixMax = 1000000;
                break;
            case "2":
                prixMin = 1000000;
                prixMax = 10000000;
                break;
            case "3":
                prixMin = 10000000;
                prixMax = 1999999999;
                break;
        }
        switch (type) {
            case "1":
                typeLogement = "villa";
                break;
            case "2":
                typeLogement = "appartement";
                break;
        }
        switch (superficie) {
            case "1":
                supMin = 0;
                supMax = 200;
                break;
            case "2":
                supMin = 200;
                supMax = 300;
                break;
            case "3":
                supMin = 300;
                supMax = 1000;
                break;
        }
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select * from logement where prix<=" + prixMax + " and prix>=" + prixMin + " and superficie<=" + supMax + " and superficie>=" + supMin + " and typeLogement='" + typeLogement + "' and region=2;");
            if (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                System.out.println("Lguit: " + logement.getTitre());
                return logement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Logement> getLogementsSelonCriteres(Logement criteres, Double prixMax, Double prixMin, Double supMax, Double supMin) {
        System.out.println("" +
                "criteres = " + criteres.toString() + "," +
                " prixMax = " + prixMax + ", " +
                "prixMin = " + prixMin + "," +
                " supMax = " + supMax + ", " +
                "supMin = " + supMin + "");
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        String avecJardin = criteres.isAvecJardin() ? " AND avecJardin = 1 " : "";
        String avecGarage = criteres.isAvecGarage() ? " AND avecGarage = 1 " : "";
        String avecSousSol = criteres.isAvecSousSol() ? " AND avecSousSol = 1 " : "";
        String avecMeubles = criteres.isMeubles() ? " AND avecMeubles = 1 " : "";
        String type = criteres.getTypeLogement() == TypeLogement.VILLA ? "villa" : "appartement";
        try {
            result = logementStatement.executeQuery("SELECT * FROM logement WHERE gele=0 and " +
                    "(superficie <= " + supMax + " AND superficie >= " + supMin + ") " +
                    "AND (prix <= " + prixMax + " AND prix >= " + prixMin + ") " +
                    "AND typeLogement='" + type + "' " +
                    "AND  gele=0 " +
                    "AND region = " + criteres.getLocalite().getId() + " " +
                    "AND nbrPieces >= " + criteres.getNbrPieces() + " " +
                    "AND nbrSdb >= " + criteres.getNbrSdb() + " " +
                    avecGarage +
                    avecJardin +
                    avecSousSol +
                    avecMeubles +
                    ";");
            while (result.next()) {

                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                System.out.println("Lguit: " + logement.getTitre());

                logements.add(logement);
            }

            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
            result = logementStatement.executeQuery("SELECT logement.* FROM logement,vente WHERE logement.id=vente.logementId AND vente.etat='confirmee';");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                list.add(logement);
            }
            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean degeler(int id) {
        try {
            logementStatement.execute("UPDATE logement SET gele=0 WHERE id=" + id + ";");
            try {
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean geler(int id) {
        try {
            logementStatement.execute("UPDATE logement SET gele=1 WHERE id=" + id + ";");
            Logement logement = new Logement();
            logement.setId(id);
            LinkedList<Visite> visites = new VisitesDao().getVisitesByLogement(logement);
            for (Visite visite : visites) {
                new VisitesDao().annulerVisite(visite);
            }
            try {
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Logement> getLogementsForUser(UserType userType, int userId) {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = logementStatement.executeQuery("SELECT * FROM logement,vente WHERE logementId=logement.id AND vente.clientId=" + userId + " AND logement.id=vente.logementId;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                list.add(logement);
            }
            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
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
            result = logementStatement.executeQuery("SELECT * FROM logement WHERE id=" + id);
            if (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                return logement;
            }
            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        return super.deleteById(id, "logement");
    }

    @Override
    public boolean update(Object object) {
        Logement logement = (Logement) object;
        try {
            int
                    jardin = logement.isAvecJardin() ? 1 : 0,
                    garage = logement.isAvecGarage() ? 1 : 0,
                    sousSol = logement.isAvecSousSol() ? 1 : 0,
                    meubles = logement.isMeubles() ? 1 : 0;
            String type = (logement.getTypeLogement() == TypeLogement.VILLA) ? "villa" : "appartement";
            logementStatement.execute("update logement set typeLogement='" + type + "', titre='" + logement.getTitre() + "', description='" + logement.getDescription() + "', superficie=" + logement.getSuperficie() + ", region=" + logement.getLocalite().getId() + ", adresse='" + logement.getAdresse() + "', nbrPieces=" + logement.getNbrPieces() + ", nbrSdb=" + logement.getNbrSdb() + ", avecJardin=" + jardin + ", avecGarage=" + garage + ", avecSousSol=" + sousSol + ", avecMeubles=" + meubles + ", etage=" + logement.getEtage() + ", prix=" + logement.getPrix() + ", latitude=" + logement.getLocation().getLatitude() + ", longitude=" + logement.getLocation().getLongitude() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            String type = (logement.getTypeLogement() == TypeLogement.VILLA) ? "villa" : "appartement";


            logementStatement.execute("INSERT INTO logement (titre, description, superficie, region, adresse, nbrPieces, nbrSdb, etage,avecJardin,avecGarage,avecSousSol,avecMeubles,latitude,longitude,prix,typeLogement) VALUES (" +
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
                    "," + meubles + "," +
                    logement.getLocation().getLatitude() + "," +
                    logement.getLocation().getLongitude() + "," +
                    logement.getPrix() + "," +
                    "'" + type + "'" +
                    ");");
            try {
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        int logement = (int) object;
        try {
            logementStatement.execute("DELETE FROM logement " +
                    "WHERE id=" + logement + ";");
            try {
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(Object object) {
        Logement logement = (Logement) object;
        ResultSet result;
        try {
            result = logementStatement.executeQuery("SELECT * FROM logement");
            while (result.next()) {
                String adresse = result.getString("adresse");
                if (logement.getAdresse().equals(adresse)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedList<Logement> getAll() {
        LinkedList<Logement> list = new LinkedList<>();
        ResultSet result;
        try {
            result = logementStatement.executeQuery("SELECT * FROM logement WHERE  gele=0;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
            result = venteStatement.executeQuery("SELECT count(id) FROM logement;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public LinkedList<Logement> getGeles() {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = logementStatement.executeQuery("SELECT * FROM logement WHERE gele=1;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
            try {
                result.close();
                logementStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Logement> getLogementsForAgent(int userId) {
        ResultSet result;
        LinkedList<Logement> list = new LinkedList<>();
        try {
            result = logementStatement.executeQuery("SELECT distinct logement.* FROM logement,visite WHERE visite.logementId=logement.id and visite.agentId=" + userId + " ;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
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
            result = logementStatement.executeQuery("SELECT * FROM logement WHERE logement.id not in (select distinct l.id from logement l,vente where l.id=vente.logementId and vente.etat='confirmee');");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);
                list.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Logement> getLogementsVisitesByUser(int userId) {
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        try {
            result = logementStatement.executeQuery("SELECT logement.* FROM logement,visite WHERE logement.id=visite.logementId AND visite.clientId=" + userId + ";");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));

                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }


    public int getNbrVendus() {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select count(l.id) from logement l,vente v where l.id=logementId and v.etat='confirmee';");
            if (result.next()) {
                return result.getInt("count(l.id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNbrGeles() {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select count(id) from logement where gele=1;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNbrLogementsForRegion(int idRegion) {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select count(id) from logement where region=" + idRegion + ";");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer nbrVendusForMonth(Month month) {
        ResultSet resultSet;
        try {
            resultSet = venteStatement.executeQuery("select count(logementId) as nbr from vente where etat='confirmee' and MONTH(date)=" + month.getValue() + " and YEAR(date)=YEAR(current_date);");
            if (resultSet.next()) {
                return resultSet.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNbrLogementsVendusForRegion(int id) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT count(logementId) as nbr from vente,logement where vente.etat='confirmee' and vente.logementId=logement.id and logement.region=" + id + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNbrGelesForRegion(int id) {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("SELECT count(logement.id) as result from logement where gele=1 and region=" + id + ";");
            if (result.next()) {
                return result.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int nbrAVendre() {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("SELECT count(logement.id) as result from logement where gele=0 ;");
            if (result.next()) {
                return result.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getSommeToutLesLogements() {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("SELECT sum(prix) as result from logement;");
            if (result.next()) {
                return result.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<Logement> getMostVisitedLogements() {
        LinkedList<Logement> logements = new LinkedList<>();
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select distinct l.*,(select count(visite.id) from visite where l.id=visite.logementId) as nbrVisites from logement l,visite v where l.id=v.logementId order by nbrVisites desc LIMIT 5;");
            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));
                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public int nbrVisitesParType(TypeLogement typeLogement) {
        ResultSet result;
        String type = typeLogement == TypeLogement.VILLA ? "villa" : "appartement";
        try {
            result = logementStatement.executeQuery("SELECT count(distinct visite.id) as result from logement,visite where logement.id=visite.logementId and logement.typeLogement='" + type + "';");
            if (result.next()) {
                return result.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVisitesParTypeParMois(TypeLogement typeLogement, Month month) {
        ResultSet result;
        String type = typeLogement == TypeLogement.VILLA ? "villa" : "appartement";
        try {
            result = logementStatement.executeQuery("SELECT count(distinct visite.id) as result from logement,visite where logement.id=visite.logementId and logement.typeLogement='" + type + "' and MONTH(visite.timestamp)=" + month.getValue() + ";");
            if (result.next()) {
                return result.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LinkedList<Logement> getLogementsForClient(int clientId) {
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        try {
            result = localiteStatement.executeQuery("SELECT distinct logement.* from logement,visite,vente where (logement.id=visite.logementId and  visite.clientId=" + clientId + ") or (logement.id=vente.id and vente.clientId=" + clientId + ")");

            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));
                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public LinkedList<Logement> getLatestAddedLogements() {
        ResultSet result;
        LinkedList<Logement> logements = new LinkedList<>();
        try {
            result = localiteStatement.executeQuery("SELECT * FROM logement where gele=0 ORDER BY id DESC LIMIT 3;");

            while (result.next()) {
                Logement logement = new Logement();

                logement.setId(result.getInt("id"));
                logement.setTitre(result.getString("titre"));
                logement.setDescription(result.getString("description"));
                logement.setSuperficie(result.getDouble("superficie"));
                logement.setGele(result.getBoolean("gele"));
                Localite localite = (Localite) new LocaliteDAO().getById(result.getInt("region"));
                logement.setLocalite(localite);
                logement.setAdresse(result.getString("adresse"));
                logement.setNbrPieces(result.getInt("nbrPieces"));
                logement.setNbrSdb(result.getInt("nbrSdb"));
                logement.setAvecJardin(result.getBoolean("avecJardin"));
                logement.setAvecGarage(result.getBoolean("avecGarage"));
                logement.setAvecSousSol(result.getBoolean("avecSousSol"));
                logement.setMeubles(result.getBoolean("avecMeubles"));
                logement.setEtage(result.getInt("etage"));
                logement.setPrix(result.getDouble("prix"));
                Location location = new Location();
                location.setLatitude(result.getDouble("latitude"));
                location.setLongitude(result.getDouble("longitude"));
                logement.setLocation(location);
                logement.setPrix(result.getDouble("prix"));
                logement.setTypeLogement(result.getString("typeLogement").equals("villa") ? TypeLogement.VILLA : TypeLogement.APPARTEMENT);

                logements.add(logement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    public boolean isInWishList(int client, int id) {
        ResultSet result;
        try {
            result = logementStatement.executeQuery("select * from souhaits where idClient=" + client + " and idLogement=" + id + " and idLogement not in (select id from logement where gele=1);");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isThereLogements(String fourchettePrix, String region) {
        ResultSet result;
        long prixMax = 0, prixMin = 0;
        switch (fourchettePrix) {
            case "1":
                prixMin = 0;
                prixMax = 1000000;
                break;
            case "2":
                prixMin = 1000000;
                prixMax = 10000000;
                break;
            case "3":
                prixMin = 10000000;
                prixMax = 1000000000;
                break;
        }
        try {
            result = logementStatement.executeQuery("select * from logement where " +
                    "prix > " + prixMin + " and prix < " + prixMax + "" +
                    " and region = " + region + " and gele=0;");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isThereLogements(String fourchettePrix, String region, String type) {
        ResultSet result;
        long prixMax = 0, prixMin = 0;
        switch (fourchettePrix) {
            case "1":
                prixMin = 0;
                prixMax = 1000000;
                break;
            case "2":
                prixMin = 1000000;
                prixMax = 10000000;
                break;
            case "3":
                prixMin = 10000000;
                prixMax = 1000000000;
                break;
        }
        String typeLogement = type.equals("1") ? "villa" : "appartement";
        try {
            result = logementStatement.executeQuery("select * from logement where " +
                    "prix > " + prixMin + " and prix < " + prixMax + "" +
                    " and region = " + region + " and gele=0" +
                    " and typeLogement = '" + typeLogement + "';");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isThereLogementsVilla(String fourchettePrix, String region, String type, String superficie) {
        ResultSet result;
        long prixMax = 0, prixMin = 0;
        switch (fourchettePrix) {
            case "1":
                prixMin = 0;
                prixMax = 1000000;
                break;
            case "2":
                prixMin = 1000000;
                prixMax = 10000000;
                break;
            case "3":
                prixMin = 10000000;
                prixMax = 1000000000;
                break;
        }
        String typeLogement = type.equals("1") ? "VILLA" : "APPARTEMENT";
        long supMin = 0, supMax = 0;
        switch (superficie) {
            case "1":
                supMin = 0;
                supMax = 100;
                break;
            case "2":
                supMin = 100;
                supMax = 200;
                break;
            case "3":
                supMin = 200;
                supMax = 10000;
                break;
        }
        try {
            result = logementStatement.executeQuery("select * from logement where " +
                    "prix > " + prixMin + " and prix < " + prixMax + "" +
                    " and region = " + region + " and gele=0" +
                    " and typeLogement = '" + typeLogement + "'" +
                    " and superficie > " + supMin + " and superficie < " + supMax + ";");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isThereLogementsAppartement(String fourchettePrix, String region, String type, String rooms) {
        ResultSet result;
        long prixMax = 0, prixMin = 0;
        switch (fourchettePrix) {
            case "1":
                prixMin = 0;
                prixMax = 1000000;
                break;
            case "2":
                prixMin = 1000000;
                prixMax = 10000000;
                break;
            case "3":
                prixMin = 10000000;
                prixMax = 1000000000;
                break;
        }
        String typeLogement = type.equals("1") ? "VILLA" : "APPARTEMENT";

        try {
            result = logementStatement.executeQuery("select * from logement where " +
                    "prix > " + prixMin + " and prix < " + prixMax + "" +
                    " and region = " + region + " and gele=0" +
                    " and typeLogement = '" + typeLogement + "'" +
                    " and nbrPieces = " + rooms + ";");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
