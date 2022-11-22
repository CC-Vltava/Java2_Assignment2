package application;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Send implements Runnable{
	Scanner in;
	PrintWriter out, oppo;
	Chessboard chessboard;
	Send(Scanner in, PrintWriter out, Chessboard chessboard, PrintWriter oppo){
		this.in = in;
		this.out = out;
		this.chessboard = chessboard;
		this.oppo = oppo;
	}
	@Override
	public void run() {
		while(true){
			while(!in.hasNextInt());
			int len = in.nextInt();
			chessboard.setChessboard(len);
			System.out.println(Arrays.deepToString(chessboard.getChessboard()));
			chessboard.changeTurn();
			out.println(len);
			out.flush();
			int win = chessboard.checkWin();
			// message 11 win, 12 lose, 13 tie
			if(win != 0){
				if(win == -1){ // tie
					out.println(13);
					out.flush();
					oppo.println(13);
					oppo.flush();
				}
				else{
					out.println(12);
					out.flush();
					oppo.println(11);
					oppo.flush();
				}
				System.out.println("Game Over!");
				if(win == -1){
					System.out.println("Tie!");
				}
				else{
					System.out.println("Player" + win + " Win!");
				}
				System.exit(0);
//				System.out.println(win + " Win!");
			}
			System.out.println("Receive len from client" + chessboard.getTurn() + ", send to other client, len is " + len);
			System.out.println("Next is Player" + chessboard.getTurn());
		}
	}
}
