import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataBase {

    private final List<Question> questionsDataBase = new ArrayList<Question>();

    public DataBase(String fileName){
        File dataFile = new File(fileName);
        Scanner in = null;
        try {
            in = new Scanner(dataFile);

            if(in.hasNextLine()){
                String header = in.nextLine(); //pomijanie naglowka podczas importu
            }

            while(in.hasNextLine()) {
                String[] importedString = in.nextLine().split(";");
                Question question = new Question(importedString[0]);

                for (int i = 1; i < importedString.length; i++) { //petla dodajaca odpowiedzi do pytania
                    String[] answerString = importedString[i].split(":");
                    String answer = answerString[0];
                    boolean isItCorrectAnswer;

                    if (answerString[1].equals("1"))
                        isItCorrectAnswer = true;
                    else isItCorrectAnswer = false;

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
