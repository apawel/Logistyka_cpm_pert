
public class Vertex {
	private int numer_zdarzenia;
	private int najwczesniejszy_moment;
	private int najpozniejszy_moment;
	private int zapas_czasu;
	private boolean czy_krytyczna;
	private boolean czy_polaczaona;
	
	/**
	 * 
	 * @return
	 */
	public int getNumer_zdarzenia() {
		return numer_zdarzenia;
	}
	
	public boolean isCzy_polaczaona() {
		return czy_polaczaona;
	}

	public void setCzy_polaczaona(boolean czy_polaczaona) {
		this.czy_polaczaona = czy_polaczaona;
	}

	public void setNumer_zdarzenia(int numer_zdarzenia) {
		this.numer_zdarzenia = numer_zdarzenia;
	}
	public int getNajwczesniejszy_moment() {
		return najwczesniejszy_moment;
	}
	public void setNajwczesniejszy_moment(int najwczesniejszy_moment) {
		this.najwczesniejszy_moment = najwczesniejszy_moment;
	}
	public int getNajpozniejszy_moment() {
		return najpozniejszy_moment;
	}
	public boolean isCzy_krytyczna() {
		return czy_krytyczna;
	}

	public void setCzy_krytyczna(boolean czy_krytyczna) {
		this.czy_krytyczna = czy_krytyczna;
	}

	public void setNajpozniejszy_moment(int najpozniejszy_moment) {
		this.najpozniejszy_moment = najpozniejszy_moment;
	}
	public int getZapas_czasu() {
		return zapas_czasu;
	}
	public void setZapas_czasu(int zapas_czasu) {
		this.zapas_czasu = zapas_czasu;
	}

	public Vertex(int numer_zdarzenia, int najwczesniejszy_moment,
			int najpozniejszy_moment, int zapas_czasu) {
		super();
		this.numer_zdarzenia = numer_zdarzenia;
		this.najwczesniejszy_moment = najwczesniejszy_moment;
		this.najpozniejszy_moment = najpozniejszy_moment;
		this.zapas_czasu = zapas_czasu;
		this.czy_krytyczna = false;
		this.czy_polaczaona=false;
	}
	public void czyKrytyczna()
	{
		if(this.getZapas_czasu()==0)
			this.czy_krytyczna=true;
		else
			this.czy_krytyczna=false;
	}
	
	

}
