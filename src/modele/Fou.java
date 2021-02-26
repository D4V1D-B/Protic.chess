package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Fou extends Pieces{
	

	public Fou(String nom, boolean couleur, Point position) {
		super(nom, couleur, position);
	}
	
	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
	{
		this.getMouvementPossible().clear();
		// ligne vers le haut à gauhce donc --
		
		for (int i = this.getEmplacement().x-1 , j = this.getEmplacement().y-1; i >= 0 && j >=0; j--, i--)
		{
			if (plateau[i][j] == null)
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						this.getMouvementPossible().add(x);
				}
			}
		}

		// ligne vers le haut à droite -+
		
		for (int i = this.getEmplacement().x-1 , j = this.getEmplacement().y+1; i >= 0 && j <=7; j++, i--)
		{
			if (plateau[i][j] == null)
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						this.getMouvementPossible().add(x);
				}
			}
		}

		
		// ligne vers le bas à droite donc ++
		
		for (int i = this.getEmplacement().x+1 , j = this.getEmplacement().y+1; i <=7 && j <=7; j++, i++)
		{
			if (plateau[i][j] == null)
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						this.getMouvementPossible().add(x);
				}
			}
		}
		
		// ligne vers le bas  à gauche
		for (int i = this.getEmplacement().x+1 , j = this.getEmplacement().y-1; i <=7 && j >=0; j--, i++)
		{
			if (plateau[i][j] == null)
			{
				this.getMouvementPossible().add(new Point(i, j));
			} 
			else
			{
				j = -30;
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point(i, j)))
						this.getMouvementPossible().add(x);
				}
			}
		}
	
	}

}
