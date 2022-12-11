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

//Main method
//Sets MVC stage and starts program

public class Main extends Application{
	
	static String currTime;
	
	@Override
	public void start(Stage primaryStage) {//Sets MVC Stage
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/mainFrame.fxml"));
			Scene scene = new Scene(root, 1280, 720);
			primaryStage.setTitle("tomo");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime now = LocalDateTime.now();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {//Starts program / Starts database
		Database db = new Database();
		ResultSet rs;
		try {
			rs = db.displayUsers();
			while(rs.next()) {
				System.out.println(rs.getString("userName") + " ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TasksController.databaseIn();
		AlarmsController.databaseIn();
		
		launch(args);
		
	}
}
