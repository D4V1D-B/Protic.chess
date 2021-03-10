package test.modele;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controleur.Plateau;
import controleur.Plateau.Equipe;
import modele.Cavalier;
import modele.Pieces;

public class CavalierTest
{
	Cavalier cavalierB, cavalierC, cavalierc;

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

	
	
		// cavalier sur le bord
		cavalierB = new Cavalier("cavalierB", true, new Point(4, 0));
		
		// cavalier au centre
		cavalierC = new Cavalier("cavalierC", true, new Point(4, 4));

		// cavalier dans un coin
		cavalierc = new Cavalier("cavalierc", true, new Point(7, 7));

	
	}

	/**
	 * Pas besoin de test d'invalidité, car les inputs seront toujours bon en
	 * théorie. Si,il y a une erreur elle proviendra d'une autre partie du code.
	 */

	@Test
	public void testSetMouvementPossible()
	{
		// cavalier du bord peut aller sur 1,6 / 2,5 / 2,3 / 1,2

		assertEquals(true,
				cavalierB.getMouvementPossible().contains(new Point(2, 1)));
		assertEquals(true,
				cavalierB.getMouvementPossible().contains(new Point(3, 2)));
		assertEquals(true,
				cavalierB.getMouvementPossible().contains(new Point(5, 2)));
		assertEquals(true,
				cavalierB.getMouvementPossible().contains(new Point(6, 1)));

	}
}
