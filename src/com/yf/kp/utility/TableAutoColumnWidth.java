/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.kp.utility;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ASUS
 */
public class TableAutoColumnWidth {

    public TableAutoColumnWidth(JTable table) {
        autoColumn(table);
    }

    private void autoColumn(JTable t) {
        //cara untuk menyesuaikan kolom dari tabel adalah mengambil
        // lebar kolom yang ada kemudian sesuaikan
        TableColumnModel tableColumnModel = t.getColumnModel();

        for (int column = 0; column < tableColumnModel.getColumnCount(); column++) {
            int columnWidthMax = 0;
            for (int row = 0; row < t.getRowCount(); row++) {
                TableCellRenderer tableCellRenderer = t.getCellRenderer(row, column);
                Object tableValue = t.getValueAt(row, column);
                Component component = tableCellRenderer.getTableCellRendererComponent(t, tableValue, false, false, row, column);
                columnWidthMax = Math.max(component.getPreferredSize().width + 5, columnWidthMax);
            }//akhir for baris
            TableColumn tableColumn = tableColumnModel.getColumn(column);
            tableColumn.setPreferredWidth(columnWidthMax + 5);
        }//akhir for kolom
    }

}
