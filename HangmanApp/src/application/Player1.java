package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Player1 {

	char[] word = { 'j', 'o', 'k', 'e', 'r' };
	ServerSocket server;
	Socket playerSocket;
	Scanner scan;

	public Player1() throws IOException {

		String input;
		scan = new Scanner(System.in);

		server = new ServerSocket(3005);
		System.out.println("Connecting to Player 2....");

		playerSocket = server.accept();

		BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream()), true);

		input = in.readLine();
		System.out.println("Player 2: " + input);

		out.println("Hello player");
		System.out.println("Player 1: Hello player");

		out.println("Welcome to the game! You have 5 attempts to guess the word.\n");
		System.out.println("Player 1: Welcome to the game! You have 5 attempts to guess the word.");
		out.println("The word has 5 letters and for each incorrect attempt, you will lose 1 life.Good luck!");
		System.out.println(
				"Player 1: The word has 5 letters and for each incorrect attempt, you will lose 1 life.Good luck!");

		int count = 0;
		int total = 0;

		char[] combo = { '_', '_', '_', '_', '_' };

		do {

			out.println(combo);
			System.out.println(combo); // word and guessed letters
			out.println("Attempts left: " + (5 - count));
			System.out.println("Player 1: Attempts left " + (5 - count));
			char guess = scan.next().charAt(0);
			
			for (int i = 0; i < word.length; i++) {
				
				if (guess == word[i]) {

					combo[i] = word[i];
					System.out.println("Player 1: Correct!");
					out.println("Correct!");
					System.out.println("Player 1: Letter " + guess + " is in position " + (i + 1) + " in the word");
					out.println("Letter " + guess + " is in position " + (i + 1) + " in the word");
					combo[i] = guess;
					total++;
					break;

				} // end if
				
				else {
					
					System.out.println("Player 1: Incorrect. - 1 attempt");
					out.println("Incorrect. - 1 attempt");
					count++;
					break;
					
				}
			} // end for
				
		} while (count < 5); // end do-while
		
		char again;
		
		if (total == 5) {
			
			System.out.println(
					"Player 1: Congratulations! You have guessed the word " + word.toString() + "\nBye");
			out.println("Congratulations! You have guessed the word " + word.toString() + "\nBye");
			// end the game
		} // end if
		else {
			
			System.out.println(
					"Player 1: You didn't guess the word! \n\nLoser!\n\n Do you want to play again? (y/n)");
			out.println("You didn't guess the word! \n\nLoser!\n\n Do you want to play again? (y/n)");

			if ((again = scan.next().charAt(0)) == 'y') {

				new Player1();
				
			} else {

				System.out.println("Player 1: BYE");
				out.println("BYE");
			} // end else
			
	} // end else

		server.close();
		playerSocket.close();
		in.close();
		out.close();
	}

	public static void main(String[] args) {

		try {
			new Player1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
