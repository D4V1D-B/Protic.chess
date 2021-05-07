package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Pieces;
import modele.Roi;
import modele.Tour;
import modele.Triplets;

public class TourTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc, noir;
	Point pointRoi = new Point(0, 0);
	Tour tourNoir, tourBlanc;

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
		for (int i = 0; i < vide.length; i++)
		{
			for (int j = 0; j < vide[i].length; j++)
			{
				blanc[i][j] = cavalierBlanc;
				noir[i][j] = cavalierNoir;
			}
		}

		tourBlanc = new Tour("tour", true, new Point(4, 4));
		tourNoir = new Tour("fou", false, new Point(4, 4));
	}

	@Test
	public void testSetMouvementPossible()
	{
		tourBlanc.setMouvementPossible(vide, pointRoi);
		tourNoir.setMouvementPossible(vide, pointRoi);
		// test mouvement de base
		assertEquals(14, tourNoir.getMouvementPossible().size());
		assertEquals(14, tourBlanc.getMouvementPossible().size());
		assertEquals(true, tourNoir.getMouvementPossible().contains(new Point(4, 7)));
		assertEquals(true, tourBlanc.getMouvementPossible().contains(new Point(4, 0)));
		assertEquals(true, tourNoir.getMouvementPossible().contains(new Point(0, 4)));
		assertEquals(true, tourBlanc.getMouvementPossible().contains(new Point(7, 4)));

		// essaie de sortir par la droite
		assertEquals(false, tourBlanc.getMouvementPossible().contains(new Point(8, 4)));
		// essaie de sortir par la droite par en haut
		assertEquals(false, tourBlanc.getMouvementPossible().contains(new Point(4, 8)));
		// essaie de sortir par la droite
		assertEquals(false, tourNoir.getMouvementPossible().contains(new Point(-1, 4)));
		// essaie de sortir par la droite par en haut
		assertEquals(false, tourNoir.getMouvementPossible().contains(new Point(4, -1)));

		// test manger par pieces
		tourNoir.getMouvementPossible().clear();
		tourBlanc.getMouvementPossible().clear();
		tourNoir.setMouvementPossible(blanc, pointRoi);
		tourBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(4, tourBlanc.getMouvementPossible().size());
		assertEquals(4, tourNoir.getMouvementPossible().size());

		// test bloquer par pieces
		tourNoir.getMouvementPossible().clear();
		tourBlanc.getMouvementPossible().clear();
		tourNoir.setMouvementPossible(noir, pointRoi);
		tourBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(0, tourBlanc.getMouvementPossible().size());
		assertEquals(0, tourNoir.getMouvementPossible().size());

		// Test triplets
		// gauche
		Tour attaqueRoi = new Tour("tour", true, new Point(4, 4));
		Roi roi = new Roi("roi", false, new Point(0, 4));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState() == 1);
		assertEquals(true, test.getAttaquant().equals(attaqueRoi));
		assertEquals(true, test.getDefendant().equals(roi));

		// droite
		roi.setEmplacement(new Point(6, 4));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState() == 2);

		// bas
		roi.setEmplacement(new Point(4, 3));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState() == 3);

		//haut
		roi.setEmplacement(new Point(4, 6));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState() == 4);

		// Test triplets
		// gauche
		Tour attaqueRoiNoir = new Tour("cavalierc", false, new Point(4, 4));
		Roi roiBlanc = new Roi("roi", true, new Point(0, 4));
		vide[roiBlanc.getEmplacement().x][roiBlanc.getEmplacement().y] = roiBlanc;
		Triplets testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiBlanc.getEmplacement());
		assertEquals(true, testNoir.getState() == 1);
		assertEquals(true, testNoir.getAttaquant().equals(attaqueRoiNoir));
		assertEquals(true, testNoir.getDefendant().equals(roiBlanc));

		// droit
		roiBlanc.setEmplacement(new Point(6, 4));
		vide[roiBlanc.getEmplacement().x][roiBlanc.getEmplacement().y] = roiBlanc;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiBlanc.getEmplacement());
		assertEquals(true, testNoir.getState() == 2);

		// bas
		roiBlanc.setEmplacement(new Point(4, 3));
		vide[roiBlanc.getEmplacement().x][roiBlanc.getEmplacement().y] = roiBlanc;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiBlanc.getEmplacement());
		assertEquals(true, testNoir.getState() == 3);

		// haut
		roiBlanc.setEmplacement(new Point(4, 6));
		vide[roiBlanc.getEmplacement().x][roiBlanc.getEmplacement().y] = roiBlanc;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiBlanc.getEmplacement());
		assertEquals(true, testNoir.getState() == 4);
	}
	
	@Test
	public void TestAbouger()
	{
		assertEquals(false, tourBlanc.isaBouger());
		tourBlanc.setaBouger();
		assertEquals(true, tourBlanc.isaBouger());
	}
}
