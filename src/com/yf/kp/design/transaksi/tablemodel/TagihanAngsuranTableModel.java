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
package com.yf.kp.design.transaksi.tablemodel;

import com.yf.kp.model.TagihanAngsuran;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class TagihanAngsuranTableModel extends AbstractTableModel {

    private List<TagihanAngsuran> list = new ArrayList<>();
    private final String[] header = {"Id", "Nama Tagihan", "Nominal", "Jumlah Kali Bayar"};

    public void setList(List<TagihanAngsuran> list) {
        this.list = list;
    }

    public void save(TagihanAngsuran kelas) {
        list.add(kelas);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, TagihanAngsuran kelas) {
        list.set(index, kelas);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TagihanAngsuran getOne(int index) {
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
        TagihanAngsuran tagihanAngsuran = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tagihanAngsuran.getId();
            case 1:
                return tagihanAngsuran.getNamaTagihan();
            case 2:
                return tagihanAngsuran.getJumlah();
            case 3:
                return tagihanAngsuran.getKaliBayar();
            default:
                return null;
        }
    }

}
