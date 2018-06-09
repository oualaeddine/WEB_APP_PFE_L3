import model.beans.*;
import model.beans.humans.Client;
import model.db.daos.*;

import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        Logement logement = (Logement) new LogementDAO().getById(30);
        Appel appel = new Appel();
        appel.setNumeroTel("0555113161");

        Visite visite = new Visite();
        visite.setLogement(logement);
        visite.setTimestamp(Date.valueOf("2018-06-08"));
        visite.setHorraire(2);
        visite.setClient((Client) new ClientDAO().getById(35));

    }
}
