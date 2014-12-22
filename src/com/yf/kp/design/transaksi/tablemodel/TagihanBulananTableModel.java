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

import com.yf.kp.model.TagihanBulanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class TagihanBulananTableModel extends AbstractTableModel {

    private List<TagihanBulanan> list = new ArrayList<>();
    private final String[] header = {"Id", "Nama Tagihan", "Nominal"};

    public void setList(List<TagihanBulanan> list) {
        this.list = list;
    }

    public void save(TagihanBulanan nama) {
        list.add(nama);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, TagihanBulanan nama) {
        list.set(index, nama);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TagihanBulanan getOne(int index) {
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
        TagihanBulanan tagihanBulanan = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tagihanBulanan.getId();
            case 1:
                return tagihanBulanan.getNamaTagihan();
            case 2:
                return tagihanBulanan.getJumlah();
            default:
                return null;
        }
    }

}
