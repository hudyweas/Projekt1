package pl.hudyweas.testproject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String content;
    private final List<Answer> answers = new ArrayList<>();
    private boolean isAnsweredCorrectly = false;

    public Question(String content) {
        this.content = content;
    }

    public void addAnswer(String contentOfTheAnswer, boolean isItCorrectAnswer) {
        answers.add(new Answer(contentOfTheAnswer, isItCorrectAnswer));
        Collections.shuffle(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getContent() {
        return content;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        isAnsweredCorrectly = answeredCorrectly;
    }

    public boolean isAnsweredCorrectly() {
        return isAnsweredCorrectly;
    }

    public int getNumberOfAnswers(){
        return answers.size();
    }
}