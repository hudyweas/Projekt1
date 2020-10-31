import java.util.Scanner;

public class Program {
	
	private DataBase data = new DataBase();
	private Scanner in = new Scanner(System.in);
	private int counter = 0;
	private int points = 0;
	
	private String currentQuestion;
	private String[] currentAnswers = new String[4];
	private String[] currentGoodAnswers = new String[4];;
	private boolean userAnswers[] = new boolean[4];
	
	private void introduction() {
		System.out.println("Zasady i opis dzia�ania programu:\n"
				+ "1. Test jest wielokrotnego wyboru\n"
				+ "2. Test sk�ada si� z 20 pyta�"
				+ "3. Mo�na wprowadza� tylko jedn� odpowiedz na raz\n"
				+ "4. Ka�da pytanie, na kt�re prawid�owo odpowiesz, daje 1 punkt\n"
				+ "5. By przej�� do kolejnego pytania wci�nij 'x' \n\n"
				+ "By przej�� dalej - wci�nij dowolny klawisz");
		
		in.nextLine();
	}
	
	private void getQuestion() {
		counter++;
		String line[] = data.drawQuestion().split(";");
		String answers[][] = new String[4][2];
		currentQuestion = line[0];
		
		for(int i=0;i<4;i++) {
			answers[i] = line[i+1].split(":");		
			currentAnswers[i]= answers[i][0];
			currentGoodAnswers[i]=answers[i][1];
			this.userAnswers[i] = false;
		}
	}
	
	private void displayQuestion() {
		System.out.println("Pytanie "+ counter +":");
		System.out.println(currentQuestion);
		for(int i=0;i<4;i++)
			System.out.println((i+1)+". "+currentAnswers[i]);
		
		System.out.println("By przej�� do kolejnego pytania wci�nij 'x'");
	}
	
	private boolean checkAnswers() {
		for(int i=0;i<4;i++) {
			if(userAnswers[i] == true && currentGoodAnswers[i].contentEquals("0"))
				return false;
			else if(userAnswers[i] == false && currentGoodAnswers[i].contentEquals("1"))
				return false;
		}
		return true;
	}
	
	private void showScores() {
		System.out.println("\nOdpowiedzia�e� na "+points+" pyta� poprwanie.");
	}
	
	public void start() {
		introduction();
		
		for(int i=0; i<20;i++) {
			getQuestion();
			displayQuestion();
			while(true) {
				String next = in.nextLine();
				
				switch(next) {
				case "1":
					if(userAnswers[0] == false)
						userAnswers[0] = true;
					else userAnswers[0] = false;
					break;
					
				case "2":
					if(userAnswers[1] == false)
						userAnswers[1] = true;
					else userAnswers[1] = false;
					break;
					
				case "3":
					if(userAnswers[2] == false)
						userAnswers[2] = true;
					else userAnswers[2] = false;
					break;
					
				case "4":
					if(userAnswers[3] == false)
						userAnswers[3] = true;
					else userAnswers[3] = false;
					break;

				default:
					break;
				}
				
				if(next.contentEquals("x")) {
					break;
				}
			}
			
			if(checkAnswers())
				points++;
			
		}
		
		showScores();
	}
}
