package com.teesheet.application.gui;

import com.teesheet.application.gui.fxml.FxmlLoader;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Jayden Craft Mar 31, 2022
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

			Label l = (Label) n;

			if (l.getBoundsInParent().contains(new Point2D(e.getX(), e.getY()))) {
				l.setUnderline(true);
				if (!selected.equals(l)) {
					selected = l;
					loadPage(l.getText());
				}

			} else {
				l.setUnderline(false);
			}

		}
	}

	private void loadPage(String page) {
		
		HBox parent = (HBox) reference.getParent();
//		System.out.println(parent);
//		System.out.println(parent.getChildren());
		parent.getChildren().remove(reference);

		FxmlLoader loader = null;

		switch (page) {

		case "Dashboard": loader = new FxmlLoader("Dashboard.fxml");
		break;
		case "Blocking": loader = new FxmlLoader("Blocking.fxml");
		break;
		}
		
//		System.out.println(loader);
		reference = (VBox) loader.loadPage();

		parent.getChildren().add(reference);

	}

	public void initialize() {

		selected = (Label) navMenu.getChildren().get(0);

//		System.out.println(selected);

		HBox parent = (HBox) reference.getParent();
		parent.getChildren().remove(reference);

		FxmlLoader loader = new FxmlLoader("Dashboard.fxml");
		reference = (VBox) loader.loadPage();

		parent.getChildren().add(reference);
//		System.out.println(reference.getParent());

	}

}
