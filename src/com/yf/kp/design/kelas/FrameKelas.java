package com.yf.kp.design.kelas;

import com.yf.kp.model.Kelas;
import com.yf.kp.service.CountService;
import com.yf.kp.service.KelasService;
import com.yf.kp.service.impl.CountServiceImpl;
import com.yf.kp.service.impl.KelasServiceImpl;
import com.yf.kp.utility.TableAutoColumnWidth;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author aspire
 */
public class FrameKelas extends javax.swing.JInternalFrame {

    Integer noHalaman = 1;
    Integer banyakBaris = 10;
    Integer totalHalaman = 1;
    Integer totalData = 0;
    private KelasService service;
    private KelasTableModel tableModel;
    private CountService countService;
    private List<Kelas> list;
    private Kelas model;

    /**
     * Creates new form FrameKelas
     */
    public FrameKelas() {
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

    private void loadData() {
        service = new KelasServiceImpl();
        tableModel = new KelasTableModel();
        countService = new CountServiceImpl();

        totalData = countService.countKelas().intValue();
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

        list = service.findAll(noHalaman, banyakBaris);
        tableModel.setList(list);
        tableKelas.setModel(tableModel);
        TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tableKelas);
    }

    public void cbBaris() {
        cmbJumlahBaris.addItem("10");
        cmbJumlahBaris.addItem("20");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKelas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        cmbJumlahBaris = new javax.swing.JComboBox();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNamaKelas = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setTitle("Data Kelas");
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kelas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel3.setOpaque(false);

        jScrollPane2.setViewportView(tableKelas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setOpaque(false);

        btnFirst.setText("<<");
        btnFirst.setToolTipText("First");
        btnFirst.setPreferredSize(new java.awt.Dimension(50, 30));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel4.add(btnFirst);

        btnPrev.setText("<");
        btnPrev.setToolTipText("Prev");
        btnPrev.setPreferredSize(new java.awt.Dimension(50, 30));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrev);

        cmbJumlahBaris.setPreferredSize(new java.awt.Dimension(70, 25));
        cmbJumlahBaris.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJumlahBarisItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbJumlahBaris);

        btnNext.setText(">");
        btnNext.setToolTipText("Next");
        btnNext.setPreferredSize(new java.awt.Dimension(50, 30));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setText(">>");
        btnLast.setToolTipText("Last");
        btnLast.setPreferredSize(new java.awt.Dimension(50, 30));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel4.add(btnLast);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/date_delete.gif"))); // NOI18N
        jButton1.setText("Hapus");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_edit.gif"))); // NOI18N
        jButton3.setText("Edit");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_stop.gif"))); // NOI18N
        jButton2.setText("Keluar");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jLabel1.setText("Nama Kelas");

        txtNamaKelas.setPreferredSize(new java.awt.Dimension(200, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/date_new.gif"))); // NOI18N
        jButton4.setText("Simpan");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        setUndecoretedInternalFrame();
        cbBaris();
        loadData();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (tableKelas.getSelectedRow() != -1) {
            int index = tableKelas.convertRowIndexToModel(tableKelas.getSelectedRow());
            Kelas kelas = list.get(index);
            txtNamaKelas.setText(kelas.getNama_kelas());
            this.setTitle(kelas.getId().toString());
            jButton4.setText("Update");
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (txtNamaKelas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Kelas Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            service = new KelasServiceImpl();
            model = new Kelas();
            if ("Update".equals(jButton4.getText())) {
                model.setId(Long.valueOf(this.getTitle()));
                model.setNama_kelas(txtNamaKelas.getText());
                service.update(model);
                txtNamaKelas.setText("");
                loadData();
                jButton4.setText("Simpan");
            } else {
                model.setNama_kelas(txtNamaKelas.getText());
                service.save(model);
                txtNamaKelas.setText("");
                loadData();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tableKelas.getSelectedRow() != -1) {
            int index = tableKelas.convertRowIndexToModel(tableKelas.getSelectedRow());
            Kelas kelas = list.get(index);
            if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                service = new KelasServiceImpl();
                service.delete(kelas.getId());
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbJumlahBarisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJumlahBarisItemStateChanged
        loadData();
    }//GEN-LAST:event_cmbJumlahBarisItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox cmbJumlahBaris;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableKelas;
    private javax.swing.JTextField txtNamaKelas;
    // End of variables declaration//GEN-END:variables

}
