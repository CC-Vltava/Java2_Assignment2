package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
	public static void main(String[] args) throws IOException {
		final int SBAP_PORT = 8888;
		try (Socket s = new Socket("localhost", SBAP_PORT)) {
			InputStream instream = s.getInputStream();
			OutputStream outstream = s.getOutputStream();
			Scanner in = new Scanner(instream);
			PrintWriter out = new PrintWriter(outstream);
			System.out.println("Client1 connected");
			while (!in.hasNext()) ;
			String command = in.next();
			if (command.equals("wait")) {
				System.out.println("Waiting for other player");
			}
			while (!in.hasNext()) ;
			command = in.next();
			if (command.equals("start")) {
				System.out.println("Start Game");
			}
			
			Thread thread = new Thread(new Launch());
			thread.setDaemon(true);
			thread.start();
			
			// 下面是判断服务器是否连接
			int F = 1;
			while (!in.hasNext()){
				try{
					s.sendUrgentData(0xFF);
				}catch(Exception ex){
					F = 0;
					break;
				}
			}
			if(F == 0){
				System.out.println("Server ERROR");
			}
			else {
				command = in.next();
				System.out.println(command);
			}
		}
	}
	
	
}
