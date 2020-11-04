public class Answer {
    private String contentOfTheAnswer;
    private boolean isItCorrectAnswer;
    private boolean isAnswerApproved = false;

    public Answer(String contentOfTheAnswer, boolean isItCorrectAnswer) {
        this.contentOfTheAnswer = contentOfTheAnswer;
        this.isItCorrectAnswer = isItCorrectAnswer;
        
    }

    public String getContentOfTheAnswer() {
        return contentOfTheAnswer;
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
