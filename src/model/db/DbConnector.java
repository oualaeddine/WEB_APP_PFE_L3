package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnector {

    private static final String
            db_name = "heroku_405ddb6fa8921ad",
            host = "us-cdbr-iron-east-04.cleardb.net",
            port = "3306",
            user = "b1155db24dac1b",
            pass = "cdafe6b2f76bc16";

  /*  private static final String
            db_name = "soc_imm",
            host = "db4free.net",
            port = "3306",
            user = "soc_imm_root",
            pass = "soc_imm_root";*/

    private static final String conn = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
    private static Connection connexion;

    DbConnector() {
    }

    static void createConnexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connexion == null)
                connexion = DriverManager.getConnection(conn, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Statement getStatment() {
        try {
            return connexion.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
