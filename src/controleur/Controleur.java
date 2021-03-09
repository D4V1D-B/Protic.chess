package controleur;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modele.Cavalier;
import modele.Fou;
import modele.Pieces;
import modele.Pion;
import modele.Reine;
import modele.Roi;
import modele.Tour;

public class Controleur implements Initializable
{
	HashMap<String, String> tableau = new HashMap<>();
	private Plateau plateau;
	public String placementDepart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
	public Pieces pieceSelect;
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Tour R1 = new Tour("R1", true, new Point());
		Cavalier N1 = new Cavalier("N1", true, chercherCoordonnee('N'));
		Fou B1 = new Fou("B1", true, chercherCoordonnee('B'));
		Reine Q = new Reine("Q", true, chercherCoordonnee('Q'));
		Roi K = new Roi("K", true, chercherCoordonnee('K'));
		Fou B2 = new Fou("B2", true, chercherCoordonnee('B'));
		Cavalier N2 = new Cavalier("N2", true, chercherCoordonnee('N'));
		Tour R2 = new Tour("R2", true, chercherCoordonnee('R'));

		Pion P1 = new Pion("P1", true, chercherCoordonnee('P'));
		Pion P2 = new Pion("P2", true, chercherCoordonnee('P'));
		Pion P3 = new Pion("P3", true, chercherCoordonnee('P'));
		Pion P4 = new Pion("P4", true, chercherCoordonnee('P'));
		Pion P5 = new Pion("P5", true, chercherCoordonnee('P'));
		Pion P6 = new Pion("P6", true, chercherCoordonnee('P'));
		Pion P7 = new Pion("P7", true, chercherCoordonnee('P'));
		Pion P8 = new Pion("P8", true, chercherCoordonnee('P'));

		Tour r1 = new Tour("r1", false, new Point(0,0));
		Cavalier n1 = new Cavalier("n1", false, new Point(1,0));
		Fou b1 = new Fou("b1", false, new Point(2,0));
		Reine q = new Reine("q", false, new Point(3,0));
		Roi k = new Roi("k", false, new Point(4,0));
		Fou b2 = new Fou("b2", false, new Point(5,0));
		Cavalier n2 = new Cavalier("n2", false, new Point(6,0));
		Tour r2 = new Tour("r2", false, new Point(7,0));

		Pion p1 = new Pion("p1", false, new Point(0,1));
		Pion p2 = new Pion("p2", false, new Point(1,1));
		Pion p3 = new Pion("p3", false, new Point(2,1));
		Pion p4 = new Pion("p4", false, new Point(3,1));
		Pion p5 = new Pion("p5", false, new Point(4,1));
		Pion p6 = new Pion("p6", false, new Point(5,1));
		Pion p7 = new Pion("p7", false, new Point(6,1));
		Pion p8 = new Pion("p8", false, new Point(7,1));

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

		afficherCodeSecret(placementDepart);

		System.out.println(k.getEmplacement().toString());
		System.out.println(p3.getEmplacement().toString());
		System.out.println(N1.getEmplacement().toString());
		System.out.println(N2.getEmplacement().toString());

	}

	public void afficherCodeSecret(String placement)
	{

		HashMap<String, Image> association = new HashMap<String, Image>();
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
		plateau.trouverPieces(rechercheCoordonnee(p.getId()));
	}

	private Point rechercheCoordonnee(String p)
	{
		Point coordonnee = new Point();
		char premiereLettre = p.toUpperCase().charAt(0);
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
				Character.getNumericValue(p.charAt(1))-1);
		return coordonnee;

	}
	
//	private Point chercherCoordonnee(char pieceCherche) // x= range y=colonne
//	{
//
//		Point p = new Point();
//
//		int x = placementDepart.indexOf(pieceCherche);
//		String subString = placementDepart.substring(0, x + 1);
//
//		int nbSlash = 0;
//		int positionDuSlash = 0;
//		int positionActuelle = 0;
//		for (char c : subString.toCharArray())
//		{
//			if (c == '/')
//			{
//				nbSlash++;
//				positionDuSlash = positionActuelle;
//
//			}
//			positionActuelle++;
//		}
//
//		// TODO ne focntionne pas coordonne x
//		String subSubString = subString.substring(positionDuSlash + 1,
//				subString.length());
//
//		int positionCherche = 0;
//		int positionActuelle2 = 0;
//		for (char h : subSubString.toCharArray())
//		{
//			if (h == pieceCherche)
//			{
//				positionCherche = positionActuelle2;
//			}
//			positionActuelle2++;
//		}
//
//		placementDepart.replaceFirst(Character.toString(pieceCherche), "1");
//		System.out.println(placementDepart);
//		p.setLocation(positionCherche, nbSlash);
//		return p;
//	}
}
