package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Tour extends Pieces
{

	private boolean aBouger;

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


	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
	{
		this.getMouvementPossible().clear();
		// ajouter sur la ligne de la tour à gauche
		for (int j = this.getEmplacement().y - 1; j >= 0; j--)
		{
			if (plateau[this.getEmplacement().x][j].equals(new PositionVide().getClass()))
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(this.getEmplacement().x, j)))
						this.getMouvementPossible().add(x);
				}

			}
		}

		// ajouter sur la ligne de la tour à droite
		for (int j = this.getEmplacement().y + 1; j <= 7; j++)
		{
			if (plateau[this.getEmplacement().x][j].equals(new PositionVide().getClass()))
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				j = 30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(this.getEmplacement().x, j)))
						this.getMouvementPossible().add(x);
				}
			}
		}

		// ajouter pour la ligne du bas
		for (int i = this.getEmplacement().x + 1; i <= 7; i++)
		{
			if (plateau[i][this.getEmplacement().y].equals(new PositionVide().getClass()))
			{
				this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				i = 30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, this.getEmplacement().y)))
						this.getMouvementPossible().add(x);
				}
			}
		}

		// ajouter la ligne du haut

		for (int i = this.getEmplacement().x - 1; i >= 0; i--)
		{
			if (plateau[i][this.getEmplacement().y].equals(new PositionVide().getClass()))
			{
				this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				i = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, this.getEmplacement().y)))
						this.getMouvementPossible().add(x);
				}
			}
		}
	}

}
