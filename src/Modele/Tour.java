package Modele;

import java.awt.Point;
import java.util.ArrayList;

public class Tour extends Pieces
{

	private boolean aBouger;
	private ArrayList<Point> mouvementPossible;

	public Tour(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
		aBouger = false;
	}

	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger()
	{
		this.aBouger = true;
	}

	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
	{
		mouvementPossible.clear();
		// ajouter sur la ligne de la tour à gauche
		for (int j = this.getEmplacement().y - 1; j >= 0; j--)
		{
			if (plateau[this.getEmplacement().x][j] == null)
			{
				mouvementPossible.add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(this.getEmplacement().x, j)))
						mouvementPossible.add(x);
				}

			}
		}

		// ajouter sur la ligne de la tour à droite
		for (int j = this.getEmplacement().y + 1; j <= 7; j++)
		{
			if (plateau[this.getEmplacement().x][j] == null)
			{
				mouvementPossible.add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				j = 30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(this.getEmplacement().x, j)))
						mouvementPossible.add(x);
				}
			}
		}

		// ajouter pour la ligne du bas
		for (int i = this.getEmplacement().x + 1; i <= 7; i++)
		{
			if (plateau[i][this.getEmplacement().y] == null)
			{
				mouvementPossible.add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				i = 30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, this.getEmplacement().y)))
						mouvementPossible.add(x);
				}
			}
		}

		// ajouter la ligne du haut

		for (int i = this.getEmplacement().x - 1; i >= 0; i--)
		{
			if (plateau[i][this.getEmplacement().y] == null)
			{
				mouvementPossible.add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				i = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, this.getEmplacement().y)))
						mouvementPossible.add(x);
				}
			}
		}
	}

}
