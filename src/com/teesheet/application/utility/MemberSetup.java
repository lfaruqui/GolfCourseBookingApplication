package com.teesheet.application.utility;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teesheet.application.utility.DatabaseConnection;
import com.teesheet.application.utility.JSON.JSONObject;



public class MemberSetup {

    /**
     * Executes SQL commands to create loginInfo table in
     * database. Intended for single use during initial
     * installation and setup. Creates admin user credentials
     * using the parameters.
     *  @param adminUsername username for admin initial user
     * @param adminPassword password for admin initial user
     */
	
	public static void removeUser(String id) throws SQLException{
		DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();
        
		PreparedStatement post = cn.prepareStatement("DELETE from classproject.memberinfo WHERE " +
                "memberID = (?)");
        post.setString(1,id);
        try{
            post.executeUpdate();
           
        }
        catch(Exception e){
        }
		
		
	}
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
    
    /**
     * Takes information from result set and creates a Member object 
     * using the information given. Used in loadMemberList method.
     * @param rs
     * @return
     */
    public static Member getMemberInfo(ResultSet rs) {
    	Member m = null;
    	try {
    		String memberID = rs.getString("member_ID");
    		String name = rs.getString("name");
    		String phone = rs.getString("phone");
    		m = new Member(memberID, name, phone);
    		return m;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return m;
    }

    
    /**
     *Populates MemberList using loadMemberList method for latest
     *info from database
     *
     * @return  JSONObject of MemberList
     */
    public static JSONObject getMemberList() {
    	MemberList list = loadMemberList();
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString;
    	try {
    		jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    		JSONObject json = new JSONObject(jsonString);
    		return json;
    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * Uses method getMemberInfo to add each Member to MemberList
     * 
     * @return MemberList object populated with info from DB
     */
    public static MemberList loadMemberList() {
    	MemberList list = new MemberList();
    	DatabaseConnection connectDB = new DatabaseConnection();
    	Connection cn = connectDB.getConnection();
    	try {
    		Statement statement = cn.createStatement();
    		String selectEntireMemberList = "SELECT * FROM classproject.memberInfo";
    		ResultSet queryResult = statement.executeQuery(selectEntireMemberList);

    		while (queryResult.next()) {
    			list.addMember(getMemberInfo(queryResult));
    		}
  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
}
