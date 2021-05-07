package modele;

import java.awt.Point;
import java.util.ArrayList;

public class OperationSurUneMatrice
{
	public static ArrayList<Point> getVoidSpace(Pieces[][] plateau)
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
}