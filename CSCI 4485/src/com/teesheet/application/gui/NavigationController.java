package com.teesheet.application.gui;

import com.teesheet.application.gui.fxml.FxmlLoader;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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
		
		FxmlLoader loader = new FxmlLoader("Dashboard.fxml");
		reference = (VBox) loader.loadPage();
		
//		DashboardController dc = loader.getLoader().getController();
//		
//		Accordion ac = dc.getHours();
		
		parent.getChildren().add(reference);
		
		/*
		 * We need the titled panes to be loaded before we can access the
		 * TitleSkin attribute to do the initial load of blocked times. 
		 * i.e. We are trying to style something that isn't there if we perform this
		 * in DashboardController.initialize()
		 */
//		int index = 0;
//		for (TitledPane t : ac.getPanes()) {
//			dc.loadTeeSheet(t, index);
//		}

	}
	

}
