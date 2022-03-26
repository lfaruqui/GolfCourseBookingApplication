package com.example.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TeeSheetController {
    @FXML
    private Button addTeeTime;
    @FXML
    private Button submitTeeTime;
    @FXML
    private Button viewTeeSheet;

    /**
     * Leave expandedTime parameter as empty string to load tee sheet unexpanded
     * @param date must be in the form of YYYY-MM-DD
     * @param expandedTime must be in the form HH:MM
     */
    public static void loadTeeSheet(String date, String expandedTime){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();

        System.out.println("Tee Sheet for " + date);
        System.out.printf("%-8s", "time");
        System.out.printf("%-15s", "memberName");
        System.out.printf("%-15s", "player1Name");
        System.out.printf("%-15s", "player2Name");
        System.out.printf("%-15s", "player3Name");
        System.out.printf("%-15s", "player4Name");
        System.out.printf("%-15s %n", "caddie");

        try {
            Statement statement = cn.createStatement();
            String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE _date_=\"" + date + "\"";
            ResultSet queryResult = statement.executeQuery(selectTeeSheet);

            while (queryResult.next()) {
                String time = queryResult.getString("Clock").substring(0, 5);
                if(Integer.parseInt(time.substring(0,2)) > 12){
                    Integer hour = Integer.parseInt(time.substring(0,2)) - 12;
                    time = hour + time.substring(2,5);

                } if(time.charAt(0) == '0'){
                    time = time.substring(1,5);
                }
                String memberName = queryResult.getString("Member_Name");
                String player1Name = queryResult.getString("Player1_Name");
                String player2Name = queryResult.getString("Player2_Name");
                String player3Name = queryResult.getString("Player3_Name");
                String player4Name = queryResult.getString("Player4_Name");
                String caddie = queryResult.getString("Caddie");

                //REPLACE PRINTF WITH JSON OBJECTS AS RETURN TYPE
                System.out.printf("%-8s", time);
                System.out.printf("%-15s", memberName);
                System.out.printf("%-15s", player1Name);
                System.out.printf("%-15s", player2Name);
                System.out.printf("%-15s", player3Name);
                System.out.printf("%-15s", player4Name);
                System.out.printf("%-15s %n", caddie);

                if(expandedTime.equals(time)){
                    String memberCart = queryResult.getString("Mem_Cart");
                    String memberPres = queryResult.getString("Mem_Present");
                    String p1Cart = queryResult.getString("P1_Cart");
                    String p1Ref = queryResult.getString("P1_reference");
                    String p2Cart = queryResult.getString("P2_Cart");
                    String p2Ref = queryResult.getString("P2_reference");
                    String p3Cart = queryResult.getString("P3_Cart");
                    String p3Ref = queryResult.getString("P3_reference");
                    String p4Cart = queryResult.getString("P4_Cart");
                    String p4Ref = queryResult.getString("P4_reference");
                    String creator = queryResult.getString("Creator");

                    //REPLACE PRINTF WITH JSON OBJECTS AS RETURN TYPE
                    System.out.printf("%-8s", "");
                    System.out.printf("%-15s", "Mem Present: " + memberPres);
                    System.out.printf("%-15s", "P1 Ref: " + p1Ref);
                    System.out.printf("%-15s", "P2 Ref: " + p2Ref);
                    System.out.printf("%-15s", "P3 Ref: " + p3Ref);
                    System.out.printf("%-15s%n", "P4 Ref: " + p4Ref);

                    System.out.printf("%-8s", "");
                    System.out.printf("%-15s", "Cart Fee: " + memberCart);
                    System.out.printf("%-15s", "Cart Fee: " + p1Cart);
                    System.out.printf("%-15s", "Cart Fee: " + p2Cart);
                    System.out.printf("%-15s", "Cart Fee: " + p3Cart);
                    System.out.printf("%-15s%n", "Cart Fee: " + p4Cart);


                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Will update existing tee time with given date and time parameters
     * This method functions as an add for new tee times as well as edit
     * and remove where the object TeeTime parameter can be new TeeTime(date,time)
     * to replace the existing time with a blank row or new information.
     * @param date is the date of the tee time to be manipulated
     * @param time is the time of the reservation
     * @param info is the Data Container that holds all information for the booking
     */
    public static void addTeeTime(String date, String time, TeeTime info) {
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn = connectDB.getConnection();
        String updateTeeTime = "UPDATE teeSheet SET " + info.toString() ;
        try {
            Statement statement = cn.createStatement();
            statement.executeUpdate(updateTeeTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will create a blank tee sheet with tee times from
     * 7:30 am to 4:50 pm by adding rows to the database table
     * 'teeSheet'
     *
     * @param date must be in the form of YYYY-MM-DD
     */
    public static void createBlankTeeSheet(String date) {
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn = connectDB.getConnection();
        try {
            for (int i = 7; i < 17; i++){
                for (int j = 0; j < 6; j++) {
                    if(i == 7 && j == 0){j = 3;}
                    Statement statement = cn.createStatement();
                    String time = i + ":" + j + "0";
                    String insertTime = "INSERT INTO classproject.teeSheet VALUES " + "('" + date + "','" + time + "'," +
                            "FALSE,NULL,NUll,Null,Null,Null,Null,NULL,NUll,Null,Null,Null,Null,NULL,NUll,Null,Null,Null);";
                    statement.execute(insertTime);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
