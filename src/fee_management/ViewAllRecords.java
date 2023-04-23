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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nikhi
 */
public class ViewAllRecords extends javax.swing.JFrame {

    /**
     * Creates new form ViewAllRecords
     */
    DefaultTableModel model;
    public ViewAllRecords() {
        initComponents();
        setRecordsintoTable();
    }
    
    public void setRecordsintoTable(){
      try{
          String sql="select * from fees_details";
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
           PreparedStatement pst=con.prepareStatement(sql);
          ResultSet rs=pst.executeQuery();
          
          while(rs.next()){
              String receiptNo=rs.getString("receipt_no");
              String rollNo=rs.getString("roll_no");
              String studentName=rs.getString("student_name");
              String paymentMode=rs.getString("payment_mode");
              String courseName=rs.getString("course_name");
              float amount=rs.getFloat("total_amount");
              String date=rs.getString("date");
              String remark=rs.getString("remark");
              
              Object[] obj= {receiptNo,rollNo,studentName,paymentMode,courseName,amount,date,remark};
              
              model=(DefaultTableModel)tbl_stu_record.getModel();
              model.addRow(obj);
          }
          
          
      } catch(Exception e){
          e.printStackTrace();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_stu_record = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1250, 850));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(102, 255, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(109, 203, 246));
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
            .addComponent(btn_home, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
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
            .addComponent(btn_editcourse, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
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
            .addComponent(btn_allrecord, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
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
            .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
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

        kGradientPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 850));

        tbl_stu_record.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No", "Roll no", "Name", "Payment Mode", "Course", "Total Amount", "Date", "Remark"
            }
        ));
        jScrollPane2.setViewportView(tbl_stu_record);

        kGradientPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 800, 740));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("All Records");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, -1, -1));

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 850));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseClicked
        home home_page= new home();
        home_page.show();
        this.dispose();
    }//GEN-LAST:event_btn_homeMouseClicked

    private void btn_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseEntered
        Color clr = new Color(0,255,165);
        panelhome.setBackground(clr);
    }//GEN-LAST:event_btn_homeMouseEntered

    private void btn_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseExited
        Color clr = new Color(184,229,250);
        panelhome.setBackground(clr);
    }//GEN-LAST:event_btn_homeMouseExited

    private void panelhomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhomeMouseEntered

    }//GEN-LAST:event_panelhomeMouseEntered

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        SearchRecord sr=new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_searchMouseClicked

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
       ViewAllRecords val =new ViewAllRecords();
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
            java.util.logging.Logger.getLogger(ViewAllRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAllRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAllRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAllRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAllRecords().setVisible(true);
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
    private javax.swing.JLabel btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JPanel panelallrecord;
    private javax.swing.JPanel panelback;
    private javax.swing.JPanel panelcourselist;
    private javax.swing.JPanel paneledit;
    private javax.swing.JPanel panelhome;
    private javax.swing.JPanel panellogout;
    private javax.swing.JPanel panelsearch;
    private javax.swing.JTable tbl_stu_record;
    // End of variables declaration//GEN-END:variables
}
