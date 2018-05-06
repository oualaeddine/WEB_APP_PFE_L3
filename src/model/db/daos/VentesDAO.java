package model.db.daos;

import model.beans.Logement;
import model.beans.Vente;
import model.beans.humans.Client;
import model.beans.humans.Employe;
import model.db.DAO;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class VentesDAO extends DAO {
    public boolean confirm(Vente vente) {
        try {
            venteStatement.execute("UPDATE vente SET etat='confirmee' WHERE id=" + vente.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean annuler(Vente vente) {
        try {
            venteStatement.execute("UPDATE vente SET etat='annulee' WHERE id=" + vente.getId() + ";");
            logementStatement.execute("UPDATE logement SET gele=0 WHERE id=" + vente.getLogement().getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vente getByClientAndlogement(Client client, Logement logement) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE clientId=" + client.getId() +
                    " AND logementId=" + logement.getId() + ";");
            if (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
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
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE id=" + id);
            if (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
                return vente;
            }
        } catch (SQLException e) {
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
        try {
            venteStatement.execute("INSERT INTO vente (clientId, logementId, etat,vente.date) VALUES (" +
                    vente.getClient().getId() + "," +
                    vente.getLogement().getId() + "," +
                    "'encours'," +
                    "current_date" +
                    ");");
            System.out.println("vente ajouté client " + vente.getClient().getId() + "logement = " + vente.getLogement().getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Vente vente = (Vente) object;
        try {
            venteStatement.execute("DELETE FROM vente WHERE id=" + vente.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(Object object) {
        Vente vente = (Vente) object;
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE id=" + vente.getId() + ";");
            if (result.next()) return true;
        } catch (SQLException e) {
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
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
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

    @Override
    public int countAll() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT count(id) FROM vente;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean cancelVente(Vente vente) {
        try {
            venteStatement.execute("UPDATE vente SET etat='annulee' WHERE id=" + vente.getId() + ";");
            logementStatement.execute("UPDATE logement SET gele=0 WHERE id=" + vente.getLogement().getId());
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
                vente.setDate(result.getDate("date"));
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

    public LinkedList<Vente> getEnCours() {
        ResultSet result;
        LinkedList<Vente> ventes = new LinkedList<>();
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE etat='encours';");
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
                ventes.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventes;
    }

    public LinkedList getByClient(Client client) {
        ResultSet result;
        LinkedList<Vente> ventes = new LinkedList<>();
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE clientId=" + client.getId() + " AND etat='encours';");
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
                ventes.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventes;
    }

    public LinkedList getEnCoursForClient(Client client) {
        ResultSet result;
        LinkedList<Vente> ventes = new LinkedList<>();
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE clientId=" + client.getId() + " AND etat='encours';");
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
                ventes.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventes;
    }

    public LinkedList<Vente> getAnnulees() {
        ResultSet result;
        LinkedList<Vente> ventes = new LinkedList<>();
        try {
            result = venteStatement.executeQuery("SELECT * FROM vente WHERE etat='annulee';");
            while (result.next()) {
                Vente vente = new Vente();
                vente.setId(result.getInt("id"));
                vente.setDate(result.getDate("date"));
                vente.setClient((Client) new ClientDAO().getById(result.getInt("clientId")));
                vente.setLogement((Logement) new LogementDAO().getById(result.getInt("logementId")));
                vente.setEtatVente(Util.getEtatVenteFromString(result.getString("etat")));
                ventes.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventes;
    }

    /**
     * Stats
     */
    public int confirmedVentesNbr() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='confirmee';");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int ventesEnCoursNbr() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='encours';");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int ventesAnnuleesNbr() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='annulee';");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrConfirmedVentesForMonth(Month month) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='confirmee' and MONTH(vente.date)=" + month.getValue() + " and YEAR(vente.date)=YEAR(current_date) ;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVentesEnCoursForMonth(Month month) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='encours' and MONTH(vente.date)=" + month.getValue() + " and YEAR(vente.date)=YEAR(current_date) ;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVentesAnnuleesForMonth(Month month) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(id) from vente where etat='annulee' and MONTH(vente.date)=" + month.getValue() + " and YEAR(vente.date)=YEAR(current_date) ;");
            if (result.next()) {
                return result.getInt("count(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrVentesParRegion(int idregion) {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("SELECT count(vente.id) as nbr from vente,logement where vente.logementId=logement.id and logement.region=" + idregion + ";");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int nbrAcheteurs() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select count(distinct clientId) as nbr from vente where etat='confirmee';");
            if (result.next()) {
                return result.getInt("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getRevenusEtimes() {
        ResultSet result;
        try {
            result = venteStatement.executeQuery("select sum(distinct montant) as nbr from versement,vente where etat<>'annulee';");
            if (result.next()) {
                return result.getDouble("nbr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
