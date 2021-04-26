package controleur;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleurAide
{

	@FXML
	private ImageView image;

	@FXML
	private Label bulleInformation;

	@FXML
	void ancienneCase(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation
				.setText("Une case rose pâle indique l’ancien emplacement de la pièce du dernier mouvement fait.");
	}

	@FXML
	void charger(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText(
				"Bouton servant à charger mouvement un depuis la liste des mouvements. Pour cela, il faut sélectionner un mouvement dans la list et ensuite appuyer sur le bouton");
	}

	@FXML
	void help(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText("Dans ce menu se trouve le bouton pour faire apparaitre cette page.");
	}

	@FXML
	void menu(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText(
				"Dans ce menu se trouve 3 sous menus. Le premier, apparence, permet de modifier le thème du plateau entre claire et sombre. Le deuxième, jouer contre IA, doit être coché si le joueur veut que l’ordinateur prennent la place des noirs.");
	}

	@FXML
	void mouvement(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation
				.setText("Une liste de tous les mouvements qui ont été fait apparaissent ici au cours de la partie.");
	}

	@FXML
	void pion(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText("Une case rose foncé indique la pièce sélectionnée.");
	}

	@FXML
	void point(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText(
				"Les points gris indiquent les mouvements possibles de la pièce sélectionnée. Il suffit de cliquer sur une case contenant un point gris pour que la pièce sélectionnée si déplace.");
	}

	@FXML
	void precedentSuivant(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText(
				"Lorsqu'un mouvement est chargé, ces boutons deviennent utilisables. Ils permetent de se déplacer dans la liste des mouvements dans l'ordre qu'ils ont été joué ou dans le sens inverse. Cela permet de rejouer chaques coups d'une partie.");
	}

	@FXML
	void recommencer(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText("Bouton servant à recommencer une nouvelle partie.");
	}

	@FXML
	void regleDuJeu(MouseEvent event)
	{
		try
		{
			Desktop.getDesktop().browse(new URI("https://www.wikiwand.com/fr/R%C3%A8gles_du_jeu_d%27%C3%A9checs"));
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{

			e.printStackTrace();
		}
	}

	@FXML
	void revenir(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText(
				"Lorsqu'un mouvement est chargé, ce bouton devient utilisable. Il permet de revenir à la partie en cours pour continuer "
						+ "à y jouer.");
	}

	@FXML
	void sauvegarde(MouseEvent event)
	{
		bulleInformation.setAlignment(Pos.TOP_CENTER);
		bulleInformation.setText("Dans ce menu se trouve 3 sous menus."
				+ " Le premier, sauvegarder la partie, sauvegarde le positionnement de chaque pièce de la partie en cours."
				+ " Le deuxième, charger une partie, permet d’ouvrir une nouvelle fenêtre montrant tous les sauvegardes des parties antérieurs. Il suffit d’en sélectionner une et d’appuyer sur le bouton « Charger »."
				+ " La dernière, effacer les sauvegardes, efface l’entièreté des parties sauvegardées.");
	}

}
