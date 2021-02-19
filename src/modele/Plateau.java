package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Plateau {

	private Object[][] plateau;
	
	public Plateau(Object[][] newPlateau)
	{
		if(isPlateauValide(newPlateau))
		this.plateau = newPlateau;
	}
	
	private boolean isPlateauValide(Object[][] plateauVerify)
	{
		boolean verification = true;
		for(int i = 0; i<plateauVerify.length;i++)
		{
			for(int j = 0; j<plateauVerify[i].length;j++)
			{
				verification= i < 8|| j < 8;
			}
		}
		return verification;
	}
	
	public Object[][] getPlateau()
	{
		return plateau;
	}
	
	public void setPlateau(Object[][] newPlateau)
	{
		if(isPlateauValide(newPlateau))
		plateau = newPlateau;
	}
	
	public ArrayList<Point> getVoidSpace()
	{
		ArrayList<Point> positionVide = new ArrayList<Point>();
		
		for(int i = 0; i<plateau.length;i++)
		{
			for(int j = 0; j<plateau[i].length;j++)
			{
				if(plateau[i][j]==null)
				{
					positionVide.add(new Point(i,j));
				}
			}
		}
		return positionVide;
	}
	
	public ArrayList<Point> getFillSpace()
	{
		ArrayList<Point> positionPlein = new ArrayList<Point>();
		
		for(int i = 0; i<plateau.length;i++)
		{
			for(int j = 0; j<plateau[i].length;j++)
			{
				if(plateau[i][j]!=null)
				{
					positionPlein.add(new Point(i,j));
				}
			}
		}
		return positionPlein;
	}
}
