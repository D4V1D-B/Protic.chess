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
import modele.Triplets;

public class ReineTest
{
	Cavalier cavalierBlanc, cavalierNoir;
	Pieces[][] vide, blanc,noir;
	Point pointRoi= new Point(5,0);
	Reine reineNoir, reineBlanc;

	
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
		
		reineBlanc = new Reine("tour", true, new Point(4, 4));

		reineNoir = new Reine("fou", false, new Point(4, 4));		
	}

	@Test
	public void testSetMouvementPossible()
	{
		reineBlanc.setMouvementPossible(vide, pointRoi);
		reineNoir.setMouvementPossible(vide, pointRoi);
		// test mouvement de base
		assertEquals(27, reineNoir.getMouvementPossible().size());
		assertEquals(27, reineBlanc.getMouvementPossible().size());
		assertEquals(true, reineNoir.getMouvementPossible().contains(new Point(4, 7)));
		assertEquals(true, reineBlanc.getMouvementPossible().contains(new Point(4, 0)));
		assertEquals(true, reineNoir.getMouvementPossible().contains(new Point(0, 4)));
		assertEquals(true, reineBlanc.getMouvementPossible().contains(new Point(7, 4)));

		// essaie de sortir par la droite
		assertEquals(false, reineBlanc.getMouvementPossible().contains(new Point(8, 4)));
		// essaie de sortir par la droite par en haut
		assertEquals(false, reineBlanc.getMouvementPossible().contains(new Point(4, 8)));
		// essaie de sortir par la droite
		assertEquals(false, reineNoir.getMouvementPossible().contains(new Point(-1, 4)));
		// essaie de sortir par la droite par en haut
		assertEquals(false, reineNoir.getMouvementPossible().contains(new Point(4, -1)));

		// test manger par pieces
		reineNoir.getMouvementPossible().clear();
		reineBlanc.getMouvementPossible().clear();
		reineNoir.setMouvementPossible(blanc, pointRoi);
		reineBlanc.setMouvementPossible(noir, pointRoi);
		assertEquals(8, reineBlanc.getMouvementPossible().size());
		assertEquals(8, reineNoir.getMouvementPossible().size());

		// test bloquer par pieces
		reineNoir.getMouvementPossible().clear();
		reineBlanc.getMouvementPossible().clear();
		reineNoir.setMouvementPossible(noir, pointRoi);
		reineBlanc.setMouvementPossible(blanc, pointRoi);
		assertEquals(0, reineBlanc.getMouvementPossible().size());
		assertEquals(0, reineNoir.getMouvementPossible().size());

		// Test triplets
		// gauche
		Reine attaqueRoi = new Reine("tour", true, new Point(4, 4));
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
		Reine attaqueRoiNoir = new Reine("cavalierc", false, new Point(4, 4));
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
		
		
		
		reineBlanc.setMouvementPossible(vide, pointRoi);
		reineNoir.setMouvementPossible(vide, pointRoi);
		//test mouvement de base 
		assertEquals(true,
				reineNoir.getMouvementPossible().contains(new Point(7, 7)));
		assertEquals(true,
				reineBlanc.getMouvementPossible().contains(new Point(5, 5)));
		assertEquals(true,
				reineNoir.getMouvementPossible().contains(new Point(5, 3)));
		assertEquals(true,
				reineBlanc.getMouvementPossible().contains(new Point(7, 1)));

		//essaie de sortir par la droite 
		assertEquals(false,
				reineBlanc.getMouvementPossible().contains(new Point(8, 0)));
		//essaie de sortir par la droite par en haut
		assertEquals(false,
				reineBlanc.getMouvementPossible().contains(new Point(0, 8)));
		//essaie sortir bas
		Reine reine = new Reine("fou", false, new Point(0, 0));	
		reine.setMouvementPossible(vide, pointRoi);
		assertEquals(false,
				reine.getMouvementPossible().contains(new Point(1, -1)));
		//essaie de sortir par gauche
		assertEquals(false,
				reine.getMouvementPossible().contains(new Point(-1, 1)));
		
		//Test triplets
		//++
		Reine attaqueRoiDiag = new Reine("cavalierc", true, new Point(4, 4));
		Roi roidiag = new Roi("roi", false, new Point(7,7));
		vide[roidiag.getEmplacement().x][roidiag.getEmplacement().y] = roidiag;
		Triplets testdiag = attaqueRoiDiag.setMouvementPossible(vide, roidiag.getEmplacement());
		assertEquals(true, testdiag.getState()==8);
		assertEquals(true, testdiag.getAttaquant().equals(attaqueRoiDiag));
		assertEquals(true, testdiag.getDefendant().equals(roidiag));

		//--
		roidiag.setEmplacement(new Point(0,0));
		vide[roidiag.getEmplacement().x][roidiag.getEmplacement().y] = roidiag;
		testdiag = attaqueRoiDiag.setMouvementPossible(vide, roidiag.getEmplacement());
		assertEquals(true, testdiag.getState()==5);
		
		//-+
		roidiag.setEmplacement(new Point(3,5));
		vide[roidiag.getEmplacement().x][roidiag.getEmplacement().y] = roidiag;
		testdiag = attaqueRoiDiag.setMouvementPossible(vide, roidiag.getEmplacement());
		assertEquals(true, testdiag.getState()==6);
		
		//+-
		roidiag.setEmplacement(new Point(5,3));
		vide[roidiag.getEmplacement().x][roidiag.getEmplacement().y] = roidiag;
		testdiag = attaqueRoiDiag.setMouvementPossible(vide, roidiag.getEmplacement());
		assertEquals(true, testdiag.getState()==7);
		
		//Test triplets
		//++
		Fou attaqueRoiNoirDiag = new Fou("cavalierc", false, new Point(4, 4));
		Roi roiBlancDiag = new Roi("roi", true, new Point(7,7));
		vide[roiBlancDiag.getEmplacement().x][roiBlancDiag.getEmplacement().y] = roiBlancDiag;
		Triplets testNoirDiag = attaqueRoiNoirDiag.setMouvementPossible(vide, roiBlancDiag.getEmplacement());
		assertEquals(true, testNoirDiag.getState()==8);
		assertEquals(true, testNoirDiag.getAttaquant().equals(attaqueRoiNoirDiag));
		assertEquals(true, testNoirDiag.getDefendant().equals(roiBlancDiag));

		//--
		roiBlancDiag.setEmplacement(new Point(0,0));
		vide[roiBlancDiag.getEmplacement().x][roiBlancDiag.getEmplacement().y] = roiBlancDiag;
		testNoirDiag = attaqueRoiNoirDiag.setMouvementPossible(vide, roiBlancDiag.getEmplacement());
		assertEquals(true, testNoirDiag.getState() == 5);

		//-+
		roiBlancDiag.setEmplacement(new Point(3,5));
		vide[roiBlancDiag.getEmplacement().x][roiBlancDiag.getEmplacement().y] = roiBlancDiag;
		testNoirDiag = attaqueRoiNoirDiag.setMouvementPossible(vide, roiBlancDiag.getEmplacement());
		assertEquals(true, testNoirDiag.getState()==6);
				
		//+-
		roiBlancDiag.setEmplacement(new Point(5,3));
		vide[roiBlancDiag.getEmplacement().x][roiBlancDiag.getEmplacement().y] = roiBlancDiag;
		testNoirDiag = attaqueRoiNoirDiag.setMouvementPossible(vide, roiBlancDiag.getEmplacement());
		assertEquals(true, testNoirDiag.getState()==7);
	}
}
