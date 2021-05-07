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
		return MinMax(3).getValue(); // retourne le déplacement pour l'image seulement

	}

	private Pair<Integer, Move> MinMax(int depth)
	{
		if (!plateau.getNoir().getMouvementJouable().equals(null))
		{
			return rechercheMax(3);
		}
		else
		{
			return rechercheMin(3);
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

			Pieces reverseMove = deplacement(anciennePosition, piece); // ancien
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
			if (evaluation> bestMovement.getKey()) // vérifie si le movement
													// actuelle c'est le
													// meilleur
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			plateau.unMakeMove(bestMovement.getValue().getPoint(), piece, reverseMove );
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

			Pieces reverseMove = deplacement(anciennePosition, piece); // ancien
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

			
			plateau.unMakeMove(bestMovement.getValue().getPoint(), piece, reverseMove);

			plateau.actualiserTeam();

		}

		return bestMovement;

	}

	private Pieces deplacement(Point anciennePosition, Pieces piecesDeplacer)
	{
		// On enleve la pieces de son ancien deplacement
		plateau.getPlateau()[anciennePosition.x][anciennePosition.y] = null;

		// On déplace la pieces et on store ce qui a été bouffe
		Pieces manger = plateau.deplacerPieces(piecesDeplacer);

		if (piecesDeplacer.getClass().toString().contains("Pion")
				&& (anciennePosition.y - piecesDeplacer.getEmplacement().y == 2
						|| anciennePosition.y - piecesDeplacer.getEmplacement().y == -2))
		{
			plateau.ajouterEnPassant(anciennePosition, piecesDeplacer);
		}

		
		return manger;
	}

	private int evaluerEquipe() // compare les valeur des équipe
	{
		return compterValeur(plateau.getBlanc().getListePieces()) - compterValeur(plateau.getNoir().getListePieces());

	}

	public ArrayList<Move> generationMove() // créer un arrayList de
											// Pieces/Point TODO movementjouable
											// liste de move?

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