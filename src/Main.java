import model.beans.*;
import model.beans.humans.Client;
import model.db.daos.*;

import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        java.util.Date date = new Date(18, 3, 15);
        System.out.println(Date.valueOf(String.valueOf(date)));
    }
}
