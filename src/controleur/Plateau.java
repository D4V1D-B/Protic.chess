package controleur;

import java.awt.Point;
import java.util.ArrayList;
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

	public Plateau(ArrayList<Pieces> pieceBlanc, ArrayList<Pieces> pieceNoir)
	{
		refreshPlateau(pieceBlanc, pieceNoir);
		blanc = new Equipe(pieceBlanc);
		noir = new Equipe(pieceNoir);
		actualiserToutLesMouvementJouable();
	}

	public Equipe getNoir()
	{
		return noir;
	}

	public Equipe getBlanc()
	{
		return blanc;
	}

	public boolean getEchecMath()
	{
		return noir.getMouvementJouable().size() == 0
				|| blanc.getMouvementJouable().size() == 0;
	}

	public boolean getEchec()
	{
		return noir.verifierEchec(blanc.mouvementPossible)
				|| blanc.verifierEchec(noir.mouvementPossible);
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

	public void deplacementProg(Point anciennePosition, Pieces piecesDeplacer)
	{
		// On enleve la pieces de son ancien deplacement
		plateau[anciennePosition.x][anciennePosition.y] = null;

		// On déplace la pieces et on store ce qui a été bouffer
		deplacerPieces(piecesDeplacer);

		actualiserToutLesMouvementJouable();

		if (piecesDeplacer.getClass().toString().contains("Pion")
				&& (anciennePosition.y - piecesDeplacer.getEmplacement().y == 2
						|| anciennePosition.y
								- piecesDeplacer.getEmplacement().y == -2))
		{
			ajouterEnPassant(anciennePosition, piecesDeplacer);
		}
	}

	public void ajouterEnPassant(Point anciennePosition, Pieces piecesDeplacer)
	{
		if (piecesDeplacer.isWhite())
		{
			if (piecesDeplacer.getEmplacement().x - 1 >= 0
					&& plateau[piecesDeplacer.getEmplacement().x
							- 1][piecesDeplacer.getEmplacement().y] != null
					&& plateau[piecesDeplacer.getEmplacement().x
							- 1][piecesDeplacer.getEmplacement().y].getClass()
									.toString().contains("Pion")
					&& !plateau[piecesDeplacer.getEmplacement().x
							- 1][piecesDeplacer.getEmplacement().y].isWhite())
			{
				plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer
						.getEmplacement().y].getMouvementJouable()
								.add(new Point(anciennePosition.x,
										anciennePosition.y + 1));
			}

			if (piecesDeplacer.getEmplacement().x + 1 <= 7
					&& plateau[piecesDeplacer.getEmplacement().x
							+ 1][piecesDeplacer.getEmplacement().y] != null
					&& plateau[piecesDeplacer.getEmplacement().x
							+ 1][piecesDeplacer.getEmplacement().y].getClass()
									.toString().contains("Pion")
					&& !plateau[piecesDeplacer.getEmplacement().x
							+ 1][piecesDeplacer.getEmplacement().y].isWhite())
			{
				plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer
						.getEmplacement().y].getMouvementJouable()
								.add(new Point(anciennePosition.x,
										anciennePosition.y + 1));
			}
		}
		else
			if (!piecesDeplacer.isWhite())
			{
				if (piecesDeplacer.getEmplacement().x - 1 >= 0
						&& plateau[piecesDeplacer.getEmplacement().x
								- 1][piecesDeplacer.getEmplacement().y] != null
						&& plateau[piecesDeplacer.getEmplacement().x
								- 1][piecesDeplacer.getEmplacement().y]
										.getClass().toString().contains("Pion")
						&& plateau[piecesDeplacer.getEmplacement().x
								- 1][piecesDeplacer.getEmplacement().y]
										.isWhite())
				{
					plateau[piecesDeplacer.getEmplacement().x
							- 1][piecesDeplacer.getEmplacement().y]
									.getMouvementJouable()
									.add(new Point(anciennePosition.x,
											anciennePosition.y - 1));
				}
				if (piecesDeplacer.getEmplacement().x + 1 <= 7
						&& plateau[piecesDeplacer.getEmplacement().x
								+ 1][piecesDeplacer.getEmplacement().y] != null
						&& plateau[piecesDeplacer.getEmplacement().x
								+ 1][piecesDeplacer.getEmplacement().y]
										.getClass().toString().contains("Pion")
						&& plateau[piecesDeplacer.getEmplacement().x
								+ 1][piecesDeplacer.getEmplacement().y]
										.isWhite())
				{
					plateau[piecesDeplacer.getEmplacement().x
							+ 1][piecesDeplacer.getEmplacement().y]
									.getMouvementJouable()
									.add(new Point(anciennePosition.x,
											anciennePosition.y - 1));
				}
			}
	}

	public Pieces deplacerPieces(Pieces piecesDeplacer)
	{
		Pieces temp = null;
		if (plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y] == null
				&& piecesDeplacer.getClass().toString().contains("Pion"))
		{
			if (piecesDeplacer.isWhite())
			{
				temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y - 1];
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y - 1] = null;
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y] = piecesDeplacer;
			}
			else
			{
				temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y + 1];
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y + 1] = null;
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
						.getEmplacement().y] = piecesDeplacer;
			}
		}
		else
		{
			temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y];
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
					.getEmplacement().y] = piecesDeplacer;
		}

		if (temp != null)
		{
			actualiserTeam();
		}

		return temp;
	}

	public Point refreshDeplacementRock(Roi roiDeplacer)
	{
		Point rockEstValide = null;

		if (roiDeplacer.getEmplacement().equals(new Point(2, 0)))
		{
			plateau[4][0] = null;
			plateau[2][0] = roiDeplacer;
			plateau[0][0].setEmplacement(new Point(3, 0));
			plateau[3][0] = plateau[0][0];
			plateau[0][0] = null;
			rockEstValide = new Point(3, 0);
		}
		else
			if (roiDeplacer.getEmplacement().equals(new Point(6, 0)))
			{
				plateau[4][0] = null;
				plateau[6][0] = roiDeplacer;
				plateau[7][0].setEmplacement(new Point(5, 0));
				plateau[5][0] = plateau[7][0];
				plateau[7][0] = null;
				rockEstValide = new Point(5, 0);
			}
			else
				if (roiDeplacer.getEmplacement().equals(new Point(2, 7)))
				{
					plateau[4][7] = null;
					plateau[2][7] = roiDeplacer;
					plateau[0][7].setEmplacement(new Point(3, 7));
					plateau[3][7] = plateau[0][7];
					plateau[0][7] = null;
					rockEstValide = new Point(3, 7);
				}
				else
					if (roiDeplacer.getEmplacement().equals(new Point(6, 7)))
					{
						plateau[4][7] = null;
						plateau[6][7] = roiDeplacer;
						plateau[7][7].setEmplacement(new Point(5, 7));
						plateau[5][7] = plateau[7][7];
						plateau[7][7] = null;
						rockEstValide = new Point(5, 7);
					}

		actualiserToutLesMouvementJouable();

		return rockEstValide;
	}

	public boolean validationDeplacement(Pieces pieces, Point posibiliter)
	{
		Point anciennePosition = pieces.getEmplacement();
		// Création d'une variable qui indique si le mouvement est valide
		boolean mouvementValide = true;
		// On enleve la pieces de son ancien deplacement
		plateau[pieces.getEmplacement().x][pieces.getEmplacement().y] = null;
		pieces.setEmplacement(posibiliter);
		// On déplace la pieces et on store ce qui a été bouffer
		Pieces temp = deplacerPieces(pieces);

		// on actualise les mouvement possible
		blanc.actualiserMouvementPossible();
		noir.actualiserMouvementPossible();

		// On regarde les échecs
		if (pieces.isWhite())
		{
			mouvementValide = !blanc.verifierEchec(noir.getMouvementPossible());
		}
		else
		{
			mouvementValide = !noir.verifierEchec(blanc.getMouvementPossible());
		}

		plateau[pieces.getEmplacement().x][pieces.getEmplacement().y] = temp;
		pieces.setEmplacement(anciennePosition);
		replacerPieces(pieces);
		actualiserTeam();
		blanc.actualiserMouvementPossible();
		noir.actualiserMouvementPossible();

		return mouvementValide;
	}

	public Pieces replacerPieces(Pieces piecesDeplacer)
	{
		Pieces temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y];
		plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y] = piecesDeplacer;

		if (temp != null)
		{
			actualiserTeam();
		}

		return temp;
	}

	public void actualiserToutLesMouvementJouable()
	{
		blanc.actualiserMouvementPossible();
		noir.actualiserMouvementPossible();
		blanc.mouvementJouable.clear();
		noir.mouvementJouable.clear();

		ArrayList<Point> pudebugcool = new ArrayList<Point>();
		ArrayList<Pieces> pudebugcool2 = new ArrayList<Pieces>();

		for (Pieces p : blanc.listePiece)
		{
			pudebugcool2.add(p);
		}

		for (Pieces p : pudebugcool2)
		{
			p.getMouvementJouable().clear();
			switch (p.getClass().toString())
			{
				case "class modele.Tour":
					pudebugcool.clear();
					for (Point posibiliter : ((Tour) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Cavalier":
					pudebugcool.clear();
					for (Point posibiliter : ((Cavalier) p)
							.getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Fou":
					pudebugcool.clear();
					for (Point posibiliter : ((Fou) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Reine":
					pudebugcool.clear();
					for (Point posibiliter : ((Reine) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Pion":
					pudebugcool.clear();
					for (Point posibiliter : ((Pion) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Roi":
					pudebugcool.clear();
					for (Point posibiliter : ((Roi) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;
			}
			blanc.getMouvementJouable().addAll(p.getMouvementJouable());
		}

		pudebugcool2.clear();
		for (Pieces p : noir.listePiece)
		{
			pudebugcool2.add(p);
		}

		for (Pieces p : pudebugcool2)
		{
			p.getMouvementJouable().clear();
			switch (p.getClass().toString())
			{
				case "class modele.Tour":
					pudebugcool.clear();
					for (Point posibiliter : ((Tour) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Cavalier":
					pudebugcool.clear();
					for (Point posibiliter : ((Cavalier) p)
							.getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Fou":
					pudebugcool.clear();
					for (Point posibiliter : ((Fou) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Reine":
					pudebugcool.clear();
					for (Point posibiliter : ((Reine) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Pion":
					pudebugcool.clear();
					for (Point posibiliter : ((Pion) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;

				case "class modele.Roi":
					pudebugcool.clear();
					for (Point posibiliter : ((Roi) p).getMouvementPossible())
					{
						pudebugcool.add(posibiliter);
					}

					for (Point posibiliter : pudebugcool)
					{
						if (validationDeplacement(p, posibiliter))
						{
							p.getMouvementJouable().add(posibiliter);
						}
					}
					break;
			}
			noir.getMouvementJouable().addAll(p.getMouvementJouable());
		}
	}

	public void actualiserTeam()
	{
		blanc.listePiece.clear();
		noir.listePiece.clear();

		for (Pieces[] row : plateau)
		{
			for (Pieces p : row)
			{
				if (p != null)
				{
					if (p.isWhite())
					{
						blanc.listePiece.add(p);
					}
					else
					{
						noir.listePiece.add(p);
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

	public void remplacerPion(Pieces changement)
	{
		plateau[changement.getEmplacement().x][changement
				.getEmplacement().y] = changement;
		actualiserTeam();
		actualiserToutLesMouvementJouable();

	}

	public boolean saveGame()
	{
		System.out.println();
		return false;

	}

	public class Equipe
	{
		private ArrayList<Point> mouvementPossible;
		private ArrayList<Point> mouvementJouable;
		private ArrayList<Pieces> listePiece;

		public Equipe(ArrayList<Pieces> pieces)
		{
			listePiece = pieces;
			getPositionRoi();
			mouvementPossible = new ArrayList<Point>();
			mouvementJouable = new ArrayList<Point>();
		}

		public ArrayList<Pieces> getListePieces()
		{
			return listePiece;
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

		public Roi getRoi()
		{
			return (Roi) listePiece.get(indexOfKing());
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
				if (p.getClass().toString() != "class modele.Pion")
				{
					mouvementPossible.addAll(p.getMouvementPossible());
				}
				else
				{
					mouvementPossible
							.addAll(((Pion) p).getMouvementDangereux(plateau));
				}
			}
		}

		public ArrayList<Point> getMouvementPossible()
		{
			return mouvementPossible;
		}

		public ArrayList<Point> getMouvementJouable()
		{
			return mouvementJouable;
		}

		public boolean verifierEchec(ArrayList<Point> mouvementPossibleEnemy)
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
