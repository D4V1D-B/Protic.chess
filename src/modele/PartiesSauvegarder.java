package modele;

import javafx.beans.property.SimpleStringProperty;

public class PartiesSauvegarder
{
	private SimpleStringProperty nom;
	private SimpleStringProperty positionFen;

	public PartiesSauvegarder(SimpleStringProperty nom,
			SimpleStringProperty positionFen)
	{
		super();
		this.nom = nom;
		this.positionFen = positionFen;
	}

	public SimpleStringProperty getNom()
	{
		return nom;
	}

	public void setNom(SimpleStringProperty nom)
	{
		this.nom = nom;
	}

	public SimpleStringProperty getPositionFen()
	{
		return positionFen;
	}

	public void setPositionFen(SimpleStringProperty positionFen)
	{
		this.positionFen = positionFen;
	}

}
