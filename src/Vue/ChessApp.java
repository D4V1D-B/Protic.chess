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
	public void start(Stage arg0) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(
				this.getClass().getResource("baord.fxml"));

		BorderPane root = loader.load();
		arg0.setScene(new Scene(root));
		arg0.show();

	}

}
