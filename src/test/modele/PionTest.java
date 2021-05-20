 package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Pieces;
import modele.Pion;
import modele.Roi;
import modele.Triplets;

public class PionTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc, noir;
	Point pointRoi = new Point(0, 0);
	Pion pionNoir, pionBlanc;

	@Before
	public void testPion()
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

		pionBlanc = new Pion("pion", true, new Point(6, 5));
		pionNoir = new Pion("pion", false, new Point(2, 6));
	}

	@Test
	public void testSetMouvementPossible()
	{
		pionBlanc.setMouvementPossible(vide, pointRoi);
		pionNoir.setMouvementPossible(vide, pointRoi);
		
		assertEquals(2, pionBlanc.getMouvementDangereux().size());
		assertEquals(true, pionBlanc.getMouvementDangereux().contains(new Point(5, 6)));
		assertEquals(true, pionBlanc.getMouvementDangereux().contains(new Point(7, 6)));
		assertEquals(2, pionNoir.getMouvementDangereux().size());
		assertEquals(true, pionNoir.getMouvementDangereux().contains(new Point(3, 5)));
		assertEquals(true, pionNoir.getMouvementDangereux().contains(new Point(1, 5)));

		// test mouvement de base
		assertEquals(1, pionBlanc.getMouvementPossible().size());
		assertEquals(false, pionBlanc.getMouvementPossible().contains(new Point(6, 7)));
		assertEquals(false, pionBlanc.getMouvementPossible().contains(new Point(7, 6)));
		assertEquals(false, pionBlanc.getMouvementPossible().contains(new Point(5, 6)));
		assertEquals(true, pionBlanc.getMouvementPossible().contains(new Point(6, 6)));
		assertEquals(2, pionNoir.getMouvementPossible().size());
		assertEquals(true, pionNoir.getMouvementPossible().contains(new Point(2, 5)));
		assertEquals(true, pionNoir.getMouvementPossible().contains(new Point(2, 4)));
		assertEquals(false, pionNoir.getMouvementPossible().contains(new Point(3, 5)));
		assertEquals(false, pionNoir.getMouvementPossible().contains(new Point(1, 5)));

		Pion pionTest = new Pion("pion", true, new Point(2, 1));
		pionTest.setMouvementPossible(vide, pointRoi);
		assertEquals(true, pionTest.getMouvementPossible().contains(new Point(2, 3)));
		assertEquals(2, pionTest.getMouvementPossible().size());

		// test manger par pieces
		pionNoir.getMouvementPossible().clear();
		pionBlanc.getMouvementPossible().clear();
		pionNoir.setMouvementPossible(blanc, pointRoi);
		pionBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(true, pionBlanc.getMouvementPossible().contains(new Point(5, 6)));
		assertEquals(true, pionNoir.getMouvementPossible().contains(new Point(1, 5)));
		assertEquals(2, pionBlanc.getMouvementPossible().size());
		assertEquals(2, pionNoir.getMouvementPossible().size());

		// test bloquer par pieces
		pionNoir.getMouvementPossible().clear();
		pionBlanc.getMouvementPossible().clear();
		pionNoir.setMouvementPossible(noir, pointRoi);
		pionBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(0, pionBlanc.getMouvementPossible().size());
		assertEquals(0, pionNoir.getMouvementPossible().size());
		
		Pion attaqueRoi = new Pion("Pion", true, new Point(4, 1));
		Roi roi = new Roi("roi", false, new Point(3,2));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		
		//roi gauche devant
		assertEquals(true, test.getState()==0);
		assertEquals(true, test.getAttaquant().equals(attaqueRoi));
		assertEquals(true, test.getDefendant().equals(roi));

		//roi droite devant
		roi.setEmplacement(new Point(5,2));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getAttaquant().equals(attaqueRoi));
		
		//roi devant
		roi.setEmplacement(new Point(4,2));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(null, test);
		roi.setEmplacement(new Point(4,3));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(null, test);
		
		
		
		Pion attaqueRoiNoir = new Pion("Pion", false, new Point(4, 6));
		Roi roiNoir = new Roi("roi", true, new Point(3,5));
		vide[roiNoir.getEmplacement().x][roiNoir.getEmplacement().y] = roiNoir;
		Triplets testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiNoir.getEmplacement());
		
		//roi gauche devant
		assertEquals(true, testNoir.getState()==0);
		assertEquals(true, testNoir.getAttaquant().equals(attaqueRoiNoir));
		assertEquals(true, testNoir.getDefendant().equals(roiNoir));

		//roi droite devant
		roiNoir.setEmplacement(new Point(5,5));
		vide[roiNoir.getEmplacement().x][roiNoir.getEmplacement().y] = roiNoir;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiNoir.getEmplacement());
		assertEquals(true, testNoir.getAttaquant().equals(attaqueRoiNoir));
		
		//roi devant
		roiNoir.setEmplacement(new Point(4,5));
		vide[roiNoir.getEmplacement().x][roiNoir.getEmplacement().y] = roiNoir;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiNoir.getEmplacement());
		assertEquals(null, testNoir);
		roiNoir.setEmplacement(new Point(4,4));
		vide[roiNoir.getEmplacement().x][roiNoir.getEmplacement().y] = roiNoir;
		testNoir = attaqueRoiNoir.setMouvementPossible(vide, roiNoir.getEmplacement());
		assertEquals(null, testNoir);
		
		
	}
}
