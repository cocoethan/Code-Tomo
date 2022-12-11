module tomotemp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application;
	opens application.Controllers to javafx.controls, javafx.graphics, javafx.fxml;
}