package vue;

import controleur.Controleur;
import controleur.ControleurAide;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChessApp extends Application
{

	public static void main(String[] args)
	{
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{

		FXMLLoader loaderChess = new FXMLLoader(this.getClass().getResource("/fxml/board.fxml"));

		BorderPane root = loaderChess.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().setAll(this.getClass().getResource("/css/claire.css").toString());
		Controleur controleurChess = loaderChess.getController();

		Stage aide = new Stage();
		FXMLLoader loader2 = new FXMLLoader(this.getClass().getResource("/fxml/aide.fxml"));

		ControleurAide controleurAide = loader2.getController();
		controleurChess.setFenetreAide(aide);

		aide.setScene(new Scene(loader2.load()));
		aide.setTitle("Aide");

		primaryStage.setScene(scene);
		primaryStage.setTitle("Échec");
		primaryStage.setMinHeight(740);
		primaryStage.setMinWidth(1000);
		primaryStage.setMaxHeight(740);
		primaryStage.setMaxWidth(1000);
		primaryStage.show();

	}

}
