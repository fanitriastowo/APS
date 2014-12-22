package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "master_kelas")
public class Kelas extends BaseModel{

    private String nama_kelas;

    public Kelas() {
    }

    public Kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

}
