package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Reine;
import modele.Roi;
import modele.Tour;
import modele.Triplets;

public class ReineTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(0,0);
	Reine reineNoir, reineBlanc;

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
		
		reineBlanc = new Reine("tour", true, new Point(0, 4));

		reineNoir = new Reine("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		Triplets test = reineBlanc.setMouvementPossible(vide, pointRoi);
		assertEquals(reineBlanc,
				test.getAttaquant());
		reineNoir.setMouvementPossible(vide, pointRoi);
		//Pieces[][] plateau, Point positionRoiEnemy
		//test mouvement de base 
		assertEquals(true,
				reineBlanc.getMouvementPossible().contains(new Point(4, 4)));
		assertEquals(true,
				reineNoir.getMouvementPossible().contains(new Point(4, 7)));
		assertEquals(false,
				reineBlanc.getMouvementPossible().contains(new Point(4, 3)));
		assertEquals(false,
				reineNoir.getMouvementPossible().contains(new Point(2, 8)));
		
		//reste tester dans tour et fou car même affaire

	}
}
