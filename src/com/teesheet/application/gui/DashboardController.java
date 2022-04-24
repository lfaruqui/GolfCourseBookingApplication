package com.teesheet.application.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import com.teesheet.application.gui.fxml.FxmlLoader;
import com.teesheet.application.utility.TeeSheetUtility;
import com.teesheet.application.utility.JSON.JSONArray;
import com.teesheet.application.utility.JSON.JSONObject;
import com.teesheet.testing.date.DateAndTime;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * @author Jayden Craft Mar 31, 2022
 */
public class DashboardController {

	@FXML
	private GridPane dates;

	@FXML
	private Accordion hours;

	private int chosenColumn = 0;

	private String date = "";

	private JSONObject teesheet;

	public JSONObject getTeeSheet() {
		return teesheet;
	}

	public Accordion getHours() {
		return hours;
	}

	private HashMap<TitledPane, TeeTimePaneController> controllers;

	public void switchDay(MouseEvent e) {

		double x = e.getX();
		double y = e.getY();

		for (Node n : dates.getChildren()) {
			if (n.getBoundsInParent().contains(new Point2D(x, y))) {

				GridPane.setMargin(dates.getChildren().get(chosenColumn), new Insets(0, 0, 0, 0));
				chosenColumn = GridPane.getColumnIndex(n);

				break;
			}
		}

		AnchorPane selected = (AnchorPane) dates.getChildren().get(chosenColumn);
		GridPane.setMargin(selected, new Insets(10, 0, 0, 0));

		// Get the label of the selected day from the top of the screen
		Label day = (Label) selected.getChildren().get(0);

		// Get the date String from the selected tab
		String[] dateDay = day.getText().split("\n");
//		System.out.println(dateDay[1]);

		LocalDate d = LocalDate.parse(dateDay[1], DateTimeFormatter.ofPattern("M/dd/yy"));

		this.date = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		teesheet = TeeSheetUtility.singleDayTeeSheet(date);

		int index = 0;

		for (TitledPane t : hours.getPanes()) {
			loadTeeSheet(t, index);
			
			controllers.get(t).setTeeTimeDate(this.date);

			index++;
		}

//		System.out.println(teesheet);

	}

	public void loadTeeSheet(TitledPane t, int index) {
		JSONArray teeTimes = teesheet.optJSONArray("tee_Times");
//			System.out.println(hours.getChildrenUnmodifiable().indexOf(t));
		JSONObject singleTeeTime = teeTimes.optJSONObject(index);
//			System.out.println(s);
		controllers.get(t).loadTeeTime(singleTeeTime);
		
	}

	/**
	 * This method is called when the page loads and is where we will set the labels
	 * based on the current date
	 */
	public void initialize() {
		DateAndTime dt = new DateAndTime();
		ArrayList<LocalDateTime> text = dt.getRangeOfDates(LocalDateTime.now(), 14);
		date = text.get(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		teesheet = TeeSheetUtility.singleDayTeeSheet(date);

		int index = 0;

		for (Node n : dates.getChildren()) {
			AnchorPane child = (AnchorPane) n;
			Label label = (Label) child.getChildren().get(0);
			label.setText(text.get(index).getDayOfWeek() + "\n"
					+ text.get(index).format(DateTimeFormatter.ofPattern("M/dd/yy")));
			index++;
		}

		controllers = new HashMap<TitledPane, TeeTimePaneController>();

		loadHours();
	}

	private void loadHours() {

		AnchorPane parent = (AnchorPane) hours.getParent();

		for (int i = 0; i < teesheet.optJSONArray("tee_Times").length(); i++) {
//			System.out.println(i);
			TitledPane t = createTitledPane();
			hours.getPanes().add(t);

			parent.setPrefHeight(parent.getPrefHeight() + 60);

			loadTeeSheet(t, i);
		}

		parent.setPrefHeight(parent.getPrefHeight() + 275);
	}

	private TitledPane createTitledPane() {
		FxmlLoader loader = new FxmlLoader("TitledPane.fxml");

		AnchorPane n = (AnchorPane) loader.loadPage();
		
//		System.out.println(n);

		TitledPane t = (TitledPane) n.getChildren().get(0);
		
		TeeTimePaneController tc = loader.getLoader().getController();

		// We want to access the inner-pane with all of the information fields so we can
		// write to the fields
		// This should be handled by the ExpandedReservationController, not this
		// controller, so we keep trak of it
		controllers.put(t, tc);
//		System.out.println(controllers);
		
		tc.setTeeTimeDate(date);

		return t;
	}
}
