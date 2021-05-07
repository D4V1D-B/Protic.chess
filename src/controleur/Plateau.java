package controleur;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau.Equipe;
import modele.Pieces;
import modele.Tour;
import modele.Triplets;
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
		actualiserToutLesMouvementJouable(false);
	}

	public Equipe getNoir()
	{
		return noir;
	}

	public Equipe getBlanc()
	{
		return blanc;
	}

	public boolean getEchecMathBlanc()
	{
		return blanc.isEchec() && blanc.getMouvementJouable().isEmpty();
	}

	public boolean getEchecMathNoir()
	{
		return noir.isEchec() && noir.getMouvementJouable().isEmpty();
	}

	public boolean partieNulle()
	{
		return noir.getMouvementJouable().isEmpty() && blanc.getMouvementJouable().isEmpty();
	}

	public Pieces[][] refreshPlateau(ArrayList<Pieces> pieceBlanc, ArrayList<Pieces> pieceNoir)
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

	public Pieces deplacementProg(Pieces pieces, Point deplacement)
	{
		Point anciennePosition = pieces.getEmplacement();
		// On enleve la pieces de son ancien deplacement
		plateau[anciennePosition.x][anciennePosition.y] = null;
		pieces.setEmplacement(deplacement);
		Pieces manger = deplacerPieces(pieces);

		actualiserToutLesMouvementJouable(pieces.isWhite());

		if (pieces.getClass().toString().contains("Pion")
				&& Math.abs(anciennePosition.y - pieces.getEmplacement().y) == 2)
		{
			ajouterEnPassant(anciennePosition, pieces);
		}
		return manger;
	}

	public void unMakeMove(Point newPosition, Pieces pieces, Pieces manger)
	{
		Point anciennePosition = pieces.getEmplacement();
		pieces.setEmplacement(newPosition);
		// On enleve la pieces de son ancien deplacement
		plateau[anciennePosition.x][anciennePosition.y] = manger;
		plateau[newPosition.x][newPosition.y] = pieces;

		actualiserToutLesMouvementJouable(!pieces.isWhite());

		if (pieces.getClass().toString().contains("Pion")
				&& Math.abs(anciennePosition.y - pieces.getEmplacement().y) == 2)
		{
			ajouterEnPassant(anciennePosition, pieces);
		}
	}

	public void ajouterEnPassant(Point anciennePosition, Pieces piecesDeplacer)
	{
		if (piecesDeplacer.isWhite())
		{
			if (piecesDeplacer.getEmplacement().x - 1 >= 0
					&& plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y] != null
					&& plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y].getClass()
							.toString().contains("Pion")
					&& !plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y].isWhite())
			{
				plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y].getMouvementJouable()
						.add(new Point(anciennePosition.x, anciennePosition.y + 1));
			}

			if (piecesDeplacer.getEmplacement().x + 1 <= 7
					&& plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y] != null
					&& plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y].getClass()
							.toString().contains("Pion")
					&& !plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y].isWhite())
			{
				plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y].getMouvementJouable()
						.add(new Point(anciennePosition.x, anciennePosition.y + 1));
			}
		}
		else
			if (!piecesDeplacer.isWhite())
			{
				if (piecesDeplacer.getEmplacement().x - 1 >= 0
						&& plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y] != null
						&& plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y].getClass()
								.toString().contains("Pion")
						&& plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y].isWhite())
				{
					plateau[piecesDeplacer.getEmplacement().x - 1][piecesDeplacer.getEmplacement().y]
							.getMouvementJouable().add(new Point(anciennePosition.x, anciennePosition.y - 1));
				}
				if (piecesDeplacer.getEmplacement().x + 1 <= 7
						&& plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y] != null
						&& plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y].getClass()
								.toString().contains("Pion")
						&& plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y].isWhite())
				{
					plateau[piecesDeplacer.getEmplacement().x + 1][piecesDeplacer.getEmplacement().y]
							.getMouvementJouable().add(new Point(anciennePosition.x, anciennePosition.y - 1));
				}
			}
	}

	public Pieces deplacerPieces(Pieces piecesDeplacer)
	{
		Pieces temp = null;
		if (plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y] == null
				&& piecesDeplacer.getClass().toString().contains("Pion"))
		{
			if (piecesDeplacer.isWhite())
			{
				temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y - 1];
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y - 1] = null;
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y] = piecesDeplacer;
			}
			else
			{
				temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y + 1];
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y + 1] = null;
				plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y] = piecesDeplacer;
			}
		}
		else
		{
			temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y];
			plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y] = piecesDeplacer;
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

		actualiserToutLesMouvementJouable(roiDeplacer.isWhite());

		return rockEstValide;
	}

	public Pieces replacerPieces(Pieces piecesDeplacer)
	{
		Pieces temp = plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y];
		plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer.getEmplacement().y] = piecesDeplacer;

		if (temp != null)
		{
			actualiserTeam();
		}

		return temp;
	}

	public void actualiserToutLesMouvementJouable(boolean tour)
	{
		ArrayList<Triplets> piecesNoirPin = new ArrayList<Triplets>();
		ArrayList<Triplets> piecesBlanchePin = new ArrayList<Triplets>();
		Triplets temp = null;
		blanc.attaquePossible.clear();
		noir.attaquePossible.clear();
		blanc.clearMouvementJouable();
		noir.clearMouvementJouable();
		blanc.mouvementJouable.clear();
		noir.mouvementJouable.clear();
		blanc.setEchec(false);
		noir.setEchec(false);
		Point positionRoiNoir = noir.getPositionRoi();

		for (Pieces p : blanc.listePiece)
		{
			switch (p.getClass().toString())
			{
				case "class modele.Tour":
					temp = ((Tour) p).setMouvementPossible(plateau, positionRoiNoir);
					if (!tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesNoirPin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Cavalier":
					temp = ((Cavalier) p).setMouvementPossible(plateau, positionRoiNoir);
					if (!tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesNoirPin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Fou":
					temp = ((Fou) p).setMouvementPossible(plateau, positionRoiNoir);
					if (!tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesNoirPin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Reine":
					temp = ((Reine) p).setMouvementPossible(plateau, positionRoiNoir);
					if (!tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesNoirPin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Pion":
					temp = ((Pion) p).setMouvementPossible(plateau, positionRoiNoir);
					if (!tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesNoirPin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Roi":
					((Roi) p).setMouvementPossible(plateau);
					if (p.getEmplacement() != blanc.getPositionRoi2())
					{
						blanc.setPositionRoi(p.getEmplacement());
					}
					break;
			}
		}
		if (tour)
			blanc.actualiserAttaquePossible();

		Point positionRoiBlanc = blanc.getPositionRoi();

		for (Pieces p : noir.listePiece)
		{
			switch (p.getClass().toString())
			{
				case "class modele.Tour":
					temp = ((Tour) p).setMouvementPossible(plateau, positionRoiBlanc);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesBlanchePin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Cavalier":
					temp = ((Cavalier) p).setMouvementPossible(plateau, positionRoiBlanc);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesBlanchePin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Fou":
					temp = ((Fou) p).setMouvementPossible(plateau, positionRoiBlanc);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesBlanchePin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Reine":
					temp = ((Reine) p).setMouvementPossible(plateau, positionRoiBlanc);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesBlanchePin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Pion":
					temp = ((Pion) p).setMouvementPossible(plateau, positionRoiBlanc);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (temp != null)
					{
						piecesBlanchePin.add(temp);
						temp = null;
					}
					break;
				case "class modele.Roi":
					((Roi) p).setMouvementPossible(plateau);
					if (tour)
						p.getMouvementJouable().addAll(p.getMouvementPossible());
					if (p.getEmplacement() != noir.getPositionRoi2())
					{
						noir.setPositionRoi(p.getEmplacement());
					}
					break;
			}
		}
		if (!tour)
			noir.actualiserAttaquePossible();

		if (tour)
			filtrerCoup(piecesNoirPin, blanc, noir);

		if (!tour)
			filtrerCoup(piecesNoirPin, noir, blanc);

		if (!tour)
			blanc.actualiserMouvementJouable();

		if (tour)
			noir.actualiserMouvementJouable();
	}

	public void filtrerCoup(ArrayList<Triplets> piecesPin, Equipe equipeAttaque, Equipe equipeDefense)
	{
		ArrayList<Triplets> echec = new ArrayList<Triplets>();

		for (Triplets p : piecesPin)
		{
			if (p.getDefendant().getEmplacement() != equipeDefense.positionRoi)
			{
				switch (p.getState())
				{
					case 1:
					case 2:
						p.getDefendant().getMouvementJouable().clear();
						for (Point test : p.getDefendant().getMouvementPossible())
						{
							if (test.getY() == p.getAttaquant().getEmplacement().y)
							{
								p.getDefendant().getMouvementJouable().add(test);
							}
						}
						break;
					case 3:
					case 4:
						p.getDefendant().getMouvementJouable().clear();
						for (Point test : p.getDefendant().getMouvementPossible())
						{
							if (test.getX() == p.getAttaquant().getEmplacement().x)
							{
								p.getDefendant().getMouvementJouable().add(test);
							}
						}
						break;
					case 5:
					case 8:
						p.getDefendant().getMouvementJouable().clear();
						for (Point test : p.getDefendant().getMouvementPossible())
						{
							if (test.getX() - p.getAttaquant().getEmplacement().x == test.getY()
									- p.getAttaquant().getEmplacement().y)
							{
								p.getDefendant().getMouvementJouable().add(test);
							}
						}
						break;
					case 6:
					case 7:
						p.getDefendant().getMouvementJouable().clear();
						for (Point test : p.getDefendant().getMouvementPossible())
						{
							if (-(test.getX() - p.getAttaquant().getEmplacement().x) == test.getY()
									- p.getAttaquant().getEmplacement().y)
							{
								p.getDefendant().getMouvementJouable().add(test);
							}
						}
						break;
				}
			}
			else
			{
				echec.add(p);
			}
		}

		if (echec.size() == 1)
		{
			equipeDefense.setEchec(true);
			equipeDefense.clearMouvementJouable();
			ArrayList<Point> testage;
			switch (echec.get(0).getState())
			{
				case 0:
					for (Pieces p : equipeDefense.listePiece)
					{
						for (Point test : p.getMouvementPossible())
						{
							if (test == echec.get(0).getAttaquant().getEmplacement())
							{
								p.getMouvementJouable().add(test);
							}
						}
					}
					break;
				case 1:
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (test.getY() == equipeDefense.positionRoi.y
										&& test.getX() <= echec.get(0).getAttaquant().getEmplacement().x
										&& test.getX() > equipeDefense.positionRoi.x)
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 2:
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (test.getY() == equipeDefense.positionRoi.y
										&& test.getX() >= echec.get(0).getAttaquant().getEmplacement().x
										&& test.getX() < equipeDefense.positionRoi.x)
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 3:
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (test.getX() == equipeDefense.positionRoi.x
										&& test.getY() <= echec.get(0).getAttaquant().getEmplacement().y
										&& test.getY() > equipeDefense.positionRoi.y)
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 4:
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (test.getX() == equipeDefense.positionRoi.x
										&& test.getY() >= echec.get(0).getAttaquant().getEmplacement().y
										&& test.getY() < equipeDefense.positionRoi.y)
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 5:
					testage = new ArrayList<Point>();
					for (int i = echec.get(0).getAttaquant().getEmplacement().x, j = echec.get(0).getAttaquant()
							.getEmplacement().y; i > equipeDefense.positionRoi.x; i--, j--)
					{
						testage.add(new Point(i, j));
					}
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (testage.contains(test))
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 8:
					testage = new ArrayList<Point>();
					for (int i = echec.get(0).getAttaquant().getEmplacement().x, j = echec.get(0).getAttaquant()
							.getEmplacement().y; i < equipeDefense.positionRoi.x; i++, j++)
					{
						testage.add(new Point(i, j));
					}
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (testage.contains(test))
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 6:
					testage = new ArrayList<Point>();
					for (int i = echec.get(0).getAttaquant().getEmplacement().x, j = echec.get(0).getAttaquant()
							.getEmplacement().y; i > equipeDefense.positionRoi.x; i--, j++)
					{
						testage.add(new Point(i, j));
					}
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (testage.contains(test))
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
				case 7:
					testage = new ArrayList<Point>();
					for (int i = echec.get(0).getAttaquant().getEmplacement().x, j = echec.get(0).getAttaquant()
							.getEmplacement().y; i < equipeDefense.positionRoi.x; i++, j--)
					{
						testage.add(new Point(i, j));
					}
					for (Pieces p : equipeDefense.listePiece)
					{
						if (!p.getEmplacement().equals(equipeDefense.positionRoi))
							for (Point test : p.getMouvementPossible())
							{
								if (testage.contains(test))
								{
									p.getMouvementJouable().add(test);
								}
							}
					}
					break;
			}
		}
		else
			if (!echec.isEmpty())
			{
				equipeDefense.clearMouvementJouable();
				equipeDefense.setEchec(true);
			}

		for (Point p : trouverPieces(equipeDefense.getPositionRoi()).getMouvementPossible())
		{
			if (!equipeAttaque.getAttaquePossible().contains(p))
			{
				if((p.getX()-1==equipeDefense.getPositionRoi().x||p.getX()+1==equipeDefense.getPositionRoi().x)||echec.isEmpty())
				{
					trouverPieces(equipeDefense.getPositionRoi()).getMouvementJouable().add(p);
				}
			}
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
		plateau[changement.getEmplacement().x][changement.getEmplacement().y] = changement;
		actualiserTeam();
		actualiserToutLesMouvementJouable(changement.isWhite());
	}

	public class Equipe
	{
		private ArrayList<Point> attaquePossible;
		private ArrayList<Point> mouvementJouable;
		private ArrayList<Pieces> listePiece;
		private Point positionRoi = new Point(-1, -1);
		boolean echec = false;

		public Equipe(ArrayList<Pieces> pieces)
		{
			listePiece = pieces;
			getPositionRoi();
			attaquePossible = new ArrayList<Point>();
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
				if (listePiece.get(i).getClass().toString().contains("Roi"))
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

		public void actualiserAttaquePossible()
		{
			for (Pieces p : listePiece)
			{
				if (!p.getClass().toString().contains("Pion"))
				{
					attaquePossible.addAll(p.getMouvementPossible());
				}
				else
				{
					attaquePossible.addAll(((Pion) p).getMouvementDangereux(plateau));
				}
			}
		}

		public void actualiserMouvementJouable()
		{
			for (Pieces p : listePiece)
			{
				mouvementJouable.addAll(p.getMouvementJouable());
			}
		}

		public void clearMouvementJouable()
		{
			for (Pieces p : listePiece)
			{
				p.getMouvementJouable().clear();
			}
		}

		public ArrayList<Point> getAttaquePossible()
		{
			return attaquePossible;
		}

		public ArrayList<Point> getMouvementJouable()
		{
			return mouvementJouable;
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

		public Point getPositionRoi2()
		{
			return positionRoi;
		}

		public void setPositionRoi(Point setPositionRoi)
		{
			positionRoi = setPositionRoi;
		}

		public boolean isEchec()
		{
			return echec;
		}

		public void setEchec(boolean echec)
		{
			this.echec = echec;
		}
	}

}
