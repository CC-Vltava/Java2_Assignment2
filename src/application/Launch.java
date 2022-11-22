package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launch extends Application implements Runnable {
	@Override
	public void run() {
		System.out.println("Launch PID is " + Thread.currentThread().getId());
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
			Pane root = fxmlLoader.load();
			primaryStage.setTitle("Tic Tac Toe");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
