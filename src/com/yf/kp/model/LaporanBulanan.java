/*
 * Copyright (C) 2014 anonymous
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
package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "laporan_bulanan")
public class LaporanBulanan extends BaseModel {

    private String nis;
    private String nama;
    private String namaTagihan;
    private String kelas;
    private Double nominal;
    private Double totalBayar;
    private Double kembalian;

    private Boolean januari;
    private Boolean februari;
    private Boolean maret;
    private Boolean april;
    private Boolean mei;
    private Boolean juni;
    private Boolean juli;
    private Boolean agustus;
    private Boolean september;
    private Boolean oktober;
    private Boolean november;
    private Boolean desember;

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

    public String getNamaTagihan() {
        return namaTagihan;
    }

    public void setNamaTagihan(String namaTagihan) {
        this.namaTagihan = namaTagihan;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(Double totalBayar) {
        this.totalBayar = totalBayar;
    }

    public Double getKembalian() {
        return kembalian;
    }

    public void setKembalian(Double kembalian) {
        this.kembalian = kembalian;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Boolean isJanuari() {
        return januari;
    }

    public void setJanuari(Boolean januari) {
        this.januari = januari;
    }

    public Boolean isFebruari() {
        return februari;
    }

    public void setFebruari(Boolean februari) {
        this.februari = februari;
    }

    public Boolean isMaret() {
        return maret;
    }

    public void setMaret(Boolean maret) {
        this.maret = maret;
    }

    public Boolean isApril() {
        return april;
    }

    public void setApril(Boolean april) {
        this.april = april;
    }

    public Boolean isMei() {
        return mei;
    }

    public void setMei(Boolean mei) {
        this.mei = mei;
    }

    public Boolean isJuni() {
        return juni;
    }

    public void setJuni(Boolean juni) {
        this.juni = juni;
    }

    public Boolean isJuli() {
        return juli;
    }

    public void setJuli(Boolean juli) {
        this.juli = juli;
    }

    public Boolean isAgustus() {
        return agustus;
    }

    public void setAgustus(Boolean agustus) {
        this.agustus = agustus;
    }

    public Boolean isSeptember() {
        return september;
    }

    public void setSeptember(Boolean september) {
        this.september = september;
    }

    public Boolean isOktober() {
        return oktober;
    }

    public void setOktober(Boolean oktober) {
        this.oktober = oktober;
    }

    public Boolean isNovember() {
        return november;
    }

    public void setNovember(Boolean november) {
        this.november = november;
    }

    public Boolean isDesember() {
        return desember;
    }

    public void setDesember(Boolean desember) {
        this.desember = desember;
    }

}
