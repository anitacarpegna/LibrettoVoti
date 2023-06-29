package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class Voto{
	private String nomeCorso;
	private int votoOttenuto; //da 18 a 31
	private LocalDate dataEsame; //LocalDate oggetto per gestire date temporali
	
	public Voto(String nomeCorso, int votoOttenuto, LocalDate dataEsame) {
		this.nomeCorso = nomeCorso;
		this.votoOttenuto = votoOttenuto;
		this.dataEsame = dataEsame;
	}
	
	//copy constructor di voti
	public Voto(Voto v) {
		this.nomeCorso = v.nomeCorso;
		this.votoOttenuto = v.votoOttenuto;
		this.dataEsame = v.dataEsame;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public int getVotoOttenuto() {
		return votoOttenuto;
	}

	public void setVotoOttenuto(int votoOttenuto) {
		this.votoOttenuto = votoOttenuto;
	}

	public LocalDate getDataEsame() {
		return dataEsame;
	}

	public void setDataEsame(LocalDate dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	public boolean isDuplicato(Voto altro) {
		return this.getNomeCorso().equals(altro.getNomeCorso()) && this.getVotoOttenuto() == altro.getVotoOttenuto();
	}
	
	public boolean isConflitto(Voto altro) {
		return this.getNomeCorso().equals(altro.getNomeCorso()) && this.getVotoOttenuto() != altro.getVotoOttenuto();
	}
	
	//metodo per copiare gli oggetti
	//copy constructor = alternativa al clone
	public Voto clone() {
		return new Voto(this.nomeCorso, this.votoOttenuto, this.dataEsame);
	}
	@Override
	public String toString() {
		return nomeCorso + " (" + this.votoOttenuto + " punti) il "+ this.dataEsame;
	}


	
	
	
	
	

}
