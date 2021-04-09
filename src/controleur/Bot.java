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

		
		ArrayList<Point> faireLeTourDesMov = new ArrayList<Point>();
		ArrayList<Pieces> faireLeTourDesPieces = (ArrayList<Pieces>) plateau.getNoir().getListePieces().clone();

		for (Pieces p : faireLeTourDesPieces)
		{
			Point anciennePosition = p.getEmplacement();
			
			faireLeTourDesMov.clear();
			faireLeTourDesMov=(ArrayList<Point>) p.getMouvementJouable().clone();
			
			for (Point mov : faireLeTourDesMov)
			{
				int oldMov=p.getMouvementJouable().size();
				p.setEmplacement(mov);
				Pieces manger = deplacementTest(anciennePosition,p);
				if(evalMouvement(manger,p,oldMov)>bestMovementPoint)
				{
					bestMovementPoint = evalMouvement(manger,p,oldMov);
					bestMovement= anciennePosition.x + anciennePosition.y + p.getNom()+mov.x+mov.y;
				}
				
				this.plateau.getPlateau()[p.getEmplacement().x][p.getEmplacement().y] = manger;
				p.setEmplacement(anciennePosition);
				this.plateau.replacerPieces(p);
				this.plateau.actualiserTeam();
				this.plateau.actualiserToutLesMouvementJouable();
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
	
	public int evalMouvement(Pieces manger, Pieces piecesActuel, int oldMov)
	{
		int nbrDePoint=0;
		if(manger!=null)
		{
			nbrDePoint+=manger.getValeur();
		}
		if(piecesActuel.getMouvementJouable()!=null)
		{
			nbrDePoint+=piecesActuel.getMouvementJouable().size();
		}
		if(manger!=null)
		{
			nbrDePoint-=oldMov;
		}
		
		return nbrDePoint;
	}
	
}