package labib;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import labib.*;

class SupplierTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int QUANTITY_COL = 2;
    private static final int PRICE_COL = 3;

    private String[] columnNames = { "Id", "Name", "Address",
            "Phone No" };
    private List<Supplier> suppliers;

    public SupplierTableModel(List<Supplier> theSuppliers) {
        suppliers = theSuppliers;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return suppliers.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Supplier tempSupplier = suppliers.get(row);

        switch (col) {
            case ID_COL:
                return tempSupplier.getId();
            case NAME_COL:
                return tempSupplier.getName();
            case QUANTITY_COL:
                return tempSupplier.getAddress();
            case PRICE_COL:
                return tempSupplier.getPhoneNo();
            case OBJECT_COL:
                return tempSupplier;
            default:
                return tempSupplier.getId();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
