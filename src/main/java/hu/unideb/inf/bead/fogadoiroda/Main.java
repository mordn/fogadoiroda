package hu.unideb.inf.bead.fogadoiroda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
	private Xml filekezel;
	
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
		filekezel = new Xml();
		try {
			filekezel.init();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		felhasznalok=filekezel.beolvas(felhasznalok);
		System.out.println("Felhasználók: ");
		felhasznalok.forEach(System.out::println);
	}	
	public Main(){
		//felhasznalok.add(new Felhasznalo("alap","alap","Jobbágy","Koppány",5000.0));
	//	filekezel = new Xml();
	//	filekezel.beolvas(felhasznalok);
	/*	try {
			filekezel.init();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}*/
		
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
	            felhasznalok.add(felhasznalo);
	            filekezel.filebair(felhasznalok);
	    		felhasznalok.stream().forEach(System.out::println);
	    		
	            
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
