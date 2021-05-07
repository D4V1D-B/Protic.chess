package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Pion;
import modele.Roi;
import modele.Tour;
import modele.Triplets;

public class PionTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(7,7);
	Pion pionNoir, pionBlanc;

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
		
		pionBlanc = new Pion("tour", true, new Point(6, 6));

		pionNoir = new Pion("fou", false, new Point(0, 6));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		Triplets test = pionBlanc.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				test.equals(null));
		pionNoir.setMouvementPossible(vide, pointRoi);
		//Pieces[][] plateau, Point positionRoiEnemy
		//test mouvement de base 
		assertEquals(true,
				pionBlanc.getMouvementPossible().contains(new Point(6, 7)));
		assertEquals(true,
				pionNoir.getMouvementPossible().contains(new Point(0, 5)));
		assertEquals(false,
				pionBlanc.getMouvementPossible().contains(new Point(6, 8)));
		assertEquals(true,
				pionNoir.getMouvementPossible().contains(new Point(0, 4)));
		
		//test manger par pieces
		pionNoir.getMouvementPossible().clear();
		pionBlanc.getMouvementPossible().clear();
		pionNoir.setMouvementPossible(blanc, pointRoi);
		pionBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(true,
				pionBlanc.getMouvementPossible().contains(new Point(5, 7)));
		assertEquals(true,
				pionNoir.getMouvementPossible().contains(new Point(1,5)));
		assertEquals(false,
				pionBlanc.getMouvementPossible().contains(new Point(6, 7)));
		assertEquals(false,
				pionNoir.getMouvementPossible().contains(new Point(0, 5)));
		
		
		//test bloquer par pieces
		pionNoir.getMouvementPossible().clear();
		pionBlanc.getMouvementPossible().clear();
		pionNoir.setMouvementPossible(noir, pointRoi);
		pionBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(false,
				pionBlanc.getMouvementPossible().contains(new Point(7, 6)));
		assertEquals(false,
				pionNoir.getMouvementPossible().contains(new Point(0, 5)));
		assertEquals(false,
				pionNoir.getMouvementPossible().contains(new Point(0, 6)));

	}
}
