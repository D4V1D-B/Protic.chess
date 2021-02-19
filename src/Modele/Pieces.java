package Modele;

public class Pieces {

	private String nom;
	private int valeur = 0;
	//si la pièce est morte
	private boolean morte = false;
	//si la pièce met le roi en échec. 
	private boolean echec = false;
	// vrai = blanc
	private boolean couleur = true;
	private int[][] emplacement;

	public Pieces(String nom, boolean couleur) {
		setNom(nom);
		setCouleur(couleur);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public boolean isWhite() {
		return couleur;
	}

	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}

	
}
