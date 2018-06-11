import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(2018, 3, 15);

        System.out.println(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
    }
}
