package model.db.daos;

import model.beans.Rapport;
import model.beans.Versement;
import model.beans.humans.Person;
import model.db.DAO;
import model.enums.EtatClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class RapportDAO extends DAO {


    public LinkedList<Rapport> getByAgent(int agentId) {
        ResultSet result;
        LinkedList<Rapport> rapports = new LinkedList<>();
        try {
            result = rapportStatement.executeQuery("SELECT rapport.* FROM rapport,visite WHERE visiteId=visite.id AND visite.agentId=" + agentId + ";");
            while (result.next()) {
                Rapport rapport;
                if (result.getString("etatClient").equals("present")) {
                    rapport = new Rapport();
                    rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                    rapport.setEtatClient(EtatClient.PRESENT);
                    rapport.setCommentaire(result.getString("commentaire"));
                    rapport.setAvis(result.getString("avis").equals("positif"));
                } else {
                    rapport = new Rapport();
                    rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                    rapport.setEtatClient(EtatClient.ABSENT);
                }
                rapports.add(rapport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rapports;
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("SELECT * FROM rapport WHERE visiteId=" + id + ";");
            if (result.next()) {
                Rapport rapport;
                if (result.getString("etatClient").equals("present")) {
                    rapport = new Rapport();
                    rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                    rapport.setEtatClient(EtatClient.PRESENT);
                    rapport.setCommentaire(result.getString("commentaire"));
                    rapport.setAvis(result.getString("avis").equals("positif"));
                } else {
                    rapport = new Rapport();
                    rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                    rapport.setEtatClient(EtatClient.ABSENT);
                }
                return rapport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            rapportStatement.execute("DELETE FROM rapport WHERE visiteId=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Rapport rapport = (Rapport) object;
        if (rapport.getEtatClient() == EtatClient.ABSENT) {
            try {
                rapportStatement.execute("INSERT INTO rapport(visiteId,etatClient)VALUES (" +
                        rapport.getVisite().getId() + "," + "'absent'" +
                        ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String avis = rapport.isAvis() ? "positif" : "negatif";
            try {
                rapportStatement.execute("INSERT INTO rapport(visiteId, etatClient, avis, commentaire) VALUES (" +
                        rapport.getVisite().getId() + "," +
                        "'present'," +
                        "'" + avis + "'," +
                        "'" + rapport.getCommentaire() + "'" +
                        ")");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public LinkedList getAll() {
        ResultSet result;
        LinkedList<Rapport> rapports = new LinkedList<>();
        try {
            result = rapportStatement.executeQuery("SELECT * FROM rapport;");
            while (result.next()) {
                if (result.next()) {
                    Rapport rapport;
                    if (result.getString("etatClient").equals("present")) {
                        rapport = new Rapport();
                        rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                        rapport.setEtatClient(EtatClient.PRESENT);
                        rapport.setCommentaire(result.getString("commentaire"));
                        rapport.setAvis(result.getString("avis").equals("positif"));
                    } else {
                        rapport = new Rapport();
                        rapport.setVisite(new VisitesDao().getById(result.getInt("visiteId")));
                        rapport.setEtatClient(EtatClient.ABSENT);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rapports;
    }

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("SELECT (count(visiteId)) FROM rapport;");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public LinkedList<Versement> getByLogement() {
        return null;
    }


    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * stats
     **/

    public Integer getAbsencesForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visiteId=visite.id and etatClient='absent' and MONTH(visite.timestamp)='" + month.getValue() + "';");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getPresencesForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) as result from rapport,visite where visiteId=visite.id and etatClient='present' and MONTH(visite.timestamp)='" + month.getValue() + "';");
            System.out.println("Result: " + result.getInt("result"));
            return result.getInt("result");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getAbsencesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where etatClient='absent';");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPresences() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where etatClient='present';");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVisitesPositivesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where avis='positif';");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVisitesNegativesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where avis='negatif';");
            return result.getInt("count(visiteId)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float pourcentageAvisPositifs() {
        int nbrAvisPositifs = getVisitesPositivesNbr();
        int allVisites = countAll();
        return nbrAvisPositifs * 100 / allVisites;
    }

    public float pourcentageAvisNegatifs() {
        int nbrAvisNeg = getVisitesNegativesNbr();
        int allVisites = countAll();
        return nbrAvisNeg * 100 / allVisites;
    }

    public float pourcentagePresences() {
        int nbrPresences = getPresences();
        int all = countAll();
        return nbrPresences * 100 / all;
    }

    public float pourcentageAbsences() {
        int nbrAbsences = getAbsencesNbr();
        int all = countAll();
        return nbrAbsences * 100 / all;
    }


//    public int getAvisPositifsForMonth(Month month) {
//
//    }
}
