package modele;

import java.awt.Point;

public class Fou extends Pieces
{

	public Fou(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
	}

	public Fou(String nom, boolean couleur, Point position, Pieces[][] plateau)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau);
	}

	public void setMouvementPossible(Pieces[][] plateau)
	{
		this.getMouvementPossible().clear();
		// ligne vers le haut à gauhce donc --

		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y
				+ 1; i >= 0 && j <= 7; j++, i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = +30;
			}
		}

		// ligne vers le haut à droite -+

		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y
				+ 1; i <= 7 && j <= 7; j++, i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = 30;
				
			}
		}

		// ligne vers le bas à droite donc ++

		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y
				- 1; i <= 7 && j >= 0; j--, i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;
			}
		}

		// ligne vers le bas à gauche
		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y
				- 1; i >= 0 && j >= 0; j--, i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;	
			}
		}

	}

	@Override
	public int setValeur()
	{
		
		return 30;
	}

}
