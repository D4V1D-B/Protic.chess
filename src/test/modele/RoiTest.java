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

public class RoiTest
{
	Tour tourBlanc, tourNoir;
	Pieces[][] vide, blanc, noir;

	Roi roiNoir, roiBlanc;

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
		tourBlanc = new Tour("cavalierB", true, new Point(4, 0));
		// cavalier au centre
		tourNoir = new Tour("cavalierC", false, new Point(4, 0));
		for (int i = 0; i < vide.length; i++)
		{
			for (int j = 0; j < vide[i].length; j++)
			{
				blanc[i][j] = tourBlanc;
				noir[i][j] = tourNoir;
			}
		}

		roiBlanc = new Roi("roi", true, new Point(4, 4));

		roiNoir = new Roi("roi", false, new Point(4, 4));
	}

	@Test
	public void testSetMouvementPossible()
	{
		roiBlanc.setMouvementPossible(vide);
		roiNoir.setMouvementPossible(vide);
		// test mouvement de base
		assertEquals(8, roiBlanc.getMouvementPossible().size());
		assertEquals(8, roiNoir.getMouvementPossible().size());
		assertEquals(true, roiBlanc.getMouvementPossible().contains(new Point(4, 5)));
		assertEquals(true, roiNoir.getMouvementPossible().contains(new Point(3, 3)));
		assertEquals(false, roiBlanc.getMouvementPossible().contains(new Point(4, 2)));
		assertEquals(false, roiNoir.getMouvementPossible().contains(new Point(6, 6)));

		// Test sortir en haut et à gauche
		Roi roiTestGaucheHaut = new Roi("roi", true, new Point(7, 7));
		Roi roiTestDroiteBas = new Roi("roi", false, new Point(0, 0));
		roiTestGaucheHaut.setMouvementPossible(vide);
		roiTestDroiteBas.setMouvementPossible(vide);
		assertEquals(3, roiTestGaucheHaut.getMouvementPossible().size());
		assertEquals(3, roiTestDroiteBas.getMouvementPossible().size());
		assertEquals(false, roiTestGaucheHaut.getMouvementPossible().contains(new Point(8, 7)));
		assertEquals(false, roiTestGaucheHaut.getMouvementPossible().contains(new Point(7, 8)));
		assertEquals(false, roiTestDroiteBas.getMouvementPossible().contains(new Point(-1, 0)));
		assertEquals(false, roiTestDroiteBas.getMouvementPossible().contains(new Point(0, -1)));

		// test manger par pieces
		roiNoir.getMouvementPossible().clear();
		roiBlanc.getMouvementPossible().clear();
		roiNoir.setMouvementPossible(blanc);
		roiBlanc.setMouvementPossible(noir);
		assertEquals(8, roiBlanc.getMouvementPossible().size());
		assertEquals(8, roiNoir.getMouvementPossible().size());

		// test bloquer par pieces
		roiNoir.getMouvementPossible().clear();
		roiBlanc.getMouvementPossible().clear();
		roiNoir.setMouvementPossible(noir);
		roiBlanc.setMouvementPossible(blanc);
		assertEquals(0, roiBlanc.getMouvementPossible().size());
		assertEquals(0, roiNoir.getMouvementPossible().size());

		// test bloquer par pieces
		roiNoir.getMouvementPossible().clear();
		roiBlanc.getMouvementPossible().clear();
		Pieces[][] rockNoir = new Pieces[8][8];
		Pieces[][] rockBlanc = new Pieces[8][8];
		rockBlanc[0][0]= tourBlanc;
		rockBlanc[7][0]= tourBlanc;
		rockNoir[0][7]= tourNoir;
		rockNoir[7][7]= tourNoir;
		roiNoir.setMouvementPossible(rockNoir);
		roiBlanc.setMouvementPossible(rockBlanc);
		assertEquals(10, roiBlanc.getMouvementPossible().size());
		assertEquals(10, roiNoir.getMouvementPossible().size());
	}

	@Test
	public void TestAbouger()
	{
		assertEquals(false, roiBlanc.isaBouger());
		roiBlanc.setaBouger();
		assertEquals(true, roiBlanc.isaBouger());
	}
}
