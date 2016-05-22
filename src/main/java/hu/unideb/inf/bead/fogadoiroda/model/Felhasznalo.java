package hu.unideb.inf.bead.fogadoiroda.model;

public class Felhasznalo {
   String felhnev;
   String jelszo;
   String vnev;
   String knev;
   Double egyenleg;
   
public Felhasznalo(String felhnev, String jelszo, String vnev, String knev, Double egyenleg) {
	this.felhnev = felhnev;
	this.jelszo = jelszo;
	this.vnev = vnev;
	this.knev = knev;
	this.egyenleg = egyenleg;
}

public Felhasznalo() {
	this.felhnev = "";
	this.jelszo = "";
	this.vnev = "";
	this.knev = "";
	this.egyenleg = 0.0;
}

public String getFelhnev() {
	return felhnev;
}

public void setFelhnev(String felhnev) {
	this.felhnev = felhnev;
}

public String getJelszo() {
	return jelszo;
}

public void setJelszo(String jelszo) {
	this.jelszo = jelszo;
}

public String getVnev() {
	return vnev;
}

public void setVnev(String vnev) {
	this.vnev = vnev;
}

public String getKnev() {
	return knev;
}

public void setKnev(String knev) {
	this.knev = knev;
}

public Double getEgyenleg() {
	return egyenleg;
}

public void setEgyenleg(Double egyenleg) {
	this.egyenleg = egyenleg;
}

@Override
public String toString() {
	return "Felhasznalo [felhnev=" + felhnev + ", jelszo=" + jelszo + ", vnev=" + vnev
			+ ", knev=" + knev + ", egyenleg=" + egyenleg + "]";
}
   
   
}
