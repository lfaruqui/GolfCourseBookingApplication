package com.teesheet.application.gui;

import com.teesheet.application.gui.fxml.FxmlLoader;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Jayden Craft
 * Mar 31, 2022
 */
public class NavigationController {
	
	@FXML
	VBox reference;
	
	@FXML
	GridPane navMenu;
	
	@FXML
	Label selected;
	
	public void switchPage(MouseEvent e) {
		for (Node n : navMenu.getChildren()) {
			
			Label l = (Label)n;
			
			if (l.getBoundsInParent().contains(new Point2D(e.getX(), e.getY()))) {
				l.setUnderline(true);
			}else {
				l.setUnderline(false);
			}
			
		}
	}
	
	public void initialize() {
		
//		System.out.println("Clicked");
		HBox parent = (HBox)reference.getParent();
		parent.getChildren().remove(reference);
		
		FxmlLoader loader = new FxmlLoader();
		reference = (VBox) loader.loadPage("Dashboard.fxml");
		
		parent.getChildren().add(reference);
		
		

	}
	

}
