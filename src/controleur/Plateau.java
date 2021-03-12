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

	public boolean refreshDeplacement(Point anciennePosition,
			Pieces piecesDeplacer)
	{
		boolean mouvementValide = true;
		plateau[anciennePosition.x][anciennePosition.y] = null;

		Pieces temp = deplacerPieces(piecesDeplacer);

		blanc.actualiserMouvementPossible();
		noir.actualiserMouvementPossible();

		if (piecesDeplacer.isWhite())
		{
			mouvementValide = !blanc.vérifierÉchec(noir.getMouvementPossible());
		}
		else
		{
			mouvementValide = !noir.vérifierÉchec(blanc.getMouvementPossible());
		}

		if (!mouvementValide)
		{
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = temp;
			piecesDeplacer.setEmplacement(anciennePosition);
			deplacerPieces(piecesDeplacer);
		}

		return mouvementValide;
	}

	public Pieces deplacerPieces(Pieces piecesDeplacer)
	{
		Pieces temp = null;
		if (plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y] != null)
		{
			temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y];
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = piecesDeplacer;
			actualiserTeam();
		}
		else
		{
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = piecesDeplacer;
		}
		return temp;
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
		private ArrayList<Point> mouvementPossible;
		private ArrayList<Pieces> listePiece;

		public Equipe(ArrayList<Pieces> pieces)
		{
			listePiece = pieces;
			getPositionRoi();
			mouvementPossible = new ArrayList<Point>();
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
			mouvementPossible.clear();
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
				if (p.getClass().toString()!="class modele.Pion")
				{
					mouvementPossible.addAll(p.getMouvementPossible());
				}
				else
				{
					mouvementPossible.addAll(((Pion)p).getMouvementDangereux(plateau));
				}
			}
		}

		public ArrayList<Point> getMouvementPossible()
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
