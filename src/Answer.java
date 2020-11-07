public class Answer {
    private final String content;
    private final boolean isItCorrectAnswer;
    private boolean isAnswerApproved = false;

    public Answer(String content, boolean isItCorrectAnswer) {
        this.content = content;
        this.isItCorrectAnswer = isItCorrectAnswer;
        
    }

    public String getContent() {
        return content;
    }

    public boolean isItCorrectAnswer() {
        return isItCorrectAnswer;
    }

    public void changeTheApprove(){
        if(isAnswerApproved = false)
            isAnswerApproved = true;
        else isAnswerApproved = false;
    }

    public boolean isAnswerApproved(){
        return isAnswerApproved;
    }
}
