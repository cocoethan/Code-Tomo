package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.Controllers.AlarmsController;
import application.Controllers.TasksController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	static String currTime;
	
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
			//scene.getStylesheets().add("/resources/css/list-view-cell-gap.css");
			primaryStage.setScene(scene);//Setting View (MVC Model)
			primaryStage.show();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime now = LocalDateTime.now();
			//System.out.println(dtf.format(now));
			
			
			//Thread clock = new Thread(){
			    //public void run(){
			      //System.out.println("Thread Running");
			      //currTime = dtf.format(now);
			    //}
			//};
			//clock.start();
			
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
		
		Database db = new Database();
		ResultSet rs;
		try {
			rs = db.displayUsers();
			while(rs.next()) {
				System.out.println(rs.getString("userName") + " ");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TasksController.databaseIn();
		AlarmsController.databaseIn();
		
		launch(args);
		
	}
}
//test