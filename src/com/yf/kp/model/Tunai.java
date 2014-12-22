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
@Table(name = "master_tunai")
public class Tunai extends BaseModel {

    private String nama;
    private Double jumlah;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
