package application;

import application.controller.Controller;
import javafx.application.Platform;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable {
	public Controller controller;
	public Socket socket;
	public Scanner in;
	public PrintWriter out;
	
	public Player(Controller controller) {
		this.controller = controller;
		try {
			socket = new Socket("localhost", 8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Player PID is " + Thread.currentThread().getId());
		System.out.println("Socket port is " + socket.getLocalPort());
		while(true){
			while(!in.hasNextInt());
			int len = in.nextInt();
			if(len == -1){
				System.out.println("It is your turn");
				continue;
			}
			int x = len / 3, y = len % 3;
			System.out.println("receive");
			if(len > 10){
				if(len == 11){
					System.out.println("You Win!");
				}
				if (len == 12) {
					System.out.println("You Lose!");
				}
				if (len == 13) {
					System.out.println("Tie!");
				}
				System.exit(0);
			}
			System.out.println("It is your turn");
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.refreshBoard(x, y);
					//更新JavaFX的主线程的代码放在此处
				}
			});
			
		}
	}
}
