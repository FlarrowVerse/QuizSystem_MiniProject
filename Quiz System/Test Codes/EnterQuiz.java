import java.io.*;

public class EnterQuiz {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		System.out.println("********************************************QUIZ SYSTEM**************************************************************");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

		while(true) {

			System.out.println("MAIN MENU:");
			System.out.println("\t1. Play Quiz");
			System.out.println("\t2. High Scores");
			System.out.println("\t3. Quit");
			System.out.print("What do you want to do?(1/2/3): ");
			int choice = Integer.parseInt(br.readLine());

			switch(choice) {
			case 1: System.out.print("Enter Player Name: ");
				String player = br.readLine();
				int score = QuizMaster.play();
				System.out.println("Your Final Score:: "+score);
				int position = Records.update(score, player);
				if(position >= 1 && position <= 10) System.out.println("Congratulations! You are in "+position+"-th postion.");
				break;
			case 2: Records.show();	break;
			case 3: System.out.println("Exiting Quiz Arena........."); return;
			default: System.out.println("No such option. Please try again...."); break;
			}
		}
		
	}
}
