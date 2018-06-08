import control.statistics.globales.RapportsStats;
import control.statistics.perso.AgentStats;
import model.beans.Localite;
import model.beans.RDV;
import model.beans.Vente;
import model.beans.Visite;
import model.beans.humans.Client;
import model.db.daos.*;

import java.sql.Date;
import java.time.Month;
import java.util.Calendar;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Visite visite = new VisitesDao().getById(32);
        System.out.println(new VisitesDao().getNewAgentForVisit(visite));
    }
}
