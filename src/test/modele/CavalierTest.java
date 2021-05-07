package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Pieces;

public class CavalierTest
{
	Cavalier cavalierBlanc, cavalierNoir, cavaliercoin;
	Pieces[][] vide, blanc,noir;
	Point roi= new Point(0,0);

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
		cavalierNoir = new Cavalier("cavalierC", true, new Point(4, 0));

		// cavalier dans un coin
		cavaliercoin = new Cavalier("cavalierc", true, new Point(7, 7));
		
		
		for(int i = 0; i< vide.length ;i++)
		{
			for(int j = 0; j< vide[i].length ;j++)
			{
				blanc[i][j]=cavalierBlanc;
				noir[i][j]=cavalierNoir;
			}
		}
				
	}

	@Test
	public void testSetMouvementPossible()
	{
		cavalierBlanc.setMouvementPossible(vide, roi);
		cavalierNoir.setMouvementPossible(vide, roi);
//		Pieces[][] plateau, Point positionRoiEnemy
		// cavalier du bord peut aller sur 1,6 / 2,5 / 2,3 / 1,2
		assertEquals(true,
				cavalierBlanc.getMouvementPossible().contains(new Point(2, 1)));
		assertEquals(true,
				cavalierBlanc.getMouvementPossible().contains(new Point(3, 2)));
		assertEquals(true,
				cavalierBlanc.getMouvementPossible().contains(new Point(5, 2)));
		assertEquals(true,
				cavalierBlanc.getMouvementPossible().contains(new Point(6, 1)));
		
		//test noir
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(3, 2)));
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(5, 2)));
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(6, 1)));

		//test bloquer par pieces
		cavalierNoir.getMouvementPossible().clear();
		cavalierNoir.setMouvementPossible(blanc, roi);
		cavaliercoin.setMouvementPossible(noir, roi);
		//noir
		assertEquals(true,
				!cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		//blanc
		assertEquals(true,
				!cavaliercoin.getMouvementPossible().contains(new Point(6, 5)));
		
		
		//test bloquer par pieces
		cavalierNoir.getMouvementPossible().clear();
		cavaliercoin.getMouvementPossible().clear();
		cavalierNoir.setMouvementPossible(noir, roi);
		cavaliercoin.setMouvementPossible(blanc, roi);

		//noir
//		assertEquals(true,
//				cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		//blanc
		assertEquals(true,
				cavaliercoin.getMouvementPossible().contains(new Point(6, 5)));
	}
}
