package pl.hudyweas.testproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataBaseOld {

    private final List<Question> questionsDataBase = new ArrayList<>();

    public DataBaseOld(String fileName){
        File dataFile = new File(fileName);
        Scanner in;
        try {
            in = new Scanner(dataFile);

            if(in.hasNextLine()){
                in.nextLine(); //pomijanie naglowka podczas importu
            }

            while(in.hasNextLine()) {
                String[] importedString = in.nextLine().split(";");
                Question question = new Question(importedString[0]);

                for (int i = 1; i < importedString.length; i++) { //petla dodajaca odpowiedzi do pytania
                    String[] answerString = importedString[i].split(":");
                    String answer = answerString[0];

                    boolean isItCorrectAnswer = answerString[1].equals("1");

                    question.addAnswer(answer, isItCorrectAnswer);
                }

                questionsDataBase.add(question);
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Question getQuestion(int indexOfTheQuestion){
        return questionsDataBase.get(indexOfTheQuestion);
    }

    public int getAmountOfQuestions(){
        return questionsDataBase.size();
    }
}
