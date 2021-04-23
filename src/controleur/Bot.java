package controleur;

import java.awt.Point;
import java.util.ArrayList;

import javafx.util.Pair;
import modele.Cavalier;
import modele.Fou;
import modele.Move;
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
					bestMovement= anciennePosition.x +" " +  anciennePosition.y + p.getNom()+mov.x+mov.y;
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
	
	public String jouerBotTest(Plateau plateau)
	{
		this.plateau = plateau;
		String deplacement = "";
		Move move = recherche(3).getValue();
		// 1 7n25
		Pieces piece = move.getPieces();
		deplacement += piece.getEmplacement().x + " " + piece.getEmplacement().y
				+ move.getPieces().getNom() + move.getPoint().x
				+ move.getPoint().y;
		System.out.println(deplacement);
		return deplacement;

	}

	private Pair<Integer, Move> recherche(int depth)
	{
		Pair<Integer, Move> bestMovement;
		int valeur = 0;
		if (depth == 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove();
		if (movePossible.size() == 0)
		{
			if (plateau.getEchec())
			{
				return new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY,
						null);
			}
			return new Pair<Integer, Move>(0, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY,
				null);
		for (Move mov : movePossible)
		{
			Pieces piece = mov.getPieces();
			Point point = piece.getEmplacement();

			Move reverseMove = deplacementDavid(piece.getEmplacement(), piece);
			int evaluation = -(recherche(depth - 1)).getKey();
			if (bestMovement.getKey() < evaluation)
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			piece.setEmplacement(point);
			plateau.deplacerPieces(piece);
			if (reverseMove.getPieces() != null)
			{
				reverseMove.getPieces().setEmplacement(reverseMove.getPoint());
				plateau.deplacerPieces(reverseMove.getPieces());
			}

			plateau.actualiserTeam();

		}

		return bestMovement;

	}
	
	private Move deplacementDavid(Point anciennePosition, Pieces piecesDeplacer)
	{
		// On enleve la pieces de son ancien deplacement
		plateau.getPlateau()[anciennePosition.x][anciennePosition.y] = null;

		// On déplace la pieces et on store ce qui a été bouffe
		Pieces manger = plateau.deplacerPieces(piecesDeplacer);
		Point positionManger = plateau.posManger(piecesDeplacer);

		plateau.actualiserToutLesMouvementJouable();

		if (piecesDeplacer.getClass().toString().contains("Pion")
				&& (anciennePosition.y - piecesDeplacer.getEmplacement().y == 2
						|| anciennePosition.y
								- piecesDeplacer.getEmplacement().y == -2))
		{
			plateau.ajouterEnPassant(anciennePosition, piecesDeplacer);
		}

		Move reverseMove = new Move(manger, positionManger);
		return reverseMove;
	}
	//
	// private int mininmum(int depth) {
	// int bestMovement = (int) Double.POSITIVE_INFINITY ;
	// return bestMovement;
	//
	// }
	//
	// private int maximum(int depth) {
	// int bestMovement = - (int) Double.POSITIVE_INFINITY ;
	// if( depth <= 0) {
	// bestMovement = evaluerEquipe();
	// ArrayList<Point> movePossible = generationMove();
	// while(movePossible.size()!= 0) {
	//
	// }
	// }
	// return bestMovement;
	//
	// }

	private int evaluerEquipe()
	{
		ArrayList<Pieces> PiecesBlanche = (ArrayList<Pieces>) plateau.getBlanc()
				.getListePieces();
		ArrayList<Pieces> PiecesNoir = (ArrayList<Pieces>) plateau.getNoir()
				.getListePieces();
		int BlancValeurTotal = compterValeur(PiecesBlanche);
		int NoirValeurTotal = compterValeur(PiecesNoir);
		int evaluer = BlancValeurTotal - NoirValeurTotal;

		return evaluer;

	}

	public ArrayList<Move> generationMove()
	{

		ArrayList<Pieces> tousPieces = (ArrayList<Pieces>) plateau.getNoir()
				.getListePieces();
		ArrayList<Move> tousMoves = new ArrayList<Move>();

		for (Pieces pieces : tousPieces)
		{
			for (Point point : pieces.getMouvementJouable())
			{
				tousMoves.add(new Move(pieces, point));
			}

		}
		return tousMoves;

	}

	private int compterValeur(ArrayList<Pieces> equipe)
	{
		int valeur = 0;
		for (Pieces p : equipe)
		{
			valeur += p.getValeur();
		}
		return valeur;
	}

	
}