package Modele;

import java.awt.Point;
import java.util.ArrayList;

public class Tour extends Pieces {

	private boolean aBouger;
	private ArrayList<Point> mouvementPossible;

	public Tour(String nom, boolean couleur, Point position) {
		super(nom, couleur, position);
		aBouger = false;
	}

	public boolean isaBouger() {
		return aBouger;
	}

	public void setaBouger() {
		this.aBouger = true;
	}

	public ArrayList<Point> getMouvementPossible() {
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau, ArrayList<Point> positionEnemie) {
		mouvementPossible.clear();
		// ajouter sur la ligne de la tour
		for (int j = this.getEmplacement().y - 1; j >= 0; j--) {
			if (plateau[this.getEmplacement().x][j] == null) {
				mouvementPossible.add(new Point(this.getEmplacement().x, j));
			} else {
				boolean estEnemie = false;
				j = -30;
				for (Point x : positionEnemie) {
					estEnemie = x == new Point(this.getEmplacement().x, j);
				}
				if (estEnemie)
					mouvementPossible.add(new Point(this.getEmplacement().x, j));
			}
		}
	}

}
