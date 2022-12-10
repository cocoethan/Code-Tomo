package application.models;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.Database;
import application.Tomo;
import application.Controllers.MainController;
public class AlarmModel {
	public static void addAlarmToDB(List<String> currAlarm) throws SQLException {
		String time = currAlarm.get(0);
		String name = currAlarm.get(1);
		System.out.println(name);
		if(name.equals("Alarm 1")) {
			name.replace(" 1","");
			int i = ThreadLocalRandom.current().nextInt(990);;
			String rand = String.valueOf(i);
			name = name.concat(rand);
		}
		System.out.println(name);
		Database.placeAlarm(name, null, time, null);
		
		Tomo.alarmAdded();
		
		//FOR LANE
		//call func from Database.java to add alarm
	}
	
	public static void removeAlarmToDB(List<String> currAlarm) throws SQLException {
		String time = currAlarm.get(0);
		String name = currAlarm.get(1);
		Database.removeAlarm(time);
		Tomo.alarmDeleted();
		
		
		//FOR LANE
		//call func from Database.java to delete alarm
	}
}