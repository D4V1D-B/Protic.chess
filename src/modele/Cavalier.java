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
	}

	public triplets setMouvementPossible(Pieces[][] plateau, Point positionRoiEnemy)
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
		
		triplets temp =null;
		for(Point p : this.getMouvementPossible())
		{
			if(p.equals(positionRoiEnemy))
				temp=new triplets(0,this,plateau[positionRoiEnemy.x][positionRoiEnemy.y]);
		}
		return temp;
	}

	@Override
	public int getValeur()
	{
		return 30;
	}
}
