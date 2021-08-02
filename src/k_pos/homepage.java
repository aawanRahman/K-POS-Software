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
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Najmul
 */
public class homepage extends javax.swing.JFrame {   
    db_connect db = new db_connect();
    
    public homepage() {        
        full_screen();   
        initComponents(); 
        this.setExtendedState(MAXIMIZED_BOTH); 
        server_status();
        seticon();  
        theme();
        exit();      
    }
    
    private void seticon(){
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
        //sidebar.setBackground(sidebar_color);                    
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


    void server_status(){      
        try{
            boolean isMysqlUp = isServerUp(3306);
            String server=Boolean.toString(isMysqlUp); 
            if(isMysqlUp == true){
                server_status.setText(" |  Server Status : Online");              
            }
            else{
                server_status.setForeground(Color.red);
                server_status.setText(" |  Server Status : Offline");    
            }                 
         }
         catch(Exception e){
             System.out.println("Error Checking Server : "+e);
         }    
    }
    
    
    public boolean isServerUp(int port) {   
        boolean isUp = false;
        try {
            Socket socket = new Socket("127.0.0.1", port);
            // Server is up
            isUp = true;
            socket.close();
        }
        catch (IOException e){
            // Server is down
        }
        return isUp;
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        customers = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        customers1 = new javax.swing.JButton();
        customers2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        calc = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        server_status = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        user = new javax.swing.JLabel();
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
        setTitle("Dashboard - K-POS");
        setMinimumSize(new java.awt.Dimension(1202, 646));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        sidebar.setBackground(new java.awt.Color(34, 49, 63));

        jButton2.setBackground(new java.awt.Color(34, 49, 63));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Logout");
        jButton2.setBorder(null);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(34, 49, 63));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Settings");
        jButton3.setBorder(null);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(34, 49, 63));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Main Menu");
        jButton8.setBorder(null);
        jButton8.setFocusable(false);
        jButton8.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        customers.setBackground(new java.awt.Color(34, 49, 63));
        customers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        customers.setForeground(new java.awt.Color(255, 255, 255));
        customers.setText("Customers");
        customers.setBorder(null);
        customers.setFocusable(false);
        customers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customersActionPerformed(evt);
            }
        });

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("@Najmul Amin 2019");
        footer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                footerMouseClicked(evt);
            }
        });

        customers1.setBackground(new java.awt.Color(34, 49, 63));
        customers1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        customers1.setForeground(new java.awt.Color(255, 255, 255));
        customers1.setText("Tasks");
        customers1.setBorder(null);
        customers1.setFocusable(false);
        customers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customers1ActionPerformed(evt);
            }
        });

        customers2.setBackground(new java.awt.Color(34, 49, 63));
        customers2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        customers2.setForeground(new java.awt.Color(255, 255, 255));
        customers2.setText("Shippings");
        customers2.setBorder(null);
        customers2.setFocusable(false);
        customers2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customers2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customers2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customers1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(footer)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customers, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(customers, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(customers2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(customers1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/input_product.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        calc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/calc.png"))); // NOI18N
        calc.setFocusable(false);
        calc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calcMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                calcMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calcMouseReleased(evt);
            }
        });
        calc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/sell product.png"))); // NOI18N
        jButton18.setFocusable(false);
        jButton18.setPreferredSize(new java.awt.Dimension(145, 145));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/stock report.png"))); // NOI18N
        jButton19.setFocusable(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/Expance.png"))); // NOI18N
        jButton20.setFocusable(false);
        jButton20.setPreferredSize(new java.awt.Dimension(145, 145));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/status_report.png"))); // NOI18N
        jButton23.setFocusable(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/sell_total.png"))); // NOI18N
        jButton24.setFocusable(false);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/Get_due_amount.png"))); // NOI18N
        jButton25.setFocusable(false);
        jButton25.setPreferredSize(new java.awt.Dimension(145, 145));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        server_status.setText("Server Status : Online ");

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/account_report.png"))); // NOI18N
        jButton26.setFocusable(false);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        user.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/admin_icon.png"))); // NOI18N
        user.setText("User");

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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(user)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(server_status)))
                    .addComponent(calc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton26, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jButton23))
                .addGap(20, 20, 20)
                .addComponent(calc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(server_status)
                    .addComponent(user))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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
    }//GEN-LAST:event_jButton6ActionPerformed

    private void calcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcActionPerformed
        try {
            calc();               
        } catch (IOException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calcActionPerformed

    private void calcMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcMouseReleased
        
    }//GEN-LAST:event_calcMouseReleased

    private void calcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcMouseExited
         
    }//GEN-LAST:event_calcMouseExited

    private void calcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_calcMouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
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
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
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
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
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
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        status_report p = new status_report();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        //Sold Report
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
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
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
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
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
    }//GEN-LAST:event_jButton26ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //Windows Activated Events
        get_settings s1=new get_settings();       
        String Username = s1.noorder;
        user.setText("User : " +Username);        
    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        status_report p = new status_report();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        //Tasks and Goal
        tasks l = new tasks();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

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

    private void customers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customers1ActionPerformed
        //Tasks and Goal
        tasks l = new tasks();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_customers1ActionPerformed

    private void footerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_footerMouseClicked
      
    }//GEN-LAST:event_footerMouseClicked

    private void customersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customersActionPerformed
        //New Customer Page
        input_customer nc = new input_customer();
        nc.setVisible(true);
    }//GEN-LAST:event_customersActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        automatic_pc_backup();
        login l = new login();
        l.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed
   
    public void automatic_pc_backup(){  
           try {       
               String Query="select * from settings";                
               PreparedStatement pst = db.connect().prepareStatement(Query);                                 
               ResultSet rs =pst.executeQuery();
               if(rs.next()){
                   String PC_Backup = rs.getString("PC_Backup");         
                   if(PC_Backup.equals("Yes")){
                       //Start Backup
                       LocalDate localDate = LocalDate.now();//For reference
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                       String Date = localDate.format(formatter);

                       String path="Backup/Automatic/"+Date+".sql";
                       Process p=null;
                       try {
                           db_connect db=new db_connect();
                           String Db_Name = db.DATABASE_NAME;
                           Runtime runtime = Runtime.getRuntime();
                           p=runtime.exec("C:/xampp/mysql/bin/mysqldump.exe -uroot --add-drop-database -B "+Db_Name+" -r"+path);

                           int processComplete = p.waitFor();
                           if (processComplete==0) {
                               System.out.println("Backup Successfully Created");
                           }else{
                               System.out.println("Can't Create backup");
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
           catch(Exception e){

           }
       }
    
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //About Jpanel
        about ab = new about();
        ab.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        //Help Page
        help h = new help();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        //Exit Button From Menu
        this.dispose();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void customers2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customers2ActionPerformed
        //Shippings Page
        shippings h = new shippings();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_customers2ActionPerformed

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
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homepage().setVisible(true);
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
    private javax.swing.JButton calc;
    private javax.swing.JButton customers;
    private javax.swing.JButton customers1;
    private javax.swing.JButton customers2;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JMenu products_menu;
    private javax.swing.JLabel server_status;
    private javax.swing.JPanel sidebar;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
