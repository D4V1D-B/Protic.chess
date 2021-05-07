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
	Cavalier cavalierBlanc, cavalierNoir, cavalierCoin;
	Pieces[][] vide, blanc, noir;
	Point pointRoi = new Point(0, 0);

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
		cavalierBlanc = new Cavalier("cavalierA", true, new Point(4, 0));

		// cavalier au centre
		cavalierNoir = new Cavalier("cavalierB", false, new Point(4, 0));

		// cavalier dans un coin
		cavalierCoin = new Cavalier("cavalierC", true, new Point(7, 7));

		for (int i = 0; i < vide.length; i++)
		{
			for (int j = 0; j < vide[i].length; j++)
			{
				blanc[i][j] = cavalierBlanc;
				noir[i][j] = cavalierNoir;
			}
		}

	}

	@Test
	public void testSetMouvementPossible()
	{
		cavalierBlanc.setMouvementPossible(vide, pointRoi);
		cavalierNoir.setMouvementPossible(vide, pointRoi);
<<<<<<< Updated upstream
		// Pieces[][] plateau, Point positionRoiEnemy
=======
//		Pieces[][] plateau, Point positionRoiEnemy
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		cavalierNoir.setMouvementPossible(blanc, pointRoi);
		cavalierCoin.setMouvementPossible(noir, pointRoi);
		// noir
		assertEquals(true, cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		// blanc
		assertEquals(true, cavalierCoin.getMouvementPossible().contains(new Point(6, 5)));

		// test bloquer par pieces
		cavalierNoir.getMouvementPossible().clear();
		cavalierCoin.getMouvementPossible().clear();
		cavalierNoir.setMouvementPossible(noir, pointRoi);
		cavalierCoin.setMouvementPossible(blanc, pointRoi);

		// noir
		// assertEquals(true,
		// cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		// blanc
		assertEquals(true, cavalierCoin.getMouvementPossible().contains(new Point(6, 5)));
		Cavalier attaqueRoi = new Cavalier("cavalierc", true, new Point(2, 1));
		Roi roi = new Roi("roi", false, pointRoi);
		vide[pointRoi.x][pointRoi.y] = roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, pointRoi);
		assertEquals(false, test.equals(null));
		// noir
		assertEquals(true, !cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		// //blanc
		assertEquals(true, !cavalierCoin.getMouvementPossible().contains(new Point(6, 5)));

//		Cavalier attaqueRoi = new Cavalier("cavalierc", true, new Point(2, 1));
//		Roi roi = new Roi("roi", false, pointRoi);
//		vide[pointRoi.x][pointRoi.y] = roi;
//		Triplets test = attaqueRoi.setMouvementPossible(vide, pointRoi);
//		assertEquals(false, test.equals(null));
=======
		blanc[cavalierNoir.getEmplacement().x][cavalierNoir.getEmplacement().y]=cavalierNoir;
		noir[cavalierCoin.getEmplacement().x][cavalierCoin.getEmplacement().y]=cavalierCoin;
		cavalierNoir.setMouvementPossible(blanc, pointRoi);
		cavalierCoin.setMouvementPossible(noir, pointRoi);
		//noir
		assertEquals(true,
				cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
		//blanc
		assertEquals(true,
				cavalierCoin.getMouvementPossible().contains(new Point(6, 5)));
		
		
		//test bloquer par pieces
		cavalierNoir.getMouvementPossible().clear();
		cavalierCoin.getMouvementPossible().clear();
		noir[cavalierNoir.getEmplacement().x][cavalierNoir.getEmplacement().y]=cavalierNoir;
		blanc[cavalierCoin.getEmplacement().x][cavalierCoin.getEmplacement().y]=cavalierCoin;
		cavalierNoir.setMouvementPossible(noir, pointRoi);
		cavalierCoin.setMouvementPossible(blanc, pointRoi);

//		//noir
		assertEquals(true,
				!cavalierNoir.getMouvementPossible().contains(new Point(2, 1)));
//		//blanc
		assertEquals(true,
				!cavalierCoin.getMouvementPossible().contains(new Point(6, 5)));
		
		Cavalier attaqueRoi = new Cavalier("cavalierc", true, new Point(2, 1));
		Roi roi = new Roi("roi",false,pointRoi);
		vide[pointRoi.x][pointRoi.y]=roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				test.equals(null));

>>>>>>> Stashed changes
	}

	@Test
	public void testGetEmplacementPiece()
	{

		assertEquals(new Point(4, 0), cavalierBlanc.getEmplacement());
		assertEquals(new Point(4, 0), cavalierNoir.getEmplacement());
		assertEquals(new Point(7, 7), cavalierCoin.getEmplacement());
	}

	@Test
	public void testSetEmplacementPiece()
	{

		cavalierBlanc.setEmplacement(new Point(5, 4));
		cavalierNoir.setEmplacement(new Point(3, 7));
		cavalierCoin.setEmplacement(new Point(2, 1));

		assertEquals(new Point(5, 4), cavalierBlanc.getEmplacement());
		assertEquals(new Point(3, 7), cavalierNoir.getEmplacement());
		assertEquals(new Point(2, 1), cavalierCoin.getEmplacement());
	}

	@Test
	public void testGetNomPiece()
	{

		assertEquals("cavalierA", cavalierBlanc.getNom());
		assertEquals("cavalierB", cavalierNoir.getNom());
		assertEquals("cavalierC", cavalierCoin.getNom());
	}

	@Test
	public void testSetNomPiece()
	{
		cavalierBlanc.setNom("cavalier1");
		cavalierNoir.setNom("cavalier2");
		cavalierCoin.setNom("cavalier3");

		assertEquals("cavalier1", cavalierBlanc.getNom());
		assertEquals("cavalier2", cavalierNoir.getNom());
		assertEquals("cavalier3", cavalierCoin.getNom());
	}

	@Test
	public void testIsWhitePiece()
	{

		assertEquals(true, cavalierBlanc.isWhite());
		assertEquals(false, cavalierNoir.isWhite());
		assertEquals(true, cavalierCoin.isWhite());
	}

	@Test
	public void testSetCouleurPiece()
	{
		cavalierBlanc.setCouleur(false);
		cavalierNoir.setCouleur(true);
		cavalierCoin.setCouleur(false);

		assertEquals(false, cavalierBlanc.isWhite());
		assertEquals(true, cavalierNoir.isWhite());
		assertEquals(false, cavalierCoin.isWhite());
	}

	
}
