package controleur;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modele.Cavalier;
import modele.Fou;
import modele.Mouvement;
import modele.Pieces;
import modele.Pion;
import modele.Reine;
import modele.Roi;
import modele.Tour;

public class Controleur implements Initializable
{
	private HashMap<String, Image> association = new HashMap<String, Image>();
	private Plateau plateau;
	private Plateau plateauTest;
	private String placementDepart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
	private Pieces pieceSelect;
	private Pane paneSelect;
	private String couleur;
	private ArrayList<Mouvement> listMouvements;

	@FXML
	private Pane a8;

	@FXML
	private Pane b8;

	@FXML
	private Pane c8;

	@FXML
	private Pane d8;

	@FXML
	private Pane e8;

	@FXML
	private Pane f8;

	@FXML
	private Pane g8;

	@FXML
	private Pane h8;

	@FXML
	private Pane a7;

	@FXML
	private Pane b7;

	@FXML
	private Pane c7;

	@FXML
	private Pane d7;

	@FXML
	private Pane e7;

	@FXML
	private Pane f7;

	@FXML
	private Pane g7;

	@FXML
	private Pane h7;

	@FXML
	private Pane a6;

	@FXML
	private Pane b6;

	@FXML
	private Pane c6;

	@FXML
	private Pane d6;

	@FXML
	private Pane e6;

	@FXML
	private Pane f6;

	@FXML
	private Pane g6;

	@FXML
	private Pane h6;

	@FXML
	private Pane a5;

	@FXML
	private Pane b5;

	@FXML
	private Pane c5;

	@FXML
	private Pane d5;

	@FXML
	private Pane e5;

	@FXML
	private Pane f5;

	@FXML
	private Pane g5;

	@FXML
	private Pane h5;

	@FXML
	private Pane a4;

	@FXML
	private Pane b4;

	@FXML
	private Pane c4;

	@FXML
	private Pane d4;

	@FXML
	private Pane e4;

	@FXML
	private Pane f4;

	@FXML
	private Pane g4;

	@FXML
	private Pane h4;

	@FXML
	private Pane a3;

	@FXML
	private Pane b3;

	@FXML
	private Pane c3;

	@FXML
	private Pane d3;

	@FXML
	private Pane e3;

	@FXML
	private Pane f3;

	@FXML
	private Pane g3;

	@FXML
	private Pane h3;

	@FXML
	private Pane a2;

	@FXML
	private Pane b2;

	@FXML
	private Pane c2;

	@FXML
	private Pane d2;

	@FXML
	private Pane e2;

	@FXML
	private Pane f2;

	@FXML
	private Pane g2;

	@FXML
	private Pane h2;

	@FXML
	private Pane a1;

	@FXML
	private Pane b1;

	@FXML
	private Pane c1;

	@FXML
	private Pane d1;

	@FXML
	private Pane e1;

	@FXML
	private Pane f1;

	@FXML
	private Pane g1;

	@FXML
	private Pane h1;

	@FXML
	private ImageView a8Image;

	@FXML
	private AnchorPane anchor;

	@FXML
	private RadioMenuItem Claire;
	@FXML
	private TableView<Mouvement> tableDeMouvementFx;

	@FXML
	private TableColumn<Mouvement, String> tableauBlanc;

	@FXML
	private TableColumn<Mouvement, String> TableauNoir;

	@FXML
	private Button recommencerPartie;

	@FXML
	void recommencerPartie(MouseEvent event)
	{
		Pane[] allPanes = new Pane[64];

		for (int nb = 0; nb < 64; nb++)
		{
			allPanes[nb] = (Pane) anchor.getChildren().get(nb);
		}

		for (int i = 0; i < 64; i++)
		{
			ImageView n = (ImageView) allPanes[i].getChildren().get(0);
			n.setImage(null);
		}

		placerPiecesString(placementDepart);
		resetTotal();

	}


	@FXML

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		listMouvements = new ArrayList<Mouvement>();
		//tableDeMouvementFx.setItems((ObservableList<Mouvement>) listMouvements);
		resetTotal();

	}

	private void resetTotal()
	{
		Tour R1 = new Tour("R", true, new Point(0, 0));
		Cavalier N1 = new Cavalier("N", true, new Point(1, 0));
		Fou B1 = new Fou("B", true, new Point(2, 0));
		Reine Q = new Reine("Q", true, new Point(3, 0));
		Roi K = new Roi("K", true, new Point(4, 0));
		Fou B2 = new Fou("B", true, new Point(5, 0));
		Cavalier N2 = new Cavalier("N", true, new Point(6, 0));
		Tour R2 = new Tour("R", true, new Point(7, 0));

		Pion P1 = new Pion("P", true, new Point(0, 1));
		Pion P2 = new Pion("P", true, new Point(1, 1));
		Pion P3 = new Pion("P", true, new Point(2, 1));
		Pion P4 = new Pion("P", true, new Point(3, 1));
		Pion P5 = new Pion("P", true, new Point(4, 1));
		Pion P6 = new Pion("P", true, new Point(5, 1));
		Pion P7 = new Pion("P", true, new Point(6, 1));
		Pion P8 = new Pion("P", true, new Point(7, 1));

		Tour r1 = new Tour("r", false, new Point(0, 7));
		Cavalier n1 = new Cavalier("n", false, new Point(1, 7));
		Fou b1 = new Fou("b", false, new Point(2, 7));
		Reine q = new Reine("q", false, new Point(3, 7));
		Roi k = new Roi("k", false, new Point(4, 7));
		Fou b2 = new Fou("b", false, new Point(5, 7));
		Cavalier n2 = new Cavalier("n", false, new Point(6, 7));
		Tour r2 = new Tour("r", false, new Point(7, 7));

		Pion p1 = new Pion("p", false, new Point(0, 6));
		Pion p2 = new Pion("p", false, new Point(1, 6));
		Pion p3 = new Pion("p", false, new Point(2, 6));
		Pion p4 = new Pion("p", false, new Point(3, 6));
		Pion p5 = new Pion("p", false, new Point(4, 6));
		Pion p6 = new Pion("p", false, new Point(5, 6));
		Pion p7 = new Pion("p", false, new Point(6, 6));
		Pion p8 = new Pion("p", false, new Point(7, 6));

		ArrayList<Pieces> blanc = new ArrayList<Pieces>();
		blanc.add(R1);
		blanc.add(N1);
		blanc.add(B1);
		blanc.add(Q);
		blanc.add(K);
		blanc.add(B2);
		blanc.add(N2);
		blanc.add(R2);
		blanc.add(P1);
		blanc.add(P2);
		blanc.add(P3);
		blanc.add(P4);
		blanc.add(P5);
		blanc.add(P6);
		blanc.add(P7);
		blanc.add(P8);

		ArrayList<Pieces> noir = new ArrayList<Pieces>();
		noir.add(r1);
		noir.add(n1);
		noir.add(b1);
		noir.add(q);
		noir.add(k);
		noir.add(b2);
		noir.add(n2);
		noir.add(r2);
		noir.add(p1);
		noir.add(p2);
		noir.add(p3);
		noir.add(p4);
		noir.add(p5);
		noir.add(p6);
		noir.add(p7);
		noir.add(p8);

		plateau = new Plateau(blanc, noir);

		placementDepart.indexOf('R');
		placementDepart.replace('R', '1');

		placerPiecesString(placementDepart);
	}

	public void placerPiecesString(String placement)
	{

		association.put("r", new Image("images/TourNoir.png"));
		association.put("n", new Image("images/CavalierNoir.png"));
		association.put("b", new Image("images/FouNoir.png"));
		association.put("q", new Image("images/ReineNoir.png"));
		association.put("k", new Image("images/RoiNoir.png"));
		association.put("p", new Image("images/PionNoir.png"));
		association.put("R", new Image("images/TourBlanc.png"));
		association.put("N", new Image("images/CavalierBlanc.png"));
		association.put("B", new Image("images/FouBlanc.png"));
		association.put("Q", new Image("images/ReineBlanc.png"));
		association.put("K", new Image("images/RoiBlanc.png"));
		association.put("P", new Image("images/PionBlanc.png"));

		Pane[] allPanes = new Pane[64];

		for (int nb = 0; nb < 64; nb++)
		{
			allPanes[nb] = (Pane) anchor.getChildren().get(nb);
		}

		int emplacementSurLeBoard = 0; // entre 0 et 63

		for (char a : placement.toCharArray())
		{

			if (a == '/')
			{
				int i = placement.indexOf(a);
				CharSequence row = placement.subSequence(0, i); // prendre le
																// string fen
																// row par row

				for (int y = 0; y < row.length(); y++)
				{
					char z = row.charAt(y); // Cycle les char de la row

					if (Character.isDigit(z))
					{
						int caseVide = z - 48;
						emplacementSurLeBoard = emplacementSurLeBoard
								+ caseVide;

					}
					else
					{
						ImageView paneActuel = (ImageView) allPanes[emplacementSurLeBoard]
								.getChildren().get(0);
						paneActuel.setImage(association.get(String.valueOf(z)));
						emplacementSurLeBoard++;
					}
				}

				placement = placement.substring(i + 1);

			}

		}

	}

	@FXML
	void mouseClick(MouseEvent event)
	{

		Pane p = (Pane) event.getSource();

		if (pieceSelect == null)
		{
			p.setStyle("-fx-background-color:gold; -fx-border-color: black");
			pieceSelect = plateau.trouverPieces(rechercheCoordonnee(p.getId()));
			paneSelect = p;
			if (pieceSelect != null)
				paneSelect.setStyle(
						"-fx-background-color:deeppink; -fx-border-color: black");
		}
		else
		{

			if (pieceSelect.getMouvementPossible()
					.contains(rechercheCoordonnee(p.getId())))
			{

				boolean mouvementValide = deplacer(pieceSelect, p);

				if (mouvementValide)
				{
					resetCouleur();
					ImageView n = (ImageView) p.getChildren().get(0);
					n.setImage(association.get(pieceSelect.getNom()));
					ImageView m = (ImageView) paneSelect.getChildren().get(0);
					m.setImage(null);
				}
				paneSelect = null;
				pieceSelect = null;
			}
			else
			{
				resetCouleur();
				pieceSelect = (null);
			}
		}

	}

	private void resetCouleur()
	{
		Pane tableau[] =
		{ a1, a2, a3, a4, a5, a6, a7, a8, b8, b7, b6, b5, b4, b3, b2, b1, c1,
				c2, c3, c4, c5, c6, c7, c8, d8, d7, d6, d5, d4, d3, d2, d1, e1,
				e2, e3, e5, e5, e6, e7, e8, f8, f7, f6, f5, f4, f3, f2, f1, g1,
				g2, g3, g4, g5, g6, g7, g8, h8, h7, h6, h5, h4, h3, h2, h1 };

		for (int i = 0; i < tableau.length; i++)
		{
			if (i % 2 == 0)
			{
				tableau[i].setStyle(
						"-fx-background-color:brown; -fx-border-color: black");
			}
			else
			{
				tableau[i].setStyle(
						"-fx-background-color:cornsilk; -fx-border-color: black");
			}
		}
	}

	private boolean deplacer(Pieces p, Pane positionFinale)

	{
		p.setEmplacement(rechercheCoordonnee(positionFinale.getId()));
		// deplacement dans la prog
		Point lastEmplacement = rechercheCoordonnee(paneSelect.getId());
		boolean mouvementValide = plateau.refreshDeplacement(lastEmplacement,
				pieceSelect);

		ajouterTableView(p, positionFinale.getId());

		return mouvementValide;
	}

	private Point rechercheCoordonnee(String position)
	{
		Point coordonnee = new Point();
		char premiereLettre = position.toUpperCase().charAt(0);
		switch (premiereLettre)
		{
			case 'A':
				premiereLettre = '0';
				break;
			case 'B':
				premiereLettre = '1';
				break;
			case 'C':
				premiereLettre = '2';
				break;
			case 'D':
				premiereLettre = '3';
				break;
			case 'E':
				premiereLettre = '4';
				break;
			case 'F':
				premiereLettre = '5';
				break;
			case 'G':
				premiereLettre = '6';
				break;
			case 'H':
				premiereLettre = '7';
				break;
		}
		coordonnee.setLocation(Character.getNumericValue(premiereLettre),
				Character.getNumericValue(position.charAt(1)) - 1);
		return coordonnee;

	}

	// private Point chercherCoordonnee(char pieceCherche) // x= range y=colonne
	// {
	//
	// Point p = new Point();
	//
	// int x = placementDepart.indexOf(pieceCherche);
	// String subString = placementDepart.substring(0, x + 1);
	//
	// int nbSlash = 0;
	// int positionDuSlash = 0;
	// int positionActuelle = 0;
	// for (char c : subString.toCharArray())
	// {
	// if (c == '/')
	// {
	// nbSlash++;
	// positionDuSlash = positionActuelle;
	//
	// }
	// positionActuelle++;
	// }
	//
	// // TODO ne focntionne pas coordonne x
	// String subSubString = subString.substring(positionDuSlash + 1,
	// subString.length());
	//
	// int positionCherche = 0;
	// int positionActuelle2 = 0;
	// for (char h : subSubString.toCharArray())
	// {
	// if (h == pieceCherche)
	// {
	// positionCherche = positionActuelle2;
	// }
	// positionActuelle2++;
	// }
	//
	// placementDepart.replaceFirst(Character.toString(pieceCherche), "1");
	// System.out.println(placementDepart);
	// p.setLocation(positionCherche, nbSlash);
	// return p;
	// }

	private void ajouterTableView(Pieces p, String positionFinale)
	{
<<<<<<< HEAD
		// Mouvement m = new Mouvement(p.getNom(), positionFinale, p.isWhite());
=======
		Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()),
				new SimpleStringProperty(positionFinale), p.isWhite());

		System.out.println(m.toString());
		listMouvements.add(m);

		if (m.isWhite())
		{
			// ajouter a la colonne blance

		}
		else
		{
			// ajouter a la colonne noir

		}
>>>>>>> e2bdd4139490649840c4581548d69075704379dc

	}

}
