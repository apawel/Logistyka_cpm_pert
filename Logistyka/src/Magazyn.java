
public class Magazyn {
	String nazwa_czyn;
	int czas_trwania;
	int pocz;
	
	public String getNazwa_czyn() {
		return nazwa_czyn;
	}

	public void setNazwa_czyn(String nazwa_czyn) {
		this.nazwa_czyn = nazwa_czyn;
	}

	public int getCzas_trwania() {
		return czas_trwania;
	}

	public void setCzas_trwania(int czas_trwania) {
		this.czas_trwania = czas_trwania;
	}

	public int getPocz() {
		return pocz;
	}

	public void setPocz(int pocz) {
		this.pocz = pocz;
	}

	public int getKoniec() {
		return koniec;
	}

	public void setKoniec(int koniec) {
		this.koniec = koniec;
	}

	public Magazyn(String nazwa_czyn, int czas_trwania, int pocz, int koniec) {
		super();
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.pocz = pocz;
		this.koniec = koniec;
	}

	int koniec;

	public Magazyn() {
	}

}
