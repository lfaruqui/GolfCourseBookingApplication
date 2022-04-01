package com.teesheet.testing;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author Jayden Craft
 * 
 * Mar 26, 2022
 */
public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxmlTests/MultiplePageDisplay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        stage.setResizable(true);
        stage.setTitle("Tee Sheet");
        stage.setMinHeight(550);
        stage.setMinWidth(900);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
