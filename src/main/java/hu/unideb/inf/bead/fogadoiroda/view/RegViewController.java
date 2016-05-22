package hu.unideb.inf.bead.fogadoiroda.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegViewController implements Initializable {

	
	private Stage stage;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private TextField felhnev;
	@FXML
	private TextField vnev;
	@FXML
	private TextField knev;
	@FXML
	private TextField egyenleg;
	@FXML
	private PasswordField jelszo1;
	@FXML
	private PasswordField jelszo2;
	@FXML
    private Label label;

	
	
	@FXML
	private void cancelAction(ActionEvent event) throws IOException{
		System.out.println("mégse");
		stage.close();
	}	
	
	@FXML
	private void okAction(ActionEvent event) throws IOException{
		System.out.println("jelszó1: "+jelszo1.getText());
		System.out.println("jelszó2: "+jelszo2.getText());
		
		
		if (!jelszo1.getText().equals(jelszo2.getText())){
			label.setText("a két jelszónak meg kell egyeznie");
			jelszo1.clear();
			jelszo2.clear();
			
		}else{
		stage.close();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}	
	
	
}
