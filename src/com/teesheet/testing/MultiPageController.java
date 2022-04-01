package com.teesheet.testing;

import java.io.IOException;

import com.teesheet.testing.fxmlTests.FxmlLoader;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Jayden Craft
 *
 * Mar 26, 2022
 */
public class MultiPageController {
	
	@FXML
	VBox reference;
	
	
	
	public void loadPage(MouseEvent event) {
		
		System.out.println("Clicked");
		HBox parent = (HBox)reference.getParent();
		parent.getChildren().remove(reference);
		
		FxmlLoader loader = new FxmlLoader();
		reference = (VBox) loader.loadPage("Test.fxml");
		
		parent.getChildren().add(reference);
		
		

	}
	
	
	

}
