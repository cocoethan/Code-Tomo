package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Database;
import application.Tomo;
import application.Controllers.TasksController;
import application.Controllers.TasksController.Cell;
import application.models.TasksModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TasksController implements Initializable{

    @FXML
    private Button addTaskBtn;

    @FXML
    private ListView<String> tasksListView;
    
    
    @FXML private DialogPane tAddTaskPane;
    @FXML private DatePicker tDateDatePicker;
    @FXML private TextField tNotesTextField;
    @FXML private ChoiceBox<String> tPriorChoiceBox;
    @FXML private TextField tTimeTextField;
    @FXML private TextField tTitleTextField;
    
    private int selectedTask;
    private int startCheck = 0;
    private int startCheck1 = 0;
    static int priorChoiceCheck = 0;
    
    private String[] priorityChoiceBox = {"Low Priority", "Medium Priority", "High Priority"};
    
    private String tTitleTextFieldToString;
    static String tPriorChoiceBoxToString;
    private int priorChoiceBoxToInt = 0;
    
    static List<String> Task = new ArrayList<>();
    static List<List<String>> taskList = new ArrayList<>();
    
    static List<String> tempTask = new ArrayList<>();
    
    static List<List<String>> dbTasks = new ArrayList<>();
    
    static ObservableList<String> mylist = FXCollections.observableArrayList();
    
    static class Cell extends ListCell<String>{
    	CheckBox c = new CheckBox();
    	Label spaceLabel = new Label("  ");
    	
    	Label priorityLabel = new Label("");
    	Label dateLabel = new Label("");
    	
    	HBox tasksListViewHbox = new HBox();
    	Pane tasksListViewPane = new Pane();
    	Label tasksListViewTitleLabel = new Label("");
    	Button tasksListViewDeleteBtn = new Button("Delete");
    	
    	public Cell() {
    		super();
    		
    		tasksListViewTitleLabel.setFont(new Font("Arial",15));
    		tasksListViewHbox.getChildren().addAll(c, spaceLabel, priorityLabel, tasksListViewTitleLabel, dateLabel, tasksListViewPane, tasksListViewDeleteBtn);
    		tasksListViewHbox.setHgrow(tasksListViewPane, Priority.ALWAYS);
    		tasksListViewHbox.setAlignment(Pos.CENTER_LEFT);
    		tasksListViewDeleteBtn.setOnAction(e -> {
    			getListView().getSelectionModel().select(getItem());
    			deleteTask(getListView().getSelectionModel().getSelectedIndex());
    			getListView().getItems().remove(getItem());
    		});
    		c.setOnAction(e ->{
    			c.setDisable(true);
    			getListView().getSelectionModel().select(getItem());
    			completeTask(getListView().getSelectionModel().getSelectedIndex());
    		});
    		
    		
    	}
    	
    	public void updateItem(String name, boolean empty) {
    		super.updateItem(name, empty);
    		setText(null);
    		setGraphic(null);
    		
    		if(name != null && !empty) {
    			tasksListViewTitleLabel.setText(name);
    			
    			if(Task.get(0).equals(name) == true && Task.get(1).equals("0") == false) {
    				priorityLabel.setText(tPriorChoiceBoxToString + "  ");
    			}
    			
    			if(Task.get(0).equals(name) == true && Task.get(2).equals("0000-00-00") == false) {
    				dateLabel.setText("  " + Task.get(2) + "  ");
    			}
    			
    			setGraphic(tasksListViewHbox);
    		}
    		
    	}
    }
    
    public static void databaseIn() {
		List<String> dbTask = new ArrayList<>();
		String[] taskTitles = null;
		Task = new ArrayList<>();
		
		try {
			taskTitles = Database.getReminderID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[] taskPriors = null;
		
		try {
			taskPriors = Database.getReminderPriority();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Date[] taskDates = null;
		
		for(int i = 0; i < taskTitles.length; i++) {
			dbTask = new ArrayList<>();
			if(taskTitles[i] != null) {
				dbTask.add(taskTitles[i]);
				Task.add(taskTitles[i]);
			}
			if(taskPriors[i] != null) {
				dbTask.add(taskPriors[i]);
				Task.add(taskPriors[i]);
			}
			if(taskDates == null) {
				dbTask.add("0000-00-00");
				Task.add("0000-00-00");
			}
			if(taskTitles[i] != null) {
				taskList.add(dbTask);
			}
		}
	}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println(taskList.size());
    	for(int i = 0; i < taskList.size(); i++) {
    		System.out.println(taskList.get(i).get(0));
    		if(taskList.get(i) != null) {
    			if(tasksListView != null) {
    				tasksListView.getItems().add(taskList.get(i).get(0));
    			}
    			startCheck1 = 1;
    		}
    	}
    	
    	if(startCheck1 == 1) {
    		if(tasksListView != null) {
    			tasksListView.setCellFactory(param -> new Cell());
    		}
    	}
    }
    
    @FXML
    void addNewTask(ActionEvent event) {
    	priorChoiceCheck = 0;
    	priorChoiceBoxToInt = 0;
    	int flag = 0;
    	
    	//Task currTask = new Task();
    	Task = new ArrayList<>();
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/resources/fxml/dialogs/addTaskDialog.fxml"));
			DialogPane tAddTaskPane = fxmlLoader.load();
			TasksController tController = fxmlLoader.getController();
			
			tController.tPriorChoiceBox.getItems().addAll(priorityChoiceBox);
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(tAddTaskPane);
			dialog.setTitle("Add New Task");
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			
			if(clickedButton.get() == ButtonType.OK) {
				if(startCheck == 0) {
					tasksListView.setCellFactory(param -> new Cell());
				}
				startCheck = 1;
				
				
				tTitleTextFieldToString = tController.tTitleTextField.getText();
				
				Task.add(tController.tTitleTextField.getText());
				
				if(tController.tPriorChoiceBox.getValue() == "Low Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!";
					priorChoiceBoxToInt = 1;
					Task.add(priorChoiceBoxToInt + "");
				}else if(tController.tPriorChoiceBox.getValue() == "Medium Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!!";
					priorChoiceBoxToInt = 2;
					Task.add(priorChoiceBoxToInt + "");
				}else if(tController.tPriorChoiceBox.getValue() == "High Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!!!";
					priorChoiceBoxToInt = 3;
					Task.add(priorChoiceBoxToInt + "");
				}else {
					priorChoiceCheck = 0;
					Task.add(0 + "");
				}
				
				if(tController.tDateDatePicker.getValue() != null) {
					Task.add(tController.tDateDatePicker.getValue().toString());
				}else {
					Task.add("0000-00-00");
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
				LocalDateTime now = LocalDateTime.now();
				String timeadded = "" + dtf.format(now);
				System.out.println(timeadded);
				Task.add(timeadded);
				Task.add("0000-00-00-00:00");
				
				for(int i = 0; i < taskList.size(); i++) {
					if(Task.get(0).equals(taskList.get(i).get(0))) {
						flag = 1;
					}
				}
				
				if(tTitleTextFieldToString.isBlank() == true || flag == 1) {
					if(flag == 1) {
						MainController.updateUpdatesList("Error adding task: task has a duplicate title.");
					}else {
						MainController.updateUpdatesList("Error adding task: task must have a title.");
					}
				}else {
					taskList.add(Task);
					tasksListView.getItems().add(tController.tTitleTextField.getText());
					TasksModel.addNewTaskToDB(Task);
					try {
						Tomo.taskAdded();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(flag == 0) {
			}
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    }
    
    static void deleteTask(int position) {
    	System.out.println("POSITION: " + position);
    	TasksModel.deleteTaskToDB(taskList.get(position));
    	taskList.remove(position);
    	
    	try {
			Tomo.taskDeleted();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    static void completeTask(int position) {
    	tempTask = new ArrayList<>();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime now = LocalDateTime.now();
		String timecompleted = "" + dtf.format(now);
    	
    	tempTask.add(taskList.get(position).get(0));
    	tempTask.add(taskList.get(position).get(1));
    	tempTask.add(taskList.get(position).get(2));
    	tempTask.add(taskList.get(position).get(3));
    	tempTask.add(timecompleted);
    	
    	taskList.set(position, tempTask);
    	
    	System.out.println(taskList.get(position));
    	
    	
    	//taskList.set(position, Task);
    	try {
			Tomo.taskCompleted(taskList.get(position).get(3).toString(),taskList.get(position).get(4).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
