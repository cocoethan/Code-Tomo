package application.models;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import application.Database;
import application.Controllers.MainController;

public class TasksModel{
	public static void addNewTaskToDB(List<String> currTask) throws SQLException {
		String name = currTask.get(0);
		String priority = currTask.get(1);
		String date = currTask.get(2);
		
		//MainController.updateUpdatesList("New task added.");
		System.out.println("Task Added: " + currTask);
		

		Database.placeReminder(name, null, null, date, priority);
		
	}
	
	public static void deleteTaskToDB(List<String> currTask) throws SQLException {
		String name = currTask.get(0);
		String priority = currTask.get(1);
		String date = currTask.get(2);
		
		System.out.println("Task Deleted: " + currTask);
		Database.removeReminder(name);
		//begin code
		
		
	}
}