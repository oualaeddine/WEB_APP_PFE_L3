import model.beans.humans.Admin;
import model.db.daos.AdminsDAO;
import utils.Util;
import model.beans.Logement;


public class Main {
    public static void main(String[] args) {
        System.out.println(Util.objectToJson(new Admin()));
    }
}
