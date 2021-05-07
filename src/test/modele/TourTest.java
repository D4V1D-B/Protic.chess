package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Roi;
import modele.Tour;
import modele.Triplets;

public class TourTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(0,0);
	Tour tourNoir, tourBlanc;

	// public Cavalier(String nom, boolean couleur, Point position, Plateau
	// plateau,
	// ArrayList<Point> positionEnemie)
	// {
	// super(nom, couleur, position);
	// setMouvementPossible(plateau, positionEnemie);
	// }
	@Before
	public void testCavalier()
	{
		vide = new Pieces[8][8];
		blanc = new Pieces[8][8];
		noir = new Pieces[8][8];
		// cavalier sur le bord
		cavalierBlanc = new Cavalier("cavalierB", true, new Point(4, 0));
		// cavalier au centre
		cavalierNoir = new Cavalier("cavalierC", false, new Point(4, 0));		
		for(int i = 0; i< vide.length ;i++)
		{
			for(int j = 0; j< vide[i].length ;j++)
			{
				blanc[i][j]=cavalierBlanc;
				noir[i][j]=cavalierNoir;
			}
		}	
		Roi roi = new Roi("roi",false,pointRoi);
		vide[pointRoi.x][pointRoi.y]=roi;
		
		tourBlanc = new Tour("tour", true, new Point(0, 4));

		tourNoir = new Tour("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		Triplets test = tourBlanc.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				test.equals(null));
		tourNoir.setMouvementPossible(vide, pointRoi);
		//Pieces[][] plateau, Point positionRoiEnemy
		//test mouvement de base 
		assertEquals(true,
				tourBlanc.getMouvementPossible().contains(new Point(4, 4)));
		assertEquals(true,
				tourNoir.getMouvementPossible().contains(new Point(4, 7)));
		assertEquals(false,
				tourBlanc.getMouvementPossible().contains(new Point(4, 3)));
		assertEquals(false,
				tourNoir.getMouvementPossible().contains(new Point(2, 8)));
		
		//test manger par pieces
		tourNoir.getMouvementPossible().clear();
		tourBlanc.getMouvementPossible().clear();
		tourNoir.setMouvementPossible(blanc, pointRoi);
		tourBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(true,
				tourBlanc.getMouvementPossible().contains(new Point(1, 4)));
		assertEquals(true,
				tourNoir.getMouvementPossible().contains(new Point(5,4)));
		assertEquals(false,
				tourBlanc.getMouvementPossible().contains(new Point(6, 4)));
		assertEquals(false,
				tourNoir.getMouvementPossible().contains(new Point(2, 4)));
		
		
		//test bloquer par pieces
		tourNoir.getMouvementPossible().clear();
		tourBlanc.getMouvementPossible().clear();
		tourNoir.setMouvementPossible(noir, pointRoi);
		tourBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(false,
				tourBlanc.getMouvementPossible().contains(new Point(4, 4)));
		assertEquals(false,
				tourNoir.getMouvementPossible().contains(new Point(8, 4)));
		assertEquals(false,
				tourBlanc.getMouvementPossible().contains(new Point(5, 4)));
		assertEquals(false,
				tourNoir.getMouvementPossible().contains(new Point(3, 4)));

	}
}
