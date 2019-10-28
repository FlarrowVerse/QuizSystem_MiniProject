import java.io.*;
import java.util.*;

class Question {
	public String statement;
	public String[] ops;
	private char ans;

	public Question(String st, String op, char a) {
		statement = st;
		ops = op.split(";");
		ans = a;
	}

	public char getAns() {
		return ans;
	}
}

public class QuizMaster {

	public static ArrayList<Question> getQuestions() throws IOException {
		
		ArrayList<Question> quiz = new ArrayList<Question>();

		File file = null;
		FileInputStream fis = null;
		BufferedReader br = null;

		try {
			file = new File("Quiz-Questions.txt"); 						// creating new file object
			fis = new FileInputStream(file);	// opening specified file for reading as an input stream
			br = new BufferedReader(new InputStreamReader(fis));		// reading using BufferedReader

			String statement = "", ops = "";
			char ans = '\0';
			int line_no = 0;

			while(statement != null) {
				statement = br.readLine(); ops = br.readLine(); ans = (char)br.read();
				br.readLine(); br.readLine(); // flushing stream manually

				quiz.add(new Question(statement, ops, ans));
				
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: File not found. \nDetails:::"+e);
		}
		catch(Exception e) {
			System.out.println("ERROR: "+e);
		}

		// cleanup, free resources
		finally {
			br.close();
			fis.close();
			
			return quiz;
		}
	}

	public static int play() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Question> quiz = getQuestions();
		int count = 1, score = 0;
		for(Question q: quiz) {
			System.out.println("Q."+count+") "+q.statement);
			char op_no = 'a';
			for(String op: q.ops) {
				System.out.println("\t"+op_no+". "+op);
				op_no++;
			}
			System.out.print("Enter your answer: ");
			char ans = (char)br.read();
			br.read();
			if(ans == q.getAns()) {
				System.out.println("Correct! :-)");
				score++;
			}else {
				System.out.println("Wrong! :-(");
			}
			count++;
		}
		return score;
		
	}
	
}
