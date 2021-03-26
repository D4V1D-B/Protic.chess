package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Cavalier extends Pieces
{

	public Cavalier(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
	}

	public Cavalier(String nom, boolean couleur, Point position,
			Pieces[][] plateau)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau);
	}

	public void setMouvementPossible(Pieces[][] plateau)
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
					if (this.isWhite() != plateau[(int) (i + a.getX())][(int) (j
							+ a.getY())].isWhite())
					{
						getMouvementPossible().add(new Point(
								(int) (i + a.getX()), (int) (j + a.getY())));
					}
				}
			}

		}

	}

	@Override
	public int setValeur()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
