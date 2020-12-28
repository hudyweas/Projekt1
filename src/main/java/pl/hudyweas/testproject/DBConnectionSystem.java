package pl.hudyweas.testproject;
import java.sql.*;
import java.util.*;

public class DBConnectionSystem {
    private Connection conn = null;

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBConnectionSystem(String url, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList getResultSetAsTable(String query, String... resultSetKeyWords) {
        ArrayList <Object> output = new ArrayList<>();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;

            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }

            while(rs.next()) {
                ArrayList <String> row = new ArrayList<>();
                for(int i=0; i<resultSetKeyWords.length; i++){
                    row.add(rs.getString(resultSetKeyWords[i]));
                }
                output.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }
}
