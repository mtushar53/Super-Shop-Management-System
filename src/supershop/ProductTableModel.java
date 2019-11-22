package labib;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import labib.*;

class ProductTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int QUANTITY_COL = 2;
    private static final int PRICE_COL = 3;

    private String[] columnNames = { "Id", "Name", "Quantity",
            "Price" };
    private List<Stock> products;

    public ProductTableModel(List<Stock> theProducts) {
        products = theProducts;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Stock tempProduct = products.get(row);

        switch (col) {
            case ID_COL:
                return tempProduct.getId();
            case NAME_COL:
                return tempProduct.getName();
            case QUANTITY_COL:
                return tempProduct.getQuantity();
            case PRICE_COL:
                return tempProduct.getPrice();
            case OBJECT_COL:
                return tempProduct;
            default:
                return tempProduct.getId();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
