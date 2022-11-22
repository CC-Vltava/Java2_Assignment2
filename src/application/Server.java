package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Server {

//	final static int SBAP_PORT = 8888;
	public static ServerSocket server;
	
	static {
		try {
			server = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		final int ACCOUNTS_LENGTH = 10;
		Chessboard chessboard = new Chessboard();
		System.out.println("Waiting for clients to connect...");
		
		Socket s1 = server.accept();
		System.out.println("Client connected.");
		Scanner in1 = new Scanner(s1.getInputStream());
		PrintWriter out1 = new PrintWriter(s1.getOutputStream());
		out1.println("wait");
		out1.flush();
		
		Socket s2 = server.accept();
		System.out.println("Client connected.");
		Scanner in2 = new Scanner(s2.getInputStream());
		PrintWriter out2 = new PrintWriter(s2.getOutputStream());
		out2.println("ok");
		out2.flush();
		System.out.println("Connect to 2 clients");
		

		
		out1.println("start");
		out1.flush();
		out2.println("start");
		out2.flush();
		
		System.out.println("Send start message to client");
		
		// 到这里，完成了两个Client到server的连接
		
		chessboard.setStatus(0);
		
		Thread thread = new Thread(new Fight());
		thread.start();
		System.out.println("Fighting Start (create layer 1)");
		
		while(chessboard.getStatus() == 0){
			int F = 1;
			try{
				s1.sendUrgentData(0xFF);
			}catch(Exception ex){
				F = 0;
				System.out.println("P1 Disconnected");
				out2.println("Opponent_Disconnected");
				out2.flush();
			}
			try{
				s2.sendUrgentData(0xFF);
			}catch(Exception ex){
				F = 0;
				System.out.println("P2 Disconnected");
				out1.println("Opponent_Disconnected");
				out1.flush();
			}
			if(F == 0){
				Thread.sleep(100);
				return;
			}
		}
		
	}
}
