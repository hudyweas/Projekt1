package pl.hudyweas.testproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String CONTENT;
    private List<Answer> answers = new ArrayList<>();
    private boolean isAnsweredCorrectly = false;
    private final int ID;

    public Question(int id, String content) {
        this.CONTENT = content;
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public void addAnswersArrayList(ArrayList<Answer> answers) {
        this.answers = answers;
        Collections.shuffle(this.answers);
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