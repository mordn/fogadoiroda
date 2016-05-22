package hu.unideb.inf.bead.fogadoiroda.model;

public class Csapat {

	private int id;
	private String nev;
	private int pontszam;
	private double erosseg;
	
	public Csapat(int id, String nev, int pontszam, double erosseg) {
		this.id = id;
		this.nev = nev;
		this.pontszam = pontszam;
		this.erosseg = erosseg;
	}
	public Csapat() {
		this.id = 999;
		this.nev = "";
		this.pontszam = 0;
		this.erosseg = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public int getPontszam() {
		return pontszam;
	}

	public void setPontszam(int pontszam) {
		this.pontszam = pontszam;	
	}

	public double getErosseg() {
		return erosseg;
	}

	public void setErosseg(double erosseg) {
		this.erosseg = erosseg;
	}

	@Override
	public String toString() {
		return "Csapat [id=" + id + ", nev=" + nev + ", pontszam=" + pontszam + ", erosseg=" + erosseg + "]";
	}
	
}