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
package com.yf.kp.design.jenispembayaran;

import com.yf.kp.model.Angsuran;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class AngsuranTableModel extends AbstractTableModel {

    private List<Angsuran> list = new ArrayList<>();
    private final String[] header = {"Id", "Nama", "Kali Bayar", "Nominal/Satuan"};

    public void setList(List<Angsuran> list) {
        this.list = list;
    }

    public void save(Angsuran angsuran) {
        list.add(angsuran);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, Angsuran angsuran) {
        list.set(index, angsuran);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Angsuran getOne(int index) {
        return list.get(index);
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angsuran angsuran = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return angsuran.getId();
            case 1:
                return angsuran.getNama();
            case 2:
                return angsuran.getKaliBayar();
            case 3:
                return angsuran.getJumlah();
            default:
                return null;
        }
    }

}
