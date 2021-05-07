package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

public class Mouvement
{
	private SimpleStringProperty pieceBouge;
	private SimpleStringProperty nouvelleEmplacement;
	
	private SimpleStringProperty fen;
	private String nomCompletDeLaPiece;
	String c;
	String determinant;

	public Mouvement(SimpleStringProperty pieceBouge, SimpleStringProperty nouvelleEmplacement,
			SimpleStringProperty fen)
	{
		super();
		this.pieceBouge = pieceBouge;
		this.nouvelleEmplacement = nouvelleEmplacement;

		this.fen = fen;
		String nom;

		switch (pieceBouge.get().toLowerCase())
		{
			case "r":
				nom = "tour ";
				break;
			case "n":
				nom = "cavalier ";
				break;
			case "b":
				nom = "fou ";
				break;
			case "q":
				nom = "reine ";
				break;
			case "k":
				nom = "roi ";
				break;

			default:
				nom = "pion ";
				break;

		}

		this.nomCompletDeLaPiece = nom;
		determinant = getNomCompletDeLaPiece() == "reine " || getNomCompletDeLaPiece() == "tour " ? "La " : "Le ";
		c = getNomCompletDeLaPiece() == "reine " || getNomCompletDeLaPiece() == "tour " ? "noire " : "noir ";
		if (Character.isUpperCase(pieceBouge.get().charAt(0)))
		{
			c = getNomCompletDeLaPiece() == "reine " || getNomCompletDeLaPiece() == "tour " ? "blanche " : "blanc ";
		}
	}

	public String getNomCompletDeLaPiece()
	{
		return nomCompletDeLaPiece;
	}

	public StringProperty getNouvelleEmplacement()
	{
		return nouvelleEmplacement;
	}

	@Override
	public String toString()
	{

		return determinant + getNomCompletDeLaPiece() + c + "se déplace sur " + getNouvelleEmplacement().get() + ".";
	}

	public String toStringCastle()
	{

		return determinant + getNomCompletDeLaPiece() + c + "castle.";

	}

	public StringProperty getFen()
	{

		return this.fen;
	}

}
