package controleur;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	private String placementDepart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
	private Pieces pieceSelect;
	private Pane paneSelect;
	private ArrayList<Circle> listeCercle = new ArrayList<Circle>();
	private boolean tourJoueur = true;
	private final ArrayList<Point> LISTPOINTROCK = new ArrayList<Point>()
	{
		{
			add(new Point(6, 0));
			add(new Point(2, 0));
			add(new Point(2, 7));
			add(new Point(6, 7));
		}
	};

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
	private Label labelTourCouleur;

	@FXML
	private AnchorPane anchor;

	@FXML
	private RadioMenuItem Claire;
	@FXML
	private ListView<String> listDeMouvement;

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
	private ObservableList<String> list;

	@FXML

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		resetTotal();

	}

	public void setLabelTourCouleur(Label labelTourCouleur)
	{
		if (this.tourJoueur)
		{
			this.labelTourCouleur.setText("Blanc");
		}
		else
		{
			this.labelTourCouleur.setText("Noir");
		}
	}

	private void resetTotal()
	{

		list = FXCollections.observableArrayList();
		listDeMouvement.setItems(list);
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
		resetCouleur();
		tourJoueur = true;
		setLabelTourCouleur(labelTourCouleur);
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
		Pane tableauPane[] = AllPane();

		Pane paneClick = (Pane) event.getSource();

		if (pieceSelect == null)
		{
			paneClick.setStyle(
					"-fx-background-color:gold; -fx-border-color: black");
			pieceSelect = plateau
					.trouverPieces(rechercheCoordonnee(paneClick.getId()));
			paneSelect = paneClick;

			if (pieceSelect != null && pieceSelect.isWhite() == tourJoueur)
			{
				paneSelect.setStyle(
						"-fx-background-color:deeppink; -fx-border-color: black");
				ArrayList<Point> tableau = pieceSelect.getMouvementJouable();

				for (int i = 0; i < tableau.size(); i++)
				{
					listeCercle.add(new Circle(37, 37, 10, Color.GREY));
					for (int j = 0; j < tableauPane.length; j++)
					{
						if (tableauPane[j].getId()
								.equals(recherchePane(tableau.get(i))))
						{
							tableauPane[j].getChildren()
									.add(listeCercle.get(i));
						}
					}
				}

			}
		}
		else
		{

			if (pieceSelect.getMouvementJouable()
					.contains(rechercheCoordonnee(paneClick.getId()))
					&& pieceSelect.isWhite() == tourJoueur)
			{
				pieceSelect
						.setEmplacement(rechercheCoordonnee(paneClick.getId()));

				if (pieceSelect.getClass().toString().contains("Roi")
						&& !((Roi) pieceSelect).isaBouger()
						&& LISTPOINTROCK.contains(
								rechercheCoordonnee(paneClick.getId())))
				{
					Point rockValide = plateau
							.refreshDeplacementRock(((Roi) pieceSelect));
					if (rockValide != null)
					{
						deplacerImage(paneClick, paneSelect, pieceSelect);
						ajouterTableView(pieceSelect, "Castle");
						deplacerTour(rockValide);
					}
				}
				else
					if (pieceSelect.getClass().toString().contains("Pion")
							&& pieceSelect
									.getEmplacement().x != rechercheCoordonnee(
											paneSelect.getId()).x
							&& plateau.trouverPieces(
									pieceSelect.getEmplacement()) == null)
					{

						deplacer(pieceSelect, paneClick);
						deplacerImage(paneClick, paneSelect, pieceSelect);
						if (pieceSelect.isWhite())
						{
							SupprimerImage(new Point(
									pieceSelect.getEmplacement().x,
									pieceSelect.getEmplacement().y - 1));
						}
						else
						{
							SupprimerImage(new Point(
									pieceSelect.getEmplacement().x,
									pieceSelect.getEmplacement().y + 1));
						}
						ajouterTableView(pieceSelect, paneClick.getId());
					}
					else
					{
						deplacer(pieceSelect, paneClick);
						deplacerImage(paneClick, paneSelect, pieceSelect);
						ajouterTableView(pieceSelect, paneClick.getId());

						if (pieceSelect.getClass().toString().contains("Roi"))
						{
							((Roi) pieceSelect).setaBouger();
						}
						else
							if (pieceSelect.getClass().toString()
									.contains("Tour"))
							{
								((Tour) pieceSelect).setaBouger();
							}
					}

				tourJoueur = !tourJoueur;
				paneSelect = null;
				pieceSelect = null;
			}
			else
			{
				resetCouleur();
				pieceSelect = (null);
			}
		}
		if (plateau.getEchecMath())
		{
			afficherFinDePartie("Les " + labelTourCouleur.getText()
					+ "s ont gagnés la partie !");
		}
		else
			if (plateau.getEchecMath())
			{
				afficherFinDePartie(
						"Partie nulle, meilleur chance la prochaine fois!");
			}

		setLabelTourCouleur(labelTourCouleur);
	}

	private void deplacer(Pieces pieces, Pane positionFinale)
	{
		// deplacement dans la prog
		Point lastEmplacement = rechercheCoordonnee(paneSelect.getId());
		plateau.deplacementProg(lastEmplacement, pieceSelect);
	}

	private void deplacerTour(Point emplacement)
	{
		if (emplacement.equals(new Point(3, 0)))
		{
			deplacerImage(d1, a1, new Tour("R", true, new Point(3, 0)));
		}
		else
			if (emplacement.equals(new Point(5, 0)))
			{
				deplacerImage(f1, h1, new Tour("R", true, new Point(3, 0)));
			}
			else
				if (emplacement.equals(new Point(3, 7)))
				{
					deplacerImage(d8, a8,
							new Tour("r", false, new Point(3, 0)));
				}
				else
					if (emplacement.equals(new Point(5, 7)))
					{
						deplacerImage(f8, h8,
								new Tour("r", false, new Point(3, 0)));
					}

	}

	private void deplacerImage(Pane pane1, Pane pane2, Pieces pieceADeplacer)
	{
		resetCouleur();
		ImageView emplacementFin = (ImageView) pane1.getChildren().get(0);
		emplacementFin.setImage(association.get(pieceADeplacer.getNom()));
		ImageView emplacementDepart = (ImageView) pane2.getChildren().get(0);
		emplacementDepart.setImage(null);
		paneSelect
				.setStyle("-fx-background-color:pink; -fx-border-color: black");
	}

	private void SupprimerImage(Point emplacementASupprimer)
	{
		Pane tableauPane[] = AllPane();
		for (int j = 0; j < tableauPane.length; j++)
		{
			if (tableauPane[j].getId()
					.equals(recherchePane(emplacementASupprimer)))
			{
				ImageView emplacementDepart = (ImageView) tableauPane[j]
						.getChildren().get(0);
				emplacementDepart.setImage(null);
			}
		}
	}

	private String recherchePane(Point point)
	{
		String coordonnee = new String();
		int premier = point.x;
		switch (premier)
		{
			case 0:
				coordonnee = "a";
				break;
			case 1:
				coordonnee = "b";
				break;
			case 2:
				coordonnee = "c";
				break;
			case 3:
				coordonnee = "d";
				break;
			case 4:
				coordonnee = "e";
				break;
			case 5:
				coordonnee = "f";
				break;
			case 6:
				coordonnee = "g";
				break;
			case 7:
				coordonnee = "h";
				break;
		}
		coordonnee = coordonnee + (point.getY() + 1);
		return coordonnee.replaceAll("()\\.0+$|(\\..+?)0+$", "$2");

	}

	private void resetCouleur()
	{
		Pane tableauPane[] = AllPane();

		for (int j = 0; j < listeCercle.size(); j++)
		{
			listeCercle.get(j).setRadius(0);
		}
		listeCercle.clear();

		for (int i = 0; i < tableauPane.length; i++)
		{

			if (i % 2 == 0)
			{
				tableauPane[i].setStyle(
						"-fx-background-color:brown; -fx-border-color: black");
			}
			else
			{
				tableauPane[i].setStyle(
						"-fx-background-color:cornsilk; -fx-border-color: black");
			}
		}
	}

	private Pane[] AllPane()
	{
		Pane tableauAllPane[] =
		{ a1, a2, a3, a4, a5, a6, a7, a8, b8, b7, b6, b5, b4, b3, b2, b1, c1,
				c2, c3, c4, c5, c6, c7, c8, d8, d7, d6, d5, d4, d3, d2, d1, e1,
				e2, e3, e4, e5, e6, e7, e8, f8, f7, f6, f5, f4, f3, f2, f1, g1,
				g2, g3, g4, g5, g6, g7, g8, h8, h7, h6, h5, h4, h3, h2, h1 };
		return tableauAllPane;

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

	private void ajouterTableView(Pieces p, String positionFinale)
	{
		if (positionFinale.equals("Castle"))
		{
			Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()),
					new SimpleStringProperty(positionFinale), p.isWhite());

			list.add(m.toStringCastle());
		}
		else
		{
			Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()),
					new SimpleStringProperty(positionFinale), p.isWhite());

			list.add(m.toString());
		}

	}

	private void afficherFinDePartie(String finParti)
	{
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("Fin de Partie !");
		alert.setHeaderText(finParti);
		alert.setContentText(null);
		ButtonType analyse = new ButtonType("Analyse");
		alert.getButtonTypes().setAll(analyse, ButtonType.OK);
		Optional<ButtonType> choice = alert.showAndWait();

		if (choice.get() == analyse)
		{
			// ouvrir analyse
		}
	}
}
