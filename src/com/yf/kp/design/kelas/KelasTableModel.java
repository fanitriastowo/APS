package com.yf.kp.design.kelas;

import com.yf.kp.model.Kelas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class KelasTableModel extends AbstractTableModel {

    private List<Kelas> list = new ArrayList<>();
    private final String[] header = {"Id", "Kelas"};

    public void setList(List<Kelas> list) {
        this.list = list;
    }

    public void save(Kelas kelas) {
        list.add(kelas);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, Kelas kelas) {
        list.set(index, kelas);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Kelas getOne(int index) {
        return list.get(index);
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
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kelas kelas = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kelas.getId();
            case 1:
                return kelas.getNama_kelas();
            default:
                return null;
        }
    }

}
