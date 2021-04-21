package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mouvement
{
	private SimpleStringProperty pieceBouge;
	private SimpleStringProperty nouvelleEmplacement;
	private boolean isWhite;
	private SimpleStringProperty fen;

	public Mouvement(SimpleStringProperty pieceBouge,
			SimpleStringProperty nouvelleEmplacement, SimpleStringProperty fen, boolean isWhite)
	{
		super();
		this.pieceBouge = pieceBouge;
		this.nouvelleEmplacement = nouvelleEmplacement;
		this.isWhite = isWhite;
		this.fen = fen;
		
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
		String c = "B";
		if (isWhite)
		{
			c = "W";
		}

		return c + getPieceBouge().get() + " - "
				+ getNouvelleEmplacement().get();
	}
	public String toStringCastle()
	{
		String c = "B";
		if (isWhite)
		{
			c = "W";
		}
		return c + " " + getNouvelleEmplacement().get();
		
	}
}
