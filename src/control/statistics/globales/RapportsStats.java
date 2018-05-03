package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.RapportDAO;

public class RapportsStats extends Stats {

    public RapportsStats() {
        dao = new RapportDAO();
    }

    public int rapportsNbr() {
        return 0;
    }

    public int absencesNbr() {
        return 0;
    }

    public int presencesNbr() {
        return 0;
    }

    public int positifsNbr() {
        return 0;
    }

    public int negatifsNbr() {
        return 0;
    }

    public float positifPercentage() {
        return 0;
    }

    public float negatifPercentage() {
        return 0;
    }

    public float abcencesPercentage() {
        return 0;
    }

    public float presencesPercentage() {
        return 0;
    }

    /**
     * variations
     **/

    public int absencesVariation() {
        return 0;
    }

    public int presencesVariation() {
        return 0;
    }

    public int positifsVariation() {
        return 0;
    }

    public int negatifsVariation() {
        return 0;
    }

}
