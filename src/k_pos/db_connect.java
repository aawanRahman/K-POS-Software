/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Najmul
 */
public class db_connect {
    //init database constrants
    static final String DATABASE_NAME = "k_pos";
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
   
   // private static final String DATABASE_URL = ""+DATABASE_NAME+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
     private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/k_pos?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
   // private static final String DATABASE_URL ="jdbc:mysql://localhost:3306/bookstoredatabase?zeroDateTimeBehavior=convertToNull";
    private static final String DATABASE_USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String MAX_POOL = "250";
   
    //init connection object
    private Connection connection;  
    
    //connect database
    public Connection connect(){
        if(connection == null){
            try{
                System.out.print("\n checking .........connection established\n");
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER_NAME,PASSWORD);
                System.out.println(connection.getCatalog());
            }
            catch(ClassNotFoundException | SQLException e){
                System.out.print(e);
            }
        }
         System.out.print("\n..............1\n");
          System.out.print(connection);
        System.out.print("\nchecked.......connection established\n");
        
        return connection;
    }

    //disconnect database
    public void disconnect(){
        if(connection !=null){
            try{
                connection.close();
                connection = null;
            }
            catch(SQLException e){
            }            
        }
    }
    
    

}
