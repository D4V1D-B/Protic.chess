package test.modele;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controleur.Plateau;
import controleur.Plateau.Equipe;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Pion;
import modele.Reine;
import modele.Roi;
import modele.Tour;
import modele.Triplets;

public class PlateauTest
{
	private Plateau plateau;
	Tour R1 = new Tour("R", true, new Point(0, 0));
	Cavalier N1 = new Cavalier("N", true, new Point(1, 0));
	Fou B1 = new Fou("B", true, new Point(2, 0));
	Reine Q = new Reine("Q", true, new Point(3, 0));
	Roi K = new Roi("K", true, new Point(4, 0));
	Fou B2 = new Fou("B", true, new Point(5, 0));
	Cavalier N2 = new Cavalier("N", true, new Point(6, 0));
	Tour R2 = new Tour("R", true, new Point(7, 0));

	Pion P1 = new Pion("P1", true, new Point(0, 1));
	Pion P2 = new Pion("P2", true, new Point(1, 1));
	Pion P3 = new Pion("P3", true, new Point(2, 1));
	Pion P4 = new Pion("P4", true, new Point(3, 1));
	Pion P5 = new Pion("P5", true, new Point(4, 1));
	Pion P6 = new Pion("P6", true, new Point(5, 1));
	Pion P7 = new Pion("P7", true, new Point(6, 1));
	Pion P8 = new Pion("P8", true, new Point(7, 1));
	
	Tour r1 = new Tour("r", false, new Point(0, 7));
	Cavalier n1 = new Cavalier("n", false, new Point(1, 7));
	Fou b1 = new Fou("b", false, new Point(2, 7));
	Reine q = new Reine("q", false, new Point(3, 7));;
	Roi k = new Roi("k", false, new Point(4, 7));;
	Fou b2 = new Fou("b", false, new Point(5, 7));
	Cavalier n2 = new Cavalier("n", false, new Point(6, 7));
	Tour r2 = new Tour("r", false, new Point(7, 7));

	Pion p1 = new Pion("p1", false, new Point(0, 7));
	Pion p2 = new Pion("p2", false, new Point(1, 7));
	Pion p3 = new Pion("p3", false, new Point(2, 7));
	Pion p4 = new Pion("p4", false, new Point(3, 7));
	Pion p5 = new Pion("p5", false, new Point(4, 7));
	Pion p6 = new Pion("p6", false, new Point(5, 7));
	Pion p7 = new Pion("p7", false, new Point(6, 7));
	Pion p8 = new Pion("p8", false, new Point(7, 7));
	ArrayList<Pieces> listBlanc;
	ArrayList<Pieces> listNoir;

	@Before
	public void testPlateauPrep()
	{
		ArrayList<Pieces> listBlanc = new ArrayList<Pieces>();
		ArrayList<Pieces> listNoir = new ArrayList<Pieces>();
		
		listBlanc.add(R1);
		listBlanc.add(R2);
		listBlanc.add(B1);
		listBlanc.add(B2);
		listBlanc.add(Q);
		listBlanc.add(K);
		listBlanc.add(N1);
		listBlanc.add(N2);
		listBlanc.add(P1);
		listBlanc.add(P2);
		listBlanc.add(P3);
		listBlanc.add(P4);
		listBlanc.add(P5);
		listBlanc.add(P6);
		listBlanc.add(P7);
		listBlanc.add(P8);
		
		
		listNoir.add(r1);
		listNoir.add(r2);
		listNoir.add(b1);
		listNoir.add(b2);
		listNoir.add(q);
		listNoir.add(k);
		listNoir.add(n1);
		listNoir.add(n2);
		listNoir.add(p1);
		listNoir.add(p2);
		listNoir.add(p3);
		listNoir.add(p4);
		listNoir.add(p5);
		listNoir.add(p6);
		listNoir.add(p7);
		listNoir.add(p8);
		
		plateau= new Plateau(listBlanc, listNoir);
	}

	@Test	
	public void testgetNoir()
	{

	}

	@Test
	public void testgetBlanc()
	{

	}

	@Test
	public void testgetEchecMathBlanc()
	{

	}

	@Test
	public void testgetEchecMathNoir()
	{

	}

	@Test
	public void testpartieNulle()
	{
		
	}

	@Test
	public void testrefreshPlateau()
	{

	}

	@Test
	public void testdeplacementProg()
	{

	}

	@Test
	public void testunMakeMove()
	{
	
	}

	@Test
	public void testajouterEnPassant()
	{
		
	}

	@Test
	public void testdeplacerPieces()
	{
	
	}

	@Test
	public void testrefreshDeplacementRock()
	{
	}

	@Test
	public void testreplacerPieces()
	{
		
	}

	@Test
	public void testactualiserToutLesMouvementJouable()
	{
		
	}

	@Test
	public void testactualiserTeam()
	{
		
	}

	@Test
	public void testgetPlateau()
	{

	}

	@Test
	public void testtrouverPieces()
	{

	}

	@Test
	public void testremplacerPion()
	{
		
	}

}
