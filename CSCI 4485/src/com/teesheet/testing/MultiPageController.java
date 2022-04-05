package com.teesheet.testing;


import com.teesheet.testing.fxmlTests.FxmlLoaderTest;

import javafx.fxml.FXML;
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
		
		HBox parent = (HBox)reference.getParent();
		parent.getChildren().remove(reference);
		
		FxmlLoaderTest loader = new FxmlLoaderTest("DashBoardTest.fxml");
		reference = (VBox) loader.loadPage();
		
		parent.getChildren().add(reference);
		
		

	}
	
	
	

}
