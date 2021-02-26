package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Pion extends Pieces
{

	private boolean aBouger;

	

	public Pion(String nom, boolean couleur, Point emplacement)
	{
		super(nom, couleur, emplacement);
		setaBouger(false);
	}

	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger(boolean aBouger)
	{
		this.aBouger = aBouger;
	}

	public void setMouvementPossible(Object[][] plateau,
			ArrayList<Point> positionEnnemie)
	{
		this.getMouvementPossible().clear();
		if (super.isWhite())
		{
			if (aBouger)
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x,
						this.getEmplacement().y - 1));
				if (positionEnnemie
						.equals(plateau[this.getEmplacement().x
								- 1][this.getEmplacement().y - 1])
						|| positionEnnemie
								.equals(plateau[this.getEmplacement().x
										+ 1][this.getEmplacement().y - 1]))
				{
					this.getMouvementPossible().add(new Point(this.getEmplacement().x - 1,
							this.getEmplacement().y - 1));
					this.getMouvementPossible().add(new Point(this.getEmplacement().x + 1,
							this.getEmplacement().y - 1));
				}
			}
			else
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x,
						this.getEmplacement().y - 2));
				setaBouger(true);
			}

		}
		else
		{
			if (aBouger)
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x,
						this.getEmplacement().y + 1));
				if (positionEnnemie
						.equals(plateau[this.getEmplacement().x
								+ 1][this.getEmplacement().y + 1])
						|| positionEnnemie
								.equals(plateau[this.getEmplacement().x
										- 1][this.getEmplacement().y + 1]))
				{
					this.getMouvementPossible().add(new Point(this.getEmplacement().x - 1,
							this.getEmplacement().y + 1));
					this.getMouvementPossible().add(new Point(this.getEmplacement().x + 1,
							this.getEmplacement().y + 1));
				}

			}
			else
			{
				this.getMouvementPossible().add(new Point(this.getEmplacement().x,
						this.getEmplacement().y + 2));
				setaBouger(true);
			}
		}

	}
}
