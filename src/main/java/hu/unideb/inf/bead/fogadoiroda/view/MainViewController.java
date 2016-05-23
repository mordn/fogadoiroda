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
import hu.unideb.inf.bead.fogadoiroda.model.Fogadas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
	private Felhasznalo felhasznalo;	
	private Stage stage;
    private int hazaiid,vendegid,hazaigol,vendeggol,fordulo;
    private String eredmeny;
    private Csapat hazai = new Csapat();
    private Csapat vendeg = new Csapat();
    private Fogadas fogadasok = new Fogadas();
    private int sorrend[]={70,24,69,13,58,87,92,36,45,1,74,18,30,62,59,41,83,25,6,97,19,8,65,72,34,40,86,21,93,57,15,84,9,32,67,71,46,20,98,53,49,5,37,82,61};
    private ObservableList<Csapat> csapatok = FXCollections.observableArrayList();
    private ObservableList<Aktualis> aktcsapatok = FXCollections.observableArrayList();
    
	public void setFelhasznalo(Felhasznalo felhasznalo){
		  felhnev.setText(felhasznalo.getFelhnev());
	      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
	      this.felhasznalo=felhasznalo;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private TableView<Csapat> csapatTable;
	@FXML
	private TableView<Aktualis> aktualisTable;
	
	@FXML
	private TableColumn<Aktualis, Double> hoddsOszlop;
	@FXML
	private TableColumn<Aktualis, String> hazaiOszlop;
	@FXML
	private TableColumn<Aktualis, String> vendegOszlop;
	@FXML
	private TableColumn<Aktualis, Double> voddsOszlop;
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
	
	@FXML
	private Label fordulolabel;
	@FXML
	private Label fogadasihiba;
	@FXML
	private Label felhnev;
	@FXML
	private Label egyenleg;
	
	@FXML
	private Button kov;
	@FXML
	private Button lejatsz;
	@FXML private TextField h1;
	@FXML private TextField h2;
	@FXML private TextField h3;
	@FXML private TextField h4;
	@FXML private TextField h5;
	@FXML private TextField v1;
	@FXML private TextField v2;
	@FXML private TextField v3;
	@FXML private TextField v4;
	@FXML private TextField v5;
	@FXML private Button bh1;
	@FXML private Button bh2;
	@FXML private Button bh3;
	@FXML private Button bh4;
	@FXML private Button bh5;
	@FXML private Button bv1;
	@FXML private Button bv2;
	@FXML private Button bv3;
	@FXML private Button bv4;
	@FXML private Button bv5;
	@FXML	
    private void lejatszas(ActionEvent event) throws IOException{
      lejatsz.setDisable(true);
	  Random r = new Random();	  
		for (int j=0;j<5;j++){
			hazaiid=sorrend[fordulo+j]/10;
			vendegid=sorrend[fordulo+j]%10;
			
			hazaigol=r.nextInt(5);
			vendeggol=r.nextInt(5);
			eredmeny=String.valueOf(hazaigol)+":"+String.valueOf(vendeggol);
			
			hazai=csapatok.stream().filter(t->t.getId()==hazaiid).findFirst().get();
			vendeg=csapatok.stream().filter(t->t.getId()==vendegid).findFirst().get();
            aktcsapatok.forEach(t->{
            	                    if(t.getHazai().equals(hazai.getNev())){
            						t.setHazaigol(hazaigol);
                                    t.setEredmeny(eredmeny);
                                    t.setVendeggol(vendeggol);}
            	                    
            });  
            if (hazaigol>vendeggol) hazainyert(hazaigol,vendeggol,hazai,vendeg);
            else if (hazaigol<vendeggol) vendegnyert(hazaigol,vendeggol,hazai,vendeg);
            else dontetlen(hazaigol,vendeggol,hazai,vendeg);           
		}
	    Collections.sort(csapatok, new Comparator<Csapat>() {
	        public int compare(Csapat o1, Csapat o2) {
	            return o2.getPontszam()-o1.getPontszam();
	        }
	    });
        aktualisTable.getColumns().get(0).setVisible(false);
        aktualisTable.getColumns().get(0).setVisible(true);
		
        csapatTable.getColumns().get(0).setVisible(false);
        csapatTable.getColumns().get(0).setVisible(true);
	    if (fordulo<40)
		   kov.setDisable(false);
	    fogadastlejatsz(); 
	    fogadastnullaz();
	    fogadasigombokletilt();
	    
    }
	
	private void hazainyert(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev().equals(hazai.getNev())) t.setPontszam(t.getPontszam()+3);});
	}
	private void dontetlen(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev().equals(hazai.getNev())) t.setPontszam(t.getPontszam()+1);});
        csapatok.forEach(t->{ if (t.getNev().equals(vendeg.getNev())) t.setPontszam(t.getPontszam()+1);});
	}
	private void vendegnyert(int hazaigol,int vendeggol,Csapat hazai,Csapat vendég){
		csapatok.forEach(t->{ if (t.getNev().equals(vendeg.getNev())) t.setPontszam(t.getPontszam()+3);});
	}
	
	@FXML	
    private void kovefordulo(ActionEvent event) throws IOException{
		lejatsz.setDisable(false);
		kov.setDisable(true);
		fordulo+=5;
		fordulolabel.setText(String.valueOf(fordulo/5+1));
		sorsolas();
		fogadasigombokengedelyez();
		
    }
	private void sorsolas(){
	    Random r = new Random();				
		aktcsapatok.clear();
		for (int j=0;j<5;j++){
			hazaiid=sorrend[fordulo+j]/10;
			vendegid=sorrend[fordulo+j]%10;
			hazai=csapatok.stream().filter(t->t.getId()==hazaiid).findFirst().get();
			vendeg=csapatok.stream().filter(t->t.getId()==vendegid).findFirst().get();
            aktcsapatok.add(new Aktualis(hazai.getNev(), vendeg.getNev(), (double)(r.nextInt(185)+115)/100, (double)(r.nextInt(185)+115)/100));     
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
		
		hoddsOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, Double>("hazaiodds"));
		hazaiOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("hazai"));
		vendegOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("vendeg"));
		voddsOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, Double>("vendegodds"));
		eredmenyOszlop.setCellValueFactory(new PropertyValueFactory<Aktualis, String>("eredmeny"));
	
		sorsolas();

		csapatTable.setItems(csapatok);
		aktualisTable.setItems(aktcsapatok);	
	}
	@FXML	
    private void h1fogad(ActionEvent event){ 
	    try{			
	    	if (Double.parseDouble(h1.getText())<=felhasznalo.getEgyenleg()){
	       fogadasihiba.setText("");	
           fogadasok.setH1(fogadasok.getH1()+Double.parseDouble(h1.getText()));  
           felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(h1.getText()));
		    }else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  h1.clear();
		    }
        }catch(Exception e){
		   fogadasihiba.setText("Oda számot kell írni!!!"); 
	    }
	   
        egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
        h1.clear();

    }
	@FXML	
    private void h2fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(h2.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setH2(fogadasok.getH2()+Double.parseDouble(h2.getText())); 
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(h2.getText()));
		}else{
	    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!");
	    	  h2.clear();
	    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			 
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      h2.clear();
		
	}
	@FXML	
    private void h3fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(h3.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setH3(fogadasok.getH3()+Double.parseDouble(h3.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(h3.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  h3.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			  
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      h3.clear();
	}
	@FXML	
    private void h4fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(h4.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setH4(fogadasok.getH4()+Double.parseDouble(h4.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(h4.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  h4.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			 
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      h4.clear();	
	}
	@FXML	
    private void h5fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(h5.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setH5(fogadasok.getH5()+Double.parseDouble(h5.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(h5.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  h5.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  } 
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      h5.clear();
	}
	@FXML	
    private void v1fogad(ActionEvent event) throws IOException{
		try{
			if (Double.parseDouble(v1.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setV1(fogadasok.getV1()+Double.parseDouble(v1.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(v1.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  v1.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      v1.clear();
	}
	@FXML	
    private void v2fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(v2.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setV2(fogadasok.getV2()+Double.parseDouble(v2.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(v2.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  v2.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			  
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      v2.clear();	
	}
	@FXML	
    private void v3fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(v3.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setV3(fogadasok.getV3()+Double.parseDouble(v3.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(v3.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  v3.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			  
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      v3.clear();
	}
	@FXML	
    private void v4fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(v4.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setV4(fogadasok.getV4()+Double.parseDouble(v4.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(v4.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  v4.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			  
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      v4.clear();
	}
	@FXML	
    private void v5fogad(ActionEvent event) throws IOException{	
		try{
			if (Double.parseDouble(v5.getText())<=felhasznalo.getEgyenleg()){
			  fogadasihiba.setText("");	
		      fogadasok.setV5(fogadasok.getV5()+Double.parseDouble(v5.getText()));  
		      felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()-Double.parseDouble(v5.getText()));
			}else{
		    	  fogadasihiba.setText("Ekkora értékű fogadáshoz nincsen fedezete!!!"); 
		    	  v5.clear();
		    }
			  }catch(Exception e){
				  fogadasihiba.setText("Oda számot kell írni!!!"); 
			  }
			  
		      egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");
		      v5.clear();
	}
	
	private void fogadastnullaz(){
		fogadasok.setH1(0.0);
		fogadasok.setH2(0.0);
		fogadasok.setH3(0.0);
		fogadasok.setH4(0.0);
		fogadasok.setH5(0.0);
		fogadasok.setV1(0.0);
		fogadasok.setV2(0.0);
		fogadasok.setV3(0.0);
		fogadasok.setV4(0.0);
		fogadasok.setV5(0.0);
	}
	
	private void fogadastlejatsz(){
		if (fogadasok.getH1()>0 && (aktcsapatok.get(0).getHazaigol()>aktcsapatok.get(0).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getH1()*aktcsapatok.get(0).getHazaiodds());
		}
		else if(fogadasok.getV1()>0 &&  (aktcsapatok.get(0).getHazaigol()<aktcsapatok.get(0).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getV1()*aktcsapatok.get(0).getHazaiodds());
		}		
		if (fogadasok.getH2()>0 && (aktcsapatok.get(1).getHazaigol()>aktcsapatok.get(1).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getH2()*aktcsapatok.get(1).getHazaiodds());
		}
		else if(fogadasok.getV2()>0 &&  (aktcsapatok.get(1).getHazaigol()<aktcsapatok.get(1).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getV2()*aktcsapatok.get(1).getHazaiodds());
		}	
		if (fogadasok.getH3()>0 && (aktcsapatok.get(2).getHazaigol()>aktcsapatok.get(2).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getH3()*aktcsapatok.get(2).getHazaiodds());
		}
		else if(fogadasok.getV3()>0 &&  (aktcsapatok.get(2).getHazaigol()<aktcsapatok.get(2).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getV3()*aktcsapatok.get(2).getHazaiodds());
		}
		if (fogadasok.getH4()>0 && (aktcsapatok.get(3).getHazaigol()>aktcsapatok.get(3).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getH4()*aktcsapatok.get(3).getHazaiodds());
		}
		else if(fogadasok.getV4()>0 &&  (aktcsapatok.get(3).getHazaigol()<aktcsapatok.get(3).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getV4()*aktcsapatok.get(3).getHazaiodds());
		}
		if (fogadasok.getH5()>0 && (aktcsapatok.get(4).getHazaigol()>aktcsapatok.get(4).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getH5()*aktcsapatok.get(4).getHazaiodds());
		}
		else if(fogadasok.getV5()>0 &&  (aktcsapatok.get(4).getHazaigol()<aktcsapatok.get(4).getVendeggol())){
			felhasznalo.setEgyenleg(felhasznalo.getEgyenleg()+fogadasok.getV5()*aktcsapatok.get(4).getHazaiodds());
		}
	    egyenleg.setText(felhasznalo.getEgyenleg()+" Ft");		
	}
	private void fogadasigombokletilt(){
	      bh1.setDisable(true);
	      bh2.setDisable(true);
	      bh3.setDisable(true);
	      bh4.setDisable(true);
	      bh5.setDisable(true);
	      bv1.setDisable(true);
	      bv2.setDisable(true);
	      bv3.setDisable(true);
	      bv4.setDisable(true);
	      bv5.setDisable(true);
	}
	
	private void fogadasigombokengedelyez(){
	      bh1.setDisable(false);
	      bh2.setDisable(false);
	      bh3.setDisable(false);
	      bh4.setDisable(false);
	      bh5.setDisable(false);
	      bv1.setDisable(false);
	      bv2.setDisable(false);
	      bv3.setDisable(false);
	      bv4.setDisable(false);
	      bv5.setDisable(false);
	}
}
