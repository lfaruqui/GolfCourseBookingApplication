package com.teesheet.application.utility;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TeeSheetUtility {

    /**
     * Leave expandedTime parameter as empty string to load tee sheet unexpanded
     * @param date must be in the form of YYYY-MM-DD
     * @param expandedTime must be in the form HH:MM
     */
    public static JsonArray loadTeeSheet(String date, String expandedTime){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection cn =  connectDB.getConnection();
        JsonArray jsonArray = new JsonArray();

        try {
            Statement statement = cn.createStatement();
            String selectTeeSheet = "SELECT * FROM classproject.teeSheet WHERE _date_=\"" + date + "\"";
            ResultSet queryResult = statement.executeQuery(selectTeeSheet);

            int i = 0;
            while (queryResult.next()) {

                String time = queryResult.getString("Clock").substring(0, 5);
                if(Integer.parseInt(time.substring(0,2)) > 12){
                    Integer hour = Integer.parseInt(time.substring(0,2)) - 12;
                    time = hour + time.substring(2,5);
                } if(time.charAt(0) == '0'){
                    time = time.substring(1,5);
                }
                boolean blocked = queryResult.getBoolean("blocked");
                String memberName = queryResult.getString("Member_Name");
                String player1Name = queryResult.getString("Player1_Name");
                String player2Name = queryResult.getString("Player2_Name");
                String player3Name = queryResult.getString("Player3_Name");
                String player4Name = queryResult.getString("Player4_Name");
                String caddie = queryResult.getString("Caddie");

                if(expandedTime.equals(time)){
                    String memberCart = queryResult.getString("Mem_Cart");
                    boolean memberPres = queryResult.getBoolean("Mem_Present");
                    String p1Cart = queryResult.getString("P1_Cart");
                    String p1Ref = queryResult.getString("P1_reference");
                    String p2Cart = queryResult.getString("P2_Cart");
                    String p2Ref = queryResult.getString("P2_reference");
                    String p3Cart = queryResult.getString("P3_Cart");
                    String p3Ref = queryResult.getString("P3_reference");
                    String p4Cart = queryResult.getString("P4_Cart");
                    String p4Ref = queryResult.getString("P4_reference");
                    String creator = queryResult.getString("Creator");
                    TeeTime t = new TeeTime(date,time,blocked,memberName,memberPres,memberCart,player1Name,p1Cart,p1Ref,
                            player2Name,p2Cart,p2Ref,player3Name,p3Cart,p3Ref,player4Name,p4Cart,p4Ref,caddie,creator);
                    JsonString j = new JsonString();
                    j.setValue(t.toJSONString());
                    jsonArray.addValue(j);

                }else{
                    TeeTime t = new TeeTime(date,time,blocked,memberName,player1Name,player2Name,player3Name,player4Name,caddie);
                    JsonString j = new JsonString();
                    j.setValue(t.toJSONString());
                    jsonArray.addValue(j);
                }
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }






}

