import java.util.Scanner;

public class Program {
	
	private DataBase data = new DataBase();
	private Scanner in = new Scanner(System.in);
	private int licznik = 0;
	private int points = 0;
	
	private String currentQuestion;
	private String[] currentAnswers;
	private String[] currentGoodAnswers;
	private boolean answered[] = new boolean[4];
	
	private void introduction() {
		System.out.println("Zasady i opis dzia³ania programu:\n"
				+ "1. Test jest wielokrotnego wyboru\n"
				+ "2. Test sk³ada siê z 20 pytañ"
				+ "3. Mo¿na wprowadzaæ tylko jedn¹ odpowiedz na raz\n"
				+ "4. Ka¿da pytanie, na które prawid³owo odpowiesz, daje 1 punkt\n"
				+ "5. By przejœæ do kolejnego pytania wciœnij 'x' \n\n"
				+ "By przejœæ dalej - wciœnij dowolny klawisz");
		
		in.nextLine(); //czekanie na wciœniêcie dowolnego klawisza
	}
	
	private void getQuestion() {
		licznik++;	
		String line[] = data.drawQuestion().split(";");
		String answers[][] = new String[4][2];
		currentQuestion = line[0];
		
		for(int i=0;i<4;i++) {
			answers[i] = line[i+1].split(":");
			
			System.out.println(answers[i][0]);
			
			currentAnswers[i]= answers[i][0];
			currentGoodAnswers[i]=answers[i][1];
			answered[i] = false;
		}
	}
	
	private void displayQuestion() {
		System.out.println("Pytanie "+licznik+":");
		System.out.println(currentQuestion);
		for(int i=0;i<4;i++)
			System.out.println((i+1)+". "+currentAnswers[i]);
		
		System.out.println("By przejœæ do kolejnego pytania wciœnij 'x'");
	}
	
	private boolean checkAnswers() {
		for(int i=0;i<4;i++)
			if(answered[i] == true && currentGoodAnswers[i].contentEquals("0"))
				return false;
			else if(answered[i] == false && currentGoodAnswers[i].contentEquals("1"))
				return false;
		return true;
	}
	
	private void showScores() {
		System.out.println("\nOdpowiedzia³eœ na "+points+" pytañ poprwanie.");
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
					if(answered[0] == false)
						answered[0] = true;
					else answered[0] = false;
					break;
					
				case "2":
					if(answered[1] == false)
						answered[1] = true;
					else answered[1] = false;
					break;
					
				case "3":
					if(answered[2] == false)
						answered[2] = true;
					else answered[2] = false;
					break;
					
				case "4":
					if(answered[3] == false)
						answered[3] = true;
					else answered[3] = false;
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
