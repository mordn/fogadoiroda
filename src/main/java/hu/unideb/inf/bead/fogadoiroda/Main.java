package hu.unideb.inf.bead.fogadoiroda;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;
import hu.unideb.inf.bead.fogadoiroda.view.LoginViewController;
import hu.unideb.inf.bead.fogadoiroda.view.RegViewController;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	public List<Felhasznalo> felhasznalok = new ArrayList<Felhasznalo>();
	
	public Stage primaryStage;
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("B.G. Fogadóiroda");
		primaryStage.setResizable(false);
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		try {
			AnchorPane logview = (AnchorPane) loader.load();
			Scene scene = new Scene(logview);
			primaryStage.setScene(scene);
			primaryStage.show();	
			LoginViewController controller = loader.getController();
			controller.setMain(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public Main(){
		felhasznalok.add(new Felhasznalo("alap","alap","Jobbágy","Koppány",5000.0));
	}
	
	public void createRegWindow(Felhasznalo felhasznalo){
		
			System.out.println("regisztracio");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RegView.fxml"));
			try {
				AnchorPane regview = (AnchorPane) loader.load();
				Stage stage = new Stage();
				stage.setTitle("Regisztráció");
				stage.initModality(Modality.WINDOW_MODAL);	
				stage.initOwner(primaryStage);
				
				Scene scene = new Scene(regview);
	            stage.setScene(scene);
	        
	            RegViewController rgw = loader.getController();
	            rgw.setFelhasznalo(felhasznalo);
	            rgw.setStage(stage);
	            stage.showAndWait();
	            		
			} catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
	public static void main(String[] args) {
		launch(args);
	}

	public List<Felhasznalo> getFelhasznalok() {
		return felhasznalok;
	}
	
}
