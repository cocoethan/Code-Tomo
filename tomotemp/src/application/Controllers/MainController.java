package application.Controllers;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
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
    private ListView<String> updatesList;

    @FXML
    private StackPane viewStackPane;
    
    static ObservableList<String> updateItems;
    
    @FXML
    private ProgressBar happyBar;
    
    static ProgressBar happyBarS;

    @FXML
    private ProgressBar healthBar;
    
    static ProgressBar healthBarS;
    
    @FXML
    private ProgressBar hungerBar;
    
    static ProgressBar hungerBarS;
    
    @FXML
    private Label pointsCounter;
    
    public static Label pointsCounterS;
    
    @FXML
    private ImageView imageView;
    
    static ImageView imageViewS;
    
    static String imgCat = "/resources/images/birth.gif";
    
    Image image = new Image(getClass().getResourceAsStream(imgCat));
    
    static Image imageS;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	imageS = image;
    	imageS = new Image(getClass().getResourceAsStream(imgCat));
    	updateItems = FXCollections.observableArrayList("Program started.");
    	updatesList.setItems(updateItems);
    	
    	happyBarS = happyBar;
    	healthBarS = healthBar;
    	hungerBarS = hungerBar;
    	pointsCounterS = pointsCounter;
    	imageViewS = imageView;
    	
    	imageViewS.setImage(imageS);
    	
    	happyBar.setStyle("-fx-accent: yellow");
    	healthBar.setStyle("-fx-accent: green");
    	hungerBar.setStyle("-fx-accent: orange");
    	
    	start();
	}
    
    //Do anything
    public void start() {
    }
    
    //Used to update list when outside function happens, for example: task added, task removed
    public static void updateUpdatesList(String message) {
    	updateItems.add(message);
    	
    	//updatesList.setItems(sortedList);
    }
    
    public static void updatePointsCounter(String points) {
    	pointsCounterS.setText(points);
    }
    
    
    
    public static void updateHealthBar(double healthDbl) {
    	healthBarS.setProgress(healthDbl);
    }
    
    public static void updateHungerBar(double hungerDbl) {
    	hungerBarS.setProgress(hungerDbl);
    }
    
    public static void updateHappyBar(double happyDbl) {
    	happyBarS.setProgress(happyDbl);
    }
    
    public static void updateImg(String img) {
    	//imgCat = "";
    	imgCat = "/resources/images/" + img;
    	System.out.println(imgCat);
    	imageS = new Image(MainController.class.getResourceAsStream(imgCat));
    	imageViewS.setImage(imageS);
    	//imgCat = "";
    }
    
}
