package modele;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau;

public class Pion extends Pieces
{

	private boolean aBouger;

	public Pion(String nom, boolean couleur, Point emplacement)
	{
		super(nom, couleur, emplacement);
		setaBouger(false);
	}

	public Pion(String nom, boolean couleur, Point position, Pieces[][] plateau)
	{
		super(nom, couleur, position);
		setaBouger(false);
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

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		if (this.isWhite())
		{
			// Premier mouvement 2 case
			if (!this.aBouger
					&& OperationSurUneMatrice.getVoidSpace(plateau)
							.contains(new Point(i, j + 1))
					&& OperationSurUneMatrice.getVoidSpace(plateau)
							.contains(new Point(i, j + 2)))
			{
				this.getMouvementPossible().add(new Point(i, j + 2));
			}

			// 1 case
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i, j + 1)))
			{
				this.getMouvementPossible().add(new Point(i, j + 1));
			}

			// droite case diagonal
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j + 1)) && i - 1 >= 0)
			{
				this.getMouvementPossible().add(new Point(i - 1, j + 1));
			}

			// gauche case diagonal
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i + 1, j + 1)) && i + 1 <= 7)
			{
				this.getMouvementPossible().add(new Point(i + 1, j + 1));
			}

		}
		else
		{
			// Premier mouvement 2 case
			if (!this.aBouger
					&& OperationSurUneMatrice.getVoidSpace(plateau)
							.contains(new Point(i, j - 1))
					&& OperationSurUneMatrice.getVoidSpace(plateau)
							.contains(new Point(i, j - 2)))
			{
				this.getMouvementPossible().add(new Point(i, j - 2));
			}

			// 1 case
			if (OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i, j - 1)))
			{
				this.getMouvementPossible().add(new Point(i, j - 1));
			}

			// droite case diagonal
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j - 1)) && i - 1 >= 0)
			{
				this.getMouvementPossible().add(new Point(i - 1, j - 1));
			}

			// gauche case diagonal
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i + 1, j - 1)) && i + 1 <= 7)
			{
				this.getMouvementPossible().add(new Point(i + 1, j - 1));
			}
		}

	}
}
