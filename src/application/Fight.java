package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Fight implements Runnable {
	Socket p1, p2;
	Scanner in1, in2;
	PrintWriter out1, out2;
	Chessboard chessboard = new Chessboard();
	
	Fight() throws IOException {
		chessboard.setStatus(0);
		p1 = Server.server.accept();
		System.out.println("P1 has connected, the port is " + p1.getPort());
		
		p2 = Server.server.accept();
		System.out.println("P2 has connected, the port is " + p2.getPort());
		
		System.out.println("Layer 1 Success");
		
		in1 = new Scanner(p1.getInputStream());
		out1 = new PrintWriter(p1.getOutputStream());
		
		in2 = new Scanner(p2.getInputStream());
		out2 = new PrintWriter(p2.getOutputStream());
	}
	
	@Override
	public void run() {
		Send send1 = new Send(in1, out2, chessboard, out1);
		Send send2 = new Send(in2, out1, chessboard, out2);
		int previousTurn = chessboard.getTurn();
		Thread thread1 = new Thread(send1);
		thread1.start();
		Thread thread2 = new Thread(send2);
		thread2.start();
		out1.println(-1);
		out1.flush();
	}
}
