import java.util.*;

public class Program {

    private DataBase dataBase = new DataBase("questions.txt");

    public void start(){
        introduction();

        Scanner in = new Scanner(System.in);
        System.out.println("Podaj ilosc pytan:");
        int numberOfQuestions = Integer.parseInt(in.nextLine());
        int points = 0;

        for(int questionIndex = 1; questionIndex <= numberOfQuestions; questionIndex++){
            Question question = drawQuestion();
            List<Answer> answers = question.getAnswers();

            System.out.println("Pytanie "+questionIndex+":");
            displayQuestion(question);
            displayAnswers(answers);
            System.out.println("\nAby zatwierdzic odpowiedzi i przejsc do kolejnego pytania wcisnij 'x'\n\n");

            String userInput = in.nextLine();
            userInput.toLowerCase();

            while(isOneOfStrings(userInput, "x", "a", "b", "c", "d") == false){
                System.out.println("Wprowadzony znak jest bledny");
                userInput = in.nextLine().toLowerCase();
            }

            while (userInput.equals("x") == false){
                while(isOneOfStrings(userInput, "x", "a", "b", "c", "d") == false){
                    System.out.println("Wprowadzony znak jest bledny");
                    userInput = in.nextLine().toLowerCase();
                }
                if(userInput.equals("x")) break;
                changeTheApprovalOfTheAnswer(answers, userInput);

                userInput = in.nextLine().toLowerCase();
            }
            if(isQuestionAnsweredCorrectly(answers)) points++;
        }
        System.out.println("Brawo! Zakonczyles test z iloscia "+points+" punktów!");
    }

    private void introduction() {
        System.out.println("Zasady i opis dzialania programu:\n"
                + "1. Test jest wielokrotnego wyboru\n"
                + "2. Test sklada się z ilosci pytan podanej przez uzytkownika\n"
                + "3. Mozna wprowadzac tylko jedna odpowiedz na raz\n"
                + "4. Kazde pytanie, na ktore prawidlowo odpowiesz, daje 1 punkt\n"
                + "5. By przejsc do kolejnego pytania wcisnij 'x' \n\n"
                + "By przejsc dalej - wcisnij dowolny klawisz");
    }

    private Question drawQuestion(){
        Random random = new Random();
        Question question;

        do{
            int drawnNumber = random.nextInt(dataBase.getAmountOfQuestions());
            question = dataBase.getQuestion(drawnNumber);
        }while(question.isHasBeenUsed() == true);

        question.setHasBeenUsed(true);

        return question;
    }

    private void displayQuestion(Question question){
        System.out.println(question.getContentOfTheQuestion());
    }

    private void displayAnswers(List<Answer> answers){
        for(int indexOfAnswer = 0; indexOfAnswer < answers.size(); indexOfAnswer++){
            Answer answer = answers.get(indexOfAnswer);
            final String[] ABCD = new String[]{"A", "B", "C", "D"};
            System.out.println((ABCD[indexOfAnswer])+") "+answer.getContentOfTheAnswer());
        }
    }

    private boolean isOneOfStrings(String string, String... args){
        for(String arg: args){
            if(string.equals(arg)) return true;
        }
        return false;
    }

    private void changeTheApprovalOfTheAnswer(List<Answer> answers, String userAnswer){
        switch (userAnswer){
            case "a":
                answers.get(0).changeTheApprove();
                break;
            case "b":
                answers.get(1).changeTheApprove();
                break;
            case "c":
                answers.get(2).changeTheApprove();
                break;
            case "d":
                answers.get(3).changeTheApprove();
                break;
        }
    }

    private boolean isQuestionAnsweredCorrectly(List<Answer> answers){
        for (Answer answer: answers) {
            if(answer.isItCorrectAnswer()){
                if(answer.isAnswerApproved() == false)
                    return false;
            }else {
                if(answer.isAnswerApproved() == true)
                    return false;
            }
        }
        return true;
    }
}
