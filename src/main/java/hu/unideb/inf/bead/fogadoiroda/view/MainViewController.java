package hu.unideb.inf.bead.fogadoiroda.view;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.ResourceBundle;

import hu.unideb.inf.bead.fogadoiroda.model.Aktualis;
import hu.unideb.inf.bead.fogadoiroda.model.Csapat;
import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
		
	private Stage stage;
	public void setFelhasznalo(Felhasznalo felhasznalo){
		  felhnev.setText(felhasznalo.getFelhnev());
	      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
	}
	
	
	@FXML
	private Label fordulolabel;
	
	@FXML
	private TableView<Csapat> csapatTable;
	@FXML
	private TableView<Aktualis> aktualisTable;
	@FXML
	private TableColumn<Aktualis, String> hazaiOszlop;
	@FXML
	private TableColumn<Aktualis, String> vendegOszlop;
	@FXML
	private TableColumn<Aktualis, String> eredmenyOszlop;

	@FXML
	private TableColumn<Csapat, String> nevOszlop;

	@FXML
	private TableColumn<Csapat, Integer> pontOszlop;
		
	@FXML
	private void bezar(ActionEvent event) throws IOException{
		stage.close();
	}	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
    private int hazaiid,vendegid,hazaigol,vendeggol;
    private String eredmeny;

    Csapat hazai = new Csapat();
    Csapat vendeg = new Csapat();
    private int sorrend[]={70,24,69,13,58,87,92,36,45,1,74,18,30,62,59,41,83,25,6,97,19,8,65,72,34,40,86,21,93,57,15,84,9,32,67,71,46,20,98,53,49,5,37,82,61};
	
	@FXML
	private Label felhnev;
	@FXML
	private Label egyenleg;
	
	@FXML
	private Button kov;
	@FXML
	private Button lejatsz;

	
    private ObservableList<Csapat> csapatok = FXCollections.observableArrayList();
    private ObservableList<Aktualis> aktcsapatok = FXCollections.observableArrayList();
    
	@FXML	
    private void lejatszas(ActionEvent event) throws IOException{
      lejatsz.setDisable(true);
	  Random r = new Random();
	  aktcsapatok.clear();
	  
		for (int j=0;j<5;j++){
			hazaiid=sorrend[fordulo+j]/10;
			vendegid=sorrend[fordulo+j]%10;
			
			hazaigol=r.nextInt(5);
			vendeggol=r.nextInt(5);
			eredmeny=String.valueOf(hazaigol)+":"+String.valueOf(vendeggol);
			
			hazai=csapatok.stream().filter(t->t.getId()==hazaiid).findFirst().get();
			vendeg=csapatok.stream().filter(t->t.getId()==vendegid).findFirst().get();
            aktcsapatok.add(new Aktualis(hazai.getNev(),vendeg.getNev(),hazaigol,vendeggol,eredmeny));
            
            if (hazaigol>vendeggol) hazainyert(hazaigol,vendeggol,hazai,vendeg);
            else if (hazaigol<vendeggol) vendegnyert(hazaigol,vendeggol,hazai,vendeg);
            else dontetlen(hazaigol,vendeggol,hazai,vendeg);
            
            
            
		}  

	    Collections.sort(csapatok, new Comparator<Csapat>() {

	        public int compare(Csapat o1, Csapat o2) {
	            return o2.getPontszam()-o1.getPontszam();
	        }
	    });
		
		
        csapatTable.getColumns().get(0).setVisible(false);
        csapatTable.getColumns().get(0).setVisible(true);
		
		aktualisTable.setItems(aktcsapatok);
	  
	  
	  
   /*   aktcsapatok.forEach(t->t.setHazaigol(r.nextInt(5)));
      aktcsapatok.forEach(t->t.setVendeggol(r.nextInt(5)));
      aktcsapatok.forEach(t->t.setEredmeny(String.valueOf(t.getHazaigol())+":"+String.valueOf(t.getVendeggol())));
      aktcsapatok.stream().forEach(System.out::println);*/
      
      
      
	 if (fordulo<40)
		  kov.setDisable(false);
	  
    }
	
	private void hazainyert(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev()==hazai.getNev()) t.setPontszam(t.getPontszam()+3);});
	}
	private void dontetlen(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev()==hazai.getNev()) t.setPontszam(t.getPontszam()+1);});
        csapatok.forEach(t->{ if (t.getNev()==vendeg.getNev()) t.setPontszam(t.getPontszam()+1);});
	}
	private void vendegnyert(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev()==vendeg.getNev()) t.setPontszam(t.getPontszam()+1);});
	}
	
	@FXML	
    private void kovefordulo(ActionEvent event) throws IOException{
		lejatsz.setDisable(false);
		kov.setDisable(true);
		fordulo+=5;
		fordulolabel.setText(String.valueOf(fordulo/5+1));
		sorsolas();

    }
	private int fordulo=0;
	
	private void sorsolas(){
		 			
					aktcsapatok.clear();
					for (int j=0;j<5;j++){
						hazaiid=sorrend[fordulo+j]/10;
						vendegid=sorrend[fordulo+j]%10;
						
						hazai=csapatok.stream().filter(t->t.getId()==hazaiid).findFirst().get();
						vendeg=csapatok.stream().filter(t->t.getId()==vendegid).findFirst().get();
		                aktcsapatok.add(new Aktualis(hazai.getNev(),vendeg.getNev()));
		                
					}  
					aktualisTable.setItems(aktcsapatok);
					
				
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        kov.setDisable(true);
        fordulolabel.setText(String.valueOf(fordulo/5+1));
        
        csapatok.add(new Csapat(0,"Arsenal",0,1));
    	csapatok.add(new Csapat(1,"Liverpool",0,1));
    	csapatok.add(new Csapat(2,"Tottenham",0,1));
    	csapatok.add(new Csapat(3,"Newcastle",0,1));
    	csapatok.add(new Csapat(4,"Leichester City",0,1));
    	csapatok.add(new Csapat(5,"Chelsea",0,1));
    	csapatok.add(new Csapat(6,"Manchester City",0,1));
    	csapatok.add(new Csapat(7,"Manchester United",0,1));
    	csapatok.add(new Csapat(8,"Everton",0,1));
    	csapatok.add(new Csapat(9,"Southampton",0,1));
    	
		nevOszlop.setCellValueFactory(new PropertyValueFactory<Csapat, String>("nev"));
		pontOszlop.setCellValueFactory(new PropertyValueFactory<Csapat, Integer>("pontszam"));
		
		hazaiOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("hazai"));
		vendegOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("vendeg"));
		eredmenyOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("eredmeny"));
	
		sorsolas();

		csapatTable.setItems(csapatok);
		aktualisTable.setItems(aktcsapatok);	
	}
	
}
