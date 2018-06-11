package model.db.daos;

import model.beans.Appel;
import model.beans.Logement;
import model.beans.Visite;
import model.beans.humans.Employe;
import model.beans.humans.Person;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class AppelsDAO extends DAO {

    public boolean confirmer(Appel appel) {
        try {
            statement.execute("update appels set isConfirmed=1");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = statement.executeQuery("select * from appels where id=" + id + ";");
            if (result.next()) {
                Appel appel = new Appel();
                appel.setId(result.getInt("id"));
                appel.setNumeroTel(result.getString("numeroTel"));
                Visite visite = new Visite();
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logement")));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                visite.setTimestamp(result.getDate("date"));
                appel.setVisite(visite);
                appel.setConfirmed(result.getBoolean("isConfirmed"));

                return appel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Appel appel = (Appel) object;
        int isConfirmed = appel.isConfirmed() ? 1 : 0;
        try {
            statement.execute("INSERT INTO appels(numeroTel, logement, horraire, `date`, isConfirmed) VALUES (" +
                    "'" + appel.getNumeroTel() + "'," +
                    appel.getVisite().getLogement().getId() + ", " +
                    "'" + appel.getVisite().getHorraire() + "'," +
                    "'" + appel.getVisite().getTimestamp() + "'," +
                    isConfirmed +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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
        LinkedList<Appel> appels = new LinkedList<>();
        try {
            result = statement.executeQuery("select * from appels;");
            while (result.next()) {
                Appel appel = new Appel();
                appel.setId(result.getInt("id"));
                appel.setNumeroTel(result.getString("numeroTel"));
                Visite visite = new Visite();
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logement")));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                visite.setTimestamp(result.getDate("date"));
                appel.setVisite(visite);
                appel.setConfirmed(result.getBoolean("isConfirmed"));

                appels.add(appel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appels;
    }

    public LinkedList<Appel> getNonConfirmes() {
        ResultSet result;
        LinkedList<Appel> appels = new LinkedList<>();
        try {
            result = statement.executeQuery("select * from appels where isConfirmed=0;");
            while (result.next()) {
                Appel appel = new Appel();
                appel.setId(result.getInt("id"));
                appel.setNumeroTel(result.getString("numeroTel"));
                Visite visite = new Visite();
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logement")));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                visite.setTimestamp(result.getDate("date"));
                appel.setVisite(visite);
                appel.setConfirmed(result.getBoolean("isConfirmed"));

                appels.add(appel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appels;
    }

    public LinkedList<Appel> getConfirmes() {
        ResultSet result;
        LinkedList<Appel> appels = new LinkedList<>();
        try {
            result = statement.executeQuery("select * from appels where isConfirmed=1;");
            while (result.next()) {
                Appel appel = new Appel();
                appel.setId(result.getInt("id"));
                appel.setNumeroTel(result.getString("numeroTel"));
                Visite visite = new Visite();
                visite.setLogement((Logement) new LogementDAO().getById(result.getInt("logement")));
                visite.setHorraire(Integer.parseInt(result.getString("horraire")));
                visite.setTimestamp(result.getDate("date"));
                appel.setVisite(visite);
                appel.setConfirmed(result.getBoolean("isConfirmed"));

                appels.add(appel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appels;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }


}
