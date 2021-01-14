package pl.hudyweas.testproject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private ArrayList<Question> questionsDataBase = new ArrayList<>();
    private int amountOfQuestions;
    DBConnectionSystem questionsDB;

    public DataBase() {
        questionsDB = new DBConnectionSystem();
        amountOfQuestions = getAmountOfQuestionsFromDB();
    }

    public List<Question> getQuestionsDataBase() {
        return questionsDataBase;
    }

    public int getAmountOfQuestions() {
        return amountOfQuestions;
    }

    private int getAmountOfQuestionsFromDB() {
        ArrayList<String> rs = questionsDB.getResultAsArrayList("SELECT COUNT(`id`) AS `NoID` FROM questions", "NoID");
        return Integer.parseInt(rs.get(0));
    }

    public void getQuestionsAndAnswersFromDatabase(int noOfUserQuestions) {
        ArrayList<Question> questions = getQuestions(noOfUserQuestions);
        for (Question question : questions) {
            String questionId = String.valueOf(question.getID());
            question.addAnswersArrayList(getAnswers(questionId));
        }

        questionsDataBase = questions;
    }

    private ArrayList<Question> getQuestions(int noOfUserQuestions) {
        ResultSet questionRS = questionsDB.getResultSet("SELECT * FROM questions ORDER BY RAND() LIMIT " + noOfUserQuestions);
        return getQuestionsFromResultSet(questionRS);
    }

    public ArrayList<Question> getQuestionsFromResultSet(ResultSet rs) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                questions.add(new Question(id, content));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return questions;
    }

    private ArrayList<Answer> getAnswers(String questionID) {
        ResultSet answersRS = questionsDB.getResultSet("SELECT * FROM answers WHERE question_id = " + questionID);
        return getAnswersFromResultSet(answersRS);
    }

    public ArrayList<Answer> getAnswersFromResultSet(ResultSet rs) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            while (rs.next()) {
                String content = rs.getString("content");
                boolean isCorrect = parseBoolean(rs.getString("isCorrect"));
                answers.add(new Answer(content, isCorrect));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return answers;
    }

    private boolean parseBoolean(String value) {
        return value.equals("1");
    }
}
