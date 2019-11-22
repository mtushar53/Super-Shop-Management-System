package labib;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import labib.*;

class CustomerTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int QUANTITY_COL = 2;
    private static final int PRICE_COL = 3;

    private String[] columnNames = { "Id", "Name", "Address",
            "Phone No" };
    private List<Customer> customers;

    public CustomerTableModel(List<Customer> theCustomer) {
        customers = theCustomer;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Customer tempCustomer = customers.get(row);

        switch (col) {
            case ID_COL:
                return tempCustomer.getId();
            case NAME_COL:
                return tempCustomer.getName();
            case QUANTITY_COL:
                return tempCustomer.getAddress();
            case PRICE_COL:
                return tempCustomer.getPhoneNo();
            case OBJECT_COL:
                return tempCustomer;
            default:
                return tempCustomer.getId();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
