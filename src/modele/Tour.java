package modele;

import java.awt.Point;

public class Tour extends Pieces
{

	private boolean aBouger;

	public Tour(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
		aBouger = false;
	}

	public Tour(String nom, boolean couleur, Point position, Pieces[][] plateau)
	{
		super(nom, couleur, position);
		aBouger = false;
		setMouvementPossible(plateau);
	}

	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger(boolean aBouger)
	{
		this.aBouger = aBouger;
	}

	public void setMouvementPossible(Pieces[][] plateau)
	{
		this.getMouvementPossible().clear();
		// ajouter sur la ligne de la tour à gauche
		for (int j = this.getEmplacement().y - 1; j >= 0; j--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(this.getEmplacement().x, j)))
			{
				this.getMouvementPossible()
						.add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				if (this.isWhite() != plateau[this.getEmplacement().x][j]
						.isWhite())
					getMouvementPossible()
							.add(new Point(this.getEmplacement().x, j));

				j = -30;
			}
		}

		// ajouter sur la ligne de la tour à droite
		for (int j = this.getEmplacement().y + 1; j <= 7; j++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(this.getEmplacement().x, j)))
			{
				this.getMouvementPossible()
						.add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				if (this.isWhite() != plateau[this.getEmplacement().x][j]
						.isWhite())
					getMouvementPossible()
							.add(new Point(this.getEmplacement().x, j));

				j = 30;
			}
		}

		// ajouter pour la ligne du bas
		for (int i = this.getEmplacement().x + 1; i <= 7; i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i, this.getEmplacement().y)))
			{
				this.getMouvementPossible()
						.add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				if (this.isWhite() != plateau[i][this.getEmplacement().y]
						.isWhite())
					getMouvementPossible()
							.add(new Point(i, this.getEmplacement().y));
				i = 30;
			}
		}

		// ajouter la ligne du haut

		for (int i = this.getEmplacement().x - 1; i >= 0; i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i, this.getEmplacement().y)))
			{
				this.getMouvementPossible()
						.add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				if (this.isWhite() != plateau[i][this.getEmplacement().y]
						.isWhite())
					getMouvementPossible()
							.add(new Point(i, this.getEmplacement().y));
				i = -30;
			}
		}
	}

}
