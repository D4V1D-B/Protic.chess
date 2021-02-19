package Vue;

import Controleur.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationChess extends Application
{

	public static void main(String[] args)
	{
		ApplicationChess.launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				this.getClass().getResource("/fxml/baord.fxml/"));
		primaryStage.setScene(new Scene(loader.load()));

		Controleur controleur = loader.getController();
		primaryStage.setTitle("Échec");
		primaryStage.show();

	}

}
