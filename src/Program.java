import java.util.*;

public class Program {

    private DataBase dataBase = new DataBase("questions.txt");

    public void start(){
        introduction();

        Scanner in = new Scanner(System.in);
        System.out.println("Podaj ilosc pytan:");
        String numberOfQuestionsString;
        int numberOfQuestions;

        do{
            do{
                numberOfQuestionsString = in.nextLine();
                if(!isInteger(numberOfQuestionsString))
                    System.out.println("Wybierz liczbe calkowita!");
            }while(!isInteger(numberOfQuestionsString));
            numberOfQuestions = Integer.parseInt(numberOfQuestionsString);
            if(isInteger(numberOfQuestionsString) && numberOfQuestions > dataBase.getAmountOfQuestions())
                System.out.println("Wybrana ilosc pytan jest wieksza od ilosci pytan w bazie. Wybierz liczbe z przedzialu od 1 do "+dataBase.getAmountOfQuestions());
        }while(numberOfQuestions > dataBase.getAmountOfQuestions() || numberOfQuestions < 1);

        int points = 0;;

        List<Question> questions = new ArrayList<Question>();

        for(int questionIndex = 1; questionIndex <= numberOfQuestions; questionIndex++){
            Question question = drawQuestion();
            List<Answer> answers = question.getAnswers();

            questions.add((question));

            System.out.println("Pytanie "+questionIndex+":");
            displayQuestion(question);
            displayAnswers(answers);
            System.out.println("\nAby zatwierdzic odpowiedzi i przejsc do kolejnego pytania wcisnij 'x'\n\n");

            String userInput = in.nextLine();

            while(isOneOfStrings(userInput, "x", "a", "b", "c", "d") == false){
                System.out.println("Wprowadzony znak jest bledny");
                userInput = in.nextLine();
            }

            while (userInput.equals("x") == false){
                while(isOneOfStrings(userInput, "x", "a", "b", "c", "d") == false){
                    System.out.println("Wprowadzony znak jest bledny");
                    userInput = in.nextLine();
                }
                if(userInput.equals("x")) break;
                changeTheApprovalOfTheAnswer(answers, userInput);

                userInput = in.nextLine();
            }
            if(isQuestionAnsweredCorrectly(answers))
                question.setAnsweredCorrectly(true);

        }

        showScores(questions);
    }

    private void introduction() {
        System.out.println("Zasady i opis dzialania programu:\n"
                + "1. Test jest wielokrotnego wyboru\n"
                + "2. Test sklada sie z ilosci pytan podanej przez uzytkownika\n"
                + "3. Mozna wprowadzac tylko jedna odpowiedz na raz\n"
                + "4. Aby odpowiedziec na pytanie wpisz odpowiadajaca jej literke np. 'a'\n"
                + "5. Kazde pytanie, na ktore prawidlowo odpowiesz, daje 1 punkt\n"
                + "6. By przejsc do kolejnego pytania wcisnij 'x' \n\n"
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
        string = string.toLowerCase();
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

    private boolean isInteger(String number){
        Scanner in = new Scanner(number.trim());
        if(!in.hasNextInt()) return false;
        in.nextInt();
        return !in.hasNext();
    }

    private void showScores(List<Question> questions){
        int points = 0;
        int index = 0;

        for(Question question : questions){
            if(question.isAnsweredCorrectly()){
                System.out.println("Pytanie "+(++index)+": - odpowiedziales POPRAWNIE");
                points++;
            }else
                System.out.println("Pytanie "+(++index)+": - odpowiedziales BLEDNIE");
        }
        System.out.println("\nWynik:"+points+"/"+index);
    }
}
