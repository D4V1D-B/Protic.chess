package modele;

import java.awt.Point;

public class Reine extends Pieces
{

	public Reine(String nom, boolean couleur, Point position)
	{
		super(nom, couleur, position);
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
			else
			{
				Point verificationRoi = new Point(positionRoiEnemy.x - this.getEmplacement().x, positionRoiEnemy.y - this.getEmplacement().y);
				if (Math.abs(verificationRoi.x) == Math.abs(verificationRoi.y))
				{
					if (verificationRoi.x < 0)
					{
						if (verificationRoi.y < 0)
							situationRoiEnememy = 5; // --
						else
							situationRoiEnememy = 6; // -+
					}
					else
					{
						if (verificationRoi.y < 0)
							situationRoiEnememy = 7; // +-
						else
							situationRoiEnememy = 8; // ++
					}
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

		// ligne vers le haut à gauhce donc -+ cas 6
		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y + 1; i >= 0 && j <= 7; j++, i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(i, j));
						pinPiecePossible = plateau[i][j];
					}

					if (situationRoiEnememy != 6 || finMouvementPossible || (i == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && j == positionRoiEnemy.y)
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

		// ligne vers le haut à droite ++ cas 8
		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y + 1; i <= 7 && j <= 7; j++, i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(i, j));
						pinPiecePossible = plateau[i][j];
					}

					if (situationRoiEnememy != 8 || finMouvementPossible || (i == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && j == positionRoiEnemy.y)
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

		// ligne vers le bas à droite donc +- cas7
		for (int i = this.getEmplacement().x + 1, j = this.getEmplacement().y - 1; i <= 7 && j >= 0; j--, i++)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(i, j));
						pinPiecePossible = plateau[i][j];
					}

					if (situationRoiEnememy != 7 || finMouvementPossible || (i == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && j == positionRoiEnemy.y)
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

		// ligne vers le bas à gauche -- cas 5
		for (int i = this.getEmplacement().x - 1, j = this.getEmplacement().y - 1; i >= 0 && j >= 0; j--, i--)
		{
			if (OperationSurUneMatrice.getVoidSpace(plateau).contains(new Point(i, j)))
			{
				if (!finMouvementPossible)
					this.getMouvementPossible().add(new Point(i, j));
			}
			else
			{
				if (this.isWhite() != plateau[i][j].isWhite())
				{
					if (!finMouvementPossible)
					{
						getMouvementPossible().add(new Point(i, j));
						pinPiecePossible = plateau[i][j];
					}

					if (situationRoiEnememy != 5 || finMouvementPossible || (i == positionRoiEnemy.x && j == positionRoiEnemy.y))
					{
						if (i == positionRoiEnemy.x && j == positionRoiEnemy.y)
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
		return 90;
	}

}
