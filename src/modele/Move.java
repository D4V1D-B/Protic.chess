package modele;

import java.awt.Point;

public class Move
{
	Pieces pieces;

	Point point;

	public Move(Pieces pieces, Point point)
	{
		this.pieces = pieces;
		this.point = point;
	}

	public Pieces getPieces()
	{
		return pieces;
	}

	public Point getPoint()
	{
		return point;
	}
}
