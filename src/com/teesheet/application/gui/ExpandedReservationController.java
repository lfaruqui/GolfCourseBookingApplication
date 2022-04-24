package com.teesheet.application.gui;

import java.util.ArrayList;

import com.teesheet.application.utility.DataEntry;
import com.teesheet.application.utility.JSON.JSONObject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

/**
 * @author Jayden Craft Apr 3, 2022
 */
public class ExpandedReservationController {

	@FXML
	private Button submit;

	@FXML
	private TextField member;

	@FXML
	private TextField player1;

	@FXML
	private TextField player2;

	@FXML
	private TextField player3;

	@FXML
	private TextField player4;

	@FXML
	private ComboBox<String> carts;

	@FXML
	private TextField caddie;

	@FXML
	private CheckBox memberPresent;

	@FXML
	private CheckBox driving;

	@FXML
	private TextArea notes;

	@FXML
	private Label creator;

	private JSONObject teeTime;

	private TitledPane titledParent;

	private String date;

	TeeTimePaneController tpc;

	public void submitReservation(ActionEvent e) {

		JSONObject info = teeTime.optJSONObject("info");

//		System.out.println(info);

		if (!info.optString("member_name").equals(member.getText())
				|| !info.optBoolean("mem_present") == memberPresent.isSelected()
				|| !info.optString("p1_cart").equals(carts.getValue())
				|| !info.optString("player1_name").equals(player1.getText())
				|| !info.optString("player2_name").equals(player2.getText())
				|| !info.optString("player3_name").equals(player3.getText())
				|| !info.optString("player4_name").equals(player4.getText())
				|| !info.optString("caddie").equals(caddie.getText())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Reservation Change");
			alert.setHeaderText("Change Reservation Confirmation");
			alert.setContentText("Selecting \"OK\" will change the current reservation scheduled for this timeslot.");

			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) {
				createNewReservation();
			}
		}

	}

	private void createNewReservation() {

//		System.out.println("CONFIRM CHANGE");

		JSONObject info = teeTime.optJSONObject("info");

		info.put("member_name", member.getText());
		info.put("mem_present", memberPresent.isSelected());
		info.put("player1_name", player1.getText());
		info.put("player2_name", player2.getText());
		info.put("player3_name", player3.getText());
		info.put("player4_name", player4.getText());
		info.put("caddie", caddie.getText());

		if (!(carts.getValue() == null)) {
			info.put("mem_cart", carts.getValue());
			info.put("p1_cart", carts.getValue());
			info.put("p2_cart", carts.getValue());
			info.put("p3_cart", carts.getValue());
			info.put("p4_cart", carts.getValue());
		}else {
			info.put("mem_cart", "nu");
			info.put("p1_cart", "nu");
			info.put("p2_cart", "nu");
			info.put("p3_cart", "nu");
			info.put("p4_cart", "nu");
		}

		teeTime.put("info", info);

		DataEntry.updateTeeTime(date, teeTime);

		tpc.loadTeeTime(teeTime);

	}

	public void setTeeTime(JSONObject json) {
		teeTime = json;

		JSONObject info = teeTime.optJSONObject("info");

		member.setText(info.optString("member_name"));

		player1.setText(info.optString("player1_name"));

		player2.setText(info.optString("player2_name"));
		player3.setText(info.optString("player3_name"));
		player4.setText(info.optString("player4_name"));
		caddie.setText(info.optString("caddie"));

		memberPresent.setSelected(info.optBoolean("mem_present"));

		carts.getSelectionModel().select(carts.getItems().indexOf(info.optString("p1_cart")));

		creator.setText("Teetime scheduled by: " + info.optString("creator"));

	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setParentController(TeeTimePaneController tpc) {
		this.tpc = tpc;
	}

	public void initialize() {

		/*
		 * the parent structure of the button is as follows: button -> AnchorPane ->
		 * VBox -> AnchorPane -> TitledPaneSkin -> TitledPane so We need to get 5
		 * parents of the button to access the time.
		 */

//		titledParent = (TitledPane) submit.getParent().getParent().getParent().getParent().getParent();
//		System.out.println(submit.getParent().getParent().getParent().getParent());
		ArrayList<String> cartID = new ArrayList<String>();

		for (int i = 1; i < 10; i++) {
			cartID.add("C" + i);
		}

		carts.setItems(FXCollections.observableArrayList(cartID));

	}

}
