import control.statistics.globales.RapportsStats;
import control.statistics.perso.AgentStats;
import model.beans.Localite;
import model.beans.RDV;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.RapportDAO;
import model.db.daos.VentesDAO;
import model.db.daos.VersementDAO;
import model.db.daos.VisitesDao;

import java.sql.Date;
import java.time.Month;
import java.util.Calendar;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        System.out.println(new VersementDAO().getSommeVersementsByVente(4));
    }
}
