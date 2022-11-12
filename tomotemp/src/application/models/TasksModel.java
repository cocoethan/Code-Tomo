package application.models;

import java.util.List;

public class TasksModel{
	public static void addNewTaskToDB(List<String> currTask) {
		String name = currTask.get(0);
		String priority = currTask.get(1);
		String date = currTask.get(2);
		
		System.out.println("Task Added: " + currTask);
	}
	
	public static void deleteTaskToDB(List<String> currTask) {
		String name = currTask.get(0);
		String priority = currTask.get(1);
		String date = currTask.get(2);
		
		System.out.println("Task Deleted: " + currTask);
	}
}