package application.models;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import application.Database;
import application.Controllers.MainController;

public class TasksModel{
	public static void addNewTaskToDB(List<String> currTask) {
		String name = currTask.get(0);
		String priority = currTask.get(1);
		String date = currTask.get(2);
		Date date1 = new Date();
		
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Date date2 = new java.sql.Date(date1.getTime());
		
		System.out.println("Task Added: " + currTask);
		
		try {
			Database.placeReminder(name, null, null, date2, priority);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteTaskToDB(List<String> currTask) {
		String name = currTask.get(0);
		
		System.out.println("Task Deleted: " + currTask);
		
		try {
			Database.removeReminder(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
