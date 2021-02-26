package controleur;

import java.awt.Point;
import java.util.ArrayList;

import modele.Pieces;
import modele.Roi;

public class TourJouer
{
	private ArrayList<Point> mouvementPossible;
	private ArrayList<Pieces> listePiece;
	private Point positionRoi;

	public TourJouer(ArrayList<Pieces> pieces)
	{
		listePiece = pieces;
		actualiserPositionRoi();
		mouvementPossible = new ArrayList<Point>();
		actualiserMouvementPossible();
	}

	public int indexOfKing()
	{
		int index = 0;
		for (int i = 0; i <= listePiece.size(); i++)
		{
			if (listePiece.get(i).getClass().toString()
					.equals("class modele.Roi"))
			{
				index = i;
			}
		}

		return index;
	}

	public void actualiserPositionRoi()
	{
		positionRoi = listePiece.get(indexOfKing()).getEmplacement();
	}

	public void actualiserMouvementPossible()
	{
		for(Pieces p: listePiece)
		{
			for(Point point: )
		}
	}
	
	public boolean vérifierÉchec(
			ArrayList<Point> mouvementPossibleEnemy)
	{
		boolean roiEchec = false;

		for (Point p : mouvementPossibleEnemy)
		{
			if (roiEchec != true)
			{
				roiEchec = positionRoi.equals(p);
			}

		}

		return roiEchec;
	}
	

}
