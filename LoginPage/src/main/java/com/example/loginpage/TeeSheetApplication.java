package com.example.loginpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeeSheetApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TeeSheetApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520 , 400);
        stage.setResizable(false);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}