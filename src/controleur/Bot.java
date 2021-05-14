package controleur;

import java.awt.Point;
import java.util.ArrayList;

import javafx.util.Pair;
import modele.Move;
import modele.Pieces;

public class Bot
{
	private int compteur;
	public Plateau plateau;

	public Move jouerBot(Plateau plateau)
	{
		this.plateau = plateau; // prend le plateau
		return alphaBetaMinMax(3, (int) Double.NEGATIVE_INFINITY, (int) Double.POSITIVE_INFINITY).getValue();

	}

	private Pair<Integer, Move> alphaBetaMinMax(int depth, int alpha, int beta)
	{
		if (!plateau.getNoir().getMouvementJouable().equals(null))
		{
			return alphaBetaMax(depth, alpha, beta);
		}
		else
		{
			return alphaBetaMin(depth, alpha, beta);
		}

	}

	private Pair<Integer, Move> alphaBetaMax(int depth, int alpha, int beta)
	{

		Pair<Integer, Move> bestMovement;
		if (depth == 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove();
		if (movePossible.size() == 0)
		{
			return new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.POSITIVE_INFINITY, null);
		for (Move mov : movePossible)
		{
			setCompteur(getCompteur() + 1);
			Pieces piece = mov.getPieces();
			Point anciennePosition = piece.getEmplacement();
			Pieces reverseMove = plateau.deplacementProg(piece, mov.getPoint());

			int evaluation = alphaBetaMin(depth - 1, alpha, beta).getKey();

			plateau.unMakeMove(anciennePosition, piece, reverseMove);

			if (evaluation >= beta)
			{
				beta = evaluation;
				return new Pair<Integer, Move>(beta, mov);
			}
			if (evaluation > alpha)
			{
				alpha = evaluation;
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			plateau.actualiserTeam();

		}
		return bestMovement;

	}

	private Pair<Integer, Move> alphaBetaMin(int depth, int alpha, int beta)
	{

		Pair<Integer, Move> bestMovement;
		if (depth == 0)
		{
			return new Pair<Integer, Move>(-evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove(); // mouvement jouable
		if (movePossible.size() == 0) // regarde si la liste est pas vide
		{
			return new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.POSITIVE_INFINITY, null); // instancier
																						// best
																						// move
		for (Move mov : movePossible) // pour chaque move
		{
			setCompteur(getCompteur() + 1);
			Pieces piece = mov.getPieces(); // prend 1 pieces
			Point anciennePosition = piece.getEmplacement();
			Pieces reverseMove = plateau.deplacementProg(piece, mov.getPoint());

			int evaluation = alphaBetaMax(depth - 1, beta, alpha).getKey();

			plateau.unMakeMove(anciennePosition, piece, reverseMove);

			if (evaluation <= beta)
			{
				beta = evaluation;
				return new Pair<Integer, Move>(beta, mov);
			}

			if (evaluation < alpha)
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			plateau.actualiserTeam();

		}
		return bestMovement;

	}

	private int evaluerEquipe() // compare les valeur des équipe
	{
		return compterValeur(plateau.getBlanc().getListePieces()) - compterValeur(plateau.getNoir().getListePieces());

	}

	public ArrayList<Move> generationMove()
	{
		ArrayList<Move> tousMoves = new ArrayList<Move>();
		ArrayList<Pieces> toutesLesPieces = new ArrayList<Pieces>();
		toutesLesPieces.addAll(plateau.getBlanc().getListePieces());
		toutesLesPieces.addAll(plateau.getNoir().getListePieces());

		for (Pieces pieces : toutesLesPieces)
		{
			for (Point point : pieces.getMouvementJouable())
			{
				tousMoves.add(new Move(pieces, point));
			}
		}
		return tousMoves;
	}

	private int compterValeur(ArrayList<Pieces> equipe) // compte les valeurs de
														// l'équipe
	{
		int valeur = 0;
		for (Pieces p : equipe)
		{
			valeur += p.getValeur();
		}
		return valeur;
	}

	public int MoveGenerationTest(int depth)
	{
		if (depth == 0)
		{
			return 1;
		}
		else
		{
			ArrayList<Move> tousMoves = generationMove();
			int nbmove = 0;

			for (Move move : tousMoves)
			{
				Point ancinennePosition = move.getPieces().getEmplacement();
				Pieces manger = plateau.deplacementProg(move.getPieces(), move.getPoint());
				nbmove += MoveGenerationTest(depth - 1);
				plateau.unMakeMove(ancinennePosition, move.getPieces(), manger);
			}
			return nbmove;
		}
	}

	public int getCompteur()
	{
		return compteur;
	}

	public void setCompteur(int compteur)
	{
		this.compteur = compteur;
	}

	public void setPlateau(Plateau plateau)
	{
		this.plateau = plateau;
	}
}
