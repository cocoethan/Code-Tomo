package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Controllers.TasksController;
import application.Controllers.TasksController.Cell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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
    
    static class Cell extends ListCell<String>{
    	HBox tasksListViewHbox = new HBox();
    	Pane tasksListViewPane = new Pane();
    	Label tasksListViewTitleLabel = new Label("");
    	Button tasksListViewEditBtn = new Button("Edit");
    	Button tasksListViewDeleteBtn = new Button("Delete");
    	
    	public Cell() {
    		super();
    		
    		tasksListViewHbox.getChildren().addAll(tasksListViewTitleLabel, tasksListViewPane, tasksListViewEditBtn, tasksListViewDeleteBtn);
    		tasksListViewHbox.setHgrow(tasksListViewPane, Priority.ALWAYS);
    	
    		tasksListViewDeleteBtn.setOnAction(e -> getListView().getItems().remove(getItem()));
    	}
    	
    	public void updateItem(String name, boolean empty) {
    		super.updateItem(name, empty);
    		setText(null);
    		setGraphic(null);
    		
    		if(name != null && !empty) {
    			tasksListViewTitleLabel.setText(name);
    			setGraphic(tasksListViewHbox);
    		}
    		
    	}
    }
    
    @FXML
    void addNewTask(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/resources/fxml/dialogs/addTaskDialog.fxml"));
			DialogPane tAddTaskPane = fxmlLoader.load();
			TasksController tController = fxmlLoader.getController();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(tAddTaskPane);
			dialog.setTitle("Add New Task");
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			
			if(clickedButton.get() == ButtonType.OK) {
				if(startCheck == 0) {
					tasksListView.setCellFactory(param -> new Cell());
				}
				startCheck = 1;
				tasksListView.getItems().add(tController.tTitleTextField.getText());
			}
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
    
}