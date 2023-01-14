package org.mql.java.models;

public class Voiture {

	private String model, marque, matricule, coleur,annee,isAutomatique;

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
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String isAutomatique() {
		return isAutomatique;
	}
	public void setAutomatique(String isAutomatique) {
		this.isAutomatique = isAutomatique;
	}
	@Override
	public String toString() {
		return "Voiture [model=" + model + ", marque=" + marque + ", matricule=" + matricule + ", coleur=" + coleur
				+ ", annee=" + annee + ", isAutomatique=" + isAutomatique + "]";
	}
}
