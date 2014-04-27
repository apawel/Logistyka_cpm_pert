import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.commons.math3.distribution.NormalDistribution;
//dwie drogi: metoda simplex, lub inaczej.
//aplikacja ktora pozwoli optymalizowac zagadnienia liniowa, funkcja liniowa poszukujemy albo max albo min przy ukladzie warunkow ograniczajacych , rowniez o charakterze liniowym
public class Mainclass {

	public static boolean czy_zawiera(ArrayList<Magazyn> magazyn,MagazynPoprz elem)
	{
		for(int i =0;i<magazyn.size();i++)
		{
			if(magazyn.get(i).getNazwa_czyn().equals(elem.getNazwa_czyn()))
				return true;
		}
		return false;
	}
	public static int znajdz_index_pocz(ArrayList<Magazyn> magazyn,int zmienna)
	{
		for(int i =0;i<magazyn.size();i++)
		{
			if(magazyn.get(i).getPocz() == zmienna)
				return i;
		
		}
		return zmienna;
	}
	public static int znajdz_index_konc(ArrayList<Magazyn> magazyn,int zmienna)
	{
		for(int i =0;i<magazyn.size();i++)
		{
		
			if(magazyn.get(i).getKoniec() == zmienna)
				return i;
		}
		return 0;
	}
	public static ArrayList<Magazyn> numeracja(ArrayList<Magazyn> magazyn)
	{
		ArrayList<Magazyn> wynik = magazyn;
		ArrayList<Integer> zdarzenia=new ArrayList<>();
		zdarzenia.add(1);
		
		for(int i =0;i<magazyn.size();i++)
		{
			if(!zdarzenia.contains(wynik.get(i).getKoniec()))
			{
			zdarzenia.add(wynik.get(i).getKoniec());			
			
			}
			
		}
		int wielkosc=zdarzenia.size();
	
		
		for(int i=0;i<wielkosc;i++)				
		{
			if(zdarzenia.contains(i+1))
			{
				
			}
			else
			{
				for(int j =0;j<magazyn.size();j++)
				{
					boolean flaga = true;
					int licznik=0;
				//	System.out.println("Porownanie poczatek " + magazyn.get(j).getPocz()+ " nazwa: " + magazyn.get(j).getNazwa_czyn() + " z: " + zdarzenia.get(i+licznik));
					while(flaga){
					if(magazyn.get(j).getPocz() == zdarzenia.get(i+licznik) && !magazyn.get(j).isZmianapocz())
					{						
					
						magazyn.get(j).setPocz(i+licznik+1);
						magazyn.get(j).setZmianapocz(true);
						flaga=false;
					}
				//	System.out.println("Porownanie koniec " + magazyn.get(j).getKoniec() + " nazwa: " + magazyn.get(j).getNazwa_czyn()+" z: " + zdarzenia.get(i+licznik));
					if(magazyn.get(j).getKoniec() == zdarzenia.get(i+licznik) && !magazyn.get(j).isZmianakonca())
					{
						magazyn.get(j).setKoniec(i+licznik+1);
						magazyn.get(j).setZmianakonca(true);
						flaga=false;					
					}
					if(licznik<magazyn.get(j).getKoniec())
					licznik++;
					else
						flaga=false;
					
					}
				}
				
				
			}
			
			/*if(magazyn.get(i).getPocz()==zdarzenia.get(j))
			{
				
			}
			else
			{
				
			}
			if(znajdz_index_pocz(magazyn,zdarzenia.get(i)) != 0)
			magazyn.get(znajdz_index_pocz(magazyn, zdarzenia.get(i-1))).setPocz(zdarzenia.get(i));
			else
				magazyn.get(i).setPocz(zdarzenia.get(i));
		if(znajdz_index_konc(magazyn,zdarzenia.get(i)) != 0)
			magazyn.get(znajdz_index_konc(magazyn, zdarzenia.get(i-1))).setPocz(zdarzenia.get(i));
		else
			magazyn.get(i).setKoniec(zdarzenia.get(i));*/
			
		}
		
	/*	Iterator it = magazyn.iterator();
		System.out.println("********************");
		while(it.hasNext())
		{
			Magazyn obj = (Magazyn) it.next();
			System.out.println("Nazwa: " + obj.getNazwa_czyn() + " nastepstwo: "+obj.getPocz()+"-"+obj.getKoniec()); 
		}
		System.out.println("*******************");*/
		return magazyn;
		
	}
	public void tworz_graf(Magazyn magazyn) {

	}

	public static int szukaj_konca(ArrayList<MagazynPoprz> magazyn,
			String poczatek, int max) {
		Iterator<MagazynPoprz> ite = magazyn.iterator();
		while (ite.hasNext()) {
			MagazynPoprz element = ite.next();
			if (element.getCzynn_poprzedz().equals(poczatek)) {
				return element.getNumer();
			}

		}
		return max;
	}

	public static int szukaj_pocz(ArrayList<Magazyn> magazyn, String poczatek) {
		Iterator<Magazyn> ite = magazyn.iterator();
		while (ite.hasNext()) {
			Magazyn element = ite.next();
			if (element.getNazwa_czyn().equals(poczatek)) {
				return element.getKoniec();
			}

		}
		return 0;
	}

	public static void main(String[] args) throws IOException {

		Frame a = null;

		FileDialog fd;
		String plik;

		Scanner odczyt;
		String nazwa_czyn;
		int czas_trwania;
		int pocz;
		int koniec;
		String polacz;
		Integer liczba2;

		String wejscie = JOptionPane
				.showInputDialog("(1) CPM   (2) PERT  inna wartosc zamyka program.");
		if (wejscie == null)
			wejscie = "3";
		Integer liczba = Integer.parseInt(wejscie);

		/***** CPM ******/
		switch (liczba) {
		case 1:

			wejscie = JOptionPane
					.showInputDialog("Plik z nastepstwem zdarzen (1), plik z poprzednikiem (2), inna wartosc zamyka program.");
			if (wejscie == null)
				wejscie = "3";
			liczba2 = Integer.parseInt(wejscie);

			switch (liczba2) {
			/***** Nastepstwo zdarzen ******/
			case 1:
				fd = new FileDialog(a, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				plik = fd.getFile();

				int ile_wierzch = 0;
				odczyt = new Scanner(new File(plik));
				ArrayList<Magazyn> magazyn = new ArrayList<>();
				while (odczyt.hasNext()) {
					nazwa_czyn = odczyt.next();
					czas_trwania = Integer.parseInt(odczyt.next());
					polacz = odczyt.next();
					pocz = Integer.parseInt(polacz.substring(0, 1));
					koniec = Integer.parseInt(polacz.substring(2, 3));
					if (ile_wierzch < koniec) {
						ile_wierzch = koniec;
					}
					magazyn.add(new Magazyn(nazwa_czyn, czas_trwania, pocz,
							koniec));
				}
				odczyt.close();
				Iterator<Magazyn> it = magazyn.iterator();

				/**
				 * Przepisanie danych z magazynu do klas Edge i Vertex
				 */
				it = magazyn.iterator();
				ArrayList<Edge> krawedz = new ArrayList<>();
				ArrayList<Vertex> wierzcholek = new ArrayList<>();
				for (int i = 0; i < ile_wierzch; i++) {
					wierzcholek.add(new Vertex(i + 1, 0, 0, 0));
				}

				while (it.hasNext()) {
					Magazyn element = it.next();

					krawedz.add(new Edge(element.getNazwa_czyn(), wierzcholek
							.get(element.getPocz() - 1), wierzcholek
							.get(element.getKoniec() - 1), element
							.getCzas_trwania()));

				}
				/* Uzupelnianie wierzcholkow */
				for (int i = 0; i < wierzcholek.size(); i++) {
					if (wierzcholek.get(i).getNumer_zdarzenia() == 1) {
						wierzcholek.get(i).setNajwczesniejszy_moment(0);
						wierzcholek.get(i).setZapas_czasu(0);
						wierzcholek.get(i).setNajpozniejszy_moment(0);
					} else {

						ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {

							if (x.getEnd().getNumer_zdarzenia() == wierzcholek
									.get(i).getNumer_zdarzenia()) {
								najwczsniejsze_czasy.add(wierzcholek.get(
										x.getBegin().getNumer_zdarzenia() - 1)
										.getNajwczesniejszy_moment()
										+ x.getWeight());
							}
						}
						wierzcholek.get(i).setNajwczesniejszy_moment(
								Collections.max(najwczsniejsze_czasy));
						najwczsniejsze_czasy.clear();

					}
				}
				for (int i = wierzcholek.size(); i > 0; i--) {
					if (i == wierzcholek.size()) {
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								wierzcholek.get(i - 1)
										.getNajwczesniejszy_moment());
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
					} else {
						ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {

							if (x.getBegin().getNumer_zdarzenia() == wierzcholek
									.get(i - 1).getNumer_zdarzenia()) {
								najpozniejsze_czasy.add(wierzcholek.get(
										x.getEnd().getNumer_zdarzenia() - 1)
										.getNajpozniejszy_moment()
										- x.getWeight());
							}
						}
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								(Collections.min(najpozniejsze_czasy)));
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
						najpozniejsze_czasy.clear();
					}
				}

				/* wyswietlanie */
				/*
				 * for(int i =0;i<krawedz.size();i++) {
				 * krawedz.get(i).wyswietl(); }
				 */

				for (int i = 0; i < wierzcholek.size(); i++) {
					wierzcholek.get(i).czyKrytyczna();

					// System.out.println("Werzcholek: " +
					// wierzcholek.get(i).getNumer_zdarzenia() +
					// " najwcz_czas: " +
					// wierzcholek.get(i).getNajwczesniejszy_moment() +
					// " najpoz_czas: " +
					// wierzcholek.get(i).getNajpozniejszy_moment() +
					// " luz_czas: " + wierzcholek.get(i).getZapas_czasu());
				}
				String krytyczna_sciezka = "";
				String max = "";
				int czas_trwania_przeds = 0;
				int numer_poczatku_spr = 0;
				for (int i = 0; i < krawedz.size(); i++) {
					if (krawedz.get(i).getBegin().isCzy_krytyczna()
							&& krawedz.get(i).getEnd().isCzy_krytyczna()
							&& !krawedz.get(i).getBegin().isCzy_polaczaona()) {
						max = krawedz.get(i).getNazwa();
						// System.out.println("Sprawdzam " + max +
						// " w zdarzeniu poczatkowym " +
						// krawedz.get(i).getBegin().getNumer_zdarzenia());
						// for(Edge x: krawedz)
						for (numer_poczatku_spr = krawedz.get(i).getBegin()
								.getNumer_zdarzenia(); numer_poczatku_spr < krawedz
								.size(); numer_poczatku_spr++) {
							Edge x = krawedz.get(numer_poczatku_spr);
							if (x.getBegin().getNumer_zdarzenia() == krawedz
									.get(i).getBegin().getNumer_zdarzenia()
									&& x.getNazwa() != krawedz.get(i)
											.getNazwa()
									&& x.getEnd().isCzy_krytyczna()) {
								if (x.getWeight() > krawedz.get(i).getWeight()) {
									// System.out.println("Usuwam " + max +
									// " bedac przy krawedzi " +
									// krawedz.get(i).getNazwa());
									// System.out.println("bo sprawdzany  " +
									// x.getNazwa() + " jest rowny " +
									// krawedz.get(i).getNazwa() +
									// " pierwszy ma wiekszy waga od drugiego");
									max = "";
								}
								// else
								// System.out.println("Sprawdzany  " +
								// x.getNazwa() + " laczy sie z tym samym " +
								// krawedz.get(i).getNazwa() +
								// " pierwszy ma mniejsz waga od drugiego");

							}

						}
						if (max != "") {
							// System.out.println("Dodaje " + max);
							czas_trwania_przeds += krawedz.get(i).getWeight();
							krawedz.get(i).getBegin().setCzy_polaczaona(true);
							krytyczna_sciezka += " -> " + max;// +krawedz.get(i).getNazwa();
						}
					}

				}

				Component frame = null;
				String wyjscie = "Sciezka Krytyczna to: " + krytyczna_sciezka
						+ " Minimalny czas trwania: " + czas_trwania_przeds;
				JOptionPane.showMessageDialog(frame, wyjscie,
						"Wynik dzialania CPM", JOptionPane.PLAIN_MESSAGE);

				break;
			case 2:
				/***** Poprzednik ******/
				fd = new FileDialog(a, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				plik = fd.getFile();

				odczyt = new Scanner(new File(plik));
				ArrayList<MagazynPoprz> magazyn_poprz = new ArrayList<>();
				int numer = 1;
				while (odczyt.hasNext()) {
					nazwa_czyn = odczyt.next();
					czas_trwania = Integer.parseInt(odczyt.next());
					polacz = odczyt.next();
					String czynnosc_poprzedzajaca;

					for (int i = 0; i < polacz.length(); i++) {
						czynnosc_poprzedzajaca = polacz.substring(i, i + 1);
						if (czynnosc_poprzedzajaca.equals(",")) {

							continue;
						} else {
							MagazynPoprz magazyn1 = new MagazynPoprz();
							magazyn1.setCzas_trwania(czas_trwania);
							magazyn1.setNazwa_czyn(nazwa_czyn);
							magazyn1.setCzynn_poprzedz(czynnosc_poprzedzajaca);
							magazyn1.setNumer(numer);
							magazyn_poprz.add(magazyn1);

						}

					}

					numer++;
				}
				odczyt.close();

				Iterator<MagazynPoprz> ite = magazyn_poprz.iterator();
				ArrayList<Magazyn> magazyn2 = new ArrayList<>();
				int iteracja = 0;
				while (ite.hasNext()) {
					MagazynPoprz element = ite.next();
					if (element.getCzynn_poprzedz().equals("-")) {

						magazyn2.add(new Magazyn(element.getNazwa_czyn(),
								element.getCzas_trwania(), 1, szukaj_konca(
										magazyn_poprz, element.getNazwa_czyn(),
										numer)));
					} else if(!czy_zawiera(magazyn2,element)) {
						iteracja++;
						magazyn2.add(new Magazyn(element.getNazwa_czyn(),
								element.getCzas_trwania(), szukaj_pocz(
										magazyn2, element.getCzynn_poprzedz()),
								szukaj_konca(magazyn_poprz,
										element.getNazwa_czyn(), numer)));
					}

				}

				
				it = magazyn2.iterator();
		//	System.out.println("********************");
		/*	while(it.hasNext())
			{
				Magazyn obj = it.next();
				System.out.println("Nazwa: " + obj.getNazwa_czyn() + " nastepstwo: "+obj.getPocz()+"-"+obj.getKoniec()); 
			}
			System.out.println("*******************");*/
			magazyn2=numeracja(magazyn2);
				/**
				 * 
				 * 
				 * Przepisanie danych z magazynu do klas Edge i Vertex
				 */
			/*
				it = magazyn2.iterator();
				krawedz = new ArrayList<>();
				wierzcholek = new ArrayList<>();
				for (int i = 0; i < numer + 1; i++) {
					wierzcholek.add(new Vertex(i + 1, 0, 0, 0));
				}

				while (it.hasNext()) {
					Magazyn element = it.next();

					krawedz.add(new Edge(element.getNazwa_czyn(), wierzcholek
							.get(element.getPocz() - 1), wierzcholek
							.get(element.getKoniec() - 1), element
							.getCzas_trwania()));

				}
				for (int i = 0; i < krawedz.size(); i++) {
					krawedz.get(i).wyswietl();
				}*/
			/*	/* Uzupelnianie wierzcholkow */

				/*Iterator iterat = wierzcholek.iterator();

				while (iterat.hasNext()) {

					boolean czyjest = false;
					Vertex elem = (Vertex) iterat.next();
					if (elem.getNumer_zdarzenia() == 1) {
						elem.setNajwczesniejszy_moment(0);
						elem.setZapas_czasu(0);
						elem.setNajpozniejszy_moment(0);
						// elem.wyswietl();
					} else {

						ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {
							// // System.out
							// // .println("porown z X: "
							// // + x.getEnd().getNumer_zdarzenia()
							// // + " i z elem: "
							// + elem.getNumer_zdarzenia());
							if (x.getEnd().getNumer_zdarzenia() == elem
									.getNumer_zdarzenia()) {
								najwczsniejsze_czasy.add(wierzcholek.get(
										x.getBegin().getNumer_zdarzenia())
										.getNajwczesniejszy_moment()
										+ x.getWeight());
								czyjest = true;
							}
						}
						if (!czyjest)
							continue;
						else {
							// elem.wyswietl();
							// System.out.println("tyle ma i: "
							// + elem.getNumer_zdarzenia()
							// + " a taki size; " + wierzcholek.size());
							elem.setNajwczesniejszy_moment(Collections
									.max(najwczsniejsze_czasy));
							najwczsniejsze_czasy.clear();
							// elem.wyswietl();
						}

					}
				}
				// teraz tu tak poprawic ITERATOREM OD KONCA ISC

				/**
				 * @todo sdfs
				 * 
				 * 
				 */
			/*	ListIterator iter = wierzcholek
						.listIterator(wierzcholek.size());
				while (iter.hasPrevious()) {
					boolean czyjest = false;
					Vertex elem = (Vertex) iter.previous();
					if (elem.getNumer_zdarzenia() == numer) {
						elem.setNajpozniejszy_moment(elem
								.getNajwczesniejszy_moment());
						elem.setZapas_czasu(elem.getNajpozniejszy_moment()
								- elem.getNajwczesniejszy_moment());
					} else {
						ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {

							if (x.getBegin().getNumer_zdarzenia() == elem
									.getNumer_zdarzenia()) {
								najpozniejsze_czasy.add(wierzcholek.get(
										x.getEnd().getNumer_zdarzenia() - 1)
										.getNajpozniejszy_moment()
										- x.getWeight());
								czyjest = true;
							}
						}
						if (!czyjest)
							continue;
						else {
							elem.setNajpozniejszy_moment((Collections
									.min(najpozniejsze_czasy)));
							elem.setZapas_czasu(elem.getNajpozniejszy_moment()
									- elem.getNajwczesniejszy_moment());
							najpozniejsze_czasy.clear();
						}
					}
				}

				/* wyswietlanie */

				// for(int i =0;i<krawedz.size();i++) {
				// krawedz.get(i).wyswietl();
				// }

			/*	for (int i = 0; i < wierzcholek.size(); i++) {
					wierzcholek.get(i).czyKrytyczna();

					// System.out.println("Werzcholek: " +
					// wierzcholek.get(i).getNumer_zdarzenia() +
					// " najwcz_czas: " +
					// wierzcholek.get(i).getNajwczesniejszy_moment() +
					// " najpoz_czas: " +
					// wierzcholek.get(i).getNajpozniejszy_moment() +
					// " luz_czas: " + wierzcholek.get(i).getZapas_czasu());
				}
				krytyczna_sciezka = "";
				max = "";
				czas_trwania_przeds = 0;
				numer_poczatku_spr = 0;
				for (int i = 0; i < krawedz.size(); i++) {
					if (krawedz.get(i).getBegin().isCzy_krytyczna()
							&& krawedz.get(i).getEnd().isCzy_krytyczna()
							&& !krawedz.get(i).getBegin().isCzy_polaczaona()) {
						max = krawedz.get(i).getNazwa();
						// System.out.println("Sprawdzam " + max +
						// " w zdarzeniu poczatkowym " +
						// krawedz.get(i).getBegin().getNumer_zdarzenia());
						// for(Edge x: krawedz)
						for (numer_poczatku_spr = krawedz.get(i).getBegin()
								.getNumer_zdarzenia(); numer_poczatku_spr < krawedz
								.size(); numer_poczatku_spr++) {
							Edge x = krawedz.get(numer_poczatku_spr);
							if (x.getBegin().getNumer_zdarzenia() == krawedz
									.get(i).getBegin().getNumer_zdarzenia()
									&& x.getNazwa() != krawedz.get(i)
											.getNazwa()
									&& x.getEnd().isCzy_krytyczna()) {
								if (x.getWeight() > krawedz.get(i).getWeight()) {
									// System.out.println("Usuwam " + max +
									// " bedac przy krawedzi " +
									// krawedz.get(i).getNazwa());
									// System.out.println("bo sprawdzany  " +
									// x.getNazwa() + " jest rowny " +
									// krawedz.get(i).getNazwa() +
									// " pierwszy ma wiekszy waga od drugiego");
									max = "";
								}
								// else
								// System.out.println("Sprawdzany  " +
								// x.getNazwa() + " laczy sie z tym samym " +
								// krawedz.get(i).getNazwa() +
								// " pierwszy ma mniejsz waga od drugiego");

							}

						}
						if (max != "") {
							// System.out.println("Dodaje " + max);
							czas_trwania_przeds += krawedz.get(i).getWeight();
							krawedz.get(i).getBegin().setCzy_polaczaona(true);
							krytyczna_sciezka += " -> " + max;// +krawedz.get(i).getNazwa();
						}
					}

				}

				frame = null;
				wyjscie = "Sciezka Krytyczna to: " + krytyczna_sciezka
						+ " Minimalny czas trwania: " + czas_trwania_przeds;
				JOptionPane.showMessageDialog(frame, wyjscie,
						"Wynik dzialania CPM", JOptionPane.PLAIN_MESSAGE);

				break;
*/
			
			/**
			 * Przepisanie danych z magazynu do klas Edge i Vertex
			 */
			it = magazyn2.iterator();
			 krawedz = new ArrayList<>();
			wierzcholek = new ArrayList<>();
			
			ArrayList<Integer> zdarzenia=new ArrayList<>();
			zdarzenia.add(1);
			
			for(int i =0;i<magazyn2.size();i++)
			{
				if(!zdarzenia.contains(magazyn2.get(i).getKoniec()))
				{
				zdarzenia.add(magazyn2.get(i).getKoniec());			
				
				}
				
			}
			
			for (int i = 0; i < zdarzenia.size(); i++) {
				wierzcholek.add(new Vertex(i + 1, 0, 0, 0));
			}

			while (it.hasNext()) {
				Magazyn element = it.next();

				krawedz.add(new Edge(element.getNazwa_czyn(), wierzcholek
						.get(element.getPocz() - 1), wierzcholek
						.get(element.getKoniec() - 1), element
						.getCzas_trwania()));

			}
			/* Uzupelnianie wierzcholkow */
			for (int i = 0; i < wierzcholek.size(); i++) {
				if (wierzcholek.get(i).getNumer_zdarzenia() == 1) {
					wierzcholek.get(i).setNajwczesniejszy_moment(0);
					wierzcholek.get(i).setZapas_czasu(0);
					wierzcholek.get(i).setNajpozniejszy_moment(0);
				} else {

					ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
					for (Edge x : krawedz) {

						if (x.getEnd().getNumer_zdarzenia() == wierzcholek
								.get(i).getNumer_zdarzenia()) {
							najwczsniejsze_czasy.add(wierzcholek.get(
									x.getBegin().getNumer_zdarzenia() - 1)
									.getNajwczesniejszy_moment()
									+ x.getWeight());
						}
					}
					wierzcholek.get(i).setNajwczesniejszy_moment(
							Collections.max(najwczsniejsze_czasy));
					najwczsniejsze_czasy.clear();

				}
			}
			for (int i = wierzcholek.size(); i > 0; i--) {
				if (i == wierzcholek.size()) {
					wierzcholek.get(i - 1).setNajpozniejszy_moment(
							wierzcholek.get(i - 1)
									.getNajwczesniejszy_moment());
					wierzcholek.get(i - 1).setZapas_czasu(
							wierzcholek.get(i - 1)
									.getNajpozniejszy_moment()
									- wierzcholek.get(i - 1)
											.getNajwczesniejszy_moment());
				} else {
					ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
					for (Edge x : krawedz) {

						if (x.getBegin().getNumer_zdarzenia() == wierzcholek
								.get(i - 1).getNumer_zdarzenia()) {
							najpozniejsze_czasy.add(wierzcholek.get(
									x.getEnd().getNumer_zdarzenia() - 1)
									.getNajpozniejszy_moment()
									- x.getWeight());
						}
					}
					wierzcholek.get(i - 1).setNajpozniejszy_moment(
							(Collections.min(najpozniejsze_czasy)));
					wierzcholek.get(i - 1).setZapas_czasu(
							wierzcholek.get(i - 1)
									.getNajpozniejszy_moment()
									- wierzcholek.get(i - 1)
											.getNajwczesniejszy_moment());
					najpozniejsze_czasy.clear();
				}
			}

			/* wyswietlanie */
			/*
			 * for(int i =0;i<krawedz.size();i++) {
			 * krawedz.get(i).wyswietl(); }
			 */

			for (int i = 0; i < wierzcholek.size(); i++) {
				wierzcholek.get(i).czyKrytyczna();

				// System.out.println("Werzcholek: " +
				// wierzcholek.get(i).getNumer_zdarzenia() +
				// " najwcz_czas: " +
				// wierzcholek.get(i).getNajwczesniejszy_moment() +
				// " najpoz_czas: " +
				// wierzcholek.get(i).getNajpozniejszy_moment() +
				// " luz_czas: " + wierzcholek.get(i).getZapas_czasu());
			}
			 krytyczna_sciezka = "";
			 max = "";
			 czas_trwania_przeds = 0;
			 numer_poczatku_spr = 0;
			for (int i = 0; i < krawedz.size(); i++) {
				if (krawedz.get(i).getBegin().isCzy_krytyczna()
						&& krawedz.get(i).getEnd().isCzy_krytyczna()
						&& !krawedz.get(i).getBegin().isCzy_polaczaona()) {
					max = krawedz.get(i).getNazwa();
					// System.out.println("Sprawdzam " + max +
					// " w zdarzeniu poczatkowym " +
					// krawedz.get(i).getBegin().getNumer_zdarzenia());
					// for(Edge x: krawedz)
					for (numer_poczatku_spr = krawedz.get(i).getBegin()
							.getNumer_zdarzenia(); numer_poczatku_spr < krawedz
							.size(); numer_poczatku_spr++) {
						Edge x = krawedz.get(numer_poczatku_spr);
						if (x.getBegin().getNumer_zdarzenia() == krawedz
								.get(i).getBegin().getNumer_zdarzenia()
								&& x.getNazwa() != krawedz.get(i)
										.getNazwa()
								&& x.getEnd().isCzy_krytyczna()) {
							if (x.getWeight() > krawedz.get(i).getWeight()) {
								// System.out.println("Usuwam " + max +
								// " bedac przy krawedzi " +
								// krawedz.get(i).getNazwa());
								// System.out.println("bo sprawdzany  " +
								// x.getNazwa() + " jest rowny " +
								// krawedz.get(i).getNazwa() +
								// " pierwszy ma wiekszy waga od drugiego");
								max = "";
							}
							// else
							// System.out.println("Sprawdzany  " +
							// x.getNazwa() + " laczy sie z tym samym " +
							// krawedz.get(i).getNazwa() +
							// " pierwszy ma mniejsz waga od drugiego");

						}

					}
					if (max != "") {
						// System.out.println("Dodaje " + max);
						czas_trwania_przeds += krawedz.get(i).getWeight();
						krawedz.get(i).getBegin().setCzy_polaczaona(true);
						krytyczna_sciezka += " -> " + max;// +krawedz.get(i).getNazwa();
					}
				}

			}

			frame = null;
			 wyjscie = "Sciezka Krytyczna to: " + krytyczna_sciezka
					+ " Minimalny czas trwania: " + czas_trwania_przeds;
			JOptionPane.showMessageDialog(frame, wyjscie,
					"Wynik dzialania CPM", JOptionPane.PLAIN_MESSAGE);

			break;
			default:
				// input2.close();

				System.exit(1);

			}
			break;
		case 2:
			/***** PERT ******/
			wejscie = JOptionPane
					.showInputDialog("Plik z nastepstwem zdarzen (1), plik z poprzednikiem (2), inna wartosc zamyka program.");
			if (wejscie == null)
				wejscie = "3";
			liczba2 = Integer.parseInt(wejscie);

			nazwa_czyn = null;
			czas_trwania = 0;
			pocz = 0;
			koniec = 0;
			polacz = null;
			;

			switch (liczba2) {
			case 1:
				/***** Nastepstwo zdarzen ******/

				fd = new FileDialog(a, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				plik = fd.getFile();

				char nazwa = 'A';
				int ile_wierzch = 0;
				int tc,
				tm,
				tp;

				odczyt = new Scanner(new File(plik));
				ArrayList<Magazyn> magazyn = new ArrayList<>();
				while (odczyt.hasNext()) {
					nazwa_czyn = Character.toString(nazwa);
					nazwa++;

					polacz = odczyt.next();
					pocz = Integer.parseInt(polacz.substring(1, 2));
					koniec = Integer.parseInt(polacz.substring(3, 4));
					tc = Integer.parseInt(odczyt.next());
					tm = Integer.parseInt(odczyt.next());
					tp = Integer.parseInt(odczyt.next());
					czas_trwania = (tc + 4 * tm + tp) / 6;

					if (ile_wierzch < koniec) {
						ile_wierzch = koniec;
					}
					magazyn.add(new Magazyn(nazwa_czyn, czas_trwania, pocz, tc,
							tm, tp, koniec));// przy Pert troche wiecej
												// dodawania
				}
				odczyt.close();
				Iterator<Magazyn> it = magazyn.iterator();

				/**
				 * Przepisanie danych z magazynu do klas Edge i Vertex
				 */
				it = magazyn.iterator();
				ArrayList<Edge> krawedz = new ArrayList<>();
				ArrayList<Vertex> wierzcholek = new ArrayList<>();
				for (int i = 0; i < ile_wierzch; i++) {
					wierzcholek.add(new Vertex(i + 1, 0, 0, 0));
				}

				while (it.hasNext()) {
					Magazyn element = it.next();

					krawedz.add(new Edge(element.getNazwa_czyn(), wierzcholek
							.get(element.getPocz() - 1), wierzcholek
							.get(element.getKoniec() - 1), element
							.getCzas_trwania(), element.getTc(), element
							.getTm(), element.getTp()));

				}
				/* Uzupelnianie wierzcholkow */
				for (int i = 0; i < wierzcholek.size(); i++) {
					if (wierzcholek.get(i).getNumer_zdarzenia() == 1) {
						wierzcholek.get(i).setNajwczesniejszy_moment(0);
						wierzcholek.get(i).setZapas_czasu(0);
						wierzcholek.get(i).setNajpozniejszy_moment(0);
					} else {

						ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {

							if (x.getEnd().getNumer_zdarzenia() == wierzcholek
									.get(i).getNumer_zdarzenia()) {
								najwczsniejsze_czasy.add(wierzcholek.get(
										x.getBegin().getNumer_zdarzenia() - 1)
										.getNajwczesniejszy_moment()
										+ x.getWeight());
							}
						}
						wierzcholek.get(i).setNajwczesniejszy_moment(
								Collections.max(najwczsniejsze_czasy));
						najwczsniejsze_czasy.clear();

					}
				}
				for (int i = wierzcholek.size(); i > 0; i--) {
					if (i == wierzcholek.size()) {
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								wierzcholek.get(i - 1)
										.getNajwczesniejszy_moment());
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
					} else {
						ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
						for (Edge x : krawedz) {

							if (x.getBegin().getNumer_zdarzenia() == wierzcholek
									.get(i - 1).getNumer_zdarzenia()) {
								najpozniejsze_czasy.add(wierzcholek.get(
										x.getEnd().getNumer_zdarzenia() - 1)
										.getNajpozniejszy_moment()
										- x.getWeight());
							}
						}
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								(Collections.min(najpozniejsze_czasy)));
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
						najpozniejsze_czasy.clear();
					}
				}

				/* wyswietlanie */
				/*
				 * for(int i =0;i<krawedz.size();i++) {
				 * krawedz.get(i).wyswietl(); }
				 */

				for (int i = 0; i < wierzcholek.size(); i++) {
					wierzcholek.get(i).czyKrytyczna();

					// System.out.println("Werzcholek: " +
					// wierzcholek.get(i).getNumer_zdarzenia() +
					// " najwcz_czas: " +
					// wierzcholek.get(i).getNajwczesniejszy_moment() +
					// " najpoz_czas: " +
					// wierzcholek.get(i).getNajpozniejszy_moment() +
					// " luz_czas: " + wierzcholek.get(i).getZapas_czasu());
				}
				/**
				 * Tu tworze tablice w ktorej przechowuje sciezke krytyczna po
				 * krawedziach
				 **/
				ArrayList<Edge> sciezka_krytyczna = new ArrayList<>();
				/***/

				String krytyczna_sciezka = "";
				String max = "";
				int czas_trwania_przeds = 0;
				int numer_poczatku_spr = 0;
				for (int i = 0; i < krawedz.size(); i++) {
					if (krawedz.get(i).getBegin().isCzy_krytyczna()
							&& krawedz.get(i).getEnd().isCzy_krytyczna()
							&& !krawedz.get(i).getBegin().isCzy_polaczaona()) {
						max = krawedz.get(i).getNazwa();
						// System.out.println("Sprawdzam " + max +
						// " w zdarzeniu poczatkowym " +
						// krawedz.get(i).getBegin().getNumer_zdarzenia());
						// for(Edge x: krawedz)
						for (numer_poczatku_spr = krawedz.get(i).getBegin()
								.getNumer_zdarzenia(); numer_poczatku_spr < krawedz
								.size(); numer_poczatku_spr++) {
							Edge x = krawedz.get(numer_poczatku_spr);
							if (x.getBegin().getNumer_zdarzenia() == krawedz
									.get(i).getBegin().getNumer_zdarzenia()
									&& x.getNazwa() != krawedz.get(i)
											.getNazwa()
									&& x.getEnd().isCzy_krytyczna()) {
								if (x.getWeight() > krawedz.get(i).getWeight()) {
									// System.out.println("Usuwam " + max +
									// " bedac przy krawedzi " +
									// krawedz.get(i).getNazwa());
									// System.out.println("bo sprawdzany  " +
									// x.getNazwa() + " jest rowny " +
									// krawedz.get(i).getNazwa() +
									// " pierwszy ma wiekszy waga od drugiego");
									max = "";
								}
								// else
								// System.out.println("Sprawdzany  " +
								// x.getNazwa() + " laczy sie z tym samym " +
								// krawedz.get(i).getNazwa() +
								// " pierwszy ma mniejsz waga od drugiego");

							}

						}
						if (max != "") {
							// System.out.println("Dodaje " + max);
							czas_trwania_przeds += krawedz.get(i).getWeight();
							krawedz.get(i).getBegin().setCzy_polaczaona(true);
							krawedz.get(i).setKrytyczna(true);
							sciezka_krytyczna.add(krawedz.get(i));
							krytyczna_sciezka += " -> " + max;// +krawedz.get(i).getNazwa();
						}
					}

				}
				double war_tr = 0.0;// suma wariancji
				for (Edge x : sciezka_krytyczna) {
					x.setWariancja();
					/* System.out.println("krawedz " +
					 x.getBegin().getNumer_zdarzenia() + "-" +
					 x.getEnd().getNumer_zdarzenia() + " wariancja: " +
					 x.getWariancja());*/
					war_tr += x.getWariancja();
				}
				wejscie = JOptionPane
						.showInputDialog("Dla jakiego czasu realizacji wyznaczyc prawdopodobienstwo?");
				if (wejscie == null)
					wejscie = "" + czas_trwania_przeds;
				liczba2 = Integer.parseInt(wejscie);
				double x;// dla tej wartosci szukamy prawdopodobienstwa w
							// tablicach
				x = ((liczba2 - czas_trwania_przeds) / Math.sqrt(war_tr));
				double prawdopodobienstwo = 0;
				NormalDistribution prawd = new NormalDistribution();

				prawdopodobienstwo = prawd.cumulativeProbability(x);

				/** Ucinanie zbednych miejsc po przecinku **/
				prawdopodobienstwo *= 1000;
				prawdopodobienstwo = Math.round(prawdopodobienstwo);
				prawdopodobienstwo /= 1000;
				/************************************************/
				Component frame = null;
				String wyjscie = "Sciezka Krytyczna to: "
						+ krytyczna_sciezka
						+ " Minimalny czas trwania: "
						+ czas_trwania_przeds
						+ " \nPrawdopodobienstwo realizacji przedsiewziecia w czasie: "
						+ liczba2 + " jest rowne: " + prawdopodobienstwo;
				JOptionPane.showMessageDialog(frame, wyjscie,
						"Wynik dzialania PERT", JOptionPane.PLAIN_MESSAGE);

				break;
			case 2:
				/***** Poprzednik ******/
				fd = new FileDialog(a, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				plik = fd.getFile();

				odczyt = new Scanner(new File(plik));
				ArrayList<MagazynPoprz> magazyn_poprz = new ArrayList<>();
				int numer = 1;
				while (odczyt.hasNext()) {
					nazwa_czyn = odczyt.next();
					tc = Integer.parseInt(odczyt.next());
					tm = Integer.parseInt(odczyt.next());
					tp = Integer.parseInt(odczyt.next());
					czas_trwania = (tc + 4 * tm + tp) / 6;					
					
				
					polacz = odczyt.next();
					String czynnosc_poprzedzajaca;

					for (int i = 0; i < polacz.length(); i++) {
						czynnosc_poprzedzajaca = polacz.substring(i, i + 1);
						if (czynnosc_poprzedzajaca.equals(",")) {

							continue;
						} else {
							MagazynPoprz magazyn1 = new MagazynPoprz();
							magazyn1.setCzas_trwania(czas_trwania);
							magazyn1.setNazwa_czyn(nazwa_czyn);
							magazyn1.setCzynn_poprzedz(czynnosc_poprzedzajaca);
							magazyn1.setNumer(numer);
							magazyn1.setTc(tc);
							magazyn1.setTm(tm);
							magazyn1.setTp(tp);
							magazyn_poprz.add(magazyn1);

						}

					}

					numer++;
				}
				odczyt.close();
				
				Iterator<MagazynPoprz> ite = magazyn_poprz.iterator();
				ArrayList<Magazyn> magazyn2 = new ArrayList<>();
				int iteracja = 0;
				while (ite.hasNext()) {
					MagazynPoprz element = ite.next();
					if (element.getCzynn_poprzedz().equals("-")) {

						magazyn2.add(new Magazyn(element.getNazwa_czyn(), element.getCzas_trwania(), 1, element.getTc(), element.getTm(), element.getTp(), szukaj_konca(
								magazyn_poprz, element.getNazwa_czyn(),
								numer)));
					} else if(!czy_zawiera(magazyn2,element)) {
						iteracja++;
						magazyn2.add(new Magazyn(element.getNazwa_czyn(), element.getCzas_trwania(), szukaj_pocz(
								magazyn2, element.getCzynn_poprzedz()), element.getTc(), element.getTm(), element.getTp(), szukaj_konca(magazyn_poprz,
										element.getNazwa_czyn(), numer)));
					
					}

				}

				
				it = magazyn2.iterator();
		//	System.out.println("********************");
		/*	while(it.hasNext())
			{
				Magazyn obj = it.next();
				System.out.println("Nazwa: " + obj.getNazwa_czyn() + " nastepstwo: "+obj.getPocz()+"-"+obj.getKoniec()); 
			}
			System.out.println("*******************");*/
			magazyn2=numeracja(magazyn2);
				/**
				 * Przepisanie danych z magazynu do klas Edge i Vertex
				 */
				it = magazyn2.iterator();
				krawedz = new ArrayList<>();
				 wierzcholek = new ArrayList<>();
				 
				 ArrayList<Integer> zdarzenia=new ArrayList<>();
					zdarzenia.add(1);
					
					for(int i =0;i<magazyn2.size();i++)
					{
						if(!zdarzenia.contains(magazyn2.get(i).getKoniec()))
						{
						zdarzenia.add(magazyn2.get(i).getKoniec());			
						
						}
						
					}
					
					for (int i = 0; i < zdarzenia.size(); i++)
			 {
					wierzcholek.add(new Vertex(i + 1, 0, 0, 0));
				}

				while (it.hasNext()) {
					Magazyn element = it.next();

					krawedz.add(new Edge(element.getNazwa_czyn(), wierzcholek
							.get(element.getPocz() - 1), wierzcholek
							.get(element.getKoniec() - 1), element
							.getCzas_trwania(), element.getTc(), element
							.getTm(), element.getTp()));

				}
				/* Uzupelnianie wierzcholkow */
				for (int i = 0; i < wierzcholek.size(); i++) {
					if (wierzcholek.get(i).getNumer_zdarzenia() == 1) {
						wierzcholek.get(i).setNajwczesniejszy_moment(0);
						wierzcholek.get(i).setZapas_czasu(0);
						wierzcholek.get(i).setNajpozniejszy_moment(0);
					} else {

						ArrayList<Integer> najwczsniejsze_czasy = new ArrayList<>();
						for (Edge z : krawedz) {

							if (z.getEnd().getNumer_zdarzenia() == wierzcholek
									.get(i).getNumer_zdarzenia()) {
								najwczsniejsze_czasy.add(wierzcholek.get(
										z.getBegin().getNumer_zdarzenia() - 1)
										.getNajwczesniejszy_moment()
										+ z.getWeight());
							}
						}
						wierzcholek.get(i).setNajwczesniejszy_moment(
								Collections.max(najwczsniejsze_czasy));
						najwczsniejsze_czasy.clear();

					}
				}
				for (int i = wierzcholek.size(); i > 0; i--) {
					if (i == wierzcholek.size()) {
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								wierzcholek.get(i - 1)
										.getNajwczesniejszy_moment());
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
					} else {
						ArrayList<Integer> najpozniejsze_czasy = new ArrayList<>();
						for (Edge z : krawedz) {

							if (z.getBegin().getNumer_zdarzenia() == wierzcholek
									.get(i - 1).getNumer_zdarzenia()) {
								najpozniejsze_czasy.add(wierzcholek.get(
										z.getEnd().getNumer_zdarzenia() - 1)
										.getNajpozniejszy_moment()
										- z.getWeight());
							}
						}
						wierzcholek.get(i - 1).setNajpozniejszy_moment(
								(Collections.min(najpozniejsze_czasy)));
						wierzcholek.get(i - 1).setZapas_czasu(
								wierzcholek.get(i - 1)
										.getNajpozniejszy_moment()
										- wierzcholek.get(i - 1)
												.getNajwczesniejszy_moment());
						najpozniejsze_czasy.clear();
					}
				}

				/* wyswietlanie */
				
			/*	 for(int i =0;i<krawedz.size();i++) {
				 krawedz.get(i).wyswietl(); }
				*/

				for (int i = 0; i < wierzcholek.size(); i++) {
					wierzcholek.get(i).czyKrytyczna();

				/*	 System.out.println("Werzcholek: " +
					 wierzcholek.get(i).getNumer_zdarzenia() +
					 " najwcz_czas: " +
					 wierzcholek.get(i).getNajwczesniejszy_moment() +
					 " najpoz_czas: " +
					 wierzcholek.get(i).getNajpozniejszy_moment() +
					 " luz_czas: " + wierzcholek.get(i).getZapas_czasu());*/
				}
				/**
				 * Tu tworze tablice w ktorej przechowuje sciezke krytyczna po
				 * krawedziach
				 **/
				sciezka_krytyczna  = new ArrayList<>();
				/***/

				 krytyczna_sciezka = "";
				 max = "";
				 czas_trwania_przeds = 0;
				 numer_poczatku_spr = 0;
				for (int i = 0; i < krawedz.size(); i++) {
					if (krawedz.get(i).getBegin().isCzy_krytyczna()
							&& krawedz.get(i).getEnd().isCzy_krytyczna()
							&& !krawedz.get(i).getBegin().isCzy_polaczaona()) {
						max = krawedz.get(i).getNazwa();
						// System.out.println("Sprawdzam " + max +
						// " w zdarzeniu poczatkowym " +
						// krawedz.get(i).getBegin().getNumer_zdarzenia());
						// for(Edge x: krawedz)
						for (numer_poczatku_spr = krawedz.get(i).getBegin()
								.getNumer_zdarzenia(); numer_poczatku_spr < krawedz
								.size(); numer_poczatku_spr++) {
							Edge z = krawedz.get(numer_poczatku_spr);
							if (z.getBegin().getNumer_zdarzenia() == krawedz
									.get(i).getBegin().getNumer_zdarzenia()
									&& z.getNazwa() != krawedz.get(i)
											.getNazwa()
									&& z.getEnd().isCzy_krytyczna()) {
								if (z.getWeight() > krawedz.get(i).getWeight()) {
									// System.out.println("Usuwam " + max +
									// " bedac przy krawedzi " +
									// krawedz.get(i).getNazwa());
									// System.out.println("bo sprawdzany  " +
									// x.getNazwa() + " jest rowny " +
									// krawedz.get(i).getNazwa() +
									// " pierwszy ma wiekszy waga od drugiego");
									max = "";
								}
								// else
								// System.out.println("Sprawdzany  " +
								// x.getNazwa() + " laczy sie z tym samym " +
								// krawedz.get(i).getNazwa() +
								// " pierwszy ma mniejsz waga od drugiego");

							}

						}
						if (max != "") {
							// System.out.println("Dodaje " + max);
							czas_trwania_przeds += krawedz.get(i).getWeight();
							krawedz.get(i).getBegin().setCzy_polaczaona(true);
							krawedz.get(i).setKrytyczna(true);
							sciezka_krytyczna.add(krawedz.get(i));
							krytyczna_sciezka += " -> " + max;// +krawedz.get(i).getNazwa();
						}
					}

				}
				 war_tr = 0.0;// suma wariancji
				for (Edge z : sciezka_krytyczna) {
					z.setWariancja();
				/*	 System.out.println("krawedz " +
					 z.getBegin().getNumer_zdarzenia() + "-" +
					 z.getEnd().getNumer_zdarzenia() + " wariancja: " +
					 z.getWariancja());*/
					war_tr += z.getWariancja();
				}
				wejscie = JOptionPane
						.showInputDialog("Dla jakiego czasu realizacji wyznaczyc prawdopodobienstwo?");
				if (wejscie == null)
					wejscie = "" + czas_trwania_przeds;
				liczba2 = Integer.parseInt(wejscie);
				 // dla tej wartosci szukamy prawdopodobienstwa w
							// tablicach
				x = ((liczba2 - czas_trwania_przeds) / Math.sqrt(war_tr));
				 prawdopodobienstwo = 0;
				NormalDistribution prawd2 = new NormalDistribution();

				prawdopodobienstwo = prawd2.cumulativeProbability(x);

				/** Ucinanie zbednych miejsc po przecinku **/
				prawdopodobienstwo *= 1000;
				prawdopodobienstwo = Math.round(prawdopodobienstwo);
				prawdopodobienstwo /= 1000;
				/************************************************/
				 frame = null;
				 wyjscie = "Sciezka Krytyczna to: "
						+ krytyczna_sciezka
						+ " Minimalny czas trwania: "
						+ czas_trwania_przeds
						+ " \nPrawdopodobienstwo realizacji przedsiewziecia w czasie: "
						+ liczba2 + " jest rowne: " + prawdopodobienstwo;
				JOptionPane.showMessageDialog(frame, wyjscie,
						"Wynik dzialania PERT", JOptionPane.PLAIN_MESSAGE);

				break;
			default:
				// input.close();

				System.exit(1);
			}

		}

	}
}
