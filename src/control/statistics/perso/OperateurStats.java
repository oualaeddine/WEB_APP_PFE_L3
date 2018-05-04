package control.statistics.perso;

import control.statistics.PersoStats;

public class OperateurStats extends PersoStats {
    public OperateurStats(int userId) {
        super(userId);
    }

    /**
     * @return the number of unread messages
     */
    public int getNewMessagesNbr() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming reported visits
     */
    public int getReportedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming programmed visits
     */
    public int getProgrammedVisites() {// TODO: 5/3/2018
        return 0;
    }
}
