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

import com.yf.kp.model.Tunai;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class TunaiTableModel extends AbstractTableModel {

    private List<Tunai> list = new ArrayList<>();
    private final String header[] = {"Id", "Nama Pembayaran", "Nominal/Satuan"};

    public void setList(List<Tunai> list) {
        this.list = list;
    }

    public void save(Tunai tunai) {
        list.add(tunai);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, Tunai tunai) {
        list.set(index, tunai);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Tunai getOne(int index) {
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
        Tunai tunai = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tunai.getId();
            case 1:
                return tunai.getNama();
            case 2:
                return tunai.getJumlah();
            default:
                return null;
        }

    }

}
