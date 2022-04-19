package com.teesheet.application.gui;


import java.util.ArrayList;

import com.teesheet.application.utility.DatabaseConnection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class RemoveUserController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField adminPasswordPasswordField;
    @FXML
    private TextField adminUsernameTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    public  ListView<String> userListView;



    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() throws SQLException {
        getUsers();
        userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                usernameTextField.setText(userListView.getSelectionModel().getSelectedItem());
            }
        });

    }

    public void loginPage(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public  void getUsers() throws SQLException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB =  connectNow.getConnection();
        String getUsers = "SELECT * FROM classproject.logininfo";
        Statement query = connectDB.createStatement();
        ResultSet result  = query.executeQuery(getUsers);
        ArrayList<String> users = new ArrayList<String>();
        while(result.next()){
            users.add(result.getString("user_username"));
        }
        ObservableList myUsers = FXCollections.observableList(users);
        userListView.setItems(myUsers);
    }

    public boolean validateLogin() throws SQLException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB =  connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM classproject.logininfo WHERE user_username = '" +
                adminUsernameTextField.getText() + "' AND user_password = '" + adminPasswordPasswordField.getText()
                +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult  = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    return true;
                }
                else{
                    errorMessageLabel.setText("Invalid administrator credentials!");
                    return false;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    
    public void removeUser(ActionEvent event) throws SQLException {
        if(usernameTextField.getText().isBlank() ||
                adminUsernameTextField.getText().isBlank() || adminPasswordPasswordField.getText().isBlank()){
            errorMessageLabel.setText("Fill in all text fields!");
        }
        else if(validateLogin()){
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB =  connectNow.getConnection();



            String verifyUserExists = "SELECT count(1) FROM classproject.logininfo WHERE user_username = '" +
                    usernameTextField.getText() + "'";
            Statement statement = connectDB.createStatement();
            ResultSet queryResult  = statement.executeQuery(verifyUserExists);
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    PreparedStatement post = connectDB.prepareStatement("DELETE from classproject.logininfo WHERE " +
                            "user_username = (?)");
                    post.setString(1,usernameTextField.getText());
                    try{
                        post.executeUpdate();
                        errorMessageLabel.setText("Removed!");
                        usernameTextField.setText("");
                        adminUsernameTextField.setText("");
                        adminPasswordPasswordField.setText("");
                        getUsers();

                    }
                    catch(Exception e){
                        errorMessageLabel.setText("User not found!");
                    }
                }
                else{
                    errorMessageLabel.setText("User Not Found!");
                }
            }



        }
    }
}
