module Test {
	requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires mysql.connector.java;
	requires com.fasterxml.jackson.databind;
	requires java.desktop;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.bootstrapfx.core;
//    requires mysql.connector.java;
//	requires puzzle;

    opens com.teesheet.application to javafx.fxml;
    opens com.teesheet.application.gui;
    opens com.teesheet.application.utility;
    opens com.teesheet.testing to javafx.fxml;
    
    exports com.teesheet.application to javafx.graphics;
    exports com.teesheet.testing to javafx.graphics, javafx.fxml;
}