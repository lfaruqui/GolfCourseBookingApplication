package com.teesheet.application.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import com.teesheet.application.utility.DatabaseConnection;

public class AddUserController {

    @FXML
    private Button addNewUserButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField adminPasswordPasswordField;
    @FXML
    private TextField adminUsernameTextField;
    @FXML
    private TextField emailTextField;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginPage(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public boolean validateLogin(){
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
                    errorMessageLabel.setText("User added!");
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


    public void addUser(ActionEvent event) throws SQLException {
        if(usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank() ||
            emailTextField.getText().isBlank() || nameTextField.getText().isBlank() ||
            adminUsernameTextField.getText().isBlank() || adminPasswordPasswordField.getText().isBlank()){
            errorMessageLabel.setText("Fill in all text fields!");
        }
        else if(validateLogin()){
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB =  connectNow.getConnection();

            PreparedStatement post = connectDB.prepareStatement("insert into classproject.logininfo(user_username," +
                    "user_password,user_name,user_email)values(?,?,?,?)");
            post.setString(1,usernameTextField.getText());
            post.setString(2,passwordPasswordField.getText());
            post.setString(3,nameTextField.getText());
            post.setString(4,emailTextField.getText());


            try{
                post.executeUpdate();
                errorMessageLabel.setText("User added!");
                usernameTextField.setText("");
                passwordPasswordField.setText("");
                nameTextField.setText("");
                emailTextField.setText("");

            }
            catch(Exception e){
                errorMessageLabel.setText("User not added!");
            }

        }
    }
}