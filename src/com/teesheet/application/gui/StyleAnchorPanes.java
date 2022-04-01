package com.teesheet.application.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class StyleAnchorPanes {

	@FXML
	GridPane dates;
	
	public void style() {
		for (Node c : dates.getChildren()) {
			c.setStyle("-fx-background-color: #ffffff");
		}
	}
	
}
