package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.beans.Logement;
import model.db.daos.LocaliteDAO;
import model.db.daos.LogementDAO;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class LogementsStats extends Stats {

    public LogementsStats() {
        dao = new LogementDAO();
    }

    public int logementsNbr() {
        return dao.countAll();
    }

    public HashMap<Localite, Integer> logementsNbrPerRegion() {
        HashMap<Localite, Integer> variation = new HashMap<>();
        LinkedList<Localite> localites = new LocaliteDAO().getAll();
        for (Localite localite : localites) {
            variation.put(localite, new LogementDAO().getNbrLogementsForRegion(localite.getId()));
        }
        return variation;
    }

    public int logementsVendusNbr() {

        return new LogementDAO().getNbrVendus();
    }


    public HashMap<Month, Integer> logementsVendusNbrVariation() {

        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new LogementDAO().nbrVendusForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new LogementDAO().nbrVendusForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new LogementDAO().nbrVendusForMonth(Month.MARCH));
        variation.put(Month.APRIL, new LogementDAO().nbrVendusForMonth(Month.APRIL));
        variation.put(Month.MAY, new LogementDAO().nbrVendusForMonth(Month.MAY));
        variation.put(Month.JUNE, new LogementDAO().nbrVendusForMonth(Month.JUNE));
        variation.put(Month.JULY, new LogementDAO().nbrVendusForMonth(Month.JULY));
        variation.put(Month.AUGUST, new LogementDAO().nbrVendusForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new LogementDAO().nbrVendusForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new LogementDAO().nbrVendusForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new LogementDAO().nbrVendusForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new LogementDAO().nbrVendusForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Localite, Integer> logementsVendusNbrPerRegion() {
        HashMap<Localite, Integer> variation = new HashMap<>();
        LinkedList<Localite> localites = new LocaliteDAO().getAll();
        for (Localite localite : localites) {
            variation.put(localite, new LogementDAO().getNbrLogementsVendusForRegion(localite.getId()));
        }
        return variation;
    }

    public int logementsGeleNbr() {
        return new LogementDAO().getNbrGeles();
    }

    public HashMap<Localite, Integer> logementsGeleNbrPerRegion() {
        HashMap<Localite, Integer> variation = new HashMap<>();
        LinkedList<Localite> localites = new LocaliteDAO().getAll();
        for (Localite localite : localites) {
            variation.put(localite, new LogementDAO().getNbrGelesForRegion(localite.getId()));
        }
        return variation;
    }

    public int logementsAVendre() {
        return new LogementDAO().nbrAVendre();
    }

    public int nbrLogementsPerRegion(int region) {
        return new LogementDAO().getNbrLogementsForRegion(region);
    }

    public double prixTotalDesLogements() {
        return new LogementDAO().getSommeToutLesLogements();
    }

    public double logementsVendusPercentage() {
        return logementsVendusNbr() == 0 ? 0 : logementsVendusNbr() * 100 / logementsNbr();
    }

    public LinkedList<Logement> mostVisitedLogements() {
        return new LogementDAO().getMostVisitedLogements();
    }

    public LinkedList<Logement> dernierLogementsAjoutes() {
        return new LogementDAO().getLatestAddedLogements();
    }
}
