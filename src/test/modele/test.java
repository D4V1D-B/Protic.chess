package test.modele;

import java.awt.Point;

import modele.Cavalier;
import modele.Pieces;

public class test
{

	public static void main(String[] args)
	{
		Pieces[][] vide = new Pieces[8][8];
		Pieces test = new Cavalier("cavalierB", true, new Point(4, 0));
		vide[7][7]=test;
		
		System.out.println(vide);

	}

}
