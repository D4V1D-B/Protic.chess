package controleur;

import java.awt.Point;
import java.util.ArrayList;

import javafx.util.Pair;
import modele.Move;
import modele.Pieces;

public class Bot
{
	public Plateau plateau;

	public Move jouerBot(Plateau plateau)
	{
		this.plateau = plateau; // prend le plateau
		//return alphaBeta(3, (int) Double.NEGATIVE_INFINITY, (int) Double.POSITIVE_INFINITY).getValue();
		return minMax(3).getValue();

	}

	
	private Pair<Integer, Move> minMax(int depth)
	{
		if (!plateau.getNoir().getMouvementJouable().equals(null))
		{
			return rechercheMax(depth);
		}
		else
		{
			return rechercheMin(depth);
		}

	}

	private Pair<Integer, Move> rechercheMax(int depth)
	{
		Pair<Integer, Move> bestMovement;
		if (depth <= 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove(); // mouvement jouable
		if (movePossible.size() == 0) // regarde si la liste est pas vide
		{
			return new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null); // instancier
																						// best
																						// move
		for (Move mov : movePossible) // pour chaque move
		{
			Pieces piece = mov.getPieces(); // prend 1 pieces
			Point anciennePosition = piece.getEmplacement(); // ancienne
																// emplacement

			Pieces reverseMove = plateau.deplacementProg(piece, mov.getPoint()); // ancien
																		// emplacement
																		// /
																		// pieces
																		// pour
																		// faire
																		// le
																		// déplacememnt
			int evaluation = (rechercheMin(depth - 1)).getKey(); // prend le
																	// meilleur
																	// move des
																	// noirs et
																	// regarde
																	// le
																	// meilleurs
																	// des
																	// blanc.
			if (evaluation > bestMovement.getKey()) // vérifie si le movement
													// actuelle c'est le
													// meilleur
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			plateau.unMakeMove(anciennePosition, piece, reverseMove);
			plateau.actualiserTeam();

		}

		return bestMovement;

	}

	private Pair<Integer, Move> rechercheMin(int depth)
	{

		Pair<Integer, Move> bestMovement;
		if (depth <= 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove(); // mouvement jouable
		if (movePossible.size() == 0) // regarde si la liste est pas vide
		{
			return new Pair<Integer, Move>((int) Double.POSITIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.POSITIVE_INFINITY, null); // instancier
																						// best
																						// move
		for (Move mov : movePossible) // pour chaque move
		{
			Pieces piece = mov.getPieces(); // prend 1 pieces
			Point anciennePosition = piece.getEmplacement(); // ancienne
																// emplacement

			Pieces reverseMove = plateau.deplacementProg(piece, mov.getPoint()); // ancien
																		// emplacement
																		// /
																		// pieces
																		// pour
																		// faire
																		// le
																		// déplacememnt
			int evaluation = (rechercheMax(depth - 1)).getKey(); // prend le
																	// meilleur
																	// move des
																	// noirs et
																	// regarde
																	// le
																	// meilleurs
																	// des
																	// blanc.
			if (evaluation < bestMovement.getKey()) // vérifie si le movement
													// actuelle c'est le
													// meilleur
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			plateau.unMakeMove(anciennePosition, piece, reverseMove);

			plateau.actualiserTeam();

		}

		return bestMovement;

	}
	
	private Pair<Integer, Move> alphaBeta(int depth, int alpha, int beta)
	{

		Pair<Integer, Move> bestMovement;
		if (depth == 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove(); // mouvement jouable
		if (movePossible.size() == 0) // regarde si la liste est pas vide
		{
			return new Pair<Integer, Move>((int)Double.NEGATIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int)Double.POSITIVE_INFINITY, null); // instancier
																						// best
																						// move
		for (Move mov : movePossible) // pour chaque move
		{
			Pieces piece = mov.getPieces(); // prend 1 pieces
			Point anciennePosition = piece.getEmplacement();
			Pieces reverseMove = plateau.deplacementProg(piece, mov.getPoint());

			int evaluation = -(alphaBeta(depth - 1, -beta, -alpha)).getKey();

			plateau.unMakeMove(anciennePosition, piece, reverseMove);
			
			if (evaluation > alpha)
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}
			//ca ca choke
//			if (evaluation >= beta)
//			{
//				System.out.println("2");
//				return new Pair<Integer, Move>(beta, mov);
//			}

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

	public void setPlateau(Plateau plateau)
	{
		this.plateau = plateau;
	}
}
