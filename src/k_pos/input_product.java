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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import static java.lang.Math.abs;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
/**
 *
 * @author Najmul
 */

public class input_product extends javax.swing.JFrame {
    db_connect db = new db_connect();
  
    LocalDate localDate = LocalDate.now();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Date = localDate.format(formatter);
    
    public input_product() {
        full_screen();
        initComponents();   
        this.setExtendedState(MAXIMIZED_BOTH); 
        seticon();
        theme();
        exit();
        show_products();
        products.setDefaultEditor(Object.class, null); 
        products.setShowGrid(true); 
    }
    
    private void seticon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
    }  
    
    public static void createImage(String image_name,String myString)  {
        String Computer_Username = System.getProperty("user.name");
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            //write to png file
            FileOutputStream fos = new FileOutputStream("C:\\Users\\"+Computer_Username+"\\Desktop\\"+image_name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            } 
        catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    void theme(){ 
        get_settings get=new get_settings();    
        String Company = get.Company;
        String Version = get.Version;
        footer.setText(Company+" "+Version);  
        Color content_color = get.Content_Color;
        getContentPane().setBackground(content_color);   
        Color sidebar_color = get.Sidebar_Color;
        jScrollPane2.setBackground(content_color); 
        jPanel2.setBackground(content_color);         
        jPanel1.setBackground(content_color); 
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
    
    
        
    
    void show_products(){    
        products.getTableHeader().setEnabled(false);
        products.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,17));
        try{
           String Query =("SELECT id AS ID, Name, Price, Quantity, doe AS 'Expire Date' FROM products ORDER BY id desc LIMIT 100");
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

        catagory = new javax.swing.JFrame();
        catagory_name = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        show_catagory = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        notification_catagory = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        catagory_details = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        get_product_name = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        footer = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        notification_product = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        purchases_price = new javax.swing.JTextField();
        selling_price = new javax.swing.JTextField();
        qty_alert = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        location = new javax.swing.JTextField();
        company = new javax.swing.JTextField();
        doe = new com.toedter.calendar.JDateChooser();
        product_name = new javax.swing.JTextField();
        article_no = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        type = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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

        catagory.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        catagory.setTitle("Input Catagory");
        catagory.setAlwaysOnTop(true);
        catagory.setMinimumSize(new java.awt.Dimension(785, 400));
        catagory.setResizable(false);
        catagory.setType(java.awt.Window.Type.POPUP);
        catagory.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                catagoryWindowActivated(evt);
            }
        });

        catagory_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        catagory_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        catagory_name.setText(" Catagory Name");
        catagory_name.setToolTipText("Search by id or Product Name");
        catagory_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        catagory_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                catagory_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                catagory_nameFocusLost(evt);
            }
        });
        catagory_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                catagory_nameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                catagory_nameKeyTyped(evt);
            }
        });

        jScrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        show_catagory.setAutoCreateRowSorter(true);
        show_catagory.setBackground(new java.awt.Color(238, 238, 238));
        show_catagory.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        show_catagory.setForeground(new java.awt.Color(42, 42, 42));
        show_catagory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        show_catagory.setToolTipText("All Products");
        show_catagory.setAlignmentX(5.0F);
        show_catagory.setAlignmentY(5.0F);
        show_catagory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        show_catagory.setEditingColumn(0);
        show_catagory.setEditingRow(0);
        show_catagory.setFocusable(false);
        show_catagory.setGridColor(new java.awt.Color(209, 206, 206));
        show_catagory.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        show_catagory.setName("All Products"); // NOI18N
        show_catagory.setOpaque(false);
        show_catagory.setRequestFocusEnabled(false);
        show_catagory.setRowHeight(30);
        show_catagory.setSelectionBackground(new java.awt.Color(186, 186, 236));
        show_catagory.setSelectionForeground(new java.awt.Color(0, 0, 0));
        show_catagory.getTableHeader().setReorderingAllowed(false);
        show_catagory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show_catagoryMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(show_catagory);

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/add.png"))); // NOI18N
        jButton20.setToolTipText("Add");
        jButton20.setFocusable(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jButton21.setToolTipText("Add");
        jButton21.setFocusable(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/clear.png"))); // NOI18N
        jButton22.setToolTipText("Add");
        jButton22.setFocusable(false);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/delete.png"))); // NOI18N
        jButton23.setToolTipText("Add");
        jButton23.setFocusable(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        notification_catagory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notification_catagory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/help.png"))); // NOI18N
        notification_catagory.setText("Catagory Status");

        catagory_details.setColumns(20);
        catagory_details.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        catagory_details.setRows(5);
        catagory_details.setText("Catagory Details");
        catagory_details.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                catagory_detailsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                catagory_detailsFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(catagory_details);

        javax.swing.GroupLayout catagoryLayout = new javax.swing.GroupLayout(catagory.getContentPane());
        catagory.getContentPane().setLayout(catagoryLayout);
        catagoryLayout.setHorizontalGroup(
            catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catagoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(catagory_name)
                        .addComponent(jScrollPane3))
                    .addGroup(catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(notification_catagory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, catagoryLayout.createSequentialGroup()
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(jButton21)
                            .addGap(8, 8, 8)
                            .addComponent(jButton23)
                            .addGap(8, 8, 8)
                            .addComponent(jButton22))))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        catagoryLayout.setVerticalGroup(
            catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catagoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(catagory_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(catagoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(notification_catagory)
                .addContainerGap(129, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Input Product");
        setMinimumSize(new java.awt.Dimension(1212, 626));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Add New Product");

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
        products.setAlignmentX(0.0F);
        products.setAlignmentY(0.0F);
        products.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        products.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        products.setDoubleBuffered(true);
        products.setEditingColumn(0);
        products.setEditingRow(0);
        products.setFocusable(false);
        products.setGridColor(new java.awt.Color(209, 206, 206));
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
        });
        jScrollPane1.setViewportView(products);
        products.getAccessibleContext().setAccessibleName("");

        get_product_name.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        get_product_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        get_product_name.setToolTipText("Search by id or Product Name");
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/input_product.png"))); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(34, 49, 63));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Input Refill");
        jButton9.setBorder(null);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        notification_product.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notification_product.setForeground(new java.awt.Color(255, 255, 255));

        jButton11.setBackground(new java.awt.Color(34, 49, 63));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Product Type");
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
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(footer)
                    .addComponent(notification_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
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
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(notification_product, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footer)
                .addContainerGap())
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);

        purchases_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        purchases_price.setText(" Purchases Price");
        purchases_price.setToolTipText("Purchases Price");
        purchases_price.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        purchases_price.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                purchases_priceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                purchases_priceFocusLost(evt);
            }
        });
        purchases_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchases_priceActionPerformed(evt);
            }
        });

        selling_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selling_price.setText(" Selling Price");
        selling_price.setToolTipText("Selling Price");
        selling_price.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        selling_price.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selling_priceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                selling_priceFocusLost(evt);
            }
        });
        selling_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selling_priceActionPerformed(evt);
            }
        });

        qty_alert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        qty_alert.setText(" Re Order Level");
        qty_alert.setToolTipText("Minimun Quantity for Alert");
        qty_alert.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        qty_alert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                qty_alertFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                qty_alertFocusLost(evt);
            }
        });

        qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        qty.setText(" Quantity");
        qty.setToolTipText("Quantity");
        qty.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        qty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                qtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                qtyFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Expire Date");

        location.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        location.setText(" Location");
        location.setToolTipText("Location");
        location.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        location.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                locationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                locationFocusLost(evt);
            }
        });

        company.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        company.setText(" Company");
        company.setToolTipText("Company Name");
        company.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        company.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                companyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                companyFocusLost(evt);
            }
        });

        doe.setToolTipText("Date of Expire");
        doe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        product_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        product_name.setText(" Product Name");
        product_name.setToolTipText("Product Name");
        product_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        product_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                product_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                product_nameFocusLost(evt);
            }
        });
        product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_nameActionPerformed(evt);
            }
        });

        article_no.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        article_no.setText(" Article No");
        article_no.setToolTipText("Selling Price");
        article_no.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 195, 195), 1, true));
        article_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                article_noFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                article_noFocusLost(evt);
            }
        });
        article_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                article_noActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Barcode Printing");

        jButton2.setText("Generage");
        jButton2.setMaximumSize(new java.awt.Dimension(90, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(90, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setText("Print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Type" }));
        type.setToolTipText("Product Catagory or Type");
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Catagory");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(location, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qty_alert, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(company, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qty, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(purchases_price, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selling_price, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(product_name, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(article_no, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(doe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(selling_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(purchases_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(company, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(qty_alert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(type, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(doe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(article_no, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        jScrollPane2.setViewportView(jPanel2);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/add.png"))); // NOI18N
        jButton1.setToolTipText("Add Product");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/updte.png"))); // NOI18N
        jButton3.setToolTipText("Update Product");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/delete.png"))); // NOI18N
        jButton4.setToolTipText("Delete Product");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/icons/clear.png"))); // NOI18N
        jButton6.setToolTipText("Clear");
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(get_product_name, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(get_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selling_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selling_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selling_priceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Add Product
        String Product_Name = product_name.getText();
        String Price = selling_price.getText();
        String Purchase_Price = purchases_price.getText();
        String Quantity = qty.getText();
        String Company = company.getText(); 
        String Type = String.valueOf(type.getSelectedItem());
        String Qty_Alert = qty_alert.getText(); 
        String Location = location.getText(); 
        String Article_No = article_no.getText();      
        
        get_settings s1=new get_settings();       
        String Username = s1.noorder;
        
        if(Product_Name.isEmpty() || Username.isEmpty() || Price.isEmpty() || Purchase_Price.isEmpty() || Quantity.isEmpty() || Company.isEmpty()  || Type.isEmpty() || Qty_Alert.isEmpty() || Date.isEmpty() || Location.isEmpty() || doe.getDate() == null){
            JOptionPane.showMessageDialog(null, "Please Fill Up All Input Field.", "Alert", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
            String Doe = dcn.format(doe.getDate() );  
            
            //Price * Quantity of Every Product
            double value1 = Double.valueOf(Price);
            double value2 = Double.valueOf(Quantity);
            double value3 = value1*value2;
            String Total_Price = String.valueOf(value3);

            //Purcheses Price * Quantity of Every Product
            double value4 = Double.valueOf(Purchase_Price);
            double value5 = value4*value2;
            String Total_purches_Price = String.valueOf(value5);
            try{     
                //Check Duplicate Product Name
                String Check_Product_Name ="SELECT Name FROM products WHERE Name='"+Product_Name+"' ";           
                PreparedStatement statement = db.connect().prepareStatement(Check_Product_Name);
                ResultSet rs_qu =statement.executeQuery(Check_Product_Name);
                if(rs_qu.next()){
                     notification_product.setText("Same Product Name Found !");     
                }
                else{
                    PreparedStatement pst = db.connect().prepareStatement("INSERT INTO products (Name , Issuer , Price , Purchase_Price , Quantity , Company , Type , qty_alert , Date , doe , Location , total_price , total_purchese_price , Article_No)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, Product_Name);
                    pst.setString(2, Username);
                    pst.setString(3, Price);
                    pst.setString(4, Purchase_Price);
                    pst.setString(5, Quantity);
                    pst.setString(6, Company);
                    pst.setString(7, Type);
                    pst.setString(8, Qty_Alert);
                    pst.setString(9, Date);
                    pst.setString(10, Doe);
                    pst.setString(11, Location);
                    pst.setString(12, Total_Price);
                    pst.setString(13, Total_purches_Price);
                    pst.setString(14, Article_No);
                    pst.executeUpdate();     
                    notification_product.setText("Product Successfully Added ! "); 
                    clear();                     
                 
                }
            }
            catch(Exception e){
                System.out.println("Error Connecting Database. "+e);
            }     
            show_products();
        }            
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Update Product
        if(products.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please Select a Product First","Error", JOptionPane.QUESTION_MESSAGE);
        }
        else{  
            String Product_Name = product_name.getText();
            String Price = selling_price.getText();  
            String Purchase_Price = purchases_price.getText();
            String Quantity = qty.getText();   
            String Company = company.getText();
            String Type = String.valueOf(type.getSelectedItem());
            String Qty_Alert = qty_alert.getText(); 
            String Location = location.getText(); 
            String Article_No = article_no.getText();  
            
            get_settings s1=new get_settings();       
            String Username = s1.noorder;

            
            if(Product_Name.isEmpty() || Username.isEmpty() || Price.isEmpty() || Purchase_Price.isEmpty() || Quantity.isEmpty() || Company.isEmpty()  || Type.isEmpty() || Qty_Alert.isEmpty() || Date.isEmpty() || Location.isEmpty() || doe.getDate() == null){
                JOptionPane.showMessageDialog(null, "Please Fill Up All Input Field.", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                //Price * Quantity of Every Product
                double value1 = Double.valueOf(Price);
                double value2 = Double.valueOf(Quantity);
                double value3 = value1*value2;
                String Total_Price = String.valueOf(value3);

                //Purcheses Price * Quantity of Every Product
                double value4 = Double.valueOf(Purchase_Price);
                double value5 = value4*value2;
                String Total_purches_Price = String.valueOf(value5);

                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String Doe = dcn.format(doe.getDate() );
                
                //Get Selected Row
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                try{                     
                    PreparedStatement pst = db.connect().prepareStatement("UPDATE products SET Name=?,Issuer=?,Price=?,Purchase_Price=?,Quantity=?,Company=? ,Type=?,qty_alert=?,date=?,doe=?,Location=?,total_price=?,total_purchese_price=? , Article_No=? WHERE id='"+Table_Click+"' ");
                    pst.setString(1, Product_Name);
                    pst.setString(2, Username);
                    pst.setString(3, Price);
                    pst.setString(4, Purchase_Price);
                    pst.setString(5, Quantity);  
                    pst.setString(6, Company);
                    pst.setString(7, Type);
                    pst.setString(8, Qty_Alert);
                    pst.setString(9, Date);
                    pst.setString(10, Doe);
                    pst.setString(11, Location);
                    pst.setString(12, Total_Price);
                    pst.setString(13, Total_purches_Price);
                    pst.setString(14, Article_No);
                    pst.executeUpdate(); 
                    notification_product.setText("Product Updated !");    
                    clear();
                }
                catch(Exception e){
                    System.out.println("My Sql is Not Running : "+e);
                    notification_product.setText("Error !"); 
                }        
               show_products();
            }            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void search(){
        String Product_Name = get_product_name.getText();       
        if(Product_Name.isEmpty()){
           show_products();
        }
        else{
            try{
             String Query =("SELECT id AS ID,Name,Price,Quantity,doe AS 'Expire Date' FROM products WHERE Name LIKE '%"+Product_Name+"%' OR id LIKE '%"+Product_Name+"%'");
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
        //Search Button
      search();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
        //Table Mouse OnClick Event
        try{
            int row = products.getSelectedRow();
            String Table_Click = (products.getModel().getValueAt(row,0).toString());
            String Query ="SELECT * FROM products WHERE id='"+Table_Click+"'";           
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            if(rs.next()){
                String add1=rs.getString("Name");
                product_name.setText(add1);
                
                String add2=rs.getString("Price");
                selling_price.setText(add2); 
                
                String add3=rs.getString("Purchase_Price");
                purchases_price.setText(add3); 
                
                String add4=rs.getString("Quantity");
                qty.setText(add4);
                
                String add5=rs.getString("Company");
                company.setText(add5);
                
                String add6=rs.getString("Type");
                type.setSelectedItem(add6);
                
                String add9=rs.getString("qty_alert");
                qty_alert.setText(add9);
                
                String add10=rs.getString("Location");
                location.setText(add10);
                
                String add11=rs.getString("Article_No");
                article_no.setText(add11);
                
                String add7=rs.getString("doe");
                java.util.Date add8 = new SimpleDateFormat("yyyy-MM-dd").parse(add7);
                doe.setDate(add8);
            }
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_productsMouseClicked
    public void clear(){
        try{
            product_name.setText(" Product Name");
            selling_price.setText(" Selling Price");
            type.setSelectedItem("Product Type");
            location.setText(" Location");
            purchases_price.setText(" Purchases Price");
            qty_alert.setText(" Re Order Level");
            qty.setText(" Quantity");
            company.setText(" Company");
            doe.setDate(new Date());
            article_no.setText(" Article No");
        }
        catch(Exception e){
            notification_product.setText("Error Somewhere !");
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Clear Product
        clear(); 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void purchases_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchases_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchases_priceActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Delete Product
        get_settings s1=new get_settings();
        String User_Type = s1.User_Type;
        String Delete_Access = s1.Delete_Access;
        if(User_Type.equals("Yes") || Delete_Access.equals("Yes")){
            if(products.getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(this, "Please Select a Product First","Error", JOptionPane.QUESTION_MESSAGE);
            }
            else{
                int row = products.getSelectedRow();
                String Table_Click = (products.getModel().getValueAt(row,0).toString());
                try{
                    PreparedStatement pst = db.connect().prepareStatement("DELETE FROM products WHERE id='"+Table_Click+"' ");
                    pst.execute();
                    notification_product.setText("Product Deleted !");
                    clear();
                }
                catch(Exception e){
                    System.out.println("My Sql is Not Running : "+e);
                    notification_product.setText("Error !");
                }
                show_products();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You Don't Hace Authority To Access This Page.", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void product_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_product_nameFocusGained
        if(product_name.getText().equals(" Product Name")){
            product_name.setText("");
            product_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_product_nameFocusGained

    private void product_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_product_nameFocusLost
        if(product_name.getText().trim().equals("")){
            product_name.setText(" Product Name");
            product_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_product_nameFocusLost

    private void selling_priceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selling_priceFocusGained
         if(selling_price.getText().equals(" Selling Price")){
            selling_price.setText("");
            selling_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_selling_priceFocusGained

    private void selling_priceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selling_priceFocusLost
        if(selling_price.getText().trim().equals("")){
            selling_price.setText(" Selling Price");
            selling_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_selling_priceFocusLost

    private void article_noFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_article_noFocusGained
        if(article_no.getText().equals(" Article No")){
            article_no.setText("");
            article_no.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_article_noFocusGained

    private void article_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_article_noFocusLost
        if(article_no.getText().trim().equals("")){
            article_no.setText(" Article No");
            article_no.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_article_noFocusLost

    private void article_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_article_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_article_noActionPerformed

    private void purchases_priceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_purchases_priceFocusGained
        if(purchases_price.getText().equals(" Purchases Price")){
            purchases_price.setText("");
            purchases_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_purchases_priceFocusGained

    private void purchases_priceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_purchases_priceFocusLost
        if(purchases_price.getText().trim().equals("")){
            purchases_price.setText(" Purchases Price");
            purchases_price.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_purchases_priceFocusLost

    private void qtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qtyFocusGained
         if(qty.getText().equals(" Quantity")){
            qty.setText("");
            qty.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_qtyFocusGained

    private void qtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qtyFocusLost
        if(qty.getText().trim().equals("")){
            qty.setText(" Quantity");
            qty.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_qtyFocusLost

    private void companyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_companyFocusGained
        if(company.getText().equals(" Company")){
            company.setText("");
            company.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_companyFocusGained

    private void companyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_companyFocusLost
        if(company.getText().trim().equals("")){
            company.setText(" Company");
            company.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_companyFocusLost

    private void qty_alertFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qty_alertFocusGained
        if(qty_alert.getText().equals(" Re Order Level")){
            qty_alert.setText("");
            qty_alert.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_qty_alertFocusGained

    private void qty_alertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qty_alertFocusLost
        if(qty_alert.getText().trim().equals("")){
            qty_alert.setText(" Re Order Level");
            qty_alert.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_qty_alertFocusLost

    private void locationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_locationFocusGained
        if(location.getText().equals(" Location")){
            location.setText("");
            location.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_locationFocusGained

    private void locationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_locationFocusLost
        if(location.getText().trim().equals("")){
            location.setText(" Location");
            location.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_locationFocusLost

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //Refill input Product
        input_refill ri= new input_refill();
        ri.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

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
        String Article_No = article_no.getText();
        int int_random = abs(ThreadLocalRandom.current().nextInt());  
        String str1 = Integer.toString(int_random); 
        article_no.setText(str1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // Select Box Sell Page
        //Products Customers Refill Ticket Refill Products
        String selecteditem = String.valueOf(type.getSelectedItem());
        if(selecteditem.equals("Products")){

        }
    }//GEN-LAST:event_typeActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Window Activated 
        //Load Type
        try{
            String Query ="SELECT Type_Name FROM product_types";   
            PreparedStatement statement2 = db.connect().prepareStatement(Query);
            ResultSet rs =statement2.executeQuery(Query);  
            type.removeAllItems();            
            type.addItem("Product Type");
            while(rs.next()){                          
                String result = rs.getString(1);
                if(result !=null){
                    result = result.trim();     
                    type.addItem(result);
                }
            } 
        }
        catch(Exception e){

        }
    }//GEN-LAST:event_formWindowActivated

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //Catagory Popup
        catagory.setTitle("Product Catagory");
        catagory.setVisible(true);
        catagory.setLocationRelativeTo(null);//for shoeing this in center
        catagory.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
    }//GEN-LAST:event_jButton11ActionPerformed

    private void catagory_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_catagory_nameFocusGained
        //Focus Gained
        if(catagory_name.getText().equals(" Catagory Name")){
            catagory_name.setText("");
            catagory_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_catagory_nameFocusGained

    private void catagory_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_catagory_nameFocusLost
        //Focus Lost
        if(catagory_name.getText().trim().equals("")){
            catagory_name.setText(" Catagory Name");
            catagory_name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_catagory_nameFocusLost

    private void catagory_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catagory_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_catagory_nameKeyPressed

    private void catagory_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catagory_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_catagory_nameKeyTyped

    private void show_catagoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show_catagoryMouseClicked
        //Mouse Clicked
        try{
            int row = show_catagory.getSelectedRow();
            String Table_Click = (show_catagory.getModel().getValueAt(row,0).toString());
            String Query ="SELECT * FROM product_types WHERE id='"+Table_Click+"'";
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            while(rs.next()){
                String Catagory_Name=rs.getString("Type_Name");
                catagory_name.setText(Catagory_Name);

                String Description=rs.getString("Description");
                catagory_details.setText(Description);
            }
        }
        catch(Exception e){

        }
    }//GEN-LAST:event_show_catagoryMouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //Input Catagory
        String Catagory_Name = catagory_name.getText();
        String Description = catagory_details.getText();

        if(Catagory_Name.isEmpty() || Description.isEmpty()){
            notification_catagory.setText("Fill Up All Input Fields !");
        }
        else{
            try{
                String Check_Catagory_Name ="SELECT Type_Name FROM product_types WHERE Type_Name='"+Catagory_Name+"' ";           
                PreparedStatement statement = db.connect().prepareStatement(Check_Catagory_Name);
                ResultSet rs_qu =statement.executeQuery(Check_Catagory_Name);
                if(rs_qu.next()){
                     notification_catagory.setText("Same Catagory Name Found !");     
                }
                else{
                    PreparedStatement pst = db.connect().prepareStatement("INSERT INTO product_types (Type_Name ,Description , Date)" + "VALUES (?,?,?)");
                    pst.setString(1, Catagory_Name);
                    pst.setString(2, Description);
                    pst.setString(3, Date);;
                    pst.executeUpdate();
                    notification_catagory.setText("Successfully Added ! ");
                    //clear_catagory();
                }
                
                
               
            }
            catch(Exception e){
                System.out.println("Error Connecting Database. "+e);
            }
            show_catagory();
            //show_catagory_db();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //Update Catagory
        String Catagory_Name = catagory_name.getText();
        String Description = catagory_details.getText();

        if(Catagory_Name.isEmpty() || Description.isEmpty()){
            notification_catagory.setText("Please Fill Up All Input Field. ! ");
        }
        else{
            try{
                int row = show_catagory.getSelectedRow();
                String Table_Click = (show_catagory.getModel().getValueAt(row,0).toString());

                PreparedStatement pst = db.connect().prepareStatement("UPDATE product_types SET Type_Name=?,Description=?,Date=? WHERE id='"+Table_Click+"' ");
                pst.setString(1, Catagory_Name);
                pst.setString(2, Description);
                pst.setString(3, Date);;
                pst.executeUpdate();
                notification_catagory.setText("Successfully Updated ! ");
               // clear_catagory();
            }
            catch(Exception e){
                System.out.println("Error Connecting Database. "+e);
            }
            show_catagory();
            //show_catagory_db();
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // Clear Catagory
        catagory_name.setText(" Catagory Name");
        catagory_details.setText("Catagory Details");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // Delete Catagory
        if(show_catagory.getSelectionModel().isSelectionEmpty()){
            notification_catagory.setText("Select a Catagory First ! ");
        }
        else{
            int row = show_catagory.getSelectedRow();
            String Table_Click = (show_catagory.getModel().getValueAt(row,0).toString());
            try{
                PreparedStatement pst = db.connect().prepareStatement("DELETE FROM product_types WHERE id='"+Table_Click+"' ");
                pst.execute();
                notification_catagory.setText("Deleted !");
               // clear_catagory();
            }
            catch(Exception e){
                System.out.println("My Sql is Not Running : "+e);
                notification_catagory.setText("Error !");
            }
            show_catagory();
           // show_catagory_db();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void catagory_detailsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_catagory_detailsFocusGained
        //Focus Gained
        if(catagory_details.getText().equals("Catagory Details")){
            catagory_details.setText("");
            catagory_details.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_catagory_detailsFocusGained

    private void catagory_detailsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_catagory_detailsFocusLost
        //Focus Lost
        if(catagory_details.getText().trim().equals("")){
            catagory_details.setText("Catagory Details");
            catagory_details.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_catagory_detailsFocusLost

    private void catagoryWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_catagoryWindowActivated
       //Onload Function    
       show_catagory();
       show_catagory.setDefaultEditor(Object.class, null);
       show_catagory.setShowGrid(true);
       show_catagory.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,15));
    }//GEN-LAST:event_catagoryWindowActivated

    private void product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_nameActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String Product_Name = product_name.getText();
        String Article_No = article_no.getText();
        String Location = location.getText();
        if(Article_No.equals(" Article No") || Article_No.equals("")){
            notification_product.setText("No Article No Found !");
        }
        else{
            String Product_Details = Article_No + " " + Product_Name + " " + Location;
            createImage(Product_Name+".png" , Product_Details);     
            notification_product.setText("Barcode Generated !");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    public void show_catagory(){      
        try{            
            String Query =("SELECT id AS ID , Type_Name as 'Catagory Name' , Description FROM product_types ORDER BY id desc");
            PreparedStatement statement = db.connect().prepareStatement(Query);
            ResultSet rs =statement.executeQuery(Query);
            show_catagory.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             System.out.println("Error While Connecting : "+e);
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
            java.util.logging.Logger.getLogger(input_product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(input_product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(input_product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(input_product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new input_product().setVisible(true);
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
    private javax.swing.JTextField article_no;
    private javax.swing.JFrame catagory;
    private javax.swing.JTextArea catagory_details;
    private javax.swing.JTextField catagory_name;
    private javax.swing.JTextField company;
    private com.toedter.calendar.JDateChooser doe;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel footer;
    private javax.swing.JTextField get_product_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField location;
    private javax.swing.JLabel notification_catagory;
    private javax.swing.JLabel notification_product;
    private javax.swing.JTextField product_name;
    private javax.swing.JTable products;
    private javax.swing.JMenu products_menu;
    private javax.swing.JTextField purchases_price;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty_alert;
    private javax.swing.JTextField selling_price;
    private javax.swing.JTable show_catagory;
    private javax.swing.JPanel sidebar;
    private javax.swing.JComboBox<String> type;
    // End of variables declaration//GEN-END:variables
}
