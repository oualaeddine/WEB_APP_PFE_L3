import model.beans.RDV;
import model.db.daos.VisitesDao;

import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        RDV rdv = new RDV();
        rdv.setDate(Date.valueOf("2018-04-28"));
        rdv.setHorraire(1);

        System.out.println(new VisitesDao().getFreeAgentsForVisite(rdv,3));
    }
}
