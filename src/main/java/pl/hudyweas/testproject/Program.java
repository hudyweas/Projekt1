package pl.hudyweas.testproject;
import java.util.*;

public class Program {

    private final DataBase dataBase = new DataBase();

    public void start(){
        introduction();

        Scanner in = new Scanner(System.in);
        System.out.println("Podaj ilosc pytan:");
        int numberOfQuestions = 0;

        do{
            if(in.hasNextInt()) {
                numberOfQuestions = in.nextInt();
                if(numberOfQuestions>dataBase.getAmountOfQuestions()){
                    System.out.println("Wybierz liczbe calkowita od 1 do "+dataBase.getAmountOfQuestions());
                }
            }else if(in.hasNextLine()){
                in.nextLine(); //czyszczenie scannera
                System.out.println("Wybierz liczbe calkowita od 1 do "+dataBase.getAmountOfQuestions());
            }
        }while(numberOfQuestions > dataBase.getAmountOfQuestions() || numberOfQuestions < 1);

        List<Question> userQuestions = new ArrayList<>();

        for(int questionIndex = 1; questionIndex <= numberOfQuestions; questionIndex++){
            Question question = drawQuestion();
            List<Answer> answers = question.getAnswers();

            userQuestions.add(question);

            System.out.println("Pytanie "+questionIndex+":");
            displayQuestion(question);
            displayAnswers(answers);
            System.out.println("\nAby zatwierdzic odpowiedzi i przejsc do kolejnego pytania wcisnij 'x'\n\n");

            if(in.hasNextLine())
                in.nextLine();

            String userInput = in.nextLine();

            while (!userInput.equals("x")){
                while(checkInput(userInput, question.getNumberOfAnswers())){
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

        showScores(userQuestions);
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
        }while(question.isUsed());

        question.setUsed(true);

        return question;
    }

    private void displayQuestion(Question question){
        System.out.println(question.getContent());
    }

    private void displayAnswers(List<Answer> answers){
        for(int indexOfAnswer = 0; indexOfAnswer < answers.size(); indexOfAnswer++){
            Answer answer = answers.get(indexOfAnswer);
            final String[] ABCD = new String[]{"A", "B", "C", "D"};
            System.out.println((ABCD[indexOfAnswer])+") "+answer.getContent());
        }
    }

    private boolean isOneOfStrings(String string, String[] table){
        string = string.toLowerCase();
        if(string.equals("x"))
            return true;

        for(String arg: table){
            if(string.equals(arg)) return true;
        }
        return false;
    }

    private void changeTheApprovalOfTheAnswer(List<Answer> answers, String userAnswer){
        answers.get(convertLetterToNumber(userAnswer)).changeTheApprove();
    }

    private boolean isQuestionAnsweredCorrectly(List<Answer> answers){
        for (Answer answer: answers) {
            if(answer.isCorrect()){
                if(!answer.isApproved())
                    return false;
            }else {
                if(answer.isApproved())
                    return false;
            }
        }
        return true;
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

    private boolean checkInput(String userInput, int noOfAnswers){
        final String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        return !isOneOfStrings(userInput, Arrays.copyOfRange(letters, 0, noOfAnswers));
    }

    public int convertLetterToNumber(String letter){
        final ArrayList letters = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        return letters.indexOf(letter);
    }
}
