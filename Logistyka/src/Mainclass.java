import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


public class Mainclass {
	
	public void tworz_graf(Magazyn magazyn)
	{
		
	}

	public static void main(String[] args) throws IOException {
	
		
	String nazwa_czyn;
	int czas_trwania;
	int pocz;
	int koniec;
	String polacz;
	int c = 0;
	System.out.println("Plik z nastepstwem zdarzen (1), plik z poprzednikiem (2), inna wartosc zamyka program.");
	Scanner input = new Scanner(System.in);
	Scanner odczyt;
	while(true)
	{
	try
	{
	c = Integer.parseInt(input.nextLine());
	break;
	}
	catch(NumberFormatException ex)
	{
		System.out.println("Plik z nastepstwem zdarzen (1), plik z poprzednikiem (2).");
			
	}
	}
	input.close();
	switch(c)
	{
	case 1:
		int ile_wierzch = 0;
		odczyt = new Scanner(new File("dane.txt"));
		ArrayList<Magazyn> magazyn = new ArrayList<>();
		while(odczyt.hasNext())
		{
		nazwa_czyn=odczyt.next();	
		czas_trwania=Integer.parseInt(odczyt.next());
		polacz=odczyt.next();
		pocz=Integer.parseInt(polacz.substring(0,1));
		koniec=Integer.parseInt(polacz.substring(2,3));
		if(ile_wierzch<koniec)
		{
			ile_wierzch=koniec;
		}
		magazyn.add(new Magazyn(nazwa_czyn, czas_trwania, pocz, koniec));
		}
		odczyt.close();
		Iterator<Magazyn> it = magazyn.iterator();
		
		
		/**
		 * Przepisanie danych z magazynu do klas Edge i Vertex
		 */
	it = magazyn.iterator();
	ArrayList<Edge> krawedz = new ArrayList<>();
	ArrayList<Vertex> wierzcholek = new ArrayList<>();
	for(int i =0 ;i<ile_wierzch;i++)
	{
		wierzcholek.add(new Vertex(i+1,0,0,0));
	}

	while(it.hasNext())
	{
		Magazyn element = it.next();
		
		krawedz.add(new Edge(element.getNazwa_czyn(),wierzcholek.get(element.getPocz()-1),wierzcholek.get(element.getKoniec()-1), element.getCzas_trwania()));
		
	}
/*Nie wiem jak sie to uzupelnialo... nie pamietam*/
	for(int i =0;i<wierzcholek.size();i++)
	{
		if(wierzcholek.get(i).getNumer_zdarzenia() == 1)
		{
			wierzcholek.get(i).setNajwczesniejszy_moment(0);
			wierzcholek.get(i).setZapas_czasu(0);
			wierzcholek.get(i).setNajpozniejszy_moment(0);
		}
		else
		{
		
			ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
			 for(Edge x : krawedz)
			 {
				 
				 if(x.getEnd().getNumer_zdarzenia() == wierzcholek.get(i).getNumer_zdarzenia())
				 {
						najwczsniejsze_czasy.add(wierzcholek.get(x.getBegin().getNumer_zdarzenia()-1).getNajwczesniejszy_moment() + x.getWeight());
				 }
			 }
			 wierzcholek.get(i).setNajwczesniejszy_moment(Collections.max(najwczsniejsze_czasy));
			 najwczsniejsze_czasy.clear();
		
		}
	}
	for(int i = wierzcholek.size();i>0;i--)
	{
		if(i==wierzcholek.size())
		{
		wierzcholek.get(i-1).setNajpozniejszy_moment(wierzcholek.get(i-1).getNajwczesniejszy_moment());
		wierzcholek.get(i-1).setZapas_czasu(wierzcholek.get(i-1).getNajpozniejszy_moment()-wierzcholek.get(i-1).getNajwczesniejszy_moment());
		}
		else
		{
			ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
			 for(Edge x : krawedz)
			 {
				 
				 if(x.getBegin().getNumer_zdarzenia() == wierzcholek.get(i-1).getNumer_zdarzenia())
				 {
						najpozniejsze_czasy.add(wierzcholek.get(x.getEnd().getNumer_zdarzenia()-1).getNajpozniejszy_moment() - x.getWeight());
				 }
			 }
			 wierzcholek.get(i-1).setNajpozniejszy_moment((Collections.min(najpozniejsze_czasy)));
			 wierzcholek.get(i-1).setZapas_czasu(wierzcholek.get(i-1).getNajpozniejszy_moment()-wierzcholek.get(i-1).getNajwczesniejszy_moment());
			 najpozniejsze_czasy.clear();
		}
	}
		
		
	
	/*wyswietlanie*/
	for(int i =0;i<krawedz.size();i++)
	{
		krawedz.get(i).wyswietl();
	}
	System.out.println("\n");
	for(int i =0;i<wierzcholek.size();i++)
	{
		wierzcholek.get(i).czyKrytyczna();
		
		System.out.println("Werzcholek: " + wierzcholek.get(i).getNumer_zdarzenia() + " najwcz_czas: " + wierzcholek.get(i).getNajwczesniejszy_moment() + " najpoz_czas: " + wierzcholek.get(i).getNajpozniejszy_moment() + " luz_czas: " + wierzcholek.get(i).getZapas_czasu());
	}
	String krytyczna_sciezka = "";
	String max = "";
	int czas_trwania_przeds =0;
	for(int i =0;i<krawedz.size();i++)
	{
		if(krawedz.get(i).getBegin().isCzy_krytyczna() && krawedz.get(i).getEnd().isCzy_krytyczna())
		{
			max = krawedz.get(i).getNazwa();
			for(Edge x: krawedz)
			{
				
				if(x.getBegin().getNumer_zdarzenia() == krawedz.get(i).getBegin().getNumer_zdarzenia() && x.getNazwa() != krawedz.get(i).getNazwa())
				{
					if(x.getWeight()>krawedz.get(i).getWeight())
					{				
						max="";				  
					}
					
						
				}
				
				
			}
			if(max!="")
			{
				czas_trwania_przeds+=krawedz.get(i).getWeight();
			krytyczna_sciezka += " -> " + max;//+krawedz.get(i).getNazwa();	
			}
		}
		
	}
		System.out.println("Sciezka Krytyczna to: " + krytyczna_sciezka + " Minimalny czas trwania: " + czas_trwania_przeds);
		
		break;
	case 2:
		odczyt = new Scanner(new File("dane1.txt"));
		ArrayList<MagazynPoprz> magazyn_poprz = new ArrayList<>();
		while(odczyt.hasNext())
		{
		nazwa_czyn=odczyt.next();	
		czas_trwania=Integer.parseInt(odczyt.next());
		polacz=odczyt.next();
		String czynnosc_poprzedzajaca;
		for(int i =0;i<polacz.length();i++)
		{
			czynnosc_poprzedzajaca = polacz.substring(i, i+1);
			if(czynnosc_poprzedzajaca == "-" || czynnosc_poprzedzajaca == ",")
				continue;
			else
			{
				MagazynPoprz magazyn1 = new MagazynPoprz();
				magazyn1.setCzas_trwania(czas_trwania);
				magazyn1.setNazwa_czyn(nazwa_czyn);
				magazyn1.getCzynn_poprzedz().add(czynnosc_poprzedzajaca);
				
			   magazyn_poprz.add(magazyn1);
			}
		}
		
		
	
		}
		odczyt.close();
		Iterator<MagazynPoprz> ite = magazyn_poprz.iterator();
		while(ite.hasNext())
		{
			MagazynPoprz element = ite.next();
			System.out.print("Czynnoœæ: "+ element.getNazwa_czyn() + ", czas trwania: " + element.getCzas_trwania() + ", Poprzednik: ");
			for(int i =0; i<element.getCzynn_poprzedz().size();i++)
			{
				System.out.print(element.getCzynn_poprzedz().get(i) + "  ");
			}
			System.out.print("\n");
		}
		odczyt.close();
		break;
		
	default:
		input.close();
	
		System.exit(1);
					
	}
	
	
	
	
	
	

	}

}
