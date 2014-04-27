
public class Magazyn {
	String nazwa_czyn;
	int czas_trwania;
	int pocz;
	int koniec;
	boolean zmianapocz;
	boolean zmianakonca;
	public boolean isZmianapocz() {
		return zmianapocz;
	}

	public void setZmianapocz(boolean zmianapocz) {
		this.zmianapocz = zmianapocz;
	}

	public boolean isZmianakonca() {
		return zmianakonca;
	}

	public void setZmianakonca(boolean zmianakonca) {
		this.zmianakonca = zmianakonca;
	}


	int tc,tm,tp;
	
	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getTm() {
		return tm;
	}

	public void setTm(int tm) {
		this.tm = tm;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

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
		this.tc=0;
		this.tm=0;
		this.tp=0;
		this.zmianakonca=false;
		this.zmianapocz=false;
	}

	public Magazyn(String nazwa_czyn, int czas_trwania, int pocz, int tc,
			int tm, int tp, int koniec) {
		super();
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.pocz = pocz;
		this.tc = tc;
		this.tm = tm;
		this.tp = tp;
		this.koniec = koniec;
		this.zmianakonca=false;
		this.zmianapocz=false;
	}

	
	public Magazyn() {
	}

}
