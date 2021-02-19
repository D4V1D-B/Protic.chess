package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Reine extends Pieces {

	private ArrayList<Point> mouvementPossible;
	
	public Reine(String nom, boolean couleur, Point position) {
		super(nom, couleur, position);
	}

	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
	{
		mouvementPossible.clear();
		// ligne vers le haut à gauhce donc --
		
		for (int i = this.getEmplacement().x-1 , j = this.getEmplacement().y-1; i >= 0 && j >=0; j--, i--)
		{
			if (plateau[i][j] == null)
			{
				mouvementPossible.add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						mouvementPossible.add(x);
				}
			}
		}

		// ligne vers le haut à droite -+
		
		for (int i = this.getEmplacement().x-1 , j = this.getEmplacement().y+1; i >= 0 && j <=7; j++, i--)
		{
			if (plateau[i][j] == null)
			{
				mouvementPossible.add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						mouvementPossible.add(x);
				}
			}
		}

		
		// ligne vers le bas à droite donc ++
		
		for (int i = this.getEmplacement().x+1 , j = this.getEmplacement().y+1; i <=7 && j <=7; j++, i++)
		{
			if (plateau[i][j] == null)
			{
				mouvementPossible.add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						mouvementPossible.add(x);
				}
			}
		}
		
		// ligne vers le bas  à gauche
		for (int i = this.getEmplacement().x+1 , j = this.getEmplacement().y-1; i <=7 && j >=0; j--, i++)
		{
			if (plateau[i][j] == null)
			{
				mouvementPossible.add(new Point(i, j));
			} 
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						mouvementPossible.add(x);
				}
			}
		}
		
		

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
