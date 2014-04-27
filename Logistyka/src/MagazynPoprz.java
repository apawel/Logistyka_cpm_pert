import java.util.ArrayList;

public class MagazynPoprz {
	int numer;
	int tc, tm, tp;

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

	public MagazynPoprz(int numer, int tc, int tm, int tp, String nazwa_czyn,
			int czas_trwania, String czynn_poprzedz) {
		super();
		this.numer = numer;
		this.tc = tc;
		this.tm = tm;
		this.tp = tp;
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.czynn_poprzedz = czynn_poprzedz;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public MagazynPoprz(int numer, String nazwa_czyn, int czas_trwania,
			String czynn_poprzedz) {
		super();
		this.numer = numer;
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.czynn_poprzedz = czynn_poprzedz;
	}

	String nazwa_czyn;

	public String getCzynn_poprzedz() {
		return czynn_poprzedz;
	}

	public void setCzynn_poprzedz(String czynn_poprzedz) {
		this.czynn_poprzedz = czynn_poprzedz;
	}

	int czas_trwania;
	String czynn_poprzedz;

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

	public MagazynPoprz(String nazwa_czyn, int czas_trwania,
			String czynn_poprzedz) {
		super();
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.czynn_poprzedz = czynn_poprzedz;
	}

	public MagazynPoprz() {
		// TODO Auto-generated constructor stub
	}

}
