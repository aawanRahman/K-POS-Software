/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Najmul
 */

public class stock_report extends javax.swing.JFrame {
    db_connect db = new db_connect();
    
    String number_of_products ="100";

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
    String Date = now.format(date);  
    
    public stock_report() {
        full_screen();  
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon();
        theme();   
        exit();
        show_products();           
        purches_price();
        total_price();
        product_count();
        products.setDefaultEditor(Object.class, null);
        products.setShowGrid(true); 
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
    }
    
    private void seticon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
    }   
   
   //Settings
    void theme(){ 
        get_settings get=new get_settings();       
        String Company = get.Company;
        String Version = get.Version;
        footer.setText(Company+" "+Version);  
        Color content_color = get.Content_Color;
        getContentPane().setBackground(content_color);   
        Color sidebar_color = get.Sidebar_Color;
        sidebar.setBackground(sidebar_color);    
        //products.setBackground(content_color); 
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
    
    
    
    //Show All Stock Products
    void show_products(){        
        try{
            String Get ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Get);
            ResultSet rs =statement.executeQuery(Get);
            if(rs.next()){
                String number_of_products=rs.getString("number_of_products"); 
                 try{
                    String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products ORDER BY id DESC LIMIT "+number_of_products+"");
                    PreparedStatement statement2 = db.connect().prepareStatement(Query);
                    ResultSet rs2 =statement2.executeQuery(Query);
                    products.setModel(DbUtils.resultSetToTableModel(rs2));
                  }
                  catch(Exception e){
                      System.out.println("Error While Connecting : "+e);
                  }
            }           
        }
        catch(Exception e){
            System.out.println("Error While Connecting : "+e);
        }     
    }
    
    
     //Total Product
    //Sum(id) Total
    public double product_count(){
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select count(id) from products");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_products.setText("Total Products : "+sum);
          } 
        catch(Exception exc){
              System.out.println(exc.getMessage());
          }
        return value;
    }
    
    
    //Total Purchase Price
    //Sum(Purchase Price) Total
    public double purches_price(){        
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(sum(total_purchese_price),0) from products");
           ResultSet result = statement.executeQuery();
           result.next();
           String sumx = result.getString(1);
           double val1 = Double.parseDouble(sumx);
           double val2 = Math.round(val1);
           String Total_Price = Double.toString(val2);           
           purches_price.setText("Total Purches Price : "+Total_Price);
          } 
        catch(Exception exc){
              System.out.println(exc.getMessage());
          }
        return value;
    } 
    
    
    //Total Selling Price 
    //Sum(Total Price) Total
    public double total_price(){
      double value=0.0;
      try{
         PreparedStatement statement =  db.connect().prepareStatement("select sum(total_price) from products");
         ResultSet result = statement.executeQuery();
         result.next();
         String sum = result.getString(1);
         double val1 = Double.parseDouble(sum);
         double val2 = Math.round(val1);
         String Total_Price = Double.toString(val2);           
         total_price.setText("Total Selling Price : "+Total_Price);
        } 
      catch(Exception exc){
            System.out.println(exc.getMessage());
        }
      return value;
    }  
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stock_report_detaiils = new javax.swing.JFrame();
        product_name = new javax.swing.JLabel();
        product_name1 = new javax.swing.JLabel();
        product_name2 = new javax.swing.JLabel();
        purchases_price = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        product_name5 = new javax.swing.JLabel();
        selling_price = new javax.swing.JLabel();
        product_name7 = new javax.swing.JLabel();
        tp = new javax.swing.JLabel();
        product_name9 = new javax.swing.JLabel();
        product_name10 = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        product_name13 = new javax.swing.JLabel();
        total_purchases_price = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        product_name16 = new javax.swing.JLabel();
        entry_date = new javax.swing.JLabel();
        product_name18 = new javax.swing.JLabel();
        expire_date = new javax.swing.JLabel();
        product_id = new javax.swing.JLabel();
        product_name21 = new javax.swing.JLabel();
        product_name22 = new javax.swing.JLabel();
        product_type = new javax.swing.JLabel();
        product_name24 = new javax.swing.JLabel();
        qty_alert = new javax.swing.JLabel();
        product_name26 = new javax.swing.JLabel();
        location = new javax.swing.JLabel();
        issuer = new javax.swing.JLabel();
        product_name29 = new javax.swing.JLabel();
        chart = new javax.swing.JFrame();
        chart_panel = new javax.swing.JPanel();
        get_product_name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        purches_price = new javax.swing.JLabel();
        total_price = new javax.swing.JLabel();
        total_products = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        product_types = new javax.swing.JComboBox<>();
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

        stock_report_detaiils.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        stock_report_detaiils.setTitle("Product Details");
        stock_report_detaiils.setAlwaysOnTop(true);
        stock_report_detaiils.setMinimumSize(new java.awt.Dimension(500, 300));
        stock_report_detaiils.setResizable(false);

        product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name.setText("Name");

        product_name1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name1.setText("Product Name : ");

        product_name2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name2.setText("Purchases Price :");

        purchases_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchases_price.setText("Price");

        quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantity.setText("Quantity");

        product_name5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name5.setText("Selling Price :");

        selling_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selling_price.setText("Price");

        product_name7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name7.setText("Quantity :");

        tp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tp.setText("Name");

        product_name9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name9.setText("Total Price");

        product_name10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name10.setText("Company :");

        company.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        company.setText("Name");

        product_name13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name13.setText("Total Purchases Price");

        total_purchases_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_purchases_price.setText("Name");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        product_name16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name16.setText("Entry Date :");

        entry_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entry_date.setText("Date");

        product_name18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name18.setText("Expire Date :");

        expire_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expire_date.setText("Date");

        product_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_id.setText("Id");

        product_name21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name21.setText("Product Id : ");

        product_name22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name22.setText("Type :");

        product_type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_type.setText("Type");

        product_name24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name24.setText("Qty_Alert");

        qty_alert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        qty_alert.setText("xxxxx");

        product_name26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name26.setText("Location :");

        location.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        location.setText("Name");

        issuer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        issuer.setText("Name");

        product_name29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name29.setText("Issuer :");

        javax.swing.GroupLayout stock_report_detaiilsLayout = new javax.swing.GroupLayout(stock_report_detaiils.getContentPane());
        stock_report_detaiils.getContentPane().setLayout(stock_report_detaiilsLayout);
        stock_report_detaiilsLayout.setHorizontalGroup(
            stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(company))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantity))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selling_price))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(purchases_price))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(product_name))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(product_id))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(product_type))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qty_alert)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(expire_date))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entry_date))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_purchases_price))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tp))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(issuer))
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addComponent(product_name26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(location)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        stock_report_detaiilsLayout.setVerticalGroup(
            stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product_id)
                            .addComponent(product_name21))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product_name)
                            .addComponent(product_name1))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(purchases_price)
                            .addComponent(product_name2))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selling_price)
                            .addComponent(product_name5))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantity)
                            .addComponent(product_name7))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(company)
                            .addComponent(product_name10))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product_type)
                            .addComponent(product_name22))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty_alert)
                            .addComponent(product_name24)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(stock_report_detaiilsLayout.createSequentialGroup()
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(entry_date)
                            .addComponent(product_name16))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expire_date)
                            .addComponent(product_name18))
                        .addGap(30, 30, 30)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(location)
                            .addComponent(product_name26))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(issuer)
                            .addComponent(product_name29))
                        .addGap(30, 30, 30)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tp)
                            .addComponent(product_name9))
                        .addGap(10, 10, 10)
                        .addGroup(stock_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total_purchases_price)
                            .addComponent(product_name13))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        chart.setTitle("Charts | Stock Report");
        chart.setAlwaysOnTop(true);
        chart.setResizable(false);
        chart.setSize(new java.awt.Dimension(1014, 537));
        chart.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                chartWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                chartWindowOpened(evt);
            }
        });

        chart_panel.setOpaque(false);
        chart_panel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout chart_panelLayout = new javax.swing.GroupLayout(chart_panel);
        chart_panel.setLayout(chart_panelLayout);
        chart_panelLayout.setHorizontalGroup(
            chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1014, Short.MAX_VALUE)
        );
        chart_panelLayout.setVerticalGroup(
            chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chartLayout = new javax.swing.GroupLayout(chart.getContentPane());
        chart.getContentPane().setLayout(chartLayout);
        chartLayout.setHorizontalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        chartLayout.setVerticalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartLayout.createSequentialGroup()
                .addComponent(chart_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stock Product Report");
        setMinimumSize(new java.awt.Dimension(1212, 626));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        get_product_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        get_product_name.setToolTipText("Search By Product Name or id");
        get_product_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        get_product_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        get_product_name.setName(""); // NOI18N
        get_product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_product_nameActionPerformed(evt);
            }
        });
        get_product_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                get_product_nameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                get_product_nameKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/search.png"))); // NOI18N
        jButton1.setText("Search ");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        products.setAutoCreateRowSorter(true);
        products.setBackground(new java.awt.Color(238, 238, 238));
        products.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        products.setForeground(new java.awt.Color(42, 42, 42));
        products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Price", "Quantity", "Company", "Date"
            }
        ));
        products.setToolTipText("All Products");
        products.setAlignmentX(5.0F);
        products.setAlignmentY(5.0F);
        products.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        products.setEditingColumn(0);
        products.setEditingRow(0);
        products.setFocusable(false);
        products.setGridColor(new java.awt.Color(209, 206, 206));
        products.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        products.setName("All Products"); // NOI18N
        products.setOpaque(false);
        products.setRequestFocusEnabled(false);
        products.setRowHeight(30);
        products.setRowMargin(0);
        products.setSelectionBackground(new java.awt.Color(186, 186, 236));
        products.setSelectionForeground(new java.awt.Color(0, 0, 0));
        products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                productsMousePressed(evt);
            }
        });
        products.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(products);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Filter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        purches_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purches_price.setForeground(new java.awt.Color(255, 255, 255));
        purches_price.setText("Total Purches Price : ");

        total_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_price.setForeground(new java.awt.Color(255, 255, 255));
        total_price.setText("Total Price : ");

        total_products.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_products.setForeground(new java.awt.Color(255, 255, 255));
        total_products.setText("Total Products : ");

        jButton11.setBackground(new java.awt.Color(34, 49, 63));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Low on Quantity");
        jButton11.setBorder(null);
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/stock report.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(34, 49, 63));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Expire Alert");
        jButton12.setBorder(null);
        jButton12.setFocusable(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("Footer Text");

        jButton13.setBackground(new java.awt.Color(34, 49, 63));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Product Reports");
        jButton13.setBorder(null);
        jButton13.setFocusable(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(footer)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(total_price)
                    .addComponent(purches_price)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(total_products))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton7)
                .addGap(8, 8, 8)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(total_products)
                .addGap(8, 8, 8)
                .addComponent(purches_price)
                .addGap(8, 8, 8)
                .addComponent(total_price)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("To ");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/print.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        product_types.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_types.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Types" }));
        product_types.setToolTipText("Filter Products Based on Product Types or Catagory");
        product_types.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_typesActionPerformed(evt);
            }
        });

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
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(get_product_name)
                        .addGap(5, 5, 5)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(product_types, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(product_types)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search(){
        String Product_Name = get_product_name.getText();
        if(Product_Name.isEmpty()){
            show_products();
        }
        else{
            try{
             String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE Name LIKE '%"+Product_Name+"%' OR id LIKE '%"+Product_Name+"%' ORDER BY id DESC");
             PreparedStatement statement = db.connect().prepareStatement(Query);
             ResultSet rs =statement.executeQuery(Query);
             products.setModel(DbUtils.resultSetToTableModel(rs));
           }
           catch(Exception e){
               System.out.println("Error While Connecting : "+e);
           }
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      //search Button
      search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void get_product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_product_nameActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Homepage
        homepage h= new homepage();
        h.setVisible(true);
        this.dispose();
        stock_report_detaiils.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Date Search Filter
        if(from.getDate() != null &&  to.getDate() != null){
            try{
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());           
                String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE Date >= '"+From+"' AND Date <= '"+To+"' ORDER BY id DESC");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query); 
                products.setModel(DbUtils.resultSetToTableModel(rs));
            }
             catch(Exception e){
               System.out.println("Error While Connecting : "+e);
            } 
        }
        else{            
             JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      //Product Low on Quantity
        try{
           String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE Quantity <= qty_alert ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error Whigle Connecting : "+e);
         }      
        product_types.setSelectedItem("Low on Quantity");
        stock_report_detaiils.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void get_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyPressed
         //Search Enter Clicked 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            search ();
        }
    }//GEN-LAST:event_get_product_nameKeyPressed

    private void get_product_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyTyped
        // Key Pressed
        search (); 
    }//GEN-LAST:event_get_product_nameKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         //Print Button Code             
          try{
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                String Company=rs.getString("company_info");
                            
                MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                MessageFormat header = new MessageFormat("Stock Report of all Products");               
                
                
                String selecteditem = String.valueOf(product_types.getSelectedItem());
                if(selecteditem.equals("Low on Quantity")){
                    show_low_on_quantity();      
                    try {
                        products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                    } catch (java.awt.print.PrinterAbortException e) {
                    } catch (PrinterException ex) {
                        Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                    } catch (java.awt.print.PrinterAbortException e) {
                    } catch (PrinterException ex) {
                        Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            }
          }
          catch(Exception e){
              e.printStackTrace();
          }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void show_low_on_quantity(){
        try{
            String Query =("SELECT id AS ID,Name AS 'Product Name',Quantity FROM products WHERE Quantity <= qty_alert ORDER BY id DESC");
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            products.setModel(DbUtils.resultSetToTableModel(rs));
          }
          catch(Exception e){
              System.out.println("Error Whigle Connecting : "+e);
          }      
         stock_report_detaiils.dispose();
    }
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // Expire Alert
        try{
            String Query1 ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query1);
            ResultSet rs =statement.executeQuery(Query1);
            if(rs.next()){
                int Months=rs.getInt("expire_month"); 
                //System.out.println(Months); //Fetch Month From Database
                LocalDate mydate = LocalDate.now();//For reference
                mydate = mydate.plusMonths(Months);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String Date = mydate.format(formatter);
                //System.out.println(Date); //One Month Earlier
                try{
                   String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE doe <= '"+Date+"' ORDER BY id DESC");
                   PreparedStatement statement1 = db.connect().prepareStatement(Query);
                   ResultSet rs1 =statement1.executeQuery(Query);
                   products.setModel(DbUtils.resultSetToTableModel(rs1));
                 }
                 catch(Exception e){
                     System.out.println("Error Whigle Connecting : "+e);
                 }  
            }
        }
        catch(Exception e){
            
        }        
        product_types.setSelectedItem("Expired Products");
        stock_report_detaiils.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
      
    }//GEN-LAST:event_productsMouseClicked

    private void productsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productsKeyPressed
        //Enter Key Pressed Details          
       
    }//GEN-LAST:event_productsKeyPressed

    private void productsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMousePressed
         //Pressed Jtable
         try{
            String Query1 ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query1);
            ResultSet rs =statement.executeQuery(Query1);
            if(rs.next()){
                String Details=rs.getString("show_details"); 
                if(Details.equals("Enabled")){
                    try{
                        int row = products.getSelectedRow();
                        String Table_Click = (products.getModel().getValueAt(row,0).toString());
                        String Query ="SELECT * FROM products WHERE id='"+Table_Click+"'";           
                        PreparedStatement statement1 = db.connect().prepareStatement(Query);
                        ResultSet rs2 =statement1.executeQuery(Query);
                        if(rs2.next()){
                            String Product_Name = rs2.getString("Name");
                            product_name.setText(Product_Name);       
                            
                            String Product_Id = rs2.getString("id");
                            product_id.setText(Product_Id); 
                            
                            String Selling_Price = rs2.getString("Price");
                            selling_price.setText(Selling_Price);       
                            
                            String Purchases_Price = rs2.getString("Purchase_Price");
                            purchases_price.setText(Purchases_Price);    
                            
                            String Quantity = rs2.getString("Quantity");
                            quantity.setText(Quantity);
                            
                            String Company = rs2.getString("Company");
                            company.setText(Company);
                            
                            String Type = rs2.getString("Type");
                            product_type.setText(Type);
                            
                            String Entry_Date = rs2.getString("Date");
                            entry_date.setText(Entry_Date);
                            
                            String Expire_Date = rs2.getString("doe");
                            expire_date.setText(Expire_Date);
                            
                            String Location = rs2.getString("Location");
                            location.setText(Location);
                            
                            String Issuer = rs2.getString("Issuer");
                            issuer.setText(Issuer);
                            
                            String Total_Price = rs2.getString("total_price");
                            tp.setText(Total_Price);
                            
                            String Total_Purchases_Price = rs2.getString("total_purchese_price");
                            total_purchases_price.setText(Total_Purchases_Price);
                            
                            String Qty_Alert = rs2.getString("qty_alert");
                            qty_alert.setText(Qty_Alert);
                                                        
                            //stock_report_detaiils.setLocationRelativeTo(null);//for shoeing this in center
                            stock_report_detaiils.setTitle(Product_Name);
                            stock_report_detaiils.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
                            stock_report_detaiils.setVisible(true);
                        }
                    }
                    catch(Exception e){
                         System.out.println(e);
                    }
                } 
                else{
                    //Do Nothing
                }
            }
        }
        catch(Exception e){
              System.out.println(e);
        }
    }//GEN-LAST:event_productsMousePressed

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

    public void show_expired_products(){        
        try{            
            String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE doe <= '"+Date+"' ORDER BY id DESC");
            PreparedStatement statement1 = db.connect().prepareStatement(Query);
            ResultSet rs1 =statement1.executeQuery(Query);
            products.setModel(DbUtils.resultSetToTableModel(rs1));
        }
        catch(Exception e){
            System.out.println("Error Whigle Connecting : "+e);
        }  
        stock_report_detaiils.dispose();
    }
    
    
    private void product_typesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_typesActionPerformed
        //Selected Items
        String selecteditem = String.valueOf(product_types.getSelectedItem());
        if(selecteditem.equals("Show All")){
            show_products();
        }
        else if(selecteditem.equals("Low on Quantity")){
            //Product Low on Quantity
            show_low_on_quantity();      
        }
        else if(selecteditem.equals("Expired Products")){
            show_expired_products();
        }
        else{            
            try{
                String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Purchase_Price AS 'Purchase Price',Quantity,qty_alert AS 'Quantity Alert',Issuer,Company,Location,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE Type='"+selecteditem+"' ORDER BY id desc");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                 System.out.println("Error While Connecting : "+e);
             }
        } 
    }//GEN-LAST:event_product_typesActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Window Activated 
        //Load Type
        try{
            String Query ="SELECT Type_Name FROM product_types";   
            PreparedStatement statement2 = db.connect().prepareStatement(Query);
            ResultSet rs =statement2.executeQuery(Query);  
            product_types.removeAllItems();              
            product_types.addItem("Show All");
            product_types.addItem("Low on Quantity");
            product_types.addItem("Expired Products");
            while(rs.next()){                          
                String result = rs.getString(1);
                if(result !=null){
                    result = result.trim();     
                    product_types.addItem(result);
                }
            } 
        }
        catch(Exception e){

        }
    }//GEN-LAST:event_formWindowActivated

    private void chartWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chartWindowActivated
        //Chart Onload
        
        
    }//GEN-LAST:event_chartWindowActivated

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // Pie Chart
        chart.setVisible(true);
        chart.setLocationRelativeTo(null);//for shoeing this in center
        chart.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));        
        
        //Default Chart
        // Line Chart
        try {
            XYSeriesCollection dataset = new XYSeriesCollection();  

            String Query = "SELECT * FROM products ORDER BY id DESC LIMIT 50";
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);                
            XYSeries series1 = new XYSeries("Selling Price");  
            XYSeries series2 = new XYSeries("Purchase Price");  
            XYSeries series3 = new XYSeries("Quantity");     
            while (rs.next()) {
                series1.add( 
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Price" ))
                );   
                series2.add( 
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Purchase_Price" ))
                );     
                series3.add( 
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Quantity" ))
                );    
            }  
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);

            JFreeChart chart = ChartFactory.createXYLineChart("Stock Product Report","Products","Price & Quantity",dataset,PlotOrientation.VERTICAL,true,true,false);

            //Enable AND Disable Grid Line and 
            XYPlot plot = chart.getXYPlot();
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.gray);
            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.gray);
            plot.setBackgroundPaint(Color.black);
            

            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesPaint(0, Color.YELLOW);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            plot.setRenderer(renderer);

            //Add Image to JPanel
            ChartPanel panel = new ChartPanel(chart);
            panel.setVisible(true);
            panel.setSize(1000, 500);
            chart_panel.add(panel);

        } catch (SQLException e) {

        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void chartWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chartWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_chartWindowOpened

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
            java.util.logging.Logger.getLogger(stock_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stock_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stock_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stock_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock_report().setVisible(true);
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
    private javax.swing.JFrame chart;
    private javax.swing.JPanel chart_panel;
    private javax.swing.JLabel company;
    private javax.swing.JLabel entry_date;
    private javax.swing.JLabel expire_date;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JLabel issuer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel location;
    private javax.swing.JLabel product_id;
    private javax.swing.JLabel product_name;
    private javax.swing.JLabel product_name1;
    private javax.swing.JLabel product_name10;
    private javax.swing.JLabel product_name13;
    private javax.swing.JLabel product_name16;
    private javax.swing.JLabel product_name18;
    private javax.swing.JLabel product_name2;
    private javax.swing.JLabel product_name21;
    private javax.swing.JLabel product_name22;
    private javax.swing.JLabel product_name24;
    private javax.swing.JLabel product_name26;
    private javax.swing.JLabel product_name29;
    private javax.swing.JLabel product_name5;
    private javax.swing.JLabel product_name7;
    private javax.swing.JLabel product_name9;
    private javax.swing.JLabel product_type;
    private javax.swing.JComboBox<String> product_types;
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JLabel purchases_price;
    private javax.swing.JLabel purches_price;
    private javax.swing.JLabel qty_alert;
    private javax.swing.JLabel quantity;
    private javax.swing.JLabel selling_price;
    private javax.swing.JPanel sidebar;
    private javax.swing.JFrame stock_report_detaiils;
    private com.toedter.calendar.JDateChooser to;
    private javax.swing.JLabel total_price;
    private javax.swing.JLabel total_products;
    private javax.swing.JLabel total_purchases_price;
    private javax.swing.JLabel tp;
    // End of variables declaration//GEN-END:variables
}
