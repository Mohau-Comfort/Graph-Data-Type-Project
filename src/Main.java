
import gui.Home;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * @author MC Mahlakwane, 217044163
 * The Main Class
 */
public class Main extends Application {

	/**
	 * This will start the application
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Supply Chain Management");
		
		Home UI = new Home();
		
		Scene UIScene = new Scene(UI,1080,720);
		primaryStage.setScene(UIScene);
		
		primaryStage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}

