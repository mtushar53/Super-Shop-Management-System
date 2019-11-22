package labib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import labib.*;

public class Stock_page extends Component {
    static JPanel p6 = new JPanel();
    JButton add,edit,remove,search;
    JTable table;
    JLabel lbl1,id,name,quantity,supId,searchLebel,price;
    JTextField textId,textName,textQuantity,textSupId,textSearch,textPrice;

    StockDao stockDao;

    Stock_page()
    {}
    public void removepan(){
        p6.setVisible(false);
    }
    Stock_page(JPanel p1) {

        try {
            stockDao = new StockDao();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        p1.setVisible(false);
        p1.setVisible(true);

        p6.setBounds(325, 0, 2000, 2000);
        p6.setBackground(Color.RED);
        p6.setLayout(null);


        table = new JTable();

//        Object[] columns = {"Product Id", "Product Name", "Stock Quantity","Suppliers id"};
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(columns);
//        table.setModel(model);
//        table.setBackground(Color.darkGray);
//        table.setForeground(Color.orange);
//        table.setFont(new Font("", 1, 22));
//        table.setRowHeight(30);


        lbl1 = new JLabel("Stock Information");
        lbl1.setBounds(450, 10, 400, 30);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD + Font.PLAIN, 30));
        lbl1.setForeground(Color.orange);

        id = new JLabel("Id");
        name = new JLabel("Name");
        quantity = new JLabel("Quantity");
        supId = new JLabel("Suppliers id");
        searchLebel = new JLabel("Search Product");
        price=new JLabel("Price");
        id.setBounds(80, 480, 50, 30);
        name.setBounds(80, 520, 50, 30);
        quantity.setBounds(80, 560, 80, 30);
        price.setBounds(80, 600, 150, 30);
        supId.setBounds(80, 640, 150, 30);
        searchLebel.setBounds(750, 480, 150, 30);
        id.setFont(new Font("Times New Roman", Font.BOLD, 18));
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));
        quantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
        price.setFont(new Font("Times New Roman", Font.BOLD, 18));
        supId.setFont(new Font("Times New Roman", Font.BOLD, 18));
        searchLebel.setFont(new Font("Times New Roman", Font.BOLD, 18));


        id.setForeground(Color.ORANGE);
        name.setForeground(Color.ORANGE);
        quantity.setForeground(Color.ORANGE);
        price.setForeground(Color.ORANGE);
        supId.setForeground(Color.ORANGE);
        searchLebel.setForeground(Color.ORANGE);

        textId = new JTextField();
        textName = new JTextField();
        textQuantity = new JTextField();
        textSupId = new JTextField();
        textPrice = new JTextField();
        textSearch = new JTextField();

        textId.setBounds(210, 480, 400, 30);
        textName.setBounds(210, 520, 400, 30);
        textQuantity.setBounds(210, 560, 400, 30);
        textPrice.setBounds(210, 600, 400, 30);
        textSupId.setBounds(210, 640, 400, 30);
        textSearch.setBounds(750, 530, 300, 30);

        textId.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textQuantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textSupId.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textPrice.setFont(new Font("Times New Roman",Font.BOLD,18));
        textSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));


        add = new JButton("Add");
        add.setBounds(630, 510, 60, 30);
        edit = new JButton("Edit");
        edit.setBounds(630, 550, 60, 30);
        remove = new JButton("Remove");
        remove.setBounds(630, 590, 80, 30);
        search = new JButton("Search");
        search.setBounds(750, 580, 80, 30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 50, 1130, 400);
        pane.setViewportView(table);

        p6.add(price);
        p6.add(textPrice);
        p6.add(add);
        p6.add(edit);
        p6.add(remove);
        p6.add(lbl1);
        p6.add(pane);
        p6.add(id);
        p6.add(name);
        p6.add(quantity);
        p6.add(supId);
        p6.add(textId);
        p6.add(textName);
        p6.add(textQuantity);
        p6.add(textSupId);
        p6.add(textSearch);
        p6.add(search);
        p6.add(searchLebel);
        p6.setVisible(true);
        p1.add(p6);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStock();
            }
        });


        remove.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // get the selected row
                int row = table.getSelectedRow();

                // make sure a row is selected
                if (row < 0) {
                    JOptionPane.showMessageDialog(Stock_page.this,
                            "You must select an Stock", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // prompt the user
                int response = JOptionPane.showConfirmDialog(
                        Stock_page.this, "Delete this Stock?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response != JOptionPane.YES_OPTION) {
                    return;
                }

                // get the current employee
                Stock tempStock = (Stock) table.getValueAt(row, StockTableModel.OBJECT_COL);

                // delete the employee
                stockDao.deleteStock(tempStock.getId());

                // refresh GUI
                refreshStockView();

                // show success message
                textId.setText(null);
                textName.setText(null);
                textQuantity.setText(null);
                textSupId.setText(null);
                JOptionPane.showMessageDialog(Stock_page.this,
                        "Stock deleted succesfully.", "Stock Deleted",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exc) {
                JOptionPane.showMessageDialog(Stock_page.this,
                        "Error deleting Stock: " + exc.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }


        }
    });


//        edit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();

                StockTableModel model = (StockTableModel) table.getModel();
                textId.setText(model.getValueAt(i, 0).toString());
                textName.setText(model.getValueAt(i, 1).toString());
                textQuantity.setText(model.getValueAt(i, 2).toString());
                textSupId.setText(model.getValueAt(i, 3).toString());

            }
        });

//        edit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int i = table.getSelectedRow();
//
//                if (i >= 0) {
//                    model.setValueAt(textId.getText(), i, 0);
//                    model.setValueAt(textName.getText(), i, 1);
//                    model.setValueAt(textQuantity.getText(), i, 2);
//                    model.setValueAt(textPrice.getText(), i, 3);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Edit error");
//                }
//
//
//            }
//        });


        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = textSearch.getText();

                    ArrayList<Stock> stockList = null;

                    if ((searchText != null) && (searchText.trim().length() > 0)) {
                        stockList = (ArrayList<Stock>) stockDao.searchStock(searchText);
                    } else {
                        stockList = (ArrayList<Stock>) stockDao.getAllStock();
                    }

                    // create the model and update the "table"
                     StockTableModel model = new StockTableModel(stockList);

                    table.setModel(model);

					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(Stock_page.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    protected void saveStock() {

        // get the employee info from gui
        String  id = textId.getText();
        String name = textName.getText();
        String quantity = textQuantity.getText();
        String price = textPrice.getText();
        String supId = textSupId.getText();
        int idVar = Integer.parseInt(id);
        int quantityVar = Integer.parseInt(quantity);
        int supIdVar = Integer.parseInt(supId);
        double priceVar = Double.parseDouble(price);

        Stock tempStock = new Stock(idVar, name, quantityVar,priceVar, supIdVar);

        try {
            // save to the database
            stockDao.addStock(tempStock);

            // close dialog
            setVisible(false);

            // refresh gui list
            refreshStockView();

            // show success message
            textId.setText(null);
            textName.setText(null);
            textQuantity.setText(null);
            textPrice.setText(null);
            textSupId.setText(null);
            JOptionPane.showMessageDialog(this,
                    "Product added succesfully.",
                    "Product Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving employee: "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


    public void refreshStockView() {

        try {
            List<Stock> stocks = stockDao.getAllStock();

            // create the model and update the "table"
            StockTableModel model = new StockTableModel(stocks);

            table.setModel(model);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
