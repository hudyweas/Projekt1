package pl.hudyweas.testproject;
import java.util.*;

public class DataBase {
    private ArrayList<Question> questionsDataBase = new ArrayList<>();
    private int amountOfQuestions;
    DBConnectionSystem questionsDB;

    {
        questionsDB = new DBConnectionSystem("jdbc:mysql://localhost:3306/questions", "root", "");
    }

    private boolean parseBoolean(String value){
        if(value.equals("1"))
            return true;
        else
            return false;
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

        int noID = Integer.parseInt(arraylist.get(0));
        return noID;
    }

    public void getQuestionsFromDB(int noOfUserquestions){
        ArrayList<ArrayList<String>> questionsRS =questionsDB.getResultSetAsTable("SELECT * FROM (SELECT * FROM questions ORDER BY RAND() LIMIT "+noOfUserquestions+") AS T1 ORDER BY id", "id", "content");

        String questionsIdString = "question_id=0"; //"question_id=0" only to eliminate "OR" at the end of the string
        ArrayList<Question> questions = new ArrayList<>();
        for (ArrayList<String> arraylist:questionsRS) {
            questionsIdString += " OR ";
            questionsIdString += "question_id =" + arraylist.get(0);
            questions.add(new Question(arraylist.get(1), Integer.parseInt(arraylist.get(0))));
        }

        ArrayList<ArrayList<String>> answersRS =questionsDB.getResultSetAsTable("SELECT * FROM answers WHERE "+questionsIdString+" ORDER BY question_id", "id", "content", "isCorrect", "question_id");

        int questionIndex = 0;
        for (ArrayList<String> arraylist:answersRS) {
            if(Integer.parseInt(arraylist.get(3))==questions.get(questionIndex).getId()){
                questions.get(questionIndex).addAnswer(arraylist.get(1), parseBoolean(arraylist.get(2)));
            }else{
                questionIndex++;
                questions.get(questionIndex).addAnswer(arraylist.get(1), parseBoolean(arraylist.get(2)));
            }
        }

        Collections.shuffle(questions);
        questionsDataBase = (ArrayList<Question>) questions.clone();
    }
}
