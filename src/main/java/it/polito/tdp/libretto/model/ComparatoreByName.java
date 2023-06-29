package it.polito.tdp.libretto.model;

import java.util.Comparator;

public class ComparatoreByName implements Comparator <Voto>{

	@Override
	public int compare(Voto o1, Voto o2) {
		return o1.getNomeCorso().compareTo(o2.getNomeCorso());
	
	}

}
