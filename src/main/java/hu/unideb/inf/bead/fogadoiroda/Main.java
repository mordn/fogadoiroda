package hu.unideb.inf.bead.fogadoiroda;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public Stage primaryStage;
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("B.G. Fogadóiroda");
		primaryStage.setResizable(false);
	
		createLoginView();
	}	
	
	private void createLoginView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		try {
			AnchorPane logview = (AnchorPane) loader.load();
			Scene scene = new Scene(logview);
			primaryStage.setScene(scene);
			primaryStage.show();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
		
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
