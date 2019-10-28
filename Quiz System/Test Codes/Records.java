import java.io.*;
import java.util.*;

public class Records {

	public static ArrayList<String> getHighScores() throws IOException {

		// important declarations
		ArrayList<String> quiz = new ArrayList<String>();

		File file = null;
		FileInputStream fis = null;
		BufferedReader br = null;

		try {
			file = new File("High-Scores.txt"); 						// creating new file object
			fis = new FileInputStream(file);	// opening specified file for reading as an input stream
			br = new BufferedReader(new InputStreamReader(fis));		// reading using BufferedReader

			String line = br.readLine();
			int line_no = 1;

			while(line != null) {
				quiz.add(line);									// adding the name to the list of quizes
				// System.out.println("Line-"+line_no+"\t"+line);
				line = br.readLine(); line_no++;				// reading an entire line and providing line number				
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


	public static void setHighScores(ArrayList<String> scores) throws IOException {
		// important declarations
		
		File file = null;
		FileOutputStream fos = null;
		BufferedWriter bw = null;

		try {
			file = new File("High-Scores.txt"); 						// creating new file object
			fos = new FileOutputStream(file);	// opening specified file for reading as an input stream
			bw = new BufferedWriter(new OutputStreamWriter(fos));		// reading using BufferedReader

			int line_no = 0;

			for(String score: scores) {
				line_no++;
				bw.write(line_no+score.substring(score.indexOf('\t'))+"\n");	 // adding the name to the list of quizes
				// System.out.println("Line-"+line_no+"\t"+line);				
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: File not found. \nDetails:::"+e);
		}
		catch(Exception e) {
			System.out.println("ERROR: "+e);
		}

		// cleanup, free resources
		finally {
			bw.close();
			fos.close();			
		}		
	}
	
	public static int update(int score, String player_name) throws IOException {
		ArrayList<String> scores = getHighScores();
		int rank = -1;

		for(int i = 0; i < scores.size(); i++) {
			String record = scores.get(i);
			int curr_score = Integer.parseInt(record.substring(record.lastIndexOf('\t')+1));
			if(curr_score < score) {
				scores.add(i, (i+1)+"\t\t"+player_name+"\t\t"+score);
				scores.remove(scores.size()-1);
				rank = i+1;
				break;
			}
		}

		setHighScores(scores);
		return rank;
	}

	public static void show() throws IOException{

		ArrayList<String> scores = getHighScores();

		for(String score: scores) {
			System.out.println(score);
		}
	
	}
}
