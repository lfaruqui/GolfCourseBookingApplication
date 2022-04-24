package com.teesheet.application.gui;

import com.teesheet.application.utility.DataEntry;
import com.teesheet.application.utility.JSON.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Jayden Craft
 * Apr 12, 2022
 */
public class EmptyTeeTimeTitleController {
	
	@FXML
	private Button reserve;
	
	@FXML
	private TextField member;
	
	@FXML
	private TextField player1;
	
	@FXML
	private TextField player2;
	
	private String date;
	
	private JSONObject teeTime;
	
	private TeeTimePaneController tpc;
	
	
	public void makeReservation(ActionEvent e) {
		
		
		JSONObject info = teeTime.optJSONObject("info");

		info.put("member_name", member.getText());
		info.put("player1_name", player1.getText());
		info.put("player2_name", player2.getText());
		info.put("mem_cart", "nu");
		info.put("p1_cart", "nu");
		info.put("p2_cart", "nu");
		info.put("p3_cart", "nu");
		info.put("p4_cart", "nu");
		
		
		teeTime.put("info", info);
		
		DataEntry.updateTeeTime(date, teeTime);
		
		tpc.loadTeeTime(teeTime);
		
	}
	
	public void setParentController(TeeTimePaneController tpc) {
		this.tpc = tpc;
	}
	
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTeeTime(JSONObject json) {
		teeTime = json;
	}

}
