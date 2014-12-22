package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "master_siswa")
public class Siswa extends BaseModel {

    private String nis;
    private String nama;
    private String kelas;
    private String jenis_kelamin;
    private String agama;
    private String tempat_lahir;
    @Temporal(TemporalType.DATE)
    private Date tgl_lahir;
    private String alamat;
    private String nama_ortu;
    private String pekerjaan;
    private String agama_ortu;
    private String telp;
    private String alamat_ortu;

    public Siswa() {
    }

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

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_ortu() {
        return nama_ortu;
    }

    public void setNama_ortu(String nama_ortu) {
        this.nama_ortu = nama_ortu;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAgama_ortu() {
        return agama_ortu;
    }

    public void setAgama_ortu(String agama_ortu) {
        this.agama_ortu = agama_ortu;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat_ortu() {
        return alamat_ortu;
    }

    public void setAlamat_ortu(String alamat_ortu) {
        this.alamat_ortu = alamat_ortu;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

}
