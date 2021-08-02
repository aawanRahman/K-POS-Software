/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k_pos;

import java.awt.Toolkit;
import java.sql.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Najmul
 */
public class login extends javax.swing.JFrame {
    db_connect db = new db_connect();

    /**
     * Creates new form login
     */
    public login() {            
        initComponents();
        seticon();  
        //automatic_pc_backup();
        this.setLocationRelativeTo(null);//for shoeing this in center
        get_settings get=new get_settings();   
        String Company = get.Company;
        String Version = get.Version;
        footer.setText(Company+" "+Version);  
        try {            
            boolean isMysqlUp = isServerUp(3306);
            if(isMysqlUp == true){
                //If Mysql is up do nothing !
            }
            else{
                mysql();
            }  
        } 
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Mysql is Not Running Please Start is Manually.","Error", JOptionPane.QUESTION_MESSAGE); 
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
    public void mysql() throws IOException{
        Runtime.getRuntime().exec("c:\\xampp\\mysql\\bin\\mysqld.exe", null, new File("c:\\xampp\\mysql\\bin\\"));
    }
    private void seticon(){   
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fevicon.png")));
    }    
    private void add_history() {
        String Username = username.getText();
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
        String Date = now.format(date);  
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");	
        String Time = now.format(time);  
        
        //Create a Cache File for Logged in User
        get_settings get=new get_settings();
        String User_Dir = get.User_Dir;
        File file = new File (User_Dir);
        try { 
            PrintWriter writer = new PrintWriter(User_Dir, "UTF-8");
            writer.println(Username);
            writer.close();
            }            
        catch (Exception e) {            
            System.out.println(e);
        }
        
        //Add to Login History User Details
        try{
            PreparedStatement pst = db.connect().prepareStatement("INSERT INTO history (Username, Date , Time)" + "VALUES (?,?,?)");
            pst.setString(1, Username);
            pst.setString(2, Date);    
            pst.setString(3, Time);        
            pst.executeUpdate();     
            System.out.println("Login History Added ! ");
        }
        catch(Exception e){
            System.out.println("Error Connecting Database. "+e);
        }     
        
    }    
    
    
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        error_login = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        show_password = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        footer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login | K-POS");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(570, 320));
        setResizable(false);
        setSize(new java.awt.Dimension(570, 320));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setLayout(null);
        jPanel1.add(error_login);
        error_login.setBounds(300, 230, 260, 27);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Username");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(300, 70, 61, 30);

        password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password.setToolTipText("Password");
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        jPanel1.add(password);
        password.setBounds(380, 110, 151, 30);

        username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        username.setToolTipText("Username");
        jPanel1.add(username);
        username.setBounds(380, 70, 151, 30);

        jButton1.setText("Login");
        jButton1.setToolTipText("Click Here to Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(300, 190, 80, 30);

        show_password.setText("Show Password");
        show_password.setToolTipText("Show Password");
        show_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_passwordActionPerformed(evt);
            }
        });
        jPanel1.add(show_password);
        show_password.setBounds(300, 160, 150, 23);

        jButton3.setText("Clear");
        jButton3.setToolTipText("Clear Input Field");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(390, 190, 80, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(300, 110, 58, 30);

        footer.setForeground(new java.awt.Color(255, 255, 255));
        footer.setText("Footer Text");
        jPanel1.add(footer);
        footer.setBounds(50, 290, 180, 14);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/k_pos/images/background.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 570, 340);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void login(){
        String Username = username.getText();
        String Password =  String.valueOf(password.getPassword());        
        if(Username.isEmpty() && Password.isEmpty()){
            error_login.setText("Username and Passowrd Can't be Empty");
         }
        else if(Username.equals("Admin") && Password.equals("NCWORLD")){
            installation i = new installation();
            i.setVisible(true);
            this.dispose();
        }
        else{
            // Check if Activated or Not
            get_settings get=new get_settings();
           String Activation_Key = get.Activation_Key;
            //String Activation_Key = "A2X8-52C9-NXW4-L5V1";
            String Activation_Dir = get.Activation_Dir;              
            File file = new File (Activation_Dir);
            try {     
                if(file.exists()){                
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String st = br.readLine();
                    if(st != null){
                       //Print ac.kp
                       //System.out.println(st);
                       if(st.equals(Activation_Key)){
                            try {       
                                String Query="select * from users where username='"+Username+"'";                
                                PreparedStatement pst = db.connect().prepareStatement(Query);                                 
                                ResultSet rs =pst.executeQuery();
                                if(rs.next()){
                                    String Password_Get_Encrypted = rs.getString("password");                                      
                                   //Get Encrypted Password
                                    encrypt encrypt = new encrypt();
                                    if (encrypt.VerifyPassword(Password , Password_Get_Encrypted)){
                                       homepage h = new homepage();
                                       h.setVisible(true);
                                       this.dispose();
                                       add_history();
                                    }                                           
                                    else{
                                        error_login.setText("Wrong Password !");
                                    }
                                }
                                else{
                                    error_login.setText("Wrong Username !");
                                }
                            }             
                             catch (SQLException ex) {
                                 System.out.println("Error While Login .  "+ex);
                             }       
                        }
                       else{
                           error_login.setText("K-POS is Not Activated !");
                       }
                    }
                    else{
                        error_login.setText("K-POS is Not Activated !");
                    }              
                }
                else{
                    error_login.setText("K-POS is Not Activated !");
                }
            }
            catch(Exception e){
                error_login.setText("K-POS is Not Activated !");
                System.out.println("Error with Acativation Code.");
            }  
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login();      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void show_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_passwordActionPerformed
        if(show_password.isSelected()){
            password.setEchoChar((char)0);
        }else{
          password.setEchoChar('*');
        }
    }//GEN-LAST:event_show_passwordActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        username.setText("");
        password.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed
  
    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        //IF Enter Key is Pressed 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            login(); 
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Check if Activated
        // Check if Activated or Not
        get_settings get=new get_settings();
        String Activation_Key = get.Activation_Key;
        String Activation_Dir = get.Activation_Dir;              
        File file = new File (Activation_Dir);
        try {     
            if(file.exists()){                
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();
                if(st != null){
                   //Print ac.kp
                   //System.out.println(st);
                   if(st.equals(Activation_Key)){
                             
                    }
                   else{
                        installation h = new installation();
                        h.setVisible(true);
                        this.dispose();
                   }
                }
                else{
                    installation h = new installation();
                        h.setVisible(true);
                        this.dispose();
                }              
            }
            else{
                installation h = new installation();
                        h.setVisible(true);
                        this.dispose();
            }
        }
        catch(Exception e){
            error_login.setText("K-POS is Not Activated !");
            System.out.println("Error with Acativation Code.");
        }  
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error_login;
    private javax.swing.JLabel footer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JCheckBox show_password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
