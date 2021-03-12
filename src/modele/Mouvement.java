package modele;

public class Mouvement
{
	private String pieceBouge;
	private String nouvelleEmplacement;
	private boolean isWhite;

	public Mouvement(String pieceBouge, String nouvelleEmplacement,
			boolean isWhite)
	{
		super();
		this.pieceBouge = pieceBouge;
		this.nouvelleEmplacement = nouvelleEmplacement;
		this.isWhite = isWhite;
	}

	public String getPieceBouge()
	{
		return pieceBouge;
	}

	public void setPieceBouge(String pieceBouge)
	{
		this.pieceBouge = pieceBouge;
	}

	public String getNouvelleEmplacement()
	{
		return nouvelleEmplacement;
	}

	public void setNouvelleEmplacement(String nouvelleEmplacement)
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
		return getPieceBouge() + " - " + getNouvelleEmplacement();
	}
}
