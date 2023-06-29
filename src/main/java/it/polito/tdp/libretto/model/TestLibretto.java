package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {
		//creo una lista di 10 voti a piacere per testare se funziona il metodo add (noi ne mettiamo 2)
		Libretto lib = new Libretto();
		lib.add(new Voto("Analisi 1", 29, LocalDate.of(2021, 2, 15)));
		lib.add(new Voto("Fisica 2", 21, LocalDate.of(2022, 6, 10)));
		lib.add(new Voto("Informatica", 25, LocalDate.of(2021, 7, 1)));
		System.out.println("\nTutti i corsi");
		lib.stampa();
		
		System.out.println("\nCorsi con voto 25");
		lib.stampaPuntiUgualiA(25);
		
		System.out.println("\nVoti di Analisi 1");
		Voto v = lib.cercaVotoPerNome("Analisi 1");
		System.out.println(v);
		
		System.out.println("\nVoti di Analisi 2");
		v = lib.cercaVotoPerNome("Analisi 2");
		System.out.println(v);
		
		System.out.println("\nIl voto cercato esiste già?");	
		Voto a1bis = new Voto("Analisi 1", 29, LocalDate.of(2025, 2, 15));
		Voto a1ter = new Voto("Analisi 1", 30, LocalDate.of(2025, 2, 15));
		lib.add(new Voto ("Analisi 2,", 29, LocalDate.of(2021, 2, 15)));
		System.out.println(a1bis+" è duplicato: " + lib.eststeVotoDuplicato(a1bis));
		System.out.println(a1ter+" è duplicato: " + lib.eststeVotoDuplicato(a1ter));
		
		System.out.println("\nL'esame esite già con un altro voto?");
		System.out.println(a1bis+" presenta un coflitto: " + lib.esisteConflitto(a1bis));
		System.out.println(a1ter+" presenta un coflitto: " + lib.esisteConflitto(a1ter));
		
		try {
			lib.add(new Voto ("Informatica",25,LocalDate.of(2023, 7, 10)));
		} catch (IllegalArgumentException e) {
			System.out.println("Errore nell'inserimento voto.");
			System.out.println(e.getMessage());
		}
		
		Libretto migliore = lib.librettoMigliorato();
		System.out.println("\nLibretto migliorato");
		migliore.stampa();
		System.out.println("\nLibretto originario");
		lib.stampa();
		
		System.out.println("\nLibretto ordine alfabetico");
		Libretto ordinatoa = lib.librettoOrdinatoAlfabeticamente();
		ordinatoa.stampa();
		
		System.out.println("\nLibretto ordine voto");
		Libretto ordinatov = lib.librettoOrdinatoPerVoto();
		ordinatov.stampa();
		
	}

}
