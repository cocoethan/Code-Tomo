package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Loads, creates, and sets UI view
	 *@author Ethan Coco
	 *@param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/mainFrame.fxml"));
			Scene scene = new Scene(root, 1280, 720);
			primaryStage.setTitle("Test");
			primaryStage.setScene(scene);//Setting View (MVC Model)
			primaryStage.show();
			
			//Controller.tasksListView.setCellFactory(param -> new Controller.Cell());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches application
	 * @author Ethan Coco
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}