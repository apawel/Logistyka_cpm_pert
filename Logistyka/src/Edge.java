
public class Edge {


private String nazwa;
private   Vertex begin, end;        //...
private int weight;    //inaczej w pert to
double tc,tm,tp;
private boolean krytyczna;
private double wariancja;




public double getWariancja() {
	return wariancja;
}
public void setWariancja() {
	
	this.wariancja = Math.pow(((getTp()-getTc())/6),2);
}
public boolean isKrytyczna() {
	return krytyczna;
}
public void setKrytyczna(boolean krytyczna) {
	this.krytyczna = krytyczna;
}
public Edge(String nazwa, Vertex begin, Vertex end, int weight, double tc, double tm,
		double tp) {
	super();
	this.nazwa = nazwa;
	this.begin = begin;
	this.end = end;
	this.weight = weight;
	this.tc = tc;
	this.tm = tm;
	this.tp = tp;
	this.krytyczna=false;
	this.wariancja=0;
}
public Edge(String nazwa, Vertex begin, Vertex end, int weight) {
	super();
	this.nazwa = nazwa;
	this.begin = begin;
	this.end = end;
	this.weight = weight;
	this.tc=0;
	this.tm=0;
	this.tp=0;
	this.krytyczna=false;
	this.wariancja=0;
}
public double getTc() {
	return tc;
}
public void setTc(int tc) {
	this.tc = tc;
}
public double getTm() {
	return tm;
}
public void setTm(int tm) {
	this.tm = tm;
}
public double getTp() {
	return tp;
}
public void setTp(int tp) {
	this.tp = tp;
}
public Vertex getBegin() {
	return begin;
}
public void setBegin(Vertex begin) {
	this.begin = begin;
}
public Vertex getEnd() {
	return end;
}
public void setEnd(Vertex end) {
	this.end = end;
}
public int getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}
public String getNazwa() {
	return nazwa;
}
public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}

public void wyswietl()
{
	
		System.out.println("Czynnoœæ: "+ this.getNazwa() + ", czas trwania: " + this.getWeight() + ", nastepstwo: " + this.getBegin().getNumer_zdarzenia() + "-" + this.getEnd().getNumer_zdarzenia() );
	
}


	
}
