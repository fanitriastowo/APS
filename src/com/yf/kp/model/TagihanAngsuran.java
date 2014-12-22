package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "tagihan_angsuran")
public class TagihanAngsuran extends BaseModel {

    private String nis;
    private String nama;
    private String kelas;
    private String namaTagihan;
    private String kategori;
    private Integer kaliBayar;
    private Double jumlah;

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNamaTagihan() {
        return namaTagihan;
    }

    public void setNamaTagihan(String namaTagihan) {
        this.namaTagihan = namaTagihan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Integer getKaliBayar() {
        return kaliBayar;
    }

    public void setKaliBayar(Integer kaliBayar) {
        this.kaliBayar = kaliBayar;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
