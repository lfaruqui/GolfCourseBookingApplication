package com.teesheet.application.gui;

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
	
	
	
	private TitledPane titledParent;
	
	public void submitReservation(ActionEvent e) {
		
		System.out.println("Member: " + member.getText());
		System.out.println("Player 1: " + player1.getText());
		System.out.println("Player 2: " + player2.getText());
		System.out.println("Player 3: " + player3.getText());
		System.out.println("Player 4: " + player4.getText());

		
		
		titledParent = (TitledPane) submit.getParent().getParent().getParent().getParent().getParent();
		System.out.println(titledParent);
	}
	
	public void initialize() {
		
		/* the parent structure of the button is as follows:
		 * button -> AnchorPane -> VBox -> AnchorPane -> TitledPaneSkin -> TitledPane
		 * so We need to get 5 parents of the button to access the time.
		 */
		
//		titledParent = (TitledPane) submit.getParent().getParent().getParent().getParent().getParent();
		System.out.println(submit.getParent().getParent().getParent().getParent());
		
	}

}
