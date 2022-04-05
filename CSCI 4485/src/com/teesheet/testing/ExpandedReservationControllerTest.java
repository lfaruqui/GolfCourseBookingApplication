package com.teesheet.testing;

import java.util.ArrayList;

import com.teesheet.application.utility.JSON.JSONObject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

/**
 * @author Jayden Craft
 * Apr 3, 2022
 */
public class ExpandedReservationControllerTest {
	
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
	
	private JSONObject teeTime;
	
	private TitledPane titledParent;
	
	private String date;
	
	public void submitReservation(ActionEvent e) {
		
		System.out.println("Member: " + member.getText());
		System.out.println("Player 1: " + player1.getText());
		System.out.println("Player 2: " + player2.getText());
		System.out.println("Player 3: " + player3.getText());
		System.out.println("Player 4: " + player4.getText());

		
		
		titledParent = (TitledPane) submit.getParent().getParent().getParent().getParent().getParent();
		System.out.println(titledParent);
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
		
		carts.getSelectionModel().select(carts.getItems().indexOf(info.optString("p3_cart")));

		
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void initialize() {
		
		/* the parent structure of the button is as follows:
		 * button -> AnchorPane -> VBox -> AnchorPane -> TitledPaneSkin -> TitledPane
		 * so We need to get 5 parents of the button to access the time.
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
