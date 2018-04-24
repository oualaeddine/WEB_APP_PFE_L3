import control.api.VisiteApi;
import model.beans.RDV;
import model.beans.humans.Employe;
import model.db.daos.VisitesDao;
import utils.GoogleMail;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        RDV rdv = new RDV();
        rdv.setDate(Date.valueOf("2018-04-26"));
        rdv.setHorraire(1);

        System.out.println(new VisitesDao().getFreeAgentsForVisite(rdv,3));
    }
}
