package hu.unideb.inf.bead.fogadoiroda.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import hu.unideb.inf.bead.fogadoiroda.Main;
import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {
	
    Main main;
    private Felhasznalo felhasznalo = new Felhasznalo();
	private boolean sikeres = false;
	@FXML
	private Label hiba;
	@FXML
	private Button belepbtn;
	@FXML
	private Button regbtn;
	@FXML
	private TextField felhnev;
	@FXML
	private PasswordField jelszo;
	
	
	public void setMain(Main main) {
		this.main = main;
	}
	@FXML
	private void belepes(ActionEvent event) throws IOException{
		for (int i = 0; i < main.getFelhasznalok().size(); i++) {	
			if (main.getFelhasznalok().get(i).getFelhnev().equals(felhnev.getText()) && main.getFelhasznalok().get(i).getJelszo().equals(jelszo.getText())){
				felhasznalo=main.getFelhasznalok().get(i);
				sikeres=true;
			}
		}
    if(sikeres){	
    	
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        try {
        	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.close();
        
            AnchorPane mainview = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle("B.G. Fogadóiroda");
            stage.initModality(Modality.WINDOW_MODAL);	
            Scene scene = new Scene(mainview);
            stage.setScene(scene);
            MainViewController mc = loader.getController();
            mc.setFelhasznalo(felhasznalo,main.getFelhasznalok());
            mc.setStage(stage);
            stage.show();		
            
	} catch(Exception e) {
		e.printStackTrace();
	}
		}else{
			felhnev.clear();
			jelszo.clear();
		    hiba.setText("sikertelen belepes");	  
	    }	
	}
	
	@FXML
	public void regisztracio(ActionEvent event){
		Felhasznalo felhasznalo = new Felhasznalo();
		main.createRegWindow(felhasznalo);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	felhnev.setText("alap");
    	jelszo.setText("alap");		
	}	
}

