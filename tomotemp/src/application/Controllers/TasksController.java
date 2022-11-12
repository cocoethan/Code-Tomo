package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Controllers.TasksController;
import application.Controllers.TasksController.Cell;
import application.models.TasksModel;
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

/**
 * @author Ethan Coco
 */
public class TasksController{

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
    static int priorChoiceCheck = 0;
    
    private String[] priorityChoiceBox = {"Low Priority", "Medium Priority", "High Priority"};
    
    private String tTitleTextFieldToString;
    static String tPriorChoiceBoxToString;
    private int priorChoiceBoxToInt = 0;
    
    public class Task {
    	static String name;
    	static int priority;
    	static String date;
    	
    	public Task() {
    		name = this.name;
    		priority = this.priority;
    		date = this.date;
    	}
    	
    	public void setName(String namePassed) {
    		this.name = namePassed;
    	}
    	
    	public static String getName() {
    		return name;
    	}
    	
    	public void setPriority(int priorityPassed) {
    		this.priority = priorityPassed;
    	}
    	
    	public static int getPriority() {
    		return priority;
    	}
    	
    	public void setDate(String datePassed) {
    		this.date = datePassed;
    	}
    	
    	public static String getDate() {
    		return date;
    	}
    }
    
    
    static class Cell extends ListCell<String>{
    	CheckBox c = new CheckBox();
    	Label spaceLabel = new Label("  ");
    	
    	Label priorityLabel = new Label("");
    	
    	HBox tasksListViewHbox = new HBox();
    	Pane tasksListViewPane = new Pane();
    	Label tasksListViewTitleLabel = new Label("");
    	Button tasksListViewEditBtn = new Button("Edit");
    	Button tasksListViewDeleteBtn = new Button("Delete");
    	
    	public Cell() {
    		super();
    		
    		tasksListViewTitleLabel.setFont(new Font("Arial",15));
    		tasksListViewHbox.getChildren().addAll(c, spaceLabel, priorityLabel, tasksListViewTitleLabel, tasksListViewPane, tasksListViewEditBtn, tasksListViewDeleteBtn);
    		tasksListViewHbox.setHgrow(tasksListViewPane, Priority.ALWAYS);
    		tasksListViewHbox.setAlignment(Pos.CENTER_LEFT);
    		tasksListViewDeleteBtn.setOnAction(e -> {
    			getListView().getItems().remove(getItem());
    			deleteTask();
    		});
    		
    	}
    	
    	public void updateItem(String name, boolean empty) {
    		super.updateItem(name, empty);
    		setText(null);
    		setGraphic(null);
    		
    		if(name != null && !empty) {
    			tasksListViewTitleLabel.setText(name);
    			
    			if(Task.getName().equals(name) == true && Task.getPriority() != 0) {
    				priorityLabel.setText(tPriorChoiceBoxToString + "  ");
    			}
    			
    			setGraphic(tasksListViewHbox);
    		}
    		
    	}
    }
    
    @FXML
    void addNewTask(ActionEvent event) {
    	priorChoiceCheck = 0;
    	priorChoiceBoxToInt = 0;
    	
    	Task currTask = new Task();
    	
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
				
				currTask.setName(tController.tTitleTextField.getText());
				
				if(tController.tPriorChoiceBox.getValue() == "Low Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!";
					priorChoiceBoxToInt = 1;
					currTask.setPriority(priorChoiceBoxToInt);
				}else if(tController.tPriorChoiceBox.getValue() == "Medium Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!!";
					priorChoiceBoxToInt = 2;
					currTask.setPriority(priorChoiceBoxToInt);
				}else if(tController.tPriorChoiceBox.getValue() == "High Priority") {
					priorChoiceCheck = 1;
					tPriorChoiceBoxToString = "!!!";
					priorChoiceBoxToInt = 3;
					currTask.setPriority(priorChoiceBoxToInt);
				}else {
					priorChoiceCheck = 0;
					currTask.setPriority(0);
				}
				
				
				//!!! FOR LANE DEESE
				//Start Add Task to Database from controller
				TasksModel.addNewTaskToDB(tTitleTextFieldToString, priorChoiceBoxToInt);
				//End AddTask to Database from controller
				
				tasksListView.getItems().add(tController.tTitleTextField.getText());
			}
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
    
    static void deleteTask() {
    	
    }
    
}