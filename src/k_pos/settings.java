/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;


import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Najmul
 */
public class settings extends javax.swing.JFrame {
    db_connect db = new db_connect();
    
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
    String Date = now.format(date);  
    DateTimeFormatter time = DateTimeFormatter.ofPattern("_HH-mm");	
    String Time = now.format(time);  

    
    /**
     * Creates new form homepage
     */
    public settings() {
        full_screen();   
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon(); 
        exit();
        theme();
    }
  
    String path="Backup/"+Date+Time+".sql";
    String filename;
      

    private void seticon(){
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
        jPanel2.setBackground(content_color);   
        jPanel4.setBackground(content_color);   
        jPanel3.setBackground(content_color);  
        jPanel5.setBackground(content_color);   
        
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
    


    void calc() throws IOException{
        Runtime run = Runtime.getRuntime();
        for (int i = 0; i < 1; i++){
            run.exec("calc");
            i = i++;
            if (i == 2){
                break;
            }
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
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        colorx = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        expire_month = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        product_details = new javax.swing.JComboBox<>();
        success = new javax.swing.JLabel();
        nop = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        prompt_before_exit = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        full_screen = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        company = new javax.swing.JTextField();
        mobile = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        address = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        company_info = new javax.swing.JTextArea();
        notification = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        backup_success = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        help = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        backup_email = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        backup_timing = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        pc_backup = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        email_backup = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        smtp_email_host = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        email_login = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        password_login = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        stock_products = new javax.swing.JCheckBox();
        customers = new javax.swing.JCheckBox();
        expense = new javax.swing.JCheckBox();
        oel = new javax.swing.JCheckBox();
        assets = new javax.swing.JCheckBox();
        sold_report_product = new javax.swing.JCheckBox();
        sold_product = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        select_all = new javax.swing.JCheckBox();
        delete_notification = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        shipping_show = new javax.swing.JTextField();
        success_adv = new javax.swing.JLabel();
        colorx1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
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
        setTitle("Settings");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1212, 626));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
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

        jButton9.setBackground(new java.awt.Color(34, 49, 63));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("History");
        jButton9.setBorder(null);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(34, 49, 63));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Help");
        jButton10.setBorder(null);
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("Footer Text");

        jButton11.setBackground(new java.awt.Color(34, 49, 63));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Employees");
        jButton11.setBorder(null);
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(34, 49, 63));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Suppliers");
        jButton12.setBorder(null);
        jButton12.setFocusable(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(footer)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setAlignmentX(2.0F);
        jTabbedPane1.setAlignmentY(2.0F);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(150, 35));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(150, 150));
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/color.png"))); // NOI18N
        jLabel1.setText("Change Theme");

        colorx.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        colorx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "White", "Red", "Pink", "Blue", "Yellow", "Green", "Default" }));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/save.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel2.setText("Expire Alert Before Months");
        jLabel2.setToolTipText("Show Expire Alert Before Number of Month");

        expire_month.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expire_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        expire_month.setToolTipText("Show Expire Alert Before Number of Month");
        expire_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expire_monthActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel3.setText("Product Details in Stock Report");

        product_details.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_details.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enabled", "Disabled" }));

        nop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20", "50", "100", "150", "200", "300", "400", "500", "800", "1000" }));
        nop.setToolTipText("Number of Product to Show in Report Per Page");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel5.setText("Number of Product Show");
        jLabel5.setToolTipText("Number of Product to Show in Report Per Page");

        prompt_before_exit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prompt_before_exit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel12.setText("Prompt Before Exit");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel20.setText("Full Screen Application");

        full_screen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        full_screen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel23.setText("Basic Settings & Themes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(full_screen, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colorx, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(product_details, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expire_month, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nop, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prompt_before_exit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(success, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel23)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(colorx, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(success, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expire_month, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(product_details, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nop, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prompt_before_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(full_screen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 186, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Basic Settings  ", jPanel2);

        username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(51, 51, 51));
        username.setToolTipText("Username");
        username.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        company.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        company.setForeground(new java.awt.Color(51, 51, 51));
        company.setToolTipText(" Company Name");
        company.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));

        mobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mobile.setForeground(new java.awt.Color(51, 51, 51));
        mobile.setToolTipText(" Mobile");
        mobile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/save.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        address.setForeground(new java.awt.Color(51, 51, 51));
        address.setToolTipText(" Address");
        address.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));

        company_info.setColumns(20);
        company_info.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        company_info.setForeground(new java.awt.Color(51, 51, 51));
        company_info.setRows(5);
        company_info.setToolTipText("  Company Info");
        company_info.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        jScrollPane1.setViewportView(company_info);

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setForeground(new java.awt.Color(255, 51, 0));
        jLabel14.setText("*");

        jLabel16.setForeground(new java.awt.Color(255, 51, 0));
        jLabel16.setText("*");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel21.setText("User and Company Info");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Username");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Company");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Mobile");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Address");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Company Info");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jLabel22.setText("* Marked Input's are used in Print Page");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7))
                                        .addGap(37, 37, 37)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(address, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(mobile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(company, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(notification, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(notification, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(company, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  User & Company Info ", jPanel4);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel4.setText("Database Backup & Restore");

        backup_success.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText(" Select Directory to Save Backup File");
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        help.setText("Help");

        jTextField2.setEditable(false);
        jTextField2.setForeground(new java.awt.Color(102, 102, 102));
        jTextField2.setText(" Select SQL File to Restore Backup File");
        jTextField2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Browse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Restore");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Backup");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel15.setText("Email & Backup");

        backup_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backup_email.setToolTipText("Backup Email");
        backup_email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        backup_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                backup_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                backup_emailFocusLost(evt);
            }
        });
        backup_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backup_emailActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel17.setText("Backup Email Address");
        jLabel17.setToolTipText("Show Expire Alert Before Number of Month");

        backup_timing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backup_timing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Every Day", "Every Week", "Every Month" }));
        backup_timing.setToolTipText("Backup Timing");
        backup_timing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backup_timingActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel25.setText("Backup Timing");
        jLabel25.setToolTipText("Show Expire Alert Before Number of Month");

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/save.png"))); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel26.setText("Email Backup");
        jLabel26.setToolTipText("Show Expire Alert Before Number of Month");

        pc_backup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pc_backup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        pc_backup.setToolTipText("Backup PC");
        pc_backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pc_backupActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel27.setText("Automatic PC Backup");
        jLabel27.setToolTipText("Show Expire Alert Before Number of Month");

        email_backup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email_backup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        email_backup.setToolTipText("Backup Email");
        email_backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_backupActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        smtp_email_host.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        smtp_email_host.setToolTipText("Backup Email");
        smtp_email_host.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        smtp_email_host.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                smtp_email_hostFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                smtp_email_hostFocusLost(evt);
            }
        });
        smtp_email_host.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smtp_email_hostActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel30.setText("SMTP Email Host");
        jLabel30.setToolTipText("Show Expire Alert Before Number of Month");

        email_login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email_login.setToolTipText("Backup Email");
        email_login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        email_login.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                email_loginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                email_loginFocusLost(evt);
            }
        });
        email_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_loginActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel31.setText("Email & Password ");
        jLabel31.setToolTipText("Show Expire Alert Before Number of Month");

        password_login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password_login.setToolTipText("Backup Email");
        password_login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        password_login.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password_loginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_loginFocusLost(evt);
            }
        });
        password_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_loginActionPerformed(evt);
            }
        });

        jLabel32.setText("SMTP : smtp.gmail.com or smtp.mail.yahoo.com");

        jLabel33.setText("In Email and Password Field Use Email and Password");

        jLabel34.setText("For Email Backup PC Backup Must be Enabled");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addComponent(backup_success, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(email_login, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(password_login))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pc_backup, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(email_backup, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton16)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(backup_timing, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(smtp_email_host, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(backup_success, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(email_backup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(pc_backup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backup_timing, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email_login, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password_login, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(smtp_email_host, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Backup and Restore  ", jPanel3);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        jLabel18.setText("Make Sure to  Backup Your Data Before Deleting.");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/delete.png"))); // NOI18N
        jLabel19.setText("Delete Data From Database ");

        stock_products.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stock_products.setText("Stock Products");

        customers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customers.setText("Customers + Due");

        expense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expense.setText("Expenses");
        expense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseActionPerformed(evt);
            }
        });

        oel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        oel.setText("Owner's Equity and Liabilities");

        assets.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assets.setText("Assets");
        assets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assetsActionPerformed(evt);
            }
        });

        sold_report_product.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sold_report_product.setText("Sold Report (Product)");

        sold_product.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sold_product.setText("Sold Report");

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/delete.png"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        select_all.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        select_all.setText("Select All");
        select_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_allActionPerformed(evt);
            }
        });

        delete_notification.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oel)
                    .addComponent(assets)
                    .addComponent(sold_report_product)
                    .addComponent(sold_product)
                    .addComponent(customers)
                    .addComponent(expense)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(stock_products)
                        .addGap(103, 103, 103)
                        .addComponent(select_all)
                        .addGap(47, 47, 47)
                        .addComponent(delete_notification, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19)
                    .addComponent(jButton7))
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete_notification, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stock_products)
                        .addComponent(select_all)))
                .addGap(10, 10, 10)
                .addComponent(expense)
                .addGap(10, 10, 10)
                .addComponent(customers)
                .addGap(10, 10, 10)
                .addComponent(sold_product)
                .addGap(10, 10, 10)
                .addComponent(sold_report_product)
                .addGap(10, 10, 10)
                .addComponent(assets)
                .addGap(10, 10, 10)
                .addComponent(oel)
                .addGap(20, 20, 20)
                .addComponent(jButton7)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Delete Data  ", jPanel5);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel24.setText("Advance Settings");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel11.setText("Shipping Fee");
        jLabel11.setToolTipText("Show Expire Alert Before Number of Month");

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/save.png"))); // NOI18N
        jButton13.setText("Save");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        shipping_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        shipping_show.setToolTipText("Shipping Fee");
        shipping_show.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        shipping_show.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                shipping_showFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                shipping_showFocusLost(evt);
            }
        });
        shipping_show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shipping_showActionPerformed(evt);
            }
        });

        colorx1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        colorx1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jLabel28.setText("Automatic Update");
        jLabel28.setToolTipText("Show Expire Alert Before Number of Month");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jLabel29.setText("Update Settings");

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/up.png"))); // NOI18N
        jButton15.setText("Check for Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorx1, 0, 83, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(shipping_show, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(success_adv, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel24)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shipping_show, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(success_adv, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(colorx1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Advance Settings  ", jPanel1);

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
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        homepage h = new homepage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        history lh = new history();
        lh.setVisible(true);
        this.dispose();       
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Color = (String) colorx.getSelectedItem();   
        String Expire_Date = (String) expire_month.getSelectedItem();     
        String Porduct_Details = (String) product_details.getSelectedItem();   
        String Number_of_Product = (String) nop.getSelectedItem();  
        String Prompt_Before_Exit = (String) prompt_before_exit.getSelectedItem();  
        String Full_Screen = (String) full_screen.getSelectedItem(); 
        
        try{               
            PreparedStatement pst = db.connect().prepareStatement("UPDATE settings SET background=? , expire_month=? , show_details=? , number_of_products=? , exit_on_close=? , full_screen=?");
            pst.setString(1, Color);    
            pst.setString(2, Expire_Date);    
            pst.setString(3, Porduct_Details);    
            pst.setString(4, Number_of_Product);
            pst.setString(5, Prompt_Before_Exit);    
            pst.setString(6, Full_Screen);   
            pst.executeUpdate(); 
            success.setText("Success");
            theme();
        }
        catch(Exception e){
            System.out.println("Error Connecting Database. "+e);
        }     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //Add User
        input_employee ie = new input_employee();
        ie.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //Windows Activated Events
        try{
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                
                //Basic Settings
                String Color=rs.getString("background"); 
                colorx.setSelectedItem(Color);
                
                String Months=rs.getString("expire_month"); 
                expire_month.setSelectedItem(Months);        
                
                String Product_Details=rs.getString("show_details"); 
                product_details.setSelectedItem(Product_Details);     
                
                String Number_of_Product=rs.getString("number_of_products"); 
                nop.setSelectedItem(Number_of_Product);    
                
                String Prompt_Before_Exit =rs.getString("exit_on_close"); 
                prompt_before_exit.setSelectedItem(Prompt_Before_Exit);     
                
                
                //User and Company Info
                String Username =rs.getString("username"); 
                username.setText(Username);    
                
                String Company =rs.getString("company"); 
                company.setText(Company);       
                
                String Mobile =rs.getString("mobile"); 
                mobile.setText(Mobile);       
                
                String Address =rs.getString("address"); 
                address.setText(Address);       
                
                String Company_Info =rs.getString("company_info"); 
                company_info.setText(Company_Info);   
                
                String Full_Screen =rs.getString("full_screen"); 
                full_screen.setSelectedItem(Full_Screen);     
                
                
                //Show Shipping Fee
                String Shipping_Fee =rs.getString("Shipping_Cost"); 
                shipping_show.setText(Shipping_Fee);   
                
                
                
                 //Email & PC Backup
                String PC_Backup =rs.getString("PC_Backup"); 
                pc_backup.setSelectedItem(PC_Backup);     
                
                String Email_Backup =rs.getString("Email_Backup"); 
                email_backup.setSelectedItem(Email_Backup);    
                
                String Backup_Email =rs.getString("Backup_Email"); 
                backup_email.setText(Backup_Email);   
                
                String SMTP_HOST =rs.getString("SMTP_HOST"); 
                smtp_email_host.setText(SMTP_HOST);   
                
                String Email_Login =rs.getString("Email_Login"); 
                email_login.setText(Email_Login);  
                
                String Pass_Login =rs.getString("Pass_Login"); 
                password_login.setText(Pass_Login);  
                
                String Backup_Timing =rs.getString("Backup_Timing"); 
                backup_timing.setSelectedItem(Backup_Timing);    
                
                
            }
        }
        catch(Exception e){
              System.out.println(e);
        }
    }//GEN-LAST:event_formWindowActivated

    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       //Backup Button
       Process p=null;
        try {
            db_connect db=new db_connect();
            String Db_Name = db.DATABASE_NAME;
            Runtime runtime = Runtime.getRuntime();
            p=runtime.exec("C:/xampp/mysql/bin/mysqldump.exe -uroot --add-drop-database -B "+Db_Name+" -r"+path);
            
            int processComplete = p.waitFor();
            if (processComplete==0) {
                backup_success.setText("Backup Successfully Created");
            }else{
                backup_success.setText("Can't Create backup");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Select Backup Directory
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        try {
            File f =fc.getSelectedFile();
            path = f.getAbsolutePath();
            path = path.replace('\\', '/');
            path = path + "_" + date + ".sql";
            jTextField1.setText(path);
            
        } catch (Exception e) {
            System.out.println("User Cancel The Request !");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Browse Restore SQL File
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            File f = fc.getSelectedFile();
            path = f.getAbsolutePath();
            path = path.replace('\\', '/');
            
            jTextField2.setText(path);
        } catch (Exception e) {
            System.out.println("User Cancel The Request !");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Restore Button
        String dbUserName = "root";// username
        String dbPassword = "";//Password
        
        String[] restoreCmd = new String[]{"C:/xampp/mysql/bin/mysql.exe ", "--user=" + dbUserName, "--password=", "-e", "source " + path};
        Process runtimProcess;
        try {
            runtimProcess = Runtime.getRuntime().exec(restoreCmd);
            int proceCom = runtimProcess.waitFor();            
            if (proceCom==0) {
                backup_success.setText("Restore Succussed");
            }else{
                backup_success.setText("Can't Restore");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Settings User Info
        String Username = username.getText();   
        String Company = company.getText();     
        String Mobile = mobile.getText();     
        String Address = address.getText();     
        String Company_Info = company_info.getText();     
        
        get_settings s1=new get_settings();
        String Issuer = s1.noorder;

        if(Username.isEmpty() || Company.isEmpty() || Mobile.isEmpty() || Address.isEmpty() || Company_Info.isEmpty()){
            notification.setText("Fill All the Input Field.");             
        }
        else{
            try{         
                PreparedStatement pst = db.connect().prepareStatement("UPDATE settings SET username=?,company=?,mobile=?,address=?,company_info=?,issuer=?");
                pst.setString(1, Username);
                pst.setString(2, Company);
                pst.setString(3, Mobile);
                pst.setString(4, Address);
                pst.setString(5, Company_Info);
                pst.setString(6, Issuer);
                pst.executeUpdate(); 
                notification.setText("Details Updated !");          
            }
            catch(Exception e){
                System.out.println("Error Connecting Database. "+e);
            }            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void expenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expenseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expenseActionPerformed

    private void assetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assetsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assetsActionPerformed

    private void select_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_allActionPerformed
        // Select All Button
        if(select_all.isSelected()){
            stock_products.setSelected(true);
            expense.setSelected(true);
            customers.setSelected(true);
            sold_product.setSelected(true);
            sold_report_product.setSelected(true);
            assets.setSelected(true);
            oel.setSelected(true);
        }
        else if(!select_all.isSelected()){
            stock_products.setSelected(false);
            sold_product.setSelected(false);
            expense.setSelected(false);
            customers.setSelected(false);
            sold_report_product.setSelected(false);
            assets.setSelected(false);
            oel.setSelected(false);             
        }
    }//GEN-LAST:event_select_allActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Empty Table From Settings
         int choose = JOptionPane.showConfirmDialog(null,"Are You Sure You Want to Delete Those Files ?","Confirm Close", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if (choose == JOptionPane.YES_OPTION) {
            try{
               //Empty Table From Database
               if(stock_products.isSelected()){                
                    String Query ="TRUNCATE TABLE products";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }
                if(expense.isSelected()){
                    String Query ="TRUNCATE TABLE expense";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }  
                if(customers.isSelected()){
                    String Query ="TRUNCATE TABLE customers";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }  
                if(sold_report_product.isSelected()){
                    String Query ="TRUNCATE TABLE sold_report";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }  
                if(sold_product.isSelected()){
                    String Query ="TRUNCATE TABLE sold";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }   
                if(assets.isSelected()){
                    String Query ="TRUNCATE TABLE assets";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }   
                if(oel.isSelected()){
                    String Query ="TRUNCATE TABLE liability_equity";
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    int rs =statement.executeUpdate(Query);
                }
                else{
                    delete_notification.setText("Product Not Deleted !");
                }
               delete_notification.setText("Successfully Deleted !");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } else {
            //Do Nothing !
        }         
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // Suppliers
        input_supplier s = new input_supplier();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

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

    private void expire_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expire_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expire_monthActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        //Settings User Info
        String Shipping_Fee = shipping_show.getText();   
        
        get_settings s1=new get_settings();
        String Issuer = s1.noorder;

        if(Shipping_Fee.isEmpty()){
            success_adv.setText("Fill All the Input Field.");             
        }
        else{
            try{         
                PreparedStatement pst = db.connect().prepareStatement("UPDATE settings SET Shipping_Cost=?");
                pst.setString(1, Shipping_Fee);
                pst.executeUpdate(); 
                success_adv.setText("Details Updated !");          
            }
            catch(Exception e){
                success_adv.setText("Error. Try Again !");     
                System.out.println("Error Connecting Database. "+e);
            }            
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void shipping_showFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_shipping_showFocusGained
        
    }//GEN-LAST:event_shipping_showFocusGained

    private void shipping_showFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_shipping_showFocusLost
        
    }//GEN-LAST:event_shipping_showFocusLost

    private void shipping_showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shipping_showActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shipping_showActionPerformed

    private void backup_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_backup_emailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_backup_emailFocusGained

    private void backup_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_backup_emailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_backup_emailFocusLost

    private void backup_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backup_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backup_emailActionPerformed

    private void backup_timingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backup_timingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backup_timingActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        //Settings Email Settings
        String PC_Backup = (String) pc_backup.getSelectedItem();   
        String Email_Backup = (String) email_backup.getSelectedItem();   
        String Backup_Email = backup_email.getText();   
        String Backup_Timing = (String) backup_timing.getSelectedItem();   
        String SMTP_HOST = smtp_email_host.getText();   
        String Email_Login = email_login.getText();   
        String Email_Password = password_login.getText();   

        
        if(Backup_Email.isEmpty()){
            backup_success.setText("Fill Backup Email Input Field.");              
        }
        else{
            try{         
                PreparedStatement pst = db.connect().prepareStatement("UPDATE settings SET PC_Backup=? ,Email_Backup=? ,Backup_Email=?, Backup_Timing=? , SMTP_HOST=? , Email_Login=? , Pass_Login=?");
                pst.setString(1, PC_Backup); 
                pst.setString(2, Email_Backup);
                pst.setString(3, Backup_Email);
                pst.setString(4, Backup_Timing);
                pst.setString(5, SMTP_HOST);
                pst.setString(6, Email_Login);
                pst.setString(7, Email_Password);
                pst.executeUpdate(); 
                backup_success.setText("Details Updated !");          
            }
            catch(Exception e){
                backup_success.setText("Error. Try Again !");     
                System.out.println("Error Connecting Database. "+e);
            }            
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void pc_backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pc_backupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pc_backupActionPerformed

    private void email_backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_backupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_backupActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
              
    }//GEN-LAST:event_jButton15ActionPerformed

    public void email_backup(String Email , String Password , String From , String To , String SMTP){
        //authentication info                 
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", ""+SMTP+"");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Email,Password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(From));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
                msg.setSubject("K-POS Email Backup !");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Download The Backup File From Below.");

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("Backup/Automatic/"+Date+".sql");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
            
        } catch (Exception e) {
              System.out.println("Error ! "+e);
        } 
    }
    
    
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       try{         
            String Query ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                //Mail Settings
                String Email_Login =rs.getString("Email_Login");
                String Pass_Login =rs.getString("Pass_Login");
                String Backup_Email =rs.getString("Backup_Email"); 
                String SMTP_HOST =rs.getString("SMTP_HOST");       
                //Send Email
                //username password from to smtp
               //email_backup("najmulamin45@yahoo.com" , "AFSW3Rsfs322@" , "najmulamin45@yahoo.com" , "najmulamin45@gmail.com" ,"smtp.mail.yahoo.com");
                email_backup(""+Email_Login+"" , ""+Pass_Login+"" , ""+Email_Login+"" ,  ""+Backup_Email+"" , ""+SMTP_HOST+"");
                backup_success.setText("Backup Sucessful.");
            }
       }
       catch(Exception e){
           backup_success.setText("Error !");
           System.out.println("Error !");
       }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void smtp_email_hostFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_smtp_email_hostFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_smtp_email_hostFocusGained

    private void smtp_email_hostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_smtp_email_hostFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_smtp_email_hostFocusLost

    private void smtp_email_hostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smtp_email_hostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smtp_email_hostActionPerformed

    private void email_loginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_loginFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_email_loginFocusGained

    private void email_loginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_loginFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_email_loginFocusLost

    private void email_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_loginActionPerformed

    private void password_loginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_loginFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_password_loginFocusGained

    private void password_loginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_loginFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_password_loginFocusLost

    private void password_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_loginActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //Help Page
        help h = new help();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new settings().setVisible(true);
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
    private javax.swing.JTextField address;
    private javax.swing.JCheckBox assets;
    private javax.swing.JTextField backup_email;
    private javax.swing.JLabel backup_success;
    private javax.swing.JComboBox<String> backup_timing;
    private javax.swing.JComboBox<String> colorx;
    private javax.swing.JComboBox<String> colorx1;
    private javax.swing.JTextField company;
    private javax.swing.JTextArea company_info;
    private javax.swing.JCheckBox customers;
    private javax.swing.JLabel delete_notification;
    private javax.swing.JComboBox<String> email_backup;
    private javax.swing.JTextField email_login;
    private javax.swing.JCheckBox expense;
    private javax.swing.JComboBox<String> expire_month;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private javax.swing.JComboBox<String> full_screen;
    private javax.swing.JLabel help;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField mobile;
    private javax.swing.JComboBox<String> nop;
    private javax.swing.JLabel notification;
    private javax.swing.JCheckBox oel;
    private javax.swing.JTextField password_login;
    private javax.swing.JComboBox<String> pc_backup;
    private javax.swing.JComboBox<String> product_details;
    private javax.swing.JMenu products_menu;
    private javax.swing.JComboBox<String> prompt_before_exit;
    private javax.swing.JCheckBox select_all;
    private javax.swing.JTextField shipping_show;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextField smtp_email_host;
    private javax.swing.JCheckBox sold_product;
    private javax.swing.JCheckBox sold_report_product;
    private javax.swing.JCheckBox stock_products;
    private javax.swing.JLabel success;
    private javax.swing.JLabel success_adv;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
