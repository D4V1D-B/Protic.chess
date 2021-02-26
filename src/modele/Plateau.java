package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Plateau
{

	private Pieces[][] plateau;

	public Plateau(Pieces[][] newPlateau)
	{
		if (isPlateauValide(newPlateau))
			this.plateau = newPlateau;
	}

	public Plateau(ArrayList<Pieces> pieceBlanc, ArrayList<Pieces> pieceNoir)
	{
		refreshPlateau(pieceBlanc, pieceNoir);
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

	public Pieces[][] refreshPlateauDeplacement(Point anciennePosition,
			Pieces piecesDeplacer)
	{
		plateau[anciennePosition.x][anciennePosition.y] = null;
		plateau[piecesDeplacer.getEmplacement().x][piecesDeplacer
				.getEmplacement().y] = piecesDeplacer;
		return plateau;
	}

	private boolean isPlateauValide(Pieces[][] plateauVerify)
	{
		boolean verification = true;
		for (int i = 0; i < plateauVerify.length; i++)
		{
			for (int j = 0; j < plateauVerify[i].length; j++)
			{
				verification = i < 8 || j < 8;
			}
		}
		return verification;
	}

	public Pieces[][] getPlateau()
	{
		return plateau;
	}

	public ArrayList<Point> getVoidSpace()
	{
		ArrayList<Point> positionVide = new ArrayList<Point>();

		for (int i = 0; i < plateau.length; i++)
		{
			for (int j = 0; j < plateau[i].length; j++)
			{
				if (plateau[i][j] == null)
				{
					positionVide.add(new Point(i, j));
				}
			}
		}
		return positionVide;
	}

	public ArrayList<Point> getFillSpace()
	{
		ArrayList<Point> positionPlein = new ArrayList<Point>();

		for (int i = 0; i < plateau.length; i++)
		{
			for (int j = 0; j < plateau[i].length; j++)
			{
				if (plateau[i][j] != null)
				{
					positionPlein.add(new Point(i, j));
				}
			}
		}
		return positionPlein;
	}

	public Pieces trouverPieces(Point temp)
	{
		return plateau[temp.x][temp.y];
	}
}
