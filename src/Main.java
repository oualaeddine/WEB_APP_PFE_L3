import control.system.Util;
import model.beans.Logement;


public class Main {
    public static void main(String[] args) {
        Util.objectToJson(new Logement());
    }
}
