/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Najmul
 */

public class sell extends javax.swing.JFrame {
    db_connect db = new db_connect();
    
    LocalDate localDate = LocalDate.now();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Date = localDate.format(formatter);
    
    public sell() {        
        setFocusTraversalKeysEnabled(false);
        full_screen();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon();
        theme();
        exit();
        show_products();
        products.setDefaultEditor(Object.class, null);
        cart.setDefaultEditor(Object.class, null);
        products.setShowGrid(true); 
        cart.setShowGrid(true); 

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
    
    
    
    void show_products(){ 
        products.getTableHeader().setEnabled(false);
        cart.getTableHeader().setEnabled(false);
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,16)); 
        cart.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17)); 
        try{
           String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Quantity,Issuer,Company,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products ORDER BY id DESC LIMIT 100");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        get_product_name = new javax.swing.JTextField();
        search_button = new javax.swing.JButton();
        add_to_cart = new javax.swing.JButton();
        sell_qty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        cart = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        selected_item = new javax.swing.JComboBox<>();
        customer_add = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        user = new javax.swing.JTextField();
        plus_product = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        calculate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        others = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cash_pay = new javax.swing.JButton();
        due_money = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        total_all = new javax.swing.JTextField();
        vatt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        shipping_checkbox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        selected_item1 = new javax.swing.JComboBox<>();
        sold_status = new javax.swing.JLabel();
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
        setTitle("Sell Product");
        setMinimumSize(new java.awt.Dimension(1300, 725));
        setPreferredSize(new java.awt.Dimension(1300, 725));

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
        products.setGridColor(new java.awt.Color(209, 206, 206));
        products.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        products.setMinimumSize(new java.awt.Dimension(50, 0));
        products.setName("All Products"); // NOI18N
        products.setOpaque(false);
        products.setRequestFocusEnabled(false);
        products.setRowHeight(30);
        products.setSelectionBackground(new java.awt.Color(186, 186, 236));
        products.setSelectionForeground(new java.awt.Color(0, 0, 0));
        products.getTableHeader().setReorderingAllowed(false);
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

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        get_product_name.setToolTipText("Search By Product Name or id");
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

        search_button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/search.png"))); // NOI18N
        search_button.setText("Search ");
        search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_buttonActionPerformed(evt);
            }
        });

        add_to_cart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add_to_cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/add.png"))); // NOI18N
        add_to_cart.setText("Add to Cart");
        add_to_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_cartActionPerformed(evt);
            }
        });
        add_to_cart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                add_to_cartKeyPressed(evt);
            }
        });

        sell_qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sell_qty.setText("1");
        sell_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sell_qtyActionPerformed(evt);
            }
        });
        sell_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sell_qtyKeyPressed(evt);
            }
        });

        cart.setAutoCreateRowSorter(true);
        cart.setBackground(new java.awt.Color(238, 238, 238));
        cart.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cart.setForeground(new java.awt.Color(42, 42, 42));
        cart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Name", "Quantity", "Price", "Total Price", "Purchase  Price", "Issuer", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cart.setToolTipText("All Products");
        cart.setAlignmentX(5.0F);
        cart.setAlignmentY(5.0F);
        cart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cart.setEditingColumn(0);
        cart.setEditingRow(0);
        cart.setFocusable(false);
        cart.setGridColor(new java.awt.Color(209, 206, 206));
        cart.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        cart.setName("All Products"); // NOI18N
        cart.setOpaque(false);
        cart.setRequestFocusEnabled(false);
        cart.setRowHeight(30);
        cart.setSelectionBackground(new java.awt.Color(186, 186, 236));
        cart.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(cart);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/delete.png"))); // NOI18N
        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        sidebar.setBackground(new java.awt.Color(34, 49, 63));

        jButton15.setBackground(new java.awt.Color(34, 49, 63));
        jButton15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Main Menu");
        jButton15.setBorder(null);
        jButton15.setFocusable(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        footer.setForeground(new java.awt.Color(204, 204, 204));
        footer.setText("Footer Text");

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/sell product.png"))); // NOI18N
        jButton14.setEnabled(false);
        jButton14.setFocusable(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(34, 49, 63));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("All Products");
        jButton9.setBorder(null);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(34, 49, 63));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("All Customers");
        jButton12.setBorder(null);
        jButton12.setFocusable(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(34, 49, 63));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Refill Ticket");
        jButton16.setBorder(null);
        jButton16.setFocusable(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(34, 49, 63));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Refill Products");
        jButton17.setBorder(null);
        jButton17.setFocusable(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(footer))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton14)
                .addGap(8, 8, 8)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        selected_item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selected_item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Products", "Customers", "Refill Ticket", "Refill Products" }));
        selected_item.setToolTipText("Select Search Filter");
        selected_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selected_itemActionPerformed(evt);
            }
        });

        customer_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customer_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/member.png"))); // NOI18N
        customer_add.setText("Customer");
        customer_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_addActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/get.png"))); // NOI18N
        jButton13.setText("Check User");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user.setText("New");
        user.setToolTipText("User Name");
        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKeyPressed(evt);
            }
        });

        plus_product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/add.png"))); // NOI18N
        plus_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus_productActionPerformed(evt);
            }
        });
        plus_product.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                plus_productKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("Subtotal");

        discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount.setText("0");

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/due.png"))); // NOI18N
        jButton7.setText("Due");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel9.setText("Others");

        calculate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        calculate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/calculate.png"))); // NOI18N
        calculate.setText("Calculate");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });
        calculate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                calculateKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel6.setText("Due");

        paid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paid.setText("0");
        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });

        others.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        others.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("Vat");

        subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        subtotal.setText("0");
        subtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                subtotalKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel7.setText("Paid");

        cash_pay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cash_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/cash.png"))); // NOI18N
        cash_pay.setText("Cash");
        cash_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cash_payActionPerformed(evt);
            }
        });
        cash_pay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cash_payKeyPressed(evt);
            }
        });

        due_money.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        due_money.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setText("Total");

        total_all.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_all.setText("0");

        vatt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vatt.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setText("Discount");

        shipping_checkbox.setBackground(new java.awt.Color(153, 153, 153));
        shipping_checkbox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        shipping_checkbox.setText("Yes / No");
        shipping_checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shipping_checkboxActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel8.setText("Shipping");

        selected_item1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selected_item1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "Bkash", "DBBL", "Nagad" }));
        selected_item1.setToolTipText("Select Search Filter");
        selected_item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selected_item1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(total_all, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(57, 57, 57)
                                        .addComponent(due_money))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(subtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(vatt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(discount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(others, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(shipping_checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(selected_item1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cash_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calculate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vatt)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(due_money)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(discount)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_all, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(shipping_checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addComponent(calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selected_item1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cash_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        sold_status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sold_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        sold_status.setText("Product Status");

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

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(get_product_name)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(selected_item, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customer_add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(plus_product, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sell_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_to_cart, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sold_status, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selected_item, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(add_to_cart, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customer_add, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sell_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(plus_product, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sold_status, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void clear(){
        subtotal.setText("0");
        vatt.setText("0");
        due_money.setText("0");
        discount.setText("0");
        total_all.setText("0");
        paid.setText("0");
    }
    
    
    private void search(){        
        Object selected = selected_item.getSelectedItem();
        String Product_Name = get_product_name.getText();
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Date = localDate.format(formatter);
        if(selected.toString().equals("Products")){            
            if(Product_Name.isEmpty()){
                show_products();
            }
            else{
                try{
                    String Query =("SELECT id AS ID,Name AS 'Product Name',Price,Quantity,Issuer,Company,Type,Date AS 'Entry Date',doe AS 'Expire Date' FROM products WHERE Name LIKE '%"+Product_Name+"%' OR id  LIKE '%"+Product_Name+"%' OR location  LIKE '%"+Product_Name+"%' OR Article_No  LIKE '%"+Product_Name+"%'");
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);
                    products.setModel(DbUtils.resultSetToTableModel(rs));
                    ResultSet rs2 =statement.executeQuery(Query);
                    int fin = 0;          
                    while( rs2.next() ){
                        int rowNum = rs2.getRow();
                        fin = rowNum;     
                        System.out.println(rowNum);
                    }  
                    System.out.println(fin);
                    if(fin == 1){
                            products.selectAll();
                        }
                    else{
                        //Do Nothing
                    }                    
               }
               catch(Exception e){
                   System.out.println("Error While Connecting : "+e);
               }
            }       
        }
        else if(selected.toString().equals("Customers")){
            if(Product_Name.isEmpty()){
                show_customers(); 
            }
            else{
                try{
                    String Query =("SELECT id AS ID,username AS 'Username',first_name AS 'First Name',last_name AS 'Last Name',mobile AS 'Mobile',due AS 'Due',About,date AS 'Date' FROM customers WHERE username LIKE '%"+Product_Name+"%' ");
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);            
                    products.setModel(DbUtils.resultSetToTableModel(rs));
               }
               catch(Exception e){
                   System.out.println("Error While Connecting : "+e);
               }
            }       
        }
    }
    private void search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_buttonActionPerformed
        search ();          
    }//GEN-LAST:event_search_buttonActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        int count= cart.getModel().getRowCount(); 
        if(count<=0){
            homepage h= new homepage();
            h.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Please Empty Cart Before Leaving.","Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
       
    }//GEN-LAST:event_productsMouseClicked

    private void add_to_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_cartActionPerformed
        //Add to Cart Sell Products
        String selecteditem = String.valueOf(selected_item.getSelectedItem());
        if(selecteditem.equals("Products")){
            if(products.getSelectionModel().isSelectionEmpty()){
            selected_item.setSelectedItem("Products");
                //Show All Customers           
                JOptionPane.showMessageDialog(this, "Please Select a Products First","Error", JOptionPane.QUESTION_MESSAGE); 
                show_products();
            }
            else{            
                String Product_Qty= sell_qty.getText();
                if(Product_Qty.isEmpty() || Product_Qty.equals("0")) { 
                    JOptionPane.showMessageDialog(this, "Please Enter Quantity","Error", JOptionPane.QUESTION_MESSAGE); 
                }
                else{
                    get_settings s1=new get_settings();       
                    String Issuer = s1.noorder;
                     try{
                        int row = products.getSelectedRow();
                        String Table_Click = (products.getModel().getValueAt(row,0).toString());
                        String Query ="SELECT * FROM products WHERE id='"+Table_Click+"'";           
                        PreparedStatement statement = db.connect().prepareStatement(Query);
                        ResultSet rs =statement.executeQuery(Query);        
                        if(rs.next()){
                            String ID=rs.getString("id"); 
                            String Product_Name=rs.getString("Name"); 
                            String Product_Price=rs.getString("Price");
                            String Purches_Price=rs.getString("Purchase_Price");
                            String Db_Qty=rs.getString("Quantity");
                            
                            double purches_price_double = Double.parseDouble(Purches_Price);     
                            double qty = Double.parseDouble(Product_Qty);                    
                            double qty_double = Double.parseDouble(Db_Qty);                   
                            if(qty_double >= qty){                        
                                double price = Double.parseDouble(Product_Price);
                                double roundOff = price * qty;
                                //Up to tow times Ans
                                double Total_Price = Math.floor(roundOff*100)/100; 
                                //Add to Table
                                DefaultTableModel model = (DefaultTableModel)cart.getModel();
                                model.addRow(new Object[]{ID,Product_Name,Product_Qty,Product_Price,Total_Price,Purches_Price,Issuer,Date});                    

                                //Update Quantity From Here
                                double FinalQty = qty_double-qty;
                                String str = Double.toString(FinalQty);
                                double total_price_Update = price*FinalQty;
                                double purches_price_Update = purches_price_double*FinalQty;
                                String total_price_string = Double.toString(total_price_Update);
                                String purches_price_string = Double.toString(purches_price_Update);                          
                                try{ 
                                    PreparedStatement pst = db.connect().prepareStatement("UPDATE products SET Quantity=?,total_price=?,total_purchese_price=? WHERE id='"+Table_Click+"' ");
                                    pst.setString(1, str); 
                                    pst.setString(2, total_price_string);    
                                    pst.setString(3, purches_price_string);    
                                    pst.executeUpdate(); 
                                }
                                catch(Exception e){
                                    System.out.println("Error With Database : "+e);
                                }        
                                show_products();
                            }
                            else{
                                JOptionPane.showMessageDialog(this, "You Don't Have Enough Product !","Error", JOptionPane.QUESTION_MESSAGE);  
                            }
                        }
                    }
                    catch(Exception e){

                    } 
                    getSum();
                }
            }
        } 
        else if(selecteditem.equals("Customers")){
            JOptionPane.showMessageDialog(this, "Customer Cannot Be Added to Cart !","Error", JOptionPane.QUESTION_MESSAGE);
        }
        else if(selecteditem.equals("Refill Ticket")){
            //Refill Button to Refill       
            try{
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                String Query ="SELECT * FROM refill WHERE id='"+Table_Click+"'";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){  
                    String ID = rs.getString("id"); 
                    String Product_Name = rs.getString("product_name");                   
                    String Issuer = "Refill";

                    String Subtotal = subtotal.getText();
                    double Subtotal_Double = Double.parseDouble(Subtotal);
                    //Product * Quantity
                    String Product_Price = rs.getString("price"); 
                    double Product_Price_double = Double.parseDouble(Product_Price); 
                    double Product_Price_double_abs = Math.abs(Product_Price_double);
                    String Product_Price_double_abs_final = Double.toString(Product_Price_double_abs);
                    String Product_Qty = sell_qty.getText();                    
                    double Product_Qty_Double = Double.parseDouble(Product_Qty);                     
                    String Total_Price = Double.toString(Product_Qty_Double*Product_Price_double);
                    String Total_Pricex = "0";
                    double Total_Price_Double = Double.parseDouble(Total_Price);                    
                    double Total_Final = Subtotal_Double+Total_Price_Double;                    
                    String str = Double.toString(Total_Final);
                    subtotal.setText(str);                        
                    DefaultTableModel model = (DefaultTableModel)cart.getModel();
                    model.addRow(new Object[]{ID,Product_Name,Product_Qty,Product_Price,Total_Price,Total_Pricex,Issuer,Date});     
                   
                    //Update Quantity For "Refill to Sell"
                    try{
                        String Qry="SELECT * FROM refill_to_sell WHERE product_name='"+Product_Name+"'";           
                        PreparedStatement statement1 = db.connect().prepareStatement(Qry);
                        ResultSet rs1 =statement1.executeQuery(Qry);
                        
                        //Multiple with (-1)                       
                        double Total_Price_Doublex = ((-1)*Double.parseDouble(Total_Price));  
                        String Total_Price_String = Double.toString(Total_Price_Doublex);
                        
                        if(rs1.next()){  
                            //Previous Quantity + New Quantity and Qty * Price                           
                            String qty_refill_to_sell = rs1.getString("quantity"); 
                            double qty_refill_to_sell_Double = Double.parseDouble(qty_refill_to_sell);  
                            double final_Qty_double = Product_Qty_Double+qty_refill_to_sell_Double;
                            String final_Qty = Double.toString(final_Qty_double); 
                            double total_price_double = final_Qty_double * Product_Price_double_abs;
                            String final_total_price = Double.toString(total_price_double); 
                            
                            try{ 
                            PreparedStatement pst = db.connect().prepareStatement("UPDATE refill_to_sell SET quantity=?,price=?,total_price=? WHERE product_name='"+Product_Name+"' ");
                            pst.setString(1, final_Qty); 
                            pst.setString(2, Product_Price_double_abs_final);    
                            pst.setString(3, final_total_price);    
                            pst.executeUpdate(); 
                            }
                            catch(Exception e){
                                System.out.println("Error With Database : "+e);
                            }        
                        }   
                        else{
                            //insert Product Details refill to sell
                             try{
                                PreparedStatement pst = db.connect().prepareStatement("INSERT INTO refill_to_sell (product_name , quantity , price , total_price , date)" + "VALUES (?,?,?,?,?)");
                                pst.setString(1, Product_Name);
                                pst.setString(2, Product_Qty);
                                pst.setString(3, Product_Price_double_abs_final);
                                pst.setString(4, Total_Price_String);
                                pst.setString(5, Date);
                                pst.executeUpdate();     
                            }
                            catch(Exception e){
                                System.out.println("Error Connecting Database. "+e);
                            }     
                        }
                    }
                    catch(Exception e){
                        
                    }
                }
            }
            catch(Exception e){

            }     
            refill_tickets();
        }
        else if(selecteditem.equals("Refill Products")){
             try{
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                String Query ="SELECT * FROM refill_to_sell  WHERE id='"+Table_Click+"'";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){  
                    String ID = rs.getString("id"); 
                    String Quantity = rs.getString("quantity");
                    double Product_Qty_Double_db = Double.parseDouble(Quantity);   
                    String Product_Name = rs.getString("product_name");                   
                    String Issuer = "Refill Storage";

                    String Subtotal = subtotal.getText();
                    double Subtotal_Double = Double.parseDouble(Subtotal);
                    //Product * Quantity
                    String Product_Price = rs.getString("price"); 
                    double Product_Price_double = (Double.parseDouble(Product_Price)); 
                    double Product_Price_double_abs = Math.abs(Product_Price_double);
                    String Product_Price_double_abs_final = Double.toString(Product_Price_double_abs);
                    String Proudct_Price_Positive = String.valueOf(Product_Price_double_abs_final);
                    String Product_Qty = sell_qty.getText();                    
                    double Product_Qty_Double = Double.parseDouble(Product_Qty);                     
                    String Total_Price = Double.toString(Product_Qty_Double*Product_Price_double);
                    String Purchases_Price = "000";
                    double Total_Price_Double = Double.parseDouble(Total_Price);                    
                    double Total_Final = Subtotal_Double+Total_Price_Double;                    
                    String str = Double.toString(Total_Final);
                    subtotal.setText(str);                        
                    
                    //Update Quantity
                   if(Product_Qty_Double > Product_Qty_Double_db){ 
                       JOptionPane.showMessageDialog(this, "You Don't Have Enough Product !","Error", JOptionPane.QUESTION_MESSAGE); 
                    }
                    else{
                       //Update Quantity From Here
                        double FinalQty = Product_Qty_Double_db-Product_Qty_Double;
                        String final_quantity = Double.toString(FinalQty);
                        double total_price_Update = Product_Price_double_abs*FinalQty;
                        String total_price_string = Double.toString(total_price_Update);
                        try{ 
                            PreparedStatement pst = db.connect().prepareStatement("UPDATE refill_to_sell SET quantity=?,price=?,total_price=? WHERE product_name='"+Product_Name+"' ");
                            pst.setString(1, final_quantity); 
                            pst.setString(2, Product_Price);    
                            pst.setString(3, total_price_string);    
                            pst.executeUpdate(); 
                            }
                        catch(Exception e){
                            System.out.println("Error With Database : "+e);
                        }     
                        DefaultTableModel model = (DefaultTableModel)cart.getModel();
                        model.addRow(new Object[]{ID,Product_Name,Product_Qty,Proudct_Price_Positive,Total_Price,Purchases_Price,Issuer,Date});     
                    }
                }
            }
            catch(Exception e){
                System.out.println(e);
            }      
            refill_products();
        }
    }//GEN-LAST:event_add_to_cartActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Delete Date From Table        
        if(cart.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please Select a Product First","Error", JOptionPane.QUESTION_MESSAGE);
        }
        else{
            try{
                //Code for Update Quantity When 
                int row = cart.getSelectedRow();
                String Table_Click = (cart.getModel().getValueAt(row,0).toString());
                String qty_x = (cart.getModel().getValueAt(row,2).toString());
                String Issuer = (cart.getModel().getValueAt(row,6).toString());
                //System.out.println(Issuer);
                double qty = Double.parseDouble(qty_x); 
                if(Issuer.equals("Refill")){
                    DefaultTableModel model = (DefaultTableModel) cart.getModel();
                    int SelectedRowIndex = cart.getSelectedRow();
                    model.removeRow(SelectedRowIndex);
                    getSum();
                }
                else if(Issuer.equals("Refill Storage")){                    
                    String Query ="SELECT * FROM refill_to_sell WHERE id='"+Table_Click+"'";           
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);
                    if(rs.next()){
                        String Qty_x = rs.getString("quantity"); 
                        String product_price = rs.getString("price"); 
                        double Quantity = Double.parseDouble(Qty_x); 
                        double Quantity_product = Double.parseDouble(product_price); 
                        try{ 
                          double FinalQty = Quantity+qty;
                          double total_product = FinalQty*Quantity_product;

                          String str = Double.toString(FinalQty);
                          String str_final = Double.toString(total_product);

                          PreparedStatement pst = db.connect().prepareStatement("UPDATE refill_to_sell SET quantity=?,price=?,total_price=? WHERE id='"+Table_Click+"' ");
                          pst.setString(1, str); 
                          pst.setString(2, product_price);  
                          pst.setString(3, str_final);  
                          pst.executeUpdate(); 
                          show_products();
                      }
                      catch(Exception e){
                          System.out.println("Error With Database : "+e);
                      } 
                    }
                    //Code For Delete Row From Table
                    DefaultTableModel model = (DefaultTableModel) cart.getModel();
                    int SelectedRowIndex = cart.getSelectedRow();
                    if(cart.getSelectionModel().isSelectionEmpty()){
                        JOptionPane.showMessageDialog(this, "Please Select a Product First","Error", JOptionPane.QUESTION_MESSAGE); 
                    }
                    else{
                        model.removeRow(SelectedRowIndex);
                        getSum();
                    }
                    refill_products();
                }
                else{
                    String Query ="SELECT * FROM products WHERE id='"+Table_Click+"'";           
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);
                    if(rs.next()){
                        String Qty_x=rs.getString("Quantity"); 
                        String product_price=rs.getString("Price"); 
                        String purches_price=rs.getString("Purchase_Price"); 
                        double Quantity = Double.parseDouble(Qty_x); 
                        double Quantity_product = Double.parseDouble(product_price); 
                        double Quantity_purches = Double.parseDouble(purches_price); 
                        try{ 
                          double FinalQty = Quantity+qty;
                          double total_product = FinalQty*Quantity_product;
                          double purches_product = FinalQty*Quantity_purches;

                          String str = Double.toString(FinalQty);
                          String str_final = Double.toString(total_product);
                          String str_purches = Double.toString(purches_product);

                          PreparedStatement pst = db.connect().prepareStatement("UPDATE products SET Quantity=?,total_price=?,total_purchese_price=? WHERE id='"+Table_Click+"' ");
                          pst.setString(1, str);    
                          pst.setString(2, str_final);  
                          pst.setString(3, str_purches);  
                          pst.executeUpdate(); 
                          show_products();
                      }
                      catch(Exception e){
                          System.out.println("Error With Database : "+e);
                      } 
                    }

                    //Code For Delete Row From Table
                    DefaultTableModel model = (DefaultTableModel) cart.getModel();
                    int SelectedRowIndex = cart.getSelectedRow();
                    if(cart.getSelectionModel().isSelectionEmpty()){
                        JOptionPane.showMessageDialog(this, "Please Select a Product First","Error", JOptionPane.QUESTION_MESSAGE); 
                    }
                    else{
                        model.removeRow(SelectedRowIndex);
                        getSum();
                    }
                }
            }
             catch(Exception ex){
               JOptionPane.showMessageDialog(null, ex);
            }
        }

    }//GEN-LAST:event_jButton8ActionPerformed

     
    public double getSum(){
        double sum = 0;
        double sub = 0;
       try{
            for(int i = 0; i < cart.getRowCount(); i++){
                sum = sum + Double.parseDouble(cart.getValueAt(i, 4).toString());               
            }              
            double roundOff = Math.floor(sum*100)/100;
            String str = Double.toString(roundOff);
            subtotal.setText(str);
        }
        catch(Exception e){
            System.out.println(e);
        }
       return sum;
    }    
    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed
        //Calculate Button        
        double Others = Double.parseDouble(others.getText());        
        double Subtotal = Double.parseDouble(subtotal.getText());
        double Vat = Double.parseDouble(vatt.getText());
        double Discount = Double.parseDouble(discount.getText());
        double Due = Double.parseDouble(due_money.getText());
        double Total = Subtotal+Vat+Due-Discount+Others; 
        
        String showoff = Double.toString(Total);
       // String showoffx = Double.toString(Totax);
        total_all.setText(showoff);
        //total_all.setText(showoffx);
        
        
        
        
        
    }//GEN-LAST:event_calculateActionPerformed

    private void customer_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_addActionPerformed
        //New User Button
        input_customer nc = new input_customer();
        nc.setVisible(true);
    }//GEN-LAST:event_customer_addActionPerformed

    public void show_customers(){
        selected_item.setSelectedItem("Customers");
        try{
           String Query =("SELECT id AS 'ID', username AS 'Username',first_name AS 'First Name',last_name AS 'Last Name',mobile AS 'Mobile',due AS 'Due',About,date AS 'Date' FROM customers ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }
    }
    
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    //Check User Sell Product               
        if(products.getSelectionModel().isSelectionEmpty()){
        selected_item.setSelectedItem("Customers");
        //Show All Customers           
            //JOptionPane.showMessageDialog(this, "Please Select a Customer First","Error", JOptionPane.QUESTION_MESSAGE); 
            sold_status.setText("Please Select a Customer First !");
            show_customers();
        }
        else{
            try{
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                String Query ="SELECT * FROM customers WHERE id='"+Table_Click+"'";           
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);
                if(rs.next()){
                    String User_Name=rs.getString("username");  
                    String Due=rs.getString("due");  
                    user.setText(User_Name);
                    due_money.setText(Due);
                }
                else{
                    due_money.setText("0");
                    user.setText("New");
                }
            }
            catch(Exception e){
               System.out.println("Error With Check User : "+e);         
            } 
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void cash_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cash_payActionPerformed
        //Cash Button
        String Total_Input = total_all.getText();
        paid.setText(Total_Input);
        if(Total_Input.equals("0") || Total_Input.equals("0.0") ){
            JOptionPane.showMessageDialog(this, "Press Calculate Button to Continue or Add Products.","Error", JOptionPane.QUESTION_MESSAGE); 
        }
        else{
             //Cash Button From Sell Products
            String SB = subtotal.getText();
            if(SB.isEmpty() || SB.equals("0")){
                sold_status.setText("You Must Buy a Product First !");
            }
            else{
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Confirmation Purchase", dialogButton);
                if(dialogResult == 0){
                    String Total = total_all.getText();
                    String User = user.getText();
                    String Payment = "PAID";
                    
                    String Vat = vatt.getText();
                    String Due = due_money.getText();
                    String Discount = discount.getText();
                    String Others = others.getText();
                            
                    get_settings s1=new get_settings();       
                    String Username = s1.noorder;

                    try{      
                         int Custom_ID = 1;
                        //Add Custom ID
                        String custom_id =("SELECT id FROM sold ORDER BY id DESC LIMIT 1");
                        PreparedStatement statement = db.connect().prepareStatement(custom_id);
                        ResultSet rs =statement.executeQuery(custom_id);              
                        if(rs.next()){
                            int Get_Custom_ID = rs.getInt("id");  
                            Custom_ID = Get_Custom_ID+1;
                        }
                        else{
                            Custom_ID = 1;                        }                       
                        
                        //Cash Sold Reprot
                        int rows=cart.getRowCount();
                        for(int row = 0; row<rows; row++)
                        {   
                            String Product_Name = (String)cart.getValueAt(row, 1);
                            String qty = (String)cart.getValueAt(row, 2);
                            String Price = (String)cart.getValueAt(row, 3);
                            String Purcehse_Price = (String)cart.getValueAt(row, 5);
                            double val1 = Double.parseDouble(qty);
                            double val2 = Double.parseDouble(Price);
                            double val3 = val1*val2;
                            double val4 = Math.round(val3);
                            String Total_Price = Double.toString(val4);
                            String Issuer = (String)cart.getValueAt(row, 6);                        
                            //System.out.printf("Product Name: %s\nQuantity: %s",Product_Name, qty);  
                            
                            PreparedStatement pst = db.connect().prepareStatement("INSERT INTO sold_report (Username,Product,Quantity,Price,Total_Price,Issuer,Date,Payment,purchase_price,Custom_ID)" + "VALUES (  '"+User+"' ,'"+Product_Name+"'  ,'"+qty+"' ,'"+Price+"' ,'"+Total_Price+"' ,'"+Issuer+"' , '"+Date+"' , '"+Payment+"' , '"+Purcehse_Price+"' , '"+Custom_ID+"')");
                            pst.executeUpdate();  
                        }
                        JOptionPane.showMessageDialog(null, "Successfully Sold Product !");
                      }
                    catch(Exception e ){
                        System.out.println("Errorxx 11 Connecting Database. "+e);
                    }
                    
                    try{    
                        //Shipping Checkbox
                        String Shipping ="";                            
                        if(shipping_checkbox.isSelected()){
                            Shipping = "Yes";
                        }
                        else{
                             Shipping = "No";
                        }     
                        //Insert into Sold       
                        PreparedStatement pst = db.connect().prepareStatement("INSERT INTO sold (Username , Total_Price , Paid , Payment , Issuer , Shipping , Date , Vat , Due , Discount , Others , Product_Cost)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1, User);
                        pst.setString(2, Total);
                        pst.setString(3, Total);
                        pst.setString(4, Payment);
                        pst.setString(5, Username);
                        pst.setString(6, Shipping);
                        pst.setString(7, Date);
                        pst.setString(8, Vat);
                        pst.setString(9, Due);
                        pst.setString(10, Discount);
                        pst.setString(11, Others);   
                        pst.setString(12, SB); 
                        pst.executeUpdate(); 
                        sold_status.setText("Product Sold Successfully !");
                    }
                    catch(Exception e){
                        System.out.println("Error Connecting Database. "+e);
                    } 
                    int opcion = JOptionPane.showConfirmDialog(null, "Do You want to Print This Page ?", "Print this Page", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) { //The ISSUE is here
                       print();
                      clear();
                      clear_btn();
                    } else {
                         clear();
                        clear_btn();
                    }              
                }
            }     
        }
    }//GEN-LAST:event_cash_payActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String Total_Input = total_all.getText();
        if(Total_Input.equals("0") ||  Total_Input.equals("0.0") ){
            JOptionPane.showMessageDialog(this, "Press Calculate Button to Continue or Add Products.","Error", JOptionPane.QUESTION_MESSAGE); 
        }
        else{
              //Due Button From Sell Products
            if(products.getSelectionModel().isSelectionEmpty()){
            selected_item.setSelectedItem("Customers");
            //Show All Customers           
                JOptionPane.showMessageDialog(this, "Please Select a Customer First","Error", JOptionPane.QUESTION_MESSAGE); 
                show_customers();
            }
           else{
                String SB = subtotal.getText();
                String Total_Ref = total_all.getText();
                if(SB.isEmpty() || SB.equals("0")){
                    sold_status.setText("You Must Buy a Product First !");
                }
                else if(Total_Ref.isEmpty() || Total_Ref.equals("0")){
                    JOptionPane.showMessageDialog(null, "Please Press Calculate Button to Complete. !");
                }
                else{
                   if(user.getText().equals("New")){
                       JOptionPane.showMessageDialog(this, "Please Select a Customer First","Error", JOptionPane.QUESTION_MESSAGE); 
                       show_customers();
                   }    
                   else{
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Confirmation Purchase", dialogButton);
                            if(dialogResult == 0){
                                String Total = total_all.getText();
                                String User = user.getText();
                                String Payment = "DUE";
                                    
                                String Vat = vatt.getText();
                                String Duex = due_money.getText();
                                String Discount = discount.getText();
                                String Others = others.getText();

                                
                                get_settings s1=new get_settings();       
                                String Admin = s1.noorder;

                                try{     
                                    //Add Custom ID
                                    String custom_id =("SELECT id FROM sold ORDER BY id DESC LIMIT 1");
                                    PreparedStatement statement = db.connect().prepareStatement(custom_id);
                                    ResultSet rs =statement.executeQuery(custom_id);              
                                    rs.next();
                                    int Get_Custom_ID = rs.getInt("id");  
                                    int Custom_ID = Get_Custom_ID+1;
                                    
                                    //Due Sold Report 
                                    int rows=cart.getRowCount();
                                    for(int row = 0; row<rows; row++){   
                                        String Product_Name = (String)cart.getValueAt(row, 1);
                                        String qty = (String)cart.getValueAt(row, 2);
                                        String Price = (String)cart.getValueAt(row, 3);
                                        String Purchese_Price = (String)cart.getValueAt(row, 5);
                                        double val1 = Double.parseDouble(qty);
                                        double val2 = Double.parseDouble(Price);
                                        double val3 = val1*val2;
                                        double val4 = Math.round(val3);
                                        String Total_Price = Double.toString(val4);
                                        String Issuer = (String)cart.getValueAt(row, 6);                        
                                        //System.out.printf("Product Name: %s\nQuantity: %s",Product_Name, qty);              
                                        PreparedStatement pst = db.connect().prepareStatement("INSERT INTO sold_report (Username,Product,Quantity,Price,Total_Price,Issuer,Date,Payment,purchase_price,Custom_ID)" + "VALUES (  '"+User+"' ,'"+Product_Name+"'  ,'"+qty+"' ,'"+Price+"' ,'"+Total_Price+"' ,'"+Issuer+"' , '"+Date+"' , '"+Payment+"' , '"+Purchese_Price+"' , '"+Custom_ID+"')");
                                        pst.executeUpdate(); 
                                    }
                                    JOptionPane.showMessageDialog(null, "Successfully Sold Product !");
                                  }
                                catch(Exception e ){
                                    System.out.println("Errorxx 22 Connecting Database xx4. "+e);
                                }                    
                                try{
                                    //Shipping Checkbox
                                    String Shipping ="";                            
                                    if(shipping_checkbox.isSelected()){
                                        Shipping = "Yes";
                                    }
                                    else{
                                         Shipping = "No";
                                    }     
                                    
                                    double Total_Double = Double.parseDouble(Total);
                                    String Paid = paid.getText();
                                    double Paid_Double = Double.parseDouble(Paid);
                                    double Due_Double = Total_Double -Paid_Double;
                                    String Due = Double.toString(Due_Double);
                                    PreparedStatement pst = db.connect().prepareStatement("INSERT INTO sold (Username , Total_Price , Paid , Issuer , Date , Payment , Shipping , Vat , Due , Discount , Others , Product_Cost)" + "VALUES (  '"+User+"' ,'"+Total+"'  ,'"+Paid+"' ,'"+Admin+"' ,'"+Date+"' , '"+Payment+"' , '"+Shipping+"' , '"+Vat+"' , '"+Duex+"' , '"+Discount+"' , '"+Others+"' , '"+SB+"')");
                                    pst.executeUpdate();
                                    sold_status.setText("Product Sold Successfully !");     
                                    try{
                                        PreparedStatement pstx = db.connect().prepareStatement("UPDATE customers SET due=? , Expected_Payment_Date=? WHERE username='"+User+"' ");
                                        pstx.setString(1, Due);
                                        pstx.setString(2, Date);
                                        pstx.executeUpdate(); 
                                    }
                                    catch(Exception e ){
                                        System.out.println("Error Connecting Database xx5. "+e);
                                    }                                                              
                                }
                                catch(Exception e){
                                    System.out.println("Error Connecting Database xx3. "+e);
                                } 
                                int opcion = JOptionPane.showConfirmDialog(null, "Do You want to Print This Page ?", "Print this Page", JOptionPane.YES_NO_OPTION);
                                if (opcion == 0) { //The ISSUE is here
                                   print();
                                  clear();
                                  clear_btn();
                                } else {
                                     clear();
                                    clear_btn();
                                }  
                            }
                        }  
                   }
               }  
          }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void sell_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sell_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sell_qtyActionPerformed
    
//Clear Cart Product
    private void clear_btn(){        
        DefaultTableModel dm = (DefaultTableModel)cart.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
    
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

    }//GEN-LAST:event_jButton14ActionPerformed

    public void refill_tickets(){
        selected_item.setSelectedItem("Refill Ticket");
        try{
           String Query =("SELECT id AS 'ID',product_name AS 'Product Name',price AS 'Price' ,date AS 'Date' FROM refill ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
         }
    }
    
    
    private void get_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyPressed
        //Search Enter Clicked 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            search ();
        }
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }   
    }//GEN-LAST:event_get_product_nameKeyPressed

    private void get_product_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_get_product_nameKeyTyped
        search ();
    }//GEN-LAST:event_get_product_nameKeyTyped

    public void refill_products(){
         //Refill Sell
        selected_item.setSelectedItem("Refill Products");
        try{
           String Query =("SELECT id AS ID , product_name AS 'Product Name' , quantity AS Quantity , price AS Price , total_price AS 'Total Price' , date FROM refill_to_sell ORDER BY id DESC");
           PreparedStatement statement = db.connect().prepareStatement(Query);
           ResultSet rs =statement.executeQuery(Query);
           products.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
        }
    }
    private void selected_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selected_itemActionPerformed
        // Select Box Sell Page
        //Products Customers Refill Ticket Refill Products
        String selecteditem = String.valueOf(selected_item.getSelectedItem());
        if(selecteditem.equals("Products")){
            show_products();
        }
        else if(selecteditem.equals("Customers")){
            show_customers();
        }
        else if(selecteditem.equals("Refill Ticket")){
            refill_tickets();
        }
        else if(selecteditem.equals("Refill Products")){
            refill_products();
        }
    }//GEN-LAST:event_selected_itemActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        selected_item.setSelectedItem("Products");
        show_products();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        show_customers();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // Show All Refill Tickets
        refill_tickets();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        //Refill Products
        refill_products();
    }//GEN-LAST:event_jButton17ActionPerformed

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

    private void plus_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus_productActionPerformed
        //Plus Button
        String Old = sell_qty.getText();
        int Get_New = Integer.parseInt(Old);
        int Final = Get_New+1;
        String Final_Exc = Integer.toString(Final);
        sell_qty.setText(Final_Exc);
    }//GEN-LAST:event_plus_productActionPerformed

    private void shipping_checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shipping_checkboxActionPerformed
        //Shipping Checkbox
       if(shipping_checkbox.isSelected()){
            selected_item.setSelectedItem("Customers");
            sold_status.setText("Make Sure You Add a Customer Name !");
            try{    
                String Cost = "";
                String Query ="SELECT * FROM settings";   
                PreparedStatement statement = db.connect().prepareStatement(Query);
                ResultSet rs =statement.executeQuery(Query);            
                if(rs.next()){
                    Cost = rs.getString("Shipping_Cost");                
                }
                others.setText(Cost);
                db.disconnect();
            }
            catch(Exception e){
                
            }               
        }
        else{
           others.setText("0");
            selected_item.setSelectedItem("Products");
        }
    }//GEN-LAST:event_shipping_checkboxActionPerformed

    private void productsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMousePressed
         //Products Mouse Pressed
        //String selecteditem = String.valueOf(selected_item.getSelectedItem());
        //if(selecteditem.equals("Customers")){
           
        //}
    }//GEN-LAST:event_productsMousePressed

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paidActionPerformed

    private void selected_item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selected_item1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_item1ActionPerformed

    private void get_product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_product_nameActionPerformed

    private void productsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productsKeyPressed
        // TODO add your handling code here:
//        if(evt.getKeyCode() == KeyEvent.VK_A){
//             add_to_cart.requestFocusInWindow();
//        }
        
    }//GEN-LAST:event_productsKeyPressed

    private void subtotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_subtotalKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }          
    }//GEN-LAST:event_subtotalKeyPressed

    private void calculateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calculateKeyPressed
        //Keyboard Button Event   
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }  
    }//GEN-LAST:event_calculateKeyPressed

    private void userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }     
    }//GEN-LAST:event_userKeyPressed

    private void sell_qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sell_qtyKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }   
    }//GEN-LAST:event_sell_qtyKeyPressed

    private void add_to_cartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_to_cartKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }          
    }//GEN-LAST:event_add_to_cartKeyPressed

    private void plus_productKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_plus_productKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }  
    }//GEN-LAST:event_plus_productKeyPressed

    private void cash_payKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cash_payKeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        } 
    }//GEN-LAST:event_cash_payKeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        //Keyboard Button Event 
        if(evt.getKeyCode() == KeyEvent.VK_F1){
             get_product_name.requestFocusInWindow();
             get_product_name.setText("");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F2){ 
             user.requestFocusInWindow();        
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F3){ 
             sell_qty.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F4){ 
             subtotal.requestFocusInWindow();        
        }  
        else if(evt.getKeyCode() == KeyEvent.VK_F5){ 
             calculate.requestFocusInWindow();        
        }  
    }//GEN-LAST:event_jButton7KeyPressed

    //Code For Print Page Starts Here    
    public PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();   
        double middleHeight =8.0;  
        double headerHeight = 2.0;                  
        double footerHeight = 2.0;                  
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
        paper.setSize(width, height);
        paper.setImageableArea(                    
            0,
            10,
            width,            
            height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points            
        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);    
        return pf;
    }    
    protected static double convert_CM_To_PPI(double cm) {            
	return toPPI(cm * 0.393600787);            
    } 
    protected static double toPPI(double inch) {            
        return inch * 72d;            
    }
    public class BillPrintable implements Printable{
    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) throws PrinterException{ 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {     
            Graphics2D g2d = (Graphics2D) graphics;  
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
            ////////// code by alqama//////////////
            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
            //int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
        try{           
            get_settings s1=new get_settings();       
            String Admin = s1.noorder;
            
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
                try{
                    String Query ="SELECT * FROM settings";           
                    PreparedStatement statement = db.connect().prepareStatement(Query);
                    ResultSet rs =statement.executeQuery(Query);
                    if(rs.next()){
                        String Company=rs.getString("company_info");
                        String Address=rs.getString("address");
                        String Mobile =rs.getString("mobile");
                        
                        g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                        g2d.drawString("-----------------------------------------------",12,y);y+=yShift;
                        
                        int stringLen = (int) g2d.getFontMetrics().getStringBounds(Company, g2d).getWidth();
                        int start = (int) (width/2 - stringLen/2);
                        g2d.drawString(Company, start + 12, y);
                        y+=yShift;
                        stringLen = (int) g2d.getFontMetrics().getStringBounds(Address, g2d).getWidth();
                        start = (int) (width/2 - stringLen/2);
                        g2d.drawString(Address, start + 13, y);
                        y+=yShift;
                        g2d.drawString("-----------------------------------------------",12,y);
                        y+=headerRectHeight;
                        
                        
                        /*
                        g2d.drawString("         "+Company+"            ",12,y);y+=yShift;
                        g2d.drawString(""+Address+"   ",13,y);y+=yShift;
                        g2d.drawString("-----------------------------------------------",12,y);y+=headerRectHeight;

                        g2d.drawString(" Customer Name :           "+user.getText()+"  ",10,y);y+=yShift;
                        //g2d.drawString(" Invoice No :              01                  ",10,y);y+=yShift;   
                        g2d.drawString(" Date :                    "+Date+"            ",10,y);y+=yShift;
                        g2d.drawString(" Previous Due :            "+due_money.getText()+"  ",10,y);y+=yShift;    
                        g2d.drawString("------------------------------------------------",10,y);y+=headerRectHeight;
                        g2d.drawString(" Product Name(Qty)         Price(unit)          ",10,y);y+=yShift;
                        g2d.drawString("------------------------------------------------",10,y);y+=headerRectHeight;
                        */
                        g2d.drawString(" Customer Name ",10,y);
                        stringLen = (int) g2d.getFontMetrics().getStringBounds(user.getText(), g2d).getWidth();
                        start = (int) (width - stringLen-1);
                        g2d.drawString(user.getText(), start , y);
                        y+=yShift;
                        
                        // Date..............
                        
                        g2d.drawString(" Date ",10,y);
                        stringLen = (int) g2d.getFontMetrics().getStringBounds(Date, g2d).getWidth();
                        start = (int) (width - stringLen-1);
                        g2d.drawString(Date, start , y);
                        y+=yShift;
                        
                        // Previous due..............
                        
                        g2d.drawString(" Previous Due ",10,y);
                        stringLen = (int) g2d.getFontMetrics().getStringBounds(due_money.getText(), g2d).getWidth();
                        start = (int) (width - stringLen-1);
                        g2d.drawString(due_money.getText(), start , y);
                        y+=yShift;
                        g2d.drawString("-----------------------------------------------",10,y);
                        y+=yShift;
                        
                        // product list with price.............
                        
                        g2d.drawString(" Product Name(Qty)",10,y);
                        stringLen = (int) g2d.getFontMetrics().getStringBounds("Price(unit)", g2d).getWidth();
                        start = (int) (width - stringLen-1);
                        g2d.drawString("Price(unit)", start , y);
                        y+=yShift;
                        
                        
                        
                        
                        try{                   
                             int rows=cart.getRowCount();
                             for(int row = 0; row<rows; row++){   
                                 String Product_Name = (String)cart.getValueAt(row, 1);
                                 String qty = (String)cart.getValueAt(row, 2);
                                 String Price = (String)cart.getValueAt(row, 3);   
                                 //String Total_Price = (String)cart.getValueAt(row, 4); 
                                 
                                 g2d.drawString(" "+Product_Name+"("+qty+")",10,y);
                                 stringLen = (int) g2d.getFontMetrics().getStringBounds(Price, g2d).getWidth();
                                 start = (int) (width - stringLen-1);
                                 g2d.drawString(Price, start , y);
                                 y+=yShift;
                             } 
                         }
                         catch(Exception exc){
                           System.out.println(exc.getMessage());
                         }
                        
                       g2d.drawString("-----------------------------------------------",10,y);
                       y+=yShift;
                       g2d.drawString(" Total amount:",10,y);
                       stringLen = (int) g2d.getFontMetrics().getStringBounds(total_all.getText(), g2d).getWidth();
                       start = (int) (width - stringLen-1);
                       g2d.drawString(total_all.getText(), start , y);
                       y+=yShift;
                       /*
                        int val = (int) g2d.getFontMetrics().getStringBounds("445454", g2d).getWidth();
                       start = (int) (width - val-1);
                       g2d.drawString("445454", start , y);
                       y+=yShift;
                       
                       val = (int) g2d.getFontMetrics().getStringBounds("42", g2d).getWidth();
                       start = (int) (width - val-1);
                       g2d.drawString("42", start , y);
                       y+=yShift;
                       */
                       g2d.drawString(" Vat:",10,y);
                       stringLen = (int) g2d.getFontMetrics().getStringBounds(vatt.getText(), g2d).getWidth();
                       start = (int) (width - stringLen-1);
                       g2d.drawString(vatt.getText(), start , y);
                       y+=yShift;
                       g2d.drawString(" Discount:",10,y);
                       stringLen = (int) g2d.getFontMetrics().getStringBounds(discount.getText(), g2d).getWidth();
                       start = (int) (width - stringLen-1);
                       g2d.drawString(discount.getText(), start , y);
                       y+=yShift;
                       g2d.drawString(" Paid:",10,y);
                       stringLen = (int) g2d.getFontMetrics().getStringBounds(paid.getText(), g2d).getWidth();
                       start = (int) (width - stringLen-1);
                       g2d.drawString(paid.getText(), start , y);
                       y+=yShift;
                       String adminStr = " Issued By: "+Admin;
                       stringLen = (int) g2d.getFontMetrics().getStringBounds(adminStr, g2d).getWidth();
                       start = (int) (width/2 - stringLen/2);
                       g2d.drawString(adminStr, start + 12, y);
                       y+=yShift;
                       
                       
                     /*   
                     g2d.drawString("-----------------------------------------------",10,y);y+=yShift;
                     g2d.drawString(" Total amount: "+total_all.getText()+"         ",10,y);y+=yShift;
                     g2d.drawString(" Vat : "+vatt.getText()+"         ",10,y);y+=yShift;
                     g2d.drawString(" Discount : "+discount.getText()+"         ",10,y);y+=yShift;
                     g2d.drawString(" Paid : "+paid.getText()+"         ",10,y);y+=yShift;
                     g2d.drawString(" Issued By: "+Admin+"                          ",10,y);y+=yShift;
                     */
                     g2d.drawString("-----------------------------------------------",10,y);y+=yShift;
                     g2d.drawString("                Contact Us                     ",10,y);y+=yShift;
                     g2d.drawString("               "+Mobile+"                     ",10,y);y+=yShift;
                     g2d.drawString("***********************************************",10,y);y+=yShift;
                     g2d.drawString("         Thank You, Visit Us Again             ",10,y);y+=yShift;
                     g2d.drawString("***********************************************",10,y);y+=yShift;
                    }
                }
                catch(Exception e){

                }                
        }
        catch(Exception r){
            r.printStackTrace();
        }
        result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }    
    private void print(){
       int count= cart.getModel().getRowCount(); 
       String total =total_all.getText();
       double total_final=Double.parseDouble(total);  

       if(count>0 && total_final>0){
            PrinterJob pj = PrinterJob.getPrinterJob();        
             pj.setPrintable(new BillPrintable(),getPageFormat(pj));
             try {
                  pj.print();

             }
              catch (PrinterException ex) {
                      System.out.println("Error With Printing !");
            }
       }
       else{
           JOptionPane.showMessageDialog(this, "Add Product and Calculate First","Error", JOptionPane.QUESTION_MESSAGE); 
       }
    }
    
  
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
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new sell().setVisible(true);
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
    private javax.swing.JButton add_to_cart;
    private javax.swing.JButton calculate;
    private javax.swing.JTable cart;
    private javax.swing.JButton cash_pay;
    private javax.swing.JButton customer_add;
    private javax.swing.JTextField discount;
    private javax.swing.JTextField due_money;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField others;
    private javax.swing.JTextField paid;
    private javax.swing.JButton plus_product;
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JButton search_button;
    private javax.swing.JComboBox<String> selected_item;
    private javax.swing.JComboBox<String> selected_item1;
    private javax.swing.JTextField sell_qty;
    private javax.swing.JCheckBox shipping_checkbox;
    private javax.swing.JPanel sidebar;
    private javax.swing.JLabel sold_status;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTextField total_all;
    private javax.swing.JTextField user;
    private javax.swing.JTextField vatt;
    // End of variables declaration//GEN-END:variables
}
