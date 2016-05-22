package hu.unideb.inf.bead.fogadoiroda.model;

public class Aktualis {
	
	  String hazai;
	  String vendeg;
	  int hazaigol;
	  int vendeggol;
	  String eredmeny;
	  
	public Aktualis() {
	}
	
	public String getHazai() {
		return hazai;
	}
	
	public void setHazai(String hazai) {
		this.hazai = hazai;
	}
	
	public String getVendeg() {
		return vendeg;
	}
	
	public void setVendeg(String vendeg) {
		this.vendeg = vendeg;
	}
	

	public Aktualis(String hazai, String vendeg, int hazaigol, int vendeggol, String eredmeny) {
		this.hazai = hazai;
		this.vendeg = vendeg;
		this.hazaigol = hazaigol;
		this.vendeggol = vendeggol;
		this.eredmeny = eredmeny;
	}

	public Aktualis(String hazai, String vendeg) {
		this.hazai = hazai;
		this.vendeg = vendeg;
		this.eredmeny = "";

	}

	public int getHazaigol() {
		return hazaigol;
	}

	public void setHazaigol(int hazaigol) {
		this.hazaigol = hazaigol;
	}

	public int getVendeggol() {
		return vendeggol;
	}

	public void setVendeggol(int vendeggol) {
		this.vendeggol = vendeggol;
	}

	public String getEredmeny() {
		return eredmeny;
	}

	public void setEredmeny(String eredmeny) {
		this.eredmeny = eredmeny;
	}

	@Override
	public String toString() {
		return "Aktualis [hazai=" + hazai + ", vendeg=" + vendeg + ", hazaigol=" + hazaigol + ", vendeggol=" + vendeggol
				+ ", eredmeny=" + eredmeny + "]";
	}
	
	
	

}
