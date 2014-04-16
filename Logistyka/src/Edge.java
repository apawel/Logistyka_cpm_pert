
public class Edge {


private String nazwa;
private   Vertex begin, end;        //...
private int weight;    





public Edge(String nazwa, Vertex begin, Vertex end, int weight) {
	super();
	this.nazwa = nazwa;
	this.begin = begin;
	this.end = end;
	this.weight = weight;
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
