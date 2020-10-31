import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataBase {
	
	private String data[] = new String[30];
	
	public DataBase() {
		File file = new File("questions.txt");
		try(Scanner in = new Scanner(file)) {
			int i=0;
			while(in.hasNextLine()) 
				data[i++] = in.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteQuestion(int x) {
		String data2[] = new String[data.length-1];
		for(int i= 0; i<data2.length-1; i++) {
			if(i!=x) {
				data2[i]=data[i];
			}else if(i >= x){
				data2[i]=data[i+1];
			}
		}
		
		data = Arrays.copyOf(data2, data2.length);
	}
	
	private String shuffleAnswers(String line) {
		String[] data2= line.split(";");
		ArrayList<String> answers = new ArrayList<String>();
		for(int i=1; i<=4;i++)
			answers.add(data2[i]);
		Collections.shuffle(answers);
		String end = "";
		StringBuilder s= new StringBuilder(end);
		s.append(data2[0]);
		for(int i=0; i<4;i++) {
			s.append(";");
			s.append(answers.get(i));
		}
		end = s.toString();
		return end;
 	}
	
	public String drawQuestion() {
		Random random = new Random();
		int drawn = random.nextInt(data.length);
		System.out.println(data.length +" "+ drawn);
		String question = shuffleAnswers(data[drawn]);
		deleteQuestion(drawn);
		return question;
	}
	
	public String getLine(int x) {
		return data[x];
	}
	
}
