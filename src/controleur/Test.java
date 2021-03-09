package controleur;

import java.awt.Point;
import java.util.ArrayList;

import controleur.Plateau.Equipe;
import modele.Pieces;
import modele.Tour;

public class Test
{
	public static void main(String[] args)
	{
		Tour blancT = new  Tour("T", true, new Point(1,1));
		ArrayList<Pieces> blanc = new ArrayList<Pieces>();
		blanc.add(blancT);
		ArrayList<Pieces> noir = new ArrayList<Pieces>();
		
		Plateau plat = new Plateau(blanc, noir);
		
		plat
		
	}
}
