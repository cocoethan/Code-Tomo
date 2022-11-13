package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private BorderPane homeTabPane;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane mainPane;

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
    private StackPane viewStackPane;//Do not use
    
    @FXML
    private StackPane imgStackPane;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	tomoStandingEyesOpenImg.toFront();//use .toFront() to switch images/gifs
	}
    
    public static void main(String[] args) {
    	//Do Stuff Here
    }
    
}
