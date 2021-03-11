package modele;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau;

public class Pion extends Pieces
{

	public Pion(String nom, boolean couleur, Point emplacement)
	{
		super(nom, couleur, emplacement);
	}

	public Pion(String nom, boolean couleur, Point position, Pieces[][] plateau)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau);

	}

	public void setMouvementPossible(Pieces[][] plateau)
	{
		this.getMouvementPossible().clear();

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		if (this.isWhite())
		{
			// Premier mouvement 2 case
			if ((this.getEmplacement().getY()==1)
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
<<<<<<< HEAD
<<<<<<< HEAD
			if (i+1<=7 &&j-1<=0 &&!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j + 1)) && i - 1 >= 0 && !plateau[i+1][j-1]
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j + 1)) && i - 1 >= 0 && !plateau[this.getEmplacement().x][j]
>>>>>>> parent of 77eba46 (test pion)
							.isWhite())
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j + 1)) && i - 1 >= 0)
>>>>>>> parent of 0c19781 (updapte pion mangeur de pion)
			{
				this.getMouvementPossible().add(new Point(i - 1, j + 1));
			}

			// gauche case diagonal
<<<<<<< HEAD
<<<<<<< HEAD
			if (i+1<=7 &&j+1<=7 && !OperationSurUneMatrice.getVoidSpace(plateau)
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
>>>>>>> parent of 77eba46 (test pion)
					.contains(new Point(i + 1, j + 1)) && i + 1 <= 7 && !plateau[i+1][j+1]
							.isWhite())
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i + 1, j + 1)) && i + 1 <= 7)
>>>>>>> parent of 0c19781 (updapte pion mangeur de pion)
			{
				this.getMouvementPossible().add(new Point(i + 1, j + 1));
			}

		}
		else
		{
			// Premier mouvement 2 case
			if (this.getEmplacement().getY()==6
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
<<<<<<< HEAD
<<<<<<< HEAD
			if (i - 1 >= 0 && j - 1 >=0 && !OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j - 1)) && plateau[i - 1][j-1]
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j - 1)) && i - 1 >= 0&& plateau[i - 1][j-1]
>>>>>>> parent of 77eba46 (test pion)
							.isWhite())
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i - 1, j - 1)) && i - 1 >= 0)
>>>>>>> parent of 0c19781 (updapte pion mangeur de pion)
			{
				this.getMouvementPossible().add(new Point(i - 1, j - 1));
			}

			// gauche case diagonal
<<<<<<< HEAD
<<<<<<< HEAD
			if (i - 1 >= 0 && j + 1 <=7 && !OperationSurUneMatrice.getVoidSpace(plateau )
					.contains(new Point(i + 1, j - 1)) && i + 1 <= 7&& plateau[i - 1][j+1]
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau )
					.contains(new Point(i + 1, j - 1)) && i + 1 <= 7&& plateau[this.getEmplacement().x][j]
>>>>>>> parent of 77eba46 (test pion)
							.isWhite())
=======
			if (!OperationSurUneMatrice.getVoidSpace(plateau)
					.contains(new Point(i + 1, j - 1)) && i + 1 <= 7)
>>>>>>> parent of 0c19781 (updapte pion mangeur de pion)
			{
				this.getMouvementPossible().add(new Point(i + 1, j - 1));
			}
		}

	}
}
