package pl.hudyweas.testproject;

public class Answer {
    private final String content;
    private final boolean isCorrect;
    private boolean isApproved = false;

    public Answer(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public String getContent() {
        return content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void changeTheApprove(){
        isApproved = !isApproved;
    }

    public boolean isApproved(){
        return isApproved;
    }
}
