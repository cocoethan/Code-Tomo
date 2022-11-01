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

public class MainController implements Initializable{

    @FXML
    private Button homeTabBtn;

    @FXML
    private BorderPane homeTabPane;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane mainPane;

    @FXML
    private VBox tabsVBox;

    @FXML
    private Button tasksTabBtn;

    @FXML
    private BorderPane tasksTabPane;

    @FXML
    private ImageView tomoImgView;

    @FXML
    private StackPane viewStackPane;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	homeTabPane.toFront();
	}
    
    @FXML
    void switchToHomeTab(ActionEvent e) {
    	homeTabPane.toFront();
    }

    @FXML
    void switchToTasksTab(ActionEvent e) {
    		tasksTabPane.toFront();
    }
    
    
}
