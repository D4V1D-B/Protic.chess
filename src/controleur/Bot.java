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
	public ArrayList<String> jouerBot(Plateau plateau)
	{
		String bestMovement;
		
		for (Pieces p : plateau.getNoir().getListePieces())
		{
			for (Point mov : p.getMouvementJouable())
			{
				
			}
		}

		return null;
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