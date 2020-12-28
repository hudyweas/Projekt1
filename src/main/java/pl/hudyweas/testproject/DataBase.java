package pl.hudyweas.testproject;

import java.util.*;

public class DataBase {
    private ArrayList<Question> questionsDataBase = new ArrayList<>();
    private int amountOfQuestions;
    DBConnectionSystem questionsDB;

    {
        questionsDB = new DBConnectionSystem();
    }

    private boolean parseBoolean(String value) {
        return value.equals("1");
    }

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
        ArrayList<ArrayList> rs = questionsDB.getResultSetAsTable("SELECT COUNT(`id`) AS `NoID` FROM questions", "NoID");

        ArrayList<String> arraylist = rs.get(0);

        return Integer.parseInt(arraylist.get(0));
    }

    public void getQuestionsFromDB(int noOfUserquestions) {
        ArrayList<ArrayList<String>> questionsRS = questionsDB.getResultSetAsTable("SELECT * FROM (SELECT * FROM questions ORDER BY RAND() LIMIT " + noOfUserquestions + ") AS T1 ORDER BY id", "id", "content");

        StringBuilder answerMySqlWhereClause = new StringBuilder("question_id=0"); //"question_id=0" only to eliminate "OR" at the end of the string
        ArrayList<Question> questions = new ArrayList<>();
        for (ArrayList<String> questionResultSetRow : questionsRS) {
            answerMySqlWhereClause.append(getAnswersMySqlWhereClause(questionResultSetRow.get(0)));
            questions.add(new Question(questionResultSetRow.get(1), Integer.parseInt(questionResultSetRow.get(0))));
        }

        ArrayList<ArrayList<String>> answersRS = questionsDB.getResultSetAsTable("SELECT * FROM answers WHERE " + answerMySqlWhereClause + " ORDER BY question_id", "id", "content", "isCorrect", "question_id");

        int questionIndex = 0;
        for (ArrayList<String> arraylist : answersRS) {
            if (Integer.parseInt(arraylist.get(3)) != questions.get(questionIndex).getId()) {
                questionIndex++;
            }
            questions.get(questionIndex).addAnswer(arraylist.get(1), parseBoolean(arraylist.get(2)));
        }

        Collections.shuffle(questions);
        questionsDataBase = (ArrayList<Question>) questions.clone();
    }

    public String getAnswersMySqlWhereClause(String questionID) {
        String whereClause = " OR ";
        whereClause += "question_id =" + questionID;
        return whereClause;
    }
}
