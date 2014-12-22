package com.yf.kp.design.billing;

import com.yf.kp.model.TagihanBulanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class BilingBulananTableModel extends AbstractTableModel {

    private List<TagihanBulanan> list = new ArrayList<>();
    private final String[] header = {"No", "Nis", "Nama"};

    public void setList(List<TagihanBulanan> list) {
        this.list = list;
    }

    public void save(TagihanBulanan bulanan) {
        list.add(bulanan);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, TagihanBulanan bulanan) {
        list.set(index, bulanan);
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
            case 1:
                return tagihanBulanan.getNis();
            case 2:
                return tagihanBulanan.getNama();
            default:
                return null;
        }
    }

}
