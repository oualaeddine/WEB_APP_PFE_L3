package model.db.daos;

import model.beans.Rapport;
import model.beans.Vente;
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
            result = rapportStatement.executeQuery("SELECT rapport.* FROM rapport,visite WHERE visiteId=visite.id AND visite.agentId=" + agentId + " order by rapport.visiteId desc ;");
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
                visiteStatement.execute("UPDATE visite set etat = 'terminee' where visite.id=" + rapport.getVisite().getId() + ";");
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
                        "); ");
                visiteStatement.execute("UPDATE visite set etat = 'terminee' where visite.id=" + rapport.getVisite().getId() + ";");

                System.out.println("rapport etabli");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("rapport non etabli");

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
            result = rapportStatement.executeQuery("SELECT count(visiteId) FROM rapport;");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
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
    public int getAvisNegatifsForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visite.id=visiteId and avis='negatif' and MONTH(visite.timestamp)='" + month.getValue() + "' and YEAR(visite.timestamp)=YEAR(current_date );");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getAvisPositifsForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visite.id=visiteId and avis='positif' and MONTH(visite.timestamp)='" + month.getValue() + "' and YEAR(visite.timestamp)=YEAR(current_date );");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getAbsencesForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visiteId=visite.id and etatClient='absent' and MONTH(visite.timestamp)='" + month.getValue() + "' and YEAR(visite.timestamp)=YEAR(current_date );");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getPresencesForMonth(Month month) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visiteId=visite.id and etatClient='present' and MONTH(visite.timestamp)='" + month.getValue() + "' and YEAR(visite.timestamp)=YEAR(current_date );");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getAbsencesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where etatClient='absent';");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPresences() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where etatClient='present';");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVisitesPositivesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where avis='positif';");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVisitesNegativesNbr() {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport where avis='negatif';");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float pourcentageAvisPositifs() {
        int nbrAvisPositifs = getVisitesPositivesNbr();
        int allVisites = countAll();
        return allVisites == 0 ? 0 : nbrAvisPositifs * 100 / allVisites;
    }

    public float pourcentageAvisNegatifs() {
        int nbrAvisNeg = getVisitesNegativesNbr();
        int allVisites = countAll();
        return allVisites == 0 ? 0 : nbrAvisNeg * 100 / allVisites;
    }

    public float pourcentagePresences() {
        int nbrPresences = getPresences();
        int all = countAll();
        return all == 0 ? 0 : nbrPresences * 100 / all;
    }

    public float pourcentageAbsences() {
        int nbrAbsences = getAbsencesNbr();
        int all = countAll();
        return all == 0 ? 0 : nbrAbsences * 100 / all;
    }


    public int nbrRapportsForAgent(int userId) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(rapport.visiteId) as nbr from rapport,visite where rapport.visiteId=visite.id and visite.agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getAbsencesNbrForAgent(int userId) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visiteId=visite.id and etatClient='absent' and visite.agentId=" + userId + " ;");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPresencesNbrForAgent(int userId) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where visiteId=visite.id and etatClient='present' and visite.agentId=" + userId + " ;");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getAvisPositifsForAgent(int userId) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where rapport.visiteId=visite.id and avis='positif' and visite.agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getAvisNegatifsForAgent(int userId) {
        ResultSet result;
        try {
            result = rapportStatement.executeQuery("select count(visiteId) from rapport,visite where rapport.visiteId=visite.id and avis='negatif' and visite.agentId=" + userId + ";");
            if (result.next()) {
                return result.getInt("count(visiteId)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float pourcentagePositifForAgent(int userId) {
        int nbrAvisPositifs = getAvisPositifsForAgent(userId);
        int allVisites = nbrRapportsForAgent(userId);
        return allVisites == 0 ? 0 : nbrAvisPositifs * 100 / allVisites;
    }

    public float pourcentageNegatifForAgent(int userId) {
        int nbrAvisPositifs = getAvisNegatifsForAgent(userId);
        int allVisites = nbrRapportsForAgent(userId);
        return allVisites == 0 ? 0 : nbrAvisPositifs * 100 / allVisites;
    }

    public float pourcentagePresenceForAgent(int userId) {
        int nbrPresences = getPresencesNbrForAgent(userId);
        int all = nbrRapportsForAgent(userId);
        return all == 0 ? 0 : nbrPresences * 100 / all;
    }

    public float pourcentageAbsenceForAgent(int userId) {
        int nbrPresences = getAbsencesNbrForAgent(userId);
        int all = nbrRapportsForAgent(userId);
        return all == 0 ? 0 : nbrPresences * 100 / all;
    }
}
