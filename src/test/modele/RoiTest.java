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
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;

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

		
		roiBlanc = new Roi("tour", true, new Point(4, 4));

		roiNoir = new Roi("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		roiBlanc.setMouvementPossible(vide);
		roiNoir.setMouvementPossible(vide);
		//Pieces[][] plateau, Point positionRoiEnemy
		//test mouvement de base 
		assertEquals(true,
				roiBlanc.getMouvementPossible().contains(new Point(4, 5)));
		assertEquals(true,
				roiNoir.getMouvementPossible().contains(new Point(3, 3)));
		assertEquals(false,
				roiBlanc.getMouvementPossible().contains(new Point(4, 2)));
		assertEquals(false,
				roiNoir.getMouvementPossible().contains(new Point(6, 6)));
		
		//test manger par pieces
		roiNoir.getMouvementPossible().clear();
		roiBlanc.getMouvementPossible().clear();
		roiNoir.setMouvementPossible(blanc);
		roiBlanc.setMouvementPossible(noir);
		assertEquals(true,
				roiBlanc.getMouvementPossible().contains(new Point(3, 4)));
		assertEquals(true,
				roiNoir.getMouvementPossible().contains(new Point(5,5)));

		
		
		//test bloquer par pieces
		roiNoir.getMouvementPossible().clear();
		roiBlanc.getMouvementPossible().clear();
		roiNoir.setMouvementPossible(noir);
		roiBlanc.setMouvementPossible(blanc);
		assertEquals(false,
				roiBlanc.getMouvementPossible().contains(new Point(4, 3)));
		assertEquals(false,
				roiNoir.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(false,
				roiBlanc.getMouvementPossible().contains(new Point(5, 4)));
		assertEquals(false,
				roiNoir.getMouvementPossible().contains(new Point(3, 4)));
			
//				ROCK VÉRIFIER MANUELLEMENT
	}
}
