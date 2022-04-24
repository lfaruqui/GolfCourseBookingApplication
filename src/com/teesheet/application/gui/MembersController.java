package com.teesheet.application.gui;

import java.sql.SQLException;

import com.teesheet.application.utility.Member;
import com.teesheet.application.utility.MemberList;
import com.teesheet.application.utility.MemberSetup;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MembersController {

	@FXML
	TableView<Member> members;
	
	@FXML
	TableColumn<Member, String> id;
	
	@FXML
	TableColumn<Member, String> name;
	
	@FXML
	TableColumn<Member, String> phone;
	
	@FXML
	Button addMember;
	
	@FXML
	Button deleteMember;
	
	@FXML
	TextField nameField;
	
	@FXML
	TextField phoneField;
	
	public void deleteMember(ActionEvent e) throws SQLException {
		
		Member selected = members.getSelectionModel().getSelectedItem();
		
		if (selected != null) {
			MemberSetup.removeMember(selected.getMemberID());
			loadMembers();
		}
		
	}
	
	public void addMember(ActionEvent e) {
		
		try {
			MemberSetup.insertUser(nameField.getText(), phoneField.getText());
			nameField.clear();
			phoneField.clear();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		loadMembers();
		
	}

	public void initialize() {
		loadMembers();
	}

	private void loadMembers() {

//		AnchorPane parent = (AnchorPane) members.getParent();

		MemberList mems = MemberSetup.loadMemberList();
		
		id.setCellValueFactory(new PropertyValueFactory<Member, String>("memberID"));
		name.setEditable(true);
		name.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		phone.setEditable(true);
		phone.setCellValueFactory(new PropertyValueFactory<Member, String>("phone"));
		
		members.setItems(FXCollections.observableArrayList(mems.getMembers()));
		
		members.setEditable(true);
	}

}
