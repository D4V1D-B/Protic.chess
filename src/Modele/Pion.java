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
			ArrayList<Point> positionEnnemie)
	{
		mouvementPossible.clear();
		if (super.isWhite())
		{
			if (aBouger)
			{
				mouvementPossible.add(new Point(this.getEmplacement().x,
						this.getEmplacement().y - 2));
				setaBouger(true);
			}
			else
			{
				if (positionEnnemie
						.equals(plateau[this.getEmplacement().x
								- 1][this.getEmplacement().y - 1])
						|| positionEnnemie
								.equals(plateau[this.getEmplacement().x
										- 1][this.getEmplacement().y + 1]))
				{

				}
				mouvementPossible.add(new Point(this.getEmplacement().x,
						this.getEmplacement().y - 1));
			}

		}
		else
		{

		}

	}
}
