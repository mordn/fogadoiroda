package hu.unideb.inf.bead.fogadoiroda.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegViewController implements Initializable {

	private Stage stage;
	private Felhasznalo felh;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setFelhasznalo(Felhasznalo felhasznalo){
		this.felh=felhasznalo;
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

		stage.close();
	}	
	
	@FXML
	private void okAction(ActionEvent event) throws IOException{
		if (!jelszo1.getText().equals(jelszo2.getText()) || jelszo1.getText().equals("")){
			label.setText("a két jelszónak meg kell egyeznie");
			jelszo1.clear();
			jelszo2.clear();
        
		}else{
			felh.setFelhnev(felhnev.getText());
			felh.setJelszo(jelszo1.getText());
			felh.setKnev(knev.getText());
			felh.setVnev(vnev.getText());
			felh.setEgyenleg(Double.valueOf(egyenleg.getText()));
						
		stage.close();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}	
}
