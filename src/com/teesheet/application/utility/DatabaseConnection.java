package com.teesheet.application.utility;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Creates the connection to the database
 */
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "classproject";
        String databaseUsername = "root";
        String databasePassword = "$Password";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUsername,databasePassword);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return databaseLink;

    }
}