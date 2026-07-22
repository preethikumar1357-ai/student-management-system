package Internship;

import java.sql.Connection;
import java.sql.DriverManager;

public class Student {
	public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Internship", 
                "root",                               
                "123456789"                       
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}



