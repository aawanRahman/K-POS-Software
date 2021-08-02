/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Najmul
 */

public class sold_report extends javax.swing.JFrame {
    db_connect db = new db_connect();
   
    LocalDate localDate = LocalDate.now();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Date = localDate.format(formatter);
    
    public sold_report(){
        full_screen();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon();        
        theme();   
        exit();
        show_products(); 
        product_count();
        total_refill();
        total_price();
        total_refill_quantity_sum();
        total_refill_sum();
        total_refill_today();
        products.setDefaultEditor(Object.class, null);
        products.setShowGrid(true); 
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
    
    
    
   //Show all Sold Products
    void show_products(){
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                String number_of_products=rs.getString("number_of_products"); 
                 try{
                    String Query2 =("SELECT id AS ID,Username AS 'Customer Name', Product_Cost AS 'Product Cost' ,Total_Price AS 'Total Price' , Vat , Due , Discount , Others , Paid ,Payment,Issuer, Shipping ,Date FROM sold ORDER BY id DESC LIMIT "+number_of_products+"");
                    PreparedStatement statement2 = db.connect().prepareStatement(Query2);
                    ResultSet rs2 =statement2.executeQuery(Query2);
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
    //Count(id) Total
    public double product_count(){ 
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select count(id) from sold");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_products.setText("Total Item : "+sum);
        } 
        catch(Exception exc){
           System.out.println(exc.getMessage());
       }
      return value;
  }
    
    //Total Refill Items
    //Count(id) Total
    public double total_refill(){
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select count(id) from sold_report WHERE Issuer='Refill'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_refill.setText("Total Refill Item : "+sum);

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
    }
    
    
    //Total Selling Price
    //Sum(Selling Price) Total Payment = Paid
     public double total_price(){
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(sum(Total_Price),0) from sold WHERE Payment = 'PAID'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_price.setText("Total Sold Price : "+sum);

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }  
    
    
    //Total Refill Quantity Sum
    //Sum(Quantity)
    public double total_refill_quantity_sum(){      
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(sum(Quantity),0) from sold_report WHERE Issuer='Refill'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_refill_quantity_sum.setText("Total Refill (Quantity) : "+sum);
          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
    } 
     
     
    //Total Refill Selling Price
    //Sum(Selling Price)
    public double total_refill_sum(){
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(sum(Total_Price)*(-1),0) from sold_report WHERE Issuer='Refill'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_refill_sum.setText("Total Refill : "+sum);
          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
    }
    
    
    
    
     //Total Refill Today 
    //Sum(Selling Price) Today
     public double total_refill_today(){       
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(sum(Total_Price)*(-1),0) from sold_report WHERE Issuer='Refill' AND Date='"+Date+"'");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_refill_sum_today.setText("Total Refill (Today) : "+sum);
          } catch(Exception exc){
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

        sold_report_detaiils = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        sold_report_details = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        chart = new javax.swing.JFrame();
        chart_panel = new javax.swing.JPanel();
        due_chart = new javax.swing.JFrame();
        chart_panel_due = new javax.swing.JPanel();
        get_product_name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        total_price = new javax.swing.JLabel();
        total_products = new javax.swing.JLabel();
        total_refill = new javax.swing.JLabel();
        total_refill_sum = new javax.swing.JLabel();
        total_refill_sum_today = new javax.swing.JLabel();
        total_refill_quantity_sum = new javax.swing.JLabel();
        sold_report_button = new javax.swing.JButton();
        due_report_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        sold_filter = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
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

        sold_report_detaiils.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        sold_report_detaiils.setTitle("Product Details");
        sold_report_detaiils.setMinimumSize(new java.awt.Dimension(785, 400));
        sold_report_detaiils.setResizable(false);

        sold_report_details.setAutoCreateRowSorter(true);
        sold_report_details.setBackground(new java.awt.Color(238, 238, 238));
        sold_report_details.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        sold_report_details.setForeground(new java.awt.Color(42, 42, 42));
        sold_report_details.setModel(new javax.swing.table.DefaultTableModel(
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
        sold_report_details.setToolTipText("All Products");
        sold_report_details.setAlignmentX(5.0F);
        sold_report_details.setAlignmentY(5.0F);
        sold_report_details.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sold_report_details.setEditingColumn(0);
        sold_report_details.setEditingRow(0);
        sold_report_details.setFocusable(false);
        sold_report_details.setGridColor(new java.awt.Color(209, 206, 206));
        sold_report_details.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        sold_report_details.setName("All Products"); // NOI18N
        sold_report_details.setOpaque(false);
        sold_report_details.setRequestFocusEnabled(false);
        sold_report_details.setRowHeight(30);
        sold_report_details.setSelectionBackground(new java.awt.Color(186, 186, 236));
        sold_report_details.setSelectionForeground(new java.awt.Color(0, 0, 0));
        sold_report_details.getTableHeader().setReorderingAllowed(false);
        sold_report_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sold_report_detailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sold_report_details);

        jMenu3.setText("File");

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Print");

        jMenuItem2.setText("Print");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar2.add(jMenu4);

        sold_report_detaiils.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout sold_report_detaiilsLayout = new javax.swing.GroupLayout(sold_report_detaiils.getContentPane());
        sold_report_detaiils.getContentPane().setLayout(sold_report_detaiilsLayout);
        sold_report_detaiilsLayout.setHorizontalGroup(
            sold_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        sold_report_detaiilsLayout.setVerticalGroup(
            sold_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        chart.setTitle("Charts | Sold Products Report");
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

        due_chart.setTitle("Charts | Sold Products Report");
        due_chart.setAlwaysOnTop(true);
        due_chart.setResizable(false);
        due_chart.setSize(new java.awt.Dimension(1014, 537));
        due_chart.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                due_chartWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                due_chartWindowOpened(evt);
            }
        });

        chart_panel_due.setOpaque(false);
        chart_panel_due.setRequestFocusEnabled(false);

        javax.swing.GroupLayout chart_panel_dueLayout = new javax.swing.GroupLayout(chart_panel_due);
        chart_panel_due.setLayout(chart_panel_dueLayout);
        chart_panel_dueLayout.setHorizontalGroup(
            chart_panel_dueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1014, Short.MAX_VALUE)
        );
        chart_panel_dueLayout.setVerticalGroup(
            chart_panel_dueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout due_chartLayout = new javax.swing.GroupLayout(due_chart.getContentPane());
        due_chart.getContentPane().setLayout(due_chartLayout);
        due_chartLayout.setHorizontalGroup(
            due_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart_panel_due, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        due_chartLayout.setVerticalGroup(
            due_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(due_chartLayout.createSequentialGroup()
                .addComponent(chart_panel_due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sold Product Report");
        setMinimumSize(new java.awt.Dimension(1212, 626));

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        get_product_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        get_product_name.setToolTipText("Search By Customer Name or id");
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                productsMousePressed(evt);
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

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("Footer Text");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/sell_total.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        total_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_price.setForeground(new java.awt.Color(255, 255, 255));
        total_price.setText("Total Price : ");

        total_products.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_products.setForeground(new java.awt.Color(255, 255, 255));
        total_products.setText("Total Products Item : ");

        total_refill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_refill.setForeground(new java.awt.Color(255, 255, 255));
        total_refill.setText("Total Refill Item : ");

        total_refill_sum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_refill_sum.setForeground(new java.awt.Color(255, 255, 255));
        total_refill_sum.setText("Total Refill : ");

        total_refill_sum_today.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_refill_sum_today.setForeground(new java.awt.Color(255, 255, 255));
        total_refill_sum_today.setText("Total Refill (Today): ");

        total_refill_quantity_sum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_refill_quantity_sum.setForeground(new java.awt.Color(255, 255, 255));
        total_refill_quantity_sum.setText("Total Refill (Quantity) : ");

        sold_report_button.setBackground(new java.awt.Color(34, 49, 63));
        sold_report_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sold_report_button.setForeground(new java.awt.Color(255, 255, 255));
        sold_report_button.setText("Product Reports");
        sold_report_button.setBorder(null);
        sold_report_button.setFocusable(false);
        sold_report_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sold_report_buttonActionPerformed(evt);
            }
        });

        due_report_button.setBackground(new java.awt.Color(34, 49, 63));
        due_report_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        due_report_button.setForeground(new java.awt.Color(255, 255, 255));
        due_report_button.setText("Due Report");
        due_report_button.setBorder(null);
        due_report_button.setFocusable(false);
        due_report_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                due_report_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(due_report_button, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sold_report_button, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(total_refill_quantity_sum)
                        .addComponent(total_refill_sum_today)
                        .addComponent(total_refill_sum)
                        .addComponent(total_refill)
                        .addComponent(total_price)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(footer)
                        .addComponent(total_products)))
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
                .addComponent(sold_report_button, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(due_report_button, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(total_products)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_refill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_price)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_refill_quantity_sum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_refill_sum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_refill_sum_today)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("To ");

        to.setMinimumSize(new java.awt.Dimension(90, 20));
        to.setPreferredSize(new java.awt.Dimension(90, 20));

        sold_filter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sold_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sold Report", "Sold Report (Paid)", "Product Report", "Refill Report", "Expense Report", "Due Report", "Due Report (Paid)", "Sold | Most Popular", "Sold | Less Popular" }));
        sold_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sold_filterActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(get_product_name, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(sold_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(sold_filter, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search(){
        Object selected = sold_filter.getSelectedItem();
        if(selected.toString().equals("Sold Report")){        
            String Product_Name = get_product_name.getText();           
            if(Product_Name.isEmpty()){
                 show_products();    
            }
            else{
                try{
                 String Get_Name = get_product_name.getText();
                 String Query =("SELECT id AS ID,Username AS 'Customer Name' , Product_Cost AS 'Product Cost' ,Total_Price AS 'Total Price' , Vat , Due , Discount , Others , Paid ,Payment, Issuer , Shipping , Date FROM sold WHERE Username LIKE '%"+Get_Name+"%' OR id LIKE '%"+Get_Name+"%' AND Payment = 'PAID'  ORDER BY id DESC");
                 PreparedStatement statement = db.connect().prepareStatement(Query);
                 ResultSet rs =statement.executeQuery(Query);   
                 products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error Whiggggle Connecting : "+e);
               }
            }
        }
        else if(selected.toString().equals("Product Report")){
            String Product_Name = get_product_name.getText();           
            if(Product_Name.isEmpty()){

            }
            else{
                try{
                 String Get_Name = get_product_name.getText();
                 String Query =("SELECT id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Payment , Issuer , Date FROM sold_report WHERE Username LIKE '%"+Get_Name+"%' OR Product LIKE '%"+Get_Name+"%'  ORDER BY id DESC");
                 PreparedStatement statement = db.connect().prepareStatement(Query);
                 ResultSet rs =statement.executeQuery(Query);     
                 products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error Whiggggle Connecting : "+e);
               }
            }
        }
        else if(selected.toString().equals("Refill Report")){
            String Product_Name = get_product_name.getText();           
            if(Product_Name.isEmpty()){
                refill_product();
            }
            else{
                try{
                 String Get_Name = get_product_name.getText();
                 String Query =("SELECT id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Issuer , Date FROM sold_report WHERE Product LIKE '%"+Get_Name+"%' AND Issuer='Refill'  ORDER BY id DESC");
                 PreparedStatement statement = db.connect().prepareStatement(Query);
                 ResultSet rs =statement.executeQuery(Query);
                 products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error Whiggggle Connecting : "+e);
               }
            }
        }     
        else if(selected.toString().equals("Expense Report")){
           String Product_Name = get_product_name.getText();       
            if(Product_Name.isEmpty()){
                show_expense();
            }
            else{
                try{
                    String Query =("SELECT id AS ID,bill_no AS 'Bill No',company AS 'Company',reciver AS 'Reciver Name',ammount AS 'Ammount',issuer AS 'Issuer', type AS Type ,description AS 'Description',date AS 'Date' FROM expense WHERE bill_no LIKE '%"+Product_Name+"%' OR reciver LIKE '%"+Product_Name+"%' ");
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);
                    products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error While Connecting : "+e);
               }
            }
        }       
        else if(selected.toString().equals("Due Report")){
            String Username = get_product_name.getText();        
            if(Username.isEmpty()){
                show_due();
            }
            else{
                try{
                //Search DUE Product
                String Query =("SELECT id AS ID, Username AS 'Customer Name',Total_Price AS 'Total Price',Paid,Total_Price-Paid AS Due, Issuer ,Date FROM sold WHERE Payment='DUE' AND Username LIKE '%"+Username+"%' OR id LIKE '%"+Username+"%' AND Payment='DUE'  ");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error Whiggggle Connecting : "+e);
               }
            }
        } 
        else if(selected.toString().equals("Due Report (Paid)")){
            String Username = get_product_name.getText();        
            if(Username.isEmpty()){
                //Do Nothing
            }
            else{
                try{
                //Search DUE Product
                String Query =("SELECT id AS ID, Username , Total_Due , Paid,Due , Issuer , Date FROM due_report WHERE Username LIKE '%"+Username+"%' OR id LIKE '%"+Username+"%'");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error Whiggggle Connecting : "+e);
               }
            }
        } 
        else{
            //Do Nothing !
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Search Button Sold Report
       search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void get_product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_product_nameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        homepage h= new homepage();
        h.setVisible(true);
        this.dispose();
        sold_report_detaiils.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Date Filter Button
        Object selected = sold_filter.getSelectedItem();
        if(selected.toString().equals("Sold Report")){    
            if(from.getDate() != null &&  to.getDate() != null){
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());
                //System.out.println(From+" "+To);
                if(From.isEmpty() || To.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please Select a Date ?","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{            
                    try{                   
                        String Query =("select id AS ID,Username AS 'Customer Name' , Product_Cost AS 'Product Cost' ,Total_Price AS 'Total Price' , Vat , Due , Discount , Others , Paid ,Payment,Issuer , Shipping ,Date from sold where Date >= '"+From+"' and Date <= '"+To+"' ORDER BY id DESC");
                        PreparedStatement statement = db.connect().prepareStatement(Query);
                        ResultSet rs =statement.executeQuery(Query);
                        products.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                     catch(Exception e){
                       System.out.println("Error While Connecting : "+e);
                    }        
                }
            }
            else{            
                 JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
            }
        }
        else if (selected.toString().equals("Product Report")){
            if(from.getDate() != null &&  to.getDate() != null){
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());
                if(From.isEmpty() || To.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please Select a Date ?","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{            
                    try{                    
                     String Query =("select id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Payment , Issuer , Date , Custom_ID from sold_report where Date >= '"+From+"' and Date <= '"+To+"' ORDER BY id DESC");
                     PreparedStatement statement = db.connect().prepareStatement(Query);
                     ResultSet rs =statement.executeQuery(Query);
                     products.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                     catch(Exception e){
                       System.out.println("Error Whiggggle Connecting : "+e);
                    }        
                }
            }
            else{            
                 JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
            }  
        }
        else if (selected.toString().equals("Refill Report")){
            if(from.getDate() != null &&  to.getDate() != null){
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());
                //System.out.println(From+" "+To);
                if(From.isEmpty() || To.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please Select a Date ?","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{            
                    try{                   
                        String Query =("select id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Issuer , Date from sold_report where Issuer='Refill' AND Date >= '"+From+"' and Date <= '"+To+"' ORDER BY id DESC");
                        PreparedStatement statement = db.connect().prepareStatement(Query);
                        ResultSet rs =statement.executeQuery(Query);  
                        products.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                     catch(Exception e){
                       System.out.println("Error Whiggggle Connecting : "+e);
                    }        
                }
            }
            else{            
                 JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
            } 
        }
        else if (selected.toString().equals("Expense Report")){
            if(from.getDate() != null &&  to.getDate() != null){
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());
                if(From.isEmpty() || To.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please Select a Date ?","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{            
                    try{                   
                        String Query =("SELECT id AS ID,bill_no AS 'Bill No',company AS 'Company',reciver AS 'Reciver Name',ammount AS 'Ammount',issuer AS 'Issuer', type AS Type ,description AS 'Description',date AS 'Date' FROM expense WHERE Date >= '"+From+"' AND Date <= '"+To+"';");
                        PreparedStatement statement = db.connect().prepareStatement(Query);
                        ResultSet rs =statement.executeQuery(Query);
                        products.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                     catch(Exception e){
                       System.out.println("Error Whiggggle Connecting : "+e);
                    }        
                }
            }
            else{            
                 JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
            } 
        }
        else if (selected.toString().equals("Due Report")){
            if(from.getDate() != null &&  to.getDate() != null){
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());     
                String To = dcn.format(to.getDate());
                if(From.isEmpty() || To.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please Select a Date ?","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{            
                    try{                   
                        String Query =("SELECT id AS ID, Username AS 'Customer Name',Total_Price AS 'Total Price',Paid,Total_Price-Paid AS Due, Issuer ,Date FROM sold WHERE Payment='DUE' AND Date >= '"+From+"' AND Date <= '"+To+"'");
                        PreparedStatement statement = db.connect().prepareStatement(Query);
                        ResultSet rs =statement.executeQuery(Query);
                        products.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                     catch(Exception e){
                       System.out.println("Error Whiggggle Connecting : "+e);
                    }        
                }
            }
            else{            
                 JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);       
            } 
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    //Show Refill Products
    private void refill_product(){        
        sold_filter.setSelectedItem("Refill Report");
        try{
           String Query =("SELECT id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Issuer , Date FROM sold_report WHERE Issuer = 'Refill' ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
        }
    }
    
    
    private void get_product_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyTyped
        // Key Pressed Event
        search();
    }//GEN-LAST:event_get_product_nameKeyTyped

    private void get_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyPressed
        //Enter Key Pressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            search ();
        }
    }//GEN-LAST:event_get_product_nameKeyPressed
    
    void show_due(){
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
           String Query =("SELECT id AS ID, Username AS 'Customer Name',Total_Price AS 'Total Price',Paid,Total_Price-Paid AS Due, Issuer , Shipping ,Date FROM sold where Payment='DUE' ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }
    }
    
    void show_expense(){
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
           String Query =("SELECT id AS ID,bill_no AS 'Bill No',company AS 'Company',reciver AS 'Reciver Name',ammount AS 'Ammount',issuer AS 'Issuer', type AS Type ,description AS 'Description',date AS 'Date' FROM expense ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
         }
    }
    private void sold_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sold_filterActionPerformed
        //Sold Report Product Report Refill Report
        String selecteditem = String.valueOf(sold_filter.getSelectedItem());
        if(selecteditem.equals("Sold Report")){
             try{
                String Query =("SELECT id AS ID,Username AS 'Customer Name' , Product_Cost AS 'Product Cost' ,Total_Price AS 'Total Price' , Vat , Due , Discount , Others , Paid ,Payment,Issuer , Shipping ,Date FROM sold ORDER BY id DESC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
              }
              catch(Exception e){
                  System.out.println("Error While Connecting : "+e);
              }
        }
        else if(selecteditem.equals("Product Report")){
            try{
                String Query =("SELECT id AS ID , Username AS 'Customer Name' , Product , Quantity , Price , Total_Price AS 'Total Price' , Payment , Issuer , Date , Custom_ID  FROM sold_report  ORDER BY id DESC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                  System.out.println("Error While Connecting : "+e);
             }
        }
         else if(selecteditem.equals("Refill Report")){
           refill_product();
        }
         //Sold Report (Paid) Sold Report (Due)
        else if(selecteditem.equals("Sold Report (Paid)")){
            try{
                String Query =("SELECT id AS ID,Username AS 'Customer Name',Total_Price AS 'Total Price' , Paid ,Issuer , Shipping ,Date FROM sold WHERE payment='PAID' ORDER BY id DESC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
              }
              catch(Exception e){
                  System.out.println("Error Whiggggle Connecting : "+e);
              }
        }        
        else if(selecteditem.equals("Expense Report")){
            show_expense();
        }
        else if(selecteditem.equals("Due Report")){
            show_due();
        }
        else if(selecteditem.equals("Due Report (Paid)")){
            try{
                String Query =("SELECT id AS ID, Username , Total_Due , Paid,Due , Issuer , Date FROM due_report ORDER BY id DESC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                  System.out.println("Error While Connecting : "+e);
             }
        }
        else if(selecteditem.equals("Sold | Most Popular")){
            try{
                String Query =("SELECT Product , COUNT(Quantity) AS 'Sold Times' , SUM(Quantity) AS Quantity , AVG(Price) AS 'Avarage Price' , SUM(Total_Price) AS 'Total Price' FROM sold_report GROUP BY Product ORDER BY Quantity DESC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                  System.out.println("Error While Connecting : "+e);
             }
        }
        else if(selecteditem.equals("Sold | Less Popular")){
            try{
                String Query =("SELECT Product , COUNT(Quantity) AS 'Sold Times' , SUM(Quantity) AS Quantity , AVG(Price) AS 'Avarage Price' , SUM(Total_Price) AS 'Total Price' FROM sold_report GROUP BY Product ORDER BY Quantity ASC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                  System.out.println("Error While Connecting : "+e);
             }
        }
    }//GEN-LAST:event_sold_filterActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String selecteditem = String.valueOf(sold_filter.getSelectedItem());
        //Expense Report
        if(selecteditem.equals("Expense Report")){
            try{
                 String Query ="SELECT * FROM settings";           
                 PreparedStatement statement = db.connect().prepareStatement(Query);
                 ResultSet rs =statement.executeQuery(Query);
                 if(rs.next()){
                     String Company=rs.getString("company_info");
                     MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                     MessageFormat header = new MessageFormat("Expense Report");
                     try {
                         products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                     } catch (java.awt.print.PrinterAbortException e) {
                     } catch (PrinterException ex) {
                         
                     }
                 }
               }
               catch(Exception e){
                   e.printStackTrace();
               }
        }
        //Print Sod Report     
        else if(selecteditem.equals("Sold Report")){                   
            try{
                String Query ="SELECT * FROM settings";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){
                    String Company=rs.getString("company_info");

                    MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                    MessageFormat header = new MessageFormat("Sold Report");
                    try {
                        products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                    } catch (java.awt.print.PrinterAbortException e) {
                    } catch (PrinterException ex) {
                        Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
            catch(Exception e){
                e.printStackTrace();
            }              
        }
        //Print Due Report      
        else if(selecteditem.equals("Due Report")){                  
            try{
                 String Query ="SELECT * FROM settings";           
                 PreparedStatement statement = db.connect().prepareStatement(Query);
                 ResultSet rs =statement.executeQuery(Query);
                 if(rs.next()){
                     String Company=rs.getString("company_info");
                     LocalDate localDate = LocalDate.now();//For reference
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                     String Date = localDate.format(formatter);        
                     MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                     MessageFormat header = new MessageFormat("Due Report of all Products");
                     try {
                         products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                     } catch (java.awt.print.PrinterAbortException e) {
                     } catch (PrinterException ex) {
                         Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void sold_report_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sold_report_detailsMouseClicked

    }//GEN-LAST:event_sold_report_detailsMouseClicked

    private void productsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMousePressed
        //Pressed Jtable
         String selecteditem = String.valueOf(sold_filter.getSelectedItem());
        if(selecteditem.equals("Sold Report")){
            try{
                String Query1 ="SELECT * FROM settings";           
                PreparedStatement statement = db.connect().prepareStatement(Query1);
                ResultSet rs =statement.executeQuery(Query1);
                if(rs.next()){
                    String Details=rs.getString("show_details"); 
                    if(Details.equals("Enabled")){
                        try{
                            int row = products.getSelectedRow();
                            String Custom_ID = (products.getModel().getValueAt(row,0).toString());                                 
                            try{
                                String Query =("SELECT id AS ID , Username AS 'Customer' , Product , Quantity , Total_Price AS 'Total Price' , Date  FROM sold_report WHERE Custom_ID = '"+Custom_ID+"'  ORDER BY id DESC");
                                PreparedStatement ps2 = db.connect().prepareStatement(Query);
                                ResultSet rs2 =ps2.executeQuery(Query);
                                sold_report_details.setModel(DbUtils.resultSetToTableModel(rs2));

                                //sold_report_detaiils.setLocationRelativeTo(null);//for shoeing this in center
                                sold_report_details.setShowGrid(true); 
                                sold_report_details.setDefaultEditor(Object.class, null);
                                sold_report_details.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
                                sold_report_detaiils.setTitle("Sold Report Details");
                                sold_report_detaiils.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
                                sold_report_detaiils.setVisible(true);
                             }
                             catch(Exception e){
                                  System.out.println("Error While Connecting : "+e);
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
        }
        else{
            //Do Nothing
        }         
    }//GEN-LAST:event_productsMousePressed

    private void sold_report_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sold_report_buttonActionPerformed
        // Pie Chart
        chart.setVisible(true);
        chart.setLocationRelativeTo(null);//for shoeing this in center
        chart.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));

        //Default Chart
        // Line Chart
        try {
            XYSeriesCollection dataset = new XYSeriesCollection();

            String Query = "SELECT * FROM sold_report ORDER BY id DESC LIMIT 50";
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            XYSeries series1 = new XYSeries("Quantity");
            XYSeries series2 = new XYSeries("Selling Price");
            XYSeries series3 = new XYSeries("Purchase Price");
            while (rs.next()) {
                series1.add(
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Quantity" ))
                );
                series2.add(
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Price" ))
                );
                series3.add(
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "purchase_price" ))
                );
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);

            JFreeChart chart = ChartFactory.createXYLineChart("Sold Products Report","Products","Price & Quantity",dataset,PlotOrientation.VERTICAL,true,true,false);

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
    }//GEN-LAST:event_sold_report_buttonActionPerformed

    
    private void chartWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chartWindowActivated
        //Chart Onload

    }//GEN-LAST:event_chartWindowActivated

    private void chartWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chartWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_chartWindowOpened

    private void due_chartWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_due_chartWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_due_chartWindowActivated

    private void due_chartWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_due_chartWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_due_chartWindowOpened

    private void due_report_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_due_report_buttonActionPerformed
        // Line Chart
        due_chart.setVisible(true);
        due_chart.setLocationRelativeTo(null);//for shoeing this in center
        due_chart.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));

        //Default Chart
        // Line Chart
        try {
            XYSeriesCollection dataset = new XYSeriesCollection();

            String Query = "SELECT * FROM sold ORDER BY id DESC LIMIT 50";
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            XYSeries series2 = new XYSeries("Paid");
            XYSeries series3 = new XYSeries("Due");
            while (rs.next()) {                
                series2.add(
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Total_Price" ))
                );
                series3.add(
                    Double.parseDouble( rs.getString( "id" )),
                    Double.parseDouble( rs.getString( "Paid" ))
                );
            }
            dataset.addSeries(series2);
            dataset.addSeries(series3);

            JFreeChart chart = ChartFactory.createXYLineChart("Payments Report","Products","Price",dataset,PlotOrientation.VERTICAL,true,true,false);

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
            chart_panel_due.add(panel);

        } catch (SQLException e) {

        }
    }//GEN-LAST:event_due_report_buttonActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Print Button Code             
          try{
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                String Company=rs.getString("company_info");
                            
                MessageFormat footer = new MessageFormat(""+Company+" - "+Date);     
                MessageFormat header = new MessageFormat("Have to Edit");
                try {
                    sold_report_details.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                } catch (java.awt.print.PrinterAbortException e) {
                } catch (PrinterException ex) {
                    Logger.getLogger(stock_report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          }
          catch(Exception e){
              e.printStackTrace();
          }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Exit Page
        sold_report_detaiils.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(sold_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sold_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sold_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sold_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new sold_report().setVisible(true);
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
    private javax.swing.JPanel chart_panel_due;
    private javax.swing.JFrame due_chart;
    private javax.swing.JButton due_report_button;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JPanel sidebar;
    private javax.swing.JComboBox<String> sold_filter;
    private javax.swing.JButton sold_report_button;
    private javax.swing.JFrame sold_report_detaiils;
    private javax.swing.JTable sold_report_details;
    private com.toedter.calendar.JDateChooser to;
    private javax.swing.JLabel total_price;
    private javax.swing.JLabel total_products;
    private javax.swing.JLabel total_refill;
    private javax.swing.JLabel total_refill_quantity_sum;
    private javax.swing.JLabel total_refill_sum;
    private javax.swing.JLabel total_refill_sum_today;
    // End of variables declaration//GEN-END:variables
}
