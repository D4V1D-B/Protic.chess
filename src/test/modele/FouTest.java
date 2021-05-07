package test.modele;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Roi;
import modele.Triplets;

public class FouTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(0,0);
	Fou fouNoir, fouBlanc,fouTestBasGauche;

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
		
		fouBlanc = new Fou("fou", true, new Point(4, 4));

		fouNoir = new Fou("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		fouBlanc.setMouvementPossible(vide, pointRoi);
		fouNoir.setMouvementPossible(vide, pointRoi);
		//test mouvement de base 
		assertEquals(12,
				fouNoir.getMouvementPossible().size());
		assertEquals(13,
				fouBlanc.getMouvementPossible().size());
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(7, 7)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(true,
				fouNoir.getMouvementPossible().contains(new Point(5, 3)));
		assertEquals(true,
				fouBlanc.getMouvementPossible().contains(new Point(7, 1)));

		//essaie de sortir par la droite 
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(8, 0)));
		//essaie de sortir par la droite par en haut
		assertEquals(false,
				fouBlanc.getMouvementPossible().contains(new Point(0, 8)));
		//essaie sortir bas
		fouTestBasGauche = new Fou("fou", false, new Point(0, 0));	
		fouTestBasGauche.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				fouTestBasGauche.getMouvementPossible().contains(new Point(1, -1)));
		//essaie de sortir par gauche
		assertEquals(false,
				fouTestBasGauche.getMouvementPossible().contains(new Point(-1, 1)));
		
		
		//test manger par pieces
		fouNoir.getMouvementPossible().clear();
		fouBlanc.getMouvementPossible().clear();
		fouNoir.setMouvementPossible(blanc, pointRoi);
		fouBlanc.setMouvementPossible(noir, pointRoi);
		fouTestBasGauche.getMouvementPossible().clear();
		fouTestBasGauche.setMouvementPossible(blanc, pointRoi);
		assertEquals(4,
				fouBlanc.getMouvementPossible().size());
		assertEquals(4,
				fouNoir.getMouvementPossible().size());
		assertEquals(1,
				fouTestBasGauche.getMouvementPossible().size());

		
		//test bloquer par pieces
		fouNoir.getMouvementPossible().clear();
		fouBlanc.getMouvementPossible().clear();
		fouNoir.setMouvementPossible(noir, pointRoi);
		fouBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(0,
				fouBlanc.getMouvementPossible().size());
		assertEquals(0,
				fouNoir.getMouvementPossible().size());
		
		//Test triplets
		//++
		Fou attaqueRoi = new Fou("cavalierc", true, new Point(4, 4));
		Roi roi = new Roi("roi", false, new Point(7,7));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		Triplets test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState()==8);
		assertEquals(true, test.getAttaquant().equals(attaqueRoi));
		assertEquals(true, test.getDefendant().equals(roi));

		//--
		roi.setEmplacement(pointRoi);
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState()==5);
		
		//-+
		roi.setEmplacement(new Point(3,5));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState()==6);
		
		//+-
		roi.setEmplacement(new Point(5,3));
		vide[roi.getEmplacement().x][roi.getEmplacement().y] = roi;
		test = attaqueRoi.setMouvementPossible(vide, roi.getEmplacement());
		assertEquals(true, test.getState()==7);
	}
}
