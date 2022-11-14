package application;

import java.sql.Connection;
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
				state2.executeUpdate("CREATE TABLE alarm(alarmID integer," + "repeatDays varchar(9)," 
						+ "alarmTime time(7)," + "userID integer," + "PRIMARY KEY(alarmID)," 
						+ "FOREIGN KEY (userID) REFERENCES user(userID));");
				state2.executeUpdate("CREATE TABLE reminder(reminderID integer," + "repeatDays varchar(9)," 
						+ "reminderTime time(7)," + "userID integer," + "PRIMARY KEY(reminderID)," 
						+ "FOREIGN KEY (userID) REFERENCES user(userID));");
				state2.executeUpdate("CREATE TABLE tamo(tamoID integer," + "userID integer," + "tamoType varchar(60)," +
					"tamoMood varchar(60)," + "PRIMARY KEY(userID)," + "FOREIGN KEY (userID) REFERENCES user(userID));");
			}
		
		
		}
		
	}
	public static void placeAlarm(int aID, String days, Time time) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO alarm values(?,?,?,?);");
		baseId.setInt(1, aID);
		baseId.setString(2, days);
		baseId.setTime(3, time);;
		baseId.setInt(4, 1);
		baseId.execute();
	}
	public static void placeReminder(int rID, String days, Time time) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO alarm values(?,?,?,?);");
		baseId.setInt(1, rID);
		baseId.setString(2, days);
		baseId.setTime(3, time);;
		baseId.setInt(4, 1);
		baseId.execute();
	}
	public static void placeTamo(int tID, String type, String mood) throws SQLException {
		PreparedStatement baseId = conn.prepareStatement("INSERT INTO alarm values(?,?,?,?);");
		baseId.setInt(1, tID);
		baseId.setString(2, type);
		baseId.setString(3, mood);;
		baseId.setInt(4, 1);
		baseId.execute();
	}
}
