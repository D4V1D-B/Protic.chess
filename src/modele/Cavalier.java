package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Cavalier extends Pieces
{
	private ArrayList<Point> mouvementPossible;

	public Cavalier(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
	}

	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
	{
		ArrayList<Point> variationMouvement = new ArrayList<Point>();
		variationMouvement.add(new Point(-1, -2));
		variationMouvement.add(new Point(-2, -1));
		variationMouvement.add(new Point(-2, 1));
		variationMouvement.add(new Point(-1, 2));
		variationMouvement.add(new Point(1, 2));
		variationMouvement.add(new Point(2, 1));
		variationMouvement.add(new Point(2, -1));
		variationMouvement.add(new Point(1, -2));
		mouvementPossible.clear();

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		for (Point a : variationMouvement)
		{
			if (plateau[(int) (i + a.getX())][(int) (j + a.getY())] == null)
			{
				mouvementPossible.add(new Point((int) (i + a.getX()), (int) (j + a.getY())));
			}
			else
			{
				for (Point x : positionEnemie)
				{
					if (x.equals(new Point((int) (i + a.getX()), (int) (j + a.getY()))))
						mouvementPossible.add(x);
				}
			}
		}

	}
}
