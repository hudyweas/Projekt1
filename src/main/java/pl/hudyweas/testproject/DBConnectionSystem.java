package pl.hudyweas.testproject;

import java.sql.*;
import java.util.ArrayList;

public class DBConnectionSystem {
    private Connection conn = null;

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBConnectionSystem() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getResultSet(String query) {
        Statement stmt;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public ArrayList<String> getResultAsArrayList(String query, String... resultSetKeyWords) {
        ArrayList<String> row = new ArrayList<>();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;

            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }

            while (rs.next()) {
                row.add(resultSetRowToString(rs, resultSetKeyWords));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    private String resultSetRowToString(ResultSet rs, String... resultSetKeyWords) throws SQLException {
        StringBuilder output = null;
        int rsKeyWordsLength = resultSetKeyWords.length;

        if (rsKeyWordsLength == 1)
            output = new StringBuilder(rs.getString(resultSetKeyWords[0]));
        else {
            for (String keyWord : resultSetKeyWords) {
                output.append(rs.getString(keyWord)).append(";");
            }
            output = new StringBuilder(deleteLastCharFromString(output.toString()));
        }
        return output.toString();
    }

    private String deleteLastCharFromString(String string) {
        return string.substring(0, string.length() - 2);
    }
}
