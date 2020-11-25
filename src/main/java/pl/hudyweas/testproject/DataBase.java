package pl.hudyweas.testproject;
import java.sql.*;
import java.util.*;

public class DataBase {
    private final List<Question> questionsDataBase = new ArrayList<>();

    public DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "");

            stmt = conn.createStatement();

            if (stmt.execute("SELECT * FROM questions")) {
                rs = stmt.getResultSet();
            }

            while(rs.next()){
                Question question = new Question(rs.getString("content"));
                questionsDataBase.add(question);
            }

            if (stmt.execute("SELECT * FROM answers")) {
                rs = stmt.getResultSet();
            }

            while(rs.next()){
                questionsDataBase.get(rs.getInt("question_id")-1).addAnswer(rs.getString("content"), rs.getBoolean("isCorrect"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                stmt = null;
            }
        }
    }

        public Question getQuestion ( int indexOfTheQuestion){
            return questionsDataBase.get(indexOfTheQuestion);
        }

        public int getAmountOfQuestions () {
            return questionsDataBase.size();
        }

    }
