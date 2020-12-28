package pl.hudyweas.testproject;

import java.util.*;

public class Program {

    private final DataBase DATABASE = new DataBase();

    public void start() {
        printIntroduction();
        int userNoOfQuestions = inputIntFromRange(1, DATABASE.getAmountOfQuestions());

        DATABASE.getQuestionsFromDB(userNoOfQuestions);

        List<Question> testQuestions = DATABASE.getQuestionsDataBase();

        int questionIndex = 1;
        for (Question currentQuestion : testQuestions) {
            List<Answer> currentAnswers = currentQuestion.getAnswers();

            displayQuestionAndAnswers(currentQuestion, currentAnswers, questionIndex++);

            String userAnswer;
            do {
                userAnswer = inputAnswer(currentQuestion.getNumberOfAnswers());
                if (!userAnswer.equals("x"))
                    changeTheApprovalOfTheAnswer(currentAnswers, userAnswer);
            } while (!userAnswer.equals("x"));

            if (isQuestionAnsweredCorrectly(currentAnswers))
                currentQuestion.setAnsweredCorrectly(true);
        }
        printScores(testQuestions);
    }

    private void displayQuestionAndAnswers(Question question, List<Answer> answers, int questionIndex) {
        System.out.println("Pytanie " + questionIndex + ":");
        displayQuestion(question);
        displayAnswers(answers);
        System.out.println("\nAby zatwierdzic odpowiedzi i przejsc do kolejnego pytania wcisnij 'x'\n\n");
    }

    public static int inputIntFromRange(int start, int end) {
        System.out.printf("Podaj liczbę z zakresu %s do %s:", start, end);
        Scanner in = new Scanner(System.in);
        int outputInt;
        if (in.hasNextInt())
            outputInt = in.nextInt();
        else {
            System.out.println("Błąd wprowadzania danych");
            outputInt = inputIntFromRange(start, end);
        }

        if (!isFromRange(outputInt, start, end)) {
            System.out.println("Błąd wprowadzania danych");
            outputInt = inputIntFromRange(start, end);
        }

        return outputInt;
    }

    private String inputAnswer(int noOfQuestions) {
        System.out.println("Podaj odpowiedź lub X, aby zakończyć:");
        Scanner in = new Scanner(System.in);
        String output = "";
        if (in.hasNextLine()) {
            output = in.nextLine().toLowerCase();
            if (checkInput(output, noOfQuestions))
                return output;
            else {
                System.out.println("Błąd wprowadzania danych");
                output = inputAnswer(noOfQuestions);
            }
        }
        return output;
    }

    private static boolean isFromRange(int value, int start, int end) {
        if (value > end)
            return false;
        else return value >= start;
    }

    private void printIntroduction() {
        System.out.println("Zasady i opis dzialania programu:\n"
                + "1. Test jest wielokrotnego wyboru\n"
                + "2. Test sklada sie z ilosci pytan podanej przez uzytkownika\n"
                + "3. Mozna wprowadzac tylko jedna odpowiedz na raz\n"
                + "4. Aby odpowiedziec na pytanie wpisz odpowiadajaca jej literke np. 'a'\n"
                + "5. Kazde pytanie, na ktore prawidlowo odpowiesz, daje 1 punkt\n"
                + "6. By przejsc do kolejnego pytania wcisnij 'x' \n\n"
                + "By przejsc dalej - wcisnij dowolny klawisz");
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getContent());
    }

    private void displayAnswers(List<Answer> answers) {
        for (int indexOfAnswer = 0; indexOfAnswer < answers.size(); indexOfAnswer++) {
            Answer answer = answers.get(indexOfAnswer);
            final String[] ABCD = new String[]{"A", "B", "C", "D"};
            System.out.println((ABCD[indexOfAnswer]) + ") " + answer.getContent());
        }
    }

    private boolean isOneOfStrings(String string, String[] table) {
        string = string.toLowerCase();
        if (string.equals("x"))
            return true;

        for (String arg : table) {
            if (string.equals(arg)) return true;
        }
        return false;
    }

    private void changeTheApprovalOfTheAnswer(List<Answer> answers, String userAnswer) {
        answers.get(convertLetterToNumber(userAnswer)).changeTheApprove();
    }

    private boolean isQuestionAnsweredCorrectly(List<Answer> answers) {
        for (Answer answer : answers)
            if (answer.isCorrect()) {
                if (!answer.isApproved())
                    return false;
            } else if (answer.isApproved())
                return false;
        return true;
    }

    private void printScores(List<Question> questions) {
        int points = 0;
        int index = 0;

        for (Question question : questions) {
            if (question.isAnsweredCorrectly()) {
                System.out.println("Pytanie " + (++index) + ": - odpowiedziales POPRAWNIE");
                points++;
            } else
                System.out.println("Pytanie " + (++index) + ": - odpowiedziales BLEDNIE");
        }
        System.out.println("\nWynik:" + points + "/" + index);
    }

    private boolean checkInput(String userInput, int noOfAnswers) {
        final String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        return isOneOfStrings(userInput, Arrays.copyOfRange(letters, 0, noOfAnswers));
    }

    public int convertLetterToNumber(String letter) {
        final ArrayList letters = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        return letters.indexOf(letter);
    }
}
