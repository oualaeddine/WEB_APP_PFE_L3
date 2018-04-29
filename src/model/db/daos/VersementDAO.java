package model.db.daos;

import model.beans.Vente;
import model.beans.Versement;
import model.beans.humans.Person;
import model.db.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class VersementDAO extends DAO {


    public double getSommeVersementsByVente(int venteId) {
        double somme = 0;
        ResultSet result;

        try {
            result = versementStatement.executeQuery("SELECT montant FROM versement WHERE venteId=" + venteId + ";");
            while (result.next()) {
                somme = somme + result.getDouble("montant");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return somme;
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = versementStatement.executeQuery("SELECT * FROM versement WHERE id=" + id + ";");
            if (result.next()) {
                Versement versement = new Versement();
                versement.setId(result.getInt("id"));
                versement.setMontant(result.getDouble("montant"));
                versement.setDate(result.getDate("date"));
                int venteId = result.getInt("venteId");
                Vente vente = (Vente) new VentesDAO().getById(venteId);
                versement.setVente(vente);
                return versement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            versementStatement.execute("DELETE FROM versement WHERE id=" + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    public boolean premierVersement(int idVente) {
        ResultSet result;
        try {
            result = versementStatement.executeQuery("SELECT id FROM versement WHERE venteId=" + idVente + ";");
            if (result.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean dernierVersement(Vente vente, double montant) {
        return ((getSommeVersementsByVente(vente.getId()) + montant) == vente.getLogement().getPrix());
    }

    @Override
    public boolean add(Object object) {
        Versement versement = (Versement) object;
        if (premierVersement(versement.getVente().getId())) {
            new LogementDAO().geler(versement.getVente().getLogement().getId());
        }
        if (dernierVersement(versement.getVente(), versement.getMontant())) {
            new VentesDAO().confirm(versement.getVente());
        }
        try {
            versementStatement.execute("INSERT INTO versement(venteId, montant, date) VALUES (" +
                    versement.getVente().getId() + "," +
                    versement.getMontant() + "," +
                    "CURRENT_DATE" +
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
        LinkedList<Versement> list = new LinkedList<>();
        ResultSet result;
        try {
            result = versementStatement.executeQuery("SELECT * FROM versement;");
            while (result.next()) {
                Versement versement = new Versement();
                versement.setId(result.getInt("id"));
                versement.setMontant(result.getDouble("montant"));
                versement.setDate(result.getDate("date"));
                int venteId = result.getInt("venteId");
                Vente vente = (Vente) new VentesDAO().getById(venteId);
                versement.setVente(vente);

                list.add(versement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Versement> getByClient(int clientId) {// TODO: 4/26/2018
        return null;
    }

    public LinkedList<Versement> getByVente(int venteId) {
        ResultSet result;
        LinkedList<Versement> versements = new LinkedList<>();
        try {
            result = versementStatement.executeQuery("SELECT * FROM versement WHERE venteId=" + venteId + ";");
            while (result.next()) {
                Versement versement = new Versement();
                versement.setId(result.getInt("id"));
                versement.setMontant(result.getDouble("montant"));
                versement.setDate(result.getDate("date"));
                Vente vente = (Vente) new VentesDAO().getById(venteId);
                versement.setVente(vente);

                versements.add(versement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return versements;
    }
}
