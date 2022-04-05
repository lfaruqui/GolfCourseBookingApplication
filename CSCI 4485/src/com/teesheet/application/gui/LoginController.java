package com.teesheet.application.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.teesheet.application.utility.DatabaseConnection;
import com.teesheet.testing.Main;


public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button addNewUserButton;
    @FXML


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addNewUserPage(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("fxml/AddUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginButtonAction(ActionEvent e){
        errorMessageLabel.setText("Enter your username and password");
        if(usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()){
            errorMessageLabel.setText("Enter your username and password");
        }
        else if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin(e);
        }
    }
    public void validateLogin(ActionEvent e){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB =  connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM classproject.logininfo WHERE user_username = '" +
                usernameTextField.getText() + "' AND user_password = '" + passwordPasswordField.getText()
                +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult  = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
//                    errorMessageLabel.setText("Login Success!");
//                    Parent root =  FXMLLoader.load(getClass().getResource("fxml/AddUser.fxml"));
//                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    scene = new Scene(root);
//                    stage.setScene(scene);
//                    stage.show();
                    
            		FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("fxml/Navigation.fxml"));
                    scene = new Scene(fxmlLoader.load(), 1150, 650);
                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    stage.setResizable(true);
                    stage.setTitle("Tee Sheet");
                    stage.setMinHeight(650);
                    stage.setMinWidth(1150);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    errorMessageLabel.setText("Invalid Username or Password!");
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
