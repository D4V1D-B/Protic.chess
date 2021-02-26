package controleur;

import java.awt.Point;
import java.util.ArrayList;

import modele.Cavalier;
import modele.Plateau;
import modele.Roi;

public class Test
{

	public static void main(String[] args)
	{
		Plateau newPlateau = new Plateau(new Object[8][8]);
//		for(int i=0; i < newPlateau.length; i++)
//		{
//			for(int j=0; j < newPlateau.length; j++)
//			{
//				newPlateau[i][j]= new PositionVide();
//			}
//		}

		
		
		ArrayList<Point> bonjour = new ArrayList<Point>();
		Cavalier cav = new Cavalier("bn",true, new Point(1,2));
		
		cav.setMouvementPossible(newPlateau,bonjour);
	 System.out.println(cav.getMouvementPossible());
		
		
	}

}
