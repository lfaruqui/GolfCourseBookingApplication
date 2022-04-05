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
import javafx.scene.control.skin.TitledPaneSkin;
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
	
	public JSONObject getTeeSheet() {
		return teesheet;
	}
	
	public Accordion getHours() {
		return hours;
	}

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

	public void loadTeeSheet(TitledPane t, int index) {

		// Get the label in the title of the TitlePane
		Label label = (Label) t.getGraphic();

		JSONArray teeTimes = teesheet.optJSONArray("tee_Times");
//			System.out.println(hours.getChildrenUnmodifiable().indexOf(t));
		JSONObject singleTeeTime = teeTimes.optJSONObject(index);
//			System.out.println(s);
		JSONObject info = singleTeeTime.optJSONObject("info");

		if (info.optBoolean("blocked")) {
			
			t.getChildrenUnmodifiable().get(1).setStyle("-fx-background-color: linear-gradient(from 13px 38px to 52px 52px, reflect,  #ff5c6c, #ff0019 100%);");
			System.out.println(t.getChildrenUnmodifiable());
			t.setDisable(true);

		} else {
			
			t.setDisable(false);
			t.getChildrenUnmodifiable().get(1).setStyle("");

			LocalTime time = LocalTime.parse(singleTeeTime.optString("time"), DateTimeFormatter.ofPattern("HH:mm:ss"));

			String title = time.format(DateTimeFormatter.ofPattern("h:mm a")) + "   ";

			if (!info.optString("member_name").equals("")) {
				title += "Member: " + info.optString("member_name") + "   ";

				if (!info.optString("player1_name").equals("")) {
					title += "Player 1: " + info.optString("player1_name") + "   ";

					if (!info.optString("player2_name").equals("")) {
						title += "Player 2: " + info.optString("player2_name") + "   ";

						if (!info.optString("player3_name").equals("")) {
							title += "Player 3: " + info.optString("player3_name") + "   ";

							if (!info.optString("player4_name").equals("")) {
								title += "Player 4: " + info.optString("player4_name") + "   ";
							}

						}

					}

				}

			}
			
			label.setText(title);
			ExpandedReservationController rc = controllers.get(t);

			rc.setTeeTime(singleTeeTime);

		}

	}

	/**
	 * This method is called when the page loads and is where we will set the labels
	 * based on the current date
	 */
	public void initialize() {
		DateAndTime dt = new DateAndTime();
		ArrayList<LocalDateTime> text = dt.getRangeOfDates(LocalDateTime.now().minusDays(1), 14);
		date = text.get(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		teesheet = TeeSheetUtility.singleDayTeeSheet(date);
		
		System.out.println(teesheet.toCuteString());

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

			/* The skin is not set upon load by default, so we must do it before the page is finished loading
			 * so we can style the titleRegion attribute for blocked dates. This is non existent if we
			 * do not do it like this.
			 */
			TitledPaneSkin ts = new TitledPaneSkin(t);
			t.setSkin(ts);
			
			// We want to access the inner-pane with all of the information fields so we can write to the fields
			// This should be handled by the ExpandedReservationController, not this controller, so we keep trak of it
			controllers.put(t, rc);

			loadTeeSheet(t, index);

			index++;
		}

	}

}
