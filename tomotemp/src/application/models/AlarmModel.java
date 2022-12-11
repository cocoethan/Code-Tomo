package application.models;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.Database;
import application.Tomo;
import application.Controllers.MainController;

public class AlarmModel {
	public static void addAlarmToDB(List<String> currAlarm) {
		String time = currAlarm.get(2);
		String name = currAlarm.get(1);
		
		System.out.println(time);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		long ms = 0;
		try {
			ms = sdf.parse(time).getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Time t = new Time(ms);
		
		java.sql.Time sqlTime = new Time(t.getTime());
		
		System.out.println("!: " + sqlTime);
		
		try {
			Tomo.alarmAdded();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Database.placeAlarm(name, null, sqlTime, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeAlarmToDB(List<String> currAlarm) {
		String name = currAlarm.get(1);
		
		try {
			Tomo.alarmDeleted();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Database.removeAlarm(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}