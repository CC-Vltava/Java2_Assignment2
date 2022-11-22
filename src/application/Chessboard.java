package application;

public class Chessboard {
	private int[][] chessboard = new int[3][3];
	private int status = -1;// -1 waiting, 0 ing, 1 P1 win, 2 P2 win
	private int turn = 1;
	private int step = 0;
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public void setChessboard(int len) {
		int x = len / 3;
		int y = len % 3;
		setChessboard(x, y);
	}
	
	public void setChessboard(int x, int y) {
		chessboard[x][y] = turn;
	}
	
	public void changeTurn() {
		turn = turn == 1 ? 2 : 1;
		step += 1;
	}
	
	public int getStep(){
		return step;
	}
	
	public int[][] getChessboard() {
		return chessboard;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public int checkWin() {
		int F = -1;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				if(chessboard[i][j] == 0)
					F = 0;
			}
		for (int i = 0; i < 3; i++) {
			int now = chessboard[i][0], check = 1;
			if (now == 0)
				continue;
			for (int j = 0; j < 3; j++) {
				if (now != chessboard[i][j]) {
					check = 0;
					break;
				}
			}
			if (check == 1) {
				F = now;
				break;
			}
		}
		for (int j = 0; j < 3; j++) {
			int now = chessboard[0][j], check = 1;
			if (now == 0)
				continue;
			for (int i = 0; i < 3; i++) {
				if (now != chessboard[i][j]) {
					check = 0;
					break;
				}
			}
			if (check == 1) {
				F = now;
				break;
			}
		}
		int now = chessboard[0][0];
		if (now != 0) {
			if (chessboard[1][1] == now && chessboard[2][2] == now) {
				F = now;
			}
		}
		now = chessboard[2][0];
		if (now != 0) {
			if (chessboard[1][1] == now && chessboard[0][2] == now) {
				F = now;
			}
		}
		return F;
	}
}
