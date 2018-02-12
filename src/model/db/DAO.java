package model.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DAO {
    protected Statement statement;

    public DAO() {
        this.statement = DbConnector.getStatment();
    }

    protected boolean deleteById(int id, String table) {
        try {
            statement.execute("DELETE FROM " + table + " WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
