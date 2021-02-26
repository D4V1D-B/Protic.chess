package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Cavalier extends Pieces
{

	public Cavalier(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
	}
	
	public Cavalier(String nom, boolean couleur, Point position, Plateau plateau,
			ArrayList<Point> positionEnemie)
	{
		super(nom, couleur, position);
		setMouvementPossible(plateau, positionEnemie);
	}
	
	public void setMouvementPossible(Plateau plateau,
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
		this.getMouvementPossible().clear();
		

		int i = this.getEmplacement().x;
		int j = this.getEmplacement().y;

		for (Point a : variationMouvement)
		{
			if(i + a.getX()<8&&i + a.getX()>=0&& j + a.getY()<8&&j + a.getY()>=0)
			{
				if (plateau.getVoidSpace().contains(new Point((int)(i + a.getX()),(int)(j + a.getY()))))
				{
					getMouvementPossible().add(new Point((int) (i + a.getX()), (int) (j + a.getY())));
				}
				else
				{
					for (Point x : positionEnemie)
					{
						if (x.equals(new Point((int) (i + a.getX()), (int) (j + a.getY()))))
							getMouvementPossible().add(x);
					}
				}
			}
		
		}

	}
}
