package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Roi;
import modele.Triplets;

public class FouTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(0,0);
	Fou fouNoir, fouBlanc;

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
		
		fouBlanc = new Fou("fou", true, new Point(4, 4));

		fouNoir = new Fou("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		Triplets test = fouBlanc.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				test.equals(null));
		fouNoir.setMouvementPossible(vide, pointRoi);
		//Pieces[][] plateau, Point positionRoiEnemy
		//test mouvement de base 
		assertEquals(false,
				fouNoir.getMouvementPossible().contains(new Point(0, 0)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(0, 0)));
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(7, 7)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(5, 3)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(7, 1)));
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(1, 7)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(2, 6)));
		assertEquals(false,
				fouNoir.getMouvementPossible().contains(new Point(-1, -1)));
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(8, 8)));
		
		//test manger par pieces
		fouNoir.getMouvementPossible().clear();
		fouBlanc.getMouvementPossible().clear();
		fouNoir.setMouvementPossible(blanc, pointRoi);
		fouBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(3, 3)));
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(6, 2)));
		assertEquals(false,
				fouNoir.getMouvementPossible().contains(new Point(2, 6)));
		
		
		//test bloquer par pieces
		fouNoir.getMouvementPossible().clear();
		fouBlanc.getMouvementPossible().clear();
		fouNoir.setMouvementPossible(noir, pointRoi);
		fouBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(3, 3)));
		assertEquals(false,
				fouNoir.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(6, 2)));
		assertEquals(false,
				fouNoir.getMouvementPossible().contains(new Point(2, 6)));

	}
}
