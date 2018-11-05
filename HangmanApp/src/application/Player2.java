package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Player2 {
	
	Scanner scan = new Scanner(System.in);
	char [] guess;

public Player2() throws UnknownHostException, IOException {
	
	Socket socket = new Socket("localhost", 3005);
	System.out.println("Connected to Player 1");
	
	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	
	out.println("Hello server");
	System.out.println("Player 2: server");
	String input = in.readLine();
	System.out.println("Player 1: " + input);
	out.println(input);
	
	while (input != null) {
		
		input = in.readLine();
		System.out.println("Player 1: " + input);
		
	}
	
	socket.close();
	out.close();

	}// end player 2
	
	public static void main (String[] args) throws UnknownHostException, IOException {
		
		new Player2();
	}
}
