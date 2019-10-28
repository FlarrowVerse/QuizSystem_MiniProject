import java.io.*;
import java.util.*;

public class WriteFiles {

	public static void giveQuizNames(String file_base, String topics, ArrayList<String> quizes) throws IOException {
		String path = topics;
		if(!file_base.equals("")) path = file_base+"/"+topics;

		System.out.println("File Path: "+ path);

		File dir = new File(file_base);
		if(!dir.exists()) {
			if(dir.mkdirs()) System.out.println("Created "+file_base+" succesfully.");
			else System.out.println("Could not create "+file_base+" succesfully.");
		}

		// important declarations
		
		File file = null;
		FileOutputStream fos = null;
		BufferedWriter bw = null;

		try {
			file = new File(path); 						// creating new file object
			fos = new FileOutputStream(file);	// opening specified file for reading as an input stream
			bw = new BufferedWriter(new OutputStreamWriter(fos));		// reading using BufferedReader

			int line_no = 0;

			for(String quiz: quizes) {
				line_no++;
				bw.write("Line-"+line_no+"\t"+quiz+"\n");									// adding the name to the list of quizes
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

	public static void main(String[] args) throws IOException{

		if(args.length < 3) {
			System.out.println("Usage: Provide File name.(And file-base. Optional)");
		}else {
			String src_filename = args[0], dest_filename = args[1];
			String src_filebase = "Quizes/", dest_filebase = "Solutions/"+args[2];
			ArrayList<String> quizes = ReadFiles.getQuizNames(src_filebase, src_filename);

			int count = 0;
			for(String quiz: quizes) {
				count++;
				System.out.println("Line-"+count+"\t"+quiz.substring(quiz.indexOf('\t')+1));
			}

			giveQuizNames(dest_filebase, dest_filename, quizes);
		}
	}
}
