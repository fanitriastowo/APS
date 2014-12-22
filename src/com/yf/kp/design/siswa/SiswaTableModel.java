package com.yf.kp.design.siswa;

import com.yf.kp.model.Siswa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anonymous
 */
public class SiswaTableModel extends AbstractTableModel {

    private List<Siswa> list = new ArrayList<>();
    private final String[] header = {"Id", "Nis", "Nama", "Kelas"};

    public void setList(List<Siswa> list) {
        this.list = list;
    }

    public void save(Siswa siswa) {
        list.add(siswa);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, Siswa siswa) {
        list.set(index, siswa);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Siswa getOne(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Siswa siswa = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return siswa.getId();
            case 1:
                return siswa.getNis();
            case 2:
                return siswa.getNama();
            case 3:
                return siswa.getKelas();
            default:
                return null;
        }
    }

}
