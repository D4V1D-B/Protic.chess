package controleur;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
	private String placementActuel = null;
	private ArrayList<Mouvement> arrayMouvement = new ArrayList<Mouvement>();
	private boolean tuPeuxBoujerLesPieces = true;
	private Plateau plateau;
	private String placementDepart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
	private Pieces pieceSelect;
	private Pane paneSelect;
	private ArrayList<Circle> listeCercle = new ArrayList<Circle>();
	private boolean tourJoueur = true;
	private String file = "sauvegard.txt";
	private Bot bot = new Bot();
	int indiceDuMouvement = 0;
	private final ArrayList<Point> LISTPOINTROCK = new ArrayList<Point>()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new Point(6, 0));
			add(new Point(2, 0));
			add(new Point(2, 7));
			add(new Point(6, 7));
		}
	};

	private ListView<String> listViewAnciennesParties;
	private ObservableList<String> ListAnciennesParties;

	@FXML
	private CheckMenuItem CheckAI;

	@FXML
	private CheckMenuItem CheckMenuItemSon;

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
	private Button boutonChargerMouvements;

	@FXML
	private Button boutonRevenirAuJeu;

	@FXML
	private ObservableList<String> list;

	@FXML
	private Button boutonMouvementArriere;

	@FXML
	private Button boutonMouvementAvant;
	private Stage fenetreAide;

	@FXML
	void boutonTest(ActionEvent event)
	{
		TextInputDialog inDialog = new TextInputDialog("p/P/p/P/p/P/p/p/");
		inDialog.setTitle("Information demandée");
		inDialog.setHeaderText("Entrer une Fen.");
		inDialog.setContentText("Fen :");

		Optional<String> textIn = inDialog.showAndWait();
		
		placerPiecesString(textIn.get());
	}

	@FXML
	void revenirAuJeu(MouseEvent event)
	{
		placerPiecesString(placementActuel);
		boutonDisable(true);
	}

	@FXML
	void chargerMouvement(MouseEvent event)
	{
		indiceDuMouvement = listDeMouvement.getSelectionModel().getSelectedIndex();
		if (indiceDuMouvement != -1)
		{

			placementActuel = creerFen();

			Mouvement mouvementSelect = arrayMouvement.get(indiceDuMouvement);
			placerPiecesString(mouvementSelect.getFen().get());
			boutonDisable(false);
		}
	}

	@FXML
	void mouvementArriere(MouseEvent event)
	{

		if (indiceDuMouvement >= 1)
		{
			indiceDuMouvement--;
			avantArriereMouvement();
		}
	}

	@FXML
	void mouvementAvant(MouseEvent event)
	{

		if (indiceDuMouvement < arrayMouvement.size() - 1)
		{
			indiceDuMouvement++;
			avantArriereMouvement();
		}
	}

	@FXML
	void chargerPartie(ActionEvent event)
	{

		chargerUnePartie();
	}

	@FXML
	void saveGame(ActionEvent event)
	{

		TextInputDialog inDialog = new TextInputDialog("");
		inDialog.setTitle("Information demandée");
		inDialog.setHeaderText("Entrer le nom de la partie.");
		inDialog.setContentText("Le nom de la partie est :");

		Optional<String> textIn = inDialog.showAndWait();
		if (textIn.isPresent())
		{
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

				writer.append(textIn.get() + "*" + creerFen() + "\n");

				writer.close();
			}
			catch (IOException e)
			{
				System.out.println("Erreur dans la sauvegarde!");
				e.printStackTrace();
			}
		}
	}

	@FXML
	void effacerLesSauvegardes(ActionEvent event)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));

			writer.append("");

			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Erreur dans lors de effacement des sauvegardes.");
			e.printStackTrace();
		}
	}

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
	void aideEchec(ActionEvent event)
	{
		if (fenetreAide.isShowing())
		{
			fenetreAide.toFront();
		}
		else
		{
			fenetreAide.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		resetTotal();
		boutonDisable(true);
		CheckMenuSon();
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
			if (CheckAI.isSelected())
			{
				JouerAI();
			}
		}
	}

	private void resetTotal()
	{
		if (!arrayMouvement.isEmpty())
		{
			arrayMouvement.clear();
		}
		tuPeuxBoujerLesPieces = true;
		boutonRevenirAuJeu.setDisable(true);
		boutonMouvementArriere.setDisable(true);
		boutonMouvementAvant.setDisable(true);
		list = FXCollections.observableArrayList();
		listDeMouvement.setItems(list);

		placerPiecesString(placementDepart);
		resetCouleur();
		tourJoueur = true;
		setLabelTourCouleur(labelTourCouleur);
		jouerSon("/son/Game_start.mp3");
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

		ArrayList<Pieces> blanc = new ArrayList<Pieces>();
		ArrayList<Pieces> noir = new ArrayList<Pieces>();

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
					char charDeLaPiece = row.charAt(y); // Cycle les char de la
														// row

					if (Character.isDigit(charDeLaPiece))
					{
						int caseVide = charDeLaPiece - 48;
						emplacementSurLeBoard = emplacementSurLeBoard + caseVide;

					}
					else
					{
						ImageView paneActuel = (ImageView) allPanes[emplacementSurLeBoard].getChildren().get(0);
						paneActuel.setImage(association.get(String.valueOf(charDeLaPiece)));

						int nbr = 0;
						int nbn = 0;
						int nbb = 0;
						int nbp = 0;
						int nbR = 0;
						int nbN = 0;
						int nbB = 0;
						int nbP = 0;
						Tour R1 = null;
						Cavalier N1 = null;
						Fou B1 = null;
						Reine Q = null;
						Roi K = null;
						Fou B2 = null;
						Cavalier N2 = null;
						Tour R2 = null;

						Pion P1 = null;
						Pion P2 = null;
						Pion P3 = null;
						Pion P4 = null;
						Pion P5 = null;
						Pion P6 = null;
						Pion P7 = null;
						Pion P8 = null;

						Tour r1 = null;
						Cavalier n1 = null;
						Fou b1 = null;
						Reine q = null;
						Roi k = null;
						Fou b2 = null;
						Cavalier n2 = null;
						Tour r2 = null;

						Pion p1 = null;
						Pion p2 = null;
						Pion p3 = null;
						Pion p4 = null;
						Pion p5 = null;
						Pion p6 = null;
						Pion p7 = null;
						Pion p8 = null;
						switch (charDeLaPiece)
						{
							case 'r':

								if (nbr == 0)
								{
									r1 = new Tour("r", false,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(r1);
									nbr++;
								}
								else
								{
									r2 = new Tour("r", false,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(r2);
								}

								break;
							case 'n':
								if (nbn == 0)
								{
									n1 = new Cavalier("n", false,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(n1);
									nbn++;
								}
								else
								{
									n2 = new Cavalier("n", false,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(n2);
								}

								break;
							case 'b':
								if (nbb == 0)
								{
									b1 = new Fou("b", false, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(b1);
									nbn++;
								}
								else
								{
									b2 = new Fou("b", false, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									noir.add(b2);
								}
								break;
							case 'k':
								k = new Roi("k", false, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
								noir.add(k);
								break;
							case 'q':
								q = new Reine("q", false, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
								noir.add(q);
								break;
							case 'p':
								switch (nbp)
								{
									case 0:
										p1 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p1);
										nbp++;
										break;

									case 1:
										p2 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p2);
										nbp++;
										break;
									case 2:
										p3 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p3);
										nbp++;
										break;
									case 3:
										p4 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p4);
										nbp++;
										break;
									case 4:
										p5 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p5);
										nbp++;
										break;
									case 5:
										p6 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p6);
										nbp++;
										break;
									case 6:
										p7 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p7);
										nbp++;
										break;
									case 7:
										p8 = new Pion("p", false,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										noir.add(p8);
										nbp++;
										break;
								}

								break;
							case 'R':
								if (nbR == 0)
								{
									R1 = new Tour("R", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(R1);
									nbR++;
								}
								else
								{
									R2 = new Tour("R", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(R2);
								}
								break;
							case 'N':
								if (nbN == 0)
								{
									N1 = new Cavalier("N", true,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(N1);
									nbN++;
								}
								else
								{
									N2 = new Cavalier("N", true,
											pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(N2);

								}

								break;
							case 'B':
								if (nbB == 0)
								{
									B1 = new Fou("B", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(B1);
									nbB++;
								}
								else
								{
									B2 = new Fou("B", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
									blanc.add(B2);
								}
								break;
							case 'K':
								K = new Roi("K", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
								blanc.add(K);
								break;
							case 'Q':
								Q = new Reine("Q", true, pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
								blanc.add(Q);
								break;
							case 'P':
								switch (nbP)
								{
									case 0:
										P1 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P1);
										nbP++;

										break;

									case 1:
										P2 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P2);
										nbP++;

										break;
									case 2:
										P3 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P3);
										nbP++;

										break;
									case 3:
										P4 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P4);
										nbP++;

										break;
									case 4:
										P5 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P5);
										nbP++;

										break;
									case 5:
										P6 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P6);
										nbP++;

										break;
									case 6:
										P7 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P7);
										nbP++;

										break;
									case 7:
										P8 = new Pion("P", true,
												pointSelonEmplacementDansLeTableau(emplacementSurLeBoard));
										blanc.add(P8);
										nbP++;

										break;

								}
								break;

						}

						emplacementSurLeBoard++;
					}

				}

				placement = placement.substring(i + 1);

			}

		}

		plateau = new Plateau(blanc, noir);
	}

	private void JouerAI()
	{
		Pane[] allPanes = new Pane[64];

		for (int nb = 0; nb < 64; nb++)
		{
			allPanes[nb] = (Pane) anchor.getChildren().get(nb);
		}
		if (tourJoueur == false)
		{
			String position = bot.jouerBotTest(this.plateau);
			Point pointFinale = new Point((position.charAt(4) - 48), (position.charAt(5) - 48));
			Point pointInitiale = new Point((position.charAt(0) - 48), (position.charAt(2) - 48));
			Pane paneFinale = null;
			for (int i = 0; i < allPanes.length; i++)
			{
				if (allPanes[i].getId().equals(recherchePane(pointFinale)))
				{
					paneFinale = allPanes[i];
				}
			}
			paneSelect = paneFinale;
			pieceSelect = plateau.trouverPieces(pointInitiale);
			deplacer(pieceSelect, paneSelect);
			Pane paneInitiale = null;
			for (int i = 0; i < allPanes.length; i++)
			{
				if (allPanes[i].getId().equals(recherchePane(pointInitiale)))
				{
					paneInitiale = allPanes[i];
				}
			}
			deplacerImage(paneSelect, paneInitiale, pieceSelect);
			tourJoueur = !tourJoueur;
		}
	}

	@FXML
	void mouseClick(MouseEvent event)
	{
		if (tuPeuxBoujerLesPieces)
		{
			Pane tableauPane[] = allPane();

			Pane paneClick = (Pane) event.getSource();

			if (pieceSelect == null)
			{
				paneClick.setStyle("-fx-background-color:gold; -fx-border-color: black");
				pieceSelect = plateau.trouverPieces(rechercheCoordonnee(paneClick.getId()));
				paneSelect = paneClick;

				if (pieceSelect != null && pieceSelect.isWhite() == tourJoueur)
				{
					paneSelect.setStyle("-fx-background-color:deeppink; -fx-border-color: black");
					ArrayList<Point> tableau = pieceSelect.getMouvementJouable();

					for (int i = 0; i < tableau.size(); i++)
					{
						listeCercle.add(new Circle(37, 37, 10, Color.GREY));
						for (int j = 0; j < tableauPane.length; j++)
						{
							if (tableauPane[j].getId().equals(recherchePane(tableau.get(i))))
							{
								tableauPane[j].getChildren().add(listeCercle.get(i));
							}
						}
					}

				}
			}
			else
			{

				if (pieceSelect.getMouvementJouable().contains(rechercheCoordonnee(paneClick.getId()))
						&& pieceSelect.isWhite() == tourJoueur)
				{
					jouerSon("/son/Move.mp3");
					deplacer(pieceSelect, paneClick);

					if (tourJoueur)
					{
						if (plateau.getEchecMathNoir())
						{
							jouerSon("/son/Checkmate.mp3");
							afficherFinDePartie("Les blancs ont gagné! Félicitation!");
						}
					}
					else
					{
						if (plateau.getEchecMathBlanc())
						{
							jouerSon("/son/Checkmate.mp3");
							afficherFinDePartie("Les noirs ont gagné! Félicitation!");
						}
					}
					
					if(!plateau.getEchecMathBlanc()&&!plateau.getEchecMathNoir()&&plateau.partieNulle())
					{
						afficherFinDePartie("Partie nulle, meilleur chance la prochaine fois!");
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

				setLabelTourCouleur(labelTourCouleur);
			}
		}
	}

	private void deplacer(Pieces pieceSelect, Pane paneClick)
	{
		pieceSelect.setEmplacement(rechercheCoordonnee(paneClick.getId()));

		if (pieceSelect.getClass().toString().contains("Roi") && !((Roi) pieceSelect).isaBouger()
				&& LISTPOINTROCK.contains(rechercheCoordonnee(paneClick.getId())))
		{
			Point rockValide = plateau.refreshDeplacementRock(((Roi) pieceSelect));
			if (rockValide != null)
			{
				deplacerImage(paneClick, paneSelect, pieceSelect);
				ajouterTableView(pieceSelect, "Castle");
				deplacerTour(rockValide);
			}
		}
		else
			if (pieceSelect.getClass().toString().contains("Pion")
					&& pieceSelect.getEmplacement().x != rechercheCoordonnee(paneSelect.getId()).x
					&& plateau.trouverPieces(pieceSelect.getEmplacement()) == null)
			{
				deplacerProg(pieceSelect, paneClick);
				deplacerImage(paneClick, paneSelect, pieceSelect);
				if (pieceSelect.isWhite())
				{
					SupprimerImage(new Point(pieceSelect.getEmplacement().x, pieceSelect.getEmplacement().y - 1));
				}
				else
				{
					SupprimerImage(new Point(pieceSelect.getEmplacement().x, pieceSelect.getEmplacement().y + 1));
				}
				ajouterTableView(pieceSelect, paneClick.getId());
			}
			else
			{
				deplacerProg(pieceSelect, paneClick);
				deplacerImage(paneClick, paneSelect, pieceSelect);
				ajouterTableView(pieceSelect, paneClick.getId());

				if (pieceSelect.getClass().toString().contains("Roi"))
				{
					((Roi) pieceSelect).setaBouger();
				}
				else
					if (pieceSelect.getClass().toString().contains("Tour"))
					{
						((Tour) pieceSelect).setaBouger();
					}
			}

		if ((pieceSelect.getNom().equals("P") && rechercheCoordonnee(paneClick.getId()).y == 7)
				|| (pieceSelect.getNom().equals("p") && rechercheCoordonnee(paneClick.getId()).y == 0))
		{
			afficherPionUgrade(pieceSelect.isWhite(), paneClick);
		}

	}

	private void deplacerProg(Pieces pieces, Pane positionFinale)
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
					deplacerImage(d8, a8, new Tour("r", false, new Point(3, 0)));
				}
				else
					if (emplacement.equals(new Point(5, 7)))
					{
						deplacerImage(f8, h8, new Tour("r", false, new Point(3, 0)));
					}

	}

	private void deplacerImage(Pane paneArriver, Pane paneDepart, Pieces pieceADeplacer)
	{
		resetCouleur();
		ImageView emplacementFin = (ImageView) paneArriver.getChildren().get(0);
		emplacementFin.setImage(association.get(pieceADeplacer.getNom()));
		ImageView emplacementDepart = (ImageView) paneDepart.getChildren().get(0);
		emplacementDepart.setImage(null);
		paneSelect.setStyle("-fx-background-color:pink; -fx-border-color: black");
	}

	private void SupprimerImage(Point emplacementASupprimer)
	{
		Pane tableauPane[] = allPane();
		for (int j = 0; j < tableauPane.length; j++)
		{
			if (tableauPane[j].getId().equals(recherchePane(emplacementASupprimer)))
			{
				ImageView emplacementDepart = (ImageView) tableauPane[j].getChildren().get(0);
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
		Pane tableauPane[] = allPane();

		for (int j = 0; j < listeCercle.size(); j++)
		{
			listeCercle.get(j).setRadius(0);
		}
		listeCercle.clear();

		for (int i = 0; i < tableauPane.length; i++)
		{
			if (i % 2 == 0)
			{
				tableauPane[i].setStyle("-fx-background-color:brown; -fx-border-color: black");
			}
			else
			{
				tableauPane[i].setStyle("-fx-background-color:cornsilk; -fx-border-color: black");
			}
		}
	}

	private Pane[] allPane()
	{
		Pane tableauAllPane[] =
		{ a1, a2, a3, a4, a5, a6, a7, a8, b8, b7, b6, b5, b4, b3, b2, b1, c1, c2, c3, c4, c5, c6, c7, c8, d8, d7, d6,
				d5, d4, d3, d2, d1, e1, e2, e3, e4, e5, e6, e7, e8, f8, f7, f6, f5, f4, f3, f2, f1, g1, g2, g3, g4, g5,
				g6, g7, g8, h8, h7, h6, h5, h4, h3, h2, h1 };
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
			Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()), new SimpleStringProperty(positionFinale),
					new SimpleStringProperty(creerFen()), p.isWhite());

			list.add(m.toStringCastle());
			arrayMouvement.add(m);
		}
		else
		{
			Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()), new SimpleStringProperty(positionFinale),
					new SimpleStringProperty(creerFen()), p.isWhite());

			list.add(m.toString());
			arrayMouvement.add(m);
		}

	}

	private void afficherPionUgrade(boolean equipe, Pane paneClick)
	{

		Stage upgrade = new Stage();
		upgrade.setTitle("Pion upgrade !");

		upgrade.setMaxHeight(135);
		upgrade.setMinHeight(135);

		upgrade.setMaxWidth(275);
		upgrade.setMinWidth(275);

		VBox root = new VBox();
		HBox images = new HBox();
		HBox boutons = new HBox();
		upgrade.setScene(new Scene(root));

		Button reine = new Button("Reine");
		Button fou = new Button("Fou");
		Button cavalier = new Button("Chevalier");
		Button tour = new Button("Tour");
		ImageView reineImage = new ImageView();
		ImageView fouImage = new ImageView();
		ImageView cavalierImage = new ImageView();
		ImageView tourImage = new ImageView();
		if (equipe)
		{
			reineImage.setImage(association.get("Q"));
			fouImage.setImage(association.get("B"));
			cavalierImage.setImage(association.get("N"));
			tourImage.setImage(association.get("R"));
		}
		else
		{
			reineImage.setImage(association.get("q"));
			fouImage.setImage(association.get("b"));
			cavalierImage.setImage(association.get("n"));
			tourImage.setImage(association.get("r"));
		}

		images.getChildren().addAll(reineImage, tourImage, cavalierImage, fouImage);
		boutons.getChildren().addAll(reine, tour, cavalier, fou);
		boutons.setPadding(new Insets(7));
		images.setSpacing(5);
		boutons.setSpacing(15);

		root.getChildren().addAll(images, boutons);
		upgrade.show();
		ImageView imageNouvelle = (ImageView) paneClick.getChildren().get(0);
		reine.setOnAction((a) -> {
			imageNouvelle.setImage(reineImage.getImage());
			plateau.remplacerPion(new Reine("Q", equipe, rechercheCoordonnee(paneClick.getId())));
			upgrade.close();
		});

		fou.setOnAction((a) -> {
			imageNouvelle.setImage(fouImage.getImage());
			plateau.remplacerPion(new Fou("B", equipe, rechercheCoordonnee(paneClick.getId())));
			upgrade.close();
		});
		tour.setOnAction((a) -> {
			imageNouvelle.setImage(tourImage.getImage());
			plateau.remplacerPion(new Tour("R", equipe, rechercheCoordonnee(paneClick.getId())));
			upgrade.close();
		});
		cavalier.setOnAction((a) -> {
			imageNouvelle.setImage(cavalierImage.getImage());
			plateau.remplacerPion(new Cavalier("N", equipe, rechercheCoordonnee(paneClick.getId())));
			upgrade.close();
		});
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

		}
	}

	private String creerFen()
	{
		String plateauFen = "";
		int entre0et7 = 0;
		int nombreDEspace = 0;

		Pane[] tableauPane = new Pane[64];

		for (int nb = 0; nb < 64; nb++)
		{
			tableauPane[nb] = (Pane) anchor.getChildren().get(nb);
		}
		for (Pane pane : tableauPane)
		{
			ImageView ImageDansLePane = (ImageView) pane.getChildren().get(0);
			if (entre0et7 >= 8)
			{
				plateauFen += "/";
				entre0et7 = 0;
			}
			if (ImageDansLePane.getImage() == null)
			{
			
				if (Character.isDigit(plateauFen.charAt(plateauFen.length() - 1)))
				{
					nombreDEspace = Character.getNumericValue(plateauFen.charAt(plateauFen.length() - 1)) + 1;
					plateauFen = plateauFen.substring(0, plateauFen.length() - 1);
					plateauFen += nombreDEspace;

				}
				else
				{
					plateauFen += "1";
				}
			}
			else
			{

				if (plateau.trouverPieces(rechercheCoordonnee(pane.getId())) != null)
					plateauFen += plateau.trouverPieces(rechercheCoordonnee(pane.getId())).getNom();
			}
			entre0et7++;

		}
		return plateauFen + "/";
	}

	private void chargerUnePartie()
	{
		int entreNomEtFen;
		String leNom;
		String laFen;
		Stage lesAnciennesParties = new Stage();
		lesAnciennesParties.setTitle("Sauvegardes");

		lesAnciennesParties.setMaxHeight(600);
		lesAnciennesParties.setMinHeight(600);
		lesAnciennesParties.setMaxWidth(500);
		lesAnciennesParties.setMinWidth(500);

		ListAnciennesParties = FXCollections.observableArrayList();
		listViewAnciennesParties = new ListView<String>();

		Button charger = new Button("Charger");
		VBox vBox = new VBox(listViewAnciennesParties, charger);
		listViewAnciennesParties.setItems(ListAnciennesParties);

		lesAnciennesParties.setScene(new Scene(vBox));
		lesAnciennesParties.show();

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s = "0";

			while (s != null)
			{
				s = reader.readLine();
				if (s != null)
				{

					entreNomEtFen = s.indexOf("*");
					leNom = s.substring(0, entreNomEtFen);
					laFen = s.substring(entreNomEtFen + 1, s.length());

					ListAnciennesParties.add(leNom + " (" + laFen + ")");
				}
			}

			reader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("erreur dans la lecture, file not found");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("erreur dans la lecture");
			e.printStackTrace();
		}

		charger.setOnAction((a) -> {

			Optional<String> partieSelectionner = Optional
					.ofNullable(listViewAnciennesParties.getSelectionModel().getSelectedItem());

			if (partieSelectionner.isPresent())
			{

				placerPiecesString(partieSelectionner.get().substring(partieSelectionner.get().indexOf("(") + 1,
						partieSelectionner.get().length() - 1) + "/");

			}

		});

	}

	private Point pointSelonEmplacementDansLeTableau(int a)
	{
		int y = 7;
		int x = 0;

		if ((a % 8) + 1 == 8)
		{
			x = 7;
		}
		else
		{
			x = (a % 8);
		}

		y = 7 - (a / 8);
		return new Point(x, y);

	}

	private void avantArriereMouvement()
	{
		tuPeuxBoujerLesPieces = false;

		Mouvement mouvementSelect = arrayMouvement.get(indiceDuMouvement);
		placerPiecesString(mouvementSelect.getFen().get());
		boutonRevenirAuJeu.setDisable(false);
	}

	private void boutonDisable(boolean b)
	{
		tuPeuxBoujerLesPieces = b;
		boutonRevenirAuJeu.setDisable(b);
		boutonMouvementArriere.setDisable(b);
		boutonMouvementAvant.setDisable(b);
	}

	private void jouerSon(String son)
	{
		if (!CheckMenuItemSon.isSelected())
		{
			Media sound = new Media(getClass().getResource(son).toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}

	}

	private void CheckMenuSon()
	{
		CheckMenuItemSon.setText("Désactiver le son");
		CheckMenuItemSon.selectedProperty().addListener((a, o, n) -> {
			SimpleStringProperty ActiverPropertyString = new SimpleStringProperty("Activer le son");
			SimpleStringProperty DesactiverPropertyString = new SimpleStringProperty("Désactiver le son");
			if (a.getValue())
			{
				CheckMenuItemSon.textProperty().bind(ActiverPropertyString);
			}
			else
			{
				CheckMenuItemSon.textProperty().bind(DesactiverPropertyString);
			}
		});

	}

	public void setFenetreAide(Stage aide)
	{
		this.fenetreAide = aide;

	}
}
