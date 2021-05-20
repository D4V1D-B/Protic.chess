package controleur;

import java.awt.Point;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Cavalier;
import modele.Fou;
import modele.Mouvement;
import modele.Move;
import modele.Pieces;
import modele.Pion;
import modele.Reine;
import modele.Roi;
import modele.Tour;

public class Controleur implements Initializable
{
	private boolean themeClaire = true;
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
	Mouvement mouvementSelect;
	private final ArrayList<Point> LISTPOINTROCK = new ArrayList<Point>()
	{
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
	private RadioMenuItem radioClaire;

	@FXML
	private RadioMenuItem radioSombre;

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
	private Label timerNoir;

	@FXML
	private Label timerBlanc;

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
	private Button boutonAnalyse;

	@FXML
	private Button boutonMouvementAvant;

	private Stage fenetreAide;

	private ScheduledService<String> timerServiceBlanc;

	private ScheduledService<String> timerServiceNoir;

	private String tempsBlanc = "0:00";
	private String tempsNoir = "0:00";

	@FXML
	void radioClaire(ActionEvent event)
	{
		themeClaire = true;
		radioSombre.setSelected(false);
		radioClaire.setSelected(true);

		changementDeStyle(themeClaire);

	}

	@FXML
	void radioSombre(ActionEvent event)
	{
		themeClaire = false;
		radioClaire.setSelected(false);
		radioSombre.setSelected(true);

		changementDeStyle(themeClaire);
	}

	private void changementDeStyle(boolean b)
	{
		BorderPane leParent = (BorderPane) recommencerPartie.getParent().getParent().getParent().getParent().getParent()
				.getParent();
		AnchorPane anchorPane = (AnchorPane) allPane()[0].getParent();

		if (b)
		{

			String defaultButton = "-fx-default-button: #ABD8ED;";

			// Buttons
			recommencerPartie.setStyle(defaultButton);
			boutonChargerMouvements.setStyle(defaultButton);
			boutonMouvementArriere.setStyle(defaultButton);
			boutonMouvementAvant.setStyle(defaultButton);
			boutonRevenirAuJeu.setStyle(defaultButton);
			boutonAnalyse.setStyle(defaultButton);

			for (Node n : anchorPane.getChildren())
			{
				n.setStyle("-fx-text-fill: black;");
			}
			// le reste
			for (Node n : leParent.getChildren())
			{
				n.setStyle("-fx-background-color: null;");
			}

			for (Pane pane : allPane())
			{

				if (pane.getStyleClass().get(0).equals("caseClaire"))
				{
					pane.setStyle("-fx-background-color: cornsilk; -fx-border-color: black;");
				}
				else
				{
					pane.setStyle("-fx-background-color: brown; -fx-border-color: black;");
				}
			}

		}
		else
		{
			String backColor = "-fx-background-color: black;";
			String textColor = "-fx-text-fill: white;";
			String borderColor = "-fx-border-color: grey;";

			// Buttons
			recommencerPartie.setStyle(backColor + textColor + borderColor);
			boutonChargerMouvements.setStyle(backColor + textColor + borderColor);
			boutonMouvementArriere.setStyle(backColor + textColor + borderColor);
			boutonMouvementAvant.setStyle(backColor + textColor + borderColor);
			boutonRevenirAuJeu.setStyle(backColor + textColor + borderColor);
			boutonAnalyse.setStyle(backColor + textColor + borderColor);

			// le reste
			for (Node n : anchorPane.getChildren())
			{
				n.setStyle(textColor);
			}

			for (Node n : leParent.getChildren())
			{
				n.setStyle("-fx-background-color: black;");
			}

			for (Pane pane : allPane())
			{

				if (pane.getStyleClass().get(0).equals("caseClaire"))
				{
					pane.setStyle("-fx-background-color: dimgray; -fx-border-color: black;");
				}
				else
				{
					pane.setStyle("-fx-background-color: brown; -fx-border-color: black;");
				}
			}
		}
	}

	@FXML
	void analyse(MouseEvent event)
	{
		Move m = bot.jouerBot(plateau);
		String nomDuPane = recherchePane(m.getPoint());
		Pane paneConseil = null;
		Pane panePiece = null;
		for (Pane pane : allPane())
		{

			if (pane.getId().equals(nomDuPane))
			{
				paneConseil = pane;

			}
			if (pane.getId().equals(recherchePane(m.getPieces().getEmplacement())))
			{
				panePiece = pane;
			}
		}
		paneConseil.setStyle("-fx-background-color:blue; -fx-border-color: black");
		panePiece.setStyle("-fx-background-color:blue; -fx-border-color: black");

	}

	@FXML
	void boutonTest(ActionEvent event)
	{
		TextInputDialog inDialog = new TextInputDialog("p2k3p/2Q2q2/8/3rR3/3Bb3/8/2q2Q2/P3K2P/");
		inDialog.setTitle("Information demandée");
		inDialog.setHeaderText("Entrer une Fen.");
		inDialog.setContentText("Fen :");

		Optional<String> textIn = inDialog.showAndWait();
		textIn.ifPresent(str -> placerPiecesString(textIn.get()));

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

			mouvementSelect = arrayMouvement.get(indiceDuMouvement);
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
			resetCouleur();
			listDeMouvement.getSelectionModel().select(indiceDuMouvement);
		}
	}

	@FXML
	void mouvementAvant(MouseEvent event)
	{

		if (indiceDuMouvement < arrayMouvement.size() - 1)
		{
			indiceDuMouvement++;
			avantArriereMouvement();
			resetCouleur();
			listDeMouvement.getSelectionModel().select(indiceDuMouvement);
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

				tempsBlanc = timerServiceBlanc.getLastValue();
				tempsNoir = timerServiceBlanc.getLastValue();

				writer.append(textIn.get() + "É" + isTourJoueur() + "." + tempsBlanc + "!" + tempsNoir + "*"
						+ creerFen() + "\n");

				writer.close();
			}
			catch (IOException e)
			{
				System.out.println("Erreur dans la sauvegarde!");
				e.printStackTrace();
			}
		}
	}

	private void effacerLesSauvegardes()
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
		initializeTimerBlanc();
		initializeTimerNoir();
		resetTotal();
		boutonDisable(true);
		CheckMenuSon();
		radioClaire.setSelected(themeClaire);
		radioSombre.setSelected(!themeClaire);
	}

	private void initializeTimerBlanc()
	{
		timerServiceBlanc = new ScheduledService<String>()
		{
			@Override
			protected Task<String> createTask()
			{
				return new Task<String>()
				{
					@Override
					protected String call() throws Exception
					{
						String value;
						if (timerServiceBlanc.getLastValue() == null)
						{
							value = tempsBlanc;
						}
						else
						{
							String manip = timerServiceBlanc.getLastValue();
							int minute = Integer.parseInt(manip.substring(0, manip.indexOf(":")));
							int seconde = Integer.parseInt(manip.substring(manip.indexOf(":") + 1));

							seconde++;

							if (seconde == 60)
							{
								seconde = 0;
								minute++;
							}

							if (seconde < 10)
							{
								value = minute + ":0" + seconde;
							}
							else
							{
								value = minute + ":" + seconde;
							}
						}
						updateValue(value);
						return value;
					}
				};
			}
		};
		timerServiceBlanc.setPeriod(new Duration(1000));

		timerBlanc.textProperty().bind(timerServiceBlanc.lastValueProperty());

		if (timerServiceBlanc.getState() != State.READY)
		{
			timerServiceBlanc.reset();
		}
	}

	private void initializeTimerNoir()
	{
		timerServiceNoir = new ScheduledService<String>()
		{
			@Override
			protected Task<String> createTask()
			{
				return new Task<String>()
				{
					@Override
					protected String call() throws Exception
					{
						String value;
						if (timerServiceNoir.getLastValue() == null)
						{
							value = tempsNoir;
						}
						else
						{
							String manip = timerServiceNoir.getLastValue();
							int minute = Integer.parseInt(manip.substring(0, manip.indexOf(":")));
							int seconde = Integer.parseInt(manip.substring(manip.indexOf(":") + 1));

							seconde++;

							if (seconde == 60)
							{
								seconde = 0;
								minute++;
							}

							if (seconde < 10)
							{
								value = minute + ":0" + seconde;
							}
							else
							{
								value = minute + ":" + seconde;
							}
						}
						updateValue(value);
						return value;
					}
				};
			}
		};
		timerServiceNoir.setPeriod(new Duration(1000));

		timerNoir.textProperty().bind(timerServiceNoir.lastValueProperty());

		if (timerServiceNoir.getState() != State.READY)
		{
			timerServiceNoir.reset();
		}
	}

	public void setLabelTourCouleur(Label labelTourCouleur)
	{
		if (this.isTourJoueur())
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

		list = FXCollections.observableArrayList();
		listDeMouvement.setItems(list);

		placerPiecesString(placementDepart);
		resetCouleur();

		tourJoueur = true;
		setLabelTourCouleur(labelTourCouleur);

		jouerSon("/son/Game_start.mp3");

		timerServiceNoir.cancel();
		timerServiceBlanc.cancel();
		tempsBlanc = "0:00";
		tempsNoir = "0:00";
		timerServiceBlanc.reset();
		timerServiceNoir.reset();
		timerServiceBlanc.start();

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

		plateau = new Plateau(blanc, noir, tourJoueur);
	}

	private void JouerAI()
	{
		if (isTourJoueur() == false)
		{
			Move move = bot.jouerBot(this.plateau);
			Pane paneFinale = null;
			for (int i = 0; i < allPane().length && paneFinale == null; i++)
			{
				if (allPane()[i].getId().equals(recherchePane(move.getPieces().getEmplacement())))
				{
					paneFinale = allPane()[i];
				}
			}
			Pane paneClick = null;
			for (int i = 0; i < allPane().length && paneClick == null; i++)
			{
				if (allPane()[i].getId().equals(recherchePane(move.getPoint())))
				{
					paneClick = allPane()[i];
				}
			}
			paneSelect = paneFinale;
			pieceSelect = move.getPieces();
			deplacer(pieceSelect, paneClick);
			deplacerImage(paneClick, paneSelect, pieceSelect);
			tourJoueur = !isTourJoueur();
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

				if (pieceSelect != null && pieceSelect.isWhite() == isTourJoueur())
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
						&& pieceSelect.isWhite() == isTourJoueur())
				{
					timerServiceBlanc.cancel();
					timerServiceNoir.cancel();

					jouerSon("/son/Move.mp3");
					deplacer(pieceSelect, paneClick);

					if (!plateau.getEchecMathBlanc() && !plateau.getEchecMathNoir() && plateau.partieNulle())
					{
						tempsBlanc = timerServiceBlanc.getLastValue();
						tempsNoir = timerServiceNoir.getLastValue();
						int secondeBlanc = Integer.parseInt(tempsBlanc.substring(tempsBlanc.indexOf(":") + 1));
						int secondeNoir = Integer.parseInt(tempsNoir.substring(tempsNoir.indexOf(":") + 1));
						String minuteBlanc = tempsBlanc.substring(0, tempsBlanc.indexOf(":"));
						String minuteNoir = tempsNoir.substring(0, tempsNoir.indexOf(":"));
						int minute = Integer.parseInt(minuteBlanc) + Integer.parseInt(minuteNoir);
						int seconde = secondeBlanc + secondeNoir;
						jouerSon("/son/Checkmate.mp3");
						afficherFinDePartie("Partie nulle! Meilleurs chance la prochaine fois!\nLes blancs ont utilisé "
								+ minuteBlanc + " minutes et " + secondeBlanc + " secondes.\nLes noirs eux ont utilisé "
								+ minuteNoir + " minutes et " + secondeNoir + " secondes.\nLa partie a duré " + minute
								+ " minutes et " + seconde + " secondes!");
					}
					if (isTourJoueur())
					{
						tempsBlanc = timerServiceBlanc.getLastValue();
						if (plateau.getEchecMathNoir())
						{
							tempsNoir = timerServiceNoir.getLastValue();
							int secondeBlanc = Integer.parseInt(tempsBlanc.substring(tempsBlanc.indexOf(":") + 1));
							int secondeNoir = Integer.parseInt(tempsNoir.substring(tempsNoir.indexOf(":") + 1));
							String minuteBlanc = tempsBlanc.substring(0, tempsBlanc.indexOf(":"));
							String minuteNoir = tempsNoir.substring(0, tempsNoir.indexOf(":"));
							int minute = Integer.parseInt(minuteBlanc) + Integer.parseInt(minuteNoir);
							int seconde = secondeBlanc + secondeNoir;
							jouerSon("/son/Checkmate.mp3");
							afficherFinDePartie("Les blancs ont gagné! Félicitation!\nIls ont utilisé " + minuteBlanc
									+ " minutes et " + secondeBlanc + " secondes.\nLes noirs eux ont utilisé "
									+ minuteNoir + " minutes et " + secondeNoir + " secondes.\nLa partie a duré "
									+ minute + " minutes et " + seconde + " secondes!");
						}
						timerServiceNoir.restart();
					}
					else
					{
						tempsNoir = timerServiceNoir.getLastValue();
						if (plateau.getEchecMathBlanc())
						{
							tempsBlanc = timerServiceBlanc.getLastValue();
							int secondeBlanc = Integer.parseInt(tempsBlanc.substring(tempsBlanc.indexOf(":") + 1));
							int secondeNoir = Integer.parseInt(tempsNoir.substring(tempsNoir.indexOf(":") + 1));
							String minuteBlanc = tempsBlanc.substring(0, tempsBlanc.indexOf(":"));
							String minuteNoir = tempsNoir.substring(0, tempsNoir.indexOf(":"));
							int minute = Integer.parseInt(minuteBlanc) + Integer.parseInt(minuteNoir);
							int seconde = secondeBlanc + secondeNoir;
							jouerSon("/son/Checkmate.mp3");
							afficherFinDePartie("Les noirs ont gagné! Félicitation!\nIls ont utilisé " + minuteNoir
									+ " minutes et " + secondeNoir + " secondes.\nLes noirs eux ont utilisé "
									+ minuteBlanc + " minutes et " + secondeBlanc + " secondes.\nLa partie a duré "
									+ minute + " minutes et " + seconde + " secondes!");
						}
						timerServiceBlanc.restart();
					}

					tourJoueur = !isTourJoueur();
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
			// System.out.println(pieceSelect.isWhite());
			afficherPionUgrade(pieceSelect.isWhite(), paneClick);
		}

	}

	private void deplacerProg(Pieces pieces, Pane positionFinale)
	{
		// deplacement dans la prog
		pieces.setEmplacement(rechercheCoordonnee(paneSelect.getId()));
		plateau.deplacementProg(pieces, rechercheCoordonnee(positionFinale.getId()));
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
			if (themeClaire)
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
			else
			{
				if (i % 2 == 0)
				{
					tableauPane[i].setStyle("-fx-background-color:brown; -fx-border-color: black");
				}
				else
				{
					tableauPane[i].setStyle("-fx-background-color: dimgray; -fx-border-color: black;");
				}
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
					new SimpleStringProperty(creerFen()));

			list.add(m.toStringCastle());
			arrayMouvement.add(m);
		}
		else
		{
			Mouvement m = new Mouvement(new SimpleStringProperty(p.getNom()), new SimpleStringProperty(positionFinale),
					new SimpleStringProperty(creerFen()));

			list.add(m.toString());
			arrayMouvement.add(m);
		}
		listDeMouvement.getSelectionModel().select(list.size() - 1);
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
			if (equipe)
			{

				plateau.remplacerPion(new Reine("Q", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			else
			{
				plateau.remplacerPion(new Reine("q", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			upgrade.close();
		});

		fou.setOnAction((a) -> {
			imageNouvelle.setImage(fouImage.getImage());
			if (equipe)
			{
				plateau.remplacerPion(new Fou("B", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			else
			{
				plateau.remplacerPion(new Fou("b", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			upgrade.close();
		});
		tour.setOnAction((a) -> {
			imageNouvelle.setImage(tourImage.getImage());
			if (equipe)
			{
				plateau.remplacerPion(new Tour("R", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			else
			{
				plateau.remplacerPion(new Tour("r", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			upgrade.close();
		});
		cavalier.setOnAction((a) -> {
			imageNouvelle.setImage(cavalierImage.getImage());
			if (equipe)
			{
				plateau.remplacerPion(new Cavalier("N", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			else
			{
				plateau.remplacerPion(new Tour("n", equipe, rechercheCoordonnee(paneClick.getId())));
			}
			upgrade.close();
		});
	}

	private void afficherFinDePartie(String finParti)
	{
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("Fin de Partie !");
		alert.setHeaderText(finParti);
		alert.setContentText(null);

		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();

		chargerMouvement(null);
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
			ImageView imageDansLePane = (ImageView) pane.getChildren().get(0);

			if (entre0et7 >= 8)
			{
				plateauFen += "/";
				entre0et7 = 0;
			}

			if (imageDansLePane.getImage() == null)
			{
				if (plateauFen.equalsIgnoreCase(""))
				{
					plateauFen = "1";
				}
				else
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
		String leNom;
		String laFen;
		Stage lesAnciennesParties = new Stage();
		lesAnciennesParties.setTitle("Sauvegardes");

		lesAnciennesParties.setMaxHeight(400);
		lesAnciennesParties.setMinHeight(400);
		lesAnciennesParties.setMaxWidth(500);
		lesAnciennesParties.setMinWidth(500);

		ListAnciennesParties = FXCollections.observableArrayList();
		listViewAnciennesParties = new ListView<String>();
		Button effacer = new Button("Effacer");
		effacer.setMinWidth(175);
		Button charger = new Button("Charger");
		charger.setMinWidth(175);
		HBox hBox = new HBox(charger, effacer);
		hBox.setPadding(new Insets(20.0));
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(50.0);
		VBox vBox = new VBox(listViewAnciennesParties, hBox);

		listViewAnciennesParties.setItems(ListAnciennesParties);

		lesAnciennesParties.setScene(new Scene(vBox));
		lesAnciennesParties.show();

		ArrayList<String> ouiOui = new ArrayList<String>();

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s = "0";

			while (s != null)
			{
				s = reader.readLine();
				if (s != null)
				{
					ouiOui.add(s);
					leNom = s.substring(0, s.indexOf("É"));
					tourJoueur = s.substring(s.indexOf("É") + 1, s.indexOf(".")).equals("true");
					tempsBlanc = s.substring(s.indexOf(".") + 1, s.indexOf("!"));
					tempsNoir = s.substring(s.indexOf("!") + 1, s.indexOf("*"));
					laFen = s.substring(s.indexOf("*") + 1, s.length());

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
				System.out.println(tempsBlanc + "    " + tempsNoir);
				timerServiceNoir.cancel();
				timerServiceBlanc.cancel();
				timerServiceBlanc.reset();
				timerServiceNoir.reset();
				if (tourJoueur)
				{
					timerServiceBlanc.start();
				}
				else
				{
					timerServiceNoir.start();
				}
			}

		});
		effacer.setOnAction((a) -> {
			Optional<String> partieSelectionner = Optional
					.ofNullable(listViewAnciennesParties.getSelectionModel().getSelectedItem());

			if (partieSelectionner.isPresent())
			{
				ListAnciennesParties.remove(partieSelectionner.get());

				String reformate = partieSelectionner.get();
				reformate = reformate.replace(" ", "*");
				reformate = reformate.replace("(", "");
				reformate = reformate.replace(")", "");

				ouiOui.remove(reformate);

				effacerLesSauvegardes();

				try
				{
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

					for (String string : ouiOui)
					{
						writer.append(string + "\n");
					}

					writer.close();
				}
				catch (IOException e)
				{
					System.out.println("Erreur dans lors de effacement des sauvegardes.");
					e.printStackTrace();
				}

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
		boutonAnalyse.setDisable(b);
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

	public boolean isTourJoueur()
	{
		return tourJoueur;
	}

	
}
