import model.db.daos.AdminsDAO;
import utils.Util;
import model.beans.Logement;


public class Main {
    public static void main(String[] args) {
        Util.objectToJson(new AdminsDAO().getAll());
    }
}
