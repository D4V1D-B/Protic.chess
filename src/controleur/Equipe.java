package controleur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import modele.Pieces;
import modele.Roi;

public class Equipe
{
	private Set<Point> mouvementPossible;
	private ArrayList<Pieces> listePiece;
	private Point positionRoi;

	public Equipe(ArrayList<Pieces> pieces)
	{
		listePiece = pieces;
		actualiserPositionRoi();
		mouvementPossible =  new HashSet<Point>();
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
			mouvementPossible.addAll(p.getMouvementPossible());
		}
	}
	
	public  Set<Point> getMouvementPossible()
	{
		return mouvementPossible;
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
	
	public ArrayList<Point> positionEquipe()
	{
		ArrayList<Point> temp = new ArrayList<Point>();
		
		for(Pieces p: listePiece)
		{
			temp.add(p.getEmplacement());
		}
		
		return temp;
	}
	

}
