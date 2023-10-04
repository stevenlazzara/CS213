package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;	
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

public class Main extends Application{
	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Primary.fxml.fxml"));
			Scene primaryScene = new Scene(root);
			primaryStage.setScene(primaryScene);
			primaryStage.setTitle("My Pizza Store");
			primaryStage.show();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
