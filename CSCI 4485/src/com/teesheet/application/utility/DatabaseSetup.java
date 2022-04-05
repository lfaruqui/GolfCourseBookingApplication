package com.teesheet.application.utility;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseSetup {

	/**
	 * Executes SQL commands to create loginInfo table in database. Intended for
	 * single use during initial installation and setup. Creates admin user
	 * credentials using the parameters.
	 * 
	 * @param adminUsername username for admin initial user
	 * @param adminPassword password for admin initial user
	 */
	public static void createLoginInfoTable(String adminUsername, String adminPassword) {
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();
		String createTeeSheetTable = "CREATE TABLE `classproject`.`logininfo` (\n"
				+ "user_username varchar(30) not null,\n" + "user_password varchar(30) not null,\n"
				+ "user_name varchar(30) not null,\n" + "user_email varchar(30) not null,\n"
				+ "primary key(user_username)\n" + ");";
		String insertAdminUserCredentials = "INSERT INTO logininfo(user_username,user_password,user_name,user_email) VALUES ('"
				+ adminUsername + "','" + adminPassword + "','admin','');";
		try {
			Statement statement = cn.createStatement();
			statement.execute(createTeeSheetTable);
			statement.execute(insertAdminUserCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes SQL commands to create teeSheet table in database. Intended for
	 * single use during initial installation and setup.
	 */
	public static void createTeeSheetTable() {
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();
		String createTeeSheetTable = "create table teeSheet\n" + "(_date_ \tDATE NOT NULL,\n"
				+ "Clock \t\tTIME NOT NULL,\n" + "blocked\t\tboolean DEFAULT FALSE,\n" + "Member_Name \tvarchar(30),\n"
				+ "Mem_Present\tboolean,\n" + "Mem_Cart \tvarchar(2),\n" + "Player1_Name\tvarchar(30),\n"
				+ "P1_Cart \tvarchar(2),\n" + "P1_reference \tvarchar(20),\n" + "Player2_Name \tvarchar(30),\n"
				+ "P2_Cart \tvarchar(2),\n" + "P2_reference \tvarchar(20),\n" + "Player3_Name \tvarchar(30),\n"
				+ "P3_Cart \tvarchar(2),\n" + "P3_reference \tvarchar(20),\n" + "Player4_Name \tvarchar(30),\n"
				+ "P4_Cart \tvarchar(2),\n" + "P4_reference \tvarchar(20),\n" + "Caddie  \tvarchar(20),\n"
				+ "Creator  \tvarchar(10),\n" + "CONSTRAINT PRIMARY KEY (_date_,Clock)\n" + ");";
		try {
			Statement statement = cn.createStatement();
			statement.executeUpdate(createTeeSheetTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes SQL commands to create golfCourses table in database. Intended for
	 * single use during initial installation and setup.
	 */
	public static void createGolfCoursesTable() {
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();
		String createGolfCourseTable = "create table golfCourses\n" + "(name varChar(75),\n" + "location varChar(25),"
				+ "\n" + "state char(2),\n" + "contact varChar(16)\n" + ");";
		try {
			Statement statement = cn.createStatement();
			statement.executeUpdate(createGolfCourseTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Intended for single use during initial setup of database given number of days
	 * into the future that blank rows should be generated using the
	 * createBlankTeeSheet method.
	 * 
	 * @param numOfDaysIntoFuture number of days wanted in database
	 */
	public static void generateBlanks(int numOfDaysIntoFuture) {
		for (int i = 0; i <= numOfDaysIntoFuture; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, i);
			Date date = cal.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = format1.format(date);
			createBlankTeeSheet(date1);
		}
	}

	/**
	 * Will create a blank tee sheet with tee times from 7:30 am to 4:50 pm by
	 * adding rows to the database table 'teeSheet'
	 *
	 * @param date must be in the form of YYYY-MM-DD
	 */
	public static void createBlankTeeSheet(String date) {
		DatabaseConnection connectDB = new DatabaseConnection();
		Connection cn = connectDB.getConnection();
		try {
			// i is hour, j is minute (tens place)
			for (int i = 7; i < 17; i++) {
				for (int j = 0; j < 6; j++) {
					if (i == 7 && j == 0) {
						j = 3;
					}
					Statement statement = cn.createStatement();
					String time = i + ":" + j + "0";
					System.out.println(time);
					String insertTime = "INSERT INTO classproject.teeSheet VALUES " + "('" + date + "','" + time + "',"
							+ "FALSE,NULL,NUll,Null,Null,Null,Null,NULL,NUll,Null,Null,Null,Null,NULL,NUll,Null,Null,Null);";
					statement.execute(insertTime);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

//    public static void main(String[] args) {
//    	DatabaseSetup.createLoginInfoTable("username", "password");
//        DatabaseSetup.createGolfCoursesTable();
//        DatabaseSetup.createTeeSheetTable();
//        DatabaseSetup.generateBlanks(30);
//	}

}