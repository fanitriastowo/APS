/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.kp.design.siswa;

import com.yf.kp.model.Kelas;
import com.yf.kp.model.Siswa;
import com.yf.kp.service.CountService;
import com.yf.kp.service.KelasService;
import com.yf.kp.service.SiswaService;
import com.yf.kp.service.impl.CountServiceImpl;
import com.yf.kp.service.impl.KelasServiceImpl;
import com.yf.kp.service.impl.SiswaServiceImpl;
import com.yf.kp.utility.TableAutoColumnWidth;
import com.yf.kp.utility.TextBehaviour;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.HibernateException;

/**
 *
 * @author aspire
 */
public class FrameSiswa extends javax.swing.JInternalFrame {

    Integer noHalaman = 1;
    Integer banyakBaris = 10;
    Integer totalHalaman = 1;
    Integer totalData = 0;
    private SiswaService serviceSiswa;
    private SiswaTableModel tableModel;
    private CountService countService;
    private List<Siswa> listSiswa;
    private Siswa modelSiswa;
    private JFileChooser jFCImport;
    private SiswaService siswaService;

    /**
     * Creates new form FrameSiswa
     */
    public FrameSiswa() {
        initComponents();
    }

    public void fillData(File file) {
        try {
            siswaService = new SiswaServiceImpl();
            Siswa siswa;
            try (FileInputStream input = new FileInputStream(file)) {
                POIFSFileSystem fs = new POIFSFileSystem(input);
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                Row row;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    System.out.println(i);
                    row = sheet.getRow(i);
                    siswa = new Siswa();
                    siswa.setNis(row.getCell(0).getStringCellValue());
                    siswa.setNama(row.getCell(1).getStringCellValue());
                    siswa.setKelas(row.getCell(2).getStringCellValue());
                    siswa.setJenis_kelamin(row.getCell(3).getStringCellValue());
                    siswa.setAgama(row.getCell(4).getStringCellValue());
                    siswa.setTempat_lahir(row.getCell(5).getStringCellValue());
                    siswa.setTgl_lahir(row.getCell(6).getDateCellValue());
                    siswa.setAlamat(row.getCell(7).getStringCellValue());
                    siswa.setNama_ortu(row.getCell(8).getStringCellValue());
                    siswa.setPekerjaan(row.getCell(9).getStringCellValue());
                    siswa.setAgama_ortu(row.getCell(10).getStringCellValue());
                    siswa.setTelp(row.getCell(11).getStringCellValue());
                    siswa.setAlamat_ortu(row.getCell(12).getStringCellValue());
                    siswaService.save(siswa);
                }
            }
            JOptionPane.showMessageDialog(this, "Data Berhasil Dimasukan");
        } catch (HeadlessException | IOException | HibernateException | OfficeXmlFileException | IllegalStateException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Error, Contact Your System Admin\n" + e.getMessage());
        }
    }

    private void importExcel() {
        try {
            jFCImport = new JFileChooser();
            jFCImport.setFileFilter(new javax.swing.filechooser.FileFilter() {

                String description = "Excel File(*.xls)";
                String extension = "xls";

                @Override
                public boolean accept(File f) {
                    if (f == null) {
                        return false;
                    }
                    if (f.isDirectory()) {
                        return true;
                    }
                    return f.getName().toLowerCase().endsWith(extension);
                }

                @Override
                public String getDescription() {
                    return description;
                }
            });
            if (jFCImport.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = jFCImport.getSelectedFile();
                if (file.getName().endsWith("xls")) {
                    fillData(file);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select only Excel file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error, Contact Your System Admin\n" + e.getMessage());
        }
    }

    public void prepareSearch() {
        if (cmbCari.getSelectedItem() == "NIS") {
            searchByNis();
        } else if (cmbCari.getSelectedItem() == "NAMA") {
            searchByNama();
        }
    }

    private void numericOnly() {
        TextBehaviour.setNumericOnly(txtNis);
        TextBehaviour.setNumericOnly(txtTelp);
    }

    public void searchByNis() {
        try {
            serviceSiswa = new SiswaServiceImpl();
            tableModel = new SiswaTableModel();
            String nis = txtCari.getText();
            listSiswa = serviceSiswa.findByNis(nis);
            tableModel.setList(listSiswa);
            tableSiswa.setModel(tableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tableSiswa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dimohon Input dengan benar");
        }
    }

    public void searchByNama() {
        try {
            serviceSiswa = new SiswaServiceImpl();
            tableModel = new SiswaTableModel();
            String nama = txtCari.getText();
            listSiswa = serviceSiswa.findByNama(nama);
            tableModel.setList(listSiswa);
            tableSiswa.setModel(tableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tableSiswa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dimohon Input dengan benar");
        }
    }

    private void clear() {
        this.setTitle("");
        txtNis.setText("");
        txtNama.setText("");
        txtTempatLahir.setText("");
        txtAlamat.setText("");
        txtNamaOrtu.setText("");
        txtPekerjaanOrtu.setText("");
        txtTelp.setText("");
        txtAlamatOrtu.setText("");
        dateTanggalLahir.setDate(null);
        loadData();
        jButton1.setText("Simpan");
    }

    private void delete() {
        if (tableSiswa.getSelectedRow() != -1) {
            int index = tableSiswa.convertRowIndexToModel(tableSiswa.getSelectedRow());
            Siswa siswa = listSiswa.get(index);
            if (JOptionPane.showConfirmDialog(this, "Are You Sure To Delete This Item?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                serviceSiswa = new SiswaServiceImpl();
                serviceSiswa.delete(siswa.getId());
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }

    private void updateSiswa() {
        if (tableSiswa.getSelectedRow() != -1) {
            int index = tableSiswa.convertRowIndexToModel(tableSiswa.getSelectedRow());
            Siswa siswa = listSiswa.get(index);
            this.setTitle(siswa.getId().toString());
            txtNis.setText(siswa.getNis());
            txtNama.setText(siswa.getNama());
            cmbKelas.setSelectedItem(siswa.getKelas());
            cmbJenisKelamin.setSelectedItem(siswa.getJenis_kelamin());
            txtTempatLahir.setText(siswa.getTempat_lahir());
            dateTanggalLahir.setDate(siswa.getTgl_lahir());
            cmbAgama.setSelectedItem(siswa.getAgama());
            txtAlamat.setText(siswa.getAlamat());
            txtNamaOrtu.setText(siswa.getNama_ortu());
            cmbAgamaOrtu.setSelectedItem(siswa.getAgama_ortu());
            txtPekerjaanOrtu.setText(siswa.getPekerjaan());
            txtTelp.setText(siswa.getTelp());
            txtAlamatOrtu.setText(siswa.getAlamat_ortu());
            jButton1.setText("Update");
        } else {
            JOptionPane.showMessageDialog(this, "Silahakan Pilih Data Terlebih Dahulu");
        }
    }

    public void cbBaris() {
        cmbBanyakBaris.addItem("25");
        cmbBanyakBaris.addItem("50");
    }

    public void kelasLookup() {
        KelasService kelasService = new KelasServiceImpl();
        cmbKelas.removeAllItems();
        List<Kelas> listKelas = kelasService.findAll();
        for (Kelas kelas : listKelas) {
            cmbKelas.addItem(kelas.getNama_kelas());
        }
    }

    private void loadData() {
        serviceSiswa = new SiswaServiceImpl();
        tableModel = new SiswaTableModel();
        countService = new CountServiceImpl();

        totalData = countService.countSiswa().intValue();
        banyakBaris = Integer.parseInt(cmbBanyakBaris.getSelectedItem().toString());
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

        listSiswa = serviceSiswa.findAll(noHalaman, banyakBaris);
        tableModel.setList(listSiswa);
        tableSiswa.setModel(tableModel);
        TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tableSiswa);
    }

    private void save() {
        if (txtNis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nis Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtTempatLahir.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tempat Lahir Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (dateTanggalLahir.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal Lahir Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtAlamat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alamat Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtNamaOrtu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Orang Tua Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtPekerjaanOrtu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pekerjaan Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtTelp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telepon Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtAlamatOrtu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alamat Orang Tua Masih Kosong", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            serviceSiswa = new SiswaServiceImpl();
            modelSiswa = new Siswa();
            if ("Update".equals(jButton1.getText())) {
                modelSiswa.setId(Long.valueOf(this.getTitle()));
                modelSiswa.setNis(txtNis.getText());
                modelSiswa.setNama(txtNama.getText());
                modelSiswa.setKelas(cmbKelas.getSelectedItem().toString());
                modelSiswa.setJenis_kelamin(cmbJenisKelamin.getSelectedItem().toString());
                modelSiswa.setTempat_lahir(txtTempatLahir.getText());
                modelSiswa.setTgl_lahir(dateTanggalLahir.getDate());
                modelSiswa.setAgama(cmbAgama.getSelectedItem().toString());
                modelSiswa.setAlamat(txtAlamat.getText());
                modelSiswa.setNama_ortu(txtNamaOrtu.getText());
                modelSiswa.setAgama_ortu(cmbAgamaOrtu.getSelectedItem().toString());
                modelSiswa.setPekerjaan(txtPekerjaanOrtu.getText());
                modelSiswa.setTelp(txtTelp.getText());
                modelSiswa.setAlamat_ortu(txtAlamatOrtu.getText());
                serviceSiswa.update(modelSiswa);
                loadData();
                jButton1.setText("Simpan");

                this.setTitle("");
                txtNis.setText("");
                txtNama.setText("");
                txtTempatLahir.setText("");
                txtAlamat.setText("");
                txtNamaOrtu.setText("");
                txtPekerjaanOrtu.setText("");
                txtTelp.setText("");
                txtAlamatOrtu.setText("");
                dateTanggalLahir.setDate(null);
            } else {
                modelSiswa.setNis(txtNis.getText());
                modelSiswa.setNama(txtNama.getText());
                modelSiswa.setKelas(cmbKelas.getSelectedItem().toString());
                modelSiswa.setJenis_kelamin(cmbJenisKelamin.getSelectedItem().toString());
                modelSiswa.setTempat_lahir(txtTempatLahir.getText());
                modelSiswa.setTgl_lahir(dateTanggalLahir.getDate());
                modelSiswa.setAgama(cmbAgama.getSelectedItem().toString());
                modelSiswa.setAlamat(txtAlamat.getText());
                modelSiswa.setNama_ortu(txtNamaOrtu.getText());
                modelSiswa.setAgama_ortu(cmbAgamaOrtu.getSelectedItem().toString());
                modelSiswa.setPekerjaan(txtPekerjaanOrtu.getText());
                modelSiswa.setTelp(txtTelp.getText());
                modelSiswa.setAlamat_ortu(txtAlamatOrtu.getText());
                serviceSiswa.save(modelSiswa);
                loadData();
                this.setTitle("");
                txtNis.setText("");
                txtNama.setText("");
                txtTempatLahir.setText("");
                txtAlamat.setText("");
                txtNamaOrtu.setText("");
                txtPekerjaanOrtu.setText("");
                txtTelp.setText("");
                txtAlamatOrtu.setText("");
                dateTanggalLahir.setDate(null);
            }
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

    private void hapusSemua() {
        if (JOptionPane.showConfirmDialog(this, "Data akan dihapus semua, Anda yakin?", "Delete Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            serviceSiswa = new SiswaServiceImpl();
            serviceSiswa.hapusSemua();
            loadData();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNis = new javax.swing.JTextField();
        dateTanggalLahir = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        cmbAgama = new javax.swing.JComboBox();
        txtTempatLahir = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox();
        cmbJenisKelamin = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNamaOrtu = new javax.swing.JTextField();
        txtPekerjaanOrtu = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        cmbAgamaOrtu = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamatOrtu = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSiswa = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        cmbBanyakBaris = new javax.swing.JComboBox();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cmbCari = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

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

        jLabel1.setText("Nis");

        jLabel2.setText("Nama");

        jLabel3.setText("Tempat Lahir");

        jLabel4.setText("Jenis Kelamin");

        jLabel5.setText("Tanggal Lahir");

        jLabel6.setText("Agama");

        jLabel7.setText("Alamat");

        dateTanggalLahir.setDateFormatString("yyyy-MM-dd");

        txtAlamat.setColumns(20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Katolik", "Hindu", "Budha", "Lain-lain" }));

        jLabel14.setText("Kelas");

        cmbKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L", "P" }));
        cmbJenisKelamin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJenisKelaminItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateTanggalLahir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTempatLahir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbKelas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNis)
                            .addComponent(cmbAgama, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pribadi", jPanel1);

        jLabel8.setText("Nama");

        jLabel9.setText("Agama");

        jLabel10.setText("Pekerjaan");

        jLabel11.setText("Telp");

        jLabel12.setText("Alamat");

        cmbAgamaOrtu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Katolik", "Hindu", "Budha", "Lain-lain" }));

        txtAlamatOrtu.setColumns(20);
        txtAlamatOrtu.setLineWrap(true);
        txtAlamatOrtu.setRows(5);
        jScrollPane3.setViewportView(txtAlamatOrtu);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/date_new.gif"))); // NOI18N
        jButton1.setText("Simpan");
        jButton1.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_cross.gif"))); // NOI18N
        jButton5.setText("Batal");
        jButton5.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbAgamaOrtu, javax.swing.GroupLayout.Alignment.LEADING, 0, 272, Short.MAX_VALUE)
                    .addComponent(txtPekerjaanOrtu)
                    .addComponent(txtTelp)
                    .addComponent(txtNamaOrtu)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNamaOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbAgamaOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPekerjaanOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Orang Tua", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel3.setOpaque(false);

        jScrollPane2.setViewportView(tableSiswa);

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

        cmbBanyakBaris.setPreferredSize(new java.awt.Dimension(70, 25));
        cmbBanyakBaris.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBanyakBarisItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbBanyakBaris);

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

        jLabel13.setText("Cari");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_stop.gif"))); // NOI18N
        jButton2.setText("Keluar");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_edit.gif"))); // NOI18N
        jButton3.setText("Edit");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/page_delete.gif"))); // NOI18N
        jButton4.setText("Hapus");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NAMA", "NIS" }));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/arrow_down.gif"))); // NOI18N
        jButton6.setText("Import");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Hapus Semua");
        jButton7.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        setUndecoretedInternalFrame();
        cbBaris();
        kelasLookup();
        loadData();
        numericOnly();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        delete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateSiswa();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        clear();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        prepareSearch();
    }//GEN-LAST:event_txtCariKeyReleased

    private void cmbJenisKelaminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJenisKelaminItemStateChanged
        if (cmbJenisKelamin.getSelectedItem().equals("L")) {
            jTextField1.setText("Laki-laki");
        } else if (cmbJenisKelamin.getSelectedItem().equals("P")) {
            jTextField1.setText("Perempuan");
        }

    }//GEN-LAST:event_cmbJenisKelaminItemStateChanged

    private void cmbBanyakBarisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBanyakBarisItemStateChanged
        loadData();
    }//GEN-LAST:event_cmbBanyakBarisItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        importExcel();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        hapusSemua();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox cmbAgama;
    private javax.swing.JComboBox cmbAgamaOrtu;
    private javax.swing.JComboBox cmbBanyakBaris;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbJenisKelamin;
    private javax.swing.JComboBox cmbKelas;
    private com.toedter.calendar.JDateChooser dateTanggalLahir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableSiswa;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtAlamatOrtu;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaOrtu;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtPekerjaanOrtu;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables

}
