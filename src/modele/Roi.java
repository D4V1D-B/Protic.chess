package modele;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau;

public class Roi extends Pieces
{
	private boolean aBouger;

	public Roi(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
		aBouger = false;
	}

	public Roi(String nom, boolean couleur, Point position, Pieces[][] plateau)
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
		this.aBouger = true;
	}

	public void setMouvementPossible(Pieces[][] plateau)
	{
		ArrayList<Point> variationMouvement = new ArrayList<Point>();
		variationMouvement.add(new Point(-1, -1));
		variationMouvement.add(new Point(-1, 0));
		variationMouvement.add(new Point(-1, 1));
		variationMouvement.add(new Point(0, 1));
		variationMouvement.add(new Point(1, 1));
		variationMouvement.add(new Point(1, 0));
		variationMouvement.add(new Point(1, -1));
		variationMouvement.add(new Point(0, -1));
		this.getMouvementPossible().clear();

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		for (Point a : variationMouvement)
		{
			if (i + a.getX() < 8 && i + a.getX() >= 0 && j + a.getY() < 8
					&& j + a.getY() >= 0)
			{
				if (OperationSurUneMatrice.getVoidSpace(plateau).contains(
						new Point((int) (i + a.getX()), (int) (j + a.getY()))))
				{
					getMouvementPossible().add(new Point((int) (i + a.getX()),
							(int) (j + a.getY())));
				}
				else
				{
					if (this.isWhite() != plateau[(int) (i + a.getX())][(int) (j + a.getY())].isWhite())
						getMouvementPossible().add(new Point(
								(int) (i + a.getX()), (int) (j + a.getY())));

				}
			}
		}

	}

}
