package Modele;

import java.awt.Point;
import java.util.ArrayList;

public class Pion extends Pieces
{

	private boolean aBouger;

	private ArrayList<Point> mouvementPossible;

	public Pion(String nom, boolean couleur, Point emplacement)
	{
		super(nom, couleur, emplacement);
		setaBouger(false);
	}

	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger(boolean aBouger)
	{
		this.aBouger = aBouger;
	}

	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> mouvementPossible)
	{
		mouvementPossible.clear();
		//
		for (int i = 0; i <= 7; i++)
		{
			if (plateau[i][this.getEmplacement().y].equals(null))
			{

			}
		}

	}
}
