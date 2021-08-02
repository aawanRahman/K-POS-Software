/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Najmul
 */
public class account_report extends javax.swing.JFrame {
    db_connect db = new db_connect();
    
    /**
     * Creates new form homepage
     */
    public account_report() {
        full_screen();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon(); 
        theme();
        cash_in_hand();
        total_assets();
        total_assets_ammount();
        total_assets_quantity();
        exit();
        products.setDefaultEditor(Object.class, null);   
        products.getTableHeader().setEnabled(false);
        //products.setShowGrid(true);
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
    
    public double total_assets(){
        double value=0.0;
        try{
           String Query = "select count(id) from assets";
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet result =statement.executeQuery(Query);
           result.next();
           String sum = result.getString(1);
           total_count_expense.setText("No of Assets : "+sum);
          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }  
     public double total_assets_quantity(){
        double value=0.0;
        try{
           PreparedStatement statement =  db.connect().prepareStatement("select IFNULL(SUM(quantity),0) from assets");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           assets_quantity.setText("Total Assets Quantity : "+sum);

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }  
       
    public double total_assets_ammount(){
       double value=0.0;
        try{
           LocalDate localDate = LocalDate.now();//For reference
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String Date = localDate.format(formatter); 
           PreparedStatement statement =  db.connect().prepareStatement("SELECT COALESCE(SUM(price), 0) FROM assets");
           ResultSet result = statement.executeQuery();
           result.next();
           String sum = result.getString(1);
           total_expense.setText("Total Assets Ammount : "+sum);

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
      return value;
  }  
    
    
   
    
    void show_assets(){
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
           String Query =("SELECT id AS ID, product_name AS 'Assets Name', quantity AS 'Quantity',price AS Ammount, issuer AS 'Issuer',date AS 'Date' FROM assets ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet result =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(result));
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

        get_product_name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        total_count_expense = new javax.swing.JLabel();
        total_expense = new javax.swing.JLabel();
        assets_quantity = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        selected_item = new javax.swing.JComboBox<>();
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
        setTitle("Account Report");
        setMinimumSize(new java.awt.Dimension(1212, 626));

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        get_product_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        get_product_name.setToolTipText("Search By Bill No or Reciver Name");
        get_product_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        get_product_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        get_product_name.setName(""); // NOI18N
        get_product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_product_nameActionPerformed(evt);
            }
        });
        get_product_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                get_product_nameKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                get_product_nameKeyPressed(evt);
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
        products.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        products.setForeground(new java.awt.Color(42, 42, 42));
        products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Total Price"
            }
        ));
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
        products.setRowHeight(25);
        products.setRowMargin(0);
        products.setSelectionBackground(new java.awt.Color(186, 186, 236));
        products.setSelectionForeground(new java.awt.Color(0, 0, 0));
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/account_report.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        total_count_expense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_count_expense.setForeground(new java.awt.Color(255, 255, 255));
        total_count_expense.setText("Total (Count) :");

        total_expense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_expense.setForeground(new java.awt.Color(255, 255, 255));
        total_expense.setText("Total (Expense) :");

        assets_quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assets_quantity.setForeground(new java.awt.Color(255, 255, 255));
        assets_quantity.setText("Total (Expense) :");

        jButton12.setBackground(new java.awt.Color(34, 49, 63));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Withdraw");
        jButton12.setBorder(null);
        jButton12.setFocusable(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(34, 49, 63));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Input Asset");
        jButton10.setBorder(null);
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(34, 49, 63));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Input L & OE");
        jButton11.setBorder(null);
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assets_quantity)
                    .addComponent(total_expense)
                    .addComponent(total_count_expense)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(footer)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_count_expense)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total_expense)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assets_quantity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("To");

        from.setToolTipText("Start Date");

        to.setToolTipText("End Date");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/print.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        selected_item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selected_item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balance Sheet", "Assets", "L + OE" }));
        selected_item.setToolTipText("Select Search Filter");
        selected_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selected_itemActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(get_product_name, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selected_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton3)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selected_item, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search(){
        String Product_Name = get_product_name.getText();
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        if(Product_Name.isEmpty()){
            show_assets();
        }
        else{
            try{
             String Query =("SELECT id AS ID, product_name AS 'Assets Name', quantity AS 'Quantity',price AS Ammount, issuer AS 'Issuer',date AS 'Date'  FROM assets WHERE product_name LIKE '%"+Product_Name+"%' OR id LIKE '%"+Product_Name+"%' ");
             PreparedStatement statement = db.connect().prepareStatement(Query);
             ResultSet result =statement.executeQuery(Query);
             products.setModel(DbUtils.resultSetToTableModel(result));
           }
           catch(Exception e){
               System.out.println("Error Whiggggle Connecting : "+e);
           }
        }
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        homepage h= new homepage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // withdraw       
        withdraw w = new withdraw();
        w.setVisible(true);
        this.dispose();      
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Search Button
        search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void get_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyPressed
        // Enter Key Pressed Search
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            search ();
        }
    }//GEN-LAST:event_get_product_nameKeyPressed

    private void get_product_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyTyped
        //Key Pressed
        search ();
    }//GEN-LAST:event_get_product_nameKeyTyped

    private void get_product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_product_nameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(from.getDate() != null &&  to.getDate() != null){
            try{
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String From = dcn.format(from.getDate());
                String To = dcn.format(to.getDate());
                
                String Query =("SELECT id AS ID, product_name AS 'Assets Name', quantity AS 'Quantity',price AS Ammount, issuer AS 'Issuer',date AS 'Date' FROM assets WHERE date >= '"+From+"' AND Date <= '"+To+"';");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet result =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(result));
            }
            catch(Exception e){
                System.out.println("Error While Connecting : "+e);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please Select Date First ?","Error", JOptionPane.QUESTION_MESSAGE);                }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Print Button Code   
        String selecteditem = String.valueOf(selected_item.getSelectedItem());
        if(selecteditem.equals("Balance Sheet")){
             try{
                String Query ="SELECT * FROM settings";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){
                    String Company=rs.getString("company_info");
                     LocalDate localDate = LocalDate.now();//For reference
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String Date = localDate.format(formatter); 
                    MessageFormat header = new MessageFormat("Balance Sheet");
                    MessageFormat footer = new MessageFormat(""+Company+" - "+Date);
                    try {
                        products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                    } catch (java.awt.print.PrinterAbortException e) {
                    } catch (PrinterException ex) {
                        Logger.getLogger(account_report.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
              catch(Exception e){
                  e.printStackTrace();
              }
        }
        else if(selecteditem.equals("Assets")){            
            try{
                String Query ="SELECT * FROM settings";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){
                    String Company=rs.getString("company_info");
                     LocalDate localDate = LocalDate.now();//For reference
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String Date = localDate.format(formatter); 
                    MessageFormat header = new MessageFormat("Assets Report");
                    MessageFormat footer = new MessageFormat(""+Company+" - "+Date);
                    try {
                        products.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                    } catch (java.awt.print.PrinterAbortException e) {
                    } catch (PrinterException ex) {
                        Logger.getLogger(account_report.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
              catch(Exception e){
                  e.printStackTrace();
              }
        }       
        else if(selecteditem.equals("L + OE")){
            
        }       
    }//GEN-LAST:event_jButton3ActionPerformed

    public void cash_in_hand(){
        
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
         try{          
            //Cash on Hand
            PreparedStatement cash_on_hand =  db.connect().prepareStatement("SELECT((SELECT IFNULL(SUM(Total_Price),0) FROM sold_report WHERE Issuer <> 'Refill')-((SELECT IFNULL(SUM(due),0) FROM customers)+(SELECT COALESCE(SUM(ammount), 0) FROM expense WHERE type = 'Due'))+(SELECT IFNULL(SUM(commission),0) FROM withdraw)-(SELECT IFNULL(SUM(withdraw),0) FROM withdraw))+(select IFNULL(SUM(price),0) from assets WHERE type = 'Cash on Hand')");
            ResultSet result = cash_on_hand.executeQuery();
            result.next();
            String sum = result.getString(1);
            double val= Math.round(Double.parseDouble(sum));           
            String Cash_on_Hand = Double.toString(val);
            String Cash_in_Hand = Double.toString(val);
            
            //Cash on Bank
            PreparedStatement cash_on_bank =  db.connect().prepareStatement("select IFNULL(SUM(price),0) from assets WHERE type='Cash on Bank' ");
            ResultSet result1 = cash_on_bank.executeQuery();
            result1.next();
            String sum_cash_on_bank = result1.getString(1);
            double val1= Math.round(Double.parseDouble(sum_cash_on_bank));           
            String Cash_on_Bank = Double.toString(val1);
            
            //Equipment
            PreparedStatement equipment =  db.connect().prepareStatement("select IFNULL(SUM(price),0) from assets WHERE type='Equipment' ");
            ResultSet result2 = equipment.executeQuery();
            result2.next();
            String sum_equipment = result2.getString(1);
            double val2= Math.round(Double.parseDouble(sum_equipment));           
            String Equipment = Double.toString(val2);
            
           //Inventory = Sum(Total Purchases Price)+Sum(Total Refill Product)
           //inventory : sum(purchases price) products + sum(refill) stock_report
           PreparedStatement total_purchase_price =  db.connect().prepareStatement("(SELECT(select IFNULL(sum(total_purchese_price),0) from products)+(select IFNULL(sum(Total_Price)*(-1),0) from sold_report WHERE Issuer = 'Refill'))");
           ResultSet result3 = total_purchase_price.executeQuery();
           result3.next();
           String sumx3 = result3.getString(1);
           double val3 = Math.round(Double.parseDouble(sumx3));           
           String Inventory = Double.toString(val3);


           //Total Due = or Account Recivable
           PreparedStatement total_due =  db.connect().prepareStatement("SELECT IFNULL(SUM(due),0) FROM customers");
           ResultSet result4 = total_due.executeQuery();
           result4.next();
           String sumx2 = result4.getString(1);
           double val4 = Math.round(Double.parseDouble(sumx2));           
           String Total_Due = Double.toString(val4);
           
            //Others
           PreparedStatement others =  db.connect().prepareStatement("select IFNULL(SUM(price),0) from assets WHERE type='Others' ");
           ResultSet result5 = others.executeQuery();
           result5.next();
           String sumx6 = result5.getString(1);
           double val5 = Math.round(Double.parseDouble(sumx6));           
           String Others = Double.toString(val5);
           
           //Total Assets
           double total_assets = val+val1+val2+val3+val4+val5;
           String Total_Assets = Double.toString(total_assets);
           
           //Liabilities and Owners Equity
           ///////////////////////////////////////
           //Account Payable
           PreparedStatement account_payable =  db.connect().prepareStatement("SELECT IFNULL(SUM(ammount),0) FROM expense WHERE type ='due'");
           ResultSet result6 = account_payable.executeQuery();
           result6.next();
           String sumx4 = result6.getString(1);
           double val6 = Math.round(Double.parseDouble(sumx4));           
           String Account_Payable = Double.toString(val6);
           
           //Note Payable
           PreparedStatement note_payable =  db.connect().prepareStatement("SELECT IFNULL(SUM(price),0) FROM liability_equity WHERE type ='Note Payable'");
           ResultSet result7 = note_payable.executeQuery();
           result7.next();
           String sumx7 = result7.getString(1);
           double val7 = Math.round(Double.parseDouble(sumx7));           
           String Note_Payable = Double.toString(val7);          
           
           
           //Capital
           double capital = val+val1+val2+val3+val4;
           String Capital = Double.toString(capital);     
           
           //Expense
           PreparedStatement expense =  db.connect().prepareStatement("SELECT IFNULL(SUM(ammount),0) FROM expense WHERE type ='Cash'");
           ResultSet result8 = expense.executeQuery();
           result8.next();
           String sumx8 = result8.getString(1);
           double val8 = Math.round(Double.parseDouble(sumx8));           
           String Expense = Double.toString(val8);      
           
           //Withdraw
           PreparedStatement withdraw =  db.connect().prepareStatement("select IFNULL(SUM(withdraw),0) from withdraw");
           ResultSet result9 = withdraw.executeQuery();
           result9.next();
           String sumx9 = result9.getString(1);
           double val9 = Math.round(Double.parseDouble(sumx9));           
           String Withdraw = Double.toString(val9);      
           
           
           
           //Total Liabilities and Equity
           double total_loe = val6+val7+val8+val9+capital;
           String Total_LOE = Double.toString(total_loe);  
           
           
             try{  
                 LocalDate localDate = LocalDate.now();//For reference
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 String Date = localDate.format(formatter);
                 
                 //Assets               
                 DefaultTableModel model = (DefaultTableModel)products.getModel();  
                 model.addRow(new Object[]{"Assets" , ""});
                 model.addRow(new Object[]{"      Cash on Hand" , Cash_on_Hand});
                 model.addRow(new Object[]{"      Cash on Bank" , Cash_on_Bank});
                 model.addRow(new Object[]{"      Equipment" , Equipment});
                 model.addRow(new Object[]{"      Inventory" , Inventory});
                 model.addRow(new Object[]{"      Account Recivable" , Total_Due});
                 model.addRow(new Object[]{"      Others" , Others});
                 model.addRow(new Object[]{"                                                       Total Assets" , Total_Assets});
                 model.addRow(new Object[]{"" , ""});
                 
                 //Liabilities and Owners Equity                 
                 String Total = "                                                       Total Liabilities and Equity"; 
                 
                 model.addRow(new Object[]{"Liabilities and Owner's Equity" , ""});
                 model.addRow(new Object[]{"      Account Payable" , Account_Payable});
                 model.addRow(new Object[]{"      Note Payable" , Note_Payable});
                 model.addRow(new Object[]{"" , ""});
                 model.addRow(new Object[]{"Equity" , ""});                 
                 model.addRow(new Object[]{"      Capital" , Capital});
                 model.addRow(new Object[]{"      Income" , "0"});
                 model.addRow(new Object[]{"      Expense" , Expense});
                 model.addRow(new Object[]{"      Drawings" , Withdraw});
                 model.addRow(new Object[]{Total , Total_LOE});
             }
             catch(Exception e){

             }   

           } 
         catch(Exception exc){
           System.out.println(exc.getMessage());
        }
    }
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // Assets Input
        input_asset ia = new input_asset();
        ia.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Owners Equity
        input_equity oe = new input_equity();
        oe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    public void show_loe(){
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
           String Query =("SELECT id AS ID, product_name AS 'Product Name', quantity AS 'Quantity',price AS Ammount, issuer AS 'Issuer',date AS 'Date' FROM liability_equity ");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet result =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(result));
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }
    }
    private void selected_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selected_itemActionPerformed
        // Select Box Accout Report Page
        String selecteditem = String.valueOf(selected_item.getSelectedItem());
        if(selecteditem.equals("Balance Sheet")){
            
        }
        else if(selecteditem.equals("Assets")){
            show_assets();
        }
        else if(selecteditem.equals("L + OE")){
            show_loe();   
        }
    }//GEN-LAST:event_selected_itemActionPerformed

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
            java.util.logging.Logger.getLogger(account_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(account_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(account_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(account_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new account_report().setVisible(true);
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
    private javax.swing.JLabel assets_quantity;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
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
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JComboBox<String> selected_item;
    private javax.swing.JPanel sidebar;
    private com.toedter.calendar.JDateChooser to;
    private javax.swing.JLabel total_count_expense;
    private javax.swing.JLabel total_expense;
    // End of variables declaration//GEN-END:variables
}
