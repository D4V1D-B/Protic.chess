package Modele;

import java.awt.Point;
import java.util.ArrayList;

public class Roi extends Pieces
{
	private ArrayList<Point> mouvementPossible;
	private boolean aBouger;

	public Roi(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
		aBouger=false;
	}
	
	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger(boolean aBouger)
	{
		this.aBouger = true;
	}

	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnemie)
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
