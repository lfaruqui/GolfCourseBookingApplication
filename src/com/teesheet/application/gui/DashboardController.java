package com.teesheet.application.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.layout.VBox;

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

	private HashMap<TitledPane, ExpandedReservationController> controllers;

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

			index++;
		}

//		System.out.println(teesheet);

	}

	private void loadTeeSheet(TitledPane t, int index) {

		// Get the label in the title of the TitlePane
		Label label = (Label) t.getGraphic();

		JSONArray teeTimes = teesheet.optJSONArray("tee_Times");
//			System.out.println(hours.getChildrenUnmodifiable().indexOf(t));
		JSONObject singleTeeTime = teeTimes.optJSONObject(index);
//			System.out.println(s);
		JSONObject info = singleTeeTime.optJSONObject("info");
		LocalTime time = LocalTime.parse(singleTeeTime.optString("time"), DateTimeFormatter.ofPattern("HH:mm:ss"));
		label.setText(time.format(DateTimeFormatter.ofPattern("h:mm a")) + "  Member: " + info.optString("member_name"));

		ExpandedReservationController rc = controllers.get(t);

		rc.setTeeTime(singleTeeTime);

//			label.setText( + "");

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

		index = 0;
		LocalTime time = LocalTime.of(7, 0);

		controllers = new HashMap<TitledPane, ExpandedReservationController>();

		for (TitledPane t : hours.getPanes()) {

			// Get the label in the title of the TitlePane
			Label label = (Label) t.getGraphic();

			label.setText(time.format(DateTimeFormatter.ofPattern("hh:mm a")));
			time = time.plusMinutes(10);

			FxmlLoader loader = new FxmlLoader("ExpandedReservationPane.fxml");
			t.setContent(loader.loadPage());

			ExpandedReservationController rc = loader.getLoader().getController();

			controllers.put(t, rc);

			loadTeeSheet(t, index);

			index++;
		}

	}

}
