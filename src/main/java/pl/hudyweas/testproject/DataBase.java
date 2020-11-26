package pl.hudyweas.testproject;
import java.sql.*;
import java.util.*;

public class DataBase {
    private final List<Question> questionsDataBase = new ArrayList<>();
    private final int amountOfQuestions;

    public DataBase() {
        amountOfQuestions = getAmountOfQuestionsFromDB();
    }

    public List<Question> getQuestionsDataBase() {
        return questionsDataBase;
    }

    public int getAmountOfQuestions() {
        return amountOfQuestions;
    }

    private int getAmountOfQuestionsFromDB() {
        Statement stmt;
        Connection conn;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "");
            stmt = conn.createStatement();

            if (stmt.execute("SELECT COUNT(`id`) AS `NoID` FROM questions")) {
                rs = stmt.getResultSet();
            }
            assert rs != null;
            if (rs.next()) {
                return rs.getInt("NoID");
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void getQuestionsFromDB(int NoOfUserQuestions){
        Random random = new Random();
        ArrayList<Integer> questionsIndexes = new ArrayList<>();
        for(int i=0; i<NoOfUserQuestions;i++){
            int generatedIndex = random.nextInt(amountOfQuestions)+1;
            if(!questionsIndexes.contains(generatedIndex))
                questionsIndexes.add(generatedIndex);
            else {
                do{
                    generatedIndex = random.nextInt(amountOfQuestions)+1;
                }while(questionsIndexes.contains(generatedIndex));
                questionsIndexes.add(generatedIndex);
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questions", "root", "");
            stmt = conn.createStatement();

            for(Integer index: questionsIndexes){
                if (stmt.execute("SELECT * FROM questions WHERE id="+ index)) {
                    rs = stmt.getResultSet();
                }

                Question question = null;
                while (rs.next()) {
                    question = new Question(rs.getString("content"));
                }

                if (stmt.execute("SELECT * FROM answers WHERE question_id="+index)) {
                    rs = stmt.getResultSet();
                }

                while (rs.next()) {
                    assert question != null;
                    question.addAnswer(rs.getString("content"), rs.getBoolean("isCorrect"));
                }
                questionsDataBase.add(question);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
    }
}
