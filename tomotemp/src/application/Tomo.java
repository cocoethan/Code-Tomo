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
	static Double health = 0.5; //Range is 0-1; 0.4 == 40% etc.
	static Double hunger = 0.5; //Range is 0-1; 0.4 == 40% etc. 
	static Double happy = 0.5;  //Range is 0-1; 0.4 == 40% etc.
	//-0.2 to 0.8
	
	static String angryGIF = "angry.gif";    					//String containing file name to pass to MainController.updateImg()
	static String deathGIF = "death.gif";						//String containing file name to pass to MainController.updateImg()
	static String fullHealthGIF = "full_health.gif";			//String containing file name to pass to MainController.updateImg()
	static String sleepingGIF = "sleeping.gif";					//String containing file name to pass to MainController.updateImg()
	static String sleeping2GIF = "sleeping2.gif";				//String containing file name to pass to MainController.updateImg()
	static String smileBlinkingGIF = "smile blinking.gif";		//String containing file name to pass to MainController.updateImg()
	static String tastyGIF = "tasty.gif";						//String containing file name to pass to MainController.updateImg()
	static String bigsmile = "big smile.gif";					//String containing file name to pass to MainController.updateImg()
	static String birth = "birth.gif";							//String containing file name to pass to MainController.updateImg()
	static String fullhealth = "full_health.gif";				//String containing file name to pass to MainController.updateImg()
	static String surprise = "surprise.gif";					//String containing file name to pass to MainController.updateImg()
	static String blink = "smile blinking2.gif";					//String containing file name to pass to MainController.updateImg()
	static String tastyhealth = "tastyHEALTH.gif";					//String containing file name to pass to MainController.updateImg()

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
		MainController.updateImg(birth);
	}
	
	static void databaseIn() {
		
	}
	
	//Executes when new task is added
	public static void taskAdded() {
		MainController.updateUpdatesList("New task added.");//Execute this to update updates-list below tamagotchi
		//Start ->
		//Added just for reference, please add your own with different logic
		
		health+=0.2;
		hunger+=0.3;
		happy+=0.2;
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		
		MainController.updateImg(blink);
		
		//energy to do tasks
		hunger-=0.1;
		MainController.updateHungerBar(hunger);
		
		checkNumbers();
		checkCat();
		//<- End
	}
	
	//Executes when task is deleted
	public static void taskDeleted() {
		MainController.updateUpdatesList("Task removed.");//Execute this to update updates-list below tamagotchi

		health -= 0.3;
		hunger-=0.4;
		happy-=0.3;
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		
		if ((points%2)== 0){
		MainController.updateImg(surprise);	
		}//if 
		else {
			MainController.updateImg(angryGIF);	
		}//else
		
		checkNumbers();
		checkCat();
		
	}
	
	//Executes when task is completed
	public static void taskCompleted(String timeAdded, String timeCompleted) {//timeAdded/timeCompleted are strings of format "YYYY-MM-DD-HR:MN" describing time task was added and deleted
		MainController.updateImg(tastyGIF);

		points +=1;
		MainController.updatePointsCounter(points.toString());
		health += 0.3;
		hunger+=0.2;
		happy+=0.4;
		
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		MainController.updateImg(bigsmile);
		
		checkHunger();
		checkNumbers();
		
		
	}
	public static void checkCat() {
		if (health < -0.0){			
			MainController.updateImg(deathGIF);
			MainController.updateUpdatesList("Your virtual pet has died.");
			if (points == 0) {
			MainController.updateUpdatesList("Goodbye.");
			}
			if (points == 1) {
				MainController.updateUpdatesList("I'm afraid. I'm afraid, Dave. Dave, my mind is going. I can feel it.");
			}
			if (points >= 2) {
				MainController.updateUpdatesList("So, why not give it another shot?");
			}
			
		}
		else if(health == 1){
			MainController.updateImg(tastyhealth);				
		}
		else if ((hunger < 0.2)&&(health>-0.2)) {
			checkHunger();
			MainController.updateImg(angryGIF);
		}
		else if (happy <0.2){
			checkHunger();
			MainController.updateImg(angryGIF);

		}
	}
	public static void checkHunger() {
		if(hunger <0.3) {
			health -=0.2;
		}
	}
	public static void checkNumbers() {
		if (health > 1) {
			health = 1.0;
		}
		if (hunger > 1) {
			hunger = 1.0;
		}
		if (happy > 1) {
			happy = 1.0;
		}
		if (health < -0.2) {
			health = -0.2;
		}
		if (hunger < -0.2) {
			hunger = -0.2;
		}
		if (happy < -0.2) {
			happy = -0.2;
		}
	}
	
	public static void alarmAdded() {
		MainController.updateUpdatesList("New task added.");//Execute this to update updates-list below tamagotchi
		//Start ->
		//Added just for reference, please add your own with different logic
		
		health+=0.2;
		hunger+=0.3;
		happy+=0.2;
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		
		MainController.updateImg(blink);
		
		//energy to do tasks
		hunger-=0.1;
		MainController.updateHungerBar(hunger);
		
		checkNumbers();
		checkCat();
		//<- End
	}
	
	public static void alarmDeleted() {
		MainController.updateUpdatesList("Task removed.");//Execute this to update updates-list below tamagotchi

		health -= 0.3;
		hunger-=0.4;
		happy-=0.3;
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		
		if ((points%2)== 0){
		MainController.updateImg(surprise);	
		}//if 
		else {
			MainController.updateImg(angryGIF);	
		}//else
		
		checkNumbers();
		checkCat();
	}
	
	public static void alarmChecked() {//runs when alarm goes off and is responded to
		MainController.updateImg(tastyGIF);

		points +=1;
		MainController.updatePointsCounter(points.toString());
		health += 0.3;
		hunger+=0.2;
		happy+=0.4;
		
		MainController.updateHealthBar(health);
		MainController.updateHungerBar(hunger);
		MainController.updateHappyBar(happy);
		MainController.updateImg(bigsmile);
		
		checkHunger();
		checkNumbers();
	}

}