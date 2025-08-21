package com.example.mysqlconnector1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BooksDB {
    public static ResultSet myStatementView;

    public static void toConnect (){
        try{
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
            // Establish connection, hostname: local host, database name: bookinventory
            Statement myStatementAdd = myConnection.createStatement();
            Statement myStatementView = myConnection.createStatement();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
