package application.models;

import java.sql.Time;
import java.util.List;

import application.Database;
import application.Tomo;
import application.Controllers.MainController;

public class AlarmModel {
	public static void addAlarmToDB(List<String> currAlarm) {
		String time = currAlarm.get(0);
		String name = currAlarm.get(1);
		
		Tomo.alarmAdded();
		
		//FOR LANE
		//call func from Database.java to add alarm
	}
	
	public static void removeAlarmToDB(List<String> currAlarm) {
		String time = currAlarm.get(0);
		String name = currAlarm.get(1);
		
		Tomo.alarmDeleted();
		
		//FOR LANE
		//call func from Database.java to delete alarm
	}
}