package org.mql.java.models;

public class Voiture {

	private String model, marque, matricule, coleur;
	private int annee;
	private boolean isAutomatique;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getColeur() {
		return coleur;
	}
	public void setColeur(String coleur) {
		this.coleur = coleur;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public boolean isAutomatique() {
		return isAutomatique;
	}
	public void setAutomatique(boolean isAutomatique) {
		this.isAutomatique = isAutomatique;
	}
	@Override
	public String toString() {
		return "Voiture [model=" + model + ", marque=" + marque + ", matricule=" + matricule + ", coleur=" + coleur
				+ ", annee=" + annee + ", isAutomatique=" + isAutomatique + "]";
	}
}
