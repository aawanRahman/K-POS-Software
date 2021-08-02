/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Najmul
 */
public class get_settings {
    db_connect db = new db_connect();   
   
    //Default Themes Color For All Pages
    Color Content_Color = new Color(214, 217, 223);    
    Color Sidebar_Color = new Color(34, 49, 63); 
    
    //Logged in User Cache Session Saved Dir
    String User_Dir = "c:\\ProgramData\\ud.kp";
    String noorder ="No User !";
    
    //User Role and Theme
    String User_Type = "Admin";
    String Delete_Access = "No";
    String Sell_Access = "No";
    String Account_Report_Access = "No";
    String Input_Access = "No";
    String Expense_Access = "No";
    String Due_Access = "No";    
    String theme ="None";
    
    //Footer Details
    String Company  = "@Copyright Najmul";
    String Version = "1.0";
    
    //Activation
    String Activation_Dir = "c:\\ProgramData\\ac.kp";
    String Activation_Key = "A2X8-52C9-NXW4-L5V1";
    
     get_settings(){
        try{            
            File file = new File (User_Dir);            
            if(file.exists()){                
                BufferedReader br = new BufferedReader(new FileReader(file));
                noorder = br.readLine();   
                try{
                    String Query2 =("SELECT * FROM users WHERE username='"+noorder+"'");
                    PreparedStatement statement2 = db.connect().prepareStatement(Query2);
                    ResultSet rs2 =statement2.executeQuery(Query2);
                    while(rs2.next()) {                            
                            User_Type = rs2.getString("user_type");
                            Account_Report_Access = rs2.getString("Account_Report_Access");
                            Sell_Access = rs2.getString("Sell_Access");
                            Input_Access = rs2.getString("Input_Access"); 
                            Expense_Access = rs2.getString("Expense_Access"); 
                            Due_Access = rs2.getString("Due_Access"); 
                            Delete_Access = rs2.getString("Delete_Access"); 
                    }
                }
                catch(Exception e){
                    System.out.println("Error While Connecting : "+e);
                }
            }                    
        }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }
        
        
        //For Theme
        try{
            String Query =("SELECT * FROM settings");
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            while(rs.next()) {
            if(rs.isLast()) {
               theme = rs.getString("background");
               if(theme.equals("Blue")){ 
                   Content_Color = new Color(105, 124, 155); 
                   Sidebar_Color = new Color(34, 49, 63);
               }
               else if(theme.equals("Red")){
                   Content_Color = new Color(255, 0, 0);
                   Sidebar_Color = new Color(34, 49, 63);
               }
               else if(theme.equals("Pink")){
                   Content_Color = new Color(204, 166, 166);  
                   Sidebar_Color = new Color(34, 49, 63);
               }
               else if(theme.equals("Green")){
                   Content_Color = new Color(124, 252, 0); 
                   Sidebar_Color = new Color(34, 49, 63);
               }
               else if(theme.equals("Yellow")){
                   Content_Color = new Color(255, 255, 51); 
                   Sidebar_Color = new Color(34, 49, 63);
               }
               else if(theme.equals("White")){
                   Content_Color = new Color(240, 240, 240); 
                   Sidebar_Color = new Color(34, 49, 63);                   
               }    
            }
            else{
                theme ="None";
            }
          }
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }
    }  
}
