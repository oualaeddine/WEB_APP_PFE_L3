import model.beans.RDV;
import model.beans.Visite;
import model.beans.humans.Employe;
import model.db.daos.VisitesDao;
import utils.GoogleMail;

import javax.mail.MessagingException;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Employe agent = new Employe();
        agent.setId(2);
        LinkedList<Visite> list = new VisitesDao().getVisitesByAgent(agent);
        for (Visite visite : list) {
            System.out.println(visite.getTimestamp());
        }
    }
}
