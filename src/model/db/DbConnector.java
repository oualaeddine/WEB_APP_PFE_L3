package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnector {

    private static final String conn = "jdbc:mysql://localhost:3306/soc_imm", user = "root", pass = "1234";

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
