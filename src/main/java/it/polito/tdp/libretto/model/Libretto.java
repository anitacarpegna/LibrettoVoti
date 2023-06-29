package it.polito.tdp.libretto.model;

import java.util.*;

import it.polito.tdp.db.VotoDAO;

public class Libretto {
	
	private List <Voto> listaVoti; //ove possibile meglio usare l'interfaccia list e non arrayList o linkedList
								   //i metodi della classe non sa se stiamo usando i metodi di array o linked list

	public Libretto() {
		VotoDAO dao = new VotoDAO();
		this.listaVoti = dao.listVoti();
	}
	
	//metodo add per il caricamento degli esami
	public boolean add(Voto v) { //ritorno boolean perché l'add della lista ritorna boolean (rispetto meglio la delega)
		if (this.esisteConflitto(v) || this.eststeVotoDuplicato(v)) {
			//non aggiungere voto
			//System.out.println("Voto errato");
			throw new IllegalArgumentException("Voto errato: "+v);
			
		}
		VotoDAO dao = new VotoDAO();
		dao.createVoto(v);
		return this.listaVoti.add(v); //aggiunta alla lista senza alcun controllo
		//add alla lista in un metodo add = DELEGA 
		//DELEGA CIECA senza alcun controllo
	}
	/**
	 * Aggiungi un nuovo voto al libretto
	 * (per ora non fa nessun controllo)
	 * @param v il Voto da aggiungere
	 * @return true 
	 */
	
	//metodo per stampare i voti
	public void stampa() {
		for (Voto v: this.listaVoti) {
			System.out.println(v.toString());
		}
	}
	
	//metodo per stampare voti con 25
	public void stampaPuntiUgualiA(int valore) {
		for (Voto v: listaVoti) {
			if (v.getVotoOttenuto()==valore) {
				System.out.println(v);
			}
		}
	}
	
	//cercare voto dato nome corso
	public Voto cercaVotoPerNome(String corso) {
		for (Voto v: listaVoti) {
			if (v.getNomeCorso().equals(corso)) { 
			//meglio equals di compareTo per gli oggetti (compareTo presuppone che sia possibile ordinare gli oggetti (ciò non è senpre possibile)
				return v;
			}
		}
		//return null;
		//throw new RuntimeException("Voto non trovato"); 
		//bisogna scegliere tra le due opzioni: eccezione per eventi non prevedibili
		//il fatto che un metodo non trovi un dato che sta cercando è una cosa normale---> meglio null
		return null;
	}
	
	//metodo per vedere se esiste già una valutazione uguale di quell'esame
	//se il voto e il nome del corso sono uguali allora il voto esiste ---> true
	public boolean eststeVotoDuplicato(Voto nuovo) {
		for (Voto v: listaVoti) {
			//if (v.equalsCorsoPunti(nuovo)){ 
			//if(v.getNomeCorso().equals(nuovo.getNomeCorso() && v.getVotoOttenuto().equals(nuovo.getVotoOttenuto())){
			//esistono due modi per usare equals: o creo un metodo ad hoc oppure confronto direttamente gli attributi	
			//la scelta dipende da quante volte andrò ad usare il metodo
			//in questo caso scelgo quella che costa meno ovvero la seconda possibilità
			//se mi accorgo che nel codice vado a riscrivere la stessa istruzione aggiungo un metodo
			if(v.isDuplicato(nuovo)){
				return true;
			}
		}
		return false;
	}
	
	//metodo per vedere se c'è stesso esame con voto diverso
	public boolean esisteConflitto(Voto nuovo) {
		for (Voto v: listaVoti) {
			if (v.isConflitto(nuovo)) {
				return true;
			}
		}
		return false;
	}
	
	//metodi di FACTORY--> metodi che costruiscono un oggetto
	/**
	 * Metodo "factory" per creare un nuovo libretto con i voti migliorati
	 * @return
	 */
	public Libretto librettoMigliorato() {
		Libretto migliore = new Libretto();
		//LinkedList <Voti> listaNuova = new LKinkedList <Voti>(listaVoti);
		migliore.listaVoti = new ArrayList <> (this.listaVoti);
		//devo creare un deep clone non uno shallow clone
		//lo shallow clone è un clone superficiale ---> ho un contenitore distinto ma la reference è lo stesso oggetto
		//per creare un deep clone devo creare una copia anche degli elementi della lista
		//lo si fa col metodo clone di Voto
		for (Voto v: listaVoti) {
			//con clone
			migliore.listaVoti.add(v.clone());
			//con copy constructor
			//migliore.listaVoti.add(new Voto(v));
		}
		
		for (Voto v: migliore.listaVoti) {
			v.setVotoOttenuto(v.getVotoOttenuto()+2);
		}
		return migliore;
	}
	
	//metodo per cancellare i vori inferiori a 24
	public void cancellaVotiInferiori (int punti) {
		List<Voto> daCancellare = new ArrayList<Voto>();
		for(Voto v: this.listaVoti) {
			if (v.getVotoOttenuto() < punti) {
				//non si può rimuovere gli elementi durante il ciclo for
				//this.listaVoti.remove(v);
				daCancellare.add(v);
			}
		}
		
		
		listaVoti.removeAll(daCancellare);
		//oppure
//		for (Voto v1: daCancellare) {
//			this.listaVoti.remove(v1);
//		}
		
//		for (int i=0; i<this.listaVoti.size(); i++) {
//			if (this.listaVoti.get(i).getVotoOttenuto()<punti);
//				this.listaVoti.remove(i);
//		}
	}
	
	//metodo per satampare in ordine alfabetico 
	//voglio modificare l'ordine del libretto originale o solo la stampa?
	//creiamo duplicato da ordinare alfabeticamente
	public Libretto librettoOrdinatoAlfabeticamente() {
		Libretto ordinato = new Libretto();
		ordinato.listaVoti = new ArrayList<>(this.listaVoti);
		ordinato.listaVoti.sort(new ComparatoreByName());
		//oppure
		//Collections.sort(ordinato.listaVoti, new ComparatoreByName());
		return ordinato;
	}
	
		
	
	
	
	//metodo per stampare in ordine numerico decrescente di voto
	//voglio modificare l'ordine del libretto originale o solo la stampa?
	public Libretto librettoOrdinatoPerVoto() {
		Libretto ordinato = new Libretto();
		ordinato.listaVoti = new ArrayList<>(this.listaVoti);
		ordinato.listaVoti.sort(new Comparator<Voto>() {
			@Override
			public int compare(Voto o1, Voto o2) {
				
				return (o2.getVotoOttenuto()-o1.getVotoOttenuto());
			}});
		return ordinato;
	}
	
	public String toString() {
		String txt = "";
		for (Voto v: this.listaVoti) {
			txt += v.toString() + "\n";
		}
		return txt;
	}

	
	
	
	

	
}
