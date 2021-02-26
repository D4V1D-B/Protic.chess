package modele;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Pieces
{

	private String nom;
	private int valeur = 0;
	// si la pièce est morte
	private boolean morte = false;
	// si la pièce met le roi en échec.
	private boolean echec = false;
	// vrai = blanc
	private boolean couleur = true;
	private Point emplacement;
	
	private ArrayList<Point> mouvementPossible;

	public Pieces(String nom, boolean couleur, Point position)
	{
		setNom(nom);
		setCouleur(couleur);
		setEmplacement(position);
		mouvementPossible = new ArrayList<Point>();
	}

	public Point getEmplacement()
	{
		return emplacement;
	}

	public void setEmplacement(Point emplacement)
	{
		this.emplacement = emplacement;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public int getValeur()
	{
		return valeur;
	}

	public void setValeur(int valeur)
	{
		this.valeur = valeur;
	}

	public boolean isWhite()
	{
		return couleur;
	}

	public void setCouleur(boolean couleur)
	{
		this.couleur = couleur;
	}
	
	public ArrayList<Point> getMouvementPossible()
	{
		return mouvementPossible;
	}

}