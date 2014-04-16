import java.util.ArrayList;


public class MagazynPoprz {
	String nazwa_czyn;
	int czas_trwania;
	ArrayList<String> czynn_poprzedz = new ArrayList<>();
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
	public ArrayList<String> getCzynn_poprzedz() {
		return czynn_poprzedz;
	}
	public void setCzynn_poprzedz(ArrayList<String> czynn_poprzedz) {
		this.czynn_poprzedz = czynn_poprzedz;
	}
	public MagazynPoprz(String nazwa_czyn, int czas_trwania,
			ArrayList<String> czynn_poprzedz) {
		super();
		this.nazwa_czyn = nazwa_czyn;
		this.czas_trwania = czas_trwania;
		this.czynn_poprzedz = czynn_poprzedz;
	}
	public MagazynPoprz() {
		// TODO Auto-generated constructor stub
	}


	
	
	

}
