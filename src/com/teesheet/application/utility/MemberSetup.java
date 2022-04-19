package com.teesheet.application.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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



public class MemberSetup {

    /**
     * Executes SQL commands to create loginInfo table in
     * database. Intended for single use during initial
     * installation and setup. Creates admin user credentials
     * using the parameters.
     *  @param adminUsername username for admin initial user
     * @param adminPassword password for admin initial user
     */
	
	public static void insertUser(String name, String phone) throws SQLException {
		DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();
        int count = 0;
        String countQuery = "select count(*) from classproject.memberInfo";
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            count = rs.getInt(1);
            
        }
    	catch(Exception e) {
    		
    	}

        PreparedStatement post = cn.prepareStatement("insert into classproject.memberInfo(memberID, memberName, phoneNumber)values(?,?,?)");
        post.setString(1,String.valueOf(count+1));
        post.setString(2,name);
        post.setString(3,phone);
    
        try {
           post.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    public static void createMemberTable(String adminUsername, String adminPassword){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();
        String createMemberTable = "CREATE TABLE `classproject`.`memberinfo` (\n" + "memberName varchar(30) not null,\n" +
                "phoneNumber varchar(30) not null,\n" +
                "primary key(memberName)\n" + ");";
        String insertAdminUserCredentials = "INSERT INTO memberinfo(memberName,phoneNumber) VALUES ('"
                                            + adminUsername + "','" + adminPassword + "','admin','');";
        try {
            Statement statement = cn.createStatement();
            statement.execute(createMemberTable);
            statement.execute(insertAdminUserCredentials);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes SQL commands to create teeSheet table in
     * database. Intended for single use during initial
     * installation and setup.
     */
    public static void createMemberTable(){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();
        String createTeeSheetTable = "CREATE TABLE `classproject`.`memberinfo` (\r\n"
        		+ "	memberID varchar(10) not null,\r\n"
        		+ "	memberName varchar(30) not null,\r\n"
        		+ "	phoneNumber varchar(10) not null,\r\n"
        		+ "	primary key(memberID)\r\n"
        		+ ");";
        try {
            Statement statement = cn.createStatement();
            statement.executeUpdate(createTeeSheetTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
