package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Pieces;
import modele.Roi;
import modele.Triplets;

public class CavalierTest
{
	Cavalier cavalierBlanc, cavalierNoir, cavaliercoin;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(0,0);

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
		cavalierBlanc.setMouvementPossible(vide, pointRoi);
		cavalierNoir.setMouvementPossible(vide, pointRoi);
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

		//test manger par pieces
		cavalierNoir.getMouvementPossible().clear();
		blanc[cavalierNoir.getEmplacement().x][cavalierNoir.getEmplacement().y]=cavalierNoir;
		noir[cavaliercoin.getEmplacement().x][cavaliercoin.getEmplacement().y]=cavaliercoin;
		cavalierNoir.setMouvementPossible(blanc, pointRoi);
		cavaliercoin.setMouvementPossible(noir, pointRoi);
		//noir
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		//blanc
		assertEquals(true,
				cavaliercoin.getMouvementPossible().contains(new Point(6, 5)));
		
		
		//test bloquer par pieces
		cavalierNoir.getMouvementPossible().clear();
		cavaliercoin.getMouvementPossible().clear();
		noir[cavalierNoir.getEmplacement().x][cavalierNoir.getEmplacement().y]=cavalierNoir;
		blanc[cavaliercoin.getEmplacement().x][cavaliercoin.getEmplacement().y]=cavaliercoin;
		cavalierNoir.setMouvementPossible(noir, pointRoi);
		cavaliercoin.setMouvementPossible(blanc, pointRoi);

//		//noir
		assertEquals(true,
				!cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
//		//blanc
		assertEquals(true,
				!cavaliercoin.getMouvementPossible().contains(new Point(6, 5)));
		
		Cavalier attaqueRoi = new Cavalier("cavalierc", true, new Point(2, 1));
		Roi roi = new Roi("roi",false,pointRoi);
		vide[pointRoi.x][pointRoi.y]=roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				test.equals(null));

	}
}
