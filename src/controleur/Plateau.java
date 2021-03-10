package controleur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import modele.Pieces;
import modele.Tour;
import modele.Fou;
import modele.Roi;
import modele.Pion;
import modele.Reine;
import modele.Cavalier;

public class Plateau
{

	private Pieces[][] plateau;
	private Equipe blanc;
	private Equipe noir;

	/**
	 * Pour des tests
	 * 
	 * @param plateau pour des test. (Surtout vide)
	 */
	public Plateau(Pieces[][] newPlateau)
	{
		this.plateau = newPlateau;
	}

	public Plateau(ArrayList<Pieces> pieceBlanc, ArrayList<Pieces> pieceNoir)
	{
		refreshPlateau(pieceBlanc, pieceNoir);
		blanc = new Equipe(pieceBlanc);
		noir = new Equipe(pieceNoir);
	}

	public Pieces[][] refreshPlateau(ArrayList<Pieces> pieceBlanc,
			ArrayList<Pieces> pieceNoir)
	{
		ArrayList<Pieces> allPieces = new ArrayList<Pieces>();
		allPieces.addAll(pieceBlanc);
		allPieces.addAll(pieceNoir);
		plateau = new Pieces[8][8];
		for (Pieces p : allPieces)
		{
			plateau[p.getEmplacement().x][p.getEmplacement().y] = p;
		}
		return plateau;
	}

	public Pieces[][] refreshDeplacement(Point anciennePosition,
			Pieces piecesDeplacer)
	{
		plateau[anciennePosition.x][anciennePosition.y] = null;

		if (plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y] != null)
		{
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = piecesDeplacer;
			actualiserTeam();
		}
		else
		{
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = piecesDeplacer;
		}

		blanc.actualiserMouvementPossible();
		noir.actualiserMouvementPossible();
		return plateau;
	}

	public void actualiserTeam()
	{
		blanc.clear();
		noir.clear();

		for (Pieces[] row : plateau)
		{
			for (Pieces p : row)
			{
				if (p != null)
				{
					if (p.isWhite())
					{
						blanc.add(p);
					}
					else
					{
						noir.add(p);
					}
				}
			}
		}
	}

	public Pieces[][] getPlateau()
	{
		return plateau;
	}

	public Pieces trouverPieces(Point position)
	{
		return plateau[position.x][position.y];
	}

	public class Equipe
	{
		private Set<Point> mouvementPossible;
		private ArrayList<Pieces> listePiece;

		public Equipe(ArrayList<Pieces> pieces)
		{
			listePiece = pieces;
			getPositionRoi();
			mouvementPossible = new HashSet<Point>();
			actualiserMouvementPossible();
		}

		public int indexOfKing()
		{
			int index = 0;
			for (int i = 0; i < listePiece.size(); i++)
			{
				if (listePiece.get(i).getClass().toString()
						.equals("class modele.Roi"))
				{
					index = i;
				}
			}

			return index;
		}

		public Point getPositionRoi()
		{
			return listePiece.get(indexOfKing()).getEmplacement();

		}

		public void add(Pieces piece)
		{
			listePiece.add(piece);
		}

		public void clear()
		{
			listePiece.clear();
		}

		public void actualiserMouvementPossible()
		{

			for (Pieces p : listePiece)
			{
				switch (p.getClass().toString())
				{
					case "class modele.Tour":
						((Tour) p).setMouvementPossible(plateau);
						break;
					case "class modele.Cavalier":
						((Cavalier) p).setMouvementPossible(plateau);
						break;
					case "class modele.Fou":
						((Fou) p).setMouvementPossible(plateau);
						break;
					case "class modele.Reine":
						((Reine) p).setMouvementPossible(plateau);
						break;
					case "class modele.Pion":
						((Pion) p).setMouvementPossible(plateau);
						break;
					case "class modele.Roi":
						((Roi) p).setMouvementPossible(plateau);
						break;
				}

			}

			for (Pieces p : listePiece)
			{
				mouvementPossible.addAll(p.getMouvementPossible());
			}
		}

		public Set<Point> getMouvementPossible()
		{
			return mouvementPossible;
		}

		public boolean vérifierÉchec(ArrayList<Point> mouvementPossibleEnemy)
		{
			boolean roiEchec = false;

			for (Point p : mouvementPossibleEnemy)
			{
				if (roiEchec != true)
				{
					roiEchec = this.getPositionRoi().equals(p);
				}
			}

			return roiEchec;
		}

		public ArrayList<Point> getPositionEquipe()
		{
			ArrayList<Point> temp = new ArrayList<Point>();

			for (Pieces p : listePiece)
			{
				temp.add(p.getEmplacement());
			}

			return temp;
		}

	}
}

//
// public ArrayList<Point> getVoidSpace()
// {
// ArrayList<Point> positionVide = new ArrayList<Point>();
//
// for (int i = 0; i < plateau.length; i++)
// {
// for (int j = 0; j < plateau[i].length; j++)
// {
// if (plateau[i][j] == null)
// {
// positionVide.add(new Point(i, j));
// }
// }
// }
// return positionVide;
// }
//
// public ArrayList<Point> getFillSpace()
// {
// ArrayList<Point> positionPlein = new ArrayList<Point>();
//
// for (int i = 0; i < plateau.length; i++)
// {
// for (int j = 0; j < plateau[i].length; j++)
// {
// if (plateau[i][j] != null)
// {
// positionPlein.add(new Point(i, j));
// }
// }
// }
// return positionPlein;
// }
