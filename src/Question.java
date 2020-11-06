import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private String contentOfTheQuestion;
    private boolean hasBeenUsed;
    private List<Answer> answers = new ArrayList<Answer>();
    private boolean isAnsweredCorrectly = false;

    public Question() {

    }

    public Question(String contentOfTheQuestion) {
        this.contentOfTheQuestion = contentOfTheQuestion;
    }

    public void setContentOfTheQuestion(String contentOfTheQuestion) {
        this.contentOfTheQuestion = contentOfTheQuestion;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
        Collections.shuffle(answers);
    }

    public void setHasBeenUsed(boolean hasBeenUsed) {
        this.hasBeenUsed = hasBeenUsed;
    }

    public void addAnswer(String contentOfTheAnswer, boolean isItCorrectAnswer) {
        answers.add(new Answer(contentOfTheAnswer, isItCorrectAnswer));
        Collections.shuffle(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getContentOfTheQuestion() {
        return contentOfTheQuestion;
    }

    public boolean isHasBeenUsed() {
        return hasBeenUsed;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        isAnsweredCorrectly = answeredCorrectly;
    }

    public boolean isAnsweredCorrectly() {
        return isAnsweredCorrectly;
    }
}