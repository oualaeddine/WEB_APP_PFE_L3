package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.LocaliteDAO;
import model.db.daos.VentesDAO;
import model.db.daos.VersementDAO;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class VentesStats extends Stats {

    public VentesStats() {
        dao = new VentesDAO();
    }

    public double revenusEstimes() {
        return new VentesDAO().getRevenusEtimes();
    }

    public double revenusPerMonth(Month month) {
        return new VersementDAO().getSommeVersementsPerMonth(month);
    }

    public int ventesNbr() {
        return dao.countAll();
    }

    public int confirmedVentesNbr() {
        return new VentesDAO().confirmedVentesNbr();
    }

    public int pendingVentesNbr() {
        return new VentesDAO().ventesEnCoursNbr();
    }

    public int canceledVentesNbr() {
        return new VentesDAO().ventesAnnuleesNbr();
    }

    public HashMap<Month, Integer> confirmedVentesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VentesDAO().nbrConfirmedVentesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VentesDAO().nbrConfirmedVentesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VentesDAO().nbrConfirmedVentesForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VentesDAO().nbrConfirmedVentesForMonth(Month.APRIL));
        variation.put(Month.MAY, new VentesDAO().nbrConfirmedVentesForMonth(Month.MAY));
        variation.put(Month.JUNE, new VentesDAO().nbrConfirmedVentesForMonth(Month.JUNE));
        variation.put(Month.JULY, new VentesDAO().nbrConfirmedVentesForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VentesDAO().nbrConfirmedVentesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VentesDAO().nbrConfirmedVentesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VentesDAO().nbrConfirmedVentesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VentesDAO().nbrConfirmedVentesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VentesDAO().nbrConfirmedVentesForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> pendingVentesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VentesDAO().nbrVentesEnCoursForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VentesDAO().nbrVentesEnCoursForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VentesDAO().nbrVentesEnCoursForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VentesDAO().nbrVentesEnCoursForMonth(Month.APRIL));
        variation.put(Month.MAY, new VentesDAO().nbrVentesEnCoursForMonth(Month.MAY));
        variation.put(Month.JUNE, new VentesDAO().nbrVentesEnCoursForMonth(Month.JUNE));
        variation.put(Month.JULY, new VentesDAO().nbrVentesEnCoursForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VentesDAO().nbrVentesEnCoursForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VentesDAO().nbrVentesEnCoursForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VentesDAO().nbrVentesEnCoursForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VentesDAO().nbrVentesEnCoursForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VentesDAO().nbrVentesEnCoursForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> canceledVentesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VentesDAO().nbrVentesAnnuleesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VentesDAO().nbrVentesAnnuleesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VentesDAO().nbrVentesAnnuleesForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VentesDAO().nbrVentesAnnuleesForMonth(Month.APRIL));
        variation.put(Month.MAY, new VentesDAO().nbrVentesAnnuleesForMonth(Month.MAY));
        variation.put(Month.JUNE, new VentesDAO().nbrVentesAnnuleesForMonth(Month.JUNE));
        variation.put(Month.JULY, new VentesDAO().nbrVentesAnnuleesForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VentesDAO().nbrVentesAnnuleesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VentesDAO().nbrVentesAnnuleesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VentesDAO().nbrVentesAnnuleesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VentesDAO().nbrVentesAnnuleesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VentesDAO().nbrVentesAnnuleesForMonth(Month.DECEMBER));

        return variation;
    }


    public HashMap<Localite, Integer> ventesNbrPerRegion() {
        HashMap<Localite, Integer> variations = new HashMap<>();
        LinkedList<Localite> localites = new LocaliteDAO().getAll();
        for (Localite localite : localites) {
            variations.put(localite, new VentesDAO().nbrVentesParRegion(localite.getId()));
        }
        return variations;
    }

    public float getRevenusAnnuels() {
        return new VersementDAO().getRevenusAnnuels();
    }

    public int confirmedVentesNbrPerMonth(Month month) {
        return new VentesDAO().nbrConfirmedVentesForMonth(month);
    }

    public int nbrVentesPerRegion(int region) {
        return new VentesDAO().nbrVentesParRegion(region);
    }

    public int versementsOfTheDay() {
        return new VersementDAO().getNbrVersementsToday();
    }

    public double confirmedVentesPercentage() {
        return ventesNbr() == 0 ? 0 : confirmedVentesNbr() * 100 / ventesNbr();
    }

    public double canceledVentesPercentage() {
        return ventesNbr() == 0 ? 0 : canceledVentesNbr() * 100 / ventesNbr();
    }

    public int acheteursNbr() {
        return new VentesDAO().nbrAcheteurs();
    }

    public double acheteursPercentage() {
        int clients = new ClientsStats().clientsNbr();
        return clients == 0 ? 0 : acheteursNbr() * 100 / clients;
    }

    public double versementsOfTheMonth() {
        return new VersementDAO().getSommeVersementsThisMonth();
    }
}
