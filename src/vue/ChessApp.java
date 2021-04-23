package vue;

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
				this.getClass().getResource("/fxml/board.fxml"));

		BorderPane root = loader.load();
		Scene scene = new Scene(root);

		scene.getStylesheets().setAll(this.getClass()
				.getResource("/css/claire.css").toString());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Échec");
		primaryStage.setMinHeight(740);
		primaryStage.setMinWidth(1100);
		primaryStage.setMaxHeight(740);
		primaryStage.setMaxWidth(1100);
		primaryStage.show();

	}

}
