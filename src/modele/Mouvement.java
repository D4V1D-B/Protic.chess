package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mouvement
{
	private SimpleStringProperty pieceBouge;
	private SimpleStringProperty nouvelleEmplacement;
	private boolean isWhite;
	private SimpleStringProperty fen;
	private String nomCompletDeLaPiece;

	public Mouvement(SimpleStringProperty pieceBouge,
			SimpleStringProperty nouvelleEmplacement, SimpleStringProperty fen,
			boolean isWhite)
	{
		super();
		this.pieceBouge = pieceBouge;
		this.nouvelleEmplacement = nouvelleEmplacement;
		this.isWhite = isWhite;
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

	}

	public String getNomCompletDeLaPiece()
	{
		return nomCompletDeLaPiece;
	}

	public StringProperty getPieceBouge()
	{
		return pieceBouge;
	}

	public void setPieceBouge(SimpleStringProperty pieceBouge)
	{
		this.pieceBouge = pieceBouge;
	}

	public StringProperty getNouvelleEmplacement()
	{
		return nouvelleEmplacement;
	}

	public void setNouvelleEmplacement(SimpleStringProperty nouvelleEmplacement)
	{
		this.nouvelleEmplacement = nouvelleEmplacement;
	}

	public boolean isWhite()
	{
		return isWhite;
	}

	public void setWhite(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	public SimpleStringProperty getFen()
	{
		return fen;
	}

	public void setFen(SimpleStringProperty fen)
	{
		this.fen = fen;
	}

	@Override
	public String toString()
	{
		String determinant = getNomCompletDeLaPiece() == "reine " ? "La "
				: "Le ";
		String c = getNomCompletDeLaPiece() == "reine " ? "noire " : "noir ";
		if (isWhite)
		{
			c = getNomCompletDeLaPiece() == "reine " ? "blanche " : "blanc ";
		}

		return determinant + getNomCompletDeLaPiece() + c + "se déplace sur "
				+ getNouvelleEmplacement().get() + ".";
	}

	public String toStringCastle()
	{
		String determinant = getNomCompletDeLaPiece() == "reine " ? "La "
				: "Le ";
		String c = getNomCompletDeLaPiece() == "reine " ? "noire " : "noir ";
		if (isWhite)
		{
			c = getNomCompletDeLaPiece() == "reine " ? "blanche " : "blanc ";
		}
		return determinant + getNomCompletDeLaPiece() + c + "castle sur "
				+ getNouvelleEmplacement() + ".";

	}

}
