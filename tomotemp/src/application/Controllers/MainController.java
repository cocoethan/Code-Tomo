package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Ethan Coco
 */
public class MainController implements Initializable{

	@FXML
    private Label currPointsValue;

    @FXML
    private Label healthValue;

    @FXML
    private BorderPane homeTabPane;

    @FXML
    private StackPane imgStackPane;

    @FXML
    private Label levelValue;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane mainPane;

    @FXML
    private Label moodValue;

    @FXML
    private Label nameValue;

    @FXML
    private Label ovrPointsValue;

    @FXML
    private VBox tabsVBox;

    @FXML
    private BorderPane tasksTabPane;

    @FXML
    private ImageView tomoLayDownGIF;

    @FXML
    private ImageView tomoLickGIF;

    @FXML
    private ImageView tomoSitDownGIF;

    @FXML
    private ImageView tomoSleepingGIF;

    @FXML
    private ImageView tomoStandingEyesClosedImg;

    @FXML
    private ImageView tomoStandingEyesOpenImg;

    @FXML
    private ImageView tomoStandingImg;

    @FXML
    private ListView<String> updatesList;

    @FXML
    private StackPane viewStackPane;
    
    static ObservableList<String> updateItems;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	tomoSleepingGIF.toFront();//use .toFront() to switch images/gifs
    	updateItems =FXCollections.observableArrayList ("Program started.");
    	updatesList.setItems(updateItems);
    	start();
	}
    
    //Do anything
    public static void start() {
    	
    }
    
    //Used to update list when outside function happens, for example: task added, task removed
    public static void updateUpdatesList(String message) {
    	updateItems.add(message + " +5");
    }
    
    //Used to calculate points etc. when task is added
    public static void taskAdded() {
    	
    }
    
    //Used to calculate points etc. when task is removed
    public static void taskRemoved() {
    	
    }
    
    //Used to calculate points etc. when task is completed
    public static void taskCompleted() {
    	
    }
}
