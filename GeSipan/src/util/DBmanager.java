package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBmanager {
	  public static Connection getConnection(){
	        Connection conn = null;
	        try{
	            Class.forName("oracle.jdbc.OracleDriver");
	            String url = "jdbc:oracle:thin:@localhost:1521:xe";
	            conn = DriverManager.getConnection(url,"system","oracle");
	        }catch(Exception ex){
	            ex.printStackTrace();
	            System.out.println("DBmanager 에게서 에러가 났음.");
	        }
	        return conn;
	        
	    }
}
