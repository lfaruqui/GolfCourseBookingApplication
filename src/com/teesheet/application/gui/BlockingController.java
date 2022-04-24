package com.teesheet.application.gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.teesheet.application.utility.DataEntry;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @author Jayden Craft Apr 12, 2022
 */
public class BlockingController {

	@FXML
	Button rangeButton;

	@FXML
	Button singleDateButton;

	@FXML
	Button timesButton;
	
	@FXML
	DatePicker startDate;
	
	@FXML
	DatePicker endDate;
	
	@FXML
	DatePicker singleDate;
	
	@FXML
	DatePicker timeDate;
	
	@FXML
	TextField startTime;
	
	@FXML
	TextField endTime;
	
	@FXML
	ChoiceBox<String> startPM;
	
	@FXML
	ChoiceBox<String> endPM;
	
	

	public void blockRangeOfDates(ActionEvent e) {
		
		String startDate = this.startDate.getValue().toString();
		String endDate = this.endDate.getValue().toString();
		
		DataEntry.block(startDate, endDate);

	}

	public void blockSingleDate(ActionEvent e) {
		
		String singleDate = this.singleDate.getValue().toString();
		
		DataEntry.block(singleDate);

	}

	public void blockRangeOfTimes(ActionEvent e) {
		
		String timeDate = this.timeDate.getValue().toString();
		
		String startTime = this.startTime.getText() + " " + startPM.getValue();
		String endTime = this.endTime.getText() + " " + endPM.getValue();
		
		LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("h:mm a"));
		LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("h:mm a"));
		startTime = start.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		endTime = end.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		DataEntry.block(timeDate, startTime, endTime);

	}
	
	public void initialize() {
		
		// add "AM" and "PM" options to the block times option
		ArrayList<String> amPM = new ArrayList<>();
		amPM.add("AM");
		amPM.add("PM");
		
		startPM.setItems(FXCollections.observableArrayList(amPM));
		endPM.setItems(FXCollections.observableArrayList(amPM));
		
		
	}

}
