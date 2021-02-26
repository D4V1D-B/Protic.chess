package modele;

import java.awt.Point;
import controleur.Plateau;

public class Reine extends Pieces
{

	public Reine(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
	}

	public Reine(String nom, boolean couleur, Point position, Plateau plateau)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau);
	}

	public void setMouvementPossible(Plateau plateau)
	{
		this.getMouvementPossible().clear();
		// ligne vers le haut à gauhce donc --

		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y
				- 1; i >= 0 && j >= 0; j--, i--)
		{
			if (plateau.getVoidSpace().contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau.trouverPieces(new Point(i, j))
						.isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;
			}
		}

		// ligne vers le haut à droite -+

		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y
				+ 1; i >= 0 && j <= 7; j++, i--)
		{
			if (plateau.getVoidSpace().contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau.trouverPieces(new Point(i, j))
						.isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;

			}
		}

		// ligne vers le bas à droite donc ++

		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y
				+ 1; i <= 7 && j <= 7; j++, i++)
		{
			if (plateau.getVoidSpace().contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau.trouverPieces(new Point(i, j))
						.isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;
			}
		}

		// ligne vers le bas à gauche
		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y
				- 1; i <= 7 && j >= 0; j--, i++)
		{
			if (plateau.getVoidSpace().contains(new Point(i, j)))
			{
				this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau.trouverPieces(new Point(i, j))
						.isWhite())
					getMouvementPossible().add(new Point(i, j));
				j = -30;
			}
		}

		// ajouter sur la ligne de la tour à gauche
				for (int j = this.getEmplacement().y - 1; j >= 0; j--)
				{
					if (plateau.getVoidSpace()
							.contains(new Point(this.getEmplacement().x, j)))
					{
						this.getMouvementPossible()
								.add(new Point(this.getEmplacement().x, j));
					}
					else
					{
						if (this.isWhite() != plateau
								.trouverPieces(new Point(this.getEmplacement().x, j))
								.isWhite())
							getMouvementPossible()
									.add(new Point(this.getEmplacement().x, j));

						j = -30;
					}
				}

				// ajouter sur la ligne de la tour à droite
				for (int j = this.getEmplacement().y + 1; j <= 7; j++)
				{
					if (plateau.getVoidSpace()
							.contains(new Point(this.getEmplacement().x, j)))
					{
						this.getMouvementPossible()
								.add(new Point(this.getEmplacement().x, j));
					}
					else
					{
						if (this.isWhite() != plateau
								.trouverPieces(new Point(this.getEmplacement().x, j))
								.isWhite())
							getMouvementPossible()
									.add(new Point(this.getEmplacement().x, j));

						j = 30;
					}
				}

				// ajouter pour la ligne du bas
				for (int i = this.getEmplacement().x + 1; i <= 7; i++)
				{
					if (plateau.getVoidSpace()
							.contains(new Point(i, this.getEmplacement().y)))
					{
						this.getMouvementPossible()
								.add(new Point(i, this.getEmplacement().y));
					}
					else
					{
						if (this.isWhite() != plateau
								.trouverPieces(new Point(i, this.getEmplacement().y))
								.isWhite())
							getMouvementPossible()
									.add(new Point(i, this.getEmplacement().y));
						i = 30;
					}
				}

				// ajouter la ligne du haut

				for (int i = this.getEmplacement().x - 1; i >= 0; i--)
				{
					if (plateau.getVoidSpace()
							.contains(new Point(i, this.getEmplacement().y)))
					{
						this.getMouvementPossible()
								.add(new Point(i, this.getEmplacement().y));
					}
					else
					{
						if (this.isWhite() != plateau
								.trouverPieces(new Point(i, this.getEmplacement().y))
								.isWhite())
							getMouvementPossible()
									.add(new Point(i, this.getEmplacement().y));
						i = -30;
					}
				}

	}

}
