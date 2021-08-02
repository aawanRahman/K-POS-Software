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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Najmul
 */
public class shippings extends javax.swing.JFrame {
    db_connect db = new db_connect();
    
    LocalDate localDate = LocalDate.now();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Date = localDate.format(formatter);
        
    /**
     * Creates new form homepage
     */
    public shippings() {        
        full_screen();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);         
        seticon() ;
        theme();
        exit();
        show_shipping();
        products.setDefaultEditor(Object.class, null);
        products.setShowGrid(true);
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
    
    
    
    void show_shipping(){
        products.getTableHeader().setEnabled(false);
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
            String Query =("SELECT id AS ID,Username AS 'Customer Name',Total_Price AS 'Total Price',Payment,Issuer, Shipping ,Date FROM sold WHERE Shipping='Yes' ORDER BY id ASC LIMIT 100");
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error Whiggggle Connecting : "+e);
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

        sold_report_detaiils = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        sold_report_details = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        customer_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        notification_shipping = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        get_product_name = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        status = new javax.swing.JComboBox<>();
        selected_items = new javax.swing.JComboBox<>();
        total_price = new javax.swing.JTextField();
        paid = new javax.swing.JTextField();
        due = new javax.swing.JTextField();
        switch_details = new javax.swing.JToggleButton();
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
        jScrollPane3.setViewportView(sold_report_details);

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        sold_report_detaiilsLayout.setVerticalGroup(
            sold_report_detaiilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shippings");
        setMinimumSize(new java.awt.Dimension(1212, 626));

        customer_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customer_name.setText(" Customer Name");
        customer_name.setToolTipText(" Customer Name");
        customer_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        customer_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customer_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                customer_nameFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Shippings");

        phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        phone.setText(" Phone");
        phone.setToolTipText(" Phone");
        phone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneFocusLost(evt);
            }
        });
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        address.setText(" Address");
        address.setToolTipText(" Address");
        address.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressFocusLost(evt);
            }
        });

        notification_shipping.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notification_shipping.setForeground(new java.awt.Color(255, 0, 0));

        products.setAutoCreateRowSorter(true);
        products.setBackground(new java.awt.Color(238, 238, 238));
        products.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
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
        products.setAlignmentX(0.0F);
        products.setAlignmentY(0.0F);
        products.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        products.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        products.setDoubleBuffered(true);
        products.setEditingColumn(0);
        products.setEditingRow(0);
        products.setFocusable(false);
        products.setGridColor(new java.awt.Color(209, 206, 206));
        products.setInheritsPopupMenu(true);
        products.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        products.setName("All Products"); // NOI18N
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
        jScrollPane1.setViewportView(products);
        products.getAccessibleContext().setAccessibleName("");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jButton3.setToolTipText("Update");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        get_product_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        get_product_name.setToolTipText("Search by Bill No or Reciver Name");
        get_product_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        get_product_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                get_product_nameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                get_product_nameKeyTyped(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/search.png"))); // NOI18N
        jButton5.setText("Search");
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/shipping.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(footer))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/clear.png"))); // NOI18N
        jButton6.setToolTipText("Clear");
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email.setText(" Email");
        email.setToolTipText(" Phone");
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        description.setColumns(20);
        description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        description.setRows(5);
        description.setText(" Description");
        description.setToolTipText("Description");
        description.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(description);

        status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shipping Status", "Yes", "No", "Done" }));
        status.setToolTipText("Shipping Status");
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        selected_items.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selected_items.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Types", "Yes", "No", "Done" }));
        selected_items.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selected_itemsActionPerformed(evt);
            }
        });

        total_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_price.setText(" Total Price");
        total_price.setToolTipText(" Total Price");
        total_price.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        total_price.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                total_priceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                total_priceFocusLost(evt);
            }
        });
        total_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_priceActionPerformed(evt);
            }
        });

        paid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paid.setText(" Paid");
        paid.setToolTipText(" Paid");
        paid.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        paid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                paidFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                paidFocusLost(evt);
            }
        });
        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });

        due.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        due.setText(" Previous Due");
        due.setToolTipText(" Previous Due");
        due.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        due.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dueFocusLost(evt);
            }
        });

        switch_details.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(10, 10, 10)
                                .addComponent(jButton6)
                                .addGap(10, 10, 10)
                                .addComponent(switch_details))
                            .addComponent(notification_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(total_price, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(paid, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(phone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(address, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(due, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(status, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(get_product_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selected_items, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selected_items)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(total_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(due, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(switch_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(notification_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Update Expense      
        if(products.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please Select a Customer First","Error", JOptionPane.QUESTION_MESSAGE);
        }
         else{    
            String Status = String.valueOf(status.getSelectedItem());
            if(Status.equals("Shipping Status")){
                notification_shipping.setText("Select a Shipping Status First !");
            }
            else{
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                try{ 
                    PreparedStatement pst = db.connect().prepareStatement("UPDATE sold SET Shipping=? WHERE id='"+Table_Click+"' ");
                    pst.setString(1, Status);                
                    pst.executeUpdate(); 
                    notification_shipping.setText("Expense Updated !");        
                    clear();
                }
                catch(Exception e){
                    System.out.println("My Sql is Not Running : "+e);
                    notification_shipping.setText("Something Error Happend !"); 
                }        
               show_shipping();
            }
         }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void search(){
        //Bill no and Reciver Name
        String Product_Name = get_product_name.getText();       
        if(Product_Name.isEmpty()){
            show_shipping();
        }
        else{
            try{
                String Query =("SELECT id AS ID,Username AS 'Customer Name',Total_Price AS 'Total Price',Payment,Issuer, Shipping ,Date FROM sold WHERE Username LIKE '%"+Product_Name+"%' AND Shipping='Yes' OR id LIKE '%"+Product_Name+"%' AND Shipping='Yes' ORDER BY id ASC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));        
           }
           catch(Exception e){
               System.out.println("Error Whiggggle Connecting : "+e);
           }
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Shipping Search
        search();
    }//GEN-LAST:event_jButton5ActionPerformed
    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
           try{
            int row = products.getSelectedRow();
            String Table_Click = (products.getModel().getValueAt(row,0).toString());
            String Query ="SELECT * FROM sold WHERE id='"+Table_Click+"'";  
            PreparedStatement st = db.connect().prepareStatement(Query);
            ResultSet rs =st.executeQuery(Query);            
            if(rs.next()){      
                String Username=rs.getString("Username");
                
                String Total_Price=rs.getString("Total_Price");
                total_price.setText(Total_Price); 
                
                String Paid=rs.getString("Paid");
                paid.setText(Paid);      
                
                String Shipping=rs.getString("Shipping");
                status.setSelectedItem(Shipping);  
             
                
                String Query_Get ="SELECT * FROM customers WHERE username='"+Username+"'";  
                PreparedStatement st_gt = db.connect().prepareStatement(Query_Get);
                ResultSet rs_gt =st_gt.executeQuery(Query_Get);            
                if(rs_gt.next()){
                    
                    String First_Name =rs_gt.getString("first_name");
                    String Last_Name =rs_gt.getString("last_name");
                    customer_name.setText(First_Name+" "+Last_Name);
                
                    String Phone = rs_gt.getString("mobile");                    
                    phone.setText(Phone);      
                    
                    String Email = rs_gt.getString("email");
                    email.setText(Email);      
                    
                    String Address = rs_gt.getString("address");
                    address.setText(Address);      
                    
                    String About = rs_gt.getString("About");
                    description.setText(About); 
                    
                    String Due = rs_gt.getString("due");
                    due.setText(Due); 
                }
            }
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_productsMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        homepage h= new homepage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed
    public void clear(){
        try{
            customer_name.setText(" Customer Name");
            phone.setText(" Phone");
            email.setText(" Email");
            address.setText(" Address");
            description.setText(" Description");        
            total_price.setText(" Total Price");
            paid.setText(" Paid");         
            due.setText("Previous Due");
        }
        catch(Exception e){
            notification_shipping.setText("Error Somewhere !");
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Expense Clear
        clear();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void get_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyPressed
        // Enter Key Pressed Search
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            search ();
        }
    }//GEN-LAST:event_get_product_nameKeyPressed

    private void get_product_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyTyped
        // Key Pressed Any
        search ();
    }//GEN-LAST:event_get_product_nameKeyTyped

    private void customer_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customer_nameFocusGained
        if(customer_name.getText().equals(" Customer Name")){
            customer_name.setText("");
            customer_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_customer_nameFocusGained

    private void customer_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customer_nameFocusLost
        if(customer_name.getText().trim().equals("")){
            customer_name.setText(" Customer Name");
            customer_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_customer_nameFocusLost

    private void phoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneFocusGained
        if(phone.getText().equals(" Phone")){
            phone.setText("");
            phone.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_phoneFocusGained

    private void phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneFocusLost
        if(phone.getText().trim().equals("")){
            phone.setText(" Phone");
            phone.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_phoneFocusLost

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        if(email.getText().equals(" Email")){
            email.setText("");
            email.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        if(email.getText().trim().equals("")){
            email.setText(" Email");
            email.setForeground(new Color(0,0,0));
        }

    }//GEN-LAST:event_emailFocusLost

    private void addressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFocusGained
        if(address.getText().equals(" Address")){
            address.setText("");
            address.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_addressFocusGained

    private void addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFocusLost
        if(address.getText().trim().equals("")){
            address.setText(" Address");
            address.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_addressFocusLost

    private void descriptionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFocusGained
        if(description.getText().equals(" Description")){
            description.setText("");
            description.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_descriptionFocusGained

    private void descriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFocusLost
        if(description.getText().trim().equals("")){
            description.setText(" Description");
            description.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_descriptionFocusLost

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
            shippings e = new shippings();
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

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // Select Box Sell Page
        //Products Customers Refill Ticket Refill Products
        String selecteditem = String.valueOf(status.getSelectedItem());
        if(selecteditem.equals("Products")){

        }
    }//GEN-LAST:event_statusActionPerformed

    private void selected_itemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selected_itemsActionPerformed
        //Selected Items
        String selecteditem = String.valueOf(selected_items.getSelectedItem());
        if(selecteditem.equals("Select Types")){
            show_shipping();
        }       
        else{
            try{
                String Query =("SELECT id AS ID,Username AS 'Customer Name',Total_Price AS 'Total Price',Payment,Issuer, Shipping ,Date FROM sold WHERE Shipping='"+selecteditem+"' ORDER BY id ASC LIMIT 100");
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                products.setModel(DbUtils.resultSetToTableModel(rs));
             }
             catch(Exception e){
                 System.out.println("Error While Connecting : "+e);
             }
        }
    }//GEN-LAST:event_selected_itemsActionPerformed

    private void total_priceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_priceFocusGained
        if(total_price.getText().equals(" Total Price")){
            total_price.setText("");
            total_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_total_priceFocusGained

    private void total_priceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_priceFocusLost
         if(total_price.getText().trim().equals("")){
            total_price.setText(" Total Price");
            total_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_total_priceFocusLost

    private void total_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_priceActionPerformed

    private void paidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paidFocusGained
        if(paid.getText().equals(" Paid")){
            paid.setText("");
            paid.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_paidFocusGained

    private void paidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paidFocusLost
         if(paid.getText().trim().equals("")){
            paid.setText(" Paid");
            paid.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_paidFocusLost

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paidActionPerformed

    private void dueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dueFocusGained
         if(due.getText().equals(" Previous Due")){
            due.setText("");
            due.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_dueFocusGained

    private void dueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dueFocusLost
        if(due.getText().trim().equals("")){
            due.setText(" Previous Due");
            due.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_dueFocusLost

    private void sold_report_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sold_report_detailsMouseClicked

    }//GEN-LAST:event_sold_report_detailsMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Exit Page
        sold_report_detaiils.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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

    private void productsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMousePressed
        //Shipping Mouse Pressed
        try{
            String Query1 ="SELECT * FROM settings";           
            PreparedStatement statement = db.connect().prepareStatement(Query1);
            ResultSet rs =statement.executeQuery(Query1);
            if(rs.next()){
                String Details=rs.getString("show_details"); 
                if(Details.equals("Enabled")){
                   if(switch_details.isSelected()){
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
            java.util.logging.Logger.getLogger(shippings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(shippings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(shippings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(shippings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new shippings().setVisible(true);
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
    private javax.swing.JTextField customer_name;
    private javax.swing.JTextArea description;
    private javax.swing.JTextField due;
    private javax.swing.JTextField email;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel notification_shipping;
    private javax.swing.JTextField paid;
    private javax.swing.JTextField phone;
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JComboBox<String> selected_items;
    private javax.swing.JPanel sidebar;
    private javax.swing.JFrame sold_report_detaiils;
    private javax.swing.JTable sold_report_details;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JToggleButton switch_details;
    private javax.swing.JTextField total_price;
    // End of variables declaration//GEN-END:variables
}
