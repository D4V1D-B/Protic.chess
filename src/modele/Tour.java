package modele;

import java.awt.Point;

public class Tour extends Pieces
{

	private boolean aBouger;

	public Tour(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
		aBouger = false;
	}

	public Tour(String nom, boolean couleur, Point position, Pieces[][] plateau)
	{
		super(nom, couleur, position);
		aBouger = false;
	} 

	public boolean isaBouger()
	{
		return aBouger;
	}

	public void setaBouger()
	{
		this.aBouger = true;
	}

	public Triplets setMouvementPossible(Pieces[][] plateau, Point positionRoiEnemy)
	{
		this.getMouvementPossible().clear();
		int situationRoiEnememy = 0;
		Pieces piecesPin = null;
		Pieces pinPiecePossible = null;
		if (this.getEmplacement().y == positionRoiEnemy.y)
		{
			if (this.getEmplacement().x > positionRoiEnemy.x)
			{
				situationRoiEnememy = 1;
			}
			else
			{
				situationRoiEnememy = 2;
			}
		}
		else
			if (this.getEmplacement().x == positionRoiEnemy.x)
			{
				if (this.getEmplacement().y > positionRoiEnemy.y)
				{
					situationRoiEnememy = 3;
				}
				else
				{
					situationRoiEnememy = 4;
				}
			}

		boolean finMouvementPossible = false;
		// ajouter sur la ligne de la tour en bas
		for (int j = this.getEmplacement().y - 1; j >= 0; j--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(this.getEmplacement().x, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				if (this.isWhite() != plateau[this.getEmplacement().x][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(this.getEmplacement().x, j));
						pinPiecePossible = plateau[this.getEmplacement().x][j];
					}

					if (situationRoiEnememy != 3 || finMouvementPossible
							|| (this.getEmplacement().x == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (this.getEmplacement().x == positionRoiEnemy.x && j == positionRoiEnemy.y)
						{
							piecesPin = pinPiecePossible;
						}
						j = -30;
					}
					finMouvementPossible = true;
				}
				else
				{
					j = -30; // si la pièce est blanche stop
				}
			}
		}
		finMouvementPossible = false;
		pinPiecePossible = null;

		// ajouter sur la ligne de la tour en haut
		for (int j = this.getEmplacement().y + 1; j <= 7; j++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(this.getEmplacement().x, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(this.getEmplacement().x, j));
			}
			else
			{
				if (this.isWhite() != plateau[this.getEmplacement().x][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(this.getEmplacement().x, j));
						pinPiecePossible = plateau[this.getEmplacement().x][j];
					}

					if (situationRoiEnememy != 4 || finMouvementPossible
							|| (this.getEmplacement().x == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (this.getEmplacement().x == positionRoiEnemy.x && j == positionRoiEnemy.y)
						{
							piecesPin = pinPiecePossible;
						}
						j = 30;
					}
					finMouvementPossible = true;
				}
				else
				{
					j = 30; // si la pièce est blanche stop
				}
			}
		}
		finMouvementPossible = false;
		pinPiecePossible = null;

		// ajouter sur la ligne de la tour à droite
		for (int i = this.getEmplacement().x + 1; i <= 7; i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, this.getEmplacement().y)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				if (this.isWhite() != plateau[i][this.getEmplacement().y].isWhite())
				{
					if (!finMouvementPossible)
					{
						this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
						pinPiecePossible = plateau[i][this.getEmplacement().y];
					}

					if (situationRoiEnememy != 2 || finMouvementPossible
							|| (i == positionRoiEnemy.x && this.getEmplacement().y == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && this.getEmplacement().y == positionRoiEnemy.y)
						{
							piecesPin = pinPiecePossible;
						}
						i = 30;
					}
					finMouvementPossible = true;
				}
				else
				{
					i = 30; // si la pièce est blanche stop
				}
			}
		}
		finMouvementPossible = false;
		pinPiecePossible = null;

		// ajouter sur la ligne de la tour à droite
		for (int i = this.getEmplacement().x - 1; i >= 0; i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, this.getEmplacement().y)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
			}
			else
			{
				if (this.isWhite() != plateau[i][this.getEmplacement().y].isWhite())
				{
					if (!finMouvementPossible)
					{
						this.getMouvementPossible().add(new Point(i, this.getEmplacement().y));
						pinPiecePossible = plateau[i][this.getEmplacement().y];
					}

					if (situationRoiEnememy != 1 || finMouvementPossible
							|| (i == positionRoiEnemy.x && this.getEmplacement().y == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && this.getEmplacement().y == positionRoiEnemy.y)
						{
							piecesPin = pinPiecePossible;
						}
						i = -30;
					}
					finMouvementPossible = true;
				}
				else
				{
					i = -30; // si la pièce est blanche stop
				}
			}
		}
		finMouvementPossible = false;
		pinPiecePossible = null;


		Triplets temp=null;
		if(piecesPin!=null)
		{
			temp =new Triplets(situationRoiEnememy,this,piecesPin);
		}
		
		return temp;
	}

	@Override
	public int getValeur()
	{
		return 50;
	}

}
