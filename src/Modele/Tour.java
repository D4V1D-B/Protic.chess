package Modele;

import java.util.ArrayList;

public class Tour extends Pieces {

	private boolean aBouger;
	private ArrayList<String> mouvementPossible;

	public Tour(String nom, boolean couleur) {
		super(nom, couleur);
		aBouger = false;
	}

	public boolean isaBouger() {
		return aBouger;
	}

	public void setaBouger() {
		this.aBouger = true;
	}

	public ArrayList<String> getMouvementPossible() {
		return mouvementPossible;
	}

	//TODO 
	public void setMouvementPossible() {
		
	}

}
