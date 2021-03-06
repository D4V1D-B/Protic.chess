package modele;

import java.awt.Point;
import java.util.ArrayList;


public class Pion extends Pieces
{

	public Pion(String nom, boolean couleur, Point emplacement)
	{
		super(nom, couleur, emplacement);
	}

	public Triplets setMouvementPossible(Pieces[][] plateau, Point positionRoiEnemy)
	{
		this.getMouvementPossible().clear();

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		if (this.isWhite())
		{
			// Premier mouvement 2 case
			if (j == 1 && OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j + 1))
					&& OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j + 2)))
			{
				this.getMouvementPossible().add(new Point(i, j + 2));
			}

			// 1 case
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j + 1)))
			{
				this.getMouvementPossible().add(new Point(i, j + 1));
			}
		}
		else
		{
			// Premier mouvement 2 case
			if (this.getEmplacement().getY() == 6 && OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j - 1))
					&& OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j - 2)))
			{
				this.getMouvementPossible().add(new Point(i, j - 2));
			}

			// 1 case
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j - 1)))
			{
				this.getMouvementPossible().add(new Point(i, j - 1));
			}
		}
		ArrayList<Point> mouvementDangereux = new ArrayList<Point>();

		 i = this.getEmplacement().x;
		 j = this.getEmplacement().y;

		if (this.isWhite())
		{

			// droite case diagonal
			if (i + 1 <= 7 && j + 1 <= 7 && !OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i + 1, j + 1))
					&& !plateau[i + 1][j + 1].isWhite())
			{
				mouvementDangereux.add(new Point(i + 1, j + 1));
			}

			// gauche case diagonal
			if (i - 1 >= 0 && j + 1 <= 7 && !OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i - 1, j + 1))
					&& !plateau[i - 1][j + 1].isWhite())
			{
				mouvementDangereux.add(new Point(i - 1, j + 1));
			}

		}
		else
		{
			// droite case diagonal

			if (i + 1 <= 7 && j - 1 >= 0 && !OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i + 1, j - 1))
					&& plateau[i + 1][j - 1].isWhite())
			{
				mouvementDangereux.add(new Point(i + 1, j - 1));
			}

			// gauche case diagonal
			if (i - 1 >= 0 && j - 1 >= 0 && !OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i - 1, j - 1))
					&& plateau[i - 1][j - 1].isWhite())
			{
				mouvementDangereux.add(new Point(i - 1, j - 1));
			}
		}
		getMouvementPossible().addAll(mouvementDangereux);
		Triplets tripletstemp = null;

		for (Point p : mouvementDangereux)
		{
			if (p.equals(positionRoiEnemy))
				tripletstemp = new Triplets(0,this, plateau[positionRoiEnemy.x][positionRoiEnemy.y]);
		}

		return tripletstemp;
	}

	public ArrayList<Point> getMouvementDangereux()
	{
		ArrayList<Point> mouvementDangereux = new ArrayList<Point>();

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		if (this.isWhite())
		{
			// droite case diagonal
			if (i + 1 <= 7 && j + 1 <= 7)
			{
				mouvementDangereux.add(new Point(i + 1, j + 1));
			}

			// gauche case diagonal
			if (i - 1 >= 0 && j + 1 <= 7)
			{
				mouvementDangereux.add(new Point(i - 1, j + 1));
			}

		}
		else
		{
			// droite case diagonal

			if (i + 1 <= 7 && j - 1 >= 0)
			{
				mouvementDangereux.add(new Point(i + 1, j - 1));
			}

			// gauche case diagonal
			if (i - 1 >= 0 && j - 1 >= 0)
			{
				mouvementDangereux.add(new Point(i - 1, j - 1));
			}
		}
		return mouvementDangereux;
	}

	@Override
	public int getValeur()
	{
		return 10;
	}
}
