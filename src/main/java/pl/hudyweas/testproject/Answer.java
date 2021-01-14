package pl.hudyweas.testproject;

public class Answer {
    private final String CONTENT;
    private final boolean IS_CORRECT;
    private boolean isApproved = false;

    public Answer(String content, boolean isCorrect) {
        this.CONTENT = content;
        this.IS_CORRECT = isCorrect;
    }

    public String getContent() {
        return CONTENT;
    }

    public boolean isCorrect() {
        return IS_CORRECT;
    }

    public void changeTheApprove() {
        isApproved = !isApproved;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
