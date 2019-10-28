import java.io.*;
import java.util.*;

public class ReadFiles {

	public static ArrayList<String> getQuizNames(String file_base, String topics) throws IOException {
		String path = topics;
		if(file_base.equals("") != true) path = file_base+"/"+topics;

		System.out.println("File Path: "+ path);

		// important declarations
		ArrayList<String> quiz = new ArrayList<String>();

		File file = null;
		FileInputStream fis = null;
		BufferedReader br = null;

		try {
			file = new File(path); 						// creating new file object
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

	public static void main(String[] args) throws IOException{

		if(args.length < 1) {
			System.out.println("Usage: Provide File name.(And file-base. Optional)");
		}else {
			String filename = args[0];
			String filebase = "";
			if(args.length == 2) filebase = args[1];
			ArrayList<String> quizes = getQuizNames(filebase, filename);

			int count = 0;
			for(String quiz: quizes) {
				count++;
				System.out.println("Line-"+count+"\t"+quiz.substring(quiz.indexOf('\t')+1));
			}
		}
	}
}
