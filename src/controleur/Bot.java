package controleur;

import java.awt.Point;
import java.util.ArrayList;

import javafx.util.Pair;
import modele.Move;
import modele.Pieces;


public class Bot
{
	public Plateau plateau;

	public String jouerBotTest(Plateau plateau)
	{
		this.plateau = plateau;  //prend le plateau
		String deplacement = "";  //pour déplacer l'image
		Move move = recherche(3).getValue(); //recherche du meilleur movement
		Pieces piece = move.getPieces();  //prend la pièce du meilleur mouvement
		deplacement += piece.getEmplacement().x + " " + piece.getEmplacement().y + move.getPieces().getNom() 
				+ move.getPoint().x + move.getPoint().y; //Déplacement l'image
		return deplacement; //retourne le déplacement pour l'image seulement

	}

	private Pair<Integer, Move> recherche(int depth)
	{
		Pair<Integer, Move> bestMovement;
		int valeur = 0;
		if (depth == 0)
		{
			return new Pair<Integer, Move>(evaluerEquipe(), null);
		}

		ArrayList<Move> movePossible = generationMove(); //mouvement jouable
		if (movePossible.size() == 0) //regarde si la liste est pas vide
		{
			return new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null);
		}

		bestMovement = new Pair<Integer, Move>((int) Double.NEGATIVE_INFINITY, null); //instancier best move
		for (Move mov : movePossible) //pour chaque move
		{
			Pieces piece = mov.getPieces(); //prend 1 pieces
			Point anciennePosition = piece.getEmplacement(); //ancienne emplacement

			Move reverseMove = deplacementDavid(anciennePosition, piece); //ancien emplacement / pieces pour faire le déplacememnt
			int evaluation = -(recherche(depth - 1)).getKey(); //prend le meilleur move des noirs et regarde le meilleurs des blanc.
			if (bestMovement.getKey() < evaluation)  //vérifie si le movement actuelle c'est le meilleur
			{
				bestMovement = new Pair<Integer, Move>(evaluation, mov);
			}

			piece.setEmplacement(anciennePosition); //inversé le movement
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

		if (piecesDeplacer.getClass().toString().contains("Pion")
				&& (anciennePosition.y - piecesDeplacer.getEmplacement().y == 2
						|| anciennePosition.y - piecesDeplacer.getEmplacement().y == -2))
		{
			plateau.ajouterEnPassant(anciennePosition, piecesDeplacer);
		}

		Move reverseMove = null;
		if (manger != null)
		{
			reverseMove = new Move(manger, manger.getEmplacement());
		}
		return reverseMove;
	}

	private int evaluerEquipe() // compare les valeur des équipe
	{
		return compterValeur(plateau.getBlanc().getListePieces()) - compterValeur(plateau.getNoir().getListePieces());

	}

	public ArrayList<Move> generationMove() // créer un arrayList de Pieces/Point TODO movementjouable liste de move?

	{
		ArrayList<Move> tousMoves = new ArrayList<Move>();

		for (Pieces pieces : plateau.getNoir().getListePieces())
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