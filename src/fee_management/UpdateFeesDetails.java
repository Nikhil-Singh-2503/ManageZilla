/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee_management;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


/**
 *
 * @author nikhil
 */
public class UpdateFeesDetails extends javax.swing.JFrame {

    /**
     * Creates new form Addfees
     */
    public UpdateFeesDetails() {
        initComponents();
        displaycashfirst();
        fillComboBox();
        int receiptNo=getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
        setRecords();
    }
    
    public void displaycashfirst(){
        lbl_dd.setVisible(false);
        lbl_chequeno.setVisible(false);
        lbl_bankname.setVisible(false);
        lbl_transactionid.setVisible(false);
        txt_transaction.setVisible(false);
        txt_ddNo.setVisible(false);
        txt_chequeNo.setVisible(false);
        txt_bankname.setVisible(false);
    }
    
    
   public boolean validation(){
        if (txt_receivedfrom.getText ( ).equals("")){
        JOptionPane.showMessageDialog(this ," please enter user name ");
        return false ;
         }
        
        if(date_chooser.getDate()== null){
       JOptionPane.showMessageDialog (this ," please select a date " ) ;
        return false ;
        }
        
       if ( txt_amount.getText().equals ("")||txt_amount.getText().matches("[0-9]+")==false){
       JOptionPane.showMessageDialog (this ,"please enter amount ( in numbers )");
        return false ;
      }
       
      if ( combo_payment.getSelectedItem().toString().equalsIgnoreCase("cheque")){
         if ( txt_chequeNo.getText ( ) . equals ( " " ) ) {
              JOptionPane.showMessageDialog( this , " please enter cheque number " );
            return false ;
                                            
         }
         if (txt_bankname.getText ( ) . equals ( " " ) ) {
              JOptionPane.showMessageDialog ( this , " please enter bank name " ) ;
             return false ;
         }
      }
      return true;
   } 
   
   public void fillComboBox(){
       try{
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
          PreparedStatement pst=con.prepareStatement("Select cname from course");
          ResultSet rs=pst.executeQuery();
          while(rs.next()){
             combo_course.addItem(rs.getString("cname")); 
          }
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public int getReceiptNo(){
       int receiptNo=0;
       try{
           String sql="select max(receipt_no) from fees_details";
           Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
           PreparedStatement pst=con.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           
           if(rs.next()){
              receiptNo= rs.getInt(1);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return receiptNo+1;
   }
   
   public String UpdateData(){
       String status="";
       
       int receiptNo=Integer.parseInt(txt_receiptNo.getText());
       String studentName=txt_receivedfrom.getText();
       String rollNo= txt_roll.getText();
       String paymentMode= combo_payment.getSelectedItem().toString();
       String chequeNo=txt_chequeNo.getText();
       String bankName=txt_bankname.getText();
       String ddNo=txt_ddNo.getText();
       String courseName=txt_coursename.getText();
       String gstin=lbl_gstno.getText();
       float totalAmount=Float.parseFloat(txt_total.getText());
       SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
       String date=dateFormat.format(date_chooser.getDate());
       float initialAmount=Float.parseFloat(txt_amount.getText());
       float cgst=Float.parseFloat(txt_cgst.getText());
       float sgst=Float.parseFloat(txt_sgst.getText());
       String totalInWords=txt_totalwords.getText();
       String remark=tfd_remark.getText();
       int year1=Integer.parseInt(txt_year1.getText());
       int year2=Integer.parseInt(txt_year2.getText());
       String transaction_id=txt_transaction.getText();
       
       try{
           String sql="UPDATE fees_details set student_name=?,roll_no=?,"
                   +"payment_mode=?,cheque_no=?,bank_name=?,dd_no=?,course_name=?,gstin=?,total_amount=?,date=?,"
                   +"amount=?,cgst=?,sgst=?,total_in_words=?,remark=?,year1=?,year2=?,transaction_Id=? where receipt_no =?";
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
           PreparedStatement pst=con.prepareStatement(sql);
           
           
           pst.setString(1,studentName);
           pst.setString(2,rollNo);
           pst.setString(3,paymentMode);
           pst.setString(4,chequeNo);
           pst.setString(5,bankName);
           pst.setString(6,ddNo);
           pst.setString(7,courseName);
           pst.setString(8,gstin);
           pst.setFloat(9,totalAmount);
           pst.setString(10,date);
           pst.setFloat(11,initialAmount);
           pst.setFloat(12, cgst);
           pst.setFloat(13,sgst);
           pst.setString(14,totalInWords);
           pst.setString(15,remark);
           pst.setInt(16, year1);
           pst.setInt(17, year2);
           pst.setString(18,transaction_id);
           pst.setInt(19, receiptNo);
           
          int rowCount= pst.executeUpdate();
           if(rowCount==1){
               status="Success";
           }
           else{
               status="Failed";
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
       return status;
   }
   
   public void setRecords(){
       try{
           String sql="Select *from fees_details order by receipt_no Desc limit 1";
          Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
           PreparedStatement pst=con.prepareStatement(sql); 
           ResultSet rs=pst.executeQuery();
           rs.next();
           txt_receiptNo.setText(rs.getString("receipt_no"));
           combo_payment.setSelectedItem(rs.getString("payment_mode"));
           txt_chequeNo.setText(rs.getString("cheque_no"));
           txt_ddNo.setText(rs.getString("dd_no"));
           txt_bankname.setText(rs.getString("bank_name"));
           txt_receivedfrom.setText(rs.getString("student_name"));
           date_chooser.setDate(rs.getDate("date"));
           txt_year1.setText(rs.getString("year1"));
           txt_year2.setText(rs.getString("year2"));
           txt_roll.setText(rs.getString("roll_no"));
           combo_course.setSelectedItem(rs.getString("course_name"));
           txt_amount.setText(rs.getString("amount"));
           txt_sgst.setText(rs.getString("sgst"));
           txt_cgst.setText(rs.getString("cgst"));
           txt_total.setText(rs.getString("total_amount"));
           txt_totalwords.setText(rs.getString("total_in_words"));
           tfd_remark.setText(rs.getString("remark"));
           txt_transaction.setText(rs.getString("transaction_Id"));
           
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        panelhome = new javax.swing.JPanel();
        btn_home = new javax.swing.JLabel();
        panelsearch = new javax.swing.JPanel();
        btn_search = new javax.swing.JLabel();
        paneledit = new javax.swing.JPanel();
        btn_editcourse = new javax.swing.JLabel();
        panelcourselist = new javax.swing.JPanel();
        btn_courselist = new javax.swing.JLabel();
        panelallrecord = new javax.swing.JPanel();
        btn_allrecord = new javax.swing.JLabel();
        panelback = new javax.swing.JPanel();
        btn_back = new javax.swing.JLabel();
        panellogout = new javax.swing.JPanel();
        btn_logout = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_chequeno = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_gstno = new javax.swing.JLabel();
        lbl_bankname = new javax.swing.JLabel();
        date_chooser = new com.toedter.calendar.JDateChooser();
        combo_payment = new javax.swing.JComboBox<>();
        lbl_dd = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_ddNo = new javax.swing.JTextField();
        txt_bankname = new javax.swing.JTextField();
        txt_chequeNo = new javax.swing.JTextField();
        txt_receivedfrom = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        combo_course = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_roll = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txt_coursename = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_sgst = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_total = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfd_remark = new javax.swing.JTextArea();
        lbl_transactionid = new javax.swing.JLabel();
        txt_transaction = new javax.swing.JTextField();
        txt_totalwords = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(102, 255, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(109, 203, 246));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1250, 850));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelhome.setBackground(new java.awt.Color(184, 229, 250));
        panelhome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelhomeMouseEntered(evt);
            }
        });

        btn_home.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/home.png"))); // NOI18N
        btn_home.setText("  Home");
        btn_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_homeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_homeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelhomeLayout = new javax.swing.GroupLayout(panelhome);
        panelhome.setLayout(panelhomeLayout);
        panelhomeLayout.setHorizontalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelhomeLayout.setVerticalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhomeLayout.createSequentialGroup()
                .addComponent(btn_home)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(panelhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 300, -1));

        panelsearch.setBackground(new java.awt.Color(184, 229, 250));
        panelsearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelsearch.setPreferredSize(new java.awt.Dimension(146, 54));

        btn_search.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/search.png"))); // NOI18N
        btn_search.setText("Search Record");
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_searchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_searchMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelsearchLayout = new javax.swing.GroupLayout(panelsearch);
        panelsearch.setLayout(panelsearchLayout);
        panelsearchLayout.setHorizontalGroup(
            panelsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_search, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        panelsearchLayout.setVerticalGroup(
            panelsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(panelsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 300, -1));

        paneledit.setBackground(new java.awt.Color(184, 229, 250));
        paneledit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btn_editcourse.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_editcourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_editcourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/edit.png"))); // NOI18N
        btn_editcourse.setText("Update Fees");
        btn_editcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editcourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_editcourseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout paneleditLayout = new javax.swing.GroupLayout(paneledit);
        paneledit.setLayout(paneleditLayout);
        paneleditLayout.setHorizontalGroup(
            paneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_editcourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneleditLayout.setVerticalGroup(
            paneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_editcourse, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel1.add(paneledit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 300, -1));

        panelcourselist.setBackground(new java.awt.Color(184, 229, 250));
        panelcourselist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btn_courselist.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_courselist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_courselist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/courselist.png"))); // NOI18N
        btn_courselist.setText("Course List");
        btn_courselist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_courselistMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_courselistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_courselistMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelcourselistLayout = new javax.swing.GroupLayout(panelcourselist);
        panelcourselist.setLayout(panelcourselistLayout);
        panelcourselistLayout.setHorizontalGroup(
            panelcourselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_courselist, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        panelcourselistLayout.setVerticalGroup(
            panelcourselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_courselist)
        );

        jPanel1.add(panelcourselist, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        panelallrecord.setBackground(new java.awt.Color(184, 229, 250));
        panelallrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btn_allrecord.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_allrecord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_allrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/allrecords.png"))); // NOI18N
        btn_allrecord.setText("View all records");
        btn_allrecord.setToolTipText("");
        btn_allrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_allrecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_allrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_allrecordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelallrecordLayout = new javax.swing.GroupLayout(panelallrecord);
        panelallrecord.setLayout(panelallrecordLayout);
        panelallrecordLayout.setHorizontalGroup(
            panelallrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_allrecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        panelallrecordLayout.setVerticalGroup(
            panelallrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_allrecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(panelallrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 300, 60));

        panelback.setBackground(new java.awt.Color(184, 229, 250));
        panelback.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btn_back.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/back.png"))); // NOI18N
        btn_back.setText("Back");
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_backMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelbackLayout = new javax.swing.GroupLayout(panelback);
        panelback.setLayout(panelbackLayout);
        panelbackLayout.setHorizontalGroup(
            panelbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbackLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelbackLayout.setVerticalGroup(
            panelbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(panelback, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 300, 60));

        panellogout.setBackground(new java.awt.Color(184, 229, 250));
        panellogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btn_logout.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        btn_logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fee_management/img_addfees/logout.png"))); // NOI18N
        btn_logout.setText("Logout");
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_logoutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panellogoutLayout = new javax.swing.GroupLayout(panellogout);
        panellogout.setLayout(panellogoutLayout);
        panellogoutLayout.setHorizontalGroup(
            panellogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_logout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        panellogoutLayout.setVerticalGroup(
            panellogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel1.add(panellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 720, 300, 60));

        kGradientPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 850));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Receipt no: NPU\n");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Mode of Payment :");
        kGradientPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        lbl_chequeno.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_chequeno.setText("Cheque No :");
        kGradientPanel1.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Date :");
        kGradientPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, -1, -1));

        lbl_gstno.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_gstno.setText("GSTIN : AB12C34D");
        kGradientPanel1.add(lbl_gstno, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 110, -1, -1));

        lbl_bankname.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_bankname.setText("Bank Name :");
        kGradientPanel1.add(lbl_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));
        kGradientPanel1.add(date_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, -1, -1));

        combo_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Cheque", "DD", "Credit card", "Debit card", " " }));
        combo_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_paymentActionPerformed(evt);
            }
        });
        kGradientPanel1.add(combo_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 120, -1));

        lbl_dd.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_dd.setText("DD No :");
        kGradientPanel1.add(lbl_dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        txt_receiptNo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 230, -1));

        txt_ddNo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_ddNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 230, -1));

        txt_bankname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 230, -1));

        txt_chequeNo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 230, -1));

        txt_receivedfrom.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_receivedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 230, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("Received from :");
        kGradientPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("The following payment is for  the month ");
        kGradientPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, -1));

        txt_year1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, 90, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("to");
        kGradientPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, -1, -1));

        txt_year2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, 90, -1));

        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });
        kGradientPanel1.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 200, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Courses :");
        kGradientPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, -1, -1));

        txt_roll.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 380, 150, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Roll No :");
        kGradientPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 390, -1, -1));
        kGradientPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 432, 790, 10));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("Sr No.");
        kGradientPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("Course Name");
        kGradientPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, 120, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Amount");
        kGradientPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 450, -1, -1));
        kGradientPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 480, 790, 10));

        txt_coursename.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_coursename.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kGradientPanel1.add(txt_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 290, -1));

        txt_amount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        kGradientPanel1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 510, 150, -1));

        txt_cgst.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 560, 150, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setText("CGST 9%");
        kGradientPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, -1, -1));

        txt_sgst.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 610, 150, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("SGST 9%");
        kGradientPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 620, -1, -1));
        kGradientPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 660, 210, 10));

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 670, 150, -1));
        kGradientPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 730, 200, 10));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setText("Receiver signature");
        kGradientPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 750, -1, -1));

        btn_print.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btn_print.setText("Update");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 790, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Total in words :");
        kGradientPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Remarks :");
        kGradientPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 760, -1, -1));

        tfd_remark.setColumns(20);
        tfd_remark.setFont(new java.awt.Font("Monospaced", 0, 20)); // NOI18N
        tfd_remark.setRows(5);
        jScrollPane2.setViewportView(tfd_remark);

        kGradientPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 730, 290, 70));

        lbl_transactionid.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_transactionid.setText("Transaction Id :");
        kGradientPanel1.add(lbl_transactionid, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        txt_transaction.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kGradientPanel1.add(txt_transaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 230, -1));
        kGradientPanel1.add(txt_totalwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 670, 460, -1));

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 850));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelhomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhomeMouseEntered


    }//GEN-LAST:event_panelhomeMouseEntered

    private void btn_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseEntered
     Color clr = new Color(0,255,165);
     panelhome.setBackground(clr);
    }//GEN-LAST:event_btn_homeMouseEntered

    private void btn_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseExited
     Color clr = new Color(184,229,250);
     panelhome.setBackground(clr);
    }//GEN-LAST:event_btn_homeMouseExited

    private void btn_searchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseEntered
       Color clr = new Color(0,255,165);
     panelsearch.setBackground(clr);
    }//GEN-LAST:event_btn_searchMouseEntered

    private void btn_searchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseExited
       Color clr = new Color(184,229,250);
     panelsearch.setBackground(clr);
    }//GEN-LAST:event_btn_searchMouseExited

    private void btn_editcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editcourseMouseEntered
        Color clr = new Color(0,255,165);
     paneledit.setBackground(clr);
    }//GEN-LAST:event_btn_editcourseMouseEntered

    private void btn_editcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editcourseMouseExited
        Color clr = new Color(184,229,250);
     paneledit.setBackground(clr);
    }//GEN-LAST:event_btn_editcourseMouseExited

    private void btn_courselistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_courselistMouseEntered
        Color clr = new Color(0,255,165);
     panelcourselist.setBackground(clr);
    }//GEN-LAST:event_btn_courselistMouseEntered

    private void btn_courselistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_courselistMouseExited
      Color clr = new Color(184,229,250);
     panelcourselist.setBackground(clr);
    }//GEN-LAST:event_btn_courselistMouseExited

    private void btn_allrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_allrecordMouseEntered
         Color clr = new Color(0,255,165);
     panelallrecord.setBackground(clr);
    }//GEN-LAST:event_btn_allrecordMouseEntered

    private void btn_allrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_allrecordMouseExited
       Color clr = new Color(184,229,250);
     panelallrecord.setBackground(clr);
    }//GEN-LAST:event_btn_allrecordMouseExited

    private void btn_backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseEntered
       Color clr = new Color(0,255,165);
     panelback.setBackground(clr);
    }//GEN-LAST:event_btn_backMouseEntered

    private void btn_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseExited
        Color clr = new Color(184,229,250);
     panelback.setBackground(clr);
    }//GEN-LAST:event_btn_backMouseExited

    private void btn_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseEntered
        Color clr = new Color(0,255,165);
     panellogout.setBackground(clr);
    }//GEN-LAST:event_btn_logoutMouseEntered

    private void btn_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseExited
        Color clr = new Color(184,229,250);
     panellogout.setBackground(clr);
    }//GEN-LAST:event_btn_logoutMouseExited

    private void btn_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseClicked
        home home_page= new home();
        home_page.show();
        this.dispose();
        
    }//GEN-LAST:event_btn_homeMouseClicked

    private void combo_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_paymentActionPerformed
        if(combo_payment.getSelectedIndex()==0){
            lbl_dd.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankname.setVisible(false);
            txt_bankname.setVisible(false);
            lbl_transactionid.setVisible(false);
            txt_transaction.setVisible(false);
            
        }
        if(combo_payment.getSelectedIndex()==1){
            lbl_dd.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeno.setVisible(true);
            txt_chequeNo.setVisible(true);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            lbl_transactionid.setVisible(false);
            txt_transaction.setVisible(false);
            
        }
        if(combo_payment.getSelectedIndex()==2){
            lbl_dd.setVisible(true);
            txt_ddNo.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            lbl_transactionid.setVisible(false);
            txt_transaction.setVisible(false);
        }
        if(combo_payment.getSelectedIndex()==3){
            lbl_dd.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            lbl_transactionid.setVisible(true);
            txt_transaction.setVisible(true);
        }
         if(combo_payment.getSelectedIndex()==4){
            lbl_dd.setVisible(false);
            txt_ddNo.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            lbl_transactionid.setVisible(true);
            txt_transaction.setVisible(true);
        }
    }//GEN-LAST:event_combo_paymentActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        if(validation()==true){
        String result=UpdateData();
        if(result.equals("Success")){
            JOptionPane.showMessageDialog(this,"Record Updated Successfully");
            PrintReceipt p=new PrintReceipt();
            p.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Record Updation Failed");
        }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
       Float amnt= Float.parseFloat(txt_amount.getText());
       Float cgst= (float)(amnt*0.09);
       Float sgst= (float)(amnt*0.09);
       txt_cgst.setText(cgst.toString());
       txt_sgst.setText(sgst.toString());
       float total= amnt+cgst+sgst;
       txt_total.setText(Float.toString(total));
       txt_totalwords.setText(NumberToWordsConverter.convert((int)total)+" only.");
    }//GEN-LAST:event_txt_amountActionPerformed

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
      txt_coursename.setText(combo_course.getSelectedItem().toString());
    }//GEN-LAST:event_combo_courseActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        SearchRecord sr=new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_searchMouseClicked

    private void btn_editcourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editcourseMouseClicked
UpdateFeesDetails ufd=new UpdateFeesDetails();
ufd.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btn_editcourseMouseClicked

    private void btn_courselistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_courselistMouseClicked
       EditCourse ec=new EditCourse();
       ec.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btn_courselistMouseClicked

    private void btn_allrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_allrecordMouseClicked
        ViewAllRecords val=new ViewAllRecords();
        val.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_allrecordMouseClicked

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        home Home =new home();
        Home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
       Login login=new Login();
       login.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btn_logoutMouseClicked

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
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFeesDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_allrecord;
    private javax.swing.JLabel btn_back;
    private javax.swing.JLabel btn_courselist;
    private javax.swing.JLabel btn_editcourse;
    private javax.swing.JLabel btn_home;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JButton btn_print;
    private javax.swing.JLabel btn_search;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JComboBox<String> combo_payment;
    private com.toedter.calendar.JDateChooser date_chooser;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lbl_bankname;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_dd;
    private javax.swing.JLabel lbl_gstno;
    private javax.swing.JLabel lbl_transactionid;
    private javax.swing.JPanel panelallrecord;
    private javax.swing.JPanel panelback;
    private javax.swing.JPanel panelcourselist;
    private javax.swing.JPanel paneledit;
    private javax.swing.JPanel panelhome;
    private javax.swing.JPanel panellogout;
    private javax.swing.JPanel panelsearch;
    private javax.swing.JTextArea tfd_remark;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankname;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_chequeNo;
    private javax.swing.JTextField txt_coursename;
    private javax.swing.JTextField txt_ddNo;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_receivedfrom;
    private javax.swing.JTextField txt_roll;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalwords;
    private javax.swing.JTextField txt_transaction;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables


}
