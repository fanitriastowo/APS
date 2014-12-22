package com.yf.kp.design.billing;

import com.yf.kp.model.TagihanAngsuran;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class BilingAngsuranTableModel extends AbstractTableModel {

    private List<TagihanAngsuran> list = new ArrayList<>();
    private final String[] header = {"Id", "Nis", "Nama"};

    public void setList(List<TagihanAngsuran> list) {
        this.list = list;
    }

    public void save(TagihanAngsuran angsuran) {
        list.add(angsuran);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, TagihanAngsuran angsuran) {
        list.set(index, angsuran);
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
                return tagihanAngsuran.getNis();
            case 2:
                return tagihanAngsuran.getNama();
            default:
                return null;
        }
    }
}
