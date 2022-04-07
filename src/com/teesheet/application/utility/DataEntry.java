package com.teesheet.application.utility;

import java.sql.Connection;
import java.sql.Statement;


public class DataEntry {

    /**
     * Will update existing tee time with given date and time parameters
     * This method functions as an add for new tee times as well as edit
     * and remove where the object TeeTime parameter can be new TeeTime(date,time)
     * to replace the existing time with a blank row or new information.
     * @param date is the date of the tee time to be manipulated
     * @param SingleTeeTime is the Data Container that holds all information for the booking
     */
    public static void updateTeeTime(String date, SingleTeeTime st) {
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn = connectDB.getConnection();
        String updateTeeTime = "UPDATE teeSheet SET " + st.getInfo().toString() + " AND _date=\"" + date +"\"";
        try {
            Statement statement = cn.createStatement();
            statement.executeUpdate(updateTeeTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Accepts two dates and iterates through the rows in the database that
     * correspond with those dates to switch the boolean value for blocked
     * to the opposite
     * @param beginDate is the start date of the time period
     * @param endDate is the last date to be blocked
     */
    public static void block(String beginDate, String endDate){
    	 DatabaseConnection connectDB = new DatabaseConnection();
         Connection cn = connectDB.getConnection();
         String selectDates = "UPDATE classproject.teesheet SET blocked= NOT blocked WHERE _date_ >= \"" + beginDate + "\" AND _date_<=\"" + endDate +"\"";
         try {
             Statement statement = cn.createStatement();
             statement.executeUpdate(selectDates);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    /**
     * Accepts one date and iterates through the rows in the database that
     * correspond with that date to switch the boolean value for blocked
     * to the opposite
     * @param date to be blocked entirely
     */
    public static void block(String date){
          
    	 DatabaseConnection connectDB = new DatabaseConnection();
         Connection cn = connectDB.getConnection();
         String selectDates = "UPDATE classproject.teesheet SET blocked= NOT blocked WHERE _date_ = \"" + date + "\"";
         try {
             Statement statement = cn.createStatement();
             statement.executeUpdate(selectDates);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    /**
     * Accepts one date and iterates through the rows in the database that
     * correspond to the time window to switch the boolean value for blocked
     * to the opposite
     * @param date is the date of the time period
     * @param startTime is the start time of the block or unblock
     * @param endTime is the last time to be blocked or unblocked
     */
    public static void block(String date, String startTime, String endTime){
    	DatabaseConnection connectDB = new DatabaseConnection();
    	Connection cn = connectDB.getConnection();
    	String selectDates = "UPDATE classproject.teesheet SET blocked= NOT blocked WHERE _date_ = \"" + date + "\" AND clock>= \"" 
    							+ startTime + "\" AND clock<= \"" + endTime + "\"";
    	try {
         Statement statement = cn.createStatement();
         statement.executeUpdate(selectDates);
    	} catch (Exception e) {
         e.printStackTrace();
    	}
    }

}
