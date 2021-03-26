package controleur;

import java.awt.Point;
import java.util.ArrayList;

import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Pion;
import modele.Reine;
import modele.Roi;
import modele.Tour;

public class Bot
{
	public Plateau plateau;
	public String jouerBot(Plateau plateau)
	{
		this.plateau=plateau;
		String bestMovement="";
		int bestMovementPoint=0;
		
		for (Pieces p : plateau.getNoir().getListePieces())
		{
			Point anciennePosition = p.getEmplacement();
			
			for (Point mov : p.getMouvementJouable())
			{
				ArrayList<Point> test=p.getMouvementJouable();
				p.setEmplacement(mov);
				Pieces manger = deplacementTest(anciennePosition,p);
				if(evalMouvement(manger,p,test)>bestMovementPoint)
				{
					bestMovement= p.getNom()+mov.x+mov.y;
				}
				
				plateau.getPlateau()[p.getEmplacement().x][p.getEmplacement().y] = manger;
				p.setEmplacement(anciennePosition);
				plateau.replacerPieces(p);
				plateau.actualiserTeam();
				plateau.getBlanc().actualiserMouvementPossible();
				plateau.getNoir().actualiserMouvementPossible();
			}
		}

		return bestMovement;
	}
	
	public Pieces deplacementTest(Point anciennePosition, Pieces piecesDeplacer)
	{
		// On enleve la pieces de son ancien deplacement
		plateau.getPlateau()[anciennePosition.x][anciennePosition.y] = null;

		// On déplace la pieces et on store ce qui a été bouffer
		Pieces manger = plateau.deplacerPieces(piecesDeplacer);

		plateau.actualiserToutLesMouvementJouable();

		if (piecesDeplacer.getClass().toString().contains("Pion")
				&& (anciennePosition.y - piecesDeplacer.getEmplacement().y == 2
						|| anciennePosition.y
								- piecesDeplacer.getEmplacement().y == -2))
		{
			plateau.ajouterEnPassant(anciennePosition, piecesDeplacer);
		}
			
		return manger;
	}
	
	public int evalMouvement(Pieces manger, Pieces piecesActuel, ArrayList<Point> oldMov)
	{
		return manger.getValeur() + piecesActuel.getMouvementJouable().size()-oldMov.size();
	}
	
}




//switch (p.getClass().toString())
//{
//	case "class modele.Tour":
//		
//		break;
//
//	case "class modele.Cavalier":
//		
//		break;
//
//	case "class modele.Fou":
//		
//		break;
//
//	case "class modele.Reine":
//		
//		break;
//
//	case "class modele.Pion":
//	
//		break;
//
//	case "class modele.Roi":
//		
//		break;
//}