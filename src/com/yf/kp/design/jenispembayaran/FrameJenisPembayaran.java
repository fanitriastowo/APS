package com.yf.kp.design.jenispembayaran;

import com.yf.kp.design.jenispembayaran.dialog.DialogAngsuran;
import com.yf.kp.design.jenispembayaran.dialog.DialogBulanan;
import com.yf.kp.design.jenispembayaran.dialog.DialogTunai;
import com.yf.kp.design.jenispembayaran.dialog.update.DialogAngsuranUpdate;
import com.yf.kp.design.jenispembayaran.dialog.update.DialogBulananUpdate;
import com.yf.kp.design.jenispembayaran.dialog.update.DialogTunaiUpdate;
import com.yf.kp.model.Angsuran;
import com.yf.kp.model.Bulanan;
import com.yf.kp.model.Tunai;
import com.yf.kp.service.AngsuranService;
import com.yf.kp.service.BulananService;
import com.yf.kp.service.CountService;
import com.yf.kp.service.TunaiService;
import com.yf.kp.service.impl.AngsuranServiceImpl;
import com.yf.kp.service.impl.BulananServiceImpl;
import com.yf.kp.service.impl.CountServiceImpl;
import com.yf.kp.service.impl.TunaiServiceImpl;
import com.yf.kp.utility.TableAutoColumnWidth;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author aspire
 */
public class FrameJenisPembayaran extends javax.swing.JInternalFrame {

    Integer noHalaman = 1;
    Integer banyakBaris = 10;
    Integer totalHalaman = 1;
    Integer totalData = 0;
    private List<Angsuran> angsuranList;
    private List<Bulanan> bulananList;
    private List<Tunai> tunaiList;

    /**
     * Creates new form FrameJenisPembayaran
     */
    public FrameJenisPembayaran() {
        initComponents();
    }

    private void setUndecoretedInternalFrame() {
        try {
            this.putClientProperty("JInternalFrame.isPallete", Boolean.TRUE);
            BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) this.getUI();
            basicInternalFrameUI.setNorthPane(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error, Contact Your System Admin\n" + e.getMessage());
        }
    }

    private void refresh() {
        if (angsuranList != null) {
            angsuranList.clear();
            AngsuranTableModel tableModel = new AngsuranTableModel();
            tableModel.setList(angsuranList);
            tablePembayaran.setModel(tableModel);
        }
        if (bulananList != null) {
            bulananList.clear();
            BulananTableModel tableModel = new BulananTableModel();
            tableModel.setList(bulananList);
            tablePembayaran.setModel(tableModel);
        }
        if (tunaiList != null) {
            tunaiList.clear();
            TunaiTableModel tableModel = new TunaiTableModel();
            tableModel.setList(tunaiList);
            tablePembayaran.setModel(tableModel);
        }
        cmbJenisBayar.setSelectedIndex(0);
    }

    private void loadData() {
        if (null != cmbJenisBayar.getSelectedItem().toString()) {
            switch (cmbJenisBayar.getSelectedItem().toString()) {
                case "Angsuran":
                    angsuranBayar();
                    break;
                case "Bulanan":
                    bulananBayar();
                    break;
                case "Tunai":
                    tunaiBayar();
                    break;
                case "Pilih...":
                    refresh();
                    break;
            }
        }
    }

    public void cbBaris() {
        cmbJumlahBaris.addItem("10");
        cmbJumlahBaris.addItem("20");
    }

    private void angsuranBayar() {
        AngsuranService service = new AngsuranServiceImpl();
        CountService countService = new CountServiceImpl();

        totalData = countService.countAngsuran().intValue();
        banyakBaris = Integer.parseInt(cmbJumlahBaris.getSelectedItem().toString());
        Double totalHalD = Math.ceil(totalData.doubleValue() / banyakBaris.doubleValue());
        totalHalaman = totalHalD.intValue();

        if (noHalaman == 1) {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }

        if (noHalaman.equals(totalHalaman)) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }

        if (noHalaman > totalHalaman) {
            noHalaman = 1;
        }

        angsuranList = service.findAll(noHalaman, banyakBaris);
        AngsuranTableModel tableModel = new AngsuranTableModel();
        tableModel.setList(angsuranList);
        tablePembayaran.setModel(tableModel);
        TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tablePembayaran);
    }

    private void bulananBayar() {
        BulananService service = new BulananServiceImpl();
        CountService countService = new CountServiceImpl();

        totalData = countService.countBulanan().intValue();
        banyakBaris = Integer.parseInt(cmbJumlahBaris.getSelectedItem().toString());
        Double totalHalD = Math.ceil(totalData.doubleValue() / banyakBaris.doubleValue());
        totalHalaman = totalHalD.intValue();

        if (noHalaman == 1) {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }

        if (noHalaman.equals(totalHalaman)) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }

        if (noHalaman > totalHalaman) {
            noHalaman = 1;
        }

        bulananList = service.findAll(noHalaman, banyakBaris);
        BulananTableModel tableModel = new BulananTableModel();
        tableModel.setList(bulananList);
        tablePembayaran.setModel(tableModel);
        TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tablePembayaran);
    }

    private void tunaiBayar() {
        TunaiService service = new TunaiServiceImpl();
        CountService countService = new CountServiceImpl();

        totalData = countService.countTunai().intValue();
        banyakBaris = Integer.parseInt(cmbJumlahBaris.getSelectedItem().toString());
        Double totalHalD = Math.ceil(totalData.doubleValue() / banyakBaris.doubleValue());
        totalHalaman = totalHalD.intValue();

        if (noHalaman == 1) {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }

        if (noHalaman.equals(totalHalaman)) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }

        if (noHalaman > totalHalaman) {
            noHalaman = 1;
        }

        tunaiList = service.findAll(noHalaman, banyakBaris);
        TunaiTableModel tableModel = new TunaiTableModel();
        tableModel.setList(tunaiList);
        tablePembayaran.setModel(tableModel);
        TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tablePembayaran);
    }

    private void updateData() {
        if (tablePembayaran.getSelectedRow() != -1) {
            switch (cmbJenisBayar.getSelectedItem().toString()) {
                case "Angsuran":
                    Integer indexAngsuran = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Angsuran angsuran = angsuranList.get(indexAngsuran);
                    DialogAngsuranUpdate updateAngsuranForm = new DialogAngsuranUpdate(null, true);
                    updateAngsuranForm.setTitle(angsuran.getId().toString());
                    updateAngsuranForm.setTxtJumlah(angsuran.getJumlah());
                    updateAngsuranForm.setTxtKaliPembayaran(angsuran.getKaliBayar());
                    updateAngsuranForm.setTxtNamaPembayaran(angsuran.getNama());
                    updateAngsuranForm.setVisible(true);
                    break;
                case "Bulanan":
                    Integer indexBulanan = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Bulanan bulanan = bulananList.get(indexBulanan);
                    DialogBulananUpdate updateBulananForm = new DialogBulananUpdate(null, true);
                    updateBulananForm.setTitle(bulanan.getId().toString());
                    updateBulananForm.setTxtNamaPembayaran(bulanan.getNama());
                    updateBulananForm.setTxtJumlah(bulanan.getJumlah());
                    updateBulananForm.setChkJanuari(bulanan.isJanuari());
                    updateBulananForm.setChkFebruari(bulanan.isFebruari());
                    updateBulananForm.setChkMaret(bulanan.isMaret());
                    updateBulananForm.setChkApril(bulanan.isApril());
                    updateBulananForm.setChkMei(bulanan.isMei());
                    updateBulananForm.setChkJuni(bulanan.isJuni());
                    updateBulananForm.setChkJuli(bulanan.isJuli());
                    updateBulananForm.setChkAgustus(bulanan.isAgustus());
                    updateBulananForm.setChkSeptember(bulanan.isSeptember());
                    updateBulananForm.setChkOktober(bulanan.isOktober());
                    updateBulananForm.setChkNovember(bulanan.isNovember());
                    updateBulananForm.setChkDesember(bulanan.isDesember());
                    updateBulananForm.setVisible(true);
                    break;
                case "Tunai":
                    Integer indexTunai = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Tunai tunai = tunaiList.get(indexTunai);
                    DialogTunaiUpdate updateTunaiForm = new DialogTunaiUpdate(null, true);
                    updateTunaiForm.setTitle(tunai.getId().toString());
                    updateTunaiForm.setTxtNamaPembayaran(tunai.getNama());
                    updateTunaiForm.setTxtJumlah(tunai.getJumlah());
                    updateTunaiForm.setVisible(true);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }

    private void deleteData() {
        if (tablePembayaran.getSelectedRow() != -1) {
            switch (cmbJenisBayar.getSelectedItem().toString()) {
                case "Angsuran":
                    Integer indexAngsuran = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Angsuran angsuran = angsuranList.get(indexAngsuran);
                    if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        AngsuranService service = new AngsuranServiceImpl();
                        service.delete(angsuran.getId());
                        loadData();
                    }
                    break;
                case "Bulanan":
                    Integer indexBulanan = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Bulanan bulanan = bulananList.get(indexBulanan);
                    if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        BulananService service = new BulananServiceImpl();
                        service.delete(bulanan.getId());
                        loadData();
                    }
                    break;
                case "Tunai":
                    Integer indexTunai = tablePembayaran.convertRowIndexToModel(tablePembayaran.getSelectedRow());
                    Tunai tunai = tunaiList.get(indexTunai);
                    if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        TunaiService service = new TunaiServiceImpl();
                        service.delete(tunai.getId());
                        loadData();
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablePembayaran = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        cmbJumlahBaris = new javax.swing.JComboBox();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbJenisBayar = new javax.swing.JComboBox();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel23.setOpaque(false);

        tablePembayaran.setName(""); // NOI18N
        jScrollPane12.setViewportView(tablePembayaran);

        jPanel24.setOpaque(false);

        btnFirst.setText("<<");
        btnFirst.setToolTipText("First");
        btnFirst.setPreferredSize(new java.awt.Dimension(50, 30));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel24.add(btnFirst);

        btnPrev.setText("<");
        btnPrev.setToolTipText("Prev");
        btnPrev.setPreferredSize(new java.awt.Dimension(50, 30));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel24.add(btnPrev);

        cmbJumlahBaris.setPreferredSize(new java.awt.Dimension(70, 25));
        cmbJumlahBaris.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJumlahBarisItemStateChanged(evt);
            }
        });
        jPanel24.add(cmbJumlahBaris);

        btnNext.setText(">");
        btnNext.setToolTipText("Next");
        btnNext.setPreferredSize(new java.awt.Dimension(50, 30));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel24.add(btnNext);

        btnLast.setText(">>");
        btnLast.setToolTipText("Last");
        btnLast.setPreferredSize(new java.awt.Dimension(50, 30));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel24.add(btnLast);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cmbJenisBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih...", "Angsuran", "Bulanan", "Tunai" }));
        cmbJenisBayar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJenisBayarItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbJenisBayar);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_edit.gif"))); // NOI18N
        jButton23.setText("Edit");
        jButton23.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton23);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_delete.gif"))); // NOI18N
        jButton24.setText("Hapus");
        jButton24.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton24);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_refresh.gif"))); // NOI18N
        jButton25.setText("Refresh");
        jButton25.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton25);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_stop.gif"))); // NOI18N
        jButton26.setText("Keluar");
        jButton26.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton26);

        jLabel2.setText("Cara Membayar:");
        jPanel3.add(jLabel2);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Angsuran");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Bulanan");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton2);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Tunai");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        noHalaman = 1;
        loadData();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        if (noHalaman > 1) {
            noHalaman--;
            loadData();
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (noHalaman < totalHalaman) {
            noHalaman++;
            loadData();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        noHalaman = totalHalaman;
        loadData();
    }//GEN-LAST:event_btnLastActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        DialogAngsuran Da = new DialogAngsuran(null, true);
        Da.setVisible(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        DialogBulanan Db = new DialogBulanan(null, true);
        Db.setVisible(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        DialogTunai Dt = new DialogTunai(null, true);
        Dt.setVisible(true);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        setUndecoretedInternalFrame();
        cbBaris();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbJenisBayarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJenisBayarItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            loadData();
        }
    }//GEN-LAST:event_cmbJenisBayarItemStateChanged

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        updateData();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        deleteData();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void cmbJumlahBarisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJumlahBarisItemStateChanged
        loadData();
    }//GEN-LAST:event_cmbJumlahBarisItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbJenisBayar;
    private javax.swing.JComboBox cmbJumlahBaris;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JTable tablePembayaran;
    // End of variables declaration//GEN-END:variables

}
