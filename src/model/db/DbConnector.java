package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnector {

      private static final String
                   db_name = "soc_imm",
            host = "localhost",
            port = "124",
            user = "root",
            pass = "1234";

//    private static final String
//            db_name = "sql11220882",
//            host = "sql11.freemysqlhosting.net",
//            port = "124",
//            user = "sql11220882",
//            pass = "kbtEFgU5Xi";

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
