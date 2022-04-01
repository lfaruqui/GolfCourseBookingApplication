package com.teesheet.application.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
 * @author Jayden Craft
 * Mar 31, 2022
 */
public class DashboardController {
	
	@FXML
	private GridPane dates;

	@FXML
	private Accordion hours;

	private int chosenColumn = 0;

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
		String date = dateDay[1];
		System.out.println(dateDay[1]);

		LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yy"));
		LocalTime time = LocalTime.of(7, 0);
		
		LocalDateTime dt = LocalDateTime.of(d, time);

		for (TitledPane t : hours.getPanes()) {

			// Get the label in the title of the TitlePane
			Label label = (Label) t.getGraphic();

			label.setText(dt.format(DateTimeFormatter.ofPattern("hh:mm a")));
			dt = dt.plusMinutes(15);
		}

	}

	/**
	 * This method is called when the page loads and is where we will set the labels
	 * based on the current date
	 */
	public void initialize() {
		DateAndTime dt = new DateAndTime();
		ArrayList<LocalDateTime> text = dt.getRangeOfDates(LocalDateTime.now(), 14);
		int index = 0;

		for (Node n : dates.getChildren()) {
			AnchorPane child = (AnchorPane) n;
			Label label = (Label) child.getChildren().get(0);
			label.setText(text.get(index).getDayOfWeek() + "\n"
					+ text.get(index).format(DateTimeFormatter.ofPattern("M/dd/yy")));
			index++;
		}

	}


}
