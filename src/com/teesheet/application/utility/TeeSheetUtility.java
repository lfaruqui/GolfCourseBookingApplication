package com.teesheet.application.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teesheet.application.utility.JSON.JSONObject;


public class TeeSheetUtility {
	
	/**
	 * Returns a JSON object of the teesheet for the indicated day
	 * @param date         must be in the form of YYYY-MM-DD
	 */
	public static JSONObject singleDayTeeSheet(String date) {
		SingleDayTeeSheet ts = loadSingleDayTeeSheet(date);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString;
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ts);
			JSONObject json = new JSONObject(jsonString);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static SingleDayTeeSheet loadSingleDayTeeSheet(String date) {
		SingleDayTeeSheet ts = new SingleDayTeeSheet(date);
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();

		try {
			Statement statement = cn.createStatement();

			String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE _date_ = \"" + date + "\"";
			ResultSet queryResult = statement.executeQuery(selectTeeSheet);

			while (queryResult.next()) {
				ts.addTeeTime(getSingleTeeTime(queryResult));
			}

			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Leave expandedTime parameter as empty string to load tee sheet unexpanded
	 * 
	 * @param date         must be in the form of YYYY-MM-DD
	 * @param expandedTime must be in the form HH:MM
	 */
	public static SingleTeeTime loadSingleTeeTime(String date, String time) {
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();

		try {
			Statement statement = cn.createStatement();

			String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE (_date_, Clock) = (\"" + date + "\", "
					+ "\"" + time + "\")";
			ResultSet queryResult = statement.executeQuery(selectTeeSheet);
			queryResult.next();
			return getSingleTeeTime(queryResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static SingleTeeTime getSingleTeeTime(ResultSet rs) {

		try {
			String date = rs.getString("_date_");
			String time = rs.getString("Clock");
			boolean blocked = rs.getBoolean("blocked");
			String memberName = rs.getString("Member_Name");
			String player1Name = rs.getString("Player1_Name");
			String player2Name = rs.getString("Player2_Name");
			String player3Name = rs.getString("Player3_Name");
			String player4Name = rs.getString("Player4_Name");
			String caddie = rs.getString("Caddie");
			String memberCart = rs.getString("Mem_Cart");
			boolean memberPres = rs.getBoolean("Mem_Present");
			String p1Cart = rs.getString("P1_Cart");
//			System.out.println(p1Cart);
			String p1Ref = rs.getString("P1_reference");
			String p2Cart = rs.getString("P2_Cart");
			String p2Ref = rs.getString("P2_reference");
			String p3Cart = rs.getString("P3_Cart");
			String p3Ref = rs.getString("P3_reference");
			String p4Cart = rs.getString("P4_Cart");
			String p4Ref = rs.getString("P4_reference");
			String creator = rs.getString("Creator");
			SingleTeeTime t = new SingleTeeTime(time, blocked, memberName, memberPres, memberCart, player1Name, p1Cart,
					p1Ref, player2Name, p2Cart, p2Ref, player3Name, p3Cart, p3Ref, player4Name, p4Cart, p4Ref, caddie,
					creator);
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static int getRoundsCount(String date) {
		int count = 0;
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();

		try {
			Statement statement = cn.createStatement();

			String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE _date_ =\"" + date + "\"";
			ResultSet queryResult = statement.executeQuery(selectTeeSheet);
			while(queryResult.next()) {
				if(queryResult.getObject("Member_Name")!= null && queryResult.getObject("Mem_present").toString() == "1") {
					count++;
				}
				if(queryResult.getObject("Player1_name")!= null) {
					count++;
				}
				if(queryResult.getObject("Player2_name")!= null) {
					count++;
				}
				if(queryResult.getObject("Player3_name")!= null) {
					count++;
				}
				if(queryResult.getObject("Player4_name")!= null) {
					count++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public static int getWalkingCount(String date) {
		int count = 0;
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();
		try {
			Statement statement = cn.createStatement();
			String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE _date_ =\"" + date + "\"";
			ResultSet queryResult = statement.executeQuery(selectTeeSheet);
			while(queryResult.next()) {
				if(queryResult.getObject("Member_Name")!= null && queryResult.getObject("Mem_present").toString() == "1" && 
						queryResult.getObject("Mem_Cart").toString().toUpperCase() == "W") {
					count++;
				}
				if(queryResult.getObject("Player1_Cart").toString().toUpperCase() == "W") {
					count++;
				}
				if(queryResult.getObject("Player2_name").toString().toUpperCase() == "W") {
					count++;
				}
				if(queryResult.getObject("Player3_name").toString().toUpperCase() == "W") {
					count++;
				}
				if(queryResult.getObject("Player4_name").toString().toUpperCase() == "W") {
					count++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public static int getCartCount(String date) {
		return getRoundsCount(date) - getWalkingCount(date);
	}
	
}
