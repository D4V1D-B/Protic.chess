package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Move;
import modele.Pieces;
import modele.Roi;
import modele.Triplets;

public class MoveTest
{
	Cavalier cavalierBlanc;
	Pieces[][] vide;
	Move test;
	
	@Before
	public void testMove()
	{
		cavalierBlanc = new Cavalier("cavalierA", true, new Point(4, 0));
		vide = new Pieces[8][8];
		vide[4][0]=cavalierBlanc;
		cavalierBlanc.setMouvementPossible(vide, new Point(0,0));
		test = new Move(cavalierBlanc, cavalierBlanc.getMouvementPossible().get(0));
	}

	@Test
	public void testGetPieces()
	{
		assertEquals(cavalierBlanc, test.getPieces());
	}
	
	@Test
	public void testGetPoint()
	{
		assertEquals(cavalierBlanc.getMouvementPossible().get(0), test.getPoint());
	}
}
