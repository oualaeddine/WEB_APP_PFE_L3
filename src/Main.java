import model.beans.RDV;
import model.db.daos.VisitesDao;
import utils.GoogleMail;

import javax.mail.MessagingException;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        LinkedList list = new VisitesDao().getTakenRDVForAgents(3);
        for (Object rdv : list) {
            System.out.println(((RDV) rdv).toString());
        }
    }
}
