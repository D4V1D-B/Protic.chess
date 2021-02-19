package Vue;

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
		FXMLLoader loader = new FXMLLoader(
				this.getClass().getResource("/fxml/baord.fxml"));

		BorderPane root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

	}

}
