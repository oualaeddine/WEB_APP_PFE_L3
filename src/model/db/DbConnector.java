package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnector {

//      private static final String
//                   db_name = "soc_imm",
//            host = "localhost",
//            port = "124",
//            user = "root",
//            pass = "1234";

    private static final String
            db_name = "sql9231481",
            host = "sql9.freemysqlhosting.net",
            port = "3306",
            user = "sql9231481",
            pass = "96lx64wwfH";

    private static final String conn = "jdbc:mysql://" + host + ":" + port + "/" + db_name;

    public DbConnector() {
    }

    static Statement getStatment() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connexion = DriverManager.getConnection(conn, user, pass);
            return connexion.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
