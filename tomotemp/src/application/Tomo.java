package application;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;

import application.Controllers.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public class Tomo implements Initializable{
	
	//Variables for tomo stats
	static Integer points = 0;
	static Double health = 0.0; //Range is 0-1; 0.4 == 40% etc.
	static Double hunger = 0.0; //Range is 0-1; 0.4 == 40% etc.
	static Double happy = 0.0;  //Range is 0-1; 0.4 == 40% etc.
	
	static String angryGIF = "angry.gif";    					//String containing file name to pass to MainController.updateImg()
	static String deathGIF = "death.gif";						//String containing file name to pass to MainController.updateImg()
	static String fullHealthGIF = "full_health.gif";			//String containing file name to pass to MainController.updateImg()
	static String sleepingGIF = "sleeping.gif";					//String containing file name to pass to MainController.updateImg()
	static String sleeping2GIF = "sleeping2.gif";				//String containing file name to pass to MainController.updateImg()
	static String smileBlinkingGIF = "smile blinking.gif";		//String containing file name to pass to MainController.updateImg()
	static String tastyGIF = "tasty.gif";						//String containing file name to pass to MainController.updateImg()
	//If adding new images/gifs, first add file to resources.images folder in eclipse project file, then create similar String object to the others
	
	
	//Useful functions -
			//Used to update points:
				//MainController.updatePointsCounter(points.toString())
			//Used to update health progress bar:
				//MainController.updateHealthBar(health);
			//Used to update hunger progress bar:
				//MainController.updateHungerBar(hunger);
			//Used to update happiness progress bar:
				//MainController.updateHappyBar(happy);
			//Add to updates list below tamagotchi
				//MainController.updateUpdatesList("anything");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	//Executes when new task is added
	public static void taskAdded() {
		MainController.updateUpdatesList("New task added.");//Execute this to update updates-list below tamagotchi
		//Start ->
		//Added just for reference, please add your own with different logic
		points = 1;
		MainController.updatePointsCounter(points.toString());
		health = 0.4;
		MainController.updateHealthBar(health);
		hunger = 0.3;
		MainController.updateHungerBar(hunger);
		happy = 0.8;
		MainController.updateHappyBar(happy);
		
		MainController.updateImg(deathGIF);
		//<- End
	}
	
	//Executes when task is deleted
	public static void taskDeleted() {
		MainController.updateUpdatesList("Task removed.");//Execute this to update updates-list below tamagotchi
	}
	
	//Executes when task is completed
	public static void taskCompleted(String timeAdded, String timeCompleted) {//timeAdded/timeCompleted are strings of format "YYYY-MM-DD-HR:MN" describing time task was added and deleted
		
	}
}