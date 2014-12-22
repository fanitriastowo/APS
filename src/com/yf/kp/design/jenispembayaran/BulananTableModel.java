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

import com.yf.kp.model.Bulanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class BulananTableModel extends AbstractTableModel {

    private List<Bulanan> list = new ArrayList<>();
    private final String[] header = {"Id", "Nama", "Jan", "Feb", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember", "Nominal/Satuan"};

    public void setList(List<Bulanan> list) {
        this.list = list;
    }

    public void save(Bulanan bulanan) {
        list.add(bulanan);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, Bulanan bulanan) {
        list.set(index, bulanan);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Bulanan getOne(int index) {
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
        Bulanan bulanan = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bulanan.getId();
            case 1:
                return bulanan.getNama();
            case 2:
                return bulanan.isJanuari();
            case 3:
                return bulanan.isFebruari();
            case 4:
                return bulanan.isMaret();
            case 5:
                return bulanan.isApril();
            case 6:
                return bulanan.isMei();
            case 7:
                return bulanan.isJuni();
            case 8:
                return bulanan.isJuli();
            case 9:
                return bulanan.isAgustus();
            case 10:
                return bulanan.isSeptember();
            case 11:
                return bulanan.isOktober();
            case 12:
                return bulanan.isNovember();
            case 13:
                return bulanan.isDesember();
            case 14:
                return bulanan.getJumlah();
            default:
                return null;
        }
    }
}
