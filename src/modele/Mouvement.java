package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mouvement
{
	private SimpleStringProperty pieceBouge;
	private SimpleStringProperty nouvelleEmplacement;
	private boolean isWhite;

	public Mouvement(SimpleStringProperty pieceBouge,
			SimpleStringProperty nouvelleEmplacement, boolean isWhite)
	{
		super();
		this.pieceBouge = pieceBouge;
		this.nouvelleEmplacement = nouvelleEmplacement;
		this.isWhite = isWhite;
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

	@Override
	public String toString()
	{
		String c = "N";
		if (isWhite)
		{
			c = "B";
		}

		return c + getPieceBouge().get() + " - "
				+ getNouvelleEmplacement().get();
	}
}
