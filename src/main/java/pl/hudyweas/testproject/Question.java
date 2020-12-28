package pl.hudyweas.testproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String CONTENT;
    private List<Answer> answers = new ArrayList<>();
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
        answers.add(new Answer(contentOfTheAnswer, isItCorrectAnswer));
        Collections.shuffle(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
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

    public int getNumberOfAnswers() {
        return answers.size();
    }
}