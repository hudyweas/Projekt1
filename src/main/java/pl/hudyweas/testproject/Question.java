package pl.hudyweas.testproject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String CONTENT;
    private final List<Answer> ANSWERS = new ArrayList<>();
    private boolean isAnsweredCorrectly = false;
    private int id;

    public Question(String content, int id) {
        this.CONTENT = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addAnswer(String contentOfTheAnswer, boolean isItCorrectAnswer) {
        ANSWERS.add(new Answer(contentOfTheAnswer, isItCorrectAnswer));
        Collections.shuffle(ANSWERS);
    }

    public List<Answer> getAnswers() {
        return ANSWERS;
    }

    public String getContent() {
        return CONTENT;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        isAnsweredCorrectly = answeredCorrectly;
    }

    public boolean isAnsweredCorrectly() {
        return isAnsweredCorrectly;
    }

    public int getNumberOfAnswers(){
        return ANSWERS.size();
    }
}