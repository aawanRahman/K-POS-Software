/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Najmul
 */

public class status_report extends javax.swing.JFrame {
    db_connect db = new db_connect();
   
    LocalDate localDate = LocalDate.now();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Date = localDate.format(formatter);
    
    LocalDate MonthBegin = localDate.withDayOfMonth(1);
    LocalDate MonthEnd = localDate.withDayOfMonth(localDate.lengthOfMonth());
    
    SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
              
    public status_report() {      
        full_screen();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon();
        theme();
        exit();       
        
        //Today's  
        total_label.setText(" Today's");
        todays_purchase_price();
        todays_selling_price();
        todays_profit();
        todays_expense();
        todays_item_sold();
        todays_customers();
        todays_paid();
        todays_due();
        todays_cash_on_hand();
        
        
        status_report.getTableHeader().setEnabled(false);
        status_report.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
    }  
    
    
     private void seticon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
    } 
     
    void theme(){ 
        get_settings get=new get_settings();   
        String Company = get.Company;
        String Version = get.Version;
        footer.setText(Company+" "+Version);  
        Color content_color = get.Content_Color;
        getContentPane().setBackground(content_color);   
        Color sidebar_color = get.Sidebar_Color;
        sidebar.setBackground(sidebar_color);                
    }  
    
    public void exit(){
        String ExitonClose = "No";
        try{            
            String Query ="SELECT * FROM settings";   
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                   ExitonClose = rs.getString("exit_on_close");                   
                   if(ExitonClose.equals("Yes")){
                        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        this.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                int choose = JOptionPane.showConfirmDialog(null,
                                        "Are You Really Want to Exit this Application ?",
                                        "Confirm Close", JOptionPane.YES_NO_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                                if (choose == JOptionPane.YES_OPTION) {
                                    e.getWindow().dispose();                  
                                } else {

                                }
                            }
                        });
                   }
                   else{
                       //Do Nothing
                   }
            }
            db.disconnect();
        }
        catch(Exception e){
            
        }        
    }
    
    public void full_screen(){
        String Full_Screen ="No";
        try{            
            String Query ="SELECT * FROM settings";   
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                Full_Screen = rs.getString("full_screen");
                if(Full_Screen.equals("Yes")){
                    this.setUndecorated(true); 
                }                   
                else{
                    //Do Nothing
                }
            }
            db.disconnect();
        }
        catch(Exception e){
            
        }
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////  Total  /////////////////////////////////////
    
    
    
    //Total Purchase Price 1.1
    //Sum(Total Purchases Price) Today
    void total_purchase_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Issuer <> 'Refill')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);            
           rs.next();
           String sum = rs.getString(1);
           total_purchases_price.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Purchases Price" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
     
   //total selling price 1.2
   //Sum(Selling Pirce) Total
    void total_selling_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Issuer <> 'Refill'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_selling_price.setText(sum); 
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Selling Price" , sum});            
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    }
   
    
      //Total Profit 1.3
    //(Total Purchases Price - Total Selling Price) (+ commission - withdraw)
     void total_profit(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{               
           String Query = ("SELECT(((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Issuer <> 'Refill')-(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Issuer <> 'Refill'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw)-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           total_profit.setText(sum);   
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Profit" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    
    //Total Paid 1.4
    //sum(Paid) Today = Sold
    void total_paid(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("(select((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Issuer <> 'Refill')-(SELECT IFNULL(SUM(due),0) FROM customers)))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_paid.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Paid" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    //Today's Total Due 1.5
    //sum(Selling Price) - sum(Paid) Today   //Total Selling Price - Total Paid
     void total_due(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(due),0) FROM customers");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);             
           rs.next();
           String sum = rs.getString(1);
           total_due.setText(sum);      
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Due" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
     
    //Total Expense 1.6
     //Sum(Expense) Today
    public double total_expense_ammount(){
       double value=0.0;
        try{
           LocalDate localDate = LocalDate.now();//For reference
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String Date = localDate.format(formatter);                                                
           PreparedStatement statement =  db.connect().prepareStatement("SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Cash'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_expense.setText(sum);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Expenses" , sum}); 

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  } 
    
         
   //Total Item Sold 
    void total_item_sold(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold_report WHERE Issuer <> 'Refill' ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);               
           rs.next();
           String sum = rs.getString(1);
           total_item_sold.setText(sum); 
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Items Sold" , sum});            
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    } 
    
    //Total Customers
    void total_customers(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           total_customers.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Customers" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    } 
    
    
    //Cash on Hand 1.7
     //Due = sum(Selling Price)-sum(Paid)
    //Sum(Selling Price) - (Due + Expense)
    void cash_on_hand(){        
        try{     
           String Query =("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Issuer <> 'Refill')-((SELECT IFNULL(SUM(due),0) FROM customers)+(SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Due'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw)-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))+(select IFNULL(SUM(price),0) from assets WHERE type = 'Cash on Hand')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);          
           rs.next();
           String sum = rs.getString(1);
           cash_on_hand.setText(sum);       
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Cash on Hand" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////  Today's  ///////////////////////////////////
    
    
    //Today's Purchase Price 2.1
    //Sum(Total Purchases Price) Today
    void todays_purchase_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_purchases_price.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Purchases Price" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
     //Sold Total Product Today 2.2
   //Sum(Selling Pirce) Today
    void todays_selling_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);     
           rs.next();
           String sum = rs.getString(1);
           total_selling_price.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Selling Price" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    //Todays Profit 2.3
    //(Total Purchases Price - Total Selling Price)
     void todays_profit(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{               
           String Query =("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill'))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);             
           rs.next();
           String sum = rs.getString(1);
           total_profit.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Profit" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
     
     
     
      //Today's Expense 2.4
    //Sum(Expense) Today
    public double todays_expense(){
       double value=0.0;
        try{
           LocalDate localDate = LocalDate.now();//For reference
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String Date = localDate.format(formatter); 
           PreparedStatement statement =  db.connect().prepareStatement("SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE date = '"+Date+"'  AND type = 'Cash'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_expense.setText(sum);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Expenses" , sum}); 

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }   
    
        
   //Item Sold  2.5
   //Count(id) Today = Sold
    void todays_item_sold(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill' ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);               
           rs.next();
           String sum = rs.getString(1);
           total_item_sold.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Items Sold" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    } 
    
    //Custoemrs | Number of Customer Buy Today 2.6
    //Count(id) Today
    void todays_customers(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold WHERE Date='"+Date+"'  ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           total_customers.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Customers" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whigle Connecting : "+e);
         }    
    } 
          
    
    //Todays Paid 1.4
    //sum(Paid) Today = Sold
    void todays_paid(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("(select((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(due),0) FROM customers WHERE Date='"+Date+"')))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_paid.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Paid" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    //Today's Total Due 1.5
    //sum(Selling Price) - sum(Paid) Today   //Total Selling Price - Total Paid
     void todays_due(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(due),0) FROM customers WHERE Date='"+Date+"'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);             
           rs.next();
           String sum = rs.getString(1);
           total_due.setText(sum);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Due" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    //Cash on Hand 1.7
     //Due = sum(Selling Price)-sum(Paid)
    //Sum(Selling Price) - (Due + Expense)
    void todays_cash_on_hand(){        
        try{     
           String Query =("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date='"+Date+"' AND Issuer <> 'Refill')-((SELECT IFNULL(SUM(due),0) FROM customers WHERE Date='"+Date+"')+(SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Due' AND Date='"+Date+"'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw WHERE Date='"+Date+"')-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))+(select IFNULL(SUM(price),0) from assets WHERE type = 'Cash on Hand' AND Date='"+Date+"')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);          
           rs.next();
           String sum = rs.getString(1);
           cash_on_hand.setText(sum); 
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Cash on Hand" , sum});            
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }  
       
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// Monthly //////////////////////////////////////
    
    
    //Monthly Purchases Price 3.1
    void monthly_purchase_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query ="SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill'";
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_purchases_price.setText(sum);            
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Purchases Price" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    
   //Monthly selling price 3.2
    void monthly_selling_price(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_selling_price.setText(sum); 
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Selling Price" , sum});            
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    }
    
     //Monthly Profit 3.3
     void monthly_profit(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{     
           String Query = "SELECT(((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill')) + (SELECT IFNULL(SUM(commission),0) FROM withdraw WHERE date >= '"+MonthBegin+"' AND date <= '"+MonthEnd+"')-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw WHERE date >= '"+MonthBegin+"' AND date <= '"+MonthEnd+"'))";
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           total_profit.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Profit" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
     
    //Monthly Expense 3.4
    public double monthly_expense(){
       double value=0.0;
        try{
           LocalDate localDate = LocalDate.now();//For reference
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String Date = localDate.format(formatter); 
           PreparedStatement statement =  db.connect().prepareStatement("SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND type = 'Cash'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_expense.setText(sum);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Expenses" , sum}); 

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }   
    
    
    //Item Sold  3.5
    void monthly_item_sold(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill' ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);               
           rs.next();
           String sum = rs.getString(1);
           total_item_sold.setText(sum);  
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Items Sold" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    } 
    
    
    
    //Monthly Custoemrs | Number of Customer Buy This Month 3.6
    void monthly_customers(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold  WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           total_customers.setText(sum);   
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Customers" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    } 
       
    //Monthly Paid 1.4
    //sum(Paid) Today = Sold
    void monthly_paid(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("(select((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"')))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           total_paid.setText(sum);     
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Paid" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    }
    
    
    //Monthly Total Due 1.5
    //sum(Selling Price) - sum(Paid) Today   //Total Selling Price - Total Paid
     void monthly_due(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);             
           rs.next();
           String sum = rs.getString(1);
           total_due.setText(sum);    
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Due" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }    
    }
    
      //Monthly Cash on Hand 1.7
     //Due = sum(Selling Price)-sum(Paid)
    //Sum(Selling Price) - (Due + Expense)
    void monthly_cash_on_hand(){        
        try{     
           String Query =("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"' AND Issuer <> 'Refill')-((SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"')+(SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Due' AND Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw WHERE Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"')-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))+(select IFNULL(SUM(price),0) from assets WHERE type = 'Cash on Hand' AND Date >= '"+MonthBegin+"' AND Date <= '"+MonthEnd+"')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);          
           rs.next();
           String sum = rs.getString(1);
           cash_on_hand.setText(sum); 
            DefaultTableModel model = (DefaultTableModel) status_report.getModel();
            model.addRow(new Object[]{"Cash on Hand", sum});           
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }      
    
       
    
     //Custom Purchases Price 3.1
    void custom_purchase_price(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());            
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query ="SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill'";
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Purchases Price" , sum});              
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
    
    
   //Custom selling price 3.2
    void custom_selling_price(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());            
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Selling Price" , sum});            
         }
         catch(Exception e){
             System.out.println("Error Whigle Connecting : "+e);
         }    
    }
    
     //Custom Profit 3.3
     void custom_profit(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{     
           String Query = "SELECT(((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(Quantity*purchase_price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill')) + (SELECT IFNULL(SUM(commission),0) FROM withdraw WHERE date >= '"+From+"' AND date <= '"+To+"')-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw WHERE date >= '"+From+"' AND date <= '"+To+"'))";
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Profit" , sum});            
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
     
    //Custom Expense 3.4
    public double custom_expense(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        double value=0.0;
        try{
           LocalDate localDate = LocalDate.now();//For reference
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String Date = localDate.format(formatter); 
           PreparedStatement statement =  db.connect().prepareStatement("SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND type = 'Cash'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Expenses" , sum}); 

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }   
    
    
    //Custom Item Sold  3.5
    void custom_item_sold(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill' ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);               
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Items Sold" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    } 
    
    
    
    //Custom Custoemrs | Number of Customer Buy This Month 3.6
    void custom_customers(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT count(id) FROM sold  WHERE Date >= '"+From+"' AND Date <= '"+To+"'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);       
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Customers" , sum});   
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    } 
       
    //Custom Paid 1.4
    //sum(Paid) Today = Sold
    void custom_paid(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("(select((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill')-(SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+From+"' AND Date <= '"+To+"')))");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);              
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Paid" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error Whigle Connecting : "+e);
         }    
    }
    
    
    //Custom Total Due 1.5
    //sum(Selling Price) - sum(Paid) Today   //Total Selling Price - Total Paid
     void custom_due(){
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        try{
           String Query =("SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+From+"' AND Date <= '"+To+"'");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);             
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Due" , sum});   
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }
    
      //Custom Cash on Hand 1.7
     //Due = sum(Selling Price)-sum(Paid)
    //Sum(Selling Price) - (Due + Expense)
    void custom_cash_on_hand(){  
        String From = dcn.format(from.getDate());     
        String To = dcn.format(to.getDate());
        try{     
           String Query =("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Date >= '"+From+"' AND Date <= '"+To+"' AND Issuer <> 'Refill')-((SELECT IFNULL(SUM(due),0) FROM customers WHERE Date >= '"+From+"' AND Date <= '"+To+"')+(SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Due' AND Date >= '"+From+"' AND Date <= '"+To+"'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw WHERE Date >= '"+From+"' AND Date <= '"+To+"')-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))+(select IFNULL(SUM(price),0) from assets WHERE type = 'Cash on Hand' AND Date >= '"+From+"' AND Date <= '"+To+"')");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);          
           rs.next();
           String sum = rs.getString(1);
           DefaultTableModel model = (DefaultTableModel)status_report.getModel();  
           model.addRow(new Object[]{"Cash on Hand" , sum}); 
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }    
    }     

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        total_purchases_price = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        total_selling_price = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        total_profit = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        total_due = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        total_paid = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        total_label = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cash_on_hand = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        total_expense = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        total_item_sold = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        total_customers = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        to = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        status_report = new javax.swing.JTable();
        Main_Menu = new javax.swing.JMenuBar();
        file_menu = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        products_menu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        about_menu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        about_menu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        about_menu3 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        about_menu4 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        about_menu5 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        about_menu = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Status Report");
        setMinimumSize(new java.awt.Dimension(1212, 626));

        sidebar.setBackground(new java.awt.Color(34, 49, 63));

        jButton8.setBackground(new java.awt.Color(34, 49, 63));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Main Menu");
        jButton8.setBorder(null);
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/status_report.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("Footer Text");

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(footer)
                    .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(34, 49, 63));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel5.setEnabled(false);
        jPanel5.setMaximumSize(new java.awt.Dimension(250, 115));
        jPanel5.setMinimumSize(new java.awt.Dimension(250, 115));

        total_purchases_price.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_purchases_price.setForeground(new java.awt.Color(255, 255, 255));
        total_purchases_price.setText("0");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Purchase Price");

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator19.setToolTipText("");

        total_selling_price.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_selling_price.setForeground(new java.awt.Color(255, 255, 255));
        total_selling_price.setText("0");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Profit");

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator20.setToolTipText("");

        total_profit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_profit.setForeground(new java.awt.Color(255, 255, 255));
        total_profit.setText("0");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Selling Price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Due");

        total_due.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_due.setForeground(new java.awt.Color(255, 255, 255));
        total_due.setText("0");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Paid");

        total_paid.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_paid.setForeground(new java.awt.Color(255, 255, 255));
        total_paid.setText("0");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator11.setToolTipText("");

        total_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_label.setForeground(new java.awt.Color(255, 255, 255));
        total_label.setText(" Total");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cash on Hand");

        cash_on_hand.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cash_on_hand.setForeground(new java.awt.Color(255, 255, 255));
        cash_on_hand.setText("0");

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator12.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Expense");

        total_expense.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_expense.setForeground(new java.awt.Color(255, 255, 255));
        total_expense.setText("0");

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setToolTipText("");

        total_item_sold.setBackground(new java.awt.Color(255, 255, 255));
        total_item_sold.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_item_sold.setForeground(new java.awt.Color(255, 255, 255));
        total_item_sold.setText("0");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Item Sold");

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Customers");

        total_customers.setBackground(new java.awt.Color(255, 255, 255));
        total_customers.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_customers.setForeground(new java.awt.Color(255, 255, 255));
        total_customers.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(total_label, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(total_purchases_price))
                .addGap(20, 20, 20)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_selling_price))
                .addGap(20, 20, 20)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(total_profit))
                .addGap(50, 50, 50)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_expense))
                .addGap(50, 50, 50)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_paid))
                .addGap(50, 50, 50)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_due))
                .addGap(50, 50, 50)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cash_on_hand))
                .addGap(20, 20, 20)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(total_item_sold))
                .addGap(50, 50, 50)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total_customers)
                    .addComponent(jLabel12))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator11)
            .addComponent(jSeparator19, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator12)
            .addComponent(jSeparator1)
            .addComponent(jSeparator15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator16)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_selling_price))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cash_on_hand))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_due))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(total_expense)
                            .addComponent(total_profit)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_paid))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_purchases_price))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_item_sold))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_customers))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(total_label)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("To ");

        to.setMinimumSize(new java.awt.Dimension(90, 20));
        to.setPreferredSize(new java.awt.Dimension(90, 20));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Filter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/print.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Today's");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("This Month's");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Total");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        status_report.setAutoCreateRowSorter(true);
        status_report.setBackground(new java.awt.Color(238, 238, 238));
        status_report.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        status_report.setForeground(new java.awt.Color(42, 42, 42));
        status_report.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Price"
            }
        ));
        status_report.setToolTipText("All Products");
        status_report.setAlignmentX(5.0F);
        status_report.setAlignmentY(5.0F);
        status_report.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        status_report.setEditingColumn(0);
        status_report.setEditingRow(0);
        status_report.setFocusable(false);
        status_report.setGridColor(new java.awt.Color(209, 206, 206));
        status_report.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        status_report.setName("All Products"); // NOI18N
        status_report.setOpaque(false);
        status_report.setRequestFocusEnabled(false);
        status_report.setRowHeight(30);
        status_report.setRowMargin(0);
        status_report.setSelectionBackground(new java.awt.Color(186, 186, 236));
        status_report.setSelectionForeground(new java.awt.Color(0, 0, 0));
        status_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                status_reportMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(status_report);

        Main_Menu.setToolTipText("");
        Main_Menu.setAlignmentX(1.0F);
        Main_Menu.setAlignmentY(1.0F);
        Main_Menu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Main_Menu.setMargin(new java.awt.Insets(0, 0, 0, 50));

        file_menu.setText("File");

        jMenuItem22.setText("Exit");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        file_menu.add(jMenuItem22);

        Main_Menu.add(file_menu);

        products_menu.setText("New");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/plus_v2.png"))); // NOI18N
        jMenuItem1.setText("Product");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        products_menu.add(jMenuItem1);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/plus_v2.png"))); // NOI18N
        jMenuItem15.setText("Customer");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        products_menu.add(jMenuItem15);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/plus_v2.png"))); // NOI18N
        jMenuItem21.setText("Employee");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        products_menu.add(jMenuItem21);

        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/plus_v2.png"))); // NOI18N
        jMenuItem23.setText("Supplier");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        products_menu.add(jMenuItem23);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/plus_v2.png"))); // NOI18N
        jMenuItem12.setText("Task");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        products_menu.add(jMenuItem12);

        Main_Menu.add(products_menu);

        about_menu1.setText("Sell");

        jMenuItem7.setText("Sell Product");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        about_menu1.add(jMenuItem7);

        Main_Menu.add(about_menu1);

        about_menu2.setText("Expense");

        jMenuItem10.setText("Input Expense");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        about_menu2.add(jMenuItem10);

        Main_Menu.add(about_menu2);

        about_menu3.setText("Due");

        jMenuItem13.setText("Get Due");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        about_menu3.add(jMenuItem13);

        Main_Menu.add(about_menu3);

        about_menu4.setText("Report");

        jMenuItem25.setText("Status Report");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        about_menu4.add(jMenuItem25);

        jMenuItem24.setText("Account Report");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        about_menu4.add(jMenuItem24);

        jMenuItem16.setText("Stock Product Report");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        about_menu4.add(jMenuItem16);

        jMenuItem17.setText("Sold Product Report");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        about_menu4.add(jMenuItem17);

        Main_Menu.add(about_menu4);

        about_menu5.setText("Tools");

        jMenuItem19.setText("Settings");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        about_menu5.add(jMenuItem19);

        jMenuItem20.setText("History");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        about_menu5.add(jMenuItem20);

        Main_Menu.add(about_menu5);

        about_menu.setText("Help");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jMenuItem4.setText("Help Contents");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        about_menu.add(jMenuItem4);

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jMenuItem26.setText("Keyboad Shortcut");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        about_menu.add(jMenuItem26);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jMenuItem27.setText("Support");
        about_menu.add(jMenuItem27);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        about_menu.add(jMenuItem5);

        Main_Menu.add(about_menu);

        setJMenuBar(Main_Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        homepage h= new homepage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        //Exit Button From Menu
        this.dispose();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Input Products
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Input_Access = s1.Input_Access;
        if(User_Type.equals("Yes") || Input_Access.equals("Yes")){
            input_product ip = new input_product();
            ip.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        //Input Customers
        input_customer nc = new input_customer();
        nc.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // Input Employees
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String ARC = s1.Account_Report_Access;
        if(User_Type.equals("Yes")){
            input_employee ie = new input_employee();
            ie.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // Input Suppliers
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String ARC = s1.Account_Report_Access;
        if(User_Type.equals("Yes")){
            input_supplier s = new input_supplier();
            s.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        //Tasks and Goal
        tasks l = new tasks();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        //Sell Product
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Sell_Access = s1.Sell_Access;
        if(User_Type.equals("Yes") || Sell_Access.equals("Yes")){
            sell s = new sell();
            s.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        //Expense Access
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Expense_Access = s1.Expense_Access;
        if(User_Type.equals("Yes") || Expense_Access.equals("Yes")){
            expense e = new expense();
            e.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        //Get Due Access
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Due_Access = s1.Due_Access;
        if(User_Type.equals("Yes") || Due_Access.equals("Yes")){
            due gd = new due();
            gd.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        status_report p = new status_report();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String ARC = s1.Account_Report_Access;
        if(User_Type.equals("Yes") || ARC.equals("Yes")){
            account_report ar = new account_report();
            ar.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Input_Access = s1.Input_Access;
        if(User_Type.equals("Yes") || Input_Access.equals("Yes")){
            stock_report p = new stock_report();
            p.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // Sold Product Report
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Sell_Access = s1.Sell_Access;
        if(User_Type.equals("Yes") || Sell_Access.equals("Yes")){
            sold_report sr = new sold_report();
            sr.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        //Settings
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        if(User_Type.equals("Yes")){
            settings s = new settings();
            s.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // History
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        if(User_Type.equals("Yes")){
            history lh = new history();
            lh.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        //Help Page
        help h = new help();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //About Jpanel
        about ab = new about();
        ab.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      //Custom Date Filter 
       DefaultTableModel tMOdel = (DefaultTableModel) status_report.getModel();
       tMOdel.setRowCount(0);
       if(from.getDate() != null &&  to.getDate() != null){
            try{  
                custom_purchase_price();
                custom_selling_price();
                custom_profit();
                custom_expense();
                custom_item_sold();
                custom_customers();
                custom_paid();
                custom_due();
                custom_cash_on_hand();     
            }
             catch(Exception e){
               System.out.println("Error While Connecting : "+e);
            } 
        }
        else{            
             JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
        }
      
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Print Buttons
        try{
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                String Company=rs.getString("company_info");
                MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                MessageFormat header = new MessageFormat("Custom Report");
                try {
                    status_report.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                } catch (java.awt.print.PrinterAbortException e) {
                } catch (PrinterException ex) {

                }
            }
          }
          catch(Exception e){
              e.printStackTrace();
          }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       //Todays Status
       //Today's
        DefaultTableModel tMOdel = (DefaultTableModel) status_report.getModel();
        tMOdel.setRowCount(0);
        total_label.setText(" Today's");
        todays_purchase_price();
        todays_selling_price();
        todays_profit();
        todays_expense();
        todays_item_sold();
        todays_customers();
        todays_paid();
        todays_due();
        todays_cash_on_hand();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // This Months 
         //Monthly       
        DefaultTableModel tMOdel = (DefaultTableModel) status_report.getModel();
       tMOdel.setRowCount(0);
        total_label.setText(" Monthly");
        monthly_purchase_price();
        monthly_selling_price();
        monthly_profit();
        monthly_expense();
        monthly_item_sold();
        monthly_customers();
        monthly_paid();
        monthly_due();
        monthly_cash_on_hand();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Total
        DefaultTableModel tMOdel = (DefaultTableModel) status_report.getModel();
        tMOdel.setRowCount(0);
        total_label.setText(" Total");
        total_purchase_price();    
        total_selling_price();
        total_profit();
        total_expense_ammount();
        total_item_sold();
        total_customers();
        total_paid();
        total_due();
        cash_on_hand();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void status_reportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_status_reportMousePressed
      
    }//GEN-LAST:event_status_reportMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(status_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(status_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(status_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(status_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new status_report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Main_Menu;
    private javax.swing.JMenu about_menu;
    private javax.swing.JMenu about_menu1;
    private javax.swing.JMenu about_menu2;
    private javax.swing.JMenu about_menu3;
    private javax.swing.JMenu about_menu4;
    private javax.swing.JMenu about_menu5;
    private javax.swing.JLabel cash_on_hand;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JMenu products_menu;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable status_report;
    private com.toedter.calendar.JDateChooser to;
    private javax.swing.JLabel total_customers;
    private javax.swing.JLabel total_due;
    private javax.swing.JLabel total_expense;
    private javax.swing.JLabel total_item_sold;
    private javax.swing.JLabel total_label;
    private javax.swing.JLabel total_paid;
    private javax.swing.JLabel total_profit;
    private javax.swing.JLabel total_purchases_price;
    private javax.swing.JLabel total_selling_price;
    // End of variables declaration//GEN-END:variables
}
