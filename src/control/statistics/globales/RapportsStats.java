package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.RapportDAO;

public class RapportsStats extends Stats {

    public RapportsStats() {
        dao = new RapportDAO();
    }

    // TODO: 5/4/2018
    public int rapportsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int absencesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int presencesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int positifsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int negatifsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public float positifPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float negatifPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float abcencesPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float presencesPercentage() {
        return 0;
    }

    /**
     * variations
     **/
    // TODO: 5/4/2018
    public int absencesVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int presencesVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int positifsVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int negatifsVariation() {
        return 0;
    }

}
