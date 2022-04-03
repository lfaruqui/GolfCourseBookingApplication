package com.example.loginpage;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemberSetup {

    /**
     * Executes SQL commands to create loginInfo table in
     * database. Intended for single use during initial
     * installation and setup. Creates admin user credentials
     * using the parameters.
     *  @param adminUsername username for admin initial user
     * @param adminPassword password for admin initial user
     */
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
        String createTeeSheetTable = "create table memberInfo\n" +
                "(memberName, \n"+
                "phoneNumber,\n" +
                ");"  ;
        try {
            Statement statement = cn.createStatement();
            statement.executeUpdate(createTeeSheetTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
