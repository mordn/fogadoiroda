package hu.unideb.inf.bead.fogadoiroda.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hu.unideb.inf.bead.fogadoiroda.Main;
import hu.unideb.inf.bead.fogadoiroda.model.Aktualis;
import hu.unideb.inf.bead.fogadoiroda.model.Csapat;
import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;
import hu.unideb.inf.bead.fogadoiroda.view.MainViewController;

public class Tests {
	
	@Test
	public void fogadastnullaztest(){
		
		MainViewController mvc = new MainViewController();
		
		mvc.fogadasok.setH1(1.0);
		mvc.fogadasok.setH2(1.0);
		mvc.fogadasok.setH3(1.0);
		mvc.fogadasok.setH4(1.0);
		mvc.fogadasok.setH5(1.0);
		mvc.fogadasok.setV1(1.0);
		mvc.fogadasok.setV2(1.0);
		mvc.fogadasok.setV3(1.0);
		mvc.fogadasok.setV4(1.0);
		mvc.fogadasok.setV5(1.0);
		
		mvc.fogadastnullaz();
		
		assertEquals(0,mvc.fogadasok.getH1(),0.0);
		assertEquals(0,mvc.fogadasok.getH2(),0.0);
		assertEquals(0,mvc.fogadasok.getH3(),0.0);
		assertEquals(0,mvc.fogadasok.getH4(),0.0);
		assertEquals(0,mvc.fogadasok.getH5(),0.0);
		assertEquals(0,mvc.fogadasok.getV1(),0.0);
		assertEquals(0,mvc.fogadasok.getV2(),0.0);
		assertEquals(0,mvc.fogadasok.getV3(),0.0);
		assertEquals(0,mvc.fogadasok.getV4(),0.0);
		assertEquals(0,mvc.fogadasok.getV5(),0.0);	
	}
/*	@Test
	public void h1fogadtest(){
		MainViewController mvc = new MainViewController();
		List<Felhasznalo> felhasznalolista = new ArrayList<Felhasznalo>();
		felhasznalolista.add(new Felhasznalo("test","test","test","test",1000.0));
		mvc.setFelhasznalo2(new Felhasznalo("test","test","test","test",1000.0),felhasznalolista);
		mvc.felhasznalo.setEgyenleg(10000.0);
		mvc.h1.setText("0");  <----
        mvc.h1fogad();
		assertEquals(0,mvc.fogadasok.getH1(),0.0);
	}*/
	
	@Test
	public void hazainyertest(){
		Csapat hazai = new Csapat(9,"Southampton",0,1);
		Csapat vendeg = new Csapat(1,"Liverpool",0,1);
		MainViewController mvc = new MainViewController();
		mvc.csapatok.add(new Csapat(0,"Arsenal",0,1));
		mvc.csapatok.add(new Csapat(1,"Liverpool",0,1));
		mvc.csapatok.add(new Csapat(2,"Tottenham",0,1));
		mvc.csapatok.add(new Csapat(3,"Newcastle",0,1));
		mvc.csapatok.add(new Csapat(4,"Leichester City",0,1));
		mvc.csapatok.add(new Csapat(5,"Chelsea",0,1));
		mvc.csapatok.add(new Csapat(6,"Manchester City",0,1));
		mvc.csapatok.add(new Csapat(7,"Manchester United",0,1));
		mvc.csapatok.add(new Csapat(8,"Everton",0,1));
		mvc.csapatok.add(new Csapat(9,"Southampton",0,1));
		mvc.hazainyert(3, 0, hazai, vendeg);	
		assertEquals(mvc.csapatok.get(9).getPontszam(),3);

	}
	@Test
	public void dontetlentest(){
		Csapat hazai = new Csapat(0,"Arsenal",0,1);
		Csapat vendeg = new Csapat(1,"Liverpool",0,1);
		MainViewController mvc = new MainViewController();
		mvc.csapatok.add(new Csapat(0,"Arsenal",0,1));
		mvc.csapatok.add(new Csapat(1,"Liverpool",0,1));
		mvc.csapatok.add(new Csapat(2,"Tottenham",0,1));
		mvc.csapatok.add(new Csapat(3,"Newcastle",0,1));
		mvc.csapatok.add(new Csapat(4,"Leichester City",0,1));
		mvc.csapatok.add(new Csapat(5,"Chelsea",0,1));
		mvc.csapatok.add(new Csapat(6,"Manchester City",0,1));
		mvc.csapatok.add(new Csapat(7,"Manchester United",0,1));
		mvc.csapatok.add(new Csapat(8,"Everton",0,1));
		mvc.csapatok.add(new Csapat(9,"Southampton",0,1));
		mvc.dontetlen(1, 1, hazai, vendeg);	
		assertEquals(mvc.csapatok.get(0).getPontszam(),1);
		assertEquals(mvc.csapatok.get(1).getPontszam(),1);

	}
	@Test
	public void vendegnyertest(){
		Csapat hazai = new Csapat(0,"Arsenal",0,1);
		Csapat vendeg = new Csapat(1,"Liverpool",0,1);
		MainViewController mvc = new MainViewController();
		mvc.csapatok.add(new Csapat(0,"Arsenal",0,1));
		mvc.csapatok.add(new Csapat(1,"Liverpool",0,1));
		mvc.csapatok.add(new Csapat(2,"Tottenham",0,1));
		mvc.csapatok.add(new Csapat(3,"Newcastle",0,1));
		mvc.csapatok.add(new Csapat(4,"Leichester City",0,1));
		mvc.csapatok.add(new Csapat(5,"Chelsea",0,1));
		mvc.csapatok.add(new Csapat(6,"Manchester City",0,1));
		mvc.csapatok.add(new Csapat(7,"Manchester United",0,1));
		mvc.csapatok.add(new Csapat(8,"Everton",0,1));
		mvc.csapatok.add(new Csapat(9,"Southampton",0,1));
		mvc.vendegnyert(0, 1, hazai, vendeg);	
		assertEquals(mvc.csapatok.get(1).getPontszam(),3);
	}
	@Test public void  fogadasoklejatszasa(){
		MainViewController mvc = new MainViewController();
		
		List<Felhasznalo> felhasznalolista = new ArrayList<Felhasznalo>();
		felhasznalolista.add(new Felhasznalo("test","test","test","test",1000.0));
		
		mvc.setFelhasznalo2(new Felhasznalo("test","test","test","test",1000.0),felhasznalolista);
		
		mvc.fogadasok.setH1(100.0);
		mvc.fogadasok.setV2(50.0);
		mvc.fogadasok.setV3(100.0);
		mvc.fogadasok.setH4(300.0);
		mvc.fogadasok.setH5(10.0);
		
		mvc.aktcsapatok.add(new Aktualis("Manchester United","Arsenal",5,3,2.0,1.50,"5:3"));
		mvc.aktcsapatok.add(new Aktualis("Tottenham","Leichester City",2,3,2.50,2.0,"2:3"));
	    mvc.aktcsapatok.add(new Aktualis("Manchester City", "Southampton" ,0,3,1.75,2.0,"0:3"));
	    mvc.aktcsapatok.add(new Aktualis("Liverpool","Newcastle",1,0,1.5,2.5,"1:0"));
		mvc.aktcsapatok.add(new Aktualis("Chelsea","Everton",5,2,2.5,1.5,"5:2"));
		
		mvc.fogadasoklejatszasa();
		assertEquals(mvc.felhasznalo.getEgyenleg(),1975.0,0.0);
	}
	
	@Test public void legitfelhasznalotest(){
		Main main = new Main() ;
		Felhasznalo felhasznalo1 = new Felhasznalo("test","test","test","test",1000.0);
		Felhasznalo felhasznalo2 = new Felhasznalo("test","","test","test",1000.0);
		boolean test1,test2;
		test1 = main.legitfelhasznalo(felhasznalo1);
		test2 = main.legitfelhasznalo(felhasznalo2);
		assertEquals(test1,true);
		assertEquals(test2,false);
	}
	
	
}
