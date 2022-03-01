module com.example.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;

    opens com.example.loginpage to javafx.fxml;
    exports com.example.loginpage;
}