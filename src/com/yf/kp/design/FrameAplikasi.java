package com.yf.kp.design;

import com.yf.kp.design.billing.FrameBiling;
import com.yf.kp.design.dialog.report.ReportPembayaran;
import com.yf.kp.design.dialog.report.ReportSiswa;
import com.yf.kp.design.jenispembayaran.FrameJenisPembayaran;
import com.yf.kp.design.kelas.FrameKelas;
import com.yf.kp.design.login.DialogLogIn;
import com.yf.kp.design.login.GantiPassword;
import com.yf.kp.design.siswa.FrameSiswa;
import com.yf.kp.design.transaksi.FrameTransaksi;
import com.yf.kp.model.Siswa;
import com.yf.kp.design.dialog.report.ReportTagihan;
import com.yf.kp.service.SiswaService;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author BlackCode
 */
public class FrameAplikasi extends javax.swing.JFrame {

    private Dimension dimension;
    private Image image;

    /**
     * Creates new form FrameAplikasi
     */
    public FrameAplikasi() {
        initComponents();
        setLocationRelativeTo(null);
        //fullScreen();
        setIcon();
    }

    public Boolean loginAsAdmin(Boolean login) {
        btnBiling.setEnabled(login);
        btnDataSiswa.setEnabled(login);
        btnJenisPembayaran.setEnabled(login);
        btnKelas.setEnabled(login);
        return false;
    }

    private void fullScreen() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, dimension.width, dimension.height);
    }

    private void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/yf/kp/images/icon_package_open.gif"));
        image = icon.getImage();
        setIconImage(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customDesktopPane1 = new com.yf.kp.template.CustomDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnDataSiswa = new javax.swing.JButton();
        btnKelas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnJenisPembayaran = new javax.swing.JButton();
        btnBiling = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMTransaksi = new javax.swing.JMenu();
        jmiTransaksi = new javax.swing.JMenuItem();
        jMLaporan = new javax.swing.JMenu();
        jmiDataSiswa = new javax.swing.JMenuItem();
        jmiPembayaran = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Aplikasi Pembayaran");
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1350, 710));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(79, 167, 182));

        jPanel1.setBackground(new java.awt.Color(197, 210, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnDataSiswa.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnDataSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/design/images/list_users.gif"))); // NOI18N
        btnDataSiswa.setText("Siswa");
        btnDataSiswa.setPreferredSize(new java.awt.Dimension(150, 70));
        btnDataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSiswaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDataSiswa);

        btnKelas.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/icon_home.gif"))); // NOI18N
        btnKelas.setText("KELAS");
        btnKelas.setToolTipText("Untuk membuat nama kelas");
        btnKelas.setPreferredSize(new java.awt.Dimension(150, 70));
        btnKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelasActionPerformed(evt);
            }
        });
        jPanel1.add(btnKelas);

        jTabbedPane1.addTab("KESISWAAN", jPanel1);

        jPanel5.setBackground(new java.awt.Color(197, 210, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(700, 65));

        btnJenisPembayaran.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnJenisPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/icon_network.gif"))); // NOI18N
        btnJenisPembayaran.setText("JENIS PEMBAYARAN");
        btnJenisPembayaran.setToolTipText("Untuk membuat jenis/nama pembayaran");
        btnJenisPembayaran.setPreferredSize(new java.awt.Dimension(180, 68));
        btnJenisPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJenisPembayaranActionPerformed(evt);
            }
        });
        jPanel5.add(btnJenisPembayaran);

        btnBiling.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnBiling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/list_comments.gif"))); // NOI18N
        btnBiling.setText("BILING PEMBAYARAN");
        btnBiling.setToolTipText("Membagi siswa dalam jenis pembayaran");
        btnBiling.setPreferredSize(new java.awt.Dimension(180, 68));
        btnBiling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBilingActionPerformed(evt);
            }
        });
        jPanel5.add(btnBiling);

        jTabbedPane1.addTab("PEMBAYARAN", jPanel5);

        customDesktopPane1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 700, 100);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/design/images/LogoSMP.png"))); // NOI18N
        customDesktopPane1.add(jLabel1);
        jLabel1.setBounds(830, 0, 340, 230);

        jLabel2.setFont(new java.awt.Font("Apple Garamond Light", 1, 100)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SMP NEGERI 1 PUNGGELAN");
        customDesktopPane1.add(jLabel2);
        jLabel2.setBounds(0, 290, 1280, 200);

        jMenu1.setText("File");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/design/images/action_refresh_blue.gif"))); // NOI18N
        jMenuItem2.setText("Ganti Password");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/design/images/list_keys.gif"))); // NOI18N
        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_stop.gif"))); // NOI18N
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMTransaksi.setText("Transaksi");

        jmiTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/list_users.gif"))); // NOI18N
        jmiTransaksi.setText("Pembayaran");
        jmiTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTransaksiActionPerformed(evt);
            }
        });
        jMTransaksi.add(jmiTransaksi);

        jMenuBar1.add(jMTransaksi);

        jMLaporan.setText("Laporan");

        jmiDataSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/icon_user.gif"))); // NOI18N
        jmiDataSiswa.setText("Data Siswa");
        jmiDataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDataSiswaActionPerformed(evt);
            }
        });
        jMLaporan.add(jmiDataSiswa);

        jmiPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_paste.gif"))); // NOI18N
        jmiPembayaran.setText("Pembayaran");
        jmiPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPembayaranActionPerformed(evt);
            }
        });
        jMLaporan.add(jmiPembayaran);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/design/images/page_cross.gif"))); // NOI18N
        jMenuItem4.setText("Tagihan");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMLaporan.add(jMenuItem4);

        jMenuBar1.add(jMLaporan);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(customDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTransaksiActionPerformed
        FrameTransaksi ft = new FrameTransaksi();
        ft.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(ft);
        ft.setVisible(true);
    }//GEN-LAST:event_jmiTransaksiActionPerformed

    private void btnKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelasActionPerformed
        // TODO add your handling code here:
        FrameKelas fk = new FrameKelas();
        fk.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(fk);
        fk.setVisible(true);
    }//GEN-LAST:event_btnKelasActionPerformed

    private void btnJenisPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJenisPembayaranActionPerformed
        FrameJenisPembayaran frameJenisPembayaran = new FrameJenisPembayaran();
        frameJenisPembayaran.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(frameJenisPembayaran);
        frameJenisPembayaran.setVisible(true);
    }//GEN-LAST:event_btnJenisPembayaranActionPerformed

    private void btnBilingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBilingActionPerformed
        FrameBiling Fb = new FrameBiling();
        Fb.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(Fb);
        Fb.setVisible(true);
    }//GEN-LAST:event_btnBilingActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(null, "Are You Sure to Exit?", "Exit Application", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are You Sure to Exit?", "Exit Application", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiDataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDataSiswaActionPerformed
        ReportSiswa dialog = new ReportSiswa(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jmiDataSiswaActionPerformed

    private void jmiPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPembayaranActionPerformed
        ReportPembayaran dialog = new ReportPembayaran(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jmiPembayaranActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        GantiPassword dialog = new GantiPassword(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        new DialogLogIn(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnDataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSiswaActionPerformed
        FrameSiswa fs = new FrameSiswa();
        fs.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(fs);
        fs.setVisible(true);
    }//GEN-LAST:event_btnDataSiswaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ReportTagihan rt = new ReportTagihan(this, true);
        rt.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line argument
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBiling;
    private javax.swing.JButton btnDataSiswa;
    private javax.swing.JButton btnJenisPembayaran;
    private javax.swing.JButton btnKelas;
    private com.yf.kp.template.CustomDesktopPane customDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMLaporan;
    private javax.swing.JMenu jMTransaksi;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmiDataSiswa;
    private javax.swing.JMenuItem jmiPembayaran;
    private javax.swing.JMenuItem jmiTransaksi;
    // End of variables declaration//GEN-END:variables

}
