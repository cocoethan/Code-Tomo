package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Controllers.AlarmsController;
import application.Controllers.AlarmsController.Cell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class AlarmsController implements Initializable{

	static List<String> Alarm = new ArrayList<>();
    static List<List<String>> alarmList = new ArrayList<>();
	
    @FXML
    private Button addAlarmBtn;

    @FXML
    private ListView<String> alarmsListView;
    
    @FXML
    private DialogPane addAlarmPane;
    
    @FXML
    private Spinner<String> aps = new Spinner();;

    @FXML
    private Spinner<Integer> hrs = new Spinner();;

    @FXML
    private Spinner<Integer> mns = new Spinner();

    @FXML
    private TextField textfield = new TextField();
    
    static int counter = 1;
    
    ObservableList<String> list = FXCollections.observableArrayList("AM","PM");
    
    Integer currHr;
    
    Integer currMn;
    
    String currAmPm;
    
    static String timeS;
    
    static String titleS;
    
    static String timeC;
    
    static String currentTime;
    
    static boolean check;
    
    static Timer timer = new Timer();
    
    static List<String> alarmTimer = new ArrayList<>();
    static List<List<String>> timerlist = new ArrayList<>();
    
    static List<String> activeAlarms = new ArrayList<>();
    
    static int flag = 0;
    
    static int timercount = 0;
    
    private int startCheck = 0;
    
    //int threadcount = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	SpinnerValueFactory<Integer> valueFacHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(01, 12);
    	valueFacHrs.setValue(01);
    	SpinnerValueFactory<Integer> valueFacMins = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59);
    	valueFacMins.setValue(00);
    	SpinnerValueFactory<String> valueFacAmPm = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
    	valueFacAmPm.setValue("AM");
    	
    	hrs.setValueFactory(valueFacHrs);
    	mns.setValueFactory(valueFacMins);
    	aps.setValueFactory(valueFacAmPm);
    	
    	currHr = hrs.getValue();
    	currMn = mns.getValue();
    	currAmPm = aps.getValue();
    }
    
    static class Cell extends ListCell<String>{
    
    	HBox hbox = new HBox();
    	Pane pane = new Pane();
    	
    	RadioButton toggle = new RadioButton();
    	//CheckBox toggle = new CheckBox();
    	Label time = new Label("");
    	Label title = new Label("");
    	Label spaceLbl = new Label(" ");
    	Label spaceLbl2 = new Label("  ");
    	Button deleteBtn = new Button("Delete");
    	
    	public Cell() {
    		super();
    		
    		title.setFont(new Font("Arial",15));
    		
    		hbox.getChildren().addAll(toggle,spaceLbl,time,spaceLbl2,title,pane,deleteBtn);
    		hbox.setHgrow(pane, Priority.ALWAYS);
    		hbox.setAlignment(Pos.CENTER_LEFT);
    		
    		deleteBtn.setOnAction(e -> {
    			getListView().getSelectionModel().select(getItem());
    			deleteAlarm(getListView().getSelectionModel().getSelectedIndex());
    			getListView().getItems().remove(getItem());
    		});
    		
    		toggle.setOnAction(e -> {
    			getListView().getSelectionModel().select(getItem());
    			if(toggle.isSelected() == true) {
    				activeAlarm(getListView().getSelectionModel().getSelectedIndex());
    				System.out.println("test");
    			}else if(toggle.isSelected() == false){
    				//remove alarm
    				//getListView().getSelectionModel().select(getItem());
    				deactiveAlarm(getListView().getSelectionModel().getSelectedIndex());
    			}
    		});
    		/*
    		toggle.setOnAction(e -> {
    			getListView().getSelectionModel().select(getItem());
    			activeAlarm(getListView().getSelectionModel().getSelectedIndex());
    		});*/
    	}
    	
    	public void updateItem(String name, boolean empty) {
    		super.updateItem(name, empty);
    		setText(null);
    		setGraphic(null);
    		
    		if(name != null && !empty) {
    			
    			//getListView().getSelectionModel().select(getItem());
    			//time.setText(alarmList.get(getListView().getSelectionModel().getSelectedIndex()).get(0));
    			for(int i = 0; i < alarmList.size(); i++) {
    				if(alarmList.get(i).get(1).equals(name) == true) {
    					time.setText(alarmList.get(i).get(0));
    				}
    			}
    			//time.setText(Alarm.get(0));
    			title.setText(name);
    			
    			setGraphic(hbox);
    			
    		}
    	}
    }
    
    
    @FXML
    void addAlarm(ActionEvent event) {
    	System.out.println("Success");
    	
    	Alarm = new ArrayList<>();
    	
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/resources/fxml/dialogs/addAlarmDialog.fxml"));
			DialogPane addAlarmPane = fxmlLoader.load();
			AlarmsController controller = fxmlLoader.getController();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(addAlarmPane);
			dialog.setTitle("Add New Alarm");
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			
			if(clickedButton.get() == ButtonType.OK) {
				if(startCheck == 0) {
					alarmsListView.setCellFactory(param -> new Cell());
				}
				startCheck = 1;
				timeS = "" + controller.hrs.getValue() + ":";
				
				if(controller.aps.getValue().equals("PM") == true) {
					Integer temp;
					temp = controller.hrs.getValue() + 12;
					
					timeC = "" + temp;
					
				}else {
					if(controller.hrs.getValue() < 10) {
						timeC = "0" + controller.hrs.getValue();
					}else {
						timeC = "" + controller.hrs.getValue();
					}
				}
				
				if(controller.mns.getValue() < 10) {
					timeS = timeS + "0" + controller.mns.getValue() + " " + controller.aps.getValue();
					timeC = timeC + "0" + controller.mns.getValue();
				}else {
					timeS = timeS + "" + controller.mns.getValue() + " " + controller.aps.getValue();
					timeC = timeC + "" + controller.mns.getValue();
				}
				
				
				if(controller.textfield.getText().isEmpty() == false) {
					titleS = "" + controller.textfield.getText();
				}else {
					titleS = "Alarm " + counter;
					counter++;
				}
				
				System.out.println(timeS);
				System.out.println(timeC);
				
				Alarm.add(timeS);
				Alarm.add(titleS);
				Alarm.add(timeC);
				
				alarmList.add(Alarm);
				alarmsListView.getItems().add(titleS);
				
				System.out.println(timeC);
			}
    		//alarmsListView.getItems().add(titleS);
    		
    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    static void deleteAlarm(int position) {
    	alarmList.remove(position);
    }
    
    static void activeAlarm(int position) {
    	System.out.println("POS: " + position);
    	alarmTimer = new ArrayList<>();
    	String activeAlarm = "";
    	String nm = "" + alarmList.get(position).get(1);
    	String tm = "" + alarmList.get(position).get(2);
    	alarmTimer.add(nm);
    	alarmTimer.add(tm);
    	
    	activeAlarm = "" + tm;
    	
    	timerlist.add(alarmTimer);
    	activeAlarms.add(activeAlarm);
    	//sort
    	sortalarms();
    	
    	flag = 1;
    	alarm(activeAlarm);
    }
    
    static void deactiveAlarm(int position) {
    	//System.out.println("++" + position);
    	timerlist.remove(position);
    	
    	//sort again
    	sortalarms();
    	
    	if(position == 0) {
    		flag = 0;
    		timer.cancel();
    		timercount = 0;
    		//alarm(timerlist);
    	}
    }
    
    static void sortalarms() {
    	Collections.sort(activeAlarms);
    }
    
    static void alarm(String alarm) {
    	int flag = 0;
    	
    	
    	TimerTask task = new TimerTask() {
    		@Override
    		public void run() {
    			System.out.println("ALARM");
    			Date timeOn = new Date();
    			//alarmOn();
    			//Call popup
    			//Store what time alarm is completed (timeOff)
    			//Pass both these times to Tomo
    			timer.cancel();
    			Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					alarmOn();
    				}
    			});
    		}
    	};
    	
    	//if(timercount == 0) {
    		//timer = new Timer();
    	//}
    	
    	//if(list.size() > 0) {
    		//for(int i = 0; i < list.size(); i++) {
    			timer = new Timer();
    			Date today = new Date();
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(today);

            	int year = cal.get(Calendar.YEAR);
            	int month = cal.get(Calendar.MONTH);
            	int day = cal.get(Calendar.DAY_OF_MONTH);
            	
            	String hr = alarm.substring(0,2);
            	String mn = alarm.substring(alarm.length() - 2);
            	
            	int h = Integer.parseInt(hr);
            	int m = Integer.parseInt(mn);
            	
            	Date currDate = new GregorianCalendar(year,month,day,h,m).getTime();
            	
            	System.out.println(currDate);
            	
            	timer.schedule(task, currDate);
            	//timer.cancel();
    		//}
    	//}
    }
    
    static void alarmOn() {
    	System.out.println(activeAlarms);
    	if(activeAlarms.size() > 0) {
    		activeAlarms.remove(0);
    	}
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(AlarmsController.class.getResource("/resources/fxml/dialogs/alarmRun.fxml"));
			DialogPane addAlarmPane = fxmlLoader.load();
			AlarmsController controller = fxmlLoader.getController();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(addAlarmPane);
			dialog.setTitle("Add New Alarm");
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			
			if(clickedButton.get() == ButtonType.FINISH) {
					
			}
		}catch(IOException e1) {
			e1.printStackTrace();
		}
    	if(activeAlarms.size() > 0){
    		alarm(activeAlarms.get(0));
    	}
    
    }
    //TODO:
    //Tasks default name need to be task 1
    
}