/*
 * Copyright (C) 2014 Khasdul
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.yf.kp.design.billing;

import com.stripbandunk.jwidget.model.DefaultDoubleListModel;
import com.yf.kp.model.Angsuran;
import com.yf.kp.model.Bulanan;
import com.yf.kp.model.Kelas;
import com.yf.kp.model.Siswa;
import com.yf.kp.model.TagihanAngsuran;
import com.yf.kp.model.TagihanBulanan;
import com.yf.kp.model.TagihanTunai;
import com.yf.kp.model.Tunai;
import com.yf.kp.service.AngsuranService;
import com.yf.kp.service.BulananService;
import com.yf.kp.service.KelasService;
import com.yf.kp.service.SiswaService;
import com.yf.kp.service.TagihanAngsuranService;
import com.yf.kp.service.TagihanBulananService;
import com.yf.kp.service.TagihanTunaiService;
import com.yf.kp.service.TunaiService;
import com.yf.kp.service.impl.AngsuranServiceImpl;
import com.yf.kp.service.impl.BulananServiceImpl;
import com.yf.kp.service.impl.KelasServiceImpl;
import com.yf.kp.service.impl.SiswaServiceImpl;
import com.yf.kp.service.impl.TagihanAngsuranServiceImpl;
import com.yf.kp.service.impl.TagihanBulananServiceImpl;
import com.yf.kp.service.impl.TagihanTunaiServiceImpl;
import com.yf.kp.service.impl.TunaiServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Khasdul
 */
public class FrameBiling extends javax.swing.JInternalFrame {

    private List<Siswa> listSiswa;
    private SiswaService siswaService;
    private DefaultDoubleListModel<String> dblModel;
    private BilingAngsuranTableModel bilingAngsuranTableModel;
    private BilingBulananTableModel bilingBulananTableModel;
    private BilingTunaiTableModel bilingTunaiTableModel;
    private List<TagihanAngsuran> listTagihanAngsuran;
    private List<TagihanTunai> listTagihanTunai;
    private List<TagihanBulanan> listTagihanBulanan;

    private void pilihKelas() {
        cmbKelas.removeAllItems();
        KelasService kelasService = new KelasServiceImpl();
        List<Kelas> listKelas = kelasService.findAll();
        for (Kelas kelas : listKelas) {
            cmbKelas.addItem(kelas.getNama_kelas());
        }
    }

    private void inputSiswa() {
        cmbAngsuranNamaTagihan.removeAllItems();
        cmbAngsuranNamaTagihan.addItem("Pilih");
        AngsuranService angsuranService = new AngsuranServiceImpl();
        List<Angsuran> listAngsuran = angsuranService.findAll();
        for (Angsuran angsuran : listAngsuran) {
            cmbAngsuranNamaTagihan.addItem(angsuran.getNama());
        }

        cmbBulananNamaTagihan.removeAllItems();
        cmbBulananNamaTagihan.addItem("Pilih");
        BulananService bulananService = new BulananServiceImpl();
        List<Bulanan> listBulanan = bulananService.findAll();
        for (Bulanan bulanan : listBulanan) {
            cmbBulananNamaTagihan.addItem(bulanan.getNama());
        }
        cmbTunaiNamaTagihan.removeAllItems();
        cmbTunaiNamaTagihan.addItem("Pilih");
        TunaiService tunaiService = new TunaiServiceImpl();
        List<Tunai> listTunai = tunaiService.findAll();
        for (Tunai tunai : listTunai) {
            cmbTunaiNamaTagihan.addItem(tunai.getNama());
        }
    }

    /**
     * Tab Angsuran
     */
    private void pilihNamaTagihanAngsuran() {
        bilingAngsuranTableModel = new BilingAngsuranTableModel();
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbAngsuranNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            AngsuranService angsuranService = new AngsuranServiceImpl();
            Angsuran angsuran = angsuranService.findOneByName(cmbAngsuranNamaTagihan.getSelectedItem().toString());
            txtAngsuranJumlah.setText(angsuran.getJumlah().toString());
            txtAngsuranKaliBayar.setText(angsuran.getKaliBayar().toString());
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblAngsuran.setModel(dblModel);
            TagihanAngsuranService service = new TagihanAngsuranServiceImpl();
            listTagihanAngsuran = service.findAllByNamaTagihanAndKelas(cmbAngsuranNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
            bilingAngsuranTableModel.setList(listTagihanAngsuran);
            tblBilingAngsuran.setModel(bilingAngsuranTableModel);
        } else {
            txtAngsuranJumlah.setText("");
            txtAngsuranKaliBayar.setText("");
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblAngsuran.setModel(dblModel);
            if (listTagihanAngsuran != null) {
                listTagihanAngsuran.clear();
                bilingAngsuranTableModel.setList(listTagihanAngsuran);
                tblBilingAngsuran.setModel(bilingAngsuranTableModel);
            }
        }
    }

    private void simpanTagihanAngsuran() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanAngsuranService tagihanAngsuranService = new TagihanAngsuranServiceImpl();
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanAngsuran tagihanAngsuran = new TagihanAngsuran();
            tagihanAngsuran.setNis(siswa.getNis());
            tagihanAngsuran.setNama(siswa.getNama());
            tagihanAngsuran.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanAngsuran.setNamaTagihan(cmbAngsuranNamaTagihan.getSelectedItem().toString());
            tagihanAngsuran.setKategori("Angsuran");
            tagihanAngsuran.setJumlah(Double.valueOf(txtAngsuranJumlah.getText()));
            tagihanAngsuran.setKaliBayar(Integer.valueOf(txtAngsuranKaliBayar.getText()));
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanAngsuranService.saveBatch(tagihanAngsuran, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblAngsuran.setModel(dblModel);
        bilingAngsuranTableModel = new BilingAngsuranTableModel();
        listTagihanAngsuran = tagihanAngsuranService.findAllByNamaTagihanAndKelas(cmbAngsuranNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
        bilingAngsuranTableModel.setList(listTagihanAngsuran);
        tblBilingAngsuran.setModel(bilingAngsuranTableModel);
    }

    private void hapusTagihanAngsuran() {
        if (tblBilingAngsuran.getSelectedRow() != -1) {
            int index = tblBilingAngsuran.convertRowIndexToModel(tblBilingAngsuran.getSelectedRow());
            TagihanAngsuran model = listTagihanAngsuran.get(index);
            if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                TagihanAngsuranService service = new TagihanAngsuranServiceImpl();
                service.delete(model.getId());
                pilihNamaTagihanAngsuran();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }

    /**
     * Tab Tunai
     */
    private void pilihTunaiNamaTagihan() {
        bilingTunaiTableModel = new BilingTunaiTableModel();
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbTunaiNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            TunaiService tunaiService = new TunaiServiceImpl();
            Tunai tunai = tunaiService.findOneByName(cmbTunaiNamaTagihan.getSelectedItem().toString());
            txtTunaiJumlah.setText(tunai.getJumlah().toString());
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblTunai.setModel(dblModel);
            TagihanTunaiService service = new TagihanTunaiServiceImpl();
            listTagihanTunai = service.findAllByNamaTagihanAndKelas(cmbTunaiNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
            bilingTunaiTableModel.setList(listTagihanTunai);
            tblBilingTunai.setModel(bilingTunaiTableModel);
        } else {
            txtTunaiJumlah.setText("");
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblTunai.setModel(dblModel);
            if (listTagihanTunai != null) {
                listTagihanTunai.clear();
                bilingTunaiTableModel.setList(listTagihanTunai);
                tblBilingTunai.setModel(bilingTunaiTableModel);
            }
        }
    }

    private void simpanTunai() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanTunaiService tagihanTunaiService = new TagihanTunaiServiceImpl();
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanTunai tagihanTunai = new TagihanTunai();
            tagihanTunai.setNis(siswa.getNis());
            tagihanTunai.setNama(siswa.getNama());
            tagihanTunai.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanTunai.setNamaTagihan(cmbTunaiNamaTagihan.getSelectedItem().toString());
            tagihanTunai.setKategori("Tunai");
            tagihanTunai.setJumlah(Double.valueOf(txtTunaiJumlah.getText()));
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanTunaiService.saveBatch(tagihanTunai, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblTunai.setModel(dblModel);
        bilingTunaiTableModel = new BilingTunaiTableModel();
        listTagihanTunai = tagihanTunaiService.findAllByNamaTagihanAndKelas(cmbTunaiNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
        bilingTunaiTableModel.setList(listTagihanTunai);
        tblBilingTunai.setModel(bilingTunaiTableModel);
    }

    private void hapusTagihanTunai() {
        if (tblBilingTunai.getSelectedRow() != -1) {
            int index = tblBilingTunai.convertRowIndexToModel(tblBilingTunai.getSelectedRow());
            TagihanTunai model = listTagihanTunai.get(index);
            if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                TagihanTunaiService service = new TagihanTunaiServiceImpl();
                service.delete(model.getId());
                pilihTunaiNamaTagihan();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }

    /**
     * Tab Bulanan
     */
    private void pilihBulananNamaTagihan() {
        bilingBulananTableModel = new BilingBulananTableModel();
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbBulananNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblBulanan.setModel(dblModel);
            TagihanBulananService service = new TagihanBulananServiceImpl();
            listTagihanBulanan = service.findAllByNamaTagihanAndKelas(cmbBulananNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
            bilingBulananTableModel.setList(listTagihanBulanan);
            tblBilingBulanan.setModel(bilingBulananTableModel);
        } else {
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblBulanan.setModel(dblModel);
            if (listTagihanBulanan != null) {
                listTagihanBulanan.clear();
                bilingBulananTableModel.setList(listTagihanBulanan);
                tblBilingBulanan.setModel(bilingBulananTableModel);
            }
        }
    }

    private void simpanBulanan() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanBulananService tagihanBulananService = new TagihanBulananServiceImpl();
        BulananService bulananService = new BulananServiceImpl();
        Bulanan bulanan = bulananService.findOneByName(cmbBulananNamaTagihan.getSelectedItem().toString());
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanBulanan tagihanBulanan = new TagihanBulanan();
            tagihanBulanan.setNis(siswa.getNis());
            tagihanBulanan.setNama(siswa.getNama());
            tagihanBulanan.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanBulanan.setNamaTagihan(cmbBulananNamaTagihan.getSelectedItem().toString());
            tagihanBulanan.setKategori("Bulanan");
            tagihanBulanan.setJumlah(bulanan.getJumlah());
            tagihanBulanan.setJanuari(!bulanan.isJanuari());
            tagihanBulanan.setFebruari(!bulanan.isFebruari());
            tagihanBulanan.setMaret(!bulanan.isMaret());
            tagihanBulanan.setApril(!bulanan.isApril());
            tagihanBulanan.setMei(!bulanan.isMei());
            tagihanBulanan.setJuni(!bulanan.isJuni());
            tagihanBulanan.setJuli(!bulanan.isJuli());
            tagihanBulanan.setAgustus(!bulanan.isAgustus());
            tagihanBulanan.setSeptember(!bulanan.isSeptember());
            tagihanBulanan.setOktober(!bulanan.isOktober());
            tagihanBulanan.setNovember(!bulanan.isNovember());
            tagihanBulanan.setDesember(!bulanan.isDesember());
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanBulananService.saveBatch(tagihanBulanan, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblBulanan.setModel(dblModel);
        bilingBulananTableModel = new BilingBulananTableModel();
        listTagihanBulanan = tagihanBulananService.findAllByNamaTagihanAndKelas(cmbBulananNamaTagihan.getSelectedItem().toString(), cmbKelas.getSelectedItem().toString());
        bilingBulananTableModel.setList(listTagihanBulanan);
        tblBilingBulanan.setModel(bilingBulananTableModel);
    }

    private void hapusTagihanBulanan() {
        if (tblBilingBulanan.getSelectedRow() != -1) {
            int index = tblBilingBulanan.convertRowIndexToModel(tblBilingBulanan.getSelectedRow());
            TagihanBulanan model = listTagihanBulanan.get(index);
            if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                TagihanBulananService service = new TagihanBulananServiceImpl();
                service.delete(model.getId());
                pilihBulananNamaTagihan();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
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

    /**
     * Creates new form FrameBiling
     */
    public FrameBiling() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabUtama = new javax.swing.JTabbedPane();
        panelAngsuran = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbAngsuranNamaTagihan = new javax.swing.JComboBox();
        dblAngsuran = new com.stripbandunk.jwidget.JDoubleList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtAngsuranJumlah = new javax.swing.JTextField();
        txtAngsuranKaliBayar = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBilingAngsuran = new javax.swing.JTable();
        panelBulanan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbBulananNamaTagihan = new javax.swing.JComboBox();
        dblBulanan = new com.stripbandunk.jwidget.JDoubleList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBilingBulanan = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        panelTunai = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbTunaiNamaTagihan = new javax.swing.JComboBox();
        dblTunai = new com.stripbandunk.jwidget.JDoubleList();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtTunaiJumlah = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBilingTunai = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        tabUtama.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabUtamaStateChanged(evt);
            }
        });

        jLabel2.setText("Pilih Nama Tagihan");

        cmbAngsuranNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAngsuranNamaTagihanItemStateChanged(evt);
            }
        });

        jButton1.setText("Keluar");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Simpan");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtAngsuranJumlah.setEnabled(false);

        txtAngsuranKaliBayar.setEnabled(false);

        jButton7.setText("Hapus");
        jButton7.setName(""); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tblBilingAngsuran);

        javax.swing.GroupLayout panelAngsuranLayout = new javax.swing.GroupLayout(panelAngsuran);
        panelAngsuran.setLayout(panelAngsuranLayout);
        panelAngsuranLayout.setHorizontalGroup(
            panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAngsuranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblAngsuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAngsuranLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAngsuranLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAngsuranNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAngsuranKaliBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAngsuranLayout.setVerticalGroup(
            panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAngsuranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbAngsuranNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuranKaliBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAngsuranLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabUtama.addTab("Angsuran", panelAngsuran);

        jLabel4.setText("Pilih Nama Tagihan");

        cmbBulananNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBulananNamaTagihanItemStateChanged(evt);
            }
        });

        jButton3.setText("Keluar");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Simpan");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(tblBilingBulanan);

        jButton9.setText("Hapus");
        jButton9.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBulananLayout = new javax.swing.GroupLayout(panelBulanan);
        panelBulanan.setLayout(panelBulananLayout);
        panelBulananLayout.setHorizontalGroup(
            panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBulananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblBulanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBulananLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBulananLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelBulananLayout.setVerticalGroup(
            panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBulananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblBulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBulananLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabUtama.addTab("Bulanan", panelBulanan);

        jLabel6.setText("Pilih Nama Tagihan");

        cmbTunaiNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTunaiNamaTagihanItemStateChanged(evt);
            }
        });

        jButton5.setText("Keluar");
        jButton5.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Simpan");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtTunaiJumlah.setEnabled(false);

        jButton8.setText("Hapus");
        jButton8.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(tblBilingTunai);

        javax.swing.GroupLayout panelTunaiLayout = new javax.swing.GroupLayout(panelTunai);
        panelTunai.setLayout(panelTunaiLayout);
        panelTunaiLayout.setHorizontalGroup(
            panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTunaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblTunai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTunaiLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTunaiNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 181, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTunaiLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelTunaiLayout.setVerticalGroup(
            panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTunaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTunaiNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblTunai, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTunaiLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabUtama.addTab("Tunai", panelTunai);

        jLabel1.setText("Pilih Kelas");

        cmbKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKelasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabUtama)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cmbAngsuranNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAngsuranNamaTagihanItemStateChanged
        pilihNamaTagihanAngsuran();
    }//GEN-LAST:event_cmbAngsuranNamaTagihanItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        simpanTagihanAngsuran();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbTunaiNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTunaiNamaTagihanItemStateChanged
        pilihTunaiNamaTagihan();
    }//GEN-LAST:event_cmbTunaiNamaTagihanItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        simpanTunai();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cmbBulananNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBulananNamaTagihanItemStateChanged
        pilihBulananNamaTagihan();
    }//GEN-LAST:event_cmbBulananNamaTagihanItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        simpanBulanan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        setUndecoretedInternalFrame();
        pilihKelas();
        inputSiswa();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tabUtamaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabUtamaStateChanged
    }//GEN-LAST:event_tabUtamaStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        hapusTagihanAngsuran();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        hapusTagihanBulanan();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        hapusTagihanTunai();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cmbKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKelasItemStateChanged
        cmbAngsuranNamaTagihan.setSelectedItem("Pilih");
        cmbBulananNamaTagihan.setSelectedItem("Pilih");
        cmbTunaiNamaTagihan.setSelectedItem("Pilih");
    }//GEN-LAST:event_cmbKelasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbAngsuranNamaTagihan;
    private javax.swing.JComboBox cmbBulananNamaTagihan;
    private javax.swing.JComboBox cmbKelas;
    private javax.swing.JComboBox cmbTunaiNamaTagihan;
    private com.stripbandunk.jwidget.JDoubleList dblAngsuran;
    private com.stripbandunk.jwidget.JDoubleList dblBulanan;
    private com.stripbandunk.jwidget.JDoubleList dblTunai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelAngsuran;
    private javax.swing.JPanel panelBulanan;
    private javax.swing.JPanel panelTunai;
    private javax.swing.JTabbedPane tabUtama;
    private javax.swing.JTable tblBilingAngsuran;
    private javax.swing.JTable tblBilingBulanan;
    private javax.swing.JTable tblBilingTunai;
    private javax.swing.JTextField txtAngsuranJumlah;
    private javax.swing.JTextField txtAngsuranKaliBayar;
    private javax.swing.JTextField txtTunaiJumlah;
    // End of variables declaration//GEN-END:variables
}
