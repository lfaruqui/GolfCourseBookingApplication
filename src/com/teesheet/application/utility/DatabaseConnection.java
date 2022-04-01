package com.teesheet.application.utility;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "classproject";
        String databaseUsername = "ysoserious";
        String databasePassword = "jayden0522";
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