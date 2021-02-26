package modele;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau;

public class Reine extends Pieces {


	
	public Reine(String nom, boolean couleur, Point position) {
		super(nom, couleur, position);
	}
	
	public Reine(String nom, boolean couleur, Point position, Plateau plateau,
			ArrayList<Point> positionEnemie)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau, positionEnemie);
	}

	public void setMouvementPossible(Plateau plateau,
			ArrayList<Point> positionEnemie)
	{
		this.getMouvementPossible().clear();
		// ligne vers le haut à gauhce donc --
		
				for (int i = this.getEmplacement().x-1 , j = this.getEmplacement().y-1; i >= 0 && j >=0; j--, i--)
				{
					if (plateau.getVoidSpace().contains(new Point(i,j)))
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
					if (plateau.getVoidSpace().contains(new Point(i,j)))
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
					if (plateau.getVoidSpace().contains(new Point(i,j)))
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
					if (plateau.getVoidSpace().contains(new Point(i,j)))
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
				
				
				// ajouter sur la ligne de la tour à gauche
				for (int j = this.getEmplacement().y - 1; j >= 0; j--)
				{
					if (plateau.getVoidSpace().contains(new Point(this.getEmplacement().x,j)))
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
					if (plateau.getVoidSpace().contains(new Point(this.getEmplacement().x,j)))
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
					if (plateau.getVoidSpace().contains(new Point(i,this.getEmplacement().y)))
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
					if (plateau.getVoidSpace().contains(new Point(i,this.getEmplacement().y)))
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
