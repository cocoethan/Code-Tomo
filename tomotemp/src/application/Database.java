package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class Database {
	private static Connection conn;
	private static boolean hasData = false;
	
	public ResultSet displayUsers()throws SQLException, ClassNotFoundException {
		if(conn == null) {
			getConnection();
		}
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("SELECT userName FROM user");
		return res;
	}
	private void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
		String user = "user";
		initialise(user);
	}
	private void initialise(String userName) throws SQLException, ClassNotFoundException {
		if(!hasData) {
			hasData = true;
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if(!res.next()){
				System.out.println("building user table with prepop values");
				Statement state2 = conn.createStatement();
				state2.execute("CREATE TABLE user(userID integer," + "userName varchar(60)," + "PRIMARY KEY(userID));");
				

				PreparedStatement baseId = conn.prepareStatement("INSERT INTO user values(?,?);");
				baseId.setInt(1, 1);
				baseId.setString(2, userName);
				baseId.execute();
				state2.executeUpdate("CREATE TABLE alarm(alarmID varchar(20)," + "repeatDays varchar(9)," 
						+ "alarmTime varchar(8)," + "alarmDate date," + "userID integer," + "PRIMARY KEY(alarmID)," 
						+ "FOREIGN KEY (userID) REFERENCES user(userID));");
				state2.executeUpdate("CREATE TABLE reminder(reminderID varchar(20)," + "repeatDays varchar(9)," 
						+ "reminderTime time(7)," + "reminderDate varchar(10)," + "userID integer," + "priority varchar(10),"+ "PRIMARY KEY(reminderID)," 
						+ "FOREIGN KEY (userID) REFERENCES user(userID));");
				state2.executeUpdate("CREATE TABLE tamo(tamoID varchar(30)," + "userID integer," + "tamoType varchar(60)," +
					"tamoMood varchar(60)," + "tamoHealth integer," +"tamoHunger integer," +"tamoOvrPts integer," +"tamoPts integer,"
						+"PRIMARY KEY(userID)," + "FOREIGN KEY (userID) REFERENCES user(userID));");
				Database.generateTamo("georgie", "cat", "neutral");
			}
		
		
		}
		
	}
	
	
	public static void placeAlarm(String aID, String days, String time, Date date) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO alarm values(?,?,?,?,?);");
		baseId.setString(1, aID);
		baseId.setString(2, days);
		baseId.setString(3, time);
		baseId.setDate(4, date);
		baseId.setInt(5, 1);
		baseId.execute();
	}
	
    public static void placeReminder(String rID, String days, Time time, String date, String priority) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO reminder values(?,?,?,?,?,?);");
		baseId.setString(1, rID);
		baseId.setString(2, days);
		baseId.setTime(3, time);
		baseId.setString(4, date);
		baseId.setInt(5, 1);
		baseId.setString(6, priority);
		baseId.executeLargeUpdate();
	}
	public static void removeReminder(String rID) throws SQLException {
		Statement state = conn.createStatement();
		state.execute("DELETE FROM reminder WHERE reminderID='" + rID + "';");
		
	}
	public static void removeAlarm(String time) throws SQLException {
		Statement state = conn.createStatement();
		state.execute("DELETE FROM alarm WHERE reminderID='" + time + "';");
		
	}
	public static void generateTamo(String tID, String type, String mood) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO tamo values(?,?,?,?,?,?,?,?);");
		baseId.setString(1, tID);
		baseId.setInt(2, 1);
		baseId.setString(3, type);
		baseId.setString(4, "happy");
		baseId.setInt(5, 100);
		baseId.setInt(6, 1);
		baseId.setInt(7, 0);
		baseId.setInt(8, 0);
		baseId.executeUpdate();
	}
	public static void editTamo(int i, String input) throws SQLException {
		PreparedStatement p = null;
		ResultSet rs = null;
		int currVal;
		String sql;
		switch (i) {
		case 1:
			sql = "select tamoPts from tamo where userID = 1;";
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			currVal = rs.getInt("tamoPts");
			
			currVal = currVal + Integer.parseInt(input);
			sql = "update tamo SET tamoPts = "+ currVal +" where userID = 1;";
			p = conn.prepareStatement(sql);
			p.executeUpdate();
		case 2:
			sql = "select tamoHealth from tamo where userID = 1;";
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			currVal = rs.getInt("tamoHealth");
			currVal = currVal + Integer.parseInt(input);
			if(currVal > 100) {
				currVal = 100;
			}
			if(currVal < 100) {
				currVal = 0;
			}
			sql = "update tamo SET tamoHealth = "+ currVal +" where userID = 1;";
			p = conn.prepareStatement(sql);
			p.executeUpdate();
		case 3:
			sql = "select tamoMood from tamo where userID = 1;";
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			sql = "update tamo SET tamoMood = "+ input +" where userID = 1;";
			p = conn.prepareStatement(sql);
			p.executeUpdate();
		case 4:
			sql = "select tamoOvrPts from tamo where userID = 1;";
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			sql = "update tamo SET tamoOvrPts = "+ input +" where userID = 1;";
			p = conn.prepareStatement(sql);
			p.executeUpdate();
		case 5:
			sql = "select tamoHunger from tamo where userID = 1;";
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			currVal = rs.getInt("tamoHunger");
			currVal = currVal + Integer.parseInt(input);
			sql = "update tamo SET tamoHunger = "+ currVal +" where userID = 1;";
			p = conn.prepareStatement(sql);
			p.executeUpdate();
		}
		
	}
	
	public static String[] getAlarmID() throws SQLException {
		PreparedStatement p = null;
		ResultSet rs = null;
		String[] id = new String[300];
		int i = 0;
		String sql = "select * from alarm;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String aID = rs.getString("alarmID");
			 id[i] = aID;
			 i++;
			 String days= rs.getString("repeatDays"); 
			 Time time = rs.getTime("alarmTime");
			 Date date = rs.getDate("alarmDate");
		 }
		return id;
		}
	public static String[] getAlarmTime() throws SQLException {
		PreparedStatement p = null;
		ResultSet rs = null;
		String[] t = new String[300];
		int i = 0;
		String sql = "select * from alarm;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String time = rs.getString("alarmTime");
			 t[i] = time;
			 i++;

		 }
		 return t;
	}
	public static String retrieveTamoValues(int i) throws SQLException {
		
		PreparedStatement p = null;
		ResultSet rs = null;
		String sql = "select * from tamo;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		String tID;
		int health;
		int pts;
		int hunger;
		String mood;
		String affection;
		 while (rs.next()) {
			 tID = rs.getString("tamoID");
			 health= rs.getInt("tamoHealth"); 
			 pts = rs.getInt("tamoPts");
			 mood = rs.getString("tamoMood");
			 affection = rs.getString("tamoOvrPts");
			 hunger = rs.getInt("tamoHunger");
			 switch (i){
			 case 1:
				 return tID;
			 case 2:
				 return String.valueOf(health);
			 case 3:
				 return String.valueOf(pts);
			 case 4:
				 return mood;
			 case 5:
				 return affection;
			 case 6:
				 return String.valueOf(hunger);
			 }
			}
		 System.out.println("error in tamo data retrieval");
		return null;
	}
	public static void getReminder() throws SQLException {
		PreparedStatement p = null;
		ResultSet rs = null;
		String sql = "select * from reminder;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String rID = rs.getString("reminderID");
			 String date= rs.getString("reminderDate"); 
			 String priority = rs.getString("priority");
			// System.out.println("rID:" + rID + " date: " + " priority: " + priority);
		 }
		}
	public static String[] getReminderID() throws SQLException {
		String[] rIDArr = new String[300];
		int i = 0;
		PreparedStatement p = null;
		ResultSet rs = null;
		String sql = "select * from reminder;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String rID = rs.getString("reminderID");
			 rIDArr[i] = rID;
			 i++;
		 }
		 return rIDArr;
	}
	
	public static String[] getReminderDate() throws SQLException {
		String[] rDate = new String[300];
		int i = 0;
		PreparedStatement p = null;
		ResultSet rs = null;
		String sql = "select * from reminder;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String date= rs.getString("reminderDate");
			 rDate[i] = date;
			 i++;
		 }
		 return rDate;
	}

	public static String[] getReminderPriority() throws SQLException {
		String rPrio[] = new String[300];
		int i = 0;
		PreparedStatement p = null;
		ResultSet rs = null;
		String sql = "select * from reminder;";
		p = conn.prepareStatement(sql);
		rs = p.executeQuery();
		 while (rs.next()) {
			 String priority = rs.getString("priority");
			 rPrio[i] = priority;
			 i++;
		 }
		 return rPrio;
	}
	public static void connClose() throws SQLException {
		conn.close();
	}
}
