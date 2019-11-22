package labib;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import labib.*;

class StockTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;

    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int QUANTITY_COL = 2;
    private static final int PRICE_COL = 3;
    private static final int SUPPLIERS_COL = 4;

    private String[] columnNames = { "Id", "Name", "Quantity","Product Price" ,"Supplier's Id" };
    private List<Stock> stocks;

    public StockTableModel(List<Stock> theProducts) {
        stocks = theProducts;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return stocks.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {


        Stock tempStock = stocks.get(row);

        switch (col) {
            case ID_COL:
                return tempStock.getId();
            case NAME_COL:
                return tempStock.getName();
            case QUANTITY_COL:
                return tempStock.getQuantity();
            case PRICE_COL:
                return tempStock.getPrice();
            case SUPPLIERS_COL:
                return tempStock.getSupId();
            case OBJECT_COL:
                return tempStock;
            default:
                return tempStock.getId();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
